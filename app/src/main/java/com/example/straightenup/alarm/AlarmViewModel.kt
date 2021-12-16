package com.example.straightenup.alarm

import android.app.AlarmManager
import androidx.core.graphics.convertTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*
import kotlin.time.Duration.Companion.hours

class AlarmViewModel : ViewModel() {

    private lateinit var alarmManager: AlarmManager

    private lateinit var calendar: Calendar
    private val _selectedTime = MutableLiveData<String>()
    val selectedTime: LiveData<String>
        get() = _selectedTime



    init
    {
        _selectedTime.value = "08:15 PM"



    }

    private fun setTime(){
        var currentTime : Long = Calendar.getInstance().timeInMillis

        var outputFmt = SimpleDateFormat("HH:mm ")
        var dateAsString = outputFmt.format(currentTime)


        _selectedTime.value = dateAsString

    }

    fun setAlarm(){
        setTime()
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            pendingIntent
        )

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show()

    }
}