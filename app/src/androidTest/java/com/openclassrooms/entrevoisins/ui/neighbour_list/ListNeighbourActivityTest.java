package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {

    private static final int ITEMS_COUNT = 12;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void neighbourProfileIsDisplayed() {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.list_neighbours), isDisplayed()));
        recyclerView.perform(click());

        ViewInteraction viewGroup = onView(allOf(withId(R.id.neighbourProfile), isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }

    @Test
    public void neighbourNameIsDisplayedOnProfile() {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.list_neighbours), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(allOf(withId(R.id.name), isDisplayed()));
        textView.check(matches(withText("Caroline")));
    }

    @Test
    public void deleteNeighbourIsEffective() {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.list_neighbours), isDisplayed()));
        recyclerView.check(withItemCount(ITEMS_COUNT));

        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        recyclerView.check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void favoritesTabIsEffective() {
        onView(ViewMatchers.withId(R.id.favoritesTab)).check(withItemCount(0));

        ViewInteraction recyclerView = onView(allOf(withId(R.id.list_neighbours), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction floatingActionButton = onView(allOf(withId(R.id.favoritesFab), isDisplayed()));
        floatingActionButton.perform(click());
        pressBack();
        ViewInteraction viewPager = onView(allOf(withId(R.id.main_content), isDisplayed()));
        viewPager.perform(swipeLeft());

        onView(ViewMatchers.withId(R.id.favoritesTab)).check(withItemCount(1));
    }
}