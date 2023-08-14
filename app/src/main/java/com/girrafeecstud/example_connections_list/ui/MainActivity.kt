/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.girrafeecstud.dependency_coordinator_impl.UnitCreationRequestHandler
import com.girrafeecstud.dependency_coordinator_impl.UnitCreationRequestObserver
import com.girrafeecstud.dependency_coordinator_impl.UnitCreationRequestProvider
import com.girrafeecstud.example_connections_list.R
import com.girrafeecstud.example_connections_list.databinding.ActivityMainBinding
import com.girrafeecstud.example_connections_list.di.AppComponent
import com.girrafeecstud.navigation_impl.FlowDestination
import com.girrafeecstud.navigation_impl.navigator.FlowNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment

        navController = navHostFragment.navController
    }
}