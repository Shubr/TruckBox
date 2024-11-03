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
import com.example.tuckbox.ui.theme.Cabin
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val eMail: String = "",
    val phone: String = ""
)

@Composable
fun UpdateUserInfoScreen(navController: NavController,navigationModel: NavigationModel) {
    val local = LocalContext.current
    var userInfo by remember {
        mutableStateOf(User())
    }

    val auth = FirebaseAuth.getInstance()
    val firestorm = Firebase.firestore

    var deleteUi by remember {
        mutableStateOf(false)
    }
    val userId = auth.currentUser?.uid
    userId?.let {
        firestorm.collection("users").document(userId).get().addOnSuccessListener {
                document ->
            val data = document.data
            userInfo = User(
                firstName = data?.get("firstName").toString(),
                lastName = data?.get("lastName").toString(),
                eMail = data?.get("eMail").toString(),
                phone = data?.get("phone").toString()
            )

        }
    }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var eMail by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var deleteWindow by remember{ mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Image(painterResource(id = R.drawable.beef_noodles), contentDescription = null,
            Modifier
                .offset(x = (180).dp, y = (600).dp)
                .rotate(10f)
                .size(300.dp))
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
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = "Update profile", style = TextStyle(fontFamily = Cabin, fontSize = 40.sp, color = Color(35,35,35), fontWeight = FontWeight.Bold), modifier = Modifier.padding(top = 100.dp, start = 20.dp) )
            val config = LocalConfiguration.current
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = ((config.screenWidthDp - 280)/2).dp)
                , contentAlignment = Alignment.TopCenter) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize().imePadding()) {
                    item{
                        Text(text = "First Name", fontSize = 15.sp, color = Color.Gray)
                        TextField(modifier = Modifier
                            .width(280.dp)
                            .height(55.dp)
                            .shadow(10.dp, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White), singleLine = true,
                            value = firstName,
                            placeholder = {
                                Text(
                                    text = userInfo.firstName,
                                    style = TextStyle(Color.Gray, fontSize = 20.sp)
                                )

                            },
                            onValueChange = { newText -> firstName = newText },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            )
                        )
                    }
                    item{
                        Text(text = "Last Name", fontSize = 15.sp, color = Color.Gray)
                        TextField(modifier = Modifier
                            .width(280.dp)
                            .height(55.dp)
                            .shadow(10.dp, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),singleLine = true,
                            value = lastName,
                            placeholder = {
                                Text(
                                    text = userInfo.lastName,
                                    style = TextStyle(Color.Gray, fontSize = 20.sp)
                                )
                            },
                            onValueChange = { newText -> lastName = newText },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            )
                        )
                    }
                    item{
                        Text(text = "eMail", fontSize = 15.sp, color = Color.Gray)
                        TextField(modifier = Modifier
                            .width(280.dp)
                            .height(55.dp)
                            .shadow(10.dp, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),singleLine = true,
                            value = eMail,
                            placeholder = {
                                Text(
                                    text = userInfo.eMail,
                                    style = TextStyle(Color.Gray, fontSize = 20.sp)
                                )
                            },
                            onValueChange = { newText -> eMail = newText },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            )
                        )
                    }
                    item{
                        Text(text = "Phone", fontSize = 15.sp, color = Color.Gray)
                        TextField(modifier = Modifier
                            .width(280.dp)
                            .height(55.dp)
                            .shadow(10.dp, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),singleLine = true,
                            value = phone,
                            placeholder = {
                                Text(
                                    text = userInfo.phone,
                                    style = TextStyle(Color.Gray, fontSize = 20.sp)
                                )
                            },
                            onValueChange = { newText -> phone = newText },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            )
                        )
                    }
                    item{
                        Spacer(modifier = Modifier.padding(top = 40.dp))
                        Button(onClick = {
                            if (userId != null) {
                                updateUser(firstName, lastName, eMail, phone, auth, firestorm, userId)
                                navController.navigate("home_screen")
                                Toast.makeText(local, "Successfully Updated!", Toast.LENGTH_SHORT).show()
                            }
                        }, colors = ButtonDefaults.buttonColors(containerColor = Color(230,64,67)), modifier = Modifier.width(280.dp)) {
                            Text(text = "Update", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                        }
                    }
                    item{

                        Button(onClick = { deleteUi = !deleteUi
                        }, colors = ButtonDefaults.buttonColors(containerColor = Color(50,50,50)), modifier = Modifier.width(280.dp)) {
                            Text(text = "Delete Account", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                        }
                    }
                }

            }
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)){
        NavigationBar(navController, navigationModel)
    }

    if(deleteUi){
        deleteUser(firebaseAuth = auth, firestore = firestorm, navController = navController
        ) { deleteUi = it }
    }
}

fun updateUser(firstName: String, lastName: String, eMail: String, phone: String, auth: FirebaseAuth, firestore: FirebaseFirestore, userId:String){
    val user = firestore.collection("users").document(userId)

    if(firstName.isNotEmpty()) user.update("firstName", firstName).addOnFailureListener { error -> Log.d("Firstbase Firestorm Update User", error.toString()) }
    if(lastName.isNotEmpty()) user.update("lastName", lastName).addOnFailureListener { error -> Log.d("Firstbase Firestorm Update User", error.toString()) }
    if(eMail.isNotEmpty()) user.update("eMail", eMail).addOnFailureListener { error -> Log.d("Firstbase Firestorm Update User", error.toString()) }
    if(phone.isNotEmpty()) user.update("phone", phone).addOnFailureListener { error -> Log.d("Firstbase Firestorm Update User", error.toString()) }
}

@Composable
fun deleteUser(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore, navController: NavController, closeWinodw: (Boolean)->Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Box(modifier = Modifier
            .clip(
                RoundedCornerShape(20.dp)
            )
            .width(250.dp)
            .height(200.dp)
            .background(Color(255, 235, 235))
        ){
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                Text(text = "Delete Account", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                Row() {
                    Button(onClick = {
                        navController.navigate("login_screen")
                        firebaseAuth.currentUser?.delete()
                    }) {
                        Text(text = "Delete")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                    Button(onClick = { closeWinodw(false) }) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}