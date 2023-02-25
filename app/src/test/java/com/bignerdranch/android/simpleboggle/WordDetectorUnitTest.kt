package com.bignerdranch.android.simpleboggle

import com.bignerdranch.android.simpleboggle.Utils.WordDetector
import org.junit.Assert.*
import org.junit.Test

class WordDetectorUnitTest {
    private val wordDetector = WordDetector()
    private val pastWords = mutableSetOf("SLOPE", "HOPE", "API")

    @Test
    fun wordVowelNumber_isCorrect() {
        assertTrue(wordDetector.containCertainAmountOfVowels("PRIDE",2))
        assertEquals(wordDetector.detectWord("PRIDE",pastWords), hashMapOf(200 to "Word's format is correct"))
    }


    @Test
    fun wordVowelNumber_isNotCorrect(){
        assertFalse(wordDetector.containCertainAmountOfVowels("STING",2))
        assertEquals(wordDetector.detectWord("PLANT",pastWords), hashMapOf(400 to "Error, word must contain minimum of 2 vowels"))
    }


    @Test
    fun wordLength_isCorrect(){
        assertTrue(wordDetector.meetNumOfCharRequirement("SLOPE",4))
    }


    @Test
    fun wordLength_isCorrect2(){
        assertTrue(wordDetector.meetNumOfCharRequirement("HOPE",4))
    }


    @Test
    fun wordLength_isNotCorrect(){
        assertFalse(wordDetector.meetNumOfCharRequirement("APE",4))
        assertEquals(wordDetector.detectWord("APE",pastWords), hashMapOf(400 to "Error, word must be at least 4 chars long"))
    }


    @Test
    fun checkTwoWordsHaveSameCharacterButInDifferentOrder_isCorrect(){
        assertTrue(wordDetector.checkTwoWordsHaveSameCharacterButInDifferentOrder("API","IPA"))
    }


    @Test
    fun checkTwoWordsHaveSameCharacterButInDifferentOrder_isCorrect2(){
        assertTrue(wordDetector.checkTwoWordsHaveSameCharacterButInDifferentOrder("SLOPE","OEPLS"))
    }


    @Test
    fun checkTwoWordsHaveSameCharacterButInDifferentOrder_isNotCorrect(){
        assertFalse(wordDetector.checkTwoWordsHaveSameCharacterButInDifferentOrder("API","APIS"))
    }

    @Test
    fun checkWordOccurredBefore_isCorrect(){
        assertTrue(wordDetector.checkNewWordOccurredBefore(pastWords,"SLOPE"))
        assertTrue(wordDetector.checkNewWordOccurredBefore(pastWords,"ESOPL"))
        assertEquals(wordDetector.detectWord("SLOPE",pastWords), hashMapOf(400 to "Error, you have entered this word before"))
        assertEquals(wordDetector.detectWord("ESOPL",pastWords), hashMapOf(400 to "Error, you have entered this word before"))
    }


    @Test
    fun checkWordOccurredBefore_isNotCorrect(){
        assertFalse(wordDetector.checkNewWordOccurredBefore(pastWords,"APE"))
        assertFalse(wordDetector.checkNewWordOccurredBefore(pastWords,"STING"))
    }

}