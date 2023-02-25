package com.bignerdranch.android.simpleboggle

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.bignerdranch.android.simpleboggle.Utils.PointCalculator
import com.bignerdranch.android.simpleboggle.Utils.Util
import com.bignerdranch.android.simpleboggle.Utils.WordDetector
import com.bignerdranch.android.simpleboggle.databinding.FragmentUpperBinding
import com.bignerdranch.android.simpleboggle.interfaces.ActivityCallback
import com.bignerdranch.android.simpleboggle.interfaces.UpperFragmentCallback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpperFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpperFragment : Fragment() ,UpperFragmentCallback{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentUpperBinding
    private lateinit var activityCallback: ActivityCallback
    private lateinit var util:Util



    private var pressed=mutableSetOf<String>()
    private var lastrow:Int=-1
    private var lastcol:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        util= Util()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpperBinding.inflate(inflater,container,false);
        binding.clearButton.setOnClickListener {
            clearResultText()
        }
        randomizeCharacters()
        bindButtonGroup()
        return binding.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        activityCallback=activity as ActivityCallback

    }

    private fun bindButtonGroup(){
        for(i in 0 until  4)
        {
            val view = binding.buttonContainer.getChildAt(i) as TableRow
            for(j in 0 until 4)
            {
                var button=view.get(j) as Button
                button.setOnClickListener {
                    var arr=getButtonIndex(button)
                    if(validClick(arr))
                    {
                        var str=binding.resultText.text.toString()
                        str+=button.text.toString()
                        button.setBackgroundColor(getResources().getColor(R.color.gray))
                        binding.resultText.setText(str)
                        lastrow=arr[0]
                        lastcol=arr[1]
                        pressed.add(arr[0].toString()+"_"+arr[1].toString())
                    }else
                    {
                        Toast.makeText(context, "your selection is invalid", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.submitButton.setOnClickListener {
            activityCallback.checkWord(binding.resultText.text.toString())
        }
    }
    override fun randomizeCharacters(){
        for(i in 0 until  4)
        {
            val view = binding.buttonContainer.getChildAt(i) as TableRow
            for(j in 0 until 4)
            {
                var button=view.get(j) as Button
                button.setText(util.getRandomCharacter().toString())
            }
        }
    }



    override fun resetButtonsColor() {
        for(i in 0 until  4)
        {
            val view = binding.buttonContainer.getChildAt(i) as TableRow
            for(j in 0 until 4)
            {
                var button=view.get(j) as Button
                button.setBackgroundColor(getResources().getColor(R.color.purple_200))
            }
        }
        pressed.clear()
        lastrow=-1;
        lastcol=-1
    }

    private fun getButtonIndex(tgt_button: Button):Array<Int>
    {
        for(i in 0 until  4)
        {
            val view = binding.buttonContainer.getChildAt(i) as TableRow
            for(j in 0 until 4)
            {
                var button=view.get(j) as Button
                if(button.equals(tgt_button))
                {
                    return arrayOf(i,j);
                }

            }
        }
        return Array(2){-1};
    }

    private fun validClick(currCord:Array<Int>):Boolean{
        if(lastcol==-1&&lastrow==-1)
            return true
        var coordstr=currCord[0].toString()+"_"+currCord[1].toString()
        if(!(currCord[0]==lastrow && currCord[1]==lastcol) &&
                (Math.abs(currCord[0]-lastrow)<=1 && Math.abs(currCord[1]-lastcol)<=1)&&!pressed.contains(coordstr))
            return true
        return false
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpperFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpperFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun clearResultText() {
        binding.resultText.setText("")
        pressed.clear()
        lastrow=-1;
        lastcol=-1
        resetButtonsColor()

    }
}