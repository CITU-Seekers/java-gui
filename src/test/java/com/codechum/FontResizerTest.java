package com.codechum;

import com.codechum.awt.fonts.FontResizer;
import static org.testng.Assert.*;
import java.awt.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import org.testng.annotations.Test;

public class FontResizerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Label textLabel;
    Button increaseButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FontResizer.class).start();
    }

    @Test
    public void shouldHaveAllComponents() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        increaseButton = (Button) TestUtils.findComponent("increaseButton", true);

        assertNotNull(textLabel);
        assertNotNull(increaseButton);
    }

    @Test
    public void shouldIncreaseFontSize() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        increaseButton = (Button) TestUtils.findComponent("increaseButton", true);

        int initialFontSize = textLabel.getFont().getSize();
        robot().click(increaseButton);
        int finalFontSize = textLabel.getFont().getSize();

        assertTrue(finalFontSize > initialFontSize);
    }

}
