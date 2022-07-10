package com.toadfrogson.forgetmenot.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.toadfrogson.forgetmenot.ui.components.Tabs
import com.toadfrogson.forgetmenot.ui.components.TabsContent
import com.toadfrogson.forgetmenot.ui.components.TextView
import com.toadfrogson.forgetmenot.ui.theme.Primary
import com.toadfrogson.forgetmenot.ui.theme.SecondaryTextColor
import com.toadfrogson.forgetmenot.ui.theme.WhiteBackgroundColor
import kotlin.time.ExperimentalTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    Scaffold(
        floatingActionButton = {
            AddNewTaskButton()
        }
    ) {
        TaskTabs()
    }
}

@Composable
fun AddNewTaskButton() {
    FloatingActionButton(onClick = { println("button pressed") }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalTime::class, ExperimentalUnitApi::class)
@Composable
@UiComposable
fun TaskTabs() {
    //TODO: refactor to have actual size of tab columns set by user
    val pagerState = rememberPagerState(pageCount = 3)
    Column(modifier = Modifier.background(WhiteBackgroundColor)) {
        TopAppBar(backgroundColor = Primary) {
            Column(
                modifier = Modifier.fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Your Notes, sire",
                    style = TextStyle(color = SecondaryTextColor),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(
                        18F,
                        TextUnitType.Sp
                    ),
                    modifier = Modifier.padding(all = 5.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Tabs(pagerState = pagerState)

        TabsContent(pagerState = pagerState)
    }
}
