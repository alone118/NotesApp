package com.example.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.databinding.ActivitySimpleNoteDetailsBinding
import com.example.notesapp.dd.Database
import com.example.notesapp.models.Note

class SimpleNoteDetailsActivity : AppCompatActivity(){

    private val binding:ActivitySimpleNoteDetailsBinding by lazy {
        ActivitySimpleNoteDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val note = intent.extras?.getSerializable(NOTE_KEY) as? Note
        initViews(note)

        val database = Database(this)
        binding.saveChangeButton.setOnClickListener {
            database.updateSimpleNote(
                oldNote =note!!,
                title = binding.titleTextView.text.toString(),
                description = binding.descriptionTextView.text.toString(),
            )
        }
    }

    private fun initViews(note: Note?) {
        if (note == null) return
        binding.titleTextView.setText(note.title)
        binding.descriptionTextView.setText(note.description)
        binding.lastEditedTextView.text = "Last edited: " + note.lastEditedDate.toString()
    }

}