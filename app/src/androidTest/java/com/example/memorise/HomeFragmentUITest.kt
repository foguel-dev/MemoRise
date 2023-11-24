package com.example.memorise

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.memorise.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class HomeFragmentUITest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testNotesDisplay() {
        // Given: The app is open

        // When: Navigating to the home fragment
        onView(withId(R.id.homeFragment)).perform(click())

        // Then: Verify the RecyclerView is displayed
       // onView(withId(R.id.lottie)).check(matches(isDisplayed()))
    }
}