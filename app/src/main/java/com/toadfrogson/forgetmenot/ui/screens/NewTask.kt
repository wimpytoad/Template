package com.toadfrogson.forgetmenot.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.toadfrogson.forgetmenot.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTask () {
    Scaffold(
        floatingActionButton = {
            AddNewTaskButton()
        }
    ){
        TaskTabs()
    }
}

@Composable
fun AddNewTaskButton() {
    FloatingActionButton(onClick = { println("button pressed") }) {
        Icon (painter = painterResource(id = R.drawable.ic_plus),
            modifier = Modifier.size(30.dp),
            contentDescription = null)
    }
}

@Composable
@UiComposable
fun TaskTabs() {
    var state = remember { mutableStateOf(0) }
    val titles = listOf(stringResource(id = R.string.task_tab_generic), stringResource(id = R.string.task_tab_business), stringResource(id = R.string.task_tab_social))
    Column(modifier = Modifier.padding(start = 2.dp, end = 2.dp, top = 16.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Top) {
        TabRow(selectedTabIndex = state.value) {
            titles.forEachIndexed { index, title ->
                Tab(selected = state.value == index,
                    onClick = { state.value = index }
                ) {
                    Text(text = title)
                }
            }
        }
    }
}
