package com.example.testleboncoin.activities

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testleboncoin.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private var decorView: View? = null

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testSampleRecyclerVisible() {
        onView(withId(R.id.recyclerview))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(decorView)
                )
            )
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testCaseForRecyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(decorView)
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun testCaseForRecyclerItemView() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(decorView)
                )
            )
            .check(
                matches(
                    withViewAtPosition(
                        1, Matchers.allOf(
                            ViewMatchers.withId(R.id.recyclerview), ViewMatchers.isDisplayed()
                        )
                    )
                )
            )
    }

    @Before
    fun loadDecorView() {
        activityRule.scenario.onActivity { activity: MainActivity ->
            decorView = activity.window.decorView
        }
    }

    fun withViewAtPosition(
        position: Int,
        itemMatcher: Matcher<View?>
    ): BoundedMatcher<View?, RecyclerView> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}
