package com.v01d.professormanager.ui.professor.show

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.v01d.professormanager.R
import com.v01d.professormanager.adapters.ProfessorAdapter
import com.v01d.professormanager.databinding.FragmentShowProfessorBinding
import com.v01d.professormanager.ui.specialite.show.ShowSpecialiteFragmentDirections
class ShowProfessorFragment : Fragment() {

    private lateinit var viewModel: ShowProfessorViewModel
    private lateinit var professorRecycleView: RecyclerView
    private lateinit var professorAdapter: ProfessorAdapter
    private var _binding: FragmentShowProfessorBinding? = null
    private val binding get() = _binding!!

    private val adapter: ProfessorAdapter by lazy {
        ProfessorAdapter().apply {
            onDeleteClickListener = { professor ->
                viewModel.deleteProfessor(professor.id)
            }
            onUpdateClickListener = { professor ->
                val action = ShowProfessorFragmentDirections
                    .actionShowProfessorFragmentToUpdateProfessorFragment(professor.id)
                findNavController().navigate(action)
            }
        }
    }

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowProfessorBinding.inflate(inflater, container, false)
        val view = binding.root

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        val recyclerView = view.findViewById<RecyclerView>(R.id.professorRecycleView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            // Handle the refresh action, typically by re-fetching data
            viewModel.getProfessors()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ShowProfessorViewModel::class.java)

        viewModel.professorList.observe(viewLifecycleOwner) { professors ->
            adapter.data = professors
            adapter.notifyDataSetChanged()

            // Stop the refresh animation
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.getProfessors()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
