package com.v01d.professormanager.ui.specialite.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.v01d.professormanager.databinding.FragmentCreateSpecialiteBinding
import com.v01d.professormanager.ui.professor.create.CreateProfessorFragmentDirections

class CreateSpecialiteFragment : Fragment() {

    companion object {
        fun newInstance() = CreateSpecialiteFragment()
    }

    private lateinit var viewModel: CreateSpecialiteViewModel
    private var _binding: FragmentCreateSpecialiteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateSpecialiteBinding.inflate(inflater,container,false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(CreateSpecialiteViewModel::class.java)

        binding.createBtn.setOnClickListener {
            viewModel.createSpecialite(binding.nameEditText.text.toString())
            val action = CreateSpecialiteFragmentDirections
                .actionCreateSpecialiteFragmentToShowSpecialiteFragment()
            findNavController().navigate(action)
        }
        return view
    }

}