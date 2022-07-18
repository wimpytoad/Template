package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.toadfrogson.forgetmenot.R
import com.toadfrogson.forgetmenot.data.model.TaskModel
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
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.primaryContainer,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                icon = {
                    Icon(
                        imageVector = item.second,
                        contentDescription = null,
                        tint = if (pagerState.currentPage == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                    )
                },
                text = {
                    Text(
                        item.first,
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
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


@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, tasksData: List<TaskModel>) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> TabContentScreen(tasksData)
            1 -> TabContentScreen(tasksData)
            2 -> TabContentScreen(tasksData)
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
        taskItems.forEachIndexed { index, item ->
            TaskItem(text = item.title, description = item.description)
        }
    }
}