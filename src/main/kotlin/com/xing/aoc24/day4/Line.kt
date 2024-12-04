package com.xing.aoc24.day4

typealias Line = String
val regexXMAS = "XMAS".toRegex()
val regexSAMX = "SAMX".toRegex()

fun Line.countXMAS()= regexXMAS.findAll(this).count()
fun Line.countSAMX()= regexSAMX.findAll(this).count()
