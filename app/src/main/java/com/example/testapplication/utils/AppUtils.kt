package com.example.testapplication.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.example.testapplication.R

fun passwordToggle(imgIcon: AppCompatImageView, edittext: EditText) {
    if (edittext.text.isEmpty())
        return
    if (edittext.transformationMethod == PasswordTransformationMethod.getInstance()) {
        edittext.transformationMethod = HideReturnsTransformationMethod.getInstance()
        imgIcon.setImageDrawable(
            ContextCompat.getDrawable(
                imgIcon.context,
                R.drawable.ic_eye
            )
        )
    } else {
        edittext.transformationMethod = PasswordTransformationMethod.getInstance()
        imgIcon.setImageDrawable(
            ContextCompat.getDrawable(
                imgIcon.context,
                R.drawable.ic_eye
            )
        )
    }
    edittext.setSelection(edittext.text.toString().length)

}