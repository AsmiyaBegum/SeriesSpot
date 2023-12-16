package com.ab.seriesspot.splash

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.ab.seriesspot.MainActivity
import com.ab.seriesspot.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashContent(onNavigateToMain = { navigateToMainActivity() })
        }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun SplashContent(onNavigateToMain: () -> Unit) {
    LaunchedEffect(true) {
        // Simulate a delay or perform initialization tasks
        delay(2000) // Simulating a delay, adjust as needed

        // Once the delay is complete, navigate to MainActivity
        onNavigateToMain()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_seriesspot_logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(0.8f),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun PreviewSplashContent() {
    SplashContent(){

    }
}
