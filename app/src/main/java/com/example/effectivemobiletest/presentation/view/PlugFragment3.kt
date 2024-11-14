package com.example.effectivemobiletest.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentPlug2Binding
import com.example.effectivemobiletest.databinding.FragmentPlug3Binding

class PlugFragment3 : Fragment() {


    private var _binding: FragmentPlug3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlug3Binding.inflate(inflater, container, false)

        return binding.root
    }

}