package com.example.straightenup.alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.straightenup.R
import com.example.straightenup.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment()
{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val binding: FragmentAlarmBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_alarm,
            container,
            false
        )

        val alarmViewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        binding.alarmViewModel = alarmViewModel
        binding.lifecycleOwner = this


        return binding.root
    }
}