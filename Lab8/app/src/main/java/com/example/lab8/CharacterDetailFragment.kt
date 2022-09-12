package com.example.lab8

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.lab8.R
import com.google.android.material.appbar.MaterialToolbar

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private lateinit var imagen : ImageView
    private lateinit var nombre : TextView
    private lateinit var especie : TextView
    private lateinit var estatus : TextView
    private lateinit var genero : TextView
    private lateinit var toolbar: MaterialToolbar

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagen = view.findViewById(R.id.imageView)
        nombre = view.findViewById(R.id.nombre_personaje)
        especie = view.findViewById(R.id.gender_personaje)
        estatus = view.findViewById(R.id.status_personaje)
        genero = view.findViewById(R.id.genero_personaje)

        setImagen()
        setInformacion()
    }

    private fun setInformacion() {
        nombre.text = args.characterInformation.name
        especie.text = args.characterInformation.species
        estatus.text = args.characterInformation.status
        genero.text = args.characterInformation.gender
    }

    private fun setImagen() {
        imagen.load(args.characterInformation.image){
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
            transformations(CircleCropTransformation())
            error(R.drawable.ic_baseline_error_outline_24)
            placeholder(R.drawable.ic_baseline_arrow_circle_down_24)
        }
    }

}