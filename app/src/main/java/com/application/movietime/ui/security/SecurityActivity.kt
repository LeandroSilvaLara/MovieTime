package com.application.movietime.ui.security

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.coroutineScope
import com.application.movietime.base.BaseActivity
import com.application.movietime.ui.theme.MoviesAppTheme
import com.application.movietime.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SecurityActivity: BaseActivity() {

    companion object {
        fun startActivity(activity: Activity?) {
            val intent = Intent(activity, SecurityActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()

        lifecycle.coroutineScope.launch {
            profileViewModel.isDarkMode.collect {
                setContent {
                    MoviesAppTheme(darkTheme = it.data) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.scrim
                        ) {
                            SecurityApp()
                        }
                    }
                }
            }
        }
    }
}