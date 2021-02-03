package com.tafh.animemovieapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.tafh.animemovieapp.R
import com.tafh.animemovieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupNavigation()
        }


    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun setupNavigation() {
        //find the nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHosFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        //setup the action bar
//        setSupportActionBar(binding.toolbar)

        //setup the action bar
//        setupActionBarWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
//            val toolBar = supportActionBar ?: return@addOnDestinationChangedListener
//            when(destination.id) {
//                R.id.beranda -> {
//                    toolBar.apply {
//                        show()
//                        setTitle("Anime")
//                        setDisplayShowTitleEnabled(true)
//
//                    }
//                }
//                else -> {
//                    toolBar.apply {
//                        show()
//                        setDisplayShowTitleEnabled(true)
//                        setHomeAsUpIndicator(R.drawable.ic_arrow_back)
//                        setDisplayHomeAsUpEnabled(true)
//                    }
//                }
//            }
//
//        }
    }

}