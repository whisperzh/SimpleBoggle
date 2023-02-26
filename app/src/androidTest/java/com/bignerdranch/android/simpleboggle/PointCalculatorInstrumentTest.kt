package com.bignerdranch.android.simpleboggle

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bignerdranch.android.simpleboggle.Utils.PointCalculator

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class PointCalculatorInstrumentTest {

    @Before
    fun setUp() {
    }


    @Test
    fun testCalculateScoreWithWrongWord() {
        val dictionary = HashSet<String>()
        dictionary.add("harbor")
        val pointCalculator = PointCalculator()

        val result = pointCalculator.calculateScore(dictionary, "ZZ Z")

        assertEquals("Expected score not returned", -10, result)
    }

    @Test
    fun testCalculateScoreWithRightWordNoDoubleScore() {
        val dictionary = HashSet<String>()
        dictionary.add("harbor")
        val pointCalculator = PointCalculator()

        val result = pointCalculator.calculateScore(dictionary, "HARBOR")

        assertEquals("Expected score not returned", 14, result)
    }


    @Test
    fun testCalculateScoreWithRightWordWithDoubleScore() {
        val dictionary = HashSet<String>()
        dictionary.add("slope")
        val pointCalculator = PointCalculator()

        val result = pointCalculator.calculateScore(dictionary, "SLOPE")

        assertEquals("Expected score not returned", 26, result)
    }
}

