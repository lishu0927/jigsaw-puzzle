package com.ls.pic.ui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class PicPreview extends JPanel {
	

	
//	Image image;
	protected void paintComponent(Graphics g) {
		String imageName="images/"+PicCanvas.picName+".jpg";
		ImageIcon icon=new ImageIcon(imageName);
		Image image=icon.getImage();
		g.drawImage(image, 10, 20, 480,560,this);
	}
	
}
