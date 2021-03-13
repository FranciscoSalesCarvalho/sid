package com.francisco.sid.util

import android.text.TextUtils
import java.util.regex.Pattern

object Validators {
    private val VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile(
            "^([a-z0-9_])+([.\\-]+[a-z0-9]+)*@(?!gmail\\.com\\.br)([a-z0-9\\-_]+\\.)*[a-z0-9]+\\.[a-z]{2,3}$",
            Pattern.CASE_INSENSITIVE
        )

    fun isValidEmail(email: String?): Boolean {
        if (TextUtils.isEmpty(email)) return false

        val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email)
        return matcher.find()
    }
}