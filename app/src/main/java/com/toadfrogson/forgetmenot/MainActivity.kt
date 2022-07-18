package com.toadfrogson.forgetmenot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.toadfrogson.forgetmenot.ui.screens.MainView
import com.toadfrogson.forgetmenot.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                    MainView()
            }
        }
    }
}
