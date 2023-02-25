package com.bignerdranch.android.simpleboggle.Utils

private val VOWELS = hashSetOf<Char>('A','E','I','O','U')
private val NOT_MEET_WORD_LENGTH_MESSAGE = "Error, word must be at least 4 chars long"
private val NOT_ENOUGH_VOWELS = "Error, word must contain minimum of 2 vowels"
private val REPEATED_WORD = "Error, you have entered this word before"
private val CORRECT_WORD_FORMAT = "Word's format is correct"
private val ERROR_CODE = 400
private val SUCCESS_CODE = 200

class WordDetector {

    /**
     * Takes a word and a MutableSet that contains the words the user entered before, to check if
     * the word's format is acceptable to submit.
     * It will return a HashMap:
     *  key is the status, 400 means the word is not acceptable, 200 means the word is good to submit
     *  value is the message, it can be used to display in the Toast
     */
    fun detectWord(word:String, answeredWords:MutableSet<String>): HashMap<Int, String>{
        if (!meetNumOfCharRequirement(word,4)){
            return hashMapOf(ERROR_CODE to NOT_MEET_WORD_LENGTH_MESSAGE)
        }
        if (!containCertainAmountOfVowels(word,2)){
            return hashMapOf(ERROR_CODE to NOT_ENOUGH_VOWELS)
        }
        if (checkNewWordOccurredBefore(answeredWords,word)){
            return hashMapOf(ERROR_CODE to REPEATED_WORD)
        }
        return hashMapOf(SUCCESS_CODE to CORRECT_WORD_FORMAT)
    }


    fun containCertainAmountOfVowels(word: String, charNumRequirement:Int): Boolean{
        var count = 0
        for (i in word){
            if(VOWELS.contains(i)){
                count += 1
            }
        }
        return count >= charNumRequirement
    }

    fun meetNumOfCharRequirement(word:String, numOfCharRequirement:Int): Boolean{
        return word.length >= numOfCharRequirement
    }


    fun checkNewWordOccurredBefore(pastWords: MutableSet<String>, newWord: String):Boolean{
        if (pastWords.contains(newWord)){
            return true
        }
        for (word in pastWords){
            if (checkTwoWordsHaveSameCharacterButInDifferentOrder(word,newWord)){
                return true
            }
        }
        return false
    }


    fun checkTwoWordsHaveSameCharacterButInDifferentOrder(oldWord: String, newWord:String):Boolean{
        val oldWordHashSet = hashSetOf<Char>()
        for (i in oldWord){
            oldWordHashSet.add(i)
        }
        val newWordHashSet = hashSetOf<Char>()
        for (j in newWord){
            newWordHashSet.add(j)
        }
        if (oldWordHashSet.size != newWordHashSet.size){
            return false
        }
        for(c in newWordHashSet){
            if (!oldWordHashSet.contains(c)){
                return false
            }
        }
        return true
    }




}