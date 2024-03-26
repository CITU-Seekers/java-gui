package com.codechum.awt.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;  

public class ImageInCanvasMyCanvas extends Canvas {    
  public ImageInCanvasMyCanvas() {       
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
}