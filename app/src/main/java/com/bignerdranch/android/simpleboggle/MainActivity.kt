package com.bignerdranch.android.simpleboggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.bignerdranch.android.simpleboggle.databinding.ActivityMainBinding
import com.bignerdranch.android.simpleboggle.interfaces.ActivityCallback
import com.bignerdranch.android.simpleboggle.interfaces.LowerFragmentCallback
import com.bignerdranch.android.simpleboggle.interfaces.UpperFragmentCallback

class MainActivity : AppCompatActivity(), ActivityCallback {

    private var score:Int=0

    private lateinit var binding: ActivityMainBinding

    private lateinit var lowerFragmentCallback: LowerFragmentCallback

    private lateinit var upperFragmentCallback: UpperFragmentCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
    }

    private fun getFragments(){
        lowerFragmentCallback=supportFragmentManager.findFragmentById(R.id.lower_fragment) as LowerFragmentCallback
        upperFragmentCallback=supportFragmentManager.findFragmentById(R.id.upper_fragment) as UpperFragmentCallback
    }



    override fun passScore(score: Int) {
        getFragments()
        this.score=score
        Log.d(this.localClassName,score.toString())
        lowerFragmentCallback.updateScoreText(score)
        upperFragmentCallback.clearResultText()
    }

    override fun resetGame() {
        getFragments()
        score=0;
        upperFragmentCallback.clearResultText()
        upperFragmentCallback.randomizeCharacters()
    }

    override fun passCommand(command: String) {
        Log.d(this.localClassName,command)
    }
}