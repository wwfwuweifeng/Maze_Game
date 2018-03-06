package 我是迷宫王;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import 功能部分.Basic_Function;
import 功能部分.File_Function;

public class Rigester extends JFrame {

	private JPanel contentPane;
	private JTextField tx_name;
	private JPasswordField tx_passd;
	private JPasswordField tx_passd_again;
	public Vector<String> file_getname=new Vector<String>();
	private JTextField tx_code;
	private JTextField tx_realyname;
	public Thread thread;
	public Basic_Function basic=new Basic_Function();
	public File_Function file=new File_Function();
	public int time=5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rigester frame = new Rigester();
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
	public Rigester() {
		setBackground(Color.WHITE);
		ButtonGroup regiser_btg=new ButtonGroup();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 357);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setResizable(false);
		setTitle("玩家注册");
		JLabel lblNewLabel = new JLabel("\u4FE1\u606F\u586B\u5199");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel.setBounds(126, 28, 179, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setBackground(new Color(245, 255, 250));
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 14));
	
		btnNewButton.setBounds(85, 277, 100, 29);
		contentPane.add(btnNewButton);
		
		tx_name = new JTextField();
		tx_name.setBounds(126, 100, 179, 23);
		contentPane.add(tx_name);
		tx_name.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(50, 129, 66, 29);
		contentPane.add(label);
		
		tx_passd = new JPasswordField();
		tx_passd.setBounds(126, 133, 179, 23);
		contentPane.add(tx_passd);
		
		JLabel label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(31, 168, 88, 23);
		contentPane.add(label_1);
		
		tx_passd_again = new JPasswordField();
		tx_passd_again.setBounds(126, 166, 179, 23);
		contentPane.add(tx_passd_again);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.setBackground(new Color(245, 255, 250));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login.main(null);
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(236, 277, 100, 29);
		contentPane.add(button);
		
		JLabel lb_remind = new JLabel("");
		lb_remind.setFont(new Font("宋体", Font.PLAIN, 14));
		lb_remind.setHorizontalAlignment(SwingConstants.CENTER);
		lb_remind.setBounds(10, 246, 378, 23);
		contentPane.add(lb_remind);
		
		JLabel label_2 = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(31, 201, 88, 23);
		contentPane.add(label_2);
		
		JButton bt_refresh = new JButton("\uFF08\u70B9\u51FB\u5237\u65B0\uFF09");
		
		bt_refresh.setFont(new Font("黑体", Font.PLAIN, 12));
		bt_refresh.setBackground(Color.WHITE);
		bt_refresh.setBounds(281, 205, 107, 23);
		contentPane.add(bt_refresh);
		
		JLabel lb_code = new JLabel("");
		lb_code.setHorizontalAlignment(SwingConstants.CENTER);
		lb_code.setFont(new Font("方正舒体", Font.ITALIC, 18));
		lb_code.setBounds(201, 199, 88, 30);
		contentPane.add(lb_code);
		lb_code.setText(basic.Get_Random());
		
		tx_code = new JTextField();
		tx_code.setBounds(126, 201, 75, 23);
		contentPane.add(tx_code);
		tx_code.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8D26\u53F7\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(50, 96, 66, 29);
		contentPane.add(label_3);
		
		JLabel label_5 = new JLabel("\u6635\u79F0\uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 16));
		label_5.setBounds(50, 67, 66, 29);
		contentPane.add(label_5);
		
		tx_realyname = new JTextField();
		tx_realyname.setColumns(10);
		tx_realyname.setBounds(126, 67, 179, 23);
		contentPane.add(tx_realyname);
		
//		JLabel lblNewLabel_1 = new JLabel("\u6CE8\u518C\u7528\u6237\u7C7B\u578B\uFF1A\u666E\u901A\u7528\u6237");
//		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 13));
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setBounds(87, 250, 234, 23);
//		contentPane.add(lblNewLabel_1);
//		
		
		thread=new Thread(new Runnable() {
			public void run() {
				file.Add_NewUser(tx_name.getText()+"-"+tx_realyname.getText()+"-"+tx_passd.getText()+"-");
				lb_remind.setText("注册成功，5秒后自动返回");
				for(int i=0;i<5;i++){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					time--;
					lb_remind.setText("注册成功，"+time+"秒后自动返回");
				}
				dispose();
				Login.main(null);
			}
		});
		//注册按钮的点击事件
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String code=tx_code.getText();
				if(code.equals(lb_code.getText())){
					int result=basic.Register_Submit(tx_name.getText()+"-"+tx_realyname.getText()+"-"+tx_passd.getText()+"-"+tx_passd_again.getText()+"-");
					switch (result) {
					case 0:
						lb_remind.setText("请将信息填写完整");
						break;
					case 1:
						lb_remind.setText("两次密码不一致");
						break;
					case 2:
						lb_remind.setText("该账号已存在");
						break;
					case 3:
						lb_remind.setText("该昵称已被使用");
						break;
					case 4:
					
						thread.start();
						break;
					default:
						break;
					}
				}else{
					
					lb_remind.setText("验证码错误");
				}

			
			}
		});
		bt_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lb_code.setText(basic.Get_Random());
				lb_remind.setText("");
			}
		});
	}
	


}
