package com.sevban.composetutorialssevbanbayir.State_Compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class SaveableExampleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun onRecreation_stateIsRestored() {
        val restorationTester = StateRestorationTester(composeTestRule)

        restorationTester.setContent { SaveableExpandable() }
        composeTestRule.onNodeWithText("Sevban Bayir").performClick()
        composeTestRule.onNodeWithText("8:45").assertIsDisplayed()

        //Trigger a recreation
        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithText("8:45").assertIsDisplayed()

    }
}