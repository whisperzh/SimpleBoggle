package com.bignerdranch.android.simpleboggle.Utils

class PointCalculator {

    /**
     * Use this function to calculate how many points the word user entered will be worth
     * dic: the dictionary the application downloaded
     * word: the word user entered
     * Return: the point the word is worth.
     */
    fun calculateScore(dic:HashSet<String>, word: String): Int{
        if (!checkWordIsInDictionary(dic,word.lowercase())){
            return -10
        }else{
            return calculateAWordPoints(word)
        }
    }

    private fun calculateAWordPoints(word: String):Int{
        var doubleScoreDecision = 1
        var wordTotalPoint = 0
        val doubleScoreConsonants = mutableSetOf<Char>('S','Z','P','X','Q')
        val vowels = mutableSetOf<Char>('A','E','I','O','U')
        for (i in word){
            if (vowels.contains(i)) {
                wordTotalPoint += 5
            }else{
                wordTotalPoint += 1
                if (doubleScoreConsonants.contains(i)){
                    doubleScoreDecision = 2
                }
            }
        }
        return wordTotalPoint*doubleScoreDecision
    }

    private fun checkWordIsInDictionary(dic: HashSet<String>,word: String):Boolean{
        return dic.contains(word)
    }


}