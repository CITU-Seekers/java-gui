package com.codechum.awt.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;  

public class ImageCaptionGeneratorCanvas extends Canvas {    
  public ImageCaptionGeneratorCanvas() {       
    setSize(1920, 800);    
  }    

  public void paint(Graphics g) {
    BufferedImage img = null;

    try {
      img = ImageIO.read(new File("src\\main\\java\\com\\codechum\\Images\\cody.png"));
      g.drawImage(img, 0, 0, null);  
    } catch (IOException ex) {  
      ex.printStackTrace();  
    } 
  }

  public void addCaption(String caption){
    Graphics g = this.getGraphics();

    g.setColor(Color.red);

    // set Font
    g.setFont(new Font("Bold", 1, 20));

    // draw a string
    g.drawString(caption, 50, 50);
  }
}