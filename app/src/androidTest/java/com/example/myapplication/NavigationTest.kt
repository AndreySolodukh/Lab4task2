package com.example.myapplication

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class NavigationTest {

// ------------------- Simple walks ----------------------

    @Test
    fun testWalkFirstToThird() {
        launchActivity<MainActivity>()
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun testFromSecondToFirst() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testFromThirdToFirst() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testFromThirdToSecond() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    // ------------------- Press Back ----------------------

    @Test
    fun testBackFromThirdToFirst() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment3)).perform(pressBack())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment2)).perform(pressBack())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackStackSecondToFirst() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.fragment2)).perform(pressBack())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    // ------------------- About ---------------------

    @Test
    fun testAboutFromFirstViaBottomNav() {
        launchActivity<MainActivity>()
        openAboutViaBottomNav()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
    }

    @Test
    fun testAboutFromSecondViaDrawer() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        openAboutViaDrawer()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
    }

    @Test
    fun testAboutFromThirdViaOptions() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAboutViaOptions()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromAboutToFirst() {
        launchActivity<MainActivity>()
        openAboutViaBottomNav()
        onView(withId(R.id.activity_about)).perform(pressBack())
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromAboutToSecond() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        openAboutViaBottomNav()
        onView(withId(R.id.activity_about)).perform(pressBack())
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackFromAboutToThird() {
        launchActivity<MainActivity>()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        openAboutViaBottomNav()
        onView(withId(R.id.activity_about)).perform(pressBack())
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    // --------------- Config Change Event ---------------------

    @Test
    fun testConfigurationChange() {

        val activityScenario = launchActivity<MainActivity>()

        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).perform(click())
        activityScenario.recreate()
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).perform(click())
        activityScenario.recreate()
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }
}
