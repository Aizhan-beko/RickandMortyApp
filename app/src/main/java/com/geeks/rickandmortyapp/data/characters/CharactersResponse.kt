package com.geeks.rickandmortyapp.data.characters

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results")
    val results: List<Character>?
)

data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String?,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
)

