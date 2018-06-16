package com.PAYDAY;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

@SuppressWarnings("serial")
public class NoMain extends JFrame {
	String[] columnNames = {T("工号",5),T("姓名",5),T("性别",5),T("职位",5),T("绩效",5),T("工资",5)};
	Object tableValues[][] =new Object[80][6];
	Object tableValues2[][] =new Object[80][5];
	JTable tb;
	float salarysum = 0,isum = 0,jsum = 0,xsum = 0,ysum = 0,zsum = 0;
	public NoMain(String str,int i,int j) {
		/*创建窗体*/
		setTitle("雇员工资支付系统");
		setLayout(null);
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		setVisible(true);		
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;	
		int y=screen.height;
		setSize(x-i, y-j);
		setLocation(i/2,j/2);	
		table(str);
	}
	public void table(String str){
		try {
			Connect con = new Connect();
			Statement sql = con.ConnectReturn().createStatement();
			ResultSet res = null;
			res = sql.executeQuery(str);		
			int i = 0;
			while(res.next()){
				tableValues[i][0] = res.getString("id");
				tableValues[i][1] = res.getString("name");
				tableValues[i][2] = res.getString("sex");
				tableValues[i][3] = res.getString("job");
				tableValues[i][4] = res.getString("dowork");
				tableValues[i][5] = res.getString("salary");
				i++;
			}
			JTable tb = new JTable(tableValues,columnNames);
			tb.setAutoCreateRowSorter(true);
			tb.setRowHeight(30);
			tb.setFont(new Font("Menu.font",Font.PLAIN,20));
			JScrollPane sp = new JScrollPane(tb);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
			sp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			getContentPane().add(sp);
			Toolkit kit=Toolkit.getDefaultToolkit();
			Dimension screen=kit.getScreenSize();
			int x=screen.width;	
			int y=screen.height;
			sp.setBounds(0, 0, x-117, y-100);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String T(String text,int a){
		return "<html><font color=#33OOFF size='"+a+"'>"+text+"</font>";	
	}
}