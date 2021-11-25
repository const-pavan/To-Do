package com.pansgami.to_do.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pansgami.to_do.R
import com.pansgami.to_do.Util.Constants.SPLASH_SCREEN_DELAY
import com.pansgami.to_do.ui.theme.LOGO_SIZE
import com.pansgami.to_do.ui.theme.SplashScreenBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit,
){
    var startAnimatable by remember { mutableStateOf(false)    }
    val offsetState by animateDpAsState(
        targetValue = if(startAnimatable) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val alphaState by animateFloatAsState(
        targetValue = if(startAnimatable) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 =true ){
        startAnimatable=true
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.SplashScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.
                size(LOGO_SIZE)
                .offset(y=offsetState)
                .alpha(alpha = alphaState),
            painter = painterResource(id = getLogo()),
            contentDescription = stringResource(id = R.string.to_do_logo)
        )
    }
}
@Composable
fun getLogo():Int{
    return if(isSystemInDarkTheme()){
        R.drawable.ic_logo_dark
    }
    else
    {
        R.drawable.ic_logo_light
    }
}