package com.PAYDAY;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Selectbysalary extends JFrame {
	String down,up;
	JLabel jl1 = new JLabel("<html><font color=#33OOFF size='4'>��������:</font>");
	JTextField jt1 = new JTextField("",100);
	JLabel jl2 = new JLabel("<html><font color=#33OOFF size='4'>~</font>");
	JTextField jt2= new JTextField("",100);
	JButton jb1 = new JButton("<html><font color=#33OOFF size='5'>ȷ��</font>");
	JButton jb2 = new JButton("<html><font color=#33OOFF size='5'>ȡ��</font>");
	public Selectbysalary(){
		setTitle("��������");
		setLayout(null);
		setBounds(0,0,580,200);
		setSize(580, 200);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;	
		int y=screen.height;
		int xcenter=(x-580)/2;
	    int ycenter=(y-200)/2;
		setLocation(xcenter,ycenter);
		Container ct = getContentPane();
		/*�������λ�ü���С*/
		jl1.setBounds(100, 50, 100, 30);
		jt1.setBounds(180, 50, 100, 30);
		jl2.setBounds(295, 50, 100, 30);
		jt2.setBounds(320, 50, 100, 30);
		jb1.setBounds(130, 100, 100, 30);
		jb2.setBounds(330, 100, 100, 30);
		ct.add(jl1);
		ct.add(jt1);
		ct.add(jl2);
		ct.add(jt2);
		ct.add(jb1);
		ct.add(jb2);
		setVisible(true);
		jb1.addActionListener(new ActionListener(){					
			public  void actionPerformed(ActionEvent arg0) {
				down = jt1.getText();
				up = jt2.getText();
				new NoMain("select * from tb_user where salary >= '"+down+"' and salary <= '"+up+"'",100,400);
				dispose();
			}
		});
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}