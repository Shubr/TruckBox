package com.example.tuckbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuckbox.ui.theme.Cabin

@Composable
fun LoginScreen(navController: NavController) {
    var eMail by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    val screenSize = LocalConfiguration.current

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = screenSize.screenHeightDp.dp / 10f)){
        Image(painterResource(id = R.drawable.sandwith), contentDescription = "s",
            Modifier
                .offset(x = 320.dp, y = (-170).dp)
                .rotate(10f)
                .size(200.dp))
//        Image(painterResource(id = R.drawable.veg_salad), contentDescription = "s", Modifier.offset(x = -100.dp, y = (550).dp).rotate(10f).size(200.dp))
        Column(Modifier.padding(start = 30.dp)) {
            Text(text = "Welcome", style = TextStyle(fontFamily = Cabin, fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Color(54,54,54)))
            Text(text = "Enter your eMail & Password", style = TextStyle(fontFamily = Cabin, fontSize = 20.sp, color = Color(90,90,90)))

        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 220.dp)){
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(value = eMail, placeholder = {Text(text = "eMail", style = TextStyle(Color.Gray, fontSize = 20.sp))},onValueChange = { newText -> eMail = newText}, colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent))
                Spacer(Modifier.padding(top = 50.dp))
                TextField(value = password, placeholder = {Text(text = "Password", style = TextStyle(Color.Gray, fontSize = 20.sp))},onValueChange = {newText -> password = newText}, colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent))
                Spacer(Modifier.padding(top = 90.dp))
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color(35,35,35)), modifier = Modifier.width(300.dp)) {
                    Text(text = "Login", style = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.Bold, fontSize = 18.sp))
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Button(onClick = { navController.navigate("register_screen")}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), ) {
                    Text(text = "Create a Account", style = TextStyle(fontFamily = Cabin, fontSize = 12.sp, color = Color.Gray))
                }
            }
        }

    }

}