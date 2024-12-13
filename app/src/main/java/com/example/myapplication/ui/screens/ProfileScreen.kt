package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                // 头像占位符
            }
            
            Text(
                text = "John Doe",
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 24.sp
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileMenuItem(
                    icon = Icons.Default.List,
                    label = "我的订单",
                    onClick = { /* 处理点��事件 */ }
                )
                ProfileMenuItem(
                    icon = Icons.Default.Person,
                    label = "常访问的人",
                    onClick = { /* 处理点击事件 */ }
                )
                ProfileMenuItem(
                    icon = Icons.Default.ShoppingCart,
                    label = "购物车",
                    onClick = { /* 处理点击事件 */ }
                )
                ProfileMenuItem(
                    icon = Icons.Default.Settings,
                    label = "我的钱包",
                    onClick = { /* 处理点击事件 */ }
                )
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            var selectedTab by remember { mutableStateOf(0) }
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // a 部分：三个按钮
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    TabButton(
                        icon = Icons.Default.Edit,
                        label = "笔记",
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 }
                    )
                    TabButton(
                        icon = Icons.Default.Star,
                        label = "收藏",
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 }
                    )
                    TabButton(
                        icon = Icons.Default.Favorite,
                        label = "点赞",
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 }
                    )
                }

                // b 部分：保留区域
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )

                // c 部分：内容区域
                Text(
                    text = when (selectedTab) {
                        0 -> "笔记"
                        1 -> "收藏"
                        else -> "点赞"
                    },
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier
                .size(32.dp)
                .padding(bottom = 4.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun TabButton(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp),
                tint = if (selected) MaterialTheme.colorScheme.primary 
                       else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                text = label,
                color = if (selected) MaterialTheme.colorScheme.primary 
                        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
} 