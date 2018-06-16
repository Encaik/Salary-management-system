package com.PAYDAY;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Search extends JFrame {
	String searchname;
	JLabel jl1 = new JLabel("<html><font color=#33OOFF size='4'>姓名:</font>");
	JTextField jt1 = new JTextField("",100);
	JButton jb1 = new JButton("<html><font color=#33OOFF size='5'>确定</font>");
	JButton jb2 = new JButton("<html><font color=#33OOFF size='5'>取消</font>");
	public Search(){
		/*创建窗体*/
		setTitle("搜索员工");
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
		/*设置组件位置及大小*/
		jl1.setBounds(110, 50, 100, 30);
		jt1.setBounds(150, 50, 300, 30);
		jb1.setBounds(130, 100, 100, 30);
		jb2.setBounds(330, 100, 100, 30);
		ct.add(jl1);
		ct.add(jt1);
		ct.add(jb1);
		ct.add(jb2);
		setVisible(true);
		jb1.addActionListener(new ActionListener(){						//按钮事件监听
			public void actionPerformed(ActionEvent arg0) {
				try {
						Connect con = new Connect();
						@SuppressWarnings("unused")
						Statement sql = con.ConnectReturn().createStatement();
						searchname = jt1.getText();
						new NoMain("select * from tb_user where name = '"+searchname+"'",100,400);
					}catch(SQLException g){
						System.out.println(g.getMessage());
					}
				JOptionPane.showMessageDialog(null,"查找成功！","提示！", JOptionPane.OK_OPTION);
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