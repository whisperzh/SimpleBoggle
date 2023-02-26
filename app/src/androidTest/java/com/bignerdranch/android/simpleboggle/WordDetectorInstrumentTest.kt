package com.bignerdranch.android.simpleboggle

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.bignerdranch.android.simpleboggle.Utils.WordDetector

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class WordDetectorInstrumentTest {
    private val pastWords = mutableSetOf("SLOPE", "HOPE", "API")
    private lateinit var wordDetector: WordDetector

    @Before
    fun setUp() {
        wordDetector = WordDetector()
    }

    @Test
    fun testWordVowelNumberIsCorrect() {
        val word = "PRIDE"
        val expectedMap = hashMapOf(200 to "Word's format is correct")
        assertTrue(wordDetector.containCertainAmountOfVowels(word, 2))
        assertEquals(expectedMap, wordDetector.detectWord(word, pastWords))
    }

    @Test
    fun testWordVowelNumberIsNotCorrect() {
        val word = "STING"
        val expectedMap = hashMapOf(400 to "Error, word must contain minimum of 2 vowels")
        assertFalse(wordDetector.containCertainAmountOfVowels(word, 2))
        assertEquals(expectedMap, wordDetector.detectWord(word, pastWords))
    }

    @Test
    fun testWordLengthIsCorrect() {
        val word = "SLOPE"
        assertTrue(wordDetector.meetNumOfCharRequirement(word, 4))
    }

    @Test
    fun testWordLengthIsCorrect2() {
        val word = "HOPE"
        assertTrue(wordDetector.meetNumOfCharRequirement(word, 4))
    }

    @Test
    fun testWordLengthIsNotCorrect() {
        val word = "APE"
        val expectedMap = hashMapOf(400 to "Error, word must be at least 4 chars long")
        assertFalse(wordDetector.meetNumOfCharRequirement(word, 4))
        assertEquals(expectedMap, wordDetector.detectWord(word, pastWords))
    }

    @Test
    fun testCheckTwoWordsHaveSameCharacterButInDifferentOrderIsCorrect() {
        assertTrue(wordDetector.checkTwoWordsHaveSameCharacterButInDifferentOrder("API", "IPA"))
        assertTrue(wordDetector.checkTwoWordsHaveSameCharacterButInDifferentOrder("SLOPE", "OEPLS"))
    }

    @Test
    fun testCheckTwoWordsHaveSameCharacterButInDifferentOrderIsNotCorrect() {
        assertFalse(wordDetector.checkTwoWordsHaveSameCharacterButInDifferentOrder("API", "APIS"))
    }


}