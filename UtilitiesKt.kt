package com.example.appname

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class UtilitiesKt {
    val ERROR_SYNTAX = Color.RED
    val SUCCESS_SYNTAX = Color.GREEN
    val WHITE_SYNTAX = Color.WHITE
    val BLACK_SYNTAX = Color.BLACK
    val INFO_SYNTAX = Color.BLUE
    val HIDDEN_SYNTAX = Color.LTGRAY

    val FONT_SIZE_S = 12f
    val FONT_SIZE_SM = 14f
    val FONT_SIZE_M = 16f
    val FONT_SIZE_ML = 20f
    val FONT_SIZE_L = 22f
    val FONT_SIZE_XL = 26f

    fun Snack(view: View, text: String = "Snackbar!", color: Int = Color.LTGRAY, textColor: Int = Color.BLUE, textSize: Float = 20f) {
        val snackbar = Snackbar.make(view, text,
            Snackbar.LENGTH_LONG).setAction("Action", null)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(color)
        val snackbarText = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        snackbarText.setTextColor(textColor)
        snackbarText.textSize = textSize
        snackbar.show()
    }

    fun random(range: IntRange): Int {
        return Random().nextInt(range.last - range.start)
    }

    fun Alert(context: Context, title: String, message: String) {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage(message).setCancelable(true)
        //Display the alert
        val alert = dialog.create()
        alert.setTitle(title)
        alert.show()
    }
}
