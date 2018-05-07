package com.sample.propertyreader.list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sample.propertyreader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Artur Kasprzak on 30.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

	@Rule
	public ActivityTestRule<ListActivity> mActivityRule =
			new ActivityTestRule<>(ListActivity.class);

	@Test
	public void displayList() {
		onView(withId(R.id.rvList)).check(matches(ViewMatchers.isDisplayed()));
	}
}
