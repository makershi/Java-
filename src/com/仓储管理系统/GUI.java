/**
 * 
 */
package com.仓储管理系统;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * @author ThinkPad
 *
 */

public class GUI implements ActionListener{
	//GUI是主页
	private JComboBox		jc_id;//选择身份（供货商/消费者）
    private Button     		button_signUp;//注册
    private Button     		button_signIn;//登录

    private JFrame     frame_index;
	
    private String id;
    
    private CustomerSignUp customerSignUp;
    private ManufacturerSignUp manufacturerSighUp;
    private CustomerSignIn customerSignIn;
    private ManufacturerSignIn manufacturerSignIn;
    
    public static boolean openJFrame;
    
	public GUI() {
		// TODO Auto-generated constructor stub

        this.frame_index = new JFrame("仓储管理系统");
        frame_index.setSize(350, 250);
        frame_index.setLocation(500, 300);
        frame_index.setBackground(Color.lightGray);
        frame_index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_index.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // 组件在显示区域居中对齐
        gbc.fill = GridBagConstraints.BOTH; // 设置组件填充区域
        gbc.insets = new Insets(16, 16, 16, 16); // 设置与显示区域边缘的位置

        this.jc_id = new JComboBox();
        this.jc_id.addItem("--请选择身份--");
        this.jc_id.addItem("供货商");
        this.jc_id.addItem("消费者");
        
        this.button_signUp = new Button("注册");
        this.button_signIn = new Button("登录");

        this.jc_id.addActionListener(this);
        this.button_signUp.addActionListener(this);
        this.button_signIn.addActionListener(this);
        
        openJFrame = true;//打开另一个JFrame，false：返回上一级

        Container frame_contain = frame_index.getContentPane(); // 获取窗口的内容窗格

        addComponent(jc_id, frame_contain, gbc, 0, 0, 4, 1, 1, 1);
        addComponent(button_signUp, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(button_signIn, frame_contain, gbc, 1, 2, 2, 1, 1, 1);

        frame_index.setVisible(true);
	   
	}
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // 第几行的格子
	        gbc.gridx = column; // 第几列的格子
	        gbc.gridwidth = width; // 占用几列格子
	        gbc.gridheight = height; // 占用几行格子
	        gbc.weightx = weightx; // 行方向分配的权重
	        gbc.weighty = weighty; // 列方向分配的权重
	        gbc.ipadx = 2; // 左右边缘的空白距离
	        gbc.ipady = 2; // 上下边缘的空白距离
	        contain.add(c, gbc); // 将组件c按照约束条件gbc添加到容器contain中
	    }
	 
	 public void actionPerformed(ActionEvent event) {
		 if(event.getSource() == this.jc_id) {
//			 System.out.println(this.jc_id.getSelectedItem());
			 this.id = String.valueOf(this.jc_id.getSelectedItem());
		 }
		 else if(event.getSource() == this.button_signUp) {
			 //注册
			 if(this.id.equals("消费者")) {
				 if(openJFrame) {
					 this.customerSignUp = new CustomerSignUp();
//					 this.frame_index.setVisible(false);
					 
				 }
//				 else {
//					 this.frame_index.setVisible(true);
//				 }
			 }
			 else if(this.id.equals("供货商")) {
				 this.manufacturerSighUp = new ManufacturerSignUp();
//				 this.frame_index.setVisible(false);
			 }
		 }
		 else if(event.getSource() == this.button_signIn) {
			 //登录
			 if(this.id.equals("消费者")) {
				 this.customerSignIn = new CustomerSignIn();
//				 this.frame_index.setVisible(false);
			 }
			 else if(this.id.equals("供货商")) {
				 this.manufacturerSignIn = new ManufacturerSignIn();
//				 this.frame_index.setVisible(false);
			 }
		 }
	 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI index = new GUI();
	}

}

class CustomerSignUp implements ActionListener{
	private JComboBox cmb;//地区下拉菜单
	
	private JLabel label_user;
	private JLabel label_password;
	private JLabel label_check;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	private JPasswordField field_passwordCheck;
	
	private Button button_signUp;//注册
	private Button button_cancle;//取消返回上一级
	
	private JFrame frame_customerSignUp;
		
	public CustomerSignUp() {
		this.frame_customerSignUp = new JFrame("消费者注册");
		this.frame_customerSignUp.setSize(350, 450);
		this.frame_customerSignUp.setLocation(500, 150);
		this.frame_customerSignUp.setBackground(Color.lightGray);
		this.frame_customerSignUp.setLayout(new GridBagLayout());
		this.frame_customerSignUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // 组件在显示区域居中对齐
        gbc.fill = GridBagConstraints.BOTH; // 设置组件填充区域
        gbc.insets = new Insets(16, 16, 16, 16); // 设置与显示区域边缘的位置

        this.cmb = new JComboBox();
        this.cmb.addItem("--请选择居住地区--");
        this.cmb.addItem("北京");
        this.cmb.addItem("上海");
        this.cmb.addItem("广州");
        this.cmb.addItem("深圳");
        this.cmb.addItem("浙江");
        this.cmb.addItem("福建");
        this.cmb.addItem("河北");
        this.cmb.addItem("湖南");
        this.cmb.addItem("陕西");
        this.cmb.addItem("江苏");
        
        this.label_user 	= new JLabel("用户",JLabel.LEFT);
        this.label_password = new JLabel("密码",JLabel.LEFT);
        this.label_check	= new JLabel("验证",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        this.field_passwordCheck 	= new JPasswordField("fjid",JPasswordField.LEFT);
        
        this.button_signUp = new Button("注册");
        this.button_cancle = new Button("取消");

        this.cmb.addActionListener(this);
        this.button_signUp.addActionListener(this);
        this.button_cancle.addActionListener(this);

        Container frame_contain = this.frame_customerSignUp.getContentPane(); // 获取窗口的内容窗格

        addComponent(this.label_user, frame_contain, gbc, 0, 0, 2, 1, 1, 1);
        addComponent(this.field_user, frame_contain, gbc, 0, 2, 6, 1, 1, 1);
        addComponent(this.label_password, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(this.field_password, frame_contain, gbc, 1, 2, 6, 1, 1, 1);
        addComponent(this.label_check, frame_contain, gbc, 2, 0, 2, 1, 1, 1);
        addComponent(this.field_passwordCheck, frame_contain, gbc, 2, 2, 6, 1, 1, 1);
        addComponent(this.cmb, frame_contain, gbc, 3, 0, 8, 1, 1, 1);
        addComponent(this.button_signUp, frame_contain, gbc, 4, 1, 2, 1, 1, 1);
        addComponent(this.button_cancle, frame_contain, gbc, 4, 3, 2, 1, 1, 1);

        this.frame_customerSignUp.setVisible(true);
	}
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // 第几行的格子
	        gbc.gridx = column; // 第几列的格子
	        gbc.gridwidth = width; // 占用几列格子
	        gbc.gridheight = height; // 占用几行格子
	        gbc.weightx = weightx; // 行方向分配的权重
	        gbc.weighty = weighty; // 列方向分配的权重
	        gbc.ipadx = 2; // 左右边缘的空白距离
	        gbc.ipady = 2; // 上下边缘的空白距离
	        contain.add(c, gbc); // 将组件c按照约束条件gbc添加到容器contain中
	    }
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.button_cancle) {
//			GUI.openJFrame = false;
//			this.frame_customerSignUp.dispose();
			this.frame_customerSignUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
}

class CustomerSignIn implements ActionListener{
	
	private JLabel label_user;
	private JLabel label_password;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	
	private Button button_signIn;//登录
	private Button button_changePassword;//修改密码
	
	private JFrame frame_customerSignIn;
	
	public CustomerSignIn() {
		this.frame_customerSignIn = new JFrame("消费者登录");
		this.frame_customerSignIn.setSize(350, 250);
		this.frame_customerSignIn.setLocation(500, 300);
		this.frame_customerSignIn.setBackground(Color.lightGray);
		this.frame_customerSignIn.setLayout(new GridBagLayout());
		this.frame_customerSignIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // 组件在显示区域居中对齐
        gbc.fill = GridBagConstraints.BOTH; // 设置组件填充区域
        gbc.insets = new Insets(16, 16, 16, 16); // 设置与显示区域边缘的位置
        
        this.label_user 	= new JLabel("用户",JLabel.LEFT);
        this.label_password = new JLabel("密码",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        
        this.button_signIn 			= new Button("登录");
        this.button_changePassword 	= new Button("修改密码");

        this.button_signIn.addActionListener(this);
        this.button_changePassword.addActionListener(this);

        Container frame_contain = this.frame_customerSignIn.getContentPane(); // 获取窗口的内容窗格

        addComponent(this.label_user, frame_contain, gbc, 0, 0, 2, 1, 1, 1);
        addComponent(this.field_user, frame_contain, gbc, 0, 2, 6, 1, 1, 1);
        addComponent(this.label_password, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(this.field_password, frame_contain, gbc, 1, 2, 6, 1, 1, 1);
        addComponent(this.button_signIn, frame_contain, gbc, 2, 1, 2, 1, 1, 1);
        addComponent(this.button_changePassword, frame_contain, gbc, 2, 3, 2, 1, 1, 1);

        this.frame_customerSignIn.setVisible(true);
	}
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // 第几行的格子
	        gbc.gridx = column; // 第几列的格子
	        gbc.gridwidth = width; // 占用几列格子
	        gbc.gridheight = height; // 占用几行格子
	        gbc.weightx = weightx; // 行方向分配的权重
	        gbc.weighty = weighty; // 列方向分配的权重
	        gbc.ipadx = 2; // 左右边缘的空白距离
	        gbc.ipady = 2; // 上下边缘的空白距离
	        contain.add(c, gbc); // 将组件c按照约束条件gbc添加到容器contain中
	    }
	
	public void actionPerformed(ActionEvent event) {
		
	}
}

class ManufacturerSignUp implements ActionListener{	
	private JLabel label_user;
	private JLabel label_password;
	private JLabel label_check;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	private JPasswordField field_passwordCheck;
	
	private Button button_signUp;//注册
	
	private JFrame frame_manufacturerSignUp;
	
	public ManufacturerSignUp() {
		this.frame_manufacturerSignUp = new JFrame("供货商注册");
		this.frame_manufacturerSignUp.setSize(350, 450);
		this.frame_manufacturerSignUp.setLocation(500, 150);
		this.frame_manufacturerSignUp.setBackground(Color.lightGray);
		this.frame_manufacturerSignUp.setLayout(new GridBagLayout());
        this.frame_manufacturerSignUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // 组件在显示区域居中对齐
        gbc.fill = GridBagConstraints.BOTH; // 设置组件填充区域
        gbc.insets = new Insets(16, 16, 16, 16); // 设置与显示区域边缘的位置
        
        this.label_user 	= new JLabel("用户",JLabel.LEFT);
        this.label_password = new JLabel("密码",JLabel.LEFT);
        this.label_check	= new JLabel("验证",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        this.field_passwordCheck 	= new JPasswordField("fjid",JPasswordField.LEFT);
        
        this.button_signUp = new Button("注册");

        this.button_signUp.addActionListener(this);

        Container frame_contain = this.frame_manufacturerSignUp.getContentPane(); // 获取窗口的内容窗格

        addComponent(this.label_user, frame_contain, gbc, 0, 0, 2, 1, 1, 1);
        addComponent(this.field_user, frame_contain, gbc, 0, 2, 6, 1, 1, 1);
        addComponent(this.label_password, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(this.field_password, frame_contain, gbc, 1, 2, 6, 1, 1, 1);
        addComponent(this.label_check, frame_contain, gbc, 2, 0, 2, 1, 1, 1);
        addComponent(this.field_passwordCheck, frame_contain, gbc, 2, 2, 6, 1, 1, 1);
        addComponent(this.button_signUp, frame_contain, gbc, 4, 3, 2, 1, 1, 1);

        this.frame_manufacturerSignUp.setVisible(true);
	}
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // 第几行的格子
	        gbc.gridx = column; // 第几列的格子
	        gbc.gridwidth = width; // 占用几列格子
	        gbc.gridheight = height; // 占用几行格子
	        gbc.weightx = weightx; // 行方向分配的权重
	        gbc.weighty = weighty; // 列方向分配的权重
	        gbc.ipadx = 2; // 左右边缘的空白距离
	        gbc.ipady = 2; // 上下边缘的空白距离
	        contain.add(c, gbc); // 将组件c按照约束条件gbc添加到容器contain中
	    }
	
	public void actionPerformed(ActionEvent event) {
		
	}
}

class ManufacturerSignIn implements ActionListener{	
	private JLabel label_user;
	private JLabel label_password;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	
	private Button button_signIn;//登录
	private Button button_changePassword;//修改密码
	
	private JFrame frame_manufacturerSignIn;
	
	public ManufacturerSignIn() {
		this.frame_manufacturerSignIn = new JFrame("供货商登录");
		this.frame_manufacturerSignIn.setSize(350, 250);
		this.frame_manufacturerSignIn.setLocation(500, 300);
		this.frame_manufacturerSignIn.setBackground(Color.lightGray);
		this.frame_manufacturerSignIn.setLayout(new GridBagLayout());
        this.frame_manufacturerSignIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // 组件在显示区域居中对齐
        gbc.fill = GridBagConstraints.BOTH; // 设置组件填充区域
        gbc.insets = new Insets(16, 16, 16, 16); // 设置与显示区域边缘的位置
        
        this.label_user 	= new JLabel("用户",JLabel.LEFT);
        this.label_password = new JLabel("密码",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        
        this.button_signIn 			= new Button("登录");
        this.button_changePassword 	= new Button("修改密码");

        this.button_signIn.addActionListener(this);
        this.button_changePassword.addActionListener(this);

        Container frame_contain = this.frame_manufacturerSignIn.getContentPane(); // 获取窗口的内容窗格

        addComponent(this.label_user, frame_contain, gbc, 0, 0, 2, 1, 1, 1);
        addComponent(this.field_user, frame_contain, gbc, 0, 2, 6, 1, 1, 1);
        addComponent(this.label_password, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(this.field_password, frame_contain, gbc, 1, 2, 6, 1, 1, 1);
        addComponent(this.button_signIn, frame_contain, gbc, 2, 1, 2, 1, 1, 1);
        addComponent(this.button_changePassword, frame_contain, gbc, 2, 3, 2, 1, 1, 1);


        this.frame_manufacturerSignIn.setVisible(true);
	}
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // 第几行的格子
	        gbc.gridx = column; // 第几列的格子
	        gbc.gridwidth = width; // 占用几列格子
	        gbc.gridheight = height; // 占用几行格子
	        gbc.weightx = weightx; // 行方向分配的权重
	        gbc.weighty = weighty; // 列方向分配的权重
	        gbc.ipadx = 2; // 左右边缘的空白距离
	        gbc.ipady = 2; // 上下边缘的空白距离
	        contain.add(c, gbc); // 将组件c按照约束条件gbc添加到容器contain中
	    }
	
	public void actionPerformed(ActionEvent event) {
		
	}
}

class Customer implements ActionListener{
	
	private JLabel label_user;
	private JLabel label_password;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	
	private Button button_signIn;//登录
	private Button button_changePassword;//修改密码
	
	private JFrame frame_customerSignIn;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	      // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
	     String JDriver= "com.mysql.cj.jdbc.Driver";  
	     String conURL = "jdbc:mysql://localhost:3306/student_courses?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	      // 数据库的用户名与密码，需要根据自己的设置
	       String USER = "root";                     //MySql用户名
	       String PASS = "my8.0.22SQL@";          //MySql密码，要采用你的密码
		try {
	      Class.forName(JDriver);  //加载JDBC驱动程序
	    }
	    catch(java.lang.ClassNotFoundException e) {
	      System.out.println("ForName :" + e.getMessage( ));
	    }
	    try {
	      Connection con=DriverManager.getConnection(conURL,USER,PASS);  //连接数据库URL
	      PreparedStatement ps=con.prepareStatement("insert into user(username,password) values(?,?)");  //建立Statement类对象
	      ps.setString(1, "admin");
	      ps.setBytes(2, getHashBiteString("admin"));
	      ps.executeUpdate();  //执行SQL命令
	     
	      ps.close( );  //释放Statement所连接的数据库及JDBC资源
	      con.close( );  //关闭与数据库的连线
	    }
	    catch(SQLException e){ System.out.println("SQLException: " +e.getMessage( ));  }
	}
	
	public void display() {
		this.frame_customerSignIn = new JFrame("消费者登录");
		this.frame_customerSignIn.setSize(350, 250);
		this.frame_customerSignIn.setLocation(500, 300);
		this.frame_customerSignIn.setBackground(Color.lightGray);
		this.frame_customerSignIn.setLayout(new GridBagLayout());
		this.frame_customerSignIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // 组件在显示区域居中对齐
        gbc.fill = GridBagConstraints.BOTH; // 设置组件填充区域
        gbc.insets = new Insets(16, 16, 16, 16); // 设置与显示区域边缘的位置
        
        this.label_user 	= new JLabel("用户",JLabel.LEFT);
        this.label_password = new JLabel("密码",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        
        this.button_signIn 			= new Button("登录");
        this.button_changePassword 	= new Button("修改密码");

        this.button_signIn.addActionListener(this);
        this.button_changePassword.addActionListener(this);

        Container frame_contain = this.frame_customerSignIn.getContentPane(); // 获取窗口的内容窗格

        addComponent(this.label_user, frame_contain, gbc, 0, 0, 2, 1, 1, 1);
        addComponent(this.field_user, frame_contain, gbc, 0, 2, 6, 1, 1, 1);
        addComponent(this.label_password, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(this.field_password, frame_contain, gbc, 1, 2, 6, 1, 1, 1);
        addComponent(this.button_signIn, frame_contain, gbc, 2, 1, 2, 1, 1, 1);
        addComponent(this.button_changePassword, frame_contain, gbc, 2, 3, 2, 1, 1, 1);

        this.frame_customerSignIn.setVisible(true);
	}
	
	   // 获得字符串的摘要
    private static byte[] getHashBiteString(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            // digest.update(String.valueOf(str).getBytes());
            // return new ByteArrayInputStream(digest.digest());
            return digest.digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, String.format("异常信息:\n%s\n%s", e.toString(), e.getStackTrace()), "运行时异常", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return null;
    }
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // 第几行的格子
	        gbc.gridx = column; // 第几列的格子
	        gbc.gridwidth = width; // 占用几列格子
	        gbc.gridheight = height; // 占用几行格子
	        gbc.weightx = weightx; // 行方向分配的权重
	        gbc.weighty = weighty; // 列方向分配的权重
	        gbc.ipadx = 2; // 左右边缘的空白距离
	        gbc.ipady = 2; // 上下边缘的空白距离
	        contain.add(c, gbc); // 将组件c按照约束条件gbc添加到容器contain中
	    }
	
	public void actionPerformed(ActionEvent event) {
		
	}
}
















