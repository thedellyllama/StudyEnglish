package com.del.studyenglish1;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestRequirements2_1_2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * UI tests for Requirements 2.1, 2.2, 3.5
     *
     * 2.1.	User can set/adjust goals based on the number of activities they want to complete
     * 2.2.	User can set/adjust goals to be weekly or daily
     * 3.5.	User can view/adjust learning goals
     */
    @Test
    public void testRequirements2_1_2() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("DAILY"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView.check(matches(withText("DAILY")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/5 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView2.check(matches(withText("0/5 activities completed")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/5 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView3.check(matches(withText("0/5 activities completed")));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_progress), withContentDescription("PROGRESS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("DAILY GOALS:"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView4.check(matches(withText("DAILY GOALS:")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/5 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView5.check(matches(withText("0/5 activities completed")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/5 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView6.check(matches(withText("0/5 activities completed")));

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.nav_goals), withContentDescription("GOALS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("DAILY GOALS:"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView7.check(matches(withText("DAILY GOALS:")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/5 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView8.check(matches(withText("0/5 activities completed")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/5 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView9.check(matches(withText("0/5 activities completed")));

        ViewInteraction button = onView(
                allOf(withId(R.id.button_1), withText("1"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.button_3), withText("3"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.button_5), withText("5"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.button6), withText("10"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.button_10), withText("10"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.button_daily), withText("DAILY"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction button7 = onView(
                allOf(withId(R.id.button_weekly), withText("WEEKLY"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction button8 = onView(
                allOf(withId(R.id.button_weekly), withText("WEEKLY"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button8.check(matches(isDisplayed()));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button_3), withText("3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                5),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button_weekly), withText("WEEKLY"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                11),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_update), withText("UPDATE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                12),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("WEEKLY GOALS:"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView10.check(matches(withText("WEEKLY GOALS:")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView11.check(matches(withText("0/3 activities completed")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView12.check(matches(withText("0/3 activities completed")));

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.nav_progress), withContentDescription("PROGRESS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("WEEKLY GOALS:"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView13.check(matches(withText("WEEKLY GOALS:")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView14.check(matches(withText("0/3 activities completed")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView15.check(matches(withText("0/3 activities completed")));

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.nav_home), withContentDescription("HOME"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("WEEKLY GOALS:"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView16.check(matches(withText("WEEKLY GOALS:")));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView17.check(matches(withText("0/3 activities completed")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView18.check(matches(withText("0/3 activities completed")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
