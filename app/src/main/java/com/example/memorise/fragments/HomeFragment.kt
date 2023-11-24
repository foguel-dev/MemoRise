package com.example.memorise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.memorise.R
import com.example.memorise.databinding.FragmentHomeBinding
import com.example.memorise.databinding.FragmentNoteBinding
import com.example.memorise.model.Note
import com.example.memorise.ui.MainActivity
import com.example.memorise.ui.adapter.NoteAdapter
import com.example.memorise.viewModel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home),
    SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).noteViewModel
        setUpRecyclerView()

        binding.fabAddNote.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_noteFragment2)
        }
    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            notesViewModel.getAllNote().observe(viewLifecycleOwner) { note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)
            }
        }

    }

    private fun updateUI(note: List<Note>) {
        if (note.isNotEmpty()) {
            binding.lottie.visibility = View.GONE
            binding.text.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.lottie.visibility = View.VISIBLE
            binding.text.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }



    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}