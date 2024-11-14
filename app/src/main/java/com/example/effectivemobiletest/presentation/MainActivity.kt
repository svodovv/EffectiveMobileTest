package com.example.effectivemobiletest.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.ActivityMainBinding
import com.example.effectivemobiletest.presentation.viewmodel.NavigationMenuViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navBarViewModel by viewModel<NavigationMenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                view.paddingLeft,
                view.paddingTop + systemBarsInsets.top,
                view.paddingRight,
                view.paddingBottom
            )

            ViewCompat.setOnApplyWindowInsetsListener(binding.root, null)
            insets
        }

        configureNavigation()
    }

    private fun configureNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        val badgeDrawable = binding.navigationView.getOrCreateBadge(R.id.favoriteFragment)

        binding.navigationView.setupWithNavController(navController)

        lifecycleScope.launch {
            navBarViewModel.state.collect{ count ->
                if (count > 0) {
                    badgeDrawable.backgroundColor = getColor(R.color.red)
                    badgeDrawable.isVisible = true
                    badgeDrawable.number = count
                }
                else{
                    badgeDrawable.isVisible = false
                    badgeDrawable.clearNumber()
                }
            }
        }


    }
}