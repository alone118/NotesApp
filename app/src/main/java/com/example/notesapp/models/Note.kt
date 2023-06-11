package com.example.notesapp.models

import java.io.Serializable
import java.util.Date

data class Note (
    // Данные для Simple note item
    val title: String, // общее
    val description: String,
    val lastEditedDate: Date,// общее
    val isSimpleNote: Boolean,

    // Данные для check box note item
    val checkBoxIsCheckedList: List<Boolean>,
    val checkBoxTitlesList: List<String>
): Serializable