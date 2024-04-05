package com.codechum;

import com.codechum.awt.fonts.Fonts;
import java.awt.*;

import mockit.*;
import org.testng.annotations.Test;

public class FontsTest {
    @Tested Fonts codeChumActivity;
    @Injectable Graphics g;
    
    // Description: Should display a text "There's" in bold font with a font family of "Courier" and size of 20.
    @Test
    public void shouldDisplayThereCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Courier", Font.BOLD, 20));
            g.drawString("There's", anyInt, anyInt);
        }};
    }
    
    // Description: Should display a text "nothing" in italic font with a font family of "Default" and size of 20.
    @Test
    public void shouldDisplayNothingCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Default", Font.ITALIC, 20));
            g.drawString("nothing", anyInt, anyInt);
        }};
    }
    
    // Description: Should display a text "stronger" in bold italic font with a font family of "Monospaced" and size of 24.
    @Test
    public void shouldDisplayStrongerCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Monospaced", Font.BOLD | Font.ITALIC, 24));
            g.drawString("stronger", anyInt, anyInt);
        }};
    }
    
    // Description: Should display a text "than" in bold font with a font family of "Serif" and size of 20.
    @Test
    public void shouldDisplayThanCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Serif", Font.BOLD, 20));
            g.drawString("than", anyInt, anyInt);
        }};
    }
    
    // Description: Should display a text "FAMILY" in bold italic font with a font family of "Monospaced" and size of 24.
    @Test
    public void shouldDisplayFamilyCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Monospaced", Font.BOLD | Font.ITALIC, 24));
            g.drawString("FAMILY", anyInt, anyInt);
        }};
    }
}
