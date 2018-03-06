package �����Թ���;

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

import �㷨.DFSMap;
import �㷨.Prim_Map;

public class Double_Panel extends JPanel implements KeyListener{

	protected Point P1=new Point();  //p1����ɫ���ʾ,���������������ҿ��Ʒ���
	protected Point P2=new Point();  //p2�û�ɫ���ʾ   �ص�ʱ����ɫ��ʾ
	protected boolean dir_up,dir_down,dir_left,dir_right,dir_w,dir_d,dir_s,dir_a;
	public int[][] dfsmap;                           //���ڷ����Թ�����,0��ʾ���ϰ��1��ʾ���ϰ���
	int first=1;   //��ʾ�Ƿ��һ�ε��û��ƺ���   1��ʾ�ǣ�0��ʾ��
	public int success_p1=0;
	public int success_p2=0;
	public int row_1,col_1,row_2,col_2;                              //���ڼ�¼��ǰ���������Թ������λ��
	//public int[] difficult={20,10,8,4};  //���������Թ����Ѷ�
//	int round=0;    //���ڼ�¼��ǰ�ؿ�
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
		File file1 = new File("e:/workplace/��2/src/�����ļ�/5082.wav");
		//AudioClip sound1 = null;
		try {
			sound_success = Applet.newAudioClip(file1.toURL());
	//		System.out.println("test");
		} catch (MalformedURLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		file1=new File("e:/workplace/��2/src/�����ļ�/180.wav");
		try {
			sound = Applet.newAudioClip(file1.toURL());
//			System.out.println("test");
		} catch (MalformedURLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		if(first_show==1){
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/��2/src/�����ļ�/double_start.gif");
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
			Image image = tool.getImage("e:/workplace/��2/src/�����ļ�/p1last.gif");
			g.drawImage(image,0,0,820,500, this);
	//		System.out.println("P1��ʤ");
			sound_success.play();
			sound.stop();
		}else if(finish_success==2){//P2
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/��2/src/�����ļ�/p2last.gif");
			g.drawImage(image,0,0,820,500, this);
			sound_success.play();
			sound.stop();
	//		System.out.println("P2��ʤ");
			
		}else if(finish_success==3){//ƽ��
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/��2/src/�����ļ�/pingju.gif");
			g.drawImage(image,0,0,820,500, this);
			sound_success.play();
			sound.stop();
	//		System.out.println("ƽ��");
			
		}else if(success_p1==1){
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/��2/src/�����ļ�/p1vector.gif");
			g.drawImage(image,0,0,820,500, this);
	//		System.out.println("P1��ʱ��ʤ");
			sound_success.play();
			sound.stop();
		}else if(success_p2==1){
			
			Toolkit tool = this.getToolkit();
			Image image = tool.getImage("e:/workplace/��2/src/�����ļ�/p2vector.gif");
			g.drawImage(image,0,0,820,500, this);
	//		System.out.println("P2��ʱ��ʤ");
			sound_success.play();
			sound.stop();
		}else {
			for(int i=0;i<row_i;i++){
				for(int j=0;j<col_j;j++){
					if(dfsmap[i][j]==1){  //���ϰ���,�ú�ɫ��ʾ
						g.setColor(Color.yellow);
						g.fillRect(j*width, i*width, width, width);
					}else if(dfsmap[i][j]==0){ //���ϰ���ú�ɫ��ʾ
						g.setColor(Color.blue);
						g.fillRect(j*width, i*width, width, width);
						
					}
				}
			}
			if(P1.x==P2.x&&P1.y==P2.y){//��λ����ص�
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
		col_1=col_2=0;  //�Թ������
		success_p1=0;
		success_p2=0;
		finish_success=0;
		sound.loop();
		this.repaint();
		
	}
	
	public void Move(){
		///P1���ƶ�
		if(success_p1!=1&&success_p2!=1&&restart!=1){
		if(dir_up){
			if(dfsmap[row_1-1][col_1]==1){  //�������ϰ���
				P1.y=P1.y-width;  //���������ƶ�
				row_1--;  //����ҲҪ��Ӧ�ĸı�
			}
		}
		if(dir_down){
			if(dfsmap[row_1+1][col_1]==1){//�������ϰ���
					row_1++;
					P1.y=P1.y+width;  //���������ƶ�
					//				}
			}
		}
		if(dir_left){
			if(col_1!=0){  //�����Թ������
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
		////////P2���ƶ�
		if(dir_w){
			if(dfsmap[row_2-1][col_2]==1){  //�������ϰ���
				P2.y=P2.y-width;  //���������ƶ�
				row_2--;  //����ҲҪ��Ӧ�ĸı�
			}
		}
		if(dir_s){
			if(dfsmap[row_2+1][col_2]==1){//�������ϰ���
					row_2++;
					P2.y=P2.y+width;  //���������ƶ�
					//				}
			}
		}
		if(dir_a){
			if(col_2!=0){  //�����Թ������
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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		
	}
	
	
}
