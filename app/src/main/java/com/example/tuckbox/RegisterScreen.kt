package com.example.tuckbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var eMail by remember {mutableStateOf("")}
    var phone by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}

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

            Text(modifier = Modifier.padding(start = 20.dp, top = 80.dp),text = "Create\nAccount", style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Color(54,54,54)))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp), contentAlignment = Alignment.TopCenter) {
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
                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color(35,35,35)), modifier = Modifier.width(280.dp)) {
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