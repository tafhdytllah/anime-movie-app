package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)



        return binding.root
    }




}