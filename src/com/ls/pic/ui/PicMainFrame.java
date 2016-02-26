package com.ls.pic.ui;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PicMainFrame extends JFrame{
	
	private String[] item1={"雏田","樱木花道"};
	private JComboBox<String> jcb;
	private PicCanvas pc;
	private PicPreview pp;
	private JButton start;
	private JRadioButton numinfo;
	private JRadioButton clearinfo;
	private JTextField name;
	public static JTextField step;
	private JButton end;

	public PicMainFrame(){
		init();//1.界面初始化
		addComponent();//2.添加组件
		addImage();
		addAction();
	}

	private void addAction() {
		jcb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				//获取选择的图片的名称
				PicCanvas.picName=(String) jcb.getSelectedItem();
				PicCanvas.picNum=jcb.getSelectedIndex();
				//更新游戏区
				clearinfo.setSelected(true);
				
				//更新游戏状态区
				PicCanvas.stepNum=0;
				name.setText("图片名称："+PicCanvas.picName);
				step.setText("步数： "+PicCanvas.stepNum);
				//更新预览区
				pp.repaint();
				//更新拼图区
				pc.reLoadPic();
			}
		});
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.已移动的步数清零
				PicCanvas.stepNum=0;
				step.setText("步数： "+PicCanvas.stepNum);
				//2.游戏区的拼图进行打乱
				pc.start();
			}
		});
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PicCanvas.stepNum=0;
				step.setText("步数： "+PicCanvas.stepNum);
				new PicCanvas();
			}
		});
	}

	private void addImage() {  //添加图片（拼图区，预览区）
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		pc = new PicCanvas();
		pc.setBorder(new TitledBorder("拼图区"));
		pp = new PicPreview();
		pp.setBorder(new TitledBorder("预览区"));
		
		panel.add(pc,BorderLayout.WEST);
		panel.add(pp, BorderLayout.EAST);
		
		this.add(panel,BorderLayout.CENTER);
	}

	private void addComponent() {  //添加组件（游戏区，游戏状态）
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		//游戏区
		JPanel leftpanel=new JPanel();
		panel.add(leftpanel, BorderLayout.WEST);
		leftpanel.setBorder(new TitledBorder("游戏区"));//给左侧小画板添加边框，及边框标题
		leftpanel.setBackground(Color.pink);
		
		     //按钮组
		ButtonGroup butg=new ButtonGroup();
		numinfo = new JRadioButton("数字提示",false);
		numinfo.setBackground(Color.pink);
		clearinfo = new JRadioButton("取消提示",true);
		clearinfo.setBackground(Color.pink);
		butg.add(numinfo);
		butg.add(clearinfo);
		      //下拉选框，开始按钮
	    jcb = new JComboBox<String>(item1);
		start = new JButton("start");
		end = new JButton("end ");
		     //添加
		leftpanel.add(numinfo);
		leftpanel.add(clearinfo);
		leftpanel.add(new JLabel("     选择图片："));
		leftpanel.add(jcb);
		leftpanel.add(start);
		leftpanel.add(end);
		
		//游戏状态区
		JPanel rightpanel=new JPanel();
		panel.add(rightpanel, BorderLayout.EAST);
		rightpanel.setBorder(new TitledBorder("游戏状态"));
		rightpanel.setBackground(Color.pink);
		      //设置右面板的布局
		rightpanel.setLayout(new GridLayout(1, 2));
      	name = new JTextField("游戏名称：雏田");
		name.setEditable(false);
		step = new JTextField("步数： 0");
		step.setEditable(false);
		
		rightpanel.add(name);
		rightpanel.add(step);
		
		this.add(panel,BorderLayout.NORTH);//设置主面板的位置
	}

	private void init() {
		this.setTitle("拼图游戏");
		this.setBounds(150, 15, 1000, 680);
		this.setResizable(false);//固定窗口大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
