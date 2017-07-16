package com.hobojoe.enderore

import java.util.*

/**
 * Created by Joseph on 7/15/2017.
 */

fun Int.Companion.random(min: Int, max: Int): Int {
    return (Math.random()*(max-min) + min).toInt()
}

fun Random.range(min: Int, max: Int) : Int {
    return this.nextInt(max - min + 1) + min
}