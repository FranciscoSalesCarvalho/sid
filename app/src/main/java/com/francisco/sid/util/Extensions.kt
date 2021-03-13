package com.francisco.sid.util

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.MutableLiveData

fun EditText.setTextWatcherDeErro(erro: MutableLiveData<String>) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (TextUtils.isEmpty(erro.value)) {
                erro.value = ""
            }
        }
    })
}