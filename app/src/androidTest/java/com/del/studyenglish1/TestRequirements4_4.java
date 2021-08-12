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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestRequirements4_4 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Test UI for Requirement 4.4: User is able to view all vocabulary introduced by the app
     * in a dictionary section
     */
    @Test
    public void testRequirements4_4() {
        ViewInteraction cardView = onView(
                allOf(withId(R.id.card_view_dictionary),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                4),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_topics),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                7),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction listView = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button_c1), withText("C1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                6),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner_topics),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                7),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button_b1), withText("B1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinner_topics),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                7),
                        isDisplayed()));
        appCompatSpinner3.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_search), withText("search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                8),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("GARDENS"),
                        withParent(allOf(withId(R.id.spinner_topics),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView.check(matches(withText("GARDENS")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("GARDENS"),
                        withParent(allOf(withId(R.id.spinner_topics),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("GARDENS")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text_view_topic_name), withText("GARDENS"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView3.check(matches(withText("GARDENS")));

        ViewInteraction listView2 = onView(
                allOf(withId(R.id.list_view_vocab),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        listView2.check(matches(isDisplayed()));

        ViewInteraction listView3 = onView(
                allOf(withId(R.id.list_view_vocab),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        listView3.check(matches(isDisplayed()));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.button_a1), withText("A1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_search), withText("search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                8),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.text1), withText("CLOTHES 1"),
                        withParent(allOf(withId(R.id.spinner_topics),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView4.check(matches(withText("CLOTHES 1")));

        ViewInteraction textView5 = onView(
                allOf(withId(android.R.id.text1), withText("CLOTHES 1"),
                        withParent(allOf(withId(R.id.spinner_topics),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView5.check(matches(withText("CLOTHES 1")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.text_view_topic_name), withText("CLOTHES 1"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView6.check(matches(withText("CLOTHES 1")));

        ViewInteraction listView4 = onView(
                allOf(withId(R.id.list_view_vocab),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        listView4.check(matches(isDisplayed()));

        ViewInteraction listView5 = onView(
                allOf(withId(R.id.list_view_vocab),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        listView5.check(matches(isDisplayed()));
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
