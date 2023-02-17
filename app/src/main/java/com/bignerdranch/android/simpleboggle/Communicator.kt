package com.bignerdranch.android.simpleboggle

interface Communicator {
    fun passScore(score:Int)
    fun passCommand(command:String)
}