package com.udacity.shoestore.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShowViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.ShoeInfoBinding
import com.udacity.shoestore.models.Shoe
import kotlin.math.log

class ShoeDetailFragment : Fragment() {
    private val viewModel: ShowViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )
        with(binding){
            shoeDetail= Shoe("","","","",R.drawable.ic_add)
            shoeCancelButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_shoeDetailFragment_to_showListFragment)
            }
            shoeSaveButton.setOnClickListener {
                addShoeToStore(it)
            }
        }
        return binding.root
    }

    private fun addShoeToStore(view:View){
        //check if all fields had data in them
        if(binding.shoeDetail!!.currentName!=""&&binding.shoeDetail!!.currentSize!=""&&
            binding.shoeDetail!!.currentDescription!=""&&binding.shoeDetail!!.currentCompany!="")
        {
            //add the content to the viewModel
            binding.shoeDetail!!.currentImage = viewModel.getRandomImage()
            viewModel.addShoe(binding.shoeDetail!!)
            view.findNavController().navigate(R.id.action_shoeDetailFragment_to_showListFragment)
        }
        else
            Toast.makeText(context,"Please Enter all fields",Toast.LENGTH_SHORT).show()
    }
}