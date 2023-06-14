package com.example.rickandmortyapp.data.network.model

import com.example.example.Info
import com.google.gson.annotations.SerializedName


data class CharacterResponse(

    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

)