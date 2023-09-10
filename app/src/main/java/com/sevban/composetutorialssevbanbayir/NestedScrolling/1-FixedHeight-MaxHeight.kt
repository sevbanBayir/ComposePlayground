package com.sevban.composetutorialssevbanbayir.NestedScrolling

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LazyListSample() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = Color(0xffE3F2FD)
            )
            .padding(12.dp),
        contentPadding = PaddingValues(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        itemsIndexed(
            listOf(
                "Claim requested credentials",
                "Claim received credentials",
                "Pending Requests",

                "Claim requested credentials",
                "Claim received credentials",
                "Pending Requests",

                "Claim requested credentials",
                "Claim received credentials",
                "Pending Requests",
            )
        ) { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                shape = RoundedCornerShape(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 12.dp)
                        .heightIn(max = 100.dp)
                ) {
                    ManageCredentialHeader(text = item, vcsNo = 2)
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(listOf(1, 2, 3, 4, 5, 6)) { index, item ->
                            ManageCredentialItem()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ManageCredentialHeader(text: String, vcsNo: Int) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text)
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.Blue, CircleShape)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                vcsNo.toString(),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}

@Composable
private fun ManageCredentialItem() {
    Row(modifier = Modifier.padding(top = 16.dp)) {
        Column() {
            Text("Module 1 some text")

            Text("Subtitle")

        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "VIEW",
            modifier = Modifier
                .background(Color(0xff283593), RoundedCornerShape(8.dp))
                .padding(6.dp),
            color = Color.White
        )
    }
}