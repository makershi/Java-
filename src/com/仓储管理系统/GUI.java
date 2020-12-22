/**
 * 
 */
package com.�ִ�����ϵͳ;

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
	//GUI����ҳ
	private JComboBox		jc_id;//ѡ����ݣ�������/�����ߣ�
    private Button     		button_signUp;//ע��
    private Button     		button_signIn;//��¼

    private JFrame     frame_index;
	
    private String id;
    
    private CustomerSignUp customerSignUp;
    private ManufacturerSignUp manufacturerSighUp;
    private CustomerSignIn customerSignIn;
    private ManufacturerSignIn manufacturerSignIn;
    
    public static boolean openJFrame;
    
	public GUI() {
		// TODO Auto-generated constructor stub

        this.frame_index = new JFrame("�ִ�����ϵͳ");
        frame_index.setSize(350, 250);
        frame_index.setLocation(500, 300);
        frame_index.setBackground(Color.lightGray);
        frame_index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_index.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // �������ʾ������ж���
        gbc.fill = GridBagConstraints.BOTH; // ��������������
        gbc.insets = new Insets(16, 16, 16, 16); // ��������ʾ�����Ե��λ��

        this.jc_id = new JComboBox();
        this.jc_id.addItem("--��ѡ�����--");
        this.jc_id.addItem("������");
        this.jc_id.addItem("������");
        
        this.button_signUp = new Button("ע��");
        this.button_signIn = new Button("��¼");

        this.jc_id.addActionListener(this);
        this.button_signUp.addActionListener(this);
        this.button_signIn.addActionListener(this);
        
        openJFrame = true;//����һ��JFrame��false��������һ��

        Container frame_contain = frame_index.getContentPane(); // ��ȡ���ڵ����ݴ���

        addComponent(jc_id, frame_contain, gbc, 0, 0, 4, 1, 1, 1);
        addComponent(button_signUp, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(button_signIn, frame_contain, gbc, 1, 2, 2, 1, 1, 1);

        frame_index.setVisible(true);
	   
	}
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // �ڼ��еĸ���
	        gbc.gridx = column; // �ڼ��еĸ���
	        gbc.gridwidth = width; // ռ�ü��и���
	        gbc.gridheight = height; // ռ�ü��и���
	        gbc.weightx = weightx; // �з�������Ȩ��
	        gbc.weighty = weighty; // �з�������Ȩ��
	        gbc.ipadx = 2; // ���ұ�Ե�Ŀհ׾���
	        gbc.ipady = 2; // ���±�Ե�Ŀհ׾���
	        contain.add(c, gbc); // �����c����Լ������gbc��ӵ�����contain��
	    }
	 
	 public void actionPerformed(ActionEvent event) {
		 if(event.getSource() == this.jc_id) {
//			 System.out.println(this.jc_id.getSelectedItem());
			 this.id = String.valueOf(this.jc_id.getSelectedItem());
		 }
		 else if(event.getSource() == this.button_signUp) {
			 //ע��
			 if(this.id.equals("������")) {
				 if(openJFrame) {
					 this.customerSignUp = new CustomerSignUp();
//					 this.frame_index.setVisible(false);
					 
				 }
//				 else {
//					 this.frame_index.setVisible(true);
//				 }
			 }
			 else if(this.id.equals("������")) {
				 this.manufacturerSighUp = new ManufacturerSignUp();
//				 this.frame_index.setVisible(false);
			 }
		 }
		 else if(event.getSource() == this.button_signIn) {
			 //��¼
			 if(this.id.equals("������")) {
				 this.customerSignIn = new CustomerSignIn();
//				 this.frame_index.setVisible(false);
			 }
			 else if(this.id.equals("������")) {
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
	private JComboBox cmb;//���������˵�
	
	private JLabel label_user;
	private JLabel label_password;
	private JLabel label_check;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	private JPasswordField field_passwordCheck;
	
	private Button button_signUp;//ע��
	private Button button_cancle;//ȡ��������һ��
	
	private JFrame frame_customerSignUp;
		
	public CustomerSignUp() {
		this.frame_customerSignUp = new JFrame("������ע��");
		this.frame_customerSignUp.setSize(350, 450);
		this.frame_customerSignUp.setLocation(500, 150);
		this.frame_customerSignUp.setBackground(Color.lightGray);
		this.frame_customerSignUp.setLayout(new GridBagLayout());
		this.frame_customerSignUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // �������ʾ������ж���
        gbc.fill = GridBagConstraints.BOTH; // ��������������
        gbc.insets = new Insets(16, 16, 16, 16); // ��������ʾ�����Ե��λ��

        this.cmb = new JComboBox();
        this.cmb.addItem("--��ѡ���ס����--");
        this.cmb.addItem("����");
        this.cmb.addItem("�Ϻ�");
        this.cmb.addItem("����");
        this.cmb.addItem("����");
        this.cmb.addItem("�㽭");
        this.cmb.addItem("����");
        this.cmb.addItem("�ӱ�");
        this.cmb.addItem("����");
        this.cmb.addItem("����");
        this.cmb.addItem("����");
        
        this.label_user 	= new JLabel("�û�",JLabel.LEFT);
        this.label_password = new JLabel("����",JLabel.LEFT);
        this.label_check	= new JLabel("��֤",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        this.field_passwordCheck 	= new JPasswordField("fjid",JPasswordField.LEFT);
        
        this.button_signUp = new Button("ע��");
        this.button_cancle = new Button("ȡ��");

        this.cmb.addActionListener(this);
        this.button_signUp.addActionListener(this);
        this.button_cancle.addActionListener(this);

        Container frame_contain = this.frame_customerSignUp.getContentPane(); // ��ȡ���ڵ����ݴ���

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
	        gbc.gridy = row; // �ڼ��еĸ���
	        gbc.gridx = column; // �ڼ��еĸ���
	        gbc.gridwidth = width; // ռ�ü��и���
	        gbc.gridheight = height; // ռ�ü��и���
	        gbc.weightx = weightx; // �з�������Ȩ��
	        gbc.weighty = weighty; // �з�������Ȩ��
	        gbc.ipadx = 2; // ���ұ�Ե�Ŀհ׾���
	        gbc.ipady = 2; // ���±�Ե�Ŀհ׾���
	        contain.add(c, gbc); // �����c����Լ������gbc��ӵ�����contain��
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
	
	private Button button_signIn;//��¼
	private Button button_changePassword;//�޸�����
	
	private JFrame frame_customerSignIn;
	
	public CustomerSignIn() {
		this.frame_customerSignIn = new JFrame("�����ߵ�¼");
		this.frame_customerSignIn.setSize(350, 250);
		this.frame_customerSignIn.setLocation(500, 300);
		this.frame_customerSignIn.setBackground(Color.lightGray);
		this.frame_customerSignIn.setLayout(new GridBagLayout());
		this.frame_customerSignIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // �������ʾ������ж���
        gbc.fill = GridBagConstraints.BOTH; // ��������������
        gbc.insets = new Insets(16, 16, 16, 16); // ��������ʾ�����Ե��λ��
        
        this.label_user 	= new JLabel("�û�",JLabel.LEFT);
        this.label_password = new JLabel("����",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        
        this.button_signIn 			= new Button("��¼");
        this.button_changePassword 	= new Button("�޸�����");

        this.button_signIn.addActionListener(this);
        this.button_changePassword.addActionListener(this);

        Container frame_contain = this.frame_customerSignIn.getContentPane(); // ��ȡ���ڵ����ݴ���

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
	        gbc.gridy = row; // �ڼ��еĸ���
	        gbc.gridx = column; // �ڼ��еĸ���
	        gbc.gridwidth = width; // ռ�ü��и���
	        gbc.gridheight = height; // ռ�ü��и���
	        gbc.weightx = weightx; // �з�������Ȩ��
	        gbc.weighty = weighty; // �з�������Ȩ��
	        gbc.ipadx = 2; // ���ұ�Ե�Ŀհ׾���
	        gbc.ipady = 2; // ���±�Ե�Ŀհ׾���
	        contain.add(c, gbc); // �����c����Լ������gbc��ӵ�����contain��
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
	
	private Button button_signUp;//ע��
	
	private JFrame frame_manufacturerSignUp;
	
	public ManufacturerSignUp() {
		this.frame_manufacturerSignUp = new JFrame("������ע��");
		this.frame_manufacturerSignUp.setSize(350, 450);
		this.frame_manufacturerSignUp.setLocation(500, 150);
		this.frame_manufacturerSignUp.setBackground(Color.lightGray);
		this.frame_manufacturerSignUp.setLayout(new GridBagLayout());
        this.frame_manufacturerSignUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // �������ʾ������ж���
        gbc.fill = GridBagConstraints.BOTH; // ��������������
        gbc.insets = new Insets(16, 16, 16, 16); // ��������ʾ�����Ե��λ��
        
        this.label_user 	= new JLabel("�û�",JLabel.LEFT);
        this.label_password = new JLabel("����",JLabel.LEFT);
        this.label_check	= new JLabel("��֤",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        this.field_passwordCheck 	= new JPasswordField("fjid",JPasswordField.LEFT);
        
        this.button_signUp = new Button("ע��");

        this.button_signUp.addActionListener(this);

        Container frame_contain = this.frame_manufacturerSignUp.getContentPane(); // ��ȡ���ڵ����ݴ���

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
	        gbc.gridy = row; // �ڼ��еĸ���
	        gbc.gridx = column; // �ڼ��еĸ���
	        gbc.gridwidth = width; // ռ�ü��и���
	        gbc.gridheight = height; // ռ�ü��и���
	        gbc.weightx = weightx; // �з�������Ȩ��
	        gbc.weighty = weighty; // �з�������Ȩ��
	        gbc.ipadx = 2; // ���ұ�Ե�Ŀհ׾���
	        gbc.ipady = 2; // ���±�Ե�Ŀհ׾���
	        contain.add(c, gbc); // �����c����Լ������gbc��ӵ�����contain��
	    }
	
	public void actionPerformed(ActionEvent event) {
		
	}
}

class ManufacturerSignIn implements ActionListener{	
	private JLabel label_user;
	private JLabel label_password;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	
	private Button button_signIn;//��¼
	private Button button_changePassword;//�޸�����
	
	private JFrame frame_manufacturerSignIn;
	
	public ManufacturerSignIn() {
		this.frame_manufacturerSignIn = new JFrame("�����̵�¼");
		this.frame_manufacturerSignIn.setSize(350, 250);
		this.frame_manufacturerSignIn.setLocation(500, 300);
		this.frame_manufacturerSignIn.setBackground(Color.lightGray);
		this.frame_manufacturerSignIn.setLayout(new GridBagLayout());
        this.frame_manufacturerSignIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // �������ʾ������ж���
        gbc.fill = GridBagConstraints.BOTH; // ��������������
        gbc.insets = new Insets(16, 16, 16, 16); // ��������ʾ�����Ե��λ��
        
        this.label_user 	= new JLabel("�û�",JLabel.LEFT);
        this.label_password = new JLabel("����",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        
        this.button_signIn 			= new Button("��¼");
        this.button_changePassword 	= new Button("�޸�����");

        this.button_signIn.addActionListener(this);
        this.button_changePassword.addActionListener(this);

        Container frame_contain = this.frame_manufacturerSignIn.getContentPane(); // ��ȡ���ڵ����ݴ���

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
	        gbc.gridy = row; // �ڼ��еĸ���
	        gbc.gridx = column; // �ڼ��еĸ���
	        gbc.gridwidth = width; // ռ�ü��и���
	        gbc.gridheight = height; // ռ�ü��и���
	        gbc.weightx = weightx; // �з�������Ȩ��
	        gbc.weighty = weighty; // �з�������Ȩ��
	        gbc.ipadx = 2; // ���ұ�Ե�Ŀհ׾���
	        gbc.ipady = 2; // ���±�Ե�Ŀհ׾���
	        contain.add(c, gbc); // �����c����Լ������gbc��ӵ�����contain��
	    }
	
	public void actionPerformed(ActionEvent event) {
		
	}
}

class Customer implements ActionListener{
	
	private JLabel label_user;
	private JLabel label_password;
	
	private JTextField field_user;
	
	private JPasswordField field_password;
	
	private Button button_signIn;//��¼
	private Button button_changePassword;//�޸�����
	
	private JFrame frame_customerSignIn;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	      // MySQL 8.0 ���ϰ汾 - JDBC �����������ݿ� URL
	     String JDriver= "com.mysql.cj.jdbc.Driver";  
	     String conURL = "jdbc:mysql://localhost:3306/student_courses?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	      // ���ݿ���û��������룬��Ҫ�����Լ�������
	       String USER = "root";                     //MySql�û���
	       String PASS = "my8.0.22SQL@";          //MySql���룬Ҫ�����������
		try {
	      Class.forName(JDriver);  //����JDBC��������
	    }
	    catch(java.lang.ClassNotFoundException e) {
	      System.out.println("ForName :" + e.getMessage( ));
	    }
	    try {
	      Connection con=DriverManager.getConnection(conURL,USER,PASS);  //�������ݿ�URL
	      PreparedStatement ps=con.prepareStatement("insert into user(username,password) values(?,?)");  //����Statement�����
	      ps.setString(1, "admin");
	      ps.setBytes(2, getHashBiteString("admin"));
	      ps.executeUpdate();  //ִ��SQL����
	     
	      ps.close( );  //�ͷ�Statement�����ӵ����ݿ⼰JDBC��Դ
	      con.close( );  //�ر������ݿ������
	    }
	    catch(SQLException e){ System.out.println("SQLException: " +e.getMessage( ));  }
	}
	
	public void display() {
		this.frame_customerSignIn = new JFrame("�����ߵ�¼");
		this.frame_customerSignIn.setSize(350, 250);
		this.frame_customerSignIn.setLocation(500, 300);
		this.frame_customerSignIn.setBackground(Color.lightGray);
		this.frame_customerSignIn.setLayout(new GridBagLayout());
		this.frame_customerSignIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER; // �������ʾ������ж���
        gbc.fill = GridBagConstraints.BOTH; // ��������������
        gbc.insets = new Insets(16, 16, 16, 16); // ��������ʾ�����Ե��λ��
        
        this.label_user 	= new JLabel("�û�",JLabel.LEFT);
        this.label_password = new JLabel("����",JLabel.LEFT);
        
        this.field_user 			= new JTextField("",JTextField.LEFT);
        this.field_password 		= new JPasswordField("fjd",JPasswordField.LEFT);
        
        this.button_signIn 			= new Button("��¼");
        this.button_changePassword 	= new Button("�޸�����");

        this.button_signIn.addActionListener(this);
        this.button_changePassword.addActionListener(this);

        Container frame_contain = this.frame_customerSignIn.getContentPane(); // ��ȡ���ڵ����ݴ���

        addComponent(this.label_user, frame_contain, gbc, 0, 0, 2, 1, 1, 1);
        addComponent(this.field_user, frame_contain, gbc, 0, 2, 6, 1, 1, 1);
        addComponent(this.label_password, frame_contain, gbc, 1, 0, 2, 1, 1, 1);
        addComponent(this.field_password, frame_contain, gbc, 1, 2, 6, 1, 1, 1);
        addComponent(this.button_signIn, frame_contain, gbc, 2, 1, 2, 1, 1, 1);
        addComponent(this.button_changePassword, frame_contain, gbc, 2, 3, 2, 1, 1, 1);

        this.frame_customerSignIn.setVisible(true);
	}
	
	   // ����ַ�����ժҪ
    private static byte[] getHashBiteString(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            // digest.update(String.valueOf(str).getBytes());
            // return new ByteArrayInputStream(digest.digest());
            return digest.digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, String.format("�쳣��Ϣ:\n%s\n%s", e.toString(), e.getStackTrace()), "����ʱ�쳣", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return null;
    }
	
	 private void addComponent(Component c, Container contain, GridBagConstraints gbc, int row, int column, int width, int height, int weightx, int weighty)
	    {
	        gbc.gridy = row; // �ڼ��еĸ���
	        gbc.gridx = column; // �ڼ��еĸ���
	        gbc.gridwidth = width; // ռ�ü��и���
	        gbc.gridheight = height; // ռ�ü��и���
	        gbc.weightx = weightx; // �з�������Ȩ��
	        gbc.weighty = weighty; // �з�������Ȩ��
	        gbc.ipadx = 2; // ���ұ�Ե�Ŀհ׾���
	        gbc.ipady = 2; // ���±�Ե�Ŀհ׾���
	        contain.add(c, gbc); // �����c����Լ������gbc��ӵ�����contain��
	    }
	
	public void actionPerformed(ActionEvent event) {
		
	}
}
















