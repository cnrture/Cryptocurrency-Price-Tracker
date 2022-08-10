package com.canerture.cryptocurrencypricetracker.common

import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, 1500).show()
}

fun TextInputLayout.isNullorEmpty(string: String, errorString: String): Boolean {
    return if (string.trim().isNotEmpty()) {
        isErrorEnabled = false
        true
    } else {
        error = errorString
        false
    }
}

fun TextInputLayout.isValidEmail(string: String, errorString: String): Boolean {
    return if (Patterns.EMAIL_ADDRESS.matcher(string).matches()) {
        isErrorEnabled = false
        true
    } else {
        error = errorString
        false
    }
}