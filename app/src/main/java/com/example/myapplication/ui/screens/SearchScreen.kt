package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect


@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        // 顶部热门搜索词
        TopSearchTerms()
        
        // 热门话题区域（新增）
        HotTopics()
        
        // 中间广告轮播
        AdCarousel()
        
        // 底部内容流
        ContentFeed()
    }
}

@Composable
private fun TopSearchTerms() {
    val searchTerms = listOf(
        "发现", "趋势", "问题", "榜单",
        "今日热搜", "微博热搜", "实时新闻"
    )
    
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(searchTerms) { term ->
            Text(
                text = term,
                modifier = Modifier.clickable { /* 处理点击 */ },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun HotTopics() {
    var currentPage by remember { mutableStateOf(0) }
    val allTopics = listOf(
        // 第一页话题
        listOf(
            Pair("宁夏一地成群野狗追咬", "新"),
            Pair("猎罪图鉴2已经是next", "热"),
            Pair("硕士论文写导生关系被", ""),
            Pair("交钱参加族葬科技展", "推荐"),
            Pair("博德之门3因TGA遭大", ""),
            Pair("南京大屠杀证据增加了", "热")
        ),
        // 第二页话题
        listOf(
            Pair("新发现的黑洞质量巨大", "热"),
            Pair("AI技术新突破引热议", "新"),
            Pair("冬季养生小妙招大全", ""),
            Pair("年度十大科技新闻", "推荐"),
            Pair("新能源汽车市场分析", ""),
            Pair("元宇宙发展新动向", "热")
        ),
        // 第三页话题
        listOf(
            Pair("量子计算机研究进展", "新"),
            Pair("海洋生态保护行动", "热"),
            Pair("未来城市建设规划", ""),
            Pair("数字经济发展趋势", "推荐"),
            Pair("生物技术创新成果", ""),
            Pair("教育改革新方向", "热")
        ),
        // 第四页话题
        listOf(
            Pair("人工智能伦理讨论", "新"),
            Pair("可持续发展新方案", "热"),
            Pair("区块链技术应用", ""),
            Pair("智慧城市建设进展", "推荐"),
            Pair("新材料研究突破", ""),
            Pair("远程办公新趋势", "热")
        ),
        // 第五页话题
        listOf(
            Pair("太空旅行新进展", "新"),
            Pair("环保技术新突破", "热"),
            Pair("未来教育新模式", ""),
            Pair("智能家居新方案", "推荐"),
            Pair("生物医药研究", ""),
            Pair("机器人技术应用", "热")
        )
    )
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 标题栏
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "石头热搜",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "查看详情",
                modifier = Modifier.clickable { /* 处理点击 */ },
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        val lazyListState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        // 监听滚动状态
        LaunchedEffect(lazyListState.firstVisibleItemIndex) {
            currentPage = lazyListState.firstVisibleItemIndex
        }

        // 话题列表
        LazyRow(
            state = lazyListState,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = true
        ) {
            items(allTopics.size) { pageIndex ->
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            scope.launch {
                                lazyListState.animateScrollToItem(pageIndex)
                                currentPage = pageIndex
                            }
                        }
                ) {
                    // 每页6个话题，3行2列
                    for (i in 0..2) {  // 3
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // 左侧话题
                            TopicItem(
                                topic = allTopics[pageIndex][i * 2].first,
                                tag = allTopics[pageIndex][i * 2].second,
                                modifier = Modifier.weight(1f)
                            )
                            // 右侧话题
                            TopicItem(
                                topic = allTopics[pageIndex][i * 2 + 1].first,
                                tag = allTopics[pageIndex][i * 2 + 1].second,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 页面指示器
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(
                            if (currentPage == index)
                                Color(0xFF1976D2)
                            else
                                Color(0xFFBDBDBD)
                        )
                        .clickable {
                            scope.launch {
                                lazyListState.animateScrollToItem(index)
                                currentPage = index
                            }
                        }
                )
            }
        }
    }
}

@Composable
private fun TopicItem(
    topic: String,
    tag: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = topic,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        if (tag.isNotEmpty()) {
            Text(
                text = tag,
                color = when (tag) {
                    "热" -> Color.Red
                    "新" -> Color.Blue
                    else -> MaterialTheme.colorScheme.primary
                },
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun AdCarousel() {
    var currentAd by remember { mutableStateOf(0) }
    val ads = listOf(
        "广告1",
        "广告2",
        "广告3"
    )
    
    LaunchedEffect(Unit) {
        while(true) {
            delay(3000)
            currentAd = (currentAd + 1) % ads.size
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { /* 处理广告点击 */ },
        contentAlignment = Alignment.Center
    ) {
        Text(ads[currentAd])
    }
}

@Composable
private fun ContentFeed() {
    val posts = remember {
        List(10) { index ->
            Post(
                id = index,
                userName = "用户 $index",
                userAvatar = "",
                content = "这是第 $index 条内容",
                likes = (100..999).random(),
                comments = (10..99).random(),
                favorites = (1..50).random()
            )
        }
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(posts) { post ->
            PostCard(post)
        }
    }
}

@Composable
private fun PostCard(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 用户信息
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* 跳转到用户页面 */ }
                ) {
                    // 头像
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    
                    // 用户名
                    Text(
                        text = post.userName,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            
            // 内容图片
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { /* 处理图片点击 */ }
            )
            
            // 底部操作栏
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 点赞按钮和数量
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = { /* 处理点赞 */ },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(4.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "点赞",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "1.2k",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // 评论按钮和数量
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = { /* 处理评论 */ },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(4.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "评论",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "328",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // 收藏按钮和文字
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = { /* 处理收藏 */ },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(4.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "收藏",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "收藏",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

data class Post(
    val id: Int,
    val userName: String,
    val userAvatar: String,
    val content: String,
    val likes: Int,
    val comments: Int,
    val favorites: Int
) 