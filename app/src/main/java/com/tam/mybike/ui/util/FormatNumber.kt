package com.tam.mybike.ui.util

import com.tam.mybike.ui.theme.PATTERN_NUMBER
import com.tam.mybike.ui.theme.PATTERN_NUMBER_REPLACE_WITH
import com.tam.mybike.ui.theme.PATTERN_NUMBER_TO_REPLACE
import java.text.DecimalFormat

fun Int.format(): String =
    DecimalFormat(PATTERN_NUMBER)
        .format(this)
        .replace(PATTERN_NUMBER_TO_REPLACE, PATTERN_NUMBER_REPLACE_WITH)