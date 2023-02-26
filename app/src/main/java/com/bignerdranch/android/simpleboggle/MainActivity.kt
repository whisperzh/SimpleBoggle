package com.bignerdranch.android.simpleboggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bignerdranch.android.simpleboggle.Utils.FileManager
import com.bignerdranch.android.simpleboggle.Utils.PointCalculator
import com.bignerdranch.android.simpleboggle.Utils.WordDetector
import com.bignerdranch.android.simpleboggle.databinding.ActivityMainBinding
import com.bignerdranch.android.simpleboggle.interfaces.ActivityCallback
import com.bignerdranch.android.simpleboggle.interfaces.LowerFragmentCallback
import com.bignerdranch.android.simpleboggle.interfaces.UpperFragmentCallback

class MainActivity : AppCompatActivity(), ActivityCallback {

    private var score:Int=0

    private lateinit var binding: ActivityMainBinding

    private lateinit var lowerFragmentCallback: LowerFragmentCallback

    private lateinit var upperFragmentCallback: UpperFragmentCallback

    private lateinit var fileManager: FileManager

    private var previousWordResults:MutableSet<String> = mutableSetOf<String>()

    private lateinit var dictionary: HashSet<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        fileManager = FileManager()
        dictionary = fileManager.readFileFromAssetManager("words.txt",this)
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
        upperFragmentCallback.resetButtonsAvailability()
        previousWordResults.clear()
    }

    override fun checkWord(word: String) {
        getFragments()
        var checker= WordDetector()
        var res=checker.detectWord(word, previousWordResults )
        var points=0
        var success=res.get(200)
        if(success!=null)
        {
            Toast.makeText(this, success, Toast.LENGTH_SHORT).show()
            points=PointCalculator().calculateScore(dictionary,word)
        }else
        {
            Toast.makeText(this, res.get(400), Toast.LENGTH_SHORT).show()
            points=-10
        }
        if(points>0)
            previousWordResults.add(word)
        score+=points
        lowerFragmentCallback.updateScoreText(score)
    }
}