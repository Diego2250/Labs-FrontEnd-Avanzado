package com.example.lab8

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8.model.AllAssetsResponse
import com.example.lab8.model.Resultado
import com.example.lab8.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterFragment : Fragment(R.layout.fragment_character), PlaceAdapter.PlaceListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterList : MutableList<Resultado>
    private lateinit var buttonSortAZ : Button
    private lateinit var buttonSortZA : Button
    private lateinit var APIcalllResult : MutableList<Resultado>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        buttonSortAZ = view.findViewById(R.id.bt_AZ)
        buttonSortZA = view.findViewById(R.id.bt_ZA)

        setListeners()
        setAPIrequiest()
    }

    private fun setAPIrequiest() {
        RetrofitInstance.api.getCharacter().enqueue(object : Callback<AllAssetsResponse>{
            override fun onResponse(
                call: Call<AllAssetsResponse>,
                response: Response<AllAssetsResponse>
            ) {
                if (response.isSuccessful && response.body()!=null){
                    APIcalllResult = response.body()!!.results
                    setRecycler()
                }
            }

            override fun onFailure(call: Call<AllAssetsResponse>, t: Throwable) {
                println("Ha ocurrido un error")
            }
        })
    }

    private fun setRecycler() {
        characterList = APIcalllResult
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = PlaceAdapter(characterList, this)
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setListeners() {
        buttonSortAZ.setOnClickListener(){
            characterList.sortBy{ place -> place.name }
            recyclerView.adapter!!.notifyDataSetChanged()
        }

        buttonSortZA.setOnClickListener(){
            characterList.sortByDescending { place -> place.name }
            recyclerView.adapter!!.notifyDataSetChanged()
        }
    }

    override fun onPlaceClicked(data: Resultado, position: Int) {
        requireView().findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                characterID = data.id.toString()
            )
        )
    }


}