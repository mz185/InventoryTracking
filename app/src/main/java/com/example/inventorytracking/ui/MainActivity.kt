package com.example.inventorytracking.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.inventorytracking.R.id.nav_host_fragment_content_main
import com.example.inventorytracking.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var appBarConfiguration: AppBarConfiguration? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).run {
            setContentView(root)
            setSupportActionBar(toolbar)
        }

        navController = findNavController(nav_host_fragment_content_main).apply {
            appBarConfiguration = AppBarConfiguration(graph).also {
                setupActionBarWithNavController(this, it)
            }
        }
    }

    override fun onSupportNavigateUp() =
        appBarConfiguration?.let {
            navController?.navigateUp(it)
        } == true || super.onSupportNavigateUp()
}
