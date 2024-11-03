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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuckbox.ui.theme.Cabin

@Composable
fun OrderHistoryScreen(navController: NavController, navigationModel: NavigationModel) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)){
        NavigationBar(navController, navigationModel)
    }
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
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Order History", style = TextStyle(fontFamily = Cabin, fontSize = 40.sp, color = Color(35,35,35), fontWeight = FontWeight.Bold), modifier = Modifier.padding(top = 100.dp, start = 20.dp) )

    }
}