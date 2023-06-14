package com.example.rickandmortyapp.data.network.model

import com.google.gson.annotations.SerializedName


data class Location(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)