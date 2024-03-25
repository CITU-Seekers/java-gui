package com.codechum;

import com.codechum.awt.fonts.Fonts;
import java.awt.*;

import mockit.*;
import org.testng.annotations.Test;

public class FontsTest {
    @Tested Fonts codeChumActivity;
    @Injectable Graphics g;
    
    @Test
    public void shouldDisplayThereCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Courier", Font.BOLD, 20));
            g.drawString("There's", anyInt, anyInt);
        }};
    }
    
    @Test
    public void shouldDisplayNothingCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Default", Font.ITALIC, 20));
            g.drawString("nothing", anyInt, anyInt);
        }};
    }
    
    @Test
    public void shouldDisplayStrongerCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Monospaced", Font.BOLD | Font.ITALIC, 24));
            g.drawString("stronger", anyInt, anyInt);
        }};
    }
    
    @Test
    public void shouldDisplayThanCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Serif", Font.BOLD, 20));
            g.drawString("than", anyInt, anyInt);
        }};
    }
    
    @Test
    public void shouldDisplayFamilyCorrectly() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Monospaced", Font.BOLD | Font.ITALIC, 24));
            g.drawString("FAMILY", anyInt, anyInt);
        }};
    }
}
