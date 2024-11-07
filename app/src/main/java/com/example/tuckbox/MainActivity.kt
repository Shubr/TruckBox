package com.example.tuckbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
    val navigationModel: NavigationModel = viewModel()
    val navBackStack by navController.currentBackStackEntryAsState()

    navBackStack?.destination?.route?.let { route->
        navigationModel.updateCurrentScreen(route)
    }
    NavHost(navController = navController, startDestination = "splash_screen"){
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
            HomeScreen(navController,navigationModel)
        }
        composable("place_order_screen"){
            PlaceOrderScreen(navController = navController, navigationModel)
        }
        composable("order_history_screen"){
            OrderHistoryScreen(navController = navController, navigationModel = navigationModel)
        }
        composable("user_info_screen"){
            UpdateUserInfoScreen(navController = navController, navigationModel = navigationModel)
        }
        composable("delivery_screen"){
            DeliveryScreen(navController = navController, navigationModel = navigationModel)
        }
        composable("update_info_screen"){
            UpdateInfoScreen(navController,navigationModel)
        }
    }

}

class NavigationModel : ViewModel(){
    var currentScreen by mutableStateOf<String>("place_order_screen")
        private set

    fun updateCurrentScreen(screen: String){
        currentScreen = screen
    }
}