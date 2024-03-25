package com.codechum;

import com.codechum.TestUtils;
import com.codechum.awt.list.MovieCatalogue;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MovieCatalogueTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    List comedyList, horrorList, actionList;
    Button addActionButton, addComedyButton, addHorrorButton, removeButton;
    TextField movieTextField;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MovieCatalogue.class).start();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        robot().waitForIdle(); 

        comedyList = (List) TestUtils.findComponent("comedyList", true);
        horrorList = (List) TestUtils.findComponent("horrorList", true);
        actionList = (List) TestUtils.findComponent("actionList", true);
        addActionButton = (Button) TestUtils.findComponent("addActionButton", true);
        addComedyButton = (Button) TestUtils.findComponent("addComedyButton", true);
        addHorrorButton = (Button) TestUtils.findComponent("addHorrorButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        movieTextField = (TextField) TestUtils.findComponent("movieTextField", true);

        assertNotNull(comedyList, "No comedyList found.");
        assertNotNull(horrorList, "No horrorList found.");
        assertNotNull(actionList, "No actionList found.");
        assertNotNull(addActionButton, "No addActionButton found.");
        assertNotNull(addComedyButton, "No addComedyButton found.");
        assertNotNull(addHorrorButton, "No addHorrorButton found.");
        assertNotNull(removeButton, "No removeButton found.");
        assertNotNull(movieTextField, "No movieTextField found.");        
    }

    @Test
    public void shouldAddMovieToComedyList(){
        robot().waitForIdle();
        comedyList = (List) TestUtils.findComponent("comedyList", true);
        movieTextField = (TextField) TestUtils.findComponent("movieTextField", true);
        addComedyButton = (Button) TestUtils.findComponent("addComedyButton", true);
        robot().click(movieTextField);
        robot().enterText("The Hangover");
        robot().click(addComedyButton);
        assertEquals(comedyList.getItemCount(), 1);
        assertEquals(comedyList.getItem(0), "The Hangover");
    }

    @Test
    public void shouldAddMovieToHorrorList(){
        robot().waitForIdle();
        horrorList = (List) TestUtils.findComponent("horrorList", true);
        movieTextField = (TextField) TestUtils.findComponent("movieTextField", true);
        addHorrorButton = (Button) TestUtils.findComponent("addHorrorButton", true);
        robot().click(movieTextField);
        robot().enterText("The Conjuring");
        robot().click(addHorrorButton);
        assertEquals(horrorList.getItemCount(), 1);
        assertEquals(horrorList.getItem(0), "The Conjuring");
    }

    @Test
    public void shouldAddMovieToActionList(){
        robot().waitForIdle();
        actionList = (List) TestUtils.findComponent("actionList", true);
        movieTextField = (TextField) TestUtils.findComponent("movieTextField", true);
        addActionButton = (Button) TestUtils.findComponent("addActionButton", true);
        robot().click(movieTextField);
        robot().enterText("Avengers");
        robot().click(addActionButton);
        assertEquals(actionList.getItemCount(), 1);
        assertEquals(actionList.getItem(0), "Avengers");
    }

    @Test
    public void shouldRemoveMovieFromComedyList(){
        robot().waitForIdle();
        comedyList = (List) TestUtils.findComponent("comedyList", true);
        movieTextField = (TextField) TestUtils.findComponent("movieTextField", true);
        addComedyButton = (Button) TestUtils.findComponent("addComedyButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        robot().click(movieTextField);
        robot().enterText("The Hangover");
        robot().click(addComedyButton);
        robot().click(comedyList);
        comedyList.select(0);
        robot().click(removeButton);
        assertEquals(comedyList.getItemCount(), 0);
    }

    @Test
    public void shouldRemoveMovieFromHorrorList(){
        robot().waitForIdle();
        horrorList = (List) TestUtils.findComponent("horrorList", true);
        movieTextField = (TextField) TestUtils.findComponent("movieTextField", true);
        addHorrorButton = (Button) TestUtils.findComponent("addHorrorButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        robot().click(movieTextField);
        robot().enterText("The Conjuring");
        robot().click(addHorrorButton);
        robot().click(horrorList);
        horrorList.select(0);
        robot().click(removeButton);
        assertEquals(horrorList.getItemCount(), 0);
    }

    @Test
    public void shouldRemoveMovieFromActionList(){
        robot().waitForIdle();
        actionList = (List) TestUtils.findComponent("actionList", true);
        movieTextField = (TextField) TestUtils.findComponent("movieTextField", true);
        addActionButton = (Button) TestUtils.findComponent("addActionButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        robot().click(movieTextField);
        robot().enterText("Avengers");
        robot().click(addActionButton);
        robot().click(actionList);
        actionList.select(0);
        robot().click(removeButton);
        assertEquals(actionList.getItemCount(), 0);
    }
}

