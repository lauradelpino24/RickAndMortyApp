package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortyapp.model.Routes
import com.example.rickandmortyapp.ui.dashboard.view.DashBoardScreen
import com.example.rickandmortyapp.ui.dashboard.viewmodel.DashBoardViewModel
import com.example.rickandmortyapp.ui.detail.view.CharacterDetailScreen
import com.example.rickandmortyapp.ui.login.view.LoginScreen
import com.example.rickandmortyapp.ui.login.viewmodel.LoginViewModel
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

val customFont = FontFamily(
    Font(R.font.get_schwifty, FontWeight.Normal)
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val dashBoardViewModel: DashBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0B0543)
                ) {
                    AppNavigation(loginViewModel, dashBoardViewModel)
                }
            }
        }
    }

}

@Composable
fun AppNavigation(loginViewModel: LoginViewModel, dashBoardViewModel: DashBoardViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginScreen(loginViewModel = loginViewModel, navController)
        }
        composable(Routes.DashBoard.route) {
            DashBoardScreen(dashBoardViewModel = dashBoardViewModel, navController)
        }

        composable(
            Routes.CharacterDetail.route, arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("status") { type = NavType.StringType },
                navArgument("species") { type = NavType.StringType },
                navArgument("gender") { type = NavType.StringType },
                navArgument("origin") { type = NavType.StringType },
                navArgument("image") { type = NavType.StringType }

            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "name"
            val status = backStackEntry.arguments?.getString("status") ?: "status"
            val species = backStackEntry.arguments?.getString("species") ?: "species"
            val gender = backStackEntry.arguments?.getString("gender") ?: "gender"
            val origin = backStackEntry.arguments?.getString("origin") ?: "origin"
            val image = backStackEntry.arguments?.getString("image") ?: "image"

            CharacterDetailScreen(
                name = name,
                status = status,
                species = species,
                gender = gender,
                origin = origin,
                image = image,
                navController
            )
        }
    }
}