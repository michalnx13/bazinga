package com.example.bazinga.common

const val CHAR_TO_SUBSTRING_BEFORE = 'T'
fun String.formatToLocalDate(char: Char = CHAR_TO_SUBSTRING_BEFORE) = this.substringBefore(char)