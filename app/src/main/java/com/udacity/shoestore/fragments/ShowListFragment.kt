package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.ShowViewModel
import com.udacity.shoestore.databinding.FragmentShowListBinding
import com.udacity.shoestore.databinding.ShoeInfoBinding


class ShowListFragment : Fragment() {
    private val viewModel: ShowViewModel by activityViewModels()
    private lateinit var binding: FragmentShowListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_list,container,false)
        setHasOptionsMenu(true)
        binding.addShoeFloatingButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_showListFragment_to_shoeDetailFragment)
        }
        viewModel.shoe.observe(viewLifecycleOwner, Observer { shoeLists ->
            shoeLists.forEach {
                val shoeInfoBinding=ShoeInfoBinding.inflate(LayoutInflater.from(requireContext()),binding.shoeListId,false)
                shoeInfoBinding.shoe=it
                shoeInfoBinding.imageView.setImageResource(shoeInfoBinding.shoe!!.currentImage)
                binding.shoeListId.addView(shoeInfoBinding.root)
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}