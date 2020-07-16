package com.example.deliverytest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.deliverytest.view.ListMenuActivity
import com.example.deliverytest.view.itemsadpt.ItemsListItemViewHolder
import com.example.deliverytest.viewaction.MyViewAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListMenuActivityTest {
    @get:Rule
    val rule = ActivityTestRule<ListMenuActivity>(ListMenuActivity::class.java)

    @Test
    fun shouldShowBtnItems_whenAddItemFromListToChart() {
        onView(withId(R.id.recyclerView1)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ItemsListItemViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.add_cart)
            )
        )
        onView(withId(R.id.btn_items)).check(matches(isDisplayed()))
    }
}