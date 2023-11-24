package com.example.memorise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.memorise.R
import com.example.memorise.databinding.FragmentNoteBinding
import com.example.memorise.model.Note
import com.example.memorise.ui.MainActivity
import com.example.memorise.viewModel.NoteViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        mView = view
    }

    private fun generateRandomId(): Int {
        return Random.nextInt(1, 9999)
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()

        val randomId = generateRandomId()

        if (noteTitle.isNotEmpty()) {
            val note = Note(randomId, noteTitle, noteBody)

            noteViewModel.addNote(note)
            Snackbar.make(
                view, "Nota guardada",
                Snackbar.LENGTH_SHORT
            ).show()
            view.findNavController().navigate(R.id.action_noteFragment2_to_homeFragment2)

        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.new_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
