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
	
	private String[] item1={"����","ӣľ����"};
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
		init();//1.�����ʼ��
		addComponent();//2.������
		addImage();
		addAction();
	}

	private void addAction() {
		jcb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				//��ȡѡ���ͼƬ������
				PicCanvas.picName=(String) jcb.getSelectedItem();
				PicCanvas.picNum=jcb.getSelectedIndex();
				//������Ϸ��
				clearinfo.setSelected(true);
				
				//������Ϸ״̬��
				PicCanvas.stepNum=0;
				name.setText("ͼƬ���ƣ�"+PicCanvas.picName);
				step.setText("������ "+PicCanvas.stepNum);
				//����Ԥ����
				pp.repaint();
				//����ƴͼ��
				pc.reLoadPic();
			}
		});
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.���ƶ��Ĳ�������
				PicCanvas.stepNum=0;
				step.setText("������ "+PicCanvas.stepNum);
				//2.��Ϸ����ƴͼ���д���
				pc.start();
			}
		});
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PicCanvas.stepNum=0;
				step.setText("������ "+PicCanvas.stepNum);
				new PicCanvas();
			}
		});
	}

	private void addImage() {  //���ͼƬ��ƴͼ����Ԥ������
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		pc = new PicCanvas();
		pc.setBorder(new TitledBorder("ƴͼ��"));
		pp = new PicPreview();
		pp.setBorder(new TitledBorder("Ԥ����"));
		
		panel.add(pc,BorderLayout.WEST);
		panel.add(pp, BorderLayout.EAST);
		
		this.add(panel,BorderLayout.CENTER);
	}

	private void addComponent() {  //����������Ϸ������Ϸ״̬��
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		//��Ϸ��
		JPanel leftpanel=new JPanel();
		panel.add(leftpanel, BorderLayout.WEST);
		leftpanel.setBorder(new TitledBorder("��Ϸ��"));//�����С������ӱ߿򣬼��߿����
		leftpanel.setBackground(Color.pink);
		
		     //��ť��
		ButtonGroup butg=new ButtonGroup();
		numinfo = new JRadioButton("������ʾ",false);
		numinfo.setBackground(Color.pink);
		clearinfo = new JRadioButton("ȡ����ʾ",true);
		clearinfo.setBackground(Color.pink);
		butg.add(numinfo);
		butg.add(clearinfo);
		      //����ѡ�򣬿�ʼ��ť
	    jcb = new JComboBox<String>(item1);
		start = new JButton("start");
		end = new JButton("end ");
		     //���
		leftpanel.add(numinfo);
		leftpanel.add(clearinfo);
		leftpanel.add(new JLabel("     ѡ��ͼƬ��"));
		leftpanel.add(jcb);
		leftpanel.add(start);
		leftpanel.add(end);
		
		//��Ϸ״̬��
		JPanel rightpanel=new JPanel();
		panel.add(rightpanel, BorderLayout.EAST);
		rightpanel.setBorder(new TitledBorder("��Ϸ״̬"));
		rightpanel.setBackground(Color.pink);
		      //���������Ĳ���
		rightpanel.setLayout(new GridLayout(1, 2));
      	name = new JTextField("��Ϸ���ƣ�����");
		name.setEditable(false);
		step = new JTextField("������ 0");
		step.setEditable(false);
		
		rightpanel.add(name);
		rightpanel.add(step);
		
		this.add(panel,BorderLayout.NORTH);//����������λ��
	}

	private void init() {
		this.setTitle("ƴͼ��Ϸ");
		this.setBounds(150, 15, 1000, 680);
		this.setResizable(false);//�̶����ڴ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
