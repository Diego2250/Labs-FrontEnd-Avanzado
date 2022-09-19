package com.example.lab8.model

data class Character(
    val creation: String,
    val genero: String,
    val imagen: String,
    val especie: String,
    val estatus: String,
    val tipo: String,
    val url: String,
    val nombre: String,
    val id: Int,
    val origin: Origin,
    val location: CharacterLocation,
    val episodio: List<String>
)