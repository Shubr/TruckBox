package com.example.tuckbox

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Locale

//private lateinit var auth: FirebaseAuth
//private lateinit var fireStore: Firebase


@Composable
fun RegisterScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var eMail by remember {mutableStateOf("")}
    var phone by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}

    val context = LocalContext.current

    val firstNameError by remember{ mutableStateOf(false) }
    val lastNameError by remember{ mutableStateOf(false)}
    val eMailError by remember{ mutableStateOf(false)}
    val phoneError by remember{ mutableStateOf(false)}
    val passwordError by remember{ mutableStateOf(false)}


        Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(R.drawable.sandwith), contentDescription = null,
            Modifier
                .size(250.dp)
                .offset(x = 200.dp, y = (-50).dp)
                .rotate(90f))
        Image(painter = painterResource(R.drawable.sandwith), contentDescription = null,
            Modifier
                .size(250.dp)
                .offset(x = (-50).dp, y = (710).dp)
                .rotate(270f))
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(onClick = { navController.navigate("login_screen") }) {
                Icon(painter = painterResource(R.drawable.arrow_left), contentDescription = null,
                    Modifier
                        .size(50.dp)
                        .padding(top = 10.dp))
            }

            Text(modifier = Modifier.padding(start = 20.dp, top = 50.dp),text = "Create\nAccount", style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Color(54,54,54)))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
                , contentAlignment = Alignment.TopCenter) {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    TextField(
                        value = firstName,
                        placeholder = {
                            Text(
                                text = "First Name",
                                style = TextStyle(Color.Gray, fontSize = 20.sp)
                            )

                        },
                        onValueChange = { newText -> firstName = newText },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = lastName,
                        placeholder = {
                            Text(
                                text = "Last Name",
                                style = TextStyle(Color.Gray, fontSize = 20.sp)
                            )
                        },
                        onValueChange = { newText -> lastName = newText },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = eMail,
                        placeholder = {
                            Text(
                                text = "eMail",
                                style = TextStyle(Color.Gray, fontSize = 20.sp)
                            )
                        },
                        onValueChange = { newText -> eMail = newText },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = phone,
                        placeholder = {
                            Text(
                                text = "Phone",
                                style = TextStyle(Color.Gray, fontSize = 20.sp)
                            )
                        },
                        onValueChange = { newText -> phone = newText },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = password,
                        placeholder = {
                            Text(
                                text = "Password",
                                style = TextStyle(Color.Gray, fontSize = 20.sp)
                            )
                        },
                        onValueChange = { newText -> password = newText },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    Button(onClick = {
                        if(firstName.isNotEmpty() && lastName.isNotEmpty() && eMail.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()) {
                            createUser(eMail, password, firstName, lastName, phone)
                            navController.navigate("login_screen")
                        }
                                     else Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_LONG).show()
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color(35,35,35)), modifier = Modifier.width(280.dp)) {
                        Text(text = "Sign Up", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                    }
                    Button(onClick = { navController.navigate("login_screen") }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), modifier = Modifier.padding(start = 50.dp)) {
                        Text(text = "Already have an account?", style = TextStyle(fontSize = 12.sp, color = Color.Gray))
                    }
                }

            }
        }
    }
}

fun createUser(eMail: String, password:String, firstName: String, lastName: String, phone: String){
    val auth = FirebaseAuth.getInstance()
    val fireStore = Firebase.firestore

    auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener {
            val user = FirebaseAuth.getInstance().currentUser
            val userId = user?.uid
            val userInfo = hashMapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "phone" to phone,
                "eMail" to eMail
            )
            userId?.let{
                fireStore.collection("users").document(it).set(userInfo)
            }

    }.addOnFailureListener {

    }

}