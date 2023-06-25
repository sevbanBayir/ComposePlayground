package com.sevban.composetutorialssevbanbayir.LazyLayouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Composable
fun LazyList1() {

    LazyColumn(
        //give a padding to list and avoid content to be clipped:
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp)
    ) {
        /*...*/
    }

    LazyColumn(
        //normally our items are glued each other, to add a space between them:
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        /*...*/
    }

    LazyColumn(
        //useful information about lazy list's state:
        state = rememberLazyListState()
    ) {
        /*...*/
    }

    //but... to ensure our state is remembered across recomposition hoist your state:
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState
    ) {
        /*...*/
    }

    /**use this useful information that lazylistState provides to create a button that helps user
    to scroll to top of the list:*/
    /**
     * to avoid unnecessary recomposition; wrap your calculation about lazyListState with remember
     * and derivedStateOf
     * */
    val showScrollToTopButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    /**lazyListState provides other useful informations via its layout info object:*/
    lazyListState.layoutInfo.totalItemsCount
    lazyListState.layoutInfo.visibleItemsInfo
    //use it to fetch the indices of all currently visible items:
    //DON'T USE IT AS IT IS ! ALL OF THEM MUST BE WRAPPED WITH REMEMBER AND DERIVEDSTATEOF
    val indicesOfCurrentVisibles: List<Int> = lazyListState.layoutInfo.visibleItemsInfo.map {
        it.index
    }


    /**LazyGrid customized cell columns:*/
    LazyVerticalGrid(
        columns = object : GridCells {
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn =
                    (availableSize - spacing) * 2 / 3
                val secondColumn = availableSize - firstColumn
                return listOf(firstColumn, secondColumn)
            }

        }) {

        //For items needs special column sizing:
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            //for example: a header which needs occupy full width of the screen
        }

        //if we want to give differenet column span to items that meets specific conditions:
        /*items(size, span = { GridItemSpan(if (it.isOdd) 2 else 1) }) {
            item -> ItemCard(items[item])
        }*/

        //don't use 0px height
        //use contentType parameter of items DSL in lazyLayouts when you use multiple typed items
        //in your lists to help reusing compositions.
    }
}