package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.dd.Database
import com.example.notesapp.models.Note

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter = NotesAdapter(
            navigateToSimpleDetailsScreen = ::navigateToSimpleDetailsScreen,
            navigateToCheckBoxDetailsScreen = ::navigateToCheckBoxDetailsScreen,
        )
       private val datebase by lazy {
            Database(context = this)
        }
   private val allNotesList by lazy {
       Database(this).getAllNotes().toMutableList()
   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchView.setOnQueryTextListener(this)

        adapter.updateList(allNotesList)

        binding.mainFloatingActionButton.setOnClickListener {
            binding.checkboxFloatingActionButton.isVisible = !binding.checkboxFloatingActionButton.isVisible
            binding.simpleFloatingActionButton.isVisible = !binding.simpleFloatingActionButton.isVisible

        }
        binding.checkboxFloatingActionButton.setOnClickListener {
            handleActionButtonsClick(
                isSimpleNote = false,
                allNotesList = allNotesList
            )
        }

        binding.simpleFloatingActionButton.setOnClickListener {
            handleActionButtonsClick(
                isSimpleNote = true,
                allNotesList = allNotesList
            )
        }

        binding.recyclerView.adapter = adapter
    }

    private fun handleActionButtonsClick(
        isSimpleNote: Boolean,
        allNotesList: MutableList<Note>,
    ) {
        val note = datebase.saveNewNote(isSimpleNote = isSimpleNote, allNotesList = allNotesList)
        allNotesList.add(note)
        adapter.updateList(allNotesList)
        if (isSimpleNote) navigateToSimpleDetailsScreen(note)
        else navigateToCheckBoxDetailsScreen(note)
    }

    private fun navigateToSimpleDetailsScreen(note: Note) {
        val intent = Intent(this, SimpleNoteDetailsActivity::class.java)
        intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

    private fun navigateToCheckBoxDetailsScreen(note: Note) {
        val intent = Intent(this, CheckNoteDetailsActivity::class.java)
        intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val searchString = query?: return false
        startSearch(searchString)
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        val searchString = query?: return false
        startSearch(searchString)
        return false
    }
    private fun startSearch(
        query: String
    ) {
        if (query.isEmpty()){
            adapter.updateList(allNotesList)
            return
        }
        val sortedNoteList = allNotesList.filter { note: Note ->
            val isSort = note.title.contains(query, ignoreCase = true)
            isSort
        }

        adapter.updateList(sortedNoteList)
    }
}
const val NOTE_KEY = "NOTE_KEY"

