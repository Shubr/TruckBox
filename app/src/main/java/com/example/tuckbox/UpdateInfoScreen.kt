package com.example.tuckbox

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuckbox.ui.theme.Barlow
import com.example.tuckbox.ui.theme.Cabin
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun UpdateInfoScreen(navController: NavController,navigationModel: NavigationModel) {
    var navSideWindow by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize().background(Color.White)){

        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween){
            IconButton(onClick = { navController.navigate("home_screen") }) {
                Icon(painter = painterResource(id = R.drawable.arrow_left), contentDescription = null)
            }
            IconButton(onClick = { navSideWindow = !navSideWindow }) {
                Icon(painter = painterResource(id = R.drawable.navigation), contentDescription = null)
            }

        }
        Text(text = "Update Info", style = TextStyle(fontFamily = Cabin, fontSize = 40.sp, color = Color(35,35,35), fontWeight = FontWeight.Bold), modifier = Modifier.padding(top = 100.dp, start = 20.dp) )


        Image(painterResource(id = R.drawable.sandwith), contentDescription = null, Modifier.offset(x = -(150).dp, y = (800).dp).rotate(180f).size(300.dp))
        NavigationBar(navController, navigationModel)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent), contentAlignment = Alignment.Center){

        Column(verticalArrangement = Arrangement.spacedBy(50.dp)) {
            Button(onClick = { navController.navigate("user_info_screen") }, modifier = Modifier
                .width(300.dp)
                .height(120.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))

                , colors = ButtonDefaults.buttonColors(
                    Color.White), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Update User Info", style = TextStyle(fontFamily = Barlow), fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 30.sp)
            }
            Button(onClick = { navController.navigate("delivery_screen")}, modifier = Modifier
                .width(300.dp)
                .height(120.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))

                , colors = ButtonDefaults.buttonColors(
                    Color.White), shape = RoundedCornerShape(10.dp)) {
                Text(text = "Delivery Address", style = TextStyle(fontFamily = Barlow), fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 30.sp)

            }

        }
        SideNavigationBar(navController = navController, sideNavWindow = navSideWindow)

    }

}