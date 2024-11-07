package com.example.tuckbox

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuckbox.ui.theme.Barlow
import com.example.tuckbox.ui.theme.Cabin
import com.example.tuckbox.ui.theme.CondensedReg
import com.example.tuckbox.ui.theme.Kristi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

@Composable
fun PlaceOrderScreen(navController: NavController,navigationModel: NavigationModel) {

    val firebaseAuth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore
    val userId  = firebaseAuth.currentUser?.uid
    var addressList by remember {
        mutableStateOf<List<Address>>(emptyList())
    }
    var city by remember { mutableStateOf("") }
    var cityDropDown by remember {
        mutableStateOf(false)
    }
    var address by remember {
        mutableStateOf("")
    }
    var addressDropDown by remember{ mutableStateOf(false) }
    var loading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(userId) {
        if (userId != null) {
            try {
                // Fetch address data once
                val snapShot = firestore.collection("users")
                    .document(userId)
                    .collection("address")
                    .get()
                    .await() // Await result if using coroutines

                addressList = snapShot.documents.mapNotNull { it.toObject<Address>() }
                loading = false
            } catch (e: Exception) {
                // Handle any errors (e.g., log or show error message)
                Log.e("Firestore", "Error fetching addresses: ${e.message}")
            }
        }
    }



    var timeSlot by remember{ mutableStateOf("") }
    var timeSlotDropDown by remember { mutableStateOf(false) }
    var navSideWindow by remember{ mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        NavigationBar(navigation = navController,navigationModel)
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
        Column(modifier = Modifier
            .padding(top = 60.dp, start = 30.dp, end = 30.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
            Text(text = "Place Order", style = TextStyle(fontFamily = Cabin, fontSize = 45.sp, fontWeight = FontWeight.Bold, color =  Color(35,35,35)), modifier = Modifier.padding(top = 40.dp))
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(text = "Select your city", fontFamily = CondensedReg, color = Color.Gray)
            Button(onClick = { cityDropDown = true }, shape = RoundedCornerShape(10.dp),modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .shadow(
                    10.dp,
                    RoundedCornerShape(10.dp)
                )
                .background(Color.White), colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = city, color = Color.Black, fontSize = 20.sp)
                DropdownMenu(expanded = cityDropDown, onDismissRequest = { cityDropDown = false }, modifier = Modifier.width(300.dp)) {
                    DropdownMenuItem(text = { Text(text = "Palmerston North") }, onClick = { city = "Palmerston North"
                    cityDropDown = false})
                    DropdownMenuItem(text = { Text(text = "Feilding") }, onClick = {city = "Feilding"
                        cityDropDown = false})
                    DropdownMenuItem(text = { Text(text = "Ashhurst") }, onClick = { city = "Ashhurst"
                        cityDropDown = false})
                    DropdownMenuItem(text = { Text(text = "Long Burn") }, onClick = { city = "Long Burn"
                        cityDropDown = false})
                }
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(text = "Delivery Time", fontFamily = CondensedReg, color = Color.Gray)
            Button(onClick = { timeSlotDropDown = true }, shape = RoundedCornerShape(10.dp),modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .shadow(
                    10.dp,
                    RoundedCornerShape(10.dp)
                )
                .background(Color.White), colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text(text = timeSlot, color = Color.Black, fontSize = 20.sp)
                DropdownMenu(expanded = timeSlotDropDown, onDismissRequest = { timeSlotDropDown = false }, modifier = Modifier.width(300.dp)) {
                    DropdownMenuItem(text = { Text(text = "11:45-12:15") }, onClick = { timeSlot = "11:45-12:15"
                        timeSlotDropDown = false})
                    DropdownMenuItem(text = { Text(text = "12:15-12:45") }, onClick = {timeSlot = "12:15-12:45"
                        timeSlotDropDown = false})
                    DropdownMenuItem(text = { Text(text = "12:45-13:15") }, onClick = { timeSlot = "12:45-13:15"
                        timeSlotDropDown = false})
                    DropdownMenuItem(text = { Text(text = "13:15-14:45") }, onClick = { timeSlot = "13:15-14:45"
                        timeSlotDropDown = false})
                }
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(text = "Delivery Address", fontFamily = CondensedReg, color = Color.Gray)

            Row {
                Button(onClick = { addressDropDown = !addressDropDown }, shape = RoundedCornerShape(10.dp),modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(
                        10.dp,
                        RoundedCornerShape(10.dp)
                    )
                    .background(Color.White), colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                    Text(text = address, color = Color.Black, fontSize = 20.sp)
                }
//                if(!loading){
//                    DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }){
//                        LazyColumn {
//                            items(addressList){
//                                    addressList ->
//                                DropdownMenuItem(text = { Text(text = addressList.street) }, onClick = {
//                                    address = addressList.street
//                                    addressDropDown = false
//                                })
//                            }
//                        }
//                    }
//                }
            }
            Spacer(modifier = Modifier.padding(top = 100.dp))
            FoodCard()
//            LazyRow {
//                items(viewModel.addManualItem()){
//                    food -> FoodCard(food = food)
//                }
//            }
        }
        SideNavigationBar(navController = navController, sideNavWindow = navSideWindow)

    }
}