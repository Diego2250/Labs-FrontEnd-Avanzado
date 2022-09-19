package com.example.lab8

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import coil.transform.CircleCropTransformation
import com.example.lab8.model.Resultado

class PlaceAdapter(
    private val dataSet: List<Resultado>,
    private val placeListener: PlaceListener
):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    interface PlaceListener {
        fun onPlaceClicked(data: Resultado, position: Int)
    }

    class ViewHolder(
        private val view: View,
        private val listener: PlaceListener
    ) : RecyclerView.ViewHolder(view) {
        private val imageType : ImageView = view.findViewById(R.id.imageView_recyclePlace)
        private val Name: TextView = view.findViewById(R.id.textView_recyclePlace_name)
        private val Status: TextView = view.findViewById(R.id.textView_recyclerPlace_Status)
        private val layout: ConstraintLayout = view.findViewById(R.id.layout_itemPlace)
        private lateinit var place: Resultado

        fun setData(place : Resultado){
            this.place = place
            Name.text = place.name
            (place.species + " - " + place.status).also { Status.text = it }
            imageType.load(place.image){
                transformations(CircleCropTransformation())
                error(R.drawable.ic_baseline_error_outline_24)
                placeholder(R.drawable.ic_baseline_arrow_circle_down_24)
            }
            setListeners()
        }

        private fun setListeners() {
            layout.setOnClickListener(){
                listener.onPlaceClicked(place, this.adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_place, parent, false)

        return ViewHolder(view, placeListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}