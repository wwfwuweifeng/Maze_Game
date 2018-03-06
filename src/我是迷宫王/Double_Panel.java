package 我是迷宫王;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JPanel;

import 算法.DFSMap;
import 算法.Prim_Map;

public class Double_Panel extends JPanel implements KeyListener{

	protected Point P1=new Point();  //p1用绿色球表示,且他是用上下左右控制方向
	protected Point P2=new Point();  //p2用黄色球表示   重叠时用蓝色表示
	protected boolean dir_up,dir_down,dir_left,dir_right,dir_w,dir_d,dir_s,dir_a;
	public int[][] dfsmap;                           //用于返回迷宫数组,0表示有障碍物，1表示无障碍物
	int first=1;   //表示是否第一次调用绘制函数   1表示是，0表示否
	public int success_p1=0;
	public int success_p2=0;
	public int row_1,col_1,row_2,col_2;                              //用于记录当前人物所处迷宫数组的位置
	//public int[] difficult={20,10,8,4};  //用于设置迷宫的难度
//	int round=0;    //用于记录当前关卡
	int width;
	int row_i;
	int col_j;
	public int first_show=1;
	public int finish_success=0;
	public int restart=0;
	public AudioClip sound_success = null;
	public AudioClip sound = null;
	public Double_Panel(){
		this.setSize(820,520);
		dir_down=dir_left=dir_right=dir_up=false;
		dir_w=dir_a=dir_d=dir_s=false;
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
	}
	
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		if(first_show==1){
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/double_start.gif");
			g.drawImage(image,0,0,820,500, this);
		}else{
		if(first==1){
			first=0;
			P1.x=1;
			P1.y=width+1;
			P2.x=1;
			P2.y=width+1;
		}
		if(finish_success==1){//P1
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/p1last.gif");
			g.drawImage(image,0,0,820,500, this);
	//		System.out.println("P1获胜");
			sound_success.play();
			sound.stop();
		}else if(finish_success==2){//P2
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/p2last.gif");
			g.drawImage(image,0,0,820,500, this);
			sound_success.play();
			sound.stop();
	//		System.out.println("P2获胜");
			
		}else if(finish_success==3){//平局
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/pingju.gif");
			g.drawImage(image,0,0,820,500, this);
			sound_success.play();
			sound.stop();
	//		System.out.println("平局");
			
		}else if(success_p1==1){
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/p1vector.gif");
			g.drawImage(image,0,0,820,500, this);
	//		System.out.println("P1暂时获胜");
			sound_success.play();
			sound.stop();
		}else if(success_p2==1){
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/软工2/src/配置文件/p2vector.gif");
			g.drawImage(image,0,0,820,500, this);
	//		System.out.println("P2暂时获胜");
			sound_success.play();
			sound.stop();
		}else {
			for(int i=0;i<row_i;i++){
				for(int j=0;j<col_j;j++){
					if(dfsmap[i][j]==1){  //无障碍物,用黑色表示
						g.setColor(Color.yellow);
						g.fillRect(j*width, i*width, width, width);
					}else if(dfsmap[i][j]==0){ //有障碍物，用红色表示
						g.setColor(Color.blue);
						g.fillRect(j*width, i*width, width, width);
						
					}
				}
			}
			if(P1.x==P2.x&&P1.y==P2.y){//两位玩家重叠
				g.setColor(Color.red);
				g.fillOval(P1.x, P1.y, width-1, width-1);
			}else{
				g.setColor(Color.green);
				g.fillOval(P1.x, P1.y, width-1, width-1);
				g.setColor(Color.black);
				g.fillOval(P2.x, P2.y, width-1, width-1);
			}
		}	
		}
	};
	
	public void Paint_Map(int num){
		
		width=num;
		row_i=500/width;
		col_j=800/width;
		if(row_i%2==0)row_i++;
		if(col_j%2==0)col_j++;
		first_show--;
		restart=0;
		Prim_Map t=new Prim_Map();
		first=1;
		dfsmap=new int[row_i][col_j];
		dfsmap=t.Get_Prim_Map(row_i, col_j, 1);
		row_1=row_2=1;
		col_1=col_2=0;  //迷宫的入口
		success_p1=0;
		success_p2=0;
		finish_success=0;
		sound.loop();
		this.repaint();
		
	}
	
	public void Move(){
		///P1的移动
		if(success_p1!=1&&success_p2!=1&&restart!=1){
		if(dir_up){
			if(dfsmap[row_1-1][col_1]==1){  //向上无障碍物
				P1.y=P1.y-width;  //人物向上移动
				row_1--;  //行数也要相应的改变
			}
		}
		if(dir_down){
			if(dfsmap[row_1+1][col_1]==1){//向下无障碍物
					row_1++;
					P1.y=P1.y+width;  //人物向下移动
					//				}
			}
		}
		if(dir_left){
			if(col_1!=0){  //不是迷宫的入口
				if(dfsmap[row_1][col_1-1]==1){
					P1.x=P1.x-width;
					col_1--;
				}
			}
			
		}
		if(dir_right){
			if(dfsmap[row_1][col_1+1]==1){
				P1.x=P1.x+width;
			//	System.out.println(P1.x);
				col_1++;
			}
		}
		////////P2的移动
		if(dir_w){
			if(dfsmap[row_2-1][col_2]==1){  //向上无障碍物
				P2.y=P2.y-width;  //人物向上移动
				row_2--;  //行数也要相应的改变
			}
		}
		if(dir_s){
			if(dfsmap[row_2+1][col_2]==1){//向下无障碍物
					row_2++;
					P2.y=P2.y+width;  //人物向下移动
					//				}
			}
		}
		if(dir_a){
			if(col_2!=0){  //不是迷宫的入口
				if(dfsmap[row_2][col_2-1]==1){
					P2.x=P2.x-width;
					col_2--;
				}
			}
			
		}
		if(dir_d){
			if(dfsmap[row_2][col_2+1]==1){
				P2.x=P2.x+width;
				col_2++;
			}
		}
		//////////////////////////////////////////////
		this.repaint();
		if((col_1+1)==col_j||row_1==0||(row_1+1)==row_i){
	//		round++;
			System.out.println("P1 is ok");
			success_p1=1;
			dir_down=dir_left=dir_right=dir_up=false;
			dir_w=dir_a=dir_d=dir_s=false;
			this.repaint();
		}
		if((col_2+1)==col_j||row_2==0||(row_2+1)==row_i){
		//	round++;
			System.out.println("P2 is ok");
			success_p2=1;
			dir_down=dir_left=dir_right=dir_up=false;
			dir_w=dir_a=dir_d=dir_s=false;
			this.repaint();
		
		}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		int select=e.getKeyCode();
		switch (select) {
		case KeyEvent.VK_UP:
			dir_up=true;
			break;
		case KeyEvent.VK_DOWN:
			dir_down=true;
			break;
		case KeyEvent.VK_LEFT:
			dir_left=true;
			break;
		case KeyEvent.VK_RIGHT:
			dir_right=true;
			break;
		case KeyEvent.VK_W:
			dir_w=true;
			break;
		case KeyEvent.VK_S:
			dir_s=true;
			break;
		case KeyEvent.VK_D:
			dir_d=true;
			break;
		case KeyEvent.VK_A:
			dir_a=true;
			break;
		default:
			break;
		}
		
		Move();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		int select=e.getKeyCode();
		switch (select) {
		case KeyEvent.VK_UP:
			dir_up=false;
			break;
		case KeyEvent.VK_DOWN:
			dir_down=false;
			break;
		case KeyEvent.VK_LEFT:
			dir_left=false;
			break;
		case KeyEvent.VK_RIGHT:
			dir_right=false;
			break;
		case KeyEvent.VK_W:
			dir_w=false;
			break;
		case KeyEvent.VK_S:
			dir_s=false;
			break;
		case KeyEvent.VK_D:
			dir_d=false;
			break;
		case KeyEvent.VK_A:
			dir_a=false;
			break;
		default:
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
	
}
