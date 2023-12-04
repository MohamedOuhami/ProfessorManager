package com.v01d.professormanager.ui.specialite.show

import SpecialiteAdapter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v01d.professormanager.R
import com.v01d.professormanager.databinding.FragmentShowProfessorBinding
import com.v01d.professormanager.databinding.FragmentShowSpecialiteBinding
import com.v01d.professormanager.misc.SwipeToDeleteCallback

class ShowSpecialiteFragment : Fragment() {

    companion object {
        fun newInstance() = ShowSpecialiteFragment()
    }

    private lateinit var viewModel: ShowSpecialiteViewModel
    private var _binding: FragmentShowSpecialiteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter:SpecialiteAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShowSpecialiteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ShowSpecialiteViewModel::class.java)

        viewModel.getSpecialites()


//      Fetching the specialites
        val view = binding.root

        recyclerView = binding.specialiteRecycleView

        adapter =
            viewModel.specialitesList.value?.let { SpecialiteAdapter() }!! // Initialize with an empty list

        // Set LinearLayoutManager to your RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Set click listener for the delete button
        adapter.onDeleteClickListener = { specialite ->
            viewModel.deleteSpecialite(specialite.id)
        }

        // Set click listener for the delete button
        adapter.onUpdateClickListener = { specialite ->
            val action = ShowSpecialiteFragmentDirections
                .actionShowSpecialiteFragmentToUpdateSpecialiteFragment(specialite.id)
            findNavController().navigate(action)
        }


        viewModel.specialitesList.observe(viewLifecycleOwner){ specialites ->
            if (viewModel.specialitesList.value?.isNotEmpty() == true) {
                if (adapter != null) {
                    adapter.data = viewModel.specialitesList.value!!
                }
                recyclerView.adapter = adapter
//                    enableSwipeToDeleteAndUndo()

            }
            Log.d("Change in specialites",specialites.toString())
        }

        return view
    }




}