package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.toadfrogson.forgetmenot.R
import com.toadfrogson.forgetmenot.model.TaskModel
import com.toadfrogson.forgetmenot.ui.theme.*
import kotlinx.coroutines.launch


@Composable
@ExperimentalPagerApi
fun Tabs(pagerState: PagerState) {
    val tabItems = listOf(
        stringResource(id = R.string.task_tab_generic) to Icons.Default.ShoppingCart,
        stringResource(id = R.string.task_tab_business) to Icons.Default.Phone,
        stringResource(id = R.string.task_tab_social) to Icons.Default.Person
    )

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = WhiteBackgroundColor,
        contentColor = SecondaryTextColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                icon = {
                    Icon(imageVector = item.second, contentDescription = null)
                },
                text = {
                    Text(
                        item.first,

                        color = if (pagerState.currentPage == index) SecondaryDarkColor else SecondaryColor
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}
fun generateMocks(): List<TaskModel> {
    val mockedTasks = mutableListOf<TaskModel>()
    mockedTasks.add(TaskModel("First Task", "it's short but meaningful description"))
    mockedTasks.add(TaskModel("Second Task", "it's short but meaningful description"))
    mockedTasks.add(TaskModel("Third Task", "it's short but meaningful description"))
    mockedTasks.add(TaskModel("Fourth Task", "it's short but meaningful description"))
    return mockedTasks
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) {
            page ->
        when (page) {
            0 -> TabContentScreen(generateMocks())
            1 -> TabContentScreen(generateMocks())
            2 -> TabContentScreen(generateMocks())
        }
    }
}

@Composable
fun TabContentScreen(taskItems: List<TaskModel>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        taskItems.forEachIndexed{ index, item ->
            TaskItem(text = item.title, description = item.description)
        }
    }
}