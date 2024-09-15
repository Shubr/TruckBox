package com.example.tuckbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuckbox.ui.theme.Cabin
import com.example.tuckbox.ui.theme.Kristi

@Composable
fun PlaceOrderScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween){
            IconButton(onClick = { navController.navigate("home_screen") }) {
                Icon(painter = painterResource(id = R.drawable.arrow_left), contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.navigation), contentDescription = null)
            }
        }
        Column(modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxSize()
            ) {
            Text(text = "Place Order", style = TextStyle(fontFamily = Cabin, fontSize = 45.sp, fontWeight = FontWeight.Bold, color =  Color(35,35,35)), modifier = Modifier.padding(top = 40.dp, start = 20.dp))
                FoodCard()
//            LazyRow {
//
//            }
        }
    }
}