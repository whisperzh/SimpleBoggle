package com.bignerdranch.android.simpleboggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(),Communicator {

    private var score:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun passScore(score: Int) {
        this.score=score

        TODO("Not yet implemented")
    }

    override fun passCommand(command: String) {
        TODO("Not yet implemented")
    }
}