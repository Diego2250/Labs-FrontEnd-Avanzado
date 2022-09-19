package com.example.lab8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.lab8.api.RetrofitInstance
import com.example.lab8.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private lateinit var imagen : ImageView
    private lateinit var nombre : TextView
    private lateinit var especie : TextView
    private lateinit var estatus : TextView
    private lateinit var genero : TextView
    private lateinit var ID : String
    private lateinit var APIcallresult : Character
    private lateinit var origen : TextView
    private lateinit var apariciones : TextView


    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagen = view.findViewById(R.id.imageView)
        nombre = view.findViewById(R.id.nombre_personaje)
        especie = view.findViewById(R.id.gender_personaje)
        estatus = view.findViewById(R.id.status_personaje)
        genero = view.findViewById(R.id.genero_personaje)
        origen = view.findViewById(R.id.origen_personaje)
        apariciones = view.findViewById(R.id.apariciones_personaje)

        setID()
        setAPIrequiest()
    }

    private fun setAPIrequiest() {
        RetrofitInstance.api.getOneCharacter(ID).enqueue(object : Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful && response.body()!=null){
                    APIcallresult = response.body()!!
                    setImagen()
                    setInformacion()
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                println("Ha ocurrido un error")
            }
        })
    }

    private fun setID() {
        ID = args.characterID
    }

    private fun setInformacion() {
        nombre.text = APIcallresult.name
        especie.text = APIcallresult.species
        estatus.text = APIcallresult.status
        genero.text = APIcallresult.gender
        origen.text = APIcallresult.origin.name
        apariciones.text = APIcallresult.episode.size.toString()

    }

    private fun setImagen() {
        imagen.load(APIcallresult.image){
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
            transformations(CircleCropTransformation())
            error(R.drawable.ic_baseline_error_outline_24)
            placeholder(R.drawable.ic_baseline_arrow_circle_down_24)
        }
    }

}