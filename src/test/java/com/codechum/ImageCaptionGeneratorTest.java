package com.codechum;

import com.codechum.awt.image.ImageCaptionGenerator;
import com.codechum.awt.image.ImageCaptionGeneratorCanvas;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import org.testng.annotations.*;

import mockit.*;
import java.awt.image.BufferedImage;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

public class ImageCaptionGeneratorTest extends AssertJSwingTestngTestCase {
    ImageCaptionGenerator codeChumActivity;
    @Tested ImageCaptionGeneratorCanvas canvas;
    @Injectable Graphics g;
    
    TextField captionTextField;
    Button addCaptionButton;

    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        codeChumActivity = GuiActionRunner.execute(() -> new ImageCaptionGenerator());
    }
    
    @Test
    public void testPaintMethodOfCanvasClass() {
        canvas.paint(g);
        
        new Verifications(){{
            g.drawImage(withInstanceOf(BufferedImage.class), anyInt, anyInt, null);
        }};
    }
    
    @Test
    public void shouldHaveCanvasMain() {
        ImageCaptionGeneratorCanvas mainCanvas = (ImageCaptionGeneratorCanvas) TestUtils.getChildNamed(codeChumActivity, "mainCanvas");
        assertNotNull(mainCanvas, "No mainCanvas found");
    }
    
    @Test
    public void shouldHaveButtonAndTextField() {
        captionTextField = (TextField) TestUtils.getChildNamed(codeChumActivity, "captionTextField");
        addCaptionButton = (Button) TestUtils.getChildNamed(codeChumActivity, "addCaptionButton");
        
        assertNotNull(captionTextField, "No captionTextField found");
        assertNotNull(addCaptionButton, "No addCaptionButton found");
    }
    
    @Test
    public void shouldAddCaptionOnButtonClick() {
        addCaptionButton = (Button) TestUtils.getChildNamed(codeChumActivity, "addCaptionButton");
        captionTextField = (TextField) TestUtils.getChildNamed(codeChumActivity, "captionTextField");
        
        new MockUp<Canvas>(){
            @Mock
            public Graphics getGraphics(){
                return g;
            }
        };
        
        robot().click(captionTextField);
        robot().pressAndReleaseKeys(VK_S, VK_A, VK_M, VK_P, VK_L, VK_E);
        
        robot().click(addCaptionButton);
        robot().waitForIdle();
        
        new Verifications(){{
            g.drawString("sample", anyInt, anyInt);
        }};
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}