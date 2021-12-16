package com.example.straightenup

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.straightenup.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
        const val channel_name = "my_channel";
        const val description = "This is my channel";
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////        createNotificationChannel()
////
////        binding.selectTimeButton.setOnClickListener {
////            showTimePicker()
////        }
////        binding.setAlarmButton.setOnClickListener {
////            setAlarm()
////        }
////        binding.cancelAlarmButton.setOnClickListener {
////
////            cancelAlarm()
////        }
//    }
//
////    private fun showTimePicker() {
////        picker = MaterialTimePicker.Builder()
////            .setTimeFormat(TimeFormat.CLOCK_24H)
////            .setHour(12)
////            .setMinute(0)
////            .setTitleText("Select Alarm Time")
////            .build()
////        picker.show(supportFragmentManager, "reded")
////
//////        picker.addOnPositiveButtonClickListener {
//////            if (picker.hour > 12) {
//////                binding.selectedTime.text =
//////                String.format("%02d", picker.hour - 12) + " : " + String.format(
//////                    "%02d",
//////                    picker.minute
//////                ) + "PM"
//////            } else {
//////                String.format("%02d", picker.hour) + " : " + String.format(
//////                    "%02d",
//////                    picker.minute
//////                ) + "AM"
//////            }
////
////            calendar = Calendar.getInstance()
////            calendar[Calendar.HOUR_OF_DAY] = picker.hour
////            calendar[Calendar.MINUTE] = picker.minute
////            calendar[Calendar.SECOND] = 0
////            calendar[Calendar.MILLISECOND] = 0
////        }
////
//
//    }
//
//    private fun setAlarm() {
//        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//        val intent = Intent(this, AlarmReceiver::class.java)
//
//        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
//            pendingIntent
//        )
//
//        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun cancelAlarm() {
//        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//        val intent = Intent(this, AlarmReceiver::class.java)
//
//        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//
//        alarmManager.cancel(pendingIntent)
//
//        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // Create the NotificationChannel
//            val name = channel_name
//            val descriptionText = description
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
//            mChannel.description = descriptionText
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(mChannel)
//        }
//    }

}

