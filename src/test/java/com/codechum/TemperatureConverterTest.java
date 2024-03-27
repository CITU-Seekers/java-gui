package com.codechum;

import com.codechum.awt.scrollBar.TemperatureConverter;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class TemperatureConverterTest extends AssertJSwingTestngTestCase {

    EmergencyAbortListener listener;
    
    Scrollbar temperatureRangeBar;
    Label celsiusTempLabel, fahrenheitTempLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TemperatureConverter.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        temperatureRangeBar = (Scrollbar) TestUtils.findComponent("temperatureRangeBar", true);
        celsiusTempLabel = (Label) TestUtils.findComponent("celsiusTempLabel", true);
        fahrenheitTempLabel = (Label) TestUtils.findComponent("fahrenheitTempLabel", true);
        
        assertNotNull(temperatureRangeBar, "No temperatureRangeBar found.");
        assertNotNull(celsiusTempLabel, "No celsiusTempLabel found.");
        assertNotNull(fahrenheitTempLabel, "No fahrenheitTempLabel found");
    }

    @Test
    public void shouldConvertTemperature() {
        robot().waitForIdle();
        
        temperatureRangeBar = (Scrollbar) TestUtils.findComponent("temperatureRangeBar", true);
        celsiusTempLabel = (Label) TestUtils.findComponent("celsiusTempLabel", true);
        fahrenheitTempLabel = (Label) TestUtils.findComponent("fahrenheitTempLabel", true);
        
        robot().click(temperatureRangeBar);
        robot().pressAndReleaseKeys(VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT);
        
        int temperature = temperatureRangeBar.getValue();
        double fahrenheit = Math.round((temperature * 1.8 + 32) * 100.0) / 100.0;

        // Check if celsusTempLabel contains the temperature number
        assertTrue(celsiusTempLabel.getText().contains(Integer.toString(temperature)), "celsiusTempLabel does not contain the temperature number.");
        // Check if fahrenheitTempLabel contains the fahrenheit number
        assertTrue(fahrenheitTempLabel.getText().contains(Double.toString(fahrenheit)), "fahrenheitTempLabel does not contain the fahrenheit number.");
    }
    
}
