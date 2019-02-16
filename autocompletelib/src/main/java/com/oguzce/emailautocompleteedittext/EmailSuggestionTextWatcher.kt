package com.oguzce.emailautocompleteedittext

import android.content.Context
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.text.color

class EmailSuggestionTextWatcher
@JvmOverloads
constructor(
    private val editText: EditText,
    private var suggestionList: List<String> = emptyList(),
    private val inputColor: Int,
    private val suggestionColor: Int
) : TextWatcher {

    private val AT_MARK = "@"
    private var isInputFromAutoComplete = false
    private var hasSuggestion = false

    init {
        editText.setOnFocusChangeListener { _, _ -> onFocusChanged() }
    }

    override fun afterTextChanged(editable: Editable?) {
        editable?.let {
            onTextChanged(editText, editable)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    private fun onTextChanged(editText: EditText, editable: Editable) {
        showEmailAutoCompleteSuggestion(editText, editable)
    }

    private fun onFocusChanged() {
        editText.text = SpannableStringBuilder()
            .color(inputColor) {
                append(editText.text)
            }
    }

    fun setSuggestionList(suggestions: List<String>) {
        suggestionList = suggestions
    }

    fun clearTextWatcher() {
        editText.removeTextChangedListener(this)
    }

    private fun showEmailAutoCompleteSuggestion(editText: EditText, editable: Editable) {
        if (editText.selectionStart == -1) {
            return
        }
        if (editable.contains(AT_MARK).not() && hasSuggestion) {
            deleteSuggestion(editText, editable)
            return
        }
        val currentString = editable.substring(0, editText.selectionStart)
        if (shouldSuggest(currentString)) {
            val domainText = currentString.substring(currentString.indexOf(AT_MARK))
            val hit = getEmailSuggestionForPrefix(domainText)
            showEmailSuggestion(editText, currentString, hit?.substring(domainText.length))
        } else {
            isInputFromAutoComplete = false
        }
    }

    private fun getEmailSuggestionForPrefix(prefix: String): String? {
        return suggestionList.firstOrNull { it.startsWith(prefix) }
    }

    private fun shouldSuggest(currentText: String) =
        isInputFromAutoComplete.not() && currentText.contains(AT_MARK)

    private fun showEmailSuggestion(editText: EditText, currentText: String, suggestion: String?) {
        val stringBuilder = SpannableStringBuilder(currentText)
        appendSuggestion(editText.context, stringBuilder, suggestion)
        showSuggestedText(editText, stringBuilder, currentText.length)
    }

    private fun appendSuggestion(context: Context, stringBuilder: SpannableStringBuilder, suggestion: String?) {
        stringBuilder
            .color(suggestionColor) {
                append(suggestion ?: "")
            }
    }

    private fun showSuggestedText(editText: EditText, stringBuilder: SpannableStringBuilder, position: Int) {
        editText.post {
            editText.text = stringBuilder
            editText.setSelection(position)
        }
        isInputFromAutoComplete = true
        hasSuggestion = true
    }

    private fun deleteSuggestion(editText: EditText, editable: Editable) {
        editText.post {
            editText.text = editable.delete(editText.selectionStart, editable.length)
            editText.setSelection(editable.length)
        }
        isInputFromAutoComplete = true
        hasSuggestion = false
    }

}