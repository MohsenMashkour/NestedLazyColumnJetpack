package com.mkrdeveloper.nestedlazycolumnjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkrdeveloper.nestedlazycolumnjetpack.ui.theme.NestedLazyColumnJetpackTheme

class MainActivity : ComponentActivity() {

private val parentItemsList = ArrayList<ParentDataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedLazyColumnJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xF11F1D1E)
                ) {
                    setData()
                    MyApp(modifier = Modifier, parentItemsList)

                }
            }
        }
    }

    private fun setData() {
        val images = listOf(
            ChildDataClass(R.drawable.book1),
            ChildDataClass(R.drawable.book2),
            ChildDataClass(R.drawable.book3),
            ChildDataClass(R.drawable.book4),
            ChildDataClass(R.drawable.book5),
            ChildDataClass(R.drawable.book6),
            ChildDataClass(R.drawable.book7),
            ChildDataClass(R.drawable.book8),
            ChildDataClass(R.drawable.book9),
            ChildDataClass(R.drawable.book10),
            ChildDataClass(R.drawable.book11),
            ChildDataClass(R.drawable.book12),
            ChildDataClass(R.drawable.book13),
            ChildDataClass(R.drawable.book14)
        )
        parentItemsList.add(ParentDataClass("Novel:", images))
        parentItemsList.add(ParentDataClass("Best Seller:", images.shuffled()))
        parentItemsList.add(ParentDataClass("History:", images.shuffled()))
        parentItemsList.add(ParentDataClass("Favorite:", images.reversed()))
        parentItemsList.add(ParentDataClass("Drama:", images.shuffled()))
        parentItemsList.add(ParentDataClass("Crime:", images))
    }
}

@Composable
fun MyApp(modifier: Modifier.Companion, parentItemsList: ArrayList<ParentDataClass>) {
    LazyColumn(contentPadding = PaddingValues(15.dp)){
        items(parentItemsList.size){it->
            ColumnItemUI(
                modifier = modifier,
                parentItemsList = parentItemsList,
                itemIndex = it
            )
        }
    }
}

@Composable
fun ColumnItemUI(
    modifier: Modifier.Companion,
    parentItemsList: ArrayList<ParentDataClass>,
    itemIndex: Int
) {
    Card(modifier.padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        )) {
        Text(
            text = parentItemsList[itemIndex].title,
            modifier.padding(12.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFC107)
            )
        LazyRow(contentPadding = PaddingValues(12.dp)){
            items(parentItemsList[itemIndex].childList.size){it->
                RowItemUI(
                    modifier = modifier,
                    childList = parentItemsList[itemIndex].childList,
                    rowItemIndex = it
                )
            }
        }
    }
}

@Composable
fun RowItemUI(modifier: Modifier.Companion, childList: List<ChildDataClass>, rowItemIndex: Int) {
    Box(
        modifier
            .height(200.dp)
            .width(160.dp)
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xF1201E1F))
    ){
        Image(painter = painterResource(id = childList[rowItemIndex].image),
            contentDescription = "",
            modifier.fillMaxSize())
    }
}

