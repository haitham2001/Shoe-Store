package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import kotlin.random.Random

class ShowViewModel: ViewModel() {
    private val _shoe = MutableLiveData<MutableList<Shoe>>()
    val shoe: LiveData<MutableList<Shoe>>
        get() = _shoe
    init {
        _shoe.value = mutableListOf()
    }
    fun addShoe(shoe:Shoe){
        _shoe.value!!.add(shoe)
    }
    fun getRandomImage(): Int {
        return when (Random.nextInt(0,5)){
            0 -> R.drawable.shoe_image_1
            1 -> R.drawable.shoe_image_2
            2 -> R.drawable.shoe_image_3
            3 -> R.drawable.shoe_image_4
            4 -> R.drawable.shoe_image_5
            else -> R.drawable.shoe_image_1
        }
    }
}