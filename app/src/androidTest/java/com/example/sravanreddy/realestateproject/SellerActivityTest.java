package com.example.sravanreddy.realestateproject;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.sravanreddy.realestateproject.view.activity.SellerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @Package com.example.sravanreddy.realestateproject
 * @FileName SellerActivityTest
 * @Date 5/13/18, 10:05 PM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */

@RunWith(AndroidJUnit4.class)
public class SellerActivityTest {

    @Rule
    public ActivityTestRule<SellerActivity> activityActivityTestRule = new ActivityTestRule<>(SellerActivity.class);

    @Test
    public void propertyRecyclerViewClick() throws Exception{
        onView(withId(R.id.propertyList_recycler_seller))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


    }
}
