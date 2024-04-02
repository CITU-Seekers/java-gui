package com.codechum;

import com.codechum.awt.image.ImageInCanvas;
import com.codechum.awt.image.ImageInCanvasMyCanvas;
import static org.testng.Assert.*;

import java.awt.*;
import org.testng.annotations.*;

import mockit.Tested;
import mockit.Injectable;
import mockit.Verifications;
import java.awt.image.BufferedImage;

public class ImageInCanvasTest {
    @Tested ImageInCanvas codeChumActivity;
    @Tested ImageInCanvasMyCanvas canvas;
    @Injectable Graphics g;
    
    // Description: Should override frame's paint method of MyCanvas class to display an image.
    @Test
    public void shouldOverridePaintMethodOfCanvasClass() {
        canvas.paint(g);
        
        new Verifications(){{
            g.drawImage(withInstanceOf(BufferedImage.class), anyInt, anyInt, null);
        }};
    }
    
    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveCanvasMain() {
        Canvas mainCanvas = (Canvas) TestUtils.getChildNamed(codeChumActivity, "mainCanvas");
        assertNotNull(mainCanvas, "No mainCanvas found");
    }
}
