package com.oguzce.autocompleteemailview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.buttonClear
import kotlinx.android.synthetic.main.activity_main.editTextEmail
import kotlinx.android.synthetic.main.activity_main.editTextNextFocus

class MainActivity : AppCompatActivity() {
    private val domainList by lazy { this.resources.getStringArray(R.array.email_domains).toList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonClear.setOnClickListener { clearTextInput() }
    }

    override fun onResume() {
        super.onResume()
        editTextEmail.startSuggestionTextWatcher(domainList)
        editTextNextFocus.startSuggestionTextWatcher(domainList)
    }

    override fun onPause() {
        super.onPause()
        editTextEmail.stopSuggestionTextWatcher()
        editTextNextFocus.stopSuggestionTextWatcher()
    }

    private fun clearTextInput() {
        editTextEmail.setText("")
        editTextNextFocus.setText("")
    }
}
