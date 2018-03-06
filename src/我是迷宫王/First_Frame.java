package 我是迷宫王;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import 功能部分.Basic_Function;
import 功能部分.File_Function;

import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class First_Frame extends JFrame {

	private JPanel contentPane;
	public JPanel panel_card = new JPanel();
	public ButtonGroup bt_group=new ButtonGroup();
	public CardLayout card_layout=new CardLayout();
	public JPanel panel_single=new JPanel();
	public JPanel panel_double=new JPanel();
	public JPanel panel_diymap=new JPanel();
	public JPanel panel_chellenge=new JPanel();
	public JPanel panel_check_rank=new JPanel();
	public First_Panel panel_first=new First_Panel();
	JLabel lb_username = new JLabel();
	private JTextField tx_single_score;
	private JTextField tx_single_rank;
	private JTextField tx_single_best;
	private JTextField tx_double_setround;
	private JTextField tx_double_round;
	public  JList<String> list_double=new JList<String>();
	public  JList<String> list_diy_finish=new JList<String>();
	public  JList<String> list_diy_continue=new JList<String>();
	private JTextField tx_diy_name;
	public  JList<String> list_chellenge_username=new JList<String>();
	public  JList<String> list_chellenge_mapname=new JList<String>();
	private JTextField tx_chellenge_map;
	private JTextField tx_chellenge_user;
	private JTextField tx_chelleng_width;
	private JTextField tx_chellenge_firstname;
	private JTextField tx_chellenge_firsttime;
	private JTable table_score;
	private JTable table_chellenge;
	public  int double_round;
	public  int[] double_width;
	public int now_round;
	private JTextField tx_chellenge_mytime;
	public Basic_Function basic=new Basic_Function();
	public File_Function file=new File_Function();
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					First_Frame frame = new First_Frame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public static void show(String name){
		First_Frame frame=new First_Frame();
		frame.lb_username.setText(name);
		frame.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public First_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 220));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 154, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u73A9\u5BB6\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 28, 47, 29);
		panel.add(lblNewLabel);
		lb_username.setFont(new Font("宋体", Font.BOLD, 13));
		lb_username.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lb_username.setBounds(53, 28, 91, 29);
		panel.add(lb_username);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 248, 220));
		panel_1.setBorder(new TitledBorder(null, "\u6A21\u5F0F\u9009\u62E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 81, 154, 290);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JToggleButton tgb_double = new JToggleButton("\u53CC\u4EBA\u6A21\u5F0F");
		tgb_double.setBackground(new Color(245, 245, 245));
		tgb_double.setBounds(10, 75, 140, 33);
		panel_1.add(tgb_double);
		
		JToggleButton tgb_diymap = new JToggleButton("\u8BBE\u8BA1\u8FF7\u5BAB");
		tgb_diymap.setBackground(new Color(245, 245, 245));
		tgb_diymap.setBounds(10, 118, 140, 33);
		panel_1.add(tgb_diymap);
		
		JToggleButton tgb_challenge = new JToggleButton("\u6311\u6218\u8FF7\u5BAB");
		tgb_challenge.setBackground(new Color(245, 245, 245));
		tgb_challenge.setBounds(10, 161, 140, 33);
		panel_1.add(tgb_challenge);
		
		JToggleButton tgb_check_rank = new JToggleButton("\u67E5\u770B\u699C\u5355");
		tgb_check_rank.setBackground(new Color(245, 245, 245));
		tgb_check_rank.setBounds(10, 204, 140, 33);
		panel_1.add(tgb_check_rank);
		
		JToggleButton tgb_return = new JToggleButton("\u8FD4\u56DE\u767B\u5F55");
		tgb_return.setBackground(new Color(245, 245, 245));
		tgb_return.setBounds(10, 247, 140, 33);
		panel_1.add(tgb_return);
		
		JToggleButton tgb_single = new JToggleButton("\u5355\u4EBA\u6A21\u5F0F");
		tgb_single.setBackground(new Color(245, 245, 245));
		tgb_single.setBounds(10, 32, 140, 33);
		panel_1.add(tgb_single);
		
		bt_group.add(tgb_single);
		bt_group.add(tgb_double);
		bt_group.add(tgb_challenge);
		bt_group.add(tgb_check_rank);
		bt_group.add(tgb_return);
		bt_group.add(tgb_diymap);
		
		
		panel_card.setBounds(155, 0, 575, 376);
		contentPane.add(panel_card);
		panel_card.setLayout(card_layout);
		panel_first.setBackground(Color.WHITE);
		panel_card.add("0",panel_first);
		panel_single.setBackground(new Color(255, 248, 220));
		panel_card.add("1",panel_single);
		panel_single.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u95EF\u5173\u6A21\u5F0F");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(20, 130, 115, 28);
		panel_single.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u6A21\u5F0F\u4ECB\u7ECD\uFF1A\u95EF\u5173\u6A21\u5F0F\u4E00\u51715\u5173\u3002\u96BE\u5EA6\u9010\u6E10\u9012\u589E");
		label_1.setFont(new Font("宋体", Font.BOLD, 15));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(20, 161, 526, 28);
		panel_single.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u6211\u7684\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 0, 575, 112);
		panel_single.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_2 = new JLabel("\u6211\u7684\u79EF\u5206\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("宋体", Font.BOLD, 15));
		label_2.setBounds(10, 20, 115, 28);
		panel_2.add(label_2);
		
		tx_single_score = new JTextField();
		tx_single_score.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_single_score.setEditable(false);
		tx_single_score.setHorizontalAlignment(SwingConstants.CENTER);
		tx_single_score.setBounds(88, 23, 84, 24);
		panel_2.add(tx_single_score);
		tx_single_score.setColumns(10);
		
		JLabel lb_newrank = new JLabel("\u79EF\u5206\u6392\u540D\uFF1A");
		lb_newrank.setHorizontalAlignment(SwingConstants.LEFT);
		lb_newrank.setFont(new Font("宋体", Font.BOLD, 15));
		lb_newrank.setBounds(10, 58, 115, 28);
		panel_2.add(lb_newrank);
		
		tx_single_rank = new JTextField();
		tx_single_rank.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_single_rank.setEditable(false);
		tx_single_rank.setHorizontalAlignment(SwingConstants.CENTER);
		tx_single_rank.setColumns(10);
		tx_single_rank.setBounds(88, 61, 84, 24);
		panel_2.add(tx_single_rank);
		
		JLabel label_4 = new JLabel("\u95EF\u5173\u6A21\u5F0F\u6700\u597D\u8BB0\u5F55\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("宋体", Font.BOLD, 15));
		label_4.setBounds(205, 20, 159, 28);
		panel_2.add(label_4);
		
		tx_single_best = new JTextField();
		tx_single_best.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_single_best.setEditable(false);
		tx_single_best.setHorizontalAlignment(SwingConstants.CENTER);
		tx_single_best.setColumns(10);
		tx_single_best.setBounds(360, 23, 84, 24);
		panel_2.add(tx_single_best);
		
		JLabel label_5 = new JLabel("\u81EA\u9009\u6A21\u5F0F");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("宋体", Font.BOLD, 15));
		label_5.setBounds(20, 233, 115, 28);
		panel_single.add(label_5);
		
		JLabel label_6 = new JLabel("\u6A21\u5F0F\u4ECB\u7ECD\uFF1A\u73A9\u5BB6\u81EA\u5DF1\u5B9A\u4E49\u8FF7\u5BAB\u5BBD\u5EA6\uFF0820~5\uFF09\uFF0C\u6570\u503C\u8D8A\u5C0F\uFF0C\u96BE\u5EA6\u8D8A\u5927");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("宋体", Font.BOLD, 15));
		label_6.setBounds(20, 271, 526, 28);
		panel_single.add(label_6);
		
		JButton bt_single_normal = new JButton("\u6311\u6218");
		bt_single_normal.setBackground(new Color(245, 255, 250));
		bt_single_normal.setBounds(370, 133, 115, 23);
		panel_single.add(bt_single_normal);
		
		JLabel lblNewLabel_2 = new JLabel("\u8FF7\u5BAB\u96BE\u5EA6\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2.setBounds(20, 309, 93, 23);
		panel_single.add(lblNewLabel_2);
		
		JButton bt_single_diy = new JButton("\u6311\u6218");
		bt_single_diy.setBackground(new Color(245, 255, 250));
		
		bt_single_diy.setBounds(370, 307, 115, 23);
		panel_single.add(bt_single_diy);
		
		JComboBox comb_single = new JComboBox();
		comb_single.setBackground(new Color(245, 255, 250));
		comb_single.setBounds(103, 310, 103, 22);
		panel_single.add(comb_single);
		panel_double.setBackground(new Color(255, 248, 220));
		panel_card.add("2",panel_double);
		panel_double.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u6A21\u5F0F\u4ECB\u7ECD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 0, 575, 117);
		panel_double.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("\u73A9\u5BB61\uFF1A\u901A\u8FC7\u4E0A\u4E0B\u5DE6\u53F3\u6309\u952E\u63A7\u5236\u4EBA\u7269\u7684\u4E0A\u4E0B\u5DE6\u53F3\u79FB\u52A8\r\n");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 23, 541, 28);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblwdsa = new JLabel("\u73A9\u5BB62\uFF1A\u901A\u8FC7w\uFF0Cd\uFF0Cs\uFF0Ca\u6309\u952E\u5206\u522B\u63A7\u5236\u4EBA\u7269\u4E0A\uFF0C\u4E0B\uFF0C\u5DE6\uFF0C\u53F3\u7684\u79FB\u52A8\r\n");
		lblwdsa.setFont(new Font("宋体", Font.BOLD, 13));
		lblwdsa.setBounds(10, 52, 541, 28);
		panel_3.add(lblwdsa);
		
		JLabel label_3 = new JLabel("\u73A9\u5BB6\u53EF\u4EE5\u81EA\u5B9A\u4E49\u6BD4\u8D5B\u7684\u5173\u5361\u6570\uFF0C\u8FF7\u5BAB\u96BE\u5EA6\uFF0C\u6570\u503C\u8D8A\u5C0F\uFF0C\u96BE\u5EA6\u8D8A\u5927\u3002\uFF08\u8303\u56F4\uFF1A5~20\uFF09\r\n");
		label_3.setFont(new Font("宋体", Font.BOLD, 13));
		label_3.setBounds(10, 79, 541, 28);
		panel_3.add(label_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5173\u5361\u6570\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 127, 67, 27);
		panel_double.add(lblNewLabel_4);
		
		tx_double_setround = new JTextField();
		tx_double_setround.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_double_setround.setHorizontalAlignment(SwingConstants.CENTER);
		tx_double_setround.setBounds(66, 130, 75, 21);
		panel_double.add(tx_double_setround);
		tx_double_setround.setColumns(10);
		
		JButton bt_double_setround = new JButton("\u8BBE\u7F6E\u96BE\u5EA6");
		bt_double_setround.setBackground(new Color(245, 255, 250));
		
		bt_double_setround.setBounds(177, 129, 109, 25);
		panel_double.add(bt_double_setround);
		
		JLabel lb_double_remindround = new JLabel("");
		lb_double_remindround.setHorizontalAlignment(SwingConstants.CENTER);
		lb_double_remindround.setFont(new Font("宋体", Font.BOLD, 13));
		lb_double_remindround.setBounds(296, 127, 258, 27);
		panel_double.add(lb_double_remindround);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u96BE\u5EA6\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(0, 176, 575, 156);
		panel_double.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("\u5173\u5361\uFF1A");
		lblNewLabel_5.setBounds(42, 33, 60, 27);
		panel_4.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 13));
		
		JLabel label_8 = new JLabel("\u96BE\u5EA6\uFF1A");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("宋体", Font.BOLD, 13));
		label_8.setBounds(42, 70, 60, 27);
		panel_4.add(label_8);
		
		tx_double_round = new JTextField();
		tx_double_round.setEditable(false);
		tx_double_round.setHorizontalAlignment(SwingConstants.CENTER);
		tx_double_round.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_double_round.setColumns(10);
		tx_double_round.setBounds(112, 35, 75, 21);
		panel_4.add(tx_double_round);
		
		JButton bt_double_add = new JButton("\u6DFB\u52A0");
		bt_double_add.setBackground(new Color(245, 255, 250));
		
		bt_double_add.setBounds(207, 53, 93, 23);
		panel_4.add(bt_double_add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(327, 22, 212, 124);
		scrollPane.add(list_double);
		scrollPane.setViewportView(list_double);
		DefaultListModel<String> model_double=new DefaultListModel<String>();
		list_double.setModel(model_double);
		panel_4.add(scrollPane);
		
		JButton bt_double_game = new JButton("\u5F00\u59CB\u6BD4\u8D5B");
		bt_double_game.setBackground(new Color(245, 255, 250));
		bt_double_game.setBounds(413, 339, 123, 27);
		panel_double.add(bt_double_game);
		panel_diymap.setBackground(new Color(255, 248, 220));
		panel_card.add("4",panel_diymap);
		panel_diymap.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5DF2\u8BBE\u8BA1\u8FF7\u5BAB", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(10, 10, 235, 356);
		panel_diymap.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 26, 215, 239);
		list_diy_finish.setFont(new Font("宋体", Font.BOLD, 13));
		scrollPane_2.add(list_diy_finish);
		scrollPane_2.setViewportView(list_diy_finish);
		DefaultListModel<String> model_diy_finish=new DefaultListModel<String>();
		list_diy_finish.setModel(model_diy_finish);
		panel_5.add(scrollPane_2);
		
		JButton bt_diy_finish_see = new JButton("\u67E5\u770B");
		bt_diy_finish_see.setBackground(new Color(245, 255, 250));
		
		bt_diy_finish_see.setBounds(10, 286, 93, 23);
		panel_5.add(bt_diy_finish_see);
		
		JButton bt_diy_finish_remove = new JButton("\u79FB\u9664");
		bt_diy_finish_remove.setBackground(new Color(245, 255, 250));
		
		bt_diy_finish_remove.setBounds(132, 286, 93, 23);
		panel_5.add(bt_diy_finish_remove);
		
		JLabel lb_diy_finish_remind = new JLabel("");
		lb_diy_finish_remind.setBounds(20, 319, 194, 27);
		panel_5.add(lb_diy_finish_remind);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "\u672A\u5B8C\u6210\u8FF7\u5BAB", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(255, 10, 310, 156);
		panel_diymap.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 21, 165, 125);
		list_diy_continue.setFont(new Font("宋体", Font.BOLD, 13));
		scrollPane_1.add(list_diy_continue);
		scrollPane_1.setViewportView(list_diy_continue);
		DefaultListModel<String> model_diy_continue=new DefaultListModel<String>();
		list_diy_continue.setModel(model_diy_continue);
		panel_6.add(scrollPane_1);
		
		JButton bt_diy_continue = new JButton("\u7EE7\u7EED\u8BBE\u8BA1");
		bt_diy_continue.setBackground(new Color(245, 255, 250));
		
		bt_diy_continue.setBounds(185, 33, 115, 25);
		panel_6.add(bt_diy_continue);
		
		JButton bt_diy_continue_remove = new JButton("\u79FB\u9664");
		bt_diy_continue_remove.setBackground(new Color(245, 255, 250));
		
		bt_diy_continue_remove.setBounds(185, 69, 115, 25);
		panel_6.add(bt_diy_continue_remove);
		
		JLabel lb_diy_continue_remind = new JLabel("");
		lb_diy_continue_remind.setBounds(185, 109, 115, 25);
		panel_6.add(lb_diy_continue_remind);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "\u65B0\u5EFA\u8FF7\u5BAB", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(255, 176, 310, 190);
		panel_diymap.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("\u8FF7\u5BAB\u540D\u79F0\uFF1A");
		lblNewLabel_6.setFont(new Font("宋体", Font.BOLD, 13));
		lblNewLabel_6.setBounds(10, 33, 100, 25);
		panel_7.add(lblNewLabel_6);
		
		tx_diy_name = new JTextField();
		tx_diy_name.setHorizontalAlignment(SwingConstants.CENTER);
		tx_diy_name.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_diy_name.setBounds(92, 35, 140, 25);
		panel_7.add(tx_diy_name);
		tx_diy_name.setColumns(10);
		
		JLabel label_7 = new JLabel("\u8FF7\u5BAB\u5BBD\u5EA6\uFF1A");
		label_7.setFont(new Font("宋体", Font.BOLD, 13));
		label_7.setBounds(10, 79, 100, 25);
		panel_7.add(label_7);
		
		JLabel label_9 = new JLabel("");
		label_9.setFont(new Font("宋体", Font.BOLD, 13));
		label_9.setBounds(200, 81, 100, 25);
		panel_7.add(label_9);
		
		JButton bt_diy_startdiy = new JButton("\u5F00\u59CB\u8BBE\u8BA1");
		bt_diy_startdiy.setBackground(new Color(245, 255, 250));
		
		bt_diy_startdiy.setBounds(80, 123, 125, 25);
		panel_7.add(bt_diy_startdiy);
		
		JLabel lb_diy_new = new JLabel("");
		lb_diy_new.setHorizontalAlignment(SwingConstants.CENTER);
		lb_diy_new.setBounds(35, 158, 243, 22);
		panel_7.add(lb_diy_new);
		
		JComboBox comb_diy = new JComboBox();
		comb_diy.setBackground(new Color(245, 255, 250));
		comb_diy.setFont(new Font("宋体", Font.PLAIN, 13));
		comb_diy.setBounds(92, 81, 98, 21);
		panel_7.add(comb_diy);
		panel_chellenge.setBackground(new Color(255, 248, 220));
		panel_card.add("5",panel_chellenge);
		panel_chellenge.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("\u8BBE\u8BA1\u8005");
		lblNewLabel_8.setFont(new Font("宋体", Font.BOLD, 13));
		lblNewLabel_8.setBounds(20, 25, 99, 26);
		panel_chellenge.add(lblNewLabel_8);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(20, 61, 187, 170);
		scrollPane_3.add(list_chellenge_username);
		scrollPane_3.setViewportView(list_chellenge_username);
		panel_chellenge.add(scrollPane_3);
		
		JLabel label_10 = new JLabel("\u8BE5\u8BBE\u8BA1\u8005\u8BBE\u8BA1\u7684\u8FF7\u5BAB");
		label_10.setFont(new Font("宋体", Font.BOLD, 13));
		label_10.setBounds(245, 25, 175, 26);
		panel_chellenge.add(label_10);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(245, 61, 320, 170);
		scrollPane_4.add(list_chellenge_mapname);
		scrollPane_4.setViewportView(list_chellenge_mapname);
		panel_chellenge.add(scrollPane_4);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "\u8FF7\u5BAB\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(0, 241, 565, 125);
		panel_chellenge.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("\u8FF7\u5BAB\u540D\u79F0\uFF1A");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(10, 25, 79, 24);
		panel_8.add(lblNewLabel_9);
		
		tx_chellenge_map = new JTextField();
		tx_chellenge_map.setEditable(false);
		tx_chellenge_map.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_chellenge_map.setHorizontalAlignment(SwingConstants.CENTER);
		tx_chellenge_map.setBounds(76, 26, 112, 22);
		panel_8.add(tx_chellenge_map);
		tx_chellenge_map.setColumns(10);
		
		JLabel label_11 = new JLabel("\u8BBE\u8BA1\u8005\uFF1A");
		label_11.setFont(new Font("宋体", Font.PLAIN, 13));
		label_11.setBounds(200, 25, 62, 24);
		panel_8.add(label_11);
		
		tx_chellenge_user = new JTextField();
		tx_chellenge_user.setHorizontalAlignment(SwingConstants.CENTER);
		tx_chellenge_user.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_chellenge_user.setEditable(false);
		tx_chellenge_user.setColumns(10);
		tx_chellenge_user.setBounds(261, 27, 116, 22);
		panel_8.add(tx_chellenge_user);
		
		JLabel label_12 = new JLabel("\u8FF7\u5BAB\u96BE\u5EA6\uFF1A");
		label_12.setFont(new Font("宋体", Font.PLAIN, 13));
		label_12.setBounds(387, 25, 73, 24);
		panel_8.add(label_12);
		
		tx_chelleng_width = new JTextField();
		tx_chelleng_width.setHorizontalAlignment(SwingConstants.CENTER);
		tx_chelleng_width.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_chelleng_width.setEditable(false);
		tx_chelleng_width.setColumns(10);
		tx_chelleng_width.setBounds(459, 27, 93, 22);
		panel_8.add(tx_chelleng_width);
		
		JLabel label_13 = new JLabel("\u8BB0\u5F55\u4FDD\u6301\u8005\uFF1A");
		label_13.setFont(new Font("宋体", Font.PLAIN, 13));
		label_13.setBounds(10, 64, 86, 24);
		panel_8.add(label_13);
		
		tx_chellenge_firstname = new JTextField();
		tx_chellenge_firstname.setHorizontalAlignment(SwingConstants.CENTER);
		tx_chellenge_firstname.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_chellenge_firstname.setEditable(false);
		tx_chellenge_firstname.setColumns(10);
		tx_chellenge_firstname.setBounds(89, 66, 112, 22);
		panel_8.add(tx_chellenge_firstname);
		
		JLabel label_14 = new JLabel("\u8BB0\u5F55\u7528\u65F6\uFF1A");
		label_14.setFont(new Font("宋体", Font.PLAIN, 13));
		label_14.setBounds(210, 64, 86, 24);
		panel_8.add(label_14);
		
		tx_chellenge_firsttime = new JTextField();
		tx_chellenge_firsttime.setHorizontalAlignment(SwingConstants.CENTER);
		tx_chellenge_firsttime.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_chellenge_firsttime.setEditable(false);
		tx_chellenge_firsttime.setColumns(10);
		tx_chellenge_firsttime.setBounds(273, 65, 79, 22);
		panel_8.add(tx_chellenge_firsttime);
		
		JButton bt_chellenge_start = new JButton("\u53D1\u8D77\u6311\u6218");
		bt_chellenge_start.setBackground(new Color(245, 255, 250));
		bt_chellenge_start.setBounds(425, 95, 135, 27);
		panel_8.add(bt_chellenge_start);
		
		JLabel label_16 = new JLabel("\u6211\u7684\u6700\u4F73\u8BB0\u5F55\uFF1A");
		label_16.setFont(new Font("宋体", Font.PLAIN, 13));
		label_16.setBounds(361, 64, 99, 24);
		panel_8.add(label_16);
		
		tx_chellenge_mytime = new JTextField();
		tx_chellenge_mytime.setHorizontalAlignment(SwingConstants.CENTER);
		tx_chellenge_mytime.setFont(new Font("宋体", Font.PLAIN, 13));
		tx_chellenge_mytime.setEditable(false);
		tx_chellenge_mytime.setColumns(10);
		tx_chellenge_mytime.setBounds(459, 65, 93, 22);
		panel_8.add(tx_chellenge_mytime);
		panel_check_rank.setBackground(new Color(255, 248, 220));
		panel_card.add("3",panel_check_rank);
		panel_check_rank.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("\u79EF\u5206\u699C");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("宋体", Font.BOLD, 13));
		lblNewLabel_11.setBounds(63, 21, 129, 26);
		panel_check_rank.add(lblNewLabel_11);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(24, 57, 216, 294);
		panel_check_rank.add(scrollPane_5);
		
		DefaultTableModel model_score;
		String[] check_score={"玩家姓名","积分"};
		String[][] check_score_information={};
		model_score=new DefaultTableModel(check_score_information, check_score);
		table_score = new JTable(model_score);
		table_score.setFont(new Font("宋体", Font.PLAIN, 14));
		scrollPane_5.setViewportView(table_score);
		table_score.setRowSorter(new TableRowSorter<DefaultTableModel>(model_score));
		
		JLabel label_15 = new JLabel("\u6311\u6218\u699C");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("宋体", Font.BOLD, 13));
		label_15.setBounds(357, 21, 129, 26);
		panel_check_rank.add(label_15);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(319, 57, 216, 294);
		panel_check_rank.add(scrollPane_6);
		
		DefaultTableModel model_chellenge;
		String[] check_chellenge={"玩家姓名","挑战成功次数"};
		model_chellenge=new DefaultTableModel(check_score_information, check_chellenge);
		table_chellenge = new JTable(model_chellenge);
		scrollPane_6.setViewportView(table_chellenge);
		table_chellenge.setRowSorter(new TableRowSorter<DefaultTableModel>(model_chellenge));
		table_chellenge.setFont(new Font("宋体", Font.PLAIN, 14));
		
		table_chellenge.setEnabled(false);
		table_score.setEnabled(false);
		
		list_chellenge_mapname.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_chellenge_username.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_diy_continue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_diy_finish.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_double.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tgb_single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=basic.Get_UserInformation(lb_username.getText());
				tx_single_score.setText(basic.Get_Text(s, 1));
				tx_single_rank.setText(basic.Get_Text(s, 2));
				tx_single_best.setText(basic.Get_Text(s, 3));
		//		tx_single_choose.setText("");
				card_layout.show(panel_card, "1");
		//		String 
//				Game_Frame.show_single();
//				First_Frame.this.setVisible(false);
			}
		});
		tgb_double.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card_layout.show(panel_card, "2");
//				list_double.removeAll();
//				Game_Frame.show_double();
//				First_Frame.this.setVisible(false);
				
			}
		});
		tgb_diymap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lb_diy_finish_remind.setText("");
				lb_diy_continue_remind.setText("");
				model_diy_continue.clear();
				model_diy_finish.clear();
				Vector<String> mapname=new Vector<String>();
				mapname=basic.Get_MyMap(lb_username.getText());
				mapname.remove(0);
				int i=0;
				for(;i<mapname.size();i++){
					if(!mapname.get(i).equals("finish")){
						model_diy_continue.addElement(mapname.get(i));
					}else{
						break;
					}
				}
				i++;
				for(;i<mapname.size();i++){
					model_diy_finish.addElement(mapname.get(i));
				}
				card_layout.show(panel_card, "4");
//				Game_Frame.show_diy();
//				First_Frame.this.setVisible(false);
				
			}
		});
		tgb_challenge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> user_order=new Vector<String>();
				//从文件中读取
				user_order=basic.Get_UserName(lb_username.getText());
	//			user_order.add("maytest");
				list_chellenge_username.setListData(user_order);
				bt_chellenge_start.setEnabled(false);
				card_layout.show(panel_card, "5");
			}
		});
		tgb_check_rank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> user_get=new Vector<String>();
				int rows=table_chellenge.getRowCount();
				for(int i=0;i<rows;i++){
					model_score.removeRow(0);
					model_chellenge.removeRow(0);
				}
				user_get=file.file_operation.read("users_score");
				for(int i=0;i<user_get.size();i++){
					String[] t=user_get.get(i).split("-");
					model_score.addRow(t);
				}
				user_get.clear();
				user_get=file.file_operation.read("users_successtimes");
				for(int i=0;i<user_get.size();i++){
					String[] t=user_get.get(i).split("-");
					model_chellenge.addRow(t);
				}
				card_layout.show(panel_card, "3");
			}
		});
		tgb_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login.main(null);
			//	dispose();
				
			}
		});
		
		////////////////////////////////////单人模式
		bt_single_normal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Game_Frame.show_single(lb_username.getText());
			  
			}
		});
		bt_single_diy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num=Integer.parseInt(comb_single.getSelectedItem().toString());
				Game_Frame.show_single_choose(num,lb_username.getText());
			}
		});

		///////////////////////////////////////////////////双人模式
		bt_double_add.setEnabled(false);
		
		JComboBox comb_double_difficult = new JComboBox();
		comb_double_difficult.setBackground(new Color(245, 255, 250));
		comb_double_difficult.setBounds(112, 73, 75, 21);
		panel_4.add(comb_double_difficult);
		for(int i=5;i<21;i++){
			comb_double_difficult.addItem(i);
		}
		for(int i=5;i<21;i++){
			comb_single.addItem(i);
		}
		for(int i=5;i<21;i++){
			comb_diy.addItem(i);
		}
		bt_double_setround.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=tx_double_setround.getText();
				bt_double_add.setEnabled(false);
				if(s.equals("")){
					lb_double_remindround.setText("请先填写关卡数");
				}else{
					try {
						double_round=Integer.parseInt(s);
					} catch (Exception e2) {
						// TODO: handle exception
						lb_double_remindround.setText("请填写阿拉伯数字");
					}
					if(double_round<1){
						lb_double_remindround.setText("请输入有效数值");
					}else{
						
						lb_double_remindround.setText("");
						bt_double_add.setEnabled(true);
					//	bt_double_change.setEnabled(true);
						
						double_width=new int[double_round];
						tx_double_round.setText("1");
						now_round=0;
						model_double.clear();
					}
					
		//			list_double.setModel(dlm_double);
				}
				
			}
		});
		bt_double_game.setEnabled(false);
		bt_double_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   String single_row="关卡："+tx_double_round.getText()+"           难度："+comb_double_difficult.getSelectedItem().toString();
			   double_width[now_round]=Integer.parseInt(comb_double_difficult.getSelectedItem().toString());
			   model_double.addElement(single_row);
			   now_round++;
			   tx_double_round.setText((now_round+1)+"");
			   if(now_round==double_round){
				   tx_double_round.setText("");
				   bt_double_add.setEnabled(false);
				   bt_double_game.setEnabled(true);
			   }
			//   list_double.setModel(dlm_double);
			}
		});
		
		bt_double_game.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				
//				model_double.remove(list_double.getSelectedIndex());
				Game_Frame.show_double(double_width, double_round,lb_username.getText());
				dispose();
	//			System.out.println(list_double.getSelectedValue());
			}
		});
		
		///////////////////////////////////////////////////////////////////diy
		bt_diy_startdiy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String diy_width=comb_diy.getSelectedItem().toString();
				String diymap_s=tx_diy_name.getText()+"-"+lb_username.getText()+"-"+diy_width+"-"+"未完成"+"-";
				
				if(tx_diy_name.getText().equals("")||diy_width.equals("")){
					lb_diy_new.setText("请将信息填写完整");
				}else if(!basic.Build_NewMap(lb_username.getText()+"-"+tx_diy_name.getText()+"-")){
					lb_diy_new.setText("该迷宫已存在");
				}else{
					Game_Frame.show_newOrcondiy(diymap_s,1,lb_username.getText());
					dispose();
				}
			}
		});
		
		bt_diy_finish_see.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String diy_finish_select=list_diy_finish.getSelectedValue();
				if(diy_finish_select!=null){
					lb_diy_finish_remind.setText("");
					String diy_map=diy_finish_select.substring(0, diy_finish_select.indexOf(" "))+"-"+lb_username.getText()+"-"+diy_finish_select.substring(diy_finish_select.lastIndexOf(" ")+1)+"-已完成-";//文件中读取
				//	System.out.println(diy_map);
	
					Game_Frame.show_finishdiy(diy_map,lb_username.getText());
					dispose();
				}else{
					lb_diy_finish_remind.setText("请选择操作对象");
				}
			}
		});
		
		bt_diy_finish_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String diy_finish_select=list_diy_finish.getSelectedValue();
				if(diy_finish_select!=null){
					lb_diy_finish_remind.setText("");
					model_diy_finish.remove(list_diy_finish.getSelectedIndex());
					String path=lb_username.getText()+"-finish-"+diy_finish_select.substring(0,diy_finish_select.indexOf(" "))+"-";
					String mapwidth=diy_finish_select.substring(diy_finish_select.lastIndexOf(" ")+1);
				//在文件中删除，并且删除该文件	
					file.Delete_Map(path,mapwidth);
				}else{
					lb_diy_finish_remind.setText("请选择操作对象");
				}
			
			}
		});
		
		bt_diy_continue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String diy_continue_select=list_diy_continue.getSelectedValue();
				if(diy_continue_select!=null){
					lb_diy_continue_remind.setText("");
					String diy_map=diy_continue_select.substring(0,diy_continue_select.indexOf(" "))+"-"+lb_username.getText()+"-"+diy_continue_select.substring(diy_continue_select.lastIndexOf(" ")+1)+"-未完成-";//文件中读取
					Game_Frame.show_newOrcondiy(diy_map,2,lb_username.getText());
					dispose();
				}else{
					lb_diy_continue_remind.setText("请选择操作对象");
				}
			
			}
		});
		
		bt_diy_continue_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String diy_continue_select=list_diy_continue.getSelectedValue();
				if(diy_continue_select!=null){
					lb_diy_continue_remind.setText("");
					model_diy_continue.remove(list_diy_continue.getSelectedIndex());
					String path=lb_username.getText()+"-continue-"+diy_continue_select.substring(0, diy_continue_select.indexOf(" "))+"-";
					//从文件中删除和删除文件
					String mapwidth=diy_continue_select.substring(diy_continue_select.lastIndexOf(" ")+1);
					file.Delete_Map(path,mapwidth);
				}else{
					lb_diy_continue_remind.setText("请选择操作对象");
				}
				
			}
		});
		
		///////////////////////////////////////////////////////////////////挑战模式
		
		list_chellenge_username.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO 自动生成的方法存根
				if(arg0.getValueIsAdjusting()){
					Vector<String> diymap=new Vector<String>();
					String name=list_chellenge_username.getSelectedValue();
					diymap=basic.Get_MyMap(name);
					int size=diymap.size();
					for(int i=0;i<size;i++){
						if(diymap.get(0).equals("finish")){
							diymap.remove(0);
							break;
						}else{
							diymap.remove(0);
						}
					}
			//		diymap.add("test1");
					//diymap=文件中读取
					list_chellenge_mapname.setListData(diymap);
					bt_chellenge_start.setEnabled(false);
				}
				
			}
		});
		
		list_chellenge_mapname.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO 自动生成的方法存根
				if(arg0.getValueIsAdjusting()){
					bt_chellenge_start.setEnabled(true);
					String select=lb_username.getText()+"-"+list_chellenge_username.getSelectedValue()+"-"+list_chellenge_mapname.getSelectedValue().substring(0,list_chellenge_mapname.getSelectedValue().indexOf(" "))+"-";
					String map_information=basic.Get_MapInformation(select);//文件中获得
					String[] chellenge_set_tx=map_information.split("-");
					tx_chellenge_map.setText(chellenge_set_tx[0]);
					tx_chellenge_user.setText(chellenge_set_tx[1]);
					tx_chelleng_width.setText(chellenge_set_tx[2]);
					tx_chellenge_firstname.setText(chellenge_set_tx[3]);
					tx_chellenge_firsttime.setText(chellenge_set_tx[4]);
					tx_chellenge_mytime.setText(chellenge_set_tx[5]);
					
				}
			}
		});
		
		bt_chellenge_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				String chellenge_map=tx_chellenge_user.getText()+"-"+tx_chellenge_map.getText()+"-"+tx_chelleng_width.getText()+"-";
				int mapwidth=Integer.parseInt(basic.Get_Text(chellenge_map, 3));
		//		System.out.println(chellenge_map+"========");
				Game_Frame.show_chellenge(chellenge_map, mapwidth,lb_username.getText());
				dispose();
			}
		});
	}
}
