package com.ls.pic.ui;

import javax.swing.Icon;
import javax.swing.JButton;

/*
 * 图片小方格
 */
public class Cell extends JButton {

	private int num;
	//带有图片的小方格（按钮）
	public Cell(Icon arg1) {
		super(arg1);
		this.setSize(160, 140);
		
		/*num=PicCanvas.picNum+3;
		this.setSize(480/num, 560/(num+1));*/
		
		/*if(PicCanvas.picName.equals("雏田")){
			this.setSize(160, 140);
		}
		if(PicCanvas.picName.equals("雏田鸣人")){
			this.setSize(120, 140);
		}*/
		
	}

	////带有图片和文字的小方格（按钮）
	public Cell(String arg0, Icon arg1) {
		super(arg0, arg1);
		this.setSize(160, 140);
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(CENTER);
	}

	public void move(String direction) {
		switch (direction) {
		case "down":
			this.setLocation(this.getBounds().x,this.getBounds().y+140);
			break;
		case "up":
			this.setLocation(this.getBounds().x,this.getBounds().y-140);
			break;
		case "right":
			this.setLocation(this.getBounds().x+160,this.getBounds().y);
			break;
		case "left":
			this.setLocation(this.getBounds().x-160,this.getBounds().y);
			break;
		default:
			break;
		}
	}
	
	

}
