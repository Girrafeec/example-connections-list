/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

fun String.removeTrailingZeros(): String {
    val number = this.toDoubleOrNull()
    return number?.toString()?.removeSuffix(".0") ?: this
}

fun String.cyrillicToLatin(): String {
    val builder = StringBuilder()

    for (char in this) {
        val transliteratedChar = cyrillicTransliterationAlphabet[char]
        if (transliteratedChar == null)
            builder.append(char)
        else
            builder.append(transliteratedChar)
    }

    return builder.toString()
}

internal val cyrillicTransliterationAlphabet: Map<Char, String> = mapOf(
    ' ' to " ",
    'А' to "A",
    'а' to "а",
    'Б' to "B",
    'б' to "b",
    'В' to "V",
    'в' to "v",
    'Г' to "G",
    'г' to "g",
    'Д' to "D",
    'д' to "d",
    'Е' to "E",
    'е' to "e",
    'Ё' to "Yo",
    'ё' to "yo",
    'Ж' to "Zh",
    'ж' to "zh",
    'З' to "Z",
    'з' to "z",
    'И' to "I",
    'и' to "i",
    'Й' to "I",
    'й' to "i",
    'К' to "K",
    'к' to "k",
    'Л' to "L",
    'л' to "l",
    'М' to "M",
    'м' to "m",
    'Н' to "N",
    'н' to "n",
    'О' to "O",
    'о' to "o",
    'П' to "P",
    'п' to "p",
    'Р' to "R",
    'р' to "r",
    'С' to "S",
    'с' to "s",
    'Т' to "T",
    'т' to "t",
    'У' to "U",
    'у' to "u",
    'Ф' to "F",
    'ф' to "f",
    'Х' to "Kx",
    'х' to "kx",
    'Ц' to "Ts",
    'ц' to "ts",
    'Ч' to "Ch",
    'ч' to "ch",
    'Ш' to "Sh",
    'ш' to "sh",
    'Щ' to "Shch",
    'щ' to "shch",
    'Ъ' to "",
    'ъ' to "",
    'Ы' to "Y",
    'ы' to "y",
    'Ь' to "",
    'ь' to "",
    'Э' to "E",
    'э' to "e",
    'Ю' to "Yu",
    'ю' to "yu",
    'Я' to "Ia",
    'я' to "ia"
)