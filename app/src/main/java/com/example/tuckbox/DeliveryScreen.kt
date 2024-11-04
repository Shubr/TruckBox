package com.example.tuckbox

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

data class Address(
    val street: String = "",
    val apartment: String = "",
    val city: String = "",
    val postCode: String = ""
)

@Composable
fun DeliveryScreen(navController: NavController, navigationModel: NavigationModel) {

    var newAddressWindow  by remember{mutableStateOf(false)}
    var navSideWindow by remember {
        mutableStateOf(false)
    }
    val firebaseAuth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val userId = firebaseAuth.currentUser?.uid
    var addressList by remember {
        mutableStateOf<List<Address>>(emptyList())
    }

    userId?.let {
        firestore.collection("users").document(userId).collection("address")
            .get().addOnSuccessListener {
                snapShot ->
                val address = snapShot.documents.mapNotNull { it.toObject<Address>() }
                addressList = address
            }
    }
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){


        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    ,horizontalArrangement = Arrangement.SpaceBetween){
                IconButton(onClick = { navController.navigate("update_info_screen") }) {
                    Icon(painter = painterResource(id = R.drawable.arrow_left), contentDescription = null)
                }
                IconButton(onClick = { navSideWindow = !navSideWindow }) {
                    Icon(painter = painterResource(id = R.drawable.navigation), contentDescription = null)
                }

            }
            Text(text = "Delivery Address", style = TextStyle(fontFamily = Cabin, fontSize = 40.sp, color = Color(35,35,35), fontWeight = FontWeight.Bold), modifier = Modifier.padding(top = 20.dp) )
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = { newAddressWindow = true}
                    , colors = ButtonDefaults.buttonColors(
                        containerColor = Color(225,67,67)), shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Add Address")
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth().height(550.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(addressList){
                    address -> AddressCard(address = address.street, apartment = address.apartment, city = address.city, postCode = address.postCode)
                }
            }

        }
        if(newAddressWindow){
            AddAddress{newAddressWindow = it}
        }
        NavigationBar(navController, navigationModel)
        SideNavigationBar(navController = navController, sideNavWindow = navSideWindow)
    }
}
@Composable
fun AddAddress(closeWindow:(Boolean)->Unit) {
    val localCurrent = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()
    val fireStore = Firebase.firestore

    val userId = firebaseAuth.currentUser?.uid


    var address by remember{mutableStateOf("")}
    var apartment by remember {
        mutableStateOf("")
    }
    var city by remember{mutableStateOf("")}
    var postCode by remember{ mutableStateOf("") }
    var dropDown by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Box(modifier = Modifier
            .width(320.dp)
            .height(550.dp)
            .shadow(
                50.dp,
                RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Add New Address", fontSize = 25.sp, fontFamily = Cabin, fontWeight = FontWeight.Bold)
                }
                Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Text(text = "Address*", color = Color.Gray)
                    TextField(modifier = Modifier
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                        colors = TextFieldDefaults.colors(cursorColor = Color.Black
                        , unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                        singleLine = true,value = address, onValueChange = {newAddress -> address = newAddress})

                    Text(text = "Apartment", color = Color.Gray)
                    TextField(modifier = Modifier
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                        colors = TextFieldDefaults.colors(cursorColor = Color.Black
                            , unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                        singleLine = true,value = apartment, onValueChange = {newAddress -> apartment = newAddress})

                    Text(text = "City*", color = Color.Gray)

                    Button(onClick = { dropDown = !dropDown }, modifier =
                    Modifier
                        .width(280.dp)
                        .height(50.dp)
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .background(Color.White), colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                        Text(text = city, color = Color.Black)
                        DropdownMenu(expanded = dropDown, onDismissRequest = { dropDown = !dropDown }, modifier = Modifier.width(250.dp)) {
                            DropdownMenuItem(text = { Text(text = "Palmerston North") }, onClick = { city = "Palmerston North"
                                dropDown = false})
                            DropdownMenuItem(text = { Text(text = "Feilding") }, onClick = { city="Feilding"
                            dropDown = false})
                            DropdownMenuItem(text = { Text(text = "Ashhurst") }, onClick = { city = "Ashhurt"
                                dropDown = false})
                            DropdownMenuItem(text = { Text(text = "Long Burn") }, onClick = { city = "Long Burn"
                                dropDown = false})
                        }
                    }
                    Text(text = "Post Code*", color = Color.Gray)
                    TextField(modifier = Modifier
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                        colors = TextFieldDefaults.colors(cursorColor = Color.Black
                            , unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                        singleLine = true,value = postCode, onValueChange = {newAddress -> postCode = newAddress})

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                       Button(onClick = {
                           if(address.isNotEmpty() && city.isNotEmpty() && postCode.isNotEmpty()){
                               if (userId != null) {
                                   Address(address, apartment, city, postCode)
                                   fireStore.collection("users").document(userId).collection("address").add(Address(address, apartment, city, postCode))
                                   closeWindow(false)
                               }
                           }
                           else{
                               Toast.makeText(localCurrent, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                           }
                       },

                           modifier = Modifier
                               .width(100.dp)
                               .height(50.dp)
                               .shadow(10.dp, RoundedCornerShape(10.dp))
                               .clip(
                                   RoundedCornerShape(10.dp)
                               )
                               .background(Color(60, 225, 60)), colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                            Text(text = "Add", fontSize = 16.sp)
                       }
                        Button(onClick = { closeWindow(false)},
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .shadow(10.dp, RoundedCornerShape(10.dp))
                                .clip(
                                    RoundedCornerShape(10.dp)
                                )
                                .background(Color(225, 60, 60)), colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                            Text(text = "Cancel", fontSize = 16.sp)
                        }
                    }
                }


            }
        }
    }
}

fun getAllAddress(userId: String, onResult: (List<Address>)->Unit){
    val db = FirebaseFirestore.getInstance()
    db.collection("users").document(userId).collection("address")
        .get().addOnSuccessListener {
            snapShot -> val address = snapShot.documents.mapNotNull { it.toObject<Address>() }
            onResult(address) }
}

@Composable
fun AddressCard(address: String, apartment: String, city: String, postCode: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .shadow(10.dp, RoundedCornerShape(10.dp))
        .clip(
            RoundedCornerShape(10.dp)
        )
        .background(Color.White)){
        Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
            Text(text = city, fontSize = 25.sp, fontFamily = Cabin, fontWeight = FontWeight.Bold)
            Text(text = address, fontSize = 20.sp, fontFamily = Cabin)
            if(apartment != ""){
                Text(text = "Apartment: $apartment", fontFamily = Cabin)
            }
            Text(text = "Post Code: $postCode", fontFamily = Cabin)
        }
    }
}