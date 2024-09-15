package com.example.tuckbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuckbox.ui.theme.Barlow
import com.example.tuckbox.ui.theme.Cabin
import com.example.tuckbox.ui.theme.Kristi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




@Composable
fun HomeScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val firestorm = Firebase.firestore
    var user by remember {
        mutableStateOf("")
    }
    val userId = auth.currentUser?.uid
    userId?.let {
        firestorm.collection("users").document(userId).get().addOnSuccessListener {
            document -> user = document.data?.get("firstName").toString()
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(20.dp), horizontalArrangement = Arrangement.End){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.navigation), contentDescription = null)
        }
    }
    Text(text = "Welcome, $user", style = TextStyle(fontFamily = Cabin, fontSize = 40.sp, color = Color(35,35,35), fontWeight = FontWeight.Bold), modifier = Modifier.padding(top = 100.dp, start = 20.dp) )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        Column(verticalArrangement = Arrangement.spacedBy(50.dp)) {
            Button(onClick = { navController.navigate("place_order_screen") }, modifier = Modifier
                .width(300.dp)
                .height(100.dp)

                .shadow(2.dp), colors = ButtonDefaults.buttonColors(
                Color.Transparent), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Place Order", style = TextStyle(fontFamily = Barlow), fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 30.sp)
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .shadow(2.dp), colors = ButtonDefaults.buttonColors(
                Color.Transparent), shape = RoundedCornerShape(10.dp)) {
                Text(text = "Update Info", style = TextStyle(fontFamily = Barlow), fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 30.sp)

            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .shadow(2.dp), colors = ButtonDefaults.buttonColors(
                Color.Transparent), shape = RoundedCornerShape(10.dp)) {
                Text(text = "Order History", style = TextStyle(fontFamily = Barlow), fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 30.sp)

            }
        }
    }
}