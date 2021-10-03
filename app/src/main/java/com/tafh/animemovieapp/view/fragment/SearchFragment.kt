package com.tafh.animemovieapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.databinding.FragmentSearchBinding
import com.tafh.animemovieapp.databinding.FragmentTopListBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)


        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

    }


}