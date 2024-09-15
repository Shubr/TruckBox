package com.example.tuckbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen"){
        composable("splash_screen"){
            SplashScreen(navController)
        }
        composable("main_screen"){
            MainActivity()
        }
        composable("login_screen"){
            LoginScreen(navController)
        }
        composable("register_screen"){
            RegisterScreen(navController)
        }
        composable("home_screen"){
            HomeScreen(navController)
        }
        composable("place_order_screen"){
            PlaceOrderScreen(navController = navController)
        }
        composable("order_history_screen"){

        }
        composable("update_info_screen"){

        }
    }

}