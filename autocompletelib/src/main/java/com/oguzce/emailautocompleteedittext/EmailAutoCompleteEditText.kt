package com.oguzce.emailautocompleteedittext

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class EmailAutoCompleteEditText
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.editTextStyle
) : TextInputEditText(context, attrs, defStyleAttr) {
    private val suggestionColor: Int
    private val inputColor: Int

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmailAutoCompleteEditText)
        inputColor = attributes.getColor(R.styleable.EmailAutoCompleteEditText_inputColor, ContextCompat.getColor(context, R.color.colorNormalText))
        suggestionColor = attributes.getColor(R.styleable.EmailAutoCompleteEditText_suggestionColor, ContextCompat.getColor(context, R.color.colorHintText))
        attributes.recycle()
    }
    private val emailSuggestionTextWatcher: EmailSuggestionTextWatcher
        = EmailSuggestionTextWatcher(this, inputColor = inputColor, suggestionColor = suggestionColor)

    fun setSuggestionList(suggestions: List<String>) {
        emailSuggestionTextWatcher.setSuggestionList(suggestions)
    }

    fun startSuggestionTextWatcher() {
        addTextChangedListener(emailSuggestionTextWatcher)
    }

    fun startSuggestionTextWatcher(list: List<String>) {
        setSuggestionList(list)
        startSuggestionTextWatcher()
    }

    fun stopSuggestionTextWatcher() {
        emailSuggestionTextWatcher.clearTextWatcher()
    }
}