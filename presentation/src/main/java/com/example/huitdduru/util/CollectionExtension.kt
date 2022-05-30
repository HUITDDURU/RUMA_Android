package com.example.huitdduru.util

fun Collection<String>.getAll() = run {
    var str = ""
    this.forEach {
        str += it
    }
    str
}