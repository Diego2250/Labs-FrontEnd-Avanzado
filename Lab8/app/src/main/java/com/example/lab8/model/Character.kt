package com.example.lab8.model

data class Character(
    val created: String,
    val gender: String,
    val image: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    val name: String,
    val id: Int,
    val origin: OriginCharacter,
    val location: LocationCharacter,
    val episode: List<String>
)