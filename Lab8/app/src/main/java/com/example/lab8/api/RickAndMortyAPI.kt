package com.example.lab8.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.lab8.model.AllAssetsResponse
import com.example.lab8.model.Character

interface RickAndMortyAPI {

    @GET("/api/character/")
    fun getCharacter():Call<AllAssetsResponse>

    @GET("/api/character/{id}")
    fun getOneCharacter(@Path("id") id : String):Call<Character>
}