package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(var name: String, var size: String, var company: String, var description: String,
                val image: Int) : Parcelable
{
    var currentName = name
    var currentSize = size
    var currentDescription =company
    var currentCompany = description
    var currentImage = image
}
