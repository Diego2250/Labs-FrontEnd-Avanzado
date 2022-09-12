package com.example.lab8

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharacterFragment : Fragment(R.layout.fragment_character), PlaceAdapter.PlaceListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterList : MutableList<Character>
    private lateinit var buttonSortAZ : Button
    private lateinit var buttonSortZA : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        buttonSortAZ = view.findViewById(R.id.bt_AZ)
        buttonSortZA = view.findViewById(R.id.bt_ZA)

        setListeners()
        setRecycler()
    }

    private fun setRecycler() {
        characterList = RickAndMortyDB.getCharacters()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = PlaceAdapter(characterList, this)
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setListeners() {
        buttonSortAZ.setOnClickListener(){
            characterList.sortBy { place -> place.name }
            recyclerView.adapter!!.notifyDataSetChanged()
        }

        buttonSortZA.setOnClickListener(){
            characterList.sortByDescending { place -> place.name }
            recyclerView.adapter!!.notifyDataSetChanged()
        }
    }

    override fun onPlaceClicked(data: Character, position: Int) {
        requireView().findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                data
            )
        )
    }


}