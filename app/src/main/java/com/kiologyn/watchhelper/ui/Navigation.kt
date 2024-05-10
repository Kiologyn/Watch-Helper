package com.kiologyn.watchhelper.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kiologyn.watchhelper.ui.page.ApplicationPage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation() {
    HorizontalPager(state = rememberPagerState(
        pageCount = { ApplicationPage.entries.size })
    ) { pageIndex: Int ->
        ApplicationPage.entries[pageIndex].content()
    }
}