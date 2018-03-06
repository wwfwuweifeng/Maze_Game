package 我是迷宫王;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import 功能部分.Basic_Function;
import 功能部分.File_Function;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class Game_Frame extends JFrame {

	public JPanel panel_mother=new JPanel();
	public JPanel panel_single=new JPanel();
	public Single_Panel panel_single_game=new Single_Panel();
	public JPanel panel_single_control=new JPanel();
	public JPanel panel_double=new JPanel();
	public JPanel panel_double_control=new JPanel();
	public Double_Panel panel_double_game=new Double_Panel();
	public CardLayout card=new CardLayout();
	public Thread thread_single;
	public Thread thread_single_next;
	public Thread thread_double_next;
	public Basic_Function basic=new Basic_Function();
	public File_Function file=new File_Function();
	public boolean single_stoporcontine=false;
	public int single_first=1;
	public JPanel panel_diy=new JPanel();
	public JPanel panel_diy_control=new JPanel();
	public Diy_Panel panel_diy_game=new Diy_Panel();
	public int diy_button_flag;
	public int flag_seemap=1;
	public int flag_diy_dfs=0;
	public int flag_diy_success=0;
	public int flag_single_score=1;
	public int[] double_width;
	public int double_now;
	public int flag_double=1;
	public int flag_double_paint=0;
	public int flag_double_set=1;
	public int flag_P1=1;
	public int flag_P2=1;
	public int diy_width;
	public JLabel lb_diy_mapname = new JLabel();
	public JLabel lb_diy_peoplename = new JLabel();
	public JLabel lb_diy_size = new JLabel();
	public JLabel lb_diy_state = new JLabel();
	public JButton bt_diy_start;
	public JButton bt_diy_seemap;
	public JButton bt_diy_shortsave;
	public JButton bt_diy_prim;
	public JButton bt_diy_save;
	public String chellenge_map;
	public String username;
	public double alltime=0;
	public int first_set=1;
//	public int chellenge_width;
	

	/**
	 * Launch the application.
	 */
	
	public static void show_single(String name){
		Game_Frame frame = new Game_Frame();
		frame.setVisible(true);
		frame.username=name;
		frame.thread_single_next.start();
		frame.card.show(frame.panel_mother,"1");
	}
	
	public static void show_single_choose(int num,String name){
		Game_Frame frame=new Game_Frame();
		frame.setVisible(true);
		frame.username=name;
		frame.panel_single_game.diy_or_prim=2;
		frame.panel_single_game.width=num;
		frame.thread_single_next.start();
		frame.card.show(frame.panel_mother,"1");
	}
	public static void show_double(int[] width,int round,String name){
		Game_Frame frame = new Game_Frame();
		frame.setVisible(true);
		frame.username=name;
		frame.double_now=0;
		frame.double_width=new int[round];
		frame.double_width=width;
		frame.card.show(frame.panel_mother,"2");
	//	frame.panel_double_game.repaint();
	}
	public static void show_newOrcondiy(String diy,int select_diy,String name){//1表示是新建的，2表示是继续的
		Game_Frame frame = new Game_Frame();
		frame.setVisible(true);
		frame.username=name;
	//	frame.panel_diy_game.Set_TGB(20);
		frame.card.show(frame.panel_mother,"3");
		frame.diy_button_flag=1;
		String[] diy_s=diy.split("-");
		frame.lb_diy_mapname.setText(diy_s[0]);
		frame.lb_diy_peoplename.setText(diy_s[1]);
		frame.diy_width=Integer.parseInt(diy_s[2]);
		frame.lb_diy_size.setText(frame.basic.Get_LbSize(frame.diy_width));
		frame.lb_diy_state.setText(diy_s[3]);
		frame.diy_button_flag=select_diy;
		frame.panel_diy_game.sound.loop();
	
	//	frame.panel_double_game.repaint();
	}
	
	public static void show_finishdiy(String diy,String name){
		Game_Frame frame = new Game_Frame();
		frame.setVisible(true);
	//	frame.panel_diy_game.Set_TGB(20);
		frame.username=name;
		frame.card.show(frame.panel_mother,"3");
		String[] diy_s=diy.split("-");
		frame.lb_diy_mapname.setText(diy_s[0]);
		frame.lb_diy_peoplename.setText(diy_s[1]);
		frame.diy_width=Integer.parseInt(diy_s[2]);
		frame.lb_diy_size.setText(frame.basic.Get_LbSize(frame.diy_width));
		frame.lb_diy_state.setText(diy_s[3]);
		frame.Diy_SetBt();
		String s=diy_s[1]+"-"+diy_s[0]+"-"+diy_s[2]+"-";
		frame.panel_diy_game.See_Finish_Map(frame.basic.Get_DiyMap(s,1),frame.diy_width);
	}
	
	public static void show_chellenge(String select,int select_width,String name){
		Game_Frame frame=new Game_Frame();
		frame.setVisible(true);
		frame.username=name;
		frame.panel_single_game.diy_or_prim=1;
		frame.panel_single_game.width=select_width;
		frame.chellenge_map=select;
		frame.thread_single_next.start();
		frame.card.show(frame.panel_mother,"1");
	}
	/**
	 * Create the frame.
	 */
	public Game_Frame() {
	//	setUndecorated(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 550);
		panel_mother.setLayout(card);
		setContentPane(panel_mother);
		panel_mother.add("1",panel_single);
		panel_single.setLayout(null);
		setResizable(false);
		panel_single_control.setBackground(new Color(240, 230, 140));
		panel_single_control.setBorder(new TitledBorder(null, "\u64CD\u4F5C\u533A\u57DF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_single_control.setBounds(5, 5, 200,520);
		panel_single.add(panel_single_control);
		panel_single_control.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u5173\u5361\u7528\u65F6");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(31, 90, 137, 27);
		panel_single_control.add(lblNewLabel);
		
		JLabel lb_single_time = new JLabel("0.0");
		lb_single_time.setFont(new Font("宋体", Font.BOLD, 23));
		lb_single_time.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_single_time.setBounds(10, 127, 103, 37);
		panel_single_control.add(lb_single_time);
		
		JLabel label_1 = new JLabel("\u7D2F\u8BA1\u5F97\u5206");
		label_1.setFont(new Font("宋体", Font.BOLD, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(40, 174, 117, 27);
		panel_single_control.add(label_1);
		
		JLabel lb_single_score = new JLabel("0");
		lb_single_score.setHorizontalAlignment(SwingConstants.CENTER);
		lb_single_score.setFont(new Font("宋体", Font.BOLD, 23));
		lb_single_score.setBounds(40, 211, 117, 37);
		panel_single_control.add(lb_single_score);
		
		JLabel label_3 = new JLabel("\u5F53\u524D\u5173\u5361");
		label_3.setFont(new Font("宋体", Font.BOLD, 14));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(40, 28, 117, 27);
		panel_single_control.add(label_3);
		
		JLabel lb_single_round = new JLabel("1");
		lb_single_round.setFont(new Font("宋体", Font.BOLD, 23));
		lb_single_round.setHorizontalAlignment(SwingConstants.CENTER);
		lb_single_round.setBounds(40, 53, 117, 37);
		panel_single_control.add(lb_single_round);
		
		JButton bt_single_start = new JButton("\u5F00\u59CB\u6E38\u620F");

		bt_single_start.setFont(new Font("宋体", Font.BOLD, 14));
		bt_single_start.setBounds(31, 258, 137, 37);
		panel_single_control.add(bt_single_start);
		
		JButton bt_single_stop = new JButton("\u7ED3\u675F\u6E38\u620F");
	
		bt_single_stop.setFont(new Font("宋体", Font.BOLD, 14));
		bt_single_stop.setBounds(31, 397, 137, 37);
		panel_single_control.add(bt_single_stop);
		
		JButton bt_single_return = new JButton("\u8FD4\u56DE\u754C\u9762");
		bt_single_return.setFont(new Font("宋体", Font.BOLD, 14));
		bt_single_return.setBounds(31, 444, 137, 37);
		panel_single_control.add(bt_single_return);
		
		JButton bt_single_next = new JButton("\u4E0B\u4E00\u5173");
		
		bt_single_next.setFont(new Font("宋体", Font.BOLD, 14));
		bt_single_next.setBounds(31, 350, 137, 37);
		panel_single_control.add(bt_single_next);
		panel_single_game.setBounds(210,5,820,520);
		panel_single.add(panel_single_game);
		panel_single_game.setLayout(null);
		bt_single_next.setEnabled(false);
		bt_single_stop.setEnabled(false);
		
		JLabel label = new JLabel("\u79D2");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("宋体", Font.BOLD, 23));
		label.setBounds(120, 127, 56, 37);
		panel_single_control.add(label);
		
		JButton bt_single_see = new JButton("\u67E5\u770B\u6B63\u786E\u8DEF\u7EBF");
		bt_single_see.setEnabled(false);
		bt_single_see.setFont(new Font("宋体", Font.BOLD, 14));
		bt_single_see.setBounds(31, 305, 137, 37);
		panel_single_control.add(bt_single_see);
		thread_single_next=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					synchronized(this){	
						if(panel_single_game.success==1&&panel_single_game.round==panel_single_game.difficult.length&&first_set==1){
							alltime=alltime+Double.parseDouble(lb_single_time.getText());
							single_stoporcontine=false;
							flag_single_score--;
							first_set--;
							file.Updata_Single_Time(username+"-"+alltime+"-");
							lb_single_score.setText(basic.Get_LbScore(panel_single_game.width,lb_single_score.getText(),lb_single_time.getText()));
						}
						if(panel_single_game.success==1&&panel_single_game.round<panel_single_game.difficult.length&&panel_single_game.diy_or_prim==0&&flag_single_score==1){
						bt_single_next.setEnabled(true);
						single_stoporcontine=false;
						flag_single_score--;
						alltime=alltime+Double.parseDouble(lb_single_time.getText());
						lb_single_score.setText(basic.Get_LbScore(panel_single_game.width,lb_single_score.getText(),lb_single_time.getText()));
					}else if((panel_single_game.diy_or_prim==1||panel_single_game.diy_or_prim==2)&&panel_single_game.success==1&&flag_single_score==1){
						bt_single_stop.setEnabled(false);
						single_stoporcontine=false;
						flag_single_score--;
						lb_single_score.setText(basic.Get_LbScore(panel_single_game.width,lb_single_score.getText(),lb_single_time.getText()));
						file.Updata_UserScore(username+"-"+lb_single_score.getText()+"-");
						if(panel_single_game.diy_or_prim==1){
							file.Upada_Successtimes(username);
							String s=basic.Get_Text(chellenge_map, 1)+"-"+basic.Get_Text(chellenge_map, 2)+"-"+username+"-"+lb_single_time.getText()+"-";
							file.Updata_Map_Time(s);
						}
					}
					}
				}
				
			}
		});
	//  thread_single_next.start();
		thread_single=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				while(true){
					synchronized(this){
						if(single_stoporcontine){
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							lb_single_time.setText(basic.Get_LbTime(lb_single_time.getText()));
						}
					}
				}
			}
		});
			
		
		bt_single_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(panel_single_game.diy_or_prim==0){
					panel_single_game.PaintMap(panel_single_game.difficult[panel_single_game.round]);
				}else if(panel_single_game.diy_or_prim==1){//chellenge
					panel_single_game.Diy_Chellenge_Map(basic.Get_DiyMap(chellenge_map,1));
				}else if(panel_single_game.diy_or_prim==2){
					panel_single_game.Paint_Choose_Map();
				}
				lb_single_time.setText("0.0");
				single_stoporcontine=true;
				bt_single_start.setEnabled(false);
				bt_single_stop.setEnabled(true);
				bt_single_see.setEnabled(true);
				Game_Frame.this.requestFocus();
				if(single_first==1){
					Game_Frame.this.addKeyListener(panel_single_game);
					thread_single.start();
					single_first--;
					}
//				}else{
//					try {
//						thread_single.wait();
//					} catch (InterruptedException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//					thread_single.notify();
//				}
			}
		});
		
		bt_single_see.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game_Frame.this.removeKeyListener(panel_single_game);
				panel_single_game.thread_1.start();
				
			}
		});
		bt_single_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(panel_single_game.diy_or_prim!=1){
				single_stoporcontine=false;
				bt_single_start.setEnabled(true);
				bt_single_stop.setEnabled(false);
				panel_single_game.success=0;
				panel_single_game.round=0;
				bt_single_next.setEnabled(false);
				}else{//diy模式,得遮住迷宫,直接利用一开始的图片就可以
					single_stoporcontine=false;
					panel_single_game.first_show=1;
					bt_single_start.setEnabled(true);
					panel_single_game.repaint();
				}
				if(panel_single_game.diy_or_prim==0){
					file.Updata_UserScore(username+"-"+lb_single_score.getText()+"-");
					lb_single_score.setText("0");
				}
				panel_single_game.sound.stop();
				panel_single_game.sound_success.stop();
		//		lb_single_time.setText("0.0");
			}
		});
		bt_single_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_single_game.PaintMap(panel_single_game.difficult[panel_single_game.round]);
				Game_Frame.this.requestFocus();
				lb_single_round.setText((panel_single_game.round+1)+"");
				lb_single_time.setText("0.0");
				single_stoporcontine=true;
				flag_single_score=1;
				bt_single_next.setEnabled(false);
			}
		});
		bt_single_return.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if(panel_single_game.diy_or_prim==0){
					file.Updata_UserScore(username+"-"+lb_single_score.getText()+"-");
				}
				panel_single_game.sound.stop();
				panel_single_game.sound_success.stop();
				First_Frame.show(username);
				dispose();
			}
		});
		panel_double_control.setBackground(new Color(240, 230, 140));
		panel_double_control.setBorder(new TitledBorder(null, "\u529F\u80FD\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
///////////////////////////////////////////////////////////双人区域
		panel_double_control.setBounds(5, 5, 200,520);
		panel_double.add(panel_double_control);
		panel_double.setLayout(null);
		panel_double_control.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "\u73A9\u5BB6\u6BD4\u5206", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 26, 180, 139);
		panel_double_control.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u73A9\u5BB61    \u73A9\u5BB62");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 23, 160, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lb_double_p1 = new JLabel("0");
		lb_double_p1.setFont(new Font("宋体", Font.BOLD, 24));
		lb_double_p1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_double_p1.setBounds(0, 61, 79, 55);
		panel.add(lb_double_p1);
		
		JLabel lb_double_p2 = new JLabel("0");
		lb_double_p2.setHorizontalAlignment(SwingConstants.CENTER);
		lb_double_p2.setFont(new Font("宋体", Font.BOLD, 24));
		lb_double_p2.setBounds(103, 61, 67, 55);
		panel.add(lb_double_p2);
		
		JLabel label_4 = new JLabel(":");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.BOLD, 24));
		label_4.setBounds(74, 61, 33, 55);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u5F53\u524D\u5173\u5361\u96BE\u5EA6:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("宋体", Font.BOLD, 14));
		label_5.setBounds(10, 179, 98, 27);
		panel_double_control.add(label_5);
		
		JLabel lb_double_round = new JLabel("");
		lb_double_round.setHorizontalAlignment(SwingConstants.LEFT);
		lb_double_round.setFont(new Font("宋体", Font.BOLD, 14));
		lb_double_round.setBounds(118, 179, 72, 27);
		panel_double_control.add(lb_double_round);
		
		JLabel label_7 = new JLabel("\u5F53\u524D\u5173\u5361\u5206\u503C:");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("宋体", Font.BOLD, 14));
		label_7.setBounds(10, 216, 98, 27);
		panel_double_control.add(label_7);
		
		JLabel lb_double_score = new JLabel("");
		lb_double_score.setHorizontalAlignment(SwingConstants.LEFT);
		lb_double_score.setFont(new Font("宋体", Font.BOLD, 14));
		lb_double_score.setBounds(118, 216, 72, 27);
		panel_double_control.add(lb_double_score);
		
		JLabel label_9 = new JLabel("\u5269\u4F59\u5173\u5361\u6570:");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setFont(new Font("宋体", Font.BOLD, 14));
		label_9.setBounds(10, 253, 98, 27);
		panel_double_control.add(label_9);
		
		JLabel lb_double_remind = new JLabel("");
		lb_double_remind.setHorizontalAlignment(SwingConstants.LEFT);
		lb_double_remind.setFont(new Font("宋体", Font.BOLD, 14));
		lb_double_remind.setBounds(118, 253, 72, 27);
		panel_double_control.add(lb_double_remind);
		
		JButton bt_double_start = new JButton("\u5F00\u59CB\u6E38\u620F");
		bt_double_start.setBounds(10, 302, 180, 39);
		panel_double_control.add(bt_double_start);
		
		JButton bt_double_next = new JButton("\u4E0B\u4E00\u5173");
		bt_double_next.setBounds(10, 351, 180, 39);
		panel_double_control.add(bt_double_next);
		
		JButton bt_double_finish = new JButton("\u7ED3\u675F\u6BD4\u8D5B");
		
		bt_double_finish.setBounds(10, 400, 180, 39);
		panel_double_control.add(bt_double_finish);
		
		JButton bt_double_return = new JButton("\u8FD4\u56DE\u754C\u9762");
		bt_double_return.setBounds(10, 449, 180, 39);
		panel_double_control.add(bt_double_return);
		panel_mother.add("2",panel_double);
		
		panel_double_game.setBounds(210,5,820,520);
		panel_double.add(panel_double_game);
		bt_double_next.setEnabled(false);
		bt_double_finish.setEnabled(false);
		
		thread_double_next=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				while(true){
					synchronized(this){
						
						if((panel_double_game.success_p1==1||panel_double_game.success_p2==1)&&flag_double_set==1){
							if(panel_double_game.success_p1==1&&flag_P1==1){
								lb_double_p1.setText(basic.Get_P1orP2Score(lb_double_score.getText(), lb_double_p1.getText()));
							//	panel_double_game.repaint();
								flag_P1--;
								
							}else if(panel_double_game.success_p2==1&&flag_P2==1){
								System.out.println("testP2");
								lb_double_p2.setText(basic.Get_P1orP2Score(lb_double_score.getText(),lb_double_p2.getText()));
							//	panel_double_game.repaint();
								flag_P2--;
							}
							if(double_now<double_width.length-1){
								bt_double_next.setEnabled(true);
							}
							flag_double_set--;
						}
						if((panel_double_game.success_p1==1||panel_double_game.success_p2==1)&&double_now==double_width.length-1&&flag_double_paint==1){
							//双人模式结束了
							flag_double_paint--;
							if(Integer.parseInt(lb_double_p1.getText())>Integer.parseInt(lb_double_p2.getText())){
								panel_double_game.finish_success=1;
							}else if(Integer.parseInt(lb_double_p1.getText())<Integer.parseInt(lb_double_p2.getText())){
								panel_double_game.finish_success=2;
							}else{
								panel_double_game.finish_success=3;
							}
							bt_double_start.setText("重新开始");
							bt_double_start.setEnabled(true);
							bt_double_finish.setEnabled(false);
							panel_double_game.repaint();
						}
								
					}
				}
			}
		});
		
		bt_double_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bt_double_start.setEnabled(false);
				double_now=0;
				lb_double_p1.setText("0");
				lb_double_p2.setText("0");
				panel_double_game.Paint_Map(double_width[double_now]);
				lb_double_round.setText(basic.Get_LbSize(double_width[double_now]));
				lb_double_remind.setText(double_width.length-double_now-1+"");
				lb_double_score.setText(basic.Get_LbLeveScore(double_width[double_now]));
				Game_Frame.this.requestFocus();
				bt_double_finish.setEnabled(true);
				flag_double_paint=0;
				flag_double_set=1;
				flag_P1=1;
				flag_P2=1;
				if(flag_double==1){
					thread_double_next.start();
					Game_Frame.this.addKeyListener(panel_double_game);
					flag_double--;
				}
				
			}
		});
		bt_double_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				bt_double_next.setEnabled(false);
				double_now++;
				panel_double_game.Paint_Map(double_width[double_now]);
				flag_double_set=1;
				if(double_now==double_width.length-1){
					flag_double_paint=1;
				}
				if(flag_double_set==1){
					System.out.println(flag_P1);
					flag_P1=1;
					flag_P2=1;
					System.out.println(flag_P2);
				}
				lb_double_round.setText(basic.Get_LbSize(double_width[double_now]));
				lb_double_remind.setText(double_width.length-double_now-1+"");
				lb_double_score.setText(basic.Get_LbLeveScore(double_width[double_now]));
				Game_Frame.this.requestFocus();
			}
		});
		
		bt_double_finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  
				bt_double_start.setText("重新开始");
				bt_double_start.setEnabled(true);
				bt_double_finish.setEnabled(false);
				panel_double_game.first_show=1;
				panel_double_game.restart=1;
				panel_double_game.repaint();
				panel_double_game.sound.stop();
				panel_double_game.sound_success.stop();
			}
		});
		
		bt_double_return.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				panel_double_game.sound.stop();
				panel_double_game.sound_success.stop();
				First_Frame.show(username);;
				dispose();
			}
		});
		panel_diy_control.setBackground(new Color(240, 230, 140));
		panel_diy_control.setBorder(new TitledBorder(null, "\u529F\u80FD\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
//////////////////////////////////////////////、、、、、、、、、、、、、、、、、、、、diy部分
		panel_diy_control.setBounds(5, 5, 200,520);
		panel_diy_game.setBounds(210,5,820,520);
		panel_mother.add("3",panel_diy);
		panel_diy.setLayout(null);
		panel_diy_control.setLayout(null);
		panel_diy.add(panel_diy_control);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8FF7\u5BAB\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 27, 180, 178);
		panel_diy_control.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u540D\u79F0\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(10, 26, 51, 30);
		panel_1.add(lblNewLabel_2);
		
		
		lb_diy_mapname.setHorizontalAlignment(SwingConstants.LEFT);
		lb_diy_mapname.setBounds(71, 26, 100, 30);
		panel_1.add(lb_diy_mapname);
		
		JLabel label_6 = new JLabel("\u8BBE\u8BA1\u5E08\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setBounds(10, 56, 60, 30);
		panel_1.add(label_6);
		
		lb_diy_peoplename.setHorizontalAlignment(SwingConstants.LEFT);
		lb_diy_peoplename.setBounds(71, 56, 100, 30);
		panel_1.add(lb_diy_peoplename);
		
		JLabel label_10 = new JLabel("\u89C4\u683C\uFF1A");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setBounds(10, 88, 60, 30);
		panel_1.add(label_10);
		
		
		lb_diy_size.setHorizontalAlignment(SwingConstants.LEFT);
		lb_diy_size.setBounds(71, 88, 90, 30);
		panel_1.add(lb_diy_size);
		
		
		lb_diy_state.setHorizontalAlignment(SwingConstants.LEFT);
		lb_diy_state.setBounds(71, 124, 90, 30);
		panel_1.add(lb_diy_state);
		
		JLabel label_8 = new JLabel("\u72B6\u6001\uFF1A");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setBounds(10, 124, 60, 30);
		panel_1.add(label_8);
		
		bt_diy_start = new JButton("\u5F00\u59CB\u8BBE\u8BA1");

		bt_diy_start.setBounds(10, 215, 180, 39);
		panel_diy_control.add(bt_diy_start);
		
		bt_diy_seemap = new JButton("\u67E5\u770B\u5730\u56FE");
		bt_diy_seemap.setBounds(10, 264, 180, 39);
		panel_diy_control.add(bt_diy_seemap);
		
		 bt_diy_shortsave = new JButton("\u6682\u65F6\u4FDD\u5B58");
		bt_diy_shortsave.setBounds(10, 313, 180, 39);
		panel_diy_control.add(bt_diy_shortsave);
		
		 bt_diy_prim = new JButton("\u7B97\u6CD5\u5B8C\u5584\u5730\u56FE");
		bt_diy_prim.setBounds(10, 362, 180, 39);
		panel_diy_control.add(bt_diy_prim);
		
		bt_diy_save = new JButton("\u751F\u6210\u5E76\u4FDD\u5B58");
		bt_diy_save.setBounds(10, 411, 180, 39);
		panel_diy_control.add(bt_diy_save);
		
		JButton bt_diy_return = new JButton("\u8FD4\u56DE\u754C\u9762");
		bt_diy_return.setBounds(10, 460, 180, 39);
		panel_diy_control.add(bt_diy_return);
		panel_diy.add(panel_diy_game);
		bt_diy_save.setEnabled(false);
		bt_diy_shortsave.setEnabled(false);
		bt_diy_prim.setEnabled(false);
		bt_diy_seemap.setEnabled(false);
		bt_diy_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(diy_button_flag==1){
				bt_diy_save.setEnabled(true);
				bt_diy_shortsave.setEnabled(true);
				bt_diy_prim.setEnabled(true);
				bt_diy_seemap.setEnabled(true);
				panel_diy_game.Diymap(diy_width);
			//	bt_diy_start.setEnabled(false);
				diy_button_flag--;
				bt_diy_start.setText("重新设计");
				}else if(diy_button_flag==2){//设计一半的
					bt_diy_save.setEnabled(true);
					bt_diy_shortsave.setEnabled(true);
					bt_diy_prim.setEnabled(true);
					bt_diy_seemap.setEnabled(true);
					bt_diy_start.setText("重新设计");
					diy_button_flag=0;
					String map_s=lb_diy_peoplename.getText()+"-"+lb_diy_mapname.getText()+"-"+diy_width+"-";
					panel_diy_game.Continue_Diy(basic.Get_DiyMap(map_s,0), diy_width);
					
				}else{
				//	bt_diy_start.setEnabled(false);
					flag_diy_dfs=0;
					flag_seemap=1;
					bt_diy_seemap.setText("查看地图");
					panel_diy_game.diy_map.Restart_Diy();
					panel_diy_game.Diymap(diy_width);
				}
			}
		});
		
		bt_diy_seemap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if(flag_seemap==1||flag_diy_success==1){//查看地图
					panel_diy_game.width=diy_width;
					panel_diy_game.See_Map();
					panel_diy_game.diy_sad.Restart_Success();
					if(flag_diy_success!=1){
					bt_diy_seemap.setText("继续设计");
					flag_seemap=0;
					}
			//		panel_diy_game.See_Map();
				}else if(flag_seemap==0){//继续设计
					bt_diy_seemap.setText("查看地图");
					panel_diy_game.diy_sad.Restart_Success();
					if(flag_diy_dfs==1){//有经过算法的完善
						panel_diy_game.Continue_DFS();
						flag_diy_dfs=0;
					}
					panel_diy_game.Diymap(diy_width);
					flag_seemap=1;
				}
				if(flag_diy_success!=1){
					bt_diy_save.setEnabled(true);
					bt_diy_shortsave.setEnabled(true);
				}
			}
		});
		bt_diy_prim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				panel_diy_game.width=diy_width;
				panel_diy_game.See_PrimMap();
				bt_diy_seemap.setText("继续设计");
				flag_seemap=0;
				flag_diy_dfs=1;//经过算法的
				panel_diy_game.diy_sad.Restart_Success();
				bt_diy_save.setEnabled(false);
				bt_diy_shortsave.setEnabled(false);
			//	bt_diy_start.setEnabled(true);
				
			}
		});
		bt_diy_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(panel_diy_game.Save_Diy()){//成功
					bt_diy_prim.setEnabled(false);
					bt_diy_save.setEnabled(false);
					bt_diy_shortsave.setEnabled(false);
					bt_diy_start.setEnabled(false);
					bt_diy_seemap.setText("查看地图");
					flag_diy_success=1;
					file.Save_DiyMap(lb_diy_peoplename.getText()+"-"+lb_diy_mapname.getText()+"-",panel_diy_game.Get_DiyMap(),diy_width, 1);
		//			finish_map=panel_diy_game.Get_DiyMap();
				
				}else{
					bt_diy_seemap.setText("继续设计");
					flag_seemap=0;
				
				}
				
			}
		});
		
		bt_diy_shortsave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方
				file.Save_DiyMap(lb_diy_peoplename.getText()+"-"+lb_diy_mapname.getText()+"-",panel_diy_game.Get_DiyMap(),diy_width, 0);
				panel_diy_game.short_save();
			}
		});
		bt_diy_return.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				panel_diy_game.sound.stop();
				dispose();
				First_Frame.show(username);
			}
		});
		
	}
	public void Diy_SetBt(){
		bt_diy_prim.setEnabled(false);
		bt_diy_save.setEnabled(false);
		bt_diy_seemap.setEnabled(false);
		bt_diy_shortsave.setEnabled(false);
		bt_diy_start.setEnabled(false);
	}
}
