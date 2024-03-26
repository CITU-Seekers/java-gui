package com.codechum;

import com.codechum.awt.graphicsClass.DrawingALine;
import java.awt.Graphics;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

public class DrawingALineTest {
    @Tested
    DrawingALine codeChumActivity;
    @Mocked Graphics g;

    @Test
    public void shouldOverridePaintMethod() {
        codeChumActivity.paint(g);

        new Verifications() {
            {
                g.drawLine(anyInt, anyInt, anyInt, anyInt);
            }
        };
    }
}
