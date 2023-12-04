package com.v01d.professormanager.ui.specialite.update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.v01d.professormanager.databinding.FragmentCreateSpecialiteBinding
import com.v01d.professormanager.databinding.FragmentUpdateSpecialiteBinding
import com.v01d.professormanager.ui.specialite.create.CreateSpecialiteFragmentDirections

class UpdateSpecialiteFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateSpecialiteFragment()
    }

    private lateinit var viewModel: UpdateSpecialiteViewModel
    private var _binding: FragmentUpdateSpecialiteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateSpecialiteBinding.inflate(inflater,container,false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(UpdateSpecialiteViewModel::class.java)

        binding.updateBtn.setOnClickListener {

            viewModel.updateSpecialite(UpdateSpecialiteFragmentArgs.fromBundle(requireArguments()).specId,binding.nameEditText.text.toString())
            val action = UpdateSpecialiteFragmentDirections
                .actionUpdateSpecialiteFragmentToShowSpecialiteFragment()
            findNavController().navigate(action)
        }
        return view
    }


}