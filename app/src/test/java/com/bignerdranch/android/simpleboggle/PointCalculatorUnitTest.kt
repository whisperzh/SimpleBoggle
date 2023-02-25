package com.bignerdranch.android.simpleboggle

import android.graphics.Point
import com.bignerdranch.android.simpleboggle.Utils.FileManager
import com.bignerdranch.android.simpleboggle.Utils.PointCalculator
import org.junit.Assert.*
import org.junit.Test

class PointCalculatorUnitTest {

    private val pointCalculator = PointCalculator()
    private val url = "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt"
    private val fileManager = FileManager()
    private val dic = fileManager.createDictionaryFromURL(url)

    @Test
    fun wordIsInCalculator() {
        assertTrue(pointCalculator.checkWordIsInDictionary(dic,"ZZZ"))
    }

    @Test
    fun wordIsInNotInCalculator() {
        assertFalse(pointCalculator.checkWordIsInDictionary(dic,"ZZ Z"))
    }


    @Test
    fun calculateScoreWithWrongWord(){
        assertEquals(pointCalculator.calculateScore(dic,"ZZ Z"), -10)
    }


    @Test
    fun calculateScoreWithRightWordWithDoubleScore(){
        assertEquals(pointCalculator.calculateScore(dic,"SLOPE"),26)
    }


    @Test
    fun calculateScoreWithRightWordNoDoubleScore(){
        assertEquals(pointCalculator.calculateScore(dic,"HARBOR"),14)
    }
}