package com.example.lab8.model

data class CharacterComplete(
    val creation: String,
    val genero: String,
    val imagen: String,
    val especie: String,
    val estatus: String,
    val tipo: String,
    val url: String,
    val nombre: String,
    val id: Int,
    val origin: origen,
    val location: Location,
    val episodio: List<String>
)