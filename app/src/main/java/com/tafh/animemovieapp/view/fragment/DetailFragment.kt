package com.tafh.animemovieapp.view.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.data.model.Genre
import com.tafh.animemovieapp.data.model.Studio
import com.tafh.animemovieapp.data.response.AnimeResponse
import com.tafh.animemovieapp.databinding.FragmentDetailBinding
import com.tafh.animemovieapp.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okio.IOException
import java.net.URL

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val malId = args.malId

        viewModel.getDetail(malId).observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                val anime = it.body()
                setDataUi(anime)
            } else {
                Log.d("LOG", "error = ${it.message()}")
            }
        })

        binding.toolbar.setNavigationOnClickListener{ view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setDataUi(anime: AnimeResponse?) {
        binding.apply {
            ivImageDetail.load(anime!!.imageUrl) {
                crossfade(true)
                crossfade(1000)
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_image_error)
            }

            val imageUrl = anime.imageUrl

            ivImage.load(imageUrl) {
                crossfade(true)
                crossfade(1000)
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_image_error)
            }
            tvTitle.text = anime.title

            tvRilis.text = anime.aired.string
            tvStatus.text = anime.status
            tvScore.text = anime.score.toString()
            tvDuration.text = anime.duration

            val studioList = studioString(anime.studios)
            tvStudios.text = studioList

            val genreList = genreString(anime.genres)
            tvGenres.text = genreList

            tvSynopsis.text = anime.synopsis
        }
    }

    private fun studioString(list: List<Studio>): String{

        var studio =""
        for(i in list.indices) {
            val string = list[i].name
            if (i != 0) {
                studio += ", $string"
            } else {
                studio = string
            }
        }
        return studio
    }

    private fun genreString(list: List<Genre>): String {

        var genre = ""

        for(i in list.indices) {
            val string = list[i].name
            if (i != 0) {
                genre += ", $string"
            } else {
                genre = string
            }

        }

        return genre
    }


}