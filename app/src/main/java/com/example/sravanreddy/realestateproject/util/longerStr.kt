package com.example.sravanreddy.realestateproject.util

import kotlin.math.max

var longerStr = { a: String, b: String, acomma: Int, bcomma: Int ->
    if (acomma > bcomma)
        a
    else
        b
}

fun longestBound(inputString: String): String {
    var lBound: String
    var maxBound = ""
    var commaCounter = 0
    var digitStart = 0
    var preCommaCounter = 0
    for (start in inputString.indices) {
        val char: Char = inputString[start]
        if (char == '(') {
            continue
        } else if (char == ',' && inputString[start - 1] != ')') {
            commaCounter++
        } else if (inputString[start] == ')') {
            lBound = inputString.substring(digitStart, start)
            maxBound = longerStr(maxBound, lBound, preCommaCounter, commaCounter)
            preCommaCounter = max(preCommaCounter, commaCounter)
            commaCounter = 0
            if (start == inputString.length - 2 || start == inputString.length - 3) {
                break
            }
        } else if (start > 0 && inputString[start - 1] == '(' && (inputString.get(start).isDigit() || inputString[start] == '-')) {
            digitStart = start
        }
    }
    return maxBound
}