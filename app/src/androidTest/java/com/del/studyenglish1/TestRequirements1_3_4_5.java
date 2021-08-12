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
public class TestRequirements1_3_4_5 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRequirements1_3_4_5() {
        ViewInteraction cardView = onView(
                allOf(withId(R.id.card_view_study),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                5),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button_a1), withText("BEGINNER: A1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button_grammar), withText("GRAMMAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.recycler_topic),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                3)))
                .atPosition(0);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text_view_explanation), withText("Adverbs of Frequency are adverbs of time that answer the question ''How frequently?'' or ''How often?''. They tell us how often something happens. Here are some examples:\n-daily, weekly, yearly\n-often, sometimes, rarely\nThe words in a) describe definite frequency.\nThe words in b) describe indefinite frequency\nWe separate them into two groups because they normally go in different positions in the sentence.\n\nAdverbs of definite frequency, typically go in END position.\n-Most companies pay taxes yearly.\n-The manager checks the toilets every hour.\nThe directors meet weekly to review progress.\nSometimes, usually for reasons of emphasis or style, some adverbs of definite frequency may go at the FRONT, for example:\n-Every day, more than five thousand people die on our roads.\n\nHere are some adverbs of indefinite frequency:\n100%\t always, constantly\n\tusually, normally\n\tfrequently, regularly\n\toften\n50%\tsometimes\n\toccasionally\n\trarely, infrequently\n\tseldom\n\thardly ever\n0%\tnever\n\nAdverbs of indefinite frequency mainly go in MID position in the sentence. They go before the main verb (except the main verb TO BE):\n-We usually go shopping on Saturday.\n-I have often done that.\n-She is always late."),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text_view_explanation), withText("Adverbs of Frequency are adverbs of time that answer the question ''How frequently?'' or ''How often?''. They tell us how often something happens. Here are some examples:\n-daily, weekly, yearly\n-often, sometimes, rarely\nThe words in a) describe definite frequency.\nThe words in b) describe indefinite frequency\nWe separate them into two groups because they normally go in different positions in the sentence.\n\nAdverbs of definite frequency, typically go in END position.\n-Most companies pay taxes yearly.\n-The manager checks the toilets every hour.\nThe directors meet weekly to review progress.\nSometimes, usually for reasons of emphasis or style, some adverbs of definite frequency may go at the FRONT, for example:\n-Every day, more than five thousand people die on our roads.\n\nHere are some adverbs of indefinite frequency:\n100%\t always, constantly\n\tusually, normally\n\tfrequently, regularly\n\toften\n50%\tsometimes\n\toccasionally\n\trarely, infrequently\n\tseldom\n\thardly ever\n0%\tnever\n\nAdverbs of indefinite frequency mainly go in MID position in the sentence. They go before the main verb (except the main verb TO BE):\n-We usually go shopping on Saturday.\n-I have often done that.\n-She is always late."),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView2.check(matches(withText("Adverbs of Frequency are adverbs of time that answer the question ''How frequently?'' or ''How often?''. They tell us how often something happens. Here are some examples: -daily, weekly, yearly -often, sometimes, rarely The words in a) describe definite frequency. The words in b) describe indefinite frequency We separate them into two groups because they normally go in different positions in the sentence.  Adverbs of definite frequency, typically go in END position. -Most companies pay taxes yearly. -The manager checks the toilets every hour. The directors meet weekly to review progress. Sometimes, usually for reasons of emphasis or style, some adverbs of definite frequency may go at the FRONT, for example: -Every day, more than five thousand people die on our roads.  Here are some adverbs of indefinite frequency: 100%\t always, constantly \tusually, normally \tfrequently, regularly \toften 50%\tsometimes \toccasionally \trarely, infrequently \tseldom \thardly ever 0%\tnever  Adverbs of indefinite frequency mainly go in MID position in the sentence. They go before the main verb (except the main verb TO BE): -We usually go shopping on Saturday. -I have often done that. -She is always late.")));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_activities), withText("ACTIVITIES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                3),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.button_activity1), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                4),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.message), withText("Number of questions: 8\nEstimated time to complete: 4 minutes"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.message), withText("Number of questions: 8\nEstimated time to complete: 4 minutes"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView4.check(matches(withText("Number of questions: 8 Estimated time to complete: 4 minutes")));

        ViewInteraction textView5 = onView(
                allOf(withId(android.R.id.message), withText("Number of questions: 8\nEstimated time to complete: 4 minutes"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView5.check(matches(withText("Number of questions: 8 Estimated time to complete: 4 minutes")));
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
