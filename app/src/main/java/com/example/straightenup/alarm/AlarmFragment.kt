package com.example.straightenup.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.straightenup.AlarmReceiver
import com.example.straightenup.R
import com.example.straightenup.databinding.FragmentAlarmBinding
import java.util.*
import androidx.lifecycle.LiveData
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat


class AlarmFragment : Fragment() {

    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
        const val channel_name = "my_channel";
        const val description = "This is my channel";
    }

    private lateinit var binding: FragmentAlarmBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var calendar: Calendar
    private lateinit var pendingIntent: PendingIntent
    private lateinit var picker: MaterialTimePicker
    private lateinit var alarmViewModel: AlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_alarm,
            container,
            false
        )
        alarmViewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)
        createNotificationChannel()


        binding.alarmViewModel = alarmViewModel
        binding.lifecycleOwner = this


        alarmViewModel.eventSetAlarm.observe(viewLifecycleOwner, Observer {
            if (it) {
                setAlarm(alarmViewModel.currentTime)
                alarmViewModel.eventSetAlarmCompleted()
            }
        })

        alarmViewModel.eventCanceledAlarm.observe(viewLifecycleOwner, Observer {
            if (it) {
                cancelAlarm()
                alarmViewModel.eventCancelAlarmCompleted()
            }
        })

        alarmViewModel.eventShowTimePicker.observe(viewLifecycleOwner, Observer {
            if (it) {
                showTimePicker()
                alarmViewModel.eventShowTimePickerCompleted()
            }
        })

        return binding.root
    }

    private fun setAlarm(timeInMillis: LiveData<Long>) {
        var time = timeInMillis.value!!.toLong()
        if (this::calendar.isInitialized) {
            time = calendar.timeInMillis
        }
        val intent = Intent(context, AlarmReceiver::class.java)
        alarmManager = activity?.getSystemService(ALARM_SERVICE) as AlarmManager
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            time,
            AlarmManager.INTERVAL_HALF_HOUR,
            pendingIntent
        )

        Toast.makeText(context, "Alarm set  ${formatTimeToString(time)}", Toast.LENGTH_SHORT).show()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channel_name
            val descriptionText = description
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText

            val notificationManager =
                activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun cancelAlarm() {
        alarmManager = activity?.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "Alarm Cancelled", Toast.LENGTH_SHORT).show()
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(childFragmentManager, "reded")

        picker.addOnPositiveButtonClickListener {
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

            alarmViewModel._selectedTime.value = formatTimeToString(calendar.timeInMillis)
        }


    }

    private fun formatTimeToString(time: Long): String = SimpleDateFormat("HH:mm").format(time)


}