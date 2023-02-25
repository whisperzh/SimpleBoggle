package com.bignerdranch.android.simpleboggle

import com.bignerdranch.android.simpleboggle.Utils.FileManager
import org.junit.Test
import org.junit.Assert.*

class FileManagerUnitTest {

    private val fileManager = FileManager()
    private val dictionaryUrl = "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt"


    @Test
    fun canCreateAHashSetDictionaryFromURL(){
        assertNotNull(fileManager.createDictionaryFromURL(dictionaryUrl))
    }
}