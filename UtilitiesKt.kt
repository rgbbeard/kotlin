package com.app.name

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.app.name.R
import java.lang.Double.parseDouble
import java.util.*

class UtilitiesKt {
    val success_syntax = R.color.success
    val warning_syntax = R.color.warning
    val error_syntax = R.color.error
    val white_syntax = R.color.white
    val black_syntax = R.color.black2
    val info_syntax = R.color.info
    val hidden_syntax = R.color.success

    val font_size_s = 12f
    val font_size_sm = 14f
    val font_size_m = 16f
    val font_size_ml = 20f
    val font_size_l = 22f
    val font_size_xl = 26f

    fun Snack(view: View, text: String = "Snackbar!", color: Int = this.hidden_syntax, textColor: Int = this.info_syntax, textSize: Float = this.font_size_ml) {
        val snackbar = Snackbar.make(view, text,
            Snackbar.LENGTH_LONG).setAction("Action", null)

        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(color)

        val snackbarText = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView

        snackbarText.setTextColor(textColor)
        snackbarText.textSize = textSize
        snackbar.show()
    }

    fun ErrorSnack(view: View, text: String = "Snackbar!", textSize: Float = this.font_size_sm) {
        this.Snack(
            view,
            text,
            UtilitiesKt().error_syntax,
            UtilitiesKt().white_syntax,
            textSize
        )
    }

    fun NormalSnack(view: View, text: String = "Snackbar!", textSize: Float = this.font_size_sm) {
        this.Snack(
            view,
            text,
            UtilitiesKt().hidden_syntax,
            UtilitiesKt().white_syntax,
            textSize
        )
    }

    fun InfoSnack(view: View, text: String = "Snackbar!", textSize: Float = this.font_size_sm) {
        this.Snack(
            view,
            text,
            UtilitiesKt().info_syntax,
            UtilitiesKt().white_syntax,
            textSize
        )
    }

    fun WarningSnack(view: View, text: String = "Snackbar!", textSize: Float = this.font_size_sm) {
        this.Snack(
            view,
            text,
            UtilitiesKt().warning_syntax,
            UtilitiesKt().white_syntax,
            textSize
        )
    }

    fun SuccessSnack(view: View, text: String = "Snackbar!", textSize: Float = this.font_size_sm) {
        this.Snack(
            view,
            text,
            UtilitiesKt().success_syntax,
            UtilitiesKt().white_syntax,
            textSize
        )
    }

    fun random(range: IntRange): Int {
        return Random().nextInt(range.last - range.first)
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

fun Array<Any>.inArray(item: Any): Boolean {
    for(x in this) {
        if(x == item) {
            return true
        }
    }

    return false
}

fun String.isNum(): Boolean {
    var numeric = true;

    try {
        parseDouble(this)
    } catch(e: NumberFormatException) {
        numeric = false;
    } catch(e: Exception) {
        numeric = false;
    }

    return numeric;
}
