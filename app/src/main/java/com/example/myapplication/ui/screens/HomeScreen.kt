package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.rememberLazyListState

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // 分类标签栏
        CategoryTabs()
        
        // 主内容区域
        ContentList()
    }
}

@Composable
private fun CategoryTabs() {
    var selectedCategory by remember { mutableStateOf(0) }
    val categories = listOf("推荐", "视频", "直播", "穿搭", "情感", "头像", "搞笑")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories.size) { index ->
            TextButton(
                onClick = { selectedCategory = index },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(
                    text = categories[index],
                    color = if (selectedCategory == index) 
                        MaterialTheme.colorScheme.primary
                    else 
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun ContentList() {
    // 增加更多模拟数据
    val items = listOf(
        FeedItem("你只要围着我转就行了 你哪来这么多自己的事情要干", "小欣碎了", 4944),
        FeedItem("跋山涉水就为这口牛排", "树下汉堡", 244),
        FeedItem("一起散步吧..", "甜品", 2400),
        FeedItem("今天的晚霞真美", "摄影师小王", 1234),
        FeedItem("分享一个超简单的料理方式", "美食达人", 3456),
        FeedItem("周末去看了场演唱会", "音乐迷", 5678),
        FeedItem("新买的相机体验报告", "数码控", 890),
        FeedItem("今日穿搭分享", "时尚博主", 2345),
        FeedItem("读书笔记分享", "爱看书的猫", 1567),
        FeedItem("旅行vlog第一期", "旅行者", 4321)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),  // 确保填满可用空间
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = rememberLazyListState()  // 添加状态以支持滚动
    ) {
        items(items) { item ->
            FeedItemCard(item)
        }
    }
}

@Composable
private fun FeedItemCard(item: FeedItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* 处理点击事件 */ },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column {
            // 图片占位符
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
            
            // 文字内容
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.author,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "${item.likes}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

// 数据类
data class FeedItem(
    val title: String,
    val author: String,
    val likes: Int
) 