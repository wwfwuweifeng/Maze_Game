package 我是迷宫王;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import 功能部分.Basic_Function;
import 功能部分.FileOperation;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tx_name;
	private JPasswordField tx_passdword;
	private ButtonGroup login_btg=new ButtonGroup();
	public Basic_Function basic=new Basic_Function();
	public FileOperation file=new FileOperation();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
//		java.net.URL file1 = getClass().getResource("e:/workplace/软工2/src/配置文件/5082.wav"); 
//		  AudioClip sound1 = java.applet.Applet.newAudioClip(file1); 
//		  sound1.play();
//		File file1 = new File("e:/workplace/软工2/src/配置文件/5082.wav");
//		AudioClip sound1 = null;
//		try {
//			sound1 = Applet.newAudioClip(file1.toURL());
//			System.out.println("test");
//		} catch (MalformedURLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		sound1.play();  
		 String filepath="d:/课设2游戏文件";
		 file.CreateDir(filepath);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 302);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setResizable(false);
		setTitle("我是迷宫王");
//		JLabel lblNewLabel = new JLabel("New label");
//		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		
		JButton bt_login = new JButton("\u767B\u5F55");
		bt_login.setBackground(SystemColor.inactiveCaptionBorder);
		bt_login.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		bt_login.setBounds(102, 183, 97, 23);
		contentPane.add(bt_login);
		
		JLabel lblNewLabel_1 = new JLabel("\u73A9\u5BB6\u767B\u5F55");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(135, 27, 134, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(47, 82, 65, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(47, 126, 65, 34);
		contentPane.add(label);
		
		tx_name = new JTextField();
		tx_name.setBounds(125, 84, 172, 34);
		contentPane.add(tx_name);
	//	contentPane.add(test);
		tx_name.setColumns(10);
		
		tx_passdword = new JPasswordField();
		tx_passdword.setBounds(125, 128, 172, 34);
		contentPane.add(tx_passdword);
		
		
		JButton bt_register = new JButton("\u6CE8\u518C");
		bt_register.setBackground(SystemColor.inactiveCaptionBorder);
		bt_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file.CreateStart();
				dispose();
				Rigester.main(null);
			}
		});
		bt_register.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		bt_register.setBounds(227, 183, 97, 23);
		contentPane.add(bt_register);
		
		JLabel lblNewLabel_4 = new JLabel("\u7248\u672C\u53F7\uFF1A1.2.1");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(214, 246, 182, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lb_login_remind = new JLabel("");
		lb_login_remind.setHorizontalAlignment(SwingConstants.CENTER);
		lb_login_remind.setBounds(66, 216, 302, 19);
		contentPane.add(lb_login_remind);
		
		JButton btnNewButton = new JButton("\u5FD8\u4E86\u5BC6\u7801");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				file.CreateStart();
				Change_Pass.main(null);
				dispose();
				
			}
		});
		btnNewButton.setBackground(new Color(248, 248, 255));
		btnNewButton.setBounds(303, 133, 93, 23);
		contentPane.add(btnNewButton);

		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//有问题，要改
				file.CreateStart();
				String user=basic.Login_Judge(tx_name.getText()+"-"+tx_passdword.getText()+"-");
				if(user.equals("no")){
					lb_login_remind.setText("密码错误");
				}else{
					First_Frame.show(user);
					dispose();
				}
				
			
			}
		});
	}
}
