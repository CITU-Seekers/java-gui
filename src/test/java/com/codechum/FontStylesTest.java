package com.codechum;

import com.codechum.awt.fonts.FontStyles;
import java.awt.*;

import mockit.*;
import org.testng.annotations.Test;

public class FontStylesTest {
    @Tested FontStyles codeChumActivity;
    @Injectable Graphics g;
    
    @Test
    public void shouldDrawBoldText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setFont(new Font ("Default", Font.BOLD, 20));
            g.drawString("Bold Text", anyInt, anyInt);
        }};
    }
    
    @Test
    public void shouldDrawItalicText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setFont(new Font ("Default", Font.ITALIC, 20));
            g.drawString("Italic Text", anyInt, anyInt); 
        }};
    }
    
    @Test
    public void shouldDrawBoldItalicText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setFont(new Font ("Default", Font.BOLD | Font.ITALIC, 20));
            g.drawString("Bold Italic Text", anyInt, anyInt); 
        }};
    }
}
