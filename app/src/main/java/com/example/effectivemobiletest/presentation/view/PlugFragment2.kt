package com.example.effectivemobiletest.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentPlug2Binding
import com.example.effectivemobiletest.databinding.FragmentPlug3Binding


class PlugFragment2 : Fragment() {

    private var _binding: FragmentPlug2Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlug2Binding.inflate(inflater, container, false)

        return binding.root
    }

}