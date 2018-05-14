package com.example.sravanreddy.realestateproject;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * @Package com.example.sravanreddy.realestateproject
 * @FileName LoginActivityTest
 * @Date 5/13/18, 9:35 PM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    private String TEST_PAGE_NAME = "com.example.sravanreddy.realestateproject.view.activity";

    //this is before
    @Rule
    public IntentsTestRule<LoginActivity> mLoginActivityIntentTestRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void clickSellerButton() throws Exception{
        onView(withId(R.id.button_seller_login))
                .perform(click());

        intending(allOf(
                hasComponent(hasShortClassName(".SellerActivity")),
                toPackage(TEST_PAGE_NAME)));
    }

    @Test
    public void clickBuyerButton() throws Exception{
        onView(withId(R.id.button_buyer_login))
                .perform(click());

        intending(allOf(
                hasComponent(hasShortClassName(".BuyerActivity")),
                toPackage(TEST_PAGE_NAME)));
    }

    @Test
    public void clickOcrButton() throws Exception{
        onView(withId(R.id.btn_who))
                .perform(click());

        intending(allOf(
                hasComponent(hasShortClassName(".OcrActivity")),
                toPackage(TEST_PAGE_NAME)));
    }
}
