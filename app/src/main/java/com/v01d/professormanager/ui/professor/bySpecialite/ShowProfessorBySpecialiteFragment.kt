package com.v01d.professormanager.ui.professor.bySpecialite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.v01d.professormanager.R
import com.v01d.professormanager.adapters.ProfessorAdapter
import com.v01d.professormanager.databinding.FragmentShowProfessorBinding
import com.v01d.professormanager.databinding.FragmentShowProfessorBySpecialiteBinding
import com.v01d.professormanager.entities.Specialite
import com.v01d.professormanager.ui.specialite.show.ShowSpecialiteFragmentDirections
import com.v01d.professormanager.ui.specialite.show.ShowSpecialiteViewModel

class ShowProfessorBySpecialiteFragment : Fragment() {

    private lateinit var viewModel: ShowProfessorBySpecialiteViewModel
    private lateinit var selectedSpeciality: Specialite
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var specialiteSpinner: Spinner
    private lateinit var fetchDataButton: Button
    private var _binding: FragmentShowProfessorBySpecialiteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowProfessorBySpecialiteBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShowProfessorBySpecialiteViewModel::class.java)
        val viewModelSpec = ViewModelProvider(this).get(ShowSpecialiteViewModel::class.java)
        val view = binding.root

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        recyclerView = view.findViewById(R.id.professorRecycleView)
        specialiteSpinner = view.findViewById(R.id.specialiteSpinner)
        fetchDataButton = view.findViewById(R.id.fetchDataBtn)

        viewModelSpec.specialitesList.observe(viewLifecycleOwner) { specialities ->
            // Update the spinner adapter with the new list of specialities
            updateSpinnerAdapter(specialities)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = ProfessorAdapter()
        recyclerView.adapter = adapter

        fetchDataButton.setOnClickListener {
            fetchData()
        }

        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }

        // Set item selected listener for the spinner
        specialiteSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Get the selected speciality
                selectedSpeciality = parent?.getItemAtPosition(position) as Specialite
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing if nothing is selected
            }
        }

        return view
    }

    private fun updateSpinnerAdapter(specialities: List<Specialite>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, specialities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        specialiteSpinner.adapter = adapter
    }

    private fun fetchData() {
        // Call getProfessors with the id of the chosen speciality
        viewModel.getProfessors(selectedSpeciality.id)
        swipeRefreshLayout.isRefreshing = true // Start the refreshing indicator

        viewModel.professorSpecList.observe(viewLifecycleOwner) { professors ->
            if (viewModel.professorSpecList.value?.isNotEmpty() == true) {
                val adapter = ProfessorAdapter()
                adapter.data = viewModel.professorSpecList.value!!
                recyclerView.adapter = adapter
                swipeRefreshLayout.isRefreshing = false // Stop the refreshing indicator
            }
            Log.d("Change in the professors", professors.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//    private fun updateSpinnerAdapter(specialities: List<Specialite>) {
//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, specialities)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.specialiteSpinner.adapter = adapter
//    }
