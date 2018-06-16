package com.PAYDAY;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

@SuppressWarnings("serial")
public class Main extends JFrame {
	String[] columnNames = {T("工号",5),T("姓名",5),T("性别",5),T("职位",5),T("绩效",5),T("工资",5)};
	Object tableValues[][] =new Object[80][6];
	Object tableValues2[][] =new Object[80][5];
	JTable tb;
	float salarysum = 0,isum = 0,jsum = 0,xsum = 0,ysum = 0,zsum = 0;
	public Main(){
		
	}
	public Main(String str) {
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
		setSize(x-100, y-100);
		setLocation(50,50);	
		/*创建菜单栏*/
		JMenu mu1 = new JMenu(T("文件",5));                    	//创建菜单项
		mb.add(mu1);									  		//添加菜单项到菜单栏
		JMenuItem jm1i1 = new JMenuItem(T("导入",5));          	//创建子菜单项
		mu1.add(jm1i1);											//添加子菜单项到菜单项
		JMenuItem jm1i2 = new JMenuItem(T("储存",5));
		mu1.add(jm1i2);	
		JMenuItem jm1i3 = new JMenuItem(T("注销",5));
		mu1.add(jm1i3);	
		JMenuItem jm1i4 = new JMenuItem(T("退出",5));
		mu1.add(jm1i4);	
		JMenu mu2 = new JMenu(T("操作",5));               
		mb.add(mu2);
		JMenuItem jm2i1 = new JMenuItem(T("新增",5));
		mu2.add(jm2i1);
		JMenuItem jm2i2 = new JMenuItem(T("删除",5));
		mu2.add(jm2i2);
		JMenuItem jm2i3 = new JMenuItem(T("搜索",5));
		mu2.add(jm2i3);
		JMenuItem jm2i4 = new JMenuItem(T("刷新",5));
		mu2.add(jm2i4);
		JMenu mu5 = new JMenu(T("分类",5));
		mb.add(mu5);
		JMenu jm5i1 = new JMenu(T("按性别",5));
		mu5.add(jm5i1);
		JMenuItem jm5i1j1 = new JMenuItem(T("男性",5));
		jm5i1.add(jm5i1j1);
		JMenuItem jm5i1j2 = new JMenuItem(T("女性",5));
		jm5i1.add(jm5i1j2);
		JMenuItem jm5i2 = new JMenuItem(T("按工资",5));
		mu5.add(jm5i2);
		JMenu jm5i3 = new JMenu(T("按职位",5));
		mu5.add(jm5i3);
		JMenuItem jm5i3j1 = new JMenuItem(T("经理",5));          
		jm5i3.add(jm5i3j1);																			
		JMenuItem jm5i3j2 = new JMenuItem(T("普通职工",5));
		jm5i3.add(jm5i3j2);	
		JMenuItem jm5i3j3 = new JMenuItem(T("销售人员",5));
		jm5i3.add(jm5i3j3);	
		JMenuItem jm5i3j4 = new JMenuItem(T("临时工",5));
		jm5i3.add(jm5i3j4);	
		JMenu mu6 = new JMenu(T("排序",5));
		mb.add(mu6);
		JMenuItem jm6i1 = new JMenuItem(T("按工资由高到低",5));
		mu6.add(jm6i1);
		JMenuItem jm6i2 = new JMenuItem(T("按工资由低到高",5));
		mu6.add(jm6i2);	
		JMenu mu7 = new JMenu(T("统计",5));
		mb.add(mu7);
		JMenuItem jm7i1 = new JMenuItem(T("发放工资合计",5));
		mu7.add(jm7i1);
		JMenuItem jm7i2 = new JMenuItem(T("按各职位工资占比",5));
		mu7.add(jm7i2);	
		/*菜单栏事件监听*/
		jm1i1.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				try{
					BufferedReader in = new BufferedReader(new FileReader("D:\\daoru.txt"));
					String line;  //一行数据
					int row=0;//逐行读取，并将每个数组放入到数组中
					while((line = in.readLine()) != null){
						String[] temp = line.split("\t"); 
						for(int j=0;j<temp.length;j++){
							tableValues2[row][j] = temp[j];
							}
						row++;
					  }
					  in.close();
					  //显示读取出的数组
					  for(int i=0;i<5;i++){
							  if(tableValues2[i][3].equals("经理")){
								  tableValues2[i][4] = 1;
								}else{
									tableValues2[i][4] = 2;
								}
						Connect con = new Connect();
						Statement sql = con.ConnectReturn().createStatement();
						String insert = "insert into tb_user (name,sex,born,job,JButton) values ('"+tableValues2[i][0]+"','"+tableValues2[i][1]+"','"+tableValues2[i][2]+"','"+tableValues2[i][3]+"','"+tableValues2[i][4]+"')";
						sql.executeUpdate(insert);
					  } 
				}catch(IOException | SQLException e){
					
				}
				JOptionPane.showMessageDialog(null,"导入成功!","提示！", JOptionPane.OK_OPTION);
			}
		});
		jm1i2.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				try{
					File file = new File("D:\\daochu.txt");  //存放数组数据的文件
					FileWriter out = new FileWriter(file);  //文件写入流
					  //将数组中的数据写入到文件中。每行各数据之间TAB间隔
					for(int i=0;tableValues[i][1]!=null;i++){
					  for(int j=0;j<6;j++){
						  out.write(tableValues[i][j]+"\t");
					  }
					  out.write("\r\n");
					}
					 out.close();
				}catch(IOException e){
					
				}
				JOptionPane.showMessageDialog(null,"保存成功!","提示！", JOptionPane.OK_OPTION);
			}
		});
		jm1i3.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				new Login();
				dispose();	
			}
		});	
		jm1i4.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				dispose();	
			}
		});	
		jm2i1.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				new New();		
			}
		});	
		jm2i2.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				new Del();
			}
		});	
		jm2i3.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				new Search();		 
			}
		});	
		jm2i4.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user");
			}
		});	
		jm5i1j1.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user where sex = '男'");
			}
		});	
		jm5i1j2.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user where sex = '女'");
			}
		});	
		jm5i2.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				new Selectbysalary();
			}
		});	
		jm5i3j1.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user where job = '经理'");
			}
		});	
		jm5i3j2.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user where job = '普通职工'");
			}
		});	
		jm5i3j3.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user where job = '销售人员'");
			}
		});	
		jm5i3j4.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user where job = '临时工'");
			}
		});	
		jm6i1.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user order by salary desc");
			}
		});	
		jm6i2.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				table();
				table("select * from tb_user order by salary");
			}
		});	
		jm7i1.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				try {
					Connect con = new Connect();
					Statement sql = con.ConnectReturn().createStatement();
					ResultSet res = null;
					res = sql.executeQuery("select * from tb_user");		
					int i = 0;
					while(res.next()){
						tableValues[i][5] = Float.parseFloat(res.getString("salary"));
						salarysum = salarysum + (float)tableValues[i][5];
						i++;
					} 
					JOptionPane.showMessageDialog(null,"工资合计发放："+salarysum+"元", "工资合计",JOptionPane.PLAIN_MESSAGE); 
					}catch (SQLException e) {
						e.printStackTrace();
					}
			}
		});	
		jm7i2.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent arg0){
				try {
					Connect con = new Connect();
					Statement sql = con.ConnectReturn().createStatement();
					ResultSet res = null;
					res = sql.executeQuery("select * from tb_user");		
					int z = 0;
					while(res.next()){
						tableValues[z][5] = Float.parseFloat(res.getString("salary"));
						zsum = zsum + (float)tableValues[z][5];
						z++;
					}
					res = sql.executeQuery("select * from tb_user where job = '经理'");		
					int i = 0;
					while(res.next()){
						tableValues[i][5] = Float.parseFloat(res.getString("salary"));
						isum = isum + (float)tableValues[i][5];
						i++;
					} 
					res = sql.executeQuery("select * from tb_user where job = '销售人员'");		
					int j = 0;
					while(res.next()){
						tableValues[j][5] = Float.parseFloat(res.getString("salary"));
						jsum = jsum + (float)tableValues[j][5];
						j++;
					}
					res = sql.executeQuery("select * from tb_user where job = '普通职工'");
					int x = 0;
					while(res.next()){
						tableValues[x][5] = Float.parseFloat(res.getString("salary"));
						xsum = xsum + (float)tableValues[x][5];
						x++;
					}
					res = sql.executeQuery("select * from tb_user where job = '临时工'");		
					int y = 0;
					while(res.next()){
						tableValues[y][5] = Float.parseFloat(res.getString("salary"));
						ysum = ysum + (float)tableValues[y][5];
						y++;
					}
					JOptionPane.showMessageDialog(null,"各职位工资占比：\r\n经理："+isum/zsum+"\r\n销售人员："+jsum/zsum+"\r\n普通职工："+xsum/zsum+"\r\n临时工："+ysum/zsum+"\r\n", "各职位工资占比",JOptionPane.PLAIN_MESSAGE); 
					}catch (SQLException e) {
						e.printStackTrace();
					}
			}
		});	
		table(str);
	}
	public void table(){
		for(int i = 0;i<80;i++){
			tableValues[i][0] = null;
			tableValues[i][1] = null;
			tableValues[i][2] = null;
			tableValues[i][3] = null;
			tableValues[i][4] = null;
			tableValues[i][5] = null;
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
		sp.setBounds(0, 0, x-100, y-100);
	}
	public void table(String str){
		try {
			Connect con = new Connect();
			Statement sql = con.ConnectReturn().createStatement();
			ResultSet res = null;
			res = sql.executeQuery(str);		
			int i = 0;
			float salary = 0;
			while(res.next()){
				tableValues[i][0] = res.getString("id");
				tableValues[i][1] = res.getString("name");
				tableValues[i][2] = res.getString("sex");
				tableValues[i][3] = res.getString("job");
				tableValues[i][4] = res.getString("dowork");
				tableValues[i][5] = Float.parseFloat(res.getString("salary"));
				salary += (float)tableValues[i][5];
				i++;
			}
			tableValues[i][4] = "合计";
			tableValues[i][5] = String.valueOf(salary);
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
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String T(String text,int a){
		return "<html><font color=#33OOFF size='"+a+"'>"+text+"</font>";	
	}
}