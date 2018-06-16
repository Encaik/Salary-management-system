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
public class New extends JFrame  {
	String Newname,Newpwd,Newsex,Newborn,Newjob;
	int NewJbutton;
	JLabel jl1 = new JLabel("<html><font color=#33OOFF size='4'>姓名:</font>");
	JLabel jl2 = new JLabel("<html><font color=#33OOFF size='4'>性别:</font>");
	JLabel jl3 = new JLabel("<html><font color=#33OOFF size='4'>生日:</font>");
	JLabel jl4 = new JLabel("<html><font color=#33OOFF size='4'>职位:</font>");
	JTextField jt1 = new JTextField("",100);
	JTextField jt2 = new JTextField("",100);
	JTextField jt3 = new JTextField("",100);
	JTextField jt4 = new JTextField("",100);
	JButton jb1 = new JButton("<html><font color=#33OOFF size='5'>确定</font>");
	JButton jb2 = new JButton("<html><font color=#33OOFF size='5'>取消</font>");
	public New(){
		/*创建窗体*/
		setTitle("新增员工");
		setLayout(null);
		setBounds(0,0,580,400);
		setSize(580, 400);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;	
		int y=screen.height;
		int xcenter=(x-580)/2;
	    int ycenter=(y-400)/2;
		setLocation(xcenter,ycenter);
		Container ct = getContentPane();
		/*设置组件位置及大小*/
		jl1.setBounds(110, 50, 100, 30);
		jt1.setBounds(150, 50, 300, 30);
		jl2.setBounds(110, 100, 100, 30);
		jt2.setBounds(150, 100, 300, 30);
		jl3.setBounds(110, 150, 100, 30);
		jt3.setBounds(150, 150, 300, 30);
		jl4.setBounds(110, 200, 100, 30);
		jt4.setBounds(150, 200, 300, 30);
		jb1.setBounds(130, 280, 100, 30);
		jb2.setBounds(330, 280, 100, 30);
		ct.add(jl1);
		ct.add(jt1);
		ct.add(jl2);
		ct.add(jt2);
		ct.add(jl3);
		ct.add(jt3);
		ct.add(jl4);
		ct.add(jt4);
		ct.add(jb1);
		ct.add(jb2);
		setVisible(true);
		jb1.addActionListener(new ActionListener(){						//按钮事件监听
			public void actionPerformed(ActionEvent arg0) {
				try {
						Connect con = new Connect();
						Statement sql = con.ConnectReturn().createStatement();
						Newname = jt1.getText();
						Newsex = jt2.getText();
						Newborn = jt3.getText();
						Newjob = jt4.getText();
						if(Newjob.equals("经理")){
							NewJbutton = 1;
						}else{
							NewJbutton = 2;
						}
						String insert = "insert into tb_user (name,sex,born,job,JButton) values ('"+Newname+"','"+Newsex+"','"+Newborn+"','"+Newjob+"','"+NewJbutton+"')";
						sql.executeUpdate(insert);
						Main ma = new Main();
						ma.table();
						ma.table("select * from tb_user");
					}catch(SQLException g){
						System.out.println(g.getMessage());
					}
				JOptionPane.showMessageDialog(null,"添加成功！","提示！", JOptionPane.OK_OPTION);
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
