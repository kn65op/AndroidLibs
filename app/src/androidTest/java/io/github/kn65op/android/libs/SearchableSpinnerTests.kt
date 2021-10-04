package io.github.kn65op.android.libs

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.kn65op.android.libs.ui.TestsActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchableSpinnerTests {

    @get:Rule
    val activityRule: ActivityScenarioRule<TestsActivity> =
        ActivityScenarioRule(TestsActivity::class.java)

    fun goToDialogs() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_dialog))
        Thread.sleep(1000) //??????? don't work without it
    }

    @Test
    fun selectFromSearchableSpinner() {
        goToDialogs()
        val entry = "4: last entry"
        val entryT = "last entry"
        onView(withId(R.id.searchableSpinner)).perform(click())
        onView(withText(entry)).perform(click())
        onView(withId(R.id.searchable_spinner_selected_value)).check(matches(withText(entryT)))
    }

    @Test
    fun selectFromSearchableSpinner_whenSearching() {
        goToDialogs()
        val entry = "17: Third cat"
        val entryT = "Third cat"
        onView(withId(R.id.searchableSpinner)).perform(click())
        onView(withId(R.id.searchable_selection_search_field)).perform(typeText("cat"))
        onView(withText(entry)).perform(click())
        onView(withId(R.id.searchable_spinner_selected_value)).check(matches(withText(entryT)))
    }

    @Test
    fun selectionByCodeShouldCallCallback() {
        goToDialogs()
        onView(withId(R.id.searchable_selection_set_entry)).perform(click())
        val expectedEntry = "First sentry"
        onView(withId(R.id.searchable_spinner_selected_value)).check(matches(withText(expectedEntry)))
    }

    @Test
    fun firstSelectionShouldNotCallCallback() {
        goToDialogs()
        val expectedEntry = ""
        onView(withId(R.id.searchable_spinner_selected_value)).check(matches(withText(expectedEntry)))
    }

    @Test
    fun invalidSelectionShouldNotCallCallback() {
        goToDialogs()
        onView(withId(R.id.searchable_selection_set_entry)).perform(click())
        onView(withId(R.id.searchable_spinner_invalid_entry)).perform(click())
        val expectedEntry = "First sentry"
        onView(withId(R.id.searchable_spinner_selected_value)).check(matches(withText(expectedEntry)))
    }
}
