package com.del.studyenglish1;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
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

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GoalsUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void goalsUITest() {
        ViewInteraction cardView = onView(
                allOf(withId(R.id.card_view_goals),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                3),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("DAILY GOALS:"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView.check(matches(withText("DAILY GOALS:")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/5 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView2.check(matches(withText("0/5 activities completed")));

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

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text_view_daily_goals), withText("WEEKLY GOALS:"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView3.check(matches(withText("WEEKLY GOALS:")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView4.check(matches(withText("0/3 activities completed")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("0/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView5.check(matches(withText("0/3 activities completed")));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_page5), withContentDescription("STUDY"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                4),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.button_a1), withText("BEGINNER: A1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                4),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_grammar), withText("GRAMMAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                4),
                        isDisplayed()));
        materialButton5.perform(click());

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.recycler_topic),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                6)))
                .atPosition(4);
        relativeLayout.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.button_activities), withText("ACTIVITIES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                4),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.button_activity2), withText("2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                6),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.radio_button1), withText("He doesn't wake up early usually."),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.button_check), withText("CHECK ANSWER"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        4),
                                3),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.button_check), withText("Well done!\nFinish Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        4),
                                3),
                        isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.nav_page6), withContentDescription("GOALS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("1/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView6.check(matches(withText("1/3 activities completed")));

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.nav_page7), withContentDescription("PROFILE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("1/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView7.check(matches(withText("1/3 activities completed")));

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.nav_page4), withContentDescription("HOME"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.text_view_current_goals), withText("1/3 activities completed"),
                        withParent(allOf(withId(R.id.banner_layout),
                                withParent(withId(R.id.banner_outline)))),
                        isDisplayed()));
        textView8.check(matches(withText("1/3 activities completed")));
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
