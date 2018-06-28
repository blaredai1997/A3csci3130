package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRUDTest {

    @Rule
    public ActivityTestRule<MainActivity> myActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    @Test
    // Create operation test
    public void A_createTest() throws InterruptedException {
        Thread.sleep(1000);

        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.businessNumber)).perform(typeText("123456789"));
        closeSoftKeyboard();
        onView(withId(R.id.name)).perform(typeText("Tim"));
        closeSoftKeyboard();
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fish Monger"));
        closeSoftKeyboard();
        onView(withId(R.id.address)).perform(typeText("234 Kaolin Street"));
        closeSoftKeyboard();
        onView(withId(R.id.province)).perform(typeText("AB"));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());
    }

    @Test
    // Read operation test
    public void B_readTest() throws InterruptedException {
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.businessNumber)).check(matches(withText("123456789")));
        onView(withId(R.id.name)).check(matches(withText("Tim")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("Fish Monger")));
        onView(withId(R.id.address)).check(matches(withText("234 Kaolin Street")));
        onView(withId(R.id.province)).check(matches(withText("AB")));
    }

    @Test
    // Update operation test
    public void C_updateTest() throws InterruptedException {
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.businessNumber)).perform(replaceText("666666666"));
        closeSoftKeyboard();
        onView(withId(R.id.name)).perform(replaceText("Kate"));
        closeSoftKeyboard();
        onView(withId(R.id.primaryBusiness)).perform(replaceText("Distributor"));
        closeSoftKeyboard();
        onView(withId(R.id.address)).perform(replaceText("987 Link Road"));
        closeSoftKeyboard();
        onView(withId(R.id.province)).perform(replaceText("ON"));
        closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());

        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.businessNumber)).check(matches(withText("666666666")));
        onView(withId(R.id.name)).check(matches(withText("Kate")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("Distributor")));
        onView(withId(R.id.address)).check(matches(withText("987 Link Road")));
        onView(withId(R.id.province)).check(matches(withText("ON")));
    }

    @Test
    // Delete operation test
    public void D_deleteTest() throws InterruptedException {
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }
}
