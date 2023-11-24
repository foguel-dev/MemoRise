package com.example.memorise.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memorise.repository.NoteRepo

class NoteViewModelProviderFactory(
    val app: Application,
    private val noteRepository: NoteRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository) as T
    }
}