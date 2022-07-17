package com.toadfrogson.forgetmenot.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.toadfrogson.forgetmenot.data.entity.SingleTaskEntity
import com.toadfrogson.forgetmenot.ui.components.Tabs
import com.toadfrogson.forgetmenot.ui.components.TabsContent
import com.toadfrogson.forgetmenot.ui.theme.Primary
import com.toadfrogson.forgetmenot.ui.theme.SecondaryTextColor
import com.toadfrogson.forgetmenot.ui.theme.WhiteBackgroundColor
import com.toadfrogson.forgetmenot.viewmodel.TasksViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import kotlin.time.ExperimentalTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(viewModel: TasksViewModel = getViewModel()) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    val taskList = viewModel.getTasks()
    BottomSheetScaffold(
        floatingActionButton = {
            AddNewTaskButton(scope = coroutineScope, bottomSheetScaffoldState = bottomSheetScaffoldState)
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text(text = "Hello from sheet")
            }
        }, sheetPeekHeight = 0.dp
    ) {
        TaskTabs(taskList)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddNewTaskButton(scope: CoroutineScope, bottomSheetScaffoldState: BottomSheetScaffoldState) {
    var buttonIcon = if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) Icons.Default.Add
    else Icons.Default.Done
    FloatingActionButton(modifier = Modifier.padding(bottom = 100.dp), onClick = {
        scope.launch {

            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                bottomSheetScaffoldState.bottomSheetState.expand()
            } else {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        }
    }) {
        Icon(imageVector = buttonIcon, contentDescription = null)
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalTime::class, ExperimentalUnitApi::class)
@Composable
@UiComposable
fun TaskTabs(taskList: List<SingleTaskEntity>) {
    taskList.size
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
