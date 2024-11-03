package com.example.tuckbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuckbox.ui.theme.CondensedReg

@Composable
fun FoodCard() {
    Box(modifier = Modifier
        .width(150.dp)
        .height(200.dp).shadow(10.dp, RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White).clickable {

        }
    ){
        Column {
            Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.greensalad), contentDescription = null, modifier = Modifier
                    .size(100.dp)
                    .background(Color.Transparent))
            }
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(text = "food.name", fontFamily = CondensedReg, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                Text(text = "food.description", fontFamily = CondensedReg, color = Color.Gray)
                Text(text = "food.price.toString()", fontFamily = CondensedReg, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }

    }
}