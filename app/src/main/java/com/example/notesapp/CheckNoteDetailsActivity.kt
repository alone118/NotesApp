package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivityCheckNoteDetailsBinding
import com.example.notesapp.dd.Database
import com.example.notesapp.models.Note

class CheckNoteDetailsActivity : AppCompatActivity() {

    val binding: ActivityCheckNoteDetailsBinding by lazy {
        ActivityCheckNoteDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val database = Database(this)

        binding.saveChangesButton.setOnClickListener {
            val note = intent.extras?.getSerializable(NOTE_KEY) as Note
            database.updateCheckNote(
                oldNote = note!!,
                title = binding.titleTextView.text.toString(),
                titles = listOf(
                    binding.firstCheckBoxTitle.text.toString(),
                    binding.secondCheckBoxTitle.text.toString(),
                    binding.thirdCheckBoxTitle.text.toString(),
                    binding.fourCheckBoxTitle.text.toString(),
                    binding.fifthCheckBoxTitle.text.toString(),
                    binding.sixthCheckBoxTitle.text.toString(),
                ),
                isCheckedList = listOf(
                    binding.firstCheckBox.isChecked,
                    binding.secondCheckBox.isChecked,
                    binding.thirdCheckBox.isChecked,
                    binding.fourCheckBox.isChecked,
                    binding.fifthCheckBox.isChecked,
                    binding.sixthCheckBox.isChecked,
                )
            )
        }

        val note = intent.extras?.getSerializable(NOTE_KEY) as Note

        if (note.checkBoxTitlesList.isNotEmpty()) {
            binding.firstCheckBoxTitle.setText(note.checkBoxTitlesList[0])
        }
        if (note.checkBoxTitlesList.size > 1) {
            binding.secondCheckBoxTitle.setText(note.checkBoxTitlesList[1])
        }
        if (note.checkBoxTitlesList.size > 2) {
            binding.thirdCheckBoxTitle.setText(note.checkBoxTitlesList[2])
        }
        if (note.checkBoxTitlesList.size > 3) {
            binding.fourCheckBoxTitle.setText(note.checkBoxTitlesList[3])
        }
        if (note.checkBoxTitlesList.size > 4) {
            binding.fifthCheckBoxTitle.setText(note.checkBoxTitlesList[4])
        }
        if (note.checkBoxTitlesList.size > 5) {
            binding.sixthCheckBoxTitle.setText(note.checkBoxTitlesList[5])
        }
        if (note.checkBoxTitlesList.isNotEmpty()) {
            binding.firstCheckBox.text = note.checkBoxTitlesList[0]
        }
        if (note.checkBoxTitlesList.size > 1) {
            binding.secondCheckBox.text = note.checkBoxTitlesList[1]
        }
        if (note.checkBoxTitlesList.size > 2) {
            binding.thirdCheckBox.text = note.checkBoxTitlesList[2]
        }
        if (note.checkBoxTitlesList.size > 3) {
            binding.fourCheckBox.text = note.checkBoxTitlesList[3]
        }
        if (note.checkBoxTitlesList.size > 4) {
            binding.fifthCheckBox.text = note.checkBoxTitlesList[4]
        }
        if (note.checkBoxTitlesList.size > 5) {
            binding.sixthCheckBox.text = note.checkBoxTitlesList[5]
        }
    }
}
