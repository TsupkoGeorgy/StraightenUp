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

class AlarmViewModel : ViewModel()
{

    private lateinit var alarmManager: AlarmManager

    private lateinit var calendar: Calendar


    //Костыль, из фрагмента нельзя обращаться к viewmodel
    //
    val _selectedTime = MutableLiveData<String>()
    val selectedTime: LiveData<String>
        get() = _selectedTime

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _eventSetAlarm = MutableLiveData<Boolean>()
    val eventSetAlarm: LiveData<Boolean>
        get() = _eventSetAlarm

    private val _eventCancelAlarm = MutableLiveData<Boolean>()
    val eventCanceledAlarm: LiveData<Boolean>
        get() = _eventCancelAlarm

    private val _eventShowTimePicker = MutableLiveData<Boolean>()
    val eventShowTimePicker: LiveData<Boolean>
        get() = _eventShowTimePicker


    init
    {
        setTime()
    }

    private fun setTime()
    {
        _currentTime.value = Calendar.getInstance().timeInMillis + 900000L
        var outputFmt = SimpleDateFormat("HH:mm")
        var dateAsString = outputFmt.format(currentTime.value)
        _selectedTime.value = dateAsString
    }

    fun eventSetAlarm()
    {
        _eventSetAlarm.value = true
    }

    fun eventSetAlarmCompleted()
    {
        _eventSetAlarm.value = false
    }

    fun eventCancelAlarm()
    {
        _eventCancelAlarm.value = true
    }

    fun eventCancelAlarmCompleted()
    {
        _eventCancelAlarm.value = false
    }

    fun eventShowTimePicker()
    {
        _eventShowTimePicker.value = true
    }

    fun eventShowTimePickerCompleted()
    {
        _eventShowTimePicker.value = false
    }
}