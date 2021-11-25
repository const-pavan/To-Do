package com.pansgami.to_do

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.ads.MobileAds
import com.pansgami.to_do.data.navigation.SetupNavigation
import com.pansgami.to_do.ui.theme.ToDoTheme
import com.pansgami.to_do.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel:SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAds.initialize(this) {}
            ToDoTheme {
                navController = rememberAnimatedNavController()
                SetupNavigation(
                    navController= navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}
