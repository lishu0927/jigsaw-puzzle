package com.ls.pic.ui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PicCanvas extends JPanel {
	
	public static   String picName = "雏田";
	public static   int picNum = 0;
	protected static int stepNum=0;
	private ArrayList<Cell> cells;
	private int k;
	private ImageIcon icon;
	private Cell cell;
	private boolean hasaddMouseListenr=false;
	private Rectangle nullcell;

	public PicCanvas (){
		this.setLayout(null);//帧布局
		cells=new ArrayList<Cell>();
		for(int i=0;i<4;i++)
			for(int j=0;j<3;j++){
				k = i*3+j+1;
				if(k<=9){
					icon=new ImageIcon("images/"+picName+"_0"+k+".jpg");
				}else{
					icon=new ImageIcon("images/"+picName+"_"+k+".jpg");
				}
				cell = new Cell(icon);
				cells.add(cell);
				cell.setLocation(160*j+10,140*i+20);
				this.add(cell);
			}
		this.remove(11);
		nullcell = new Rectangle(10+320, 20+420, 160, 140);
	}
	
	/**
	 * 重新加载拼图区的图片
	 */
	public void reLoadPic(){
			for(int i=0;i<4;i++)
				for(int j=0;j<3;j++){
				k = i*3+j+1;
				if(k<=9){
					icon=new ImageIcon("images/"+picName+"_0"+k+".jpg");
				}else{
					icon=new ImageIcon("images/"+picName+"_"+k+".jpg");
				}
				cells.get(k-1).setIcon(icon);
	    }
	}
	
	/**
	 * 打乱小方格的位置
	 * 给每个小方格添加鼠标监听
	 * 
	 */
	public void start(){
		//2.给小方格添加鼠标监听
		if(!hasaddMouseListenr){
			for (int i = 0; i <11; i++) {
				cells.get(i).addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						Cell Pressedcell=(Cell) e.getSource();
						int PressedX=Pressedcell.getBounds().x;
						int PressedY=Pressedcell.getBounds().y;
						int nullX=nullcell.getBounds().x;
						int nullY=nullcell.getBounds().y;
						
						if(PressedX==nullX&&PressedY-nullY==140){
							Pressedcell.move("up");
						}else if (PressedX==nullX&&nullY-PressedY==140) {
							Pressedcell.move("down");
						}else if (PressedX-nullX==160&&PressedY==nullY) {
							Pressedcell.move("left");
						}else if (nullX-PressedX==160&&PressedY==nullY) {
							Pressedcell.move("right");
						}else {
							return ;
						}
						nullcell.setLocation(PressedX,PressedY);
						new PicCanvas().repaint();
						stepNum++;
						PicMainFrame.step.setText("步数： "+stepNum);
						if(isFinish()){
							JOptionPane.showMessageDialog(new PicCanvas(),
									      "拼图成功，游戏结束！！！\n 所用步数： "+stepNum);
							for (int j = 0; j < 11; j++) {
								cells.get(j).removeMouseListener(this);//移除鼠标监听
							}
							hasaddMouseListenr=false;
						}
					}
					private boolean isFinish() {
						for (int j = 0; j < 11; j++) {
							int x=cells.get(j).getBounds().x;
							int y=cells.get(j).getBounds().y;
							if(((x-10)/160+(y-20)/140*3)!=j){
								return false;//必须对每个小方格都进行判断
							}
						}
						return true;
					}
					
				});
			}
			hasaddMouseListenr=true;	
		}
		//1.打乱方格位置，小方格与空格的位置交换
		while (cells.get(0).getBounds().x<=170&&cells.get(0).getBounds().y<=160){
			int nullX=nullcell.getBounds().x;
			int nullY=nullcell.getBounds().y;
			//通过随机数，让空方格与周围方格互换位置
			int direction=(int) (Math.random()*4);
			switch (direction) {
			case 0:
				nullY-=140;
				cellMove(nullX,nullY,"down");
				break;
			case 1:
				nullY+=140;
				cellMove(nullX,nullY,"up");
				break;
			case 2:
				nullX-=160;
				cellMove(nullX,nullY,"right");
				break;
			case 3:
				nullX+=160;
				cellMove(nullX,nullY,"left");
				break;
			}
		}
	}

	private void cellMove(int nullX, int nullY, String direction) {
		for (int i = 0; i < 11; i++) {
			if(cells.get(i).getBounds().x==nullX&&cells.get(i).getBounds().y==nullY){
				cells.get(i).move(direction);
				nullcell.setLocation(nullX, nullY);
				break;
			}
		}
	}
}
