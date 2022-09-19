package com.example.lab8.model

data class Resultado(
    val created: String,
    val gender: String,
    val image: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    val name: String,
    val id: Int,
    val origin: Origin,
    val location: Location,
    val episode: List<String>
)