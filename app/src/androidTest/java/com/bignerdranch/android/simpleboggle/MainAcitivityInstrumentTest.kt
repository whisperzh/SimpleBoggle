package com.bignerdranch.android.simpleboggle

import android.accessibilityservice.AccessibilityButtonController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bignerdranch.android.simpleboggle.Utils.FileManager
import com.bignerdranch.android.simpleboggle.Utils.PointCalculator
import junit.framework.TestCase.assertNotNull
import org.hamcrest.Matchers.allOf
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private val dic = HashSet<String>()
    private lateinit var pointCalculator: PointCalculator

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Initialize the dictionary and point calculator
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val fileManager = FileManager()
        dic.addAll(fileManager.readFileFromAssetManager("words.txt", context))
        pointCalculator = PointCalculator()
    }

    @Test
    fun initialScoreIsZero() {
        onView(withId(R.id.score_text)).check(matches(withText("0")))
    }
    @Test
    fun scoreUpdatesCorrectlyWithValidWord() {
        onView(withId(R.id.submit_button)).perform(click())
        onView(withId(R.id.score_text)).check(matches(withText("-10")))
    }


    @Test
    fun checkNewGameScore() {
        onView(withId(R.id.newGameButton)).perform(click())
        onView(withId(R.id.score_text)).check(matches(withText("0")))
    }


}

