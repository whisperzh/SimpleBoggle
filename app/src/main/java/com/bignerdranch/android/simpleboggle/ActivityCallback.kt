package com.bignerdranch.android.simpleboggle

interface ActivityCallback {
    fun passScore(score:Int)
    fun passCommand(command:String)
}