package com.PAYDAY;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JFrame {
	int a = 2;
	JLabel jl1 = new JLabel("<html><font color=#33OOFF size='6'>��Ա����֧��ϵͳ</font>");
	JLabel jl2 = new JLabel("<html><font color=#33OOFF size='5'>var1.0.0</font>");
	JLabel jl3 = new JLabel("<html><font color=#33OOFF size='4'>�˺ţ�</font>");
	JLabel jl4 = new JLabel("<html><font color=#33OOFF size='4'>���룺</font>");
	JLabel jl5 = new JLabel("<html><font color=#33OOFF size='4'>��ݣ�</font>");
	JTextField jt = new JTextField("",40);
	JPasswordField jp = new JPasswordField("",40);
	JRadioButton jr1 = new JRadioButton("<html><font color=#33OOFF size='4'>����</font>");
	JRadioButton jr2 = new JRadioButton("<html><font color=#33OOFF size='4'>ְ��</font>");
	JButton jb1 = new JButton("<html><font color=#33OOFF size='5'>��¼</font>");
	JButton jb2 = new JButton("<html><font color=#33OOFF size='5'>�˳�</font>");
	public Login(){
		/*��������*/
		setTitle("��Ա����֧��ϵͳ");
		setLayout(null);
		setBounds(0,0,580,400);
		setVisible(true);
		setSize(580, 400);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;	
		int y=screen.height;
		int xcenter=(x-580)/2;
	    int ycenter=(y-400)/2;
		setLocation(xcenter,ycenter);
		Container ct = getContentPane();
		/*�������λ�ü���С*/
		jl1.setBounds(190, 20, 200, 50);
		jl2.setBounds(240, 50, 100, 50);
		jl3.setBounds(110, 125, 50, 30);
		jt.setBounds(150, 125, 300, 30);
		jl4.setBounds(110, 175, 50, 30);
		jp.setBounds(150, 175, 300, 30);
		jl5.setBounds(110, 225, 70, 30);
		jr1.setBounds(200, 225, 100, 30);
		jr2.setBounds(300, 225, 120, 30);
		jb1.setBounds(130, 280, 100, 30);
		jb2.setBounds(330, 280, 100, 30);
		/*���õ�ѡ��ť��*/
		ButtonGroup bg = new ButtonGroup();
		bg.add(jr1);
		bg.add(jr2);
		jr2.setSelected(true);
		/*������������*/
		ct.add(jl1);
		ct.add(jl2);
		ct.add(jl3);
		ct.add(jt);
		ct.add(jl4);
		ct.add(jp);
		ct.add(jl5);
		ct.add(jr1);
		ct.add(jr2);
		ct.add(jb1);
		ct.add(jb2);
		jr1.addItemListener(new ItemListener() {						//��ѡ��ť�¼�����
			public void itemStateChanged(ItemEvent e) {
				a = 1;
			}
		});	
		jr2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				a = 2;
			}
		});
		jb1.addActionListener(new ActionListener(){						//��ť�¼�����
			public void actionPerformed(ActionEvent arg0) {
				confirm();	
			}
		});
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		//���ô��ڹرշ�ʽ
	}
	public void confirm(){	//��֤�û��Ƿ����
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			//����JDBC����
		}catch(ClassNotFoundException e){
			System.out.println("������������ʧ��!");
		}
		try{
			Connect con = new Connect();
			Statement sql = con.ConnectReturn().createStatement();
			String name=jt.getText().trim();
			@SuppressWarnings("deprecation")
			String pwd=jp.getText().trim();
			String queryMima=null;
			queryMima="select * from tb_user where name='"+name+"' and pwd='"+pwd+"' and JButton='"+a+"'";  			//��ѯ����
			ResultSet rs=sql.executeQuery(queryMima);
			if(rs.next()){
				if(a==1){ 
					new Main("select * from tb_user");
					dispose();
				}else{
					new NoMain("select * from tb_user where name = '"+name+"'",100,100);
					dispose();
				}
			}else{
				JOptionPane.showMessageDialog(null,T("���û�������",4)+"\n"+T("���������˺ţ������Լ�����Ƿ�ѡ����ȷ��",5),"��ʾ��", JOptionPane.YES_NO_OPTION);	
			}
			jt.setText("");
			jp.setText("");
		}catch(SQLException g){
			System.out.println(g.getMessage());
		}
	   }
	public String T(String text,int a){
		return "<html><font color=#33OOFF size='"+a+"'>"+text+"</font>";	
	}
	public static void main(String args[]){
		new Login(); 
	}
}
