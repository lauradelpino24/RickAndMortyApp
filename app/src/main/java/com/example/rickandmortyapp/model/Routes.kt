package com.example.rickandmortyapp.model

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object DashBoard : Routes("dashboard")
    object CharacterDetail : Routes("characterDetail/{name}/{status}/{species}/{gender}/{origin}/{image}") {
        fun createRoute(
            name: String,
            status: String,
            species: String,
            gender: String,
            origin: String,
            image: String
        ): String {
            return "characterDetail/$name/$status/$species/$gender/$origin/$image"
        }
    }
}