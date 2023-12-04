package com.v01d.professormanager.ui.professor.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.v01d.professormanager.databinding.FragmentCreateProfessorBinding
import com.v01d.professormanager.entities.Specialite
import com.v01d.professormanager.ui.professor.show.ShowProfessorFragmentDirections
import com.v01d.professormanager.ui.specialite.show.ShowSpecialiteViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateProfessorFragment : Fragment() {

    companion object {
        fun newInstance() = CreateProfessorFragment()
    }

    private lateinit var viewModel: CreateProfessorViewModel
    private var _binding: FragmentCreateProfessorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateProfessorBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(CreateProfessorViewModel::class.java)
        val viewModelSpec = ViewModelProvider(this).get(ShowSpecialiteViewModel::class.java)
        val view = binding.root

        // Observe changes in specialities list
        viewModelSpec.specialitesList.observe(viewLifecycleOwner) { specialities ->
            // Update the spinner adapter with the new list of specialities
            updateSpinnerAdapter(specialities)
        }

        binding.createprofBtn.setOnClickListener {
            viewModel.createProfessor(
                binding.firstNameEditText.text.toString(),
                binding.lastNameEditText.text.toString(),
                null,
//                parseDateString(binding.birthdateEditText.text.toString()),
                binding.Specialitespinner.selectedItem as Specialite
            )

            val action = CreateProfessorFragmentDirections
                .actionCreateProfessorFragmentToShowProfessorFragment()
            findNavController().navigate(action)
        }


        return view
    }

    fun parseDateString(dateString: String): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.parse(dateString) ?: Date()
    }


    private fun updateSpinnerAdapter(specialities: List<Specialite>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, specialities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.Specialitespinner.adapter = adapter
    }

}