package com.sample.propertyreader.home;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sample.propertyreader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSubstring;

/**
 * Created by Artur Kasprzak on 30.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

	@Rule
	public ActivityTestRule<HomeActivity> mActivityRule =
			new ActivityTestRule<>(HomeActivity.class);

	@Test
	public void displayAveragePrice() {
		onView(withId(R.id.tvAveragePrice)).check(matches(isDisplayed()));
		onView(withId(R.id.tvAveragePrice)).check(matches(withSubstring("The average")));
	}

	@Test
	public void displayDiffInAveragePrice() {
		onView(withId(R.id.tvDiffAveragePrice)).check(matches(isDisplayed()));
		onView(withId(R.id.tvDiffAveragePrice)).check(matches(withSubstring("The difference")));
	}

	@Test
	public void clickShowList() {
		onView(withId(R.id.btnShowList)).perform(click());
		onView(withId(R.id.rvList)).check(matches(isDisplayed()));
	}
}
