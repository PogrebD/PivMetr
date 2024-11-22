package com.pogreb.pivmetr.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.annotation.StringRes

/**
 * Создаёт и показывает Toast
 * @param res – ссылка на строковый ресурс, который показываем
 * @param short - если true, то Toast.LENGTH_SHORT, иначе Toast.LENGTH_LONG
 */
fun Context.toast(@StringRes res: Int, short: Boolean = true) {
    makeText(this, res, if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
}

