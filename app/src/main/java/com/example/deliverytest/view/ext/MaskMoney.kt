package com.example.deliverytest.view.ext

import java.text.NumberFormat
import java.util.*

fun String?.toMoneyFormatClean(): String {
    return if (this.isNullOrEmpty()) {
        ""
    } else {
        val nf = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        nf.format(this.replace(",", ".").toDouble())
    }
}

fun String?.unmask(): String {
    return this?.replace("[.]".toRegex(), "")?.replace("[-]".toRegex(), "")
        ?.replace("[/]".toRegex(), "")?.replace("[(]".toRegex(), "")
        ?.replace("[)]".toRegex(), "") ?: ""
}