package com.example.tuckbox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuckbox.ui.theme.Cabin
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SideNavigationBar(navController: NavController, sideNavWindow: Boolean) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val localConfig = LocalConfiguration.current
    if(sideNavWindow){
        Row(
            modifier = Modifier
                .fillMaxSize(), // Fill the entire available space
            horizontalArrangement = Arrangement.End // Aligns the Box to the right
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((LocalConfiguration.current.screenWidthDp / 1.8).dp)
                    .shadow(10.dp, RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .background(Color.White),
                contentAlignment = Alignment.CenterEnd
            ) {
                Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                    Text(text = "Home", fontFamily = Cabin, fontWeight = FontWeight.Bold, fontSize = 25.sp
                    , modifier = Modifier.clickable { navController.navigate("home_screen") })
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    Text(text = "Place Order", fontFamily = Cabin, fontWeight = FontWeight.Bold, fontSize = 25.sp
                        , modifier = Modifier.clickable { navController.navigate("place_order_screen") })
                    Spacer(modifier = Modifier.padding(top = 30.dp))

                    Text(text = "Current Order", fontFamily = Cabin, fontWeight = FontWeight.Bold, fontSize = 25.sp
                        , modifier = Modifier.clickable { navController.navigate("home_screen") })
                    Spacer(modifier = Modifier.padding(top = 30.dp))

                    Text(text = "Order History", fontFamily = Cabin, fontWeight = FontWeight.Bold, fontSize = 25.sp
                        , modifier = Modifier.clickable { navController.navigate("order_history_screen") })
                    Spacer(modifier = Modifier.padding(top = 30.dp))

                    Text(text = "Update User", fontFamily = Cabin, fontWeight = FontWeight.Bold, fontSize = 25.sp
                        , modifier = Modifier.clickable { navController.navigate("update_info_screen") })
                    Spacer(modifier = Modifier.padding(top = 30.dp))

                    Text(text = "Sign Out", fontFamily = Cabin, fontWeight = FontWeight.Bold, fontSize = 25.sp
                        , modifier = Modifier.clickable {
                            firebaseAuth.signOut()
                            navController.navigate("login_screen") })
                }
            }
        }
    }


}