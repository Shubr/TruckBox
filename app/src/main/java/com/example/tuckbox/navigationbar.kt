package com.example.tuckbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavigationBar(navigation: NavController,navigationModel: NavigationModel) {
    val currentScreen = navigationModel.currentScreen

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 30.dp), contentAlignment = Alignment.BottomCenter){
        Box(modifier = Modifier
            .width(300.dp)
            .height(60.dp)
            .shadow(10.dp, RoundedCornerShape(15.dp))
            .clip(
                RoundedCornerShape(15.dp)
            )
            .background(Color(9, 10, 10))){
            Box(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxSize(),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    if(currentScreen=="home_screen"){
                        Box(modifier = Modifier
                            .clickable { }
                            .width(100.dp)
                            .height(40.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color(37, 37, 37))){
                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Image(painter = painterResource(id = R.drawable.home), contentDescription = null)
                                }
                                Text(text = "Home", color = Color.White)
                            }
                        }
                    }
                    else{
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { navigation.navigate("home_screen") }) {
                                Image(painter = painterResource(id = R.drawable.home), contentDescription = null)
                            }
                        }
                    }
                    if(currentScreen=="place_order_screen"){
                        Box(modifier = Modifier
                            .width(135.dp)
                            .height(40.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color(37, 37, 37))){
                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {  }) {
                                    Image(painter = painterResource(id = R.drawable.pizza), contentDescription = null)
                                }
                                Text(text = "Place Order", color = Color.White)
                            }
                        }
                    }
                    else{
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { navigation.navigate("place_order_screen") }) {
                                Image(painter = painterResource(id = R.drawable.pizza), contentDescription = null)
                            }
                        }
                    }
                    if(currentScreen=="update_info_screen"){
                        Box(modifier = Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color(37, 37, 37))){
                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {  }) {
                                    Image(painter = painterResource(id = R.drawable.user), contentDescription = null)
                                }
                                Text(text = "User", color = Color.White)
                            }
                        }
                    }
                    else{
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { navigation.navigate("update_info_screen")}) {
                                Image(painter = painterResource(id = R.drawable.user), contentDescription = null)
                            }
                        }
                    }
                    if(currentScreen=="order_history_screen"){
                        Box(modifier = Modifier
                            .width(140.dp)
                            .height(40.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color(37, 37, 37))){
                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Image(painter = painterResource(id = R.drawable.order), contentDescription = null)
                                }
                                Text(text = "Order History", color = Color.White)
                            }
                        }
                    }
                    else{
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { navigation.navigate("order_history_screen")}) {
                                Image(painter = painterResource(id = R.drawable.order), contentDescription = null)
                            }
                        }
                    }
                }




            }
        }
    }
}