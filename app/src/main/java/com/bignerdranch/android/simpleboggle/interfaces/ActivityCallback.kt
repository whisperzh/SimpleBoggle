package com.bignerdranch.android.simpleboggle.interfaces

interface ActivityCallback {
    fun passScore(score:Int)
    fun resetGame()
    fun passCommand(command:String)
}