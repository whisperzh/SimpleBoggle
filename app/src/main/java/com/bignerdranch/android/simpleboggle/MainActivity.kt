package com.bignerdranch.android.simpleboggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(),ActivityCallback {

    private var score:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun passScore(score: Int) {
        this.score=score
        Log.d(this.localClassName,score.toString())
        TODO("Not yet implemented")
    }

    override fun passCommand(command: String) {
        Log.d(this.localClassName,command)
    }
}