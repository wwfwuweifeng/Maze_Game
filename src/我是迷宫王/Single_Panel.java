package 我是迷宫王;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import 算法.DFSMap;
import 算法.Prim_Map;

public class Single_Panel extends JPanel implements KeyListener {
	public int select=0;  //用于判断选择哪种单人模式
	public int people_x;    //用于记录人物坐标
	public int people_y;
	public Thread thread_1;
	public boolean dir_up,dir_down,dir_left,dir_right;  //用于记录按键方向
	public int[][] primmap;                           //用于返回迷宫数组,0表示有障碍物，1表示无障碍物
	public int first=1;   //表示是否第一次调用绘制函数   1表示是，0表示否
	public int success=0;
	public int width=0;
	public int row,col;  //用于记录当前人物所处迷宫数组的位置
	public int[] difficult={20,15,12,10,8,6};  //用于设置迷宫的难度,10,8,6
	public int round=0;    //用于记录当前关卡
	int row_i;
	int col_j;
	public int diy_or_prim=0;//1表示是diy，0表示是prim，2表示是prim，但是难度是自定义的，默认是prim
	public int first_show=1;
	public AudioClip sound_success = null;
	public AudioClip sound = null;
//	public AudioClip sound_move=null;
	public DFSMap dfsmap=new DFSMap();
	public Single_Panel() {
		// TODO 自动生成的构造函数存根
		this.setSize(820,520);
		dir_down=dir_left=dir_right=dir_up=false;
		this.repaint();
		File file1 = new File("e:/workplace/软工2/src/配置文件/5082.wav");
		//AudioClip sound1 = null;
		try {
			sound_success = Applet.newAudioClip(file1.toURL());
	//		System.out.println("test");
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		file1=new File("e:/workplace/软工2/src/配置文件/180.wav");
		try {
			sound = Applet.newAudioClip(file1.toURL());
//			System.out.println("test");
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	//	PaintMap(20);
//		file1=new File("e:/workplace/软工2/src/配置文件/7235.wav");
//		try {
//			sound_move= Applet.newAudioClip(file1.toURL());
////			System.out.println("test");
//		} catch (MalformedURLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
		thread_1=new Thread(new Runnable() {
			public void run() {
				Automatic();
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		super.paintComponent(g);
		if(first_show==1&&(diy_or_prim==0||diy_or_prim==2)){//单人闯关,自选难度
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/single_start.gif");
			g.drawImage(image,0,0,820,500, this);
		}else if(first_show==1&&diy_or_prim==1){//自己设计的地图
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/diychellenge.gif");
			g.drawImage(image,0,0,820,500, this);
		}else{
		if(first==1){
			first=0;
			people_x=1;
			people_y=width+1;	
		}
//		System.out.println("test");
		if(round==difficult.length){
//			g.setColor(Color.white);
//			g.fillRect(0, 0,820,520);
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/allsuccess.gif");
			g.drawImage(image,0,0,820,520, this);
			sound.stop();
			sound_success.play();
		}else if(success==1&&diy_or_prim==0){//单人闯关
//			g.setColor(Color.white);
//			g.fillRect(0, 0, 820, 520);
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/next.gif");
			g.drawImage(image,0,0,800,500, this);
			sound.stop();
			sound_success.play();
			
		}else if(success==1&&(diy_or_prim==1||diy_or_prim==2)){//挑战模式,自选难度
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/chellengesuccess.gif");
			g.drawImage(image,0,0,800,500, this);
			sound.stop();
			sound_success.play();
		}else{
		//	System.out.println(row_i+"==="+col_j);
			
			sound_success.stop();
			for(int i=0;i<row_i;i++){
				for(int j=0;j<col_j;j++){
					if(primmap[i][j]==1){  //无障碍物,用黄色表示
						g.setColor(Color.yellow);
						g.fillRect(j*width, i*width, width, width);
					}else if(primmap[i][j]==0){ //有障碍物，用蓝色表示
						g.setColor(Color.blue);
						g.fillRect(j*width, i*width, width, width);
						
					}
				}
			}
		
		g.setColor(Color.green);
		g.fillOval(people_x, people_y,width-1, width-1);
	}
		}
	}
	
	public void Move(){
	//	sound_move.play();
		if(success!=1){
		if(dir_up){
			
			if(primmap[row-1][col]==1){  //向上无障碍物
				people_y=people_y-width;  //人物向上移动
				row--;  //行数也要相应的改变
			}
		}
		if(dir_down){
			
		if(primmap[row+1][col]==1){//向下无障碍物
					row++;
					people_y=people_y+width;  //人物向下移动
//				}
			}
		}
		if(dir_left){
			
			if(col!=0){  //不是迷宫的入口
				if(primmap[row][col-1]==1){
					people_x=people_x-width;
					col--;
				}
			}
			
		}
		if(dir_right){

		if(primmap[row][col+1]==1){
				people_x=people_x+width;
				col++;
			}
		}
		this.repaint();
		if((col+1)==col_j||row==0||(row+1)==row_i||(col==0&&row!=1)){//通关了
			round++;
			System.out.println("ok");
			success=1;
			dir_down=dir_left=dir_right=dir_up=false;
			this.repaint();
		}
		}
	}
	
	public void PaintMap(int num){
//			DFSMap t=new DFSMap();
//			dfsmap=new int[400/num][600/num];
//			dfsmap=t.GetDFSMap(num);
		
			width=num;
			row_i=500/width;
			col_j=800/width;
			if(row_i%2==0)row_i++;
			if(col_j%2==0)col_j++;
			Prim_Map p=new Prim_Map();
			primmap=p.Get_Prim_Map(row_i,col_j,1);
			first_show--;
			first=1;
			row=1;
			col=0;  //迷宫的入口
			success=0;
			this.repaint();
			sound.loop();
	}
	
	public void Diy_Chellenge_Map(int[][] diymap){	
		row_i=500/width;
		col_j=800/width;
		if(row_i%2==0)row_i++;
		if(col_j%2==0)col_j++;
		primmap=new int[row_i][col_j];
		primmap=diymap;
		first_show--;
		first=1;
		row=1;
		col=0;  //迷宫的入口
		success=0;
		this.repaint();
		sound.loop();
	//	diy_or_prim=1;
	}
	

//	//自动走出迷宫,完成时，返回1
	public int Automatic(){
		Vector<Point> result_road=new Vector<Point>();
		result_road=dfsmap.DFS_Find(row_i,col_j, primmap);
		Point p;
		for(int i=0;i<result_road.size();i++){
			p=result_road.get(i);
			people_x=p.y*width+1;
			people_y=p.x*width+1;
			this.repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			System.out.println(people_x);
		}	
		return 1;
	}
//	
	
	public void Paint_Choose_Map(){
		
//		diy_or_prim=2;
//		width=num;
		row_i=500/width;
		col_j=800/width;
		if(row_i%2==0)row_i++;
		if(col_j%2==0)col_j++;
		Prim_Map p=new Prim_Map();
		primmap=p.Get_Prim_Map(row_i,col_j,1);
		first_show--;
		first=1;
		row=1;
		col=0;  //迷宫的入口
		success=0;
		this.repaint();
		sound.loop();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getKeyCode()==KeyEvent.VK_UP){
			dir_up=true;
			Move();
	//		System.out.println("test");
		}
		if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
			dir_down=true;
			Move();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
			dir_right=true;
			Move();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
			dir_left=true;
			Move();
		}
//		if(arg0.getKeyCode()==KeyEvent.VK_ENTER&&success==1&&round!=difficult.length){
//			PaintMap(difficult[round]);
//		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getKeyCode()==KeyEvent.VK_UP){
			dir_up=false;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
			dir_down=false;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
			dir_right=false;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
			dir_left=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

}
