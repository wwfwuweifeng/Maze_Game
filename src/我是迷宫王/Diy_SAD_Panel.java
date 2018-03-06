package �����Թ���;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Diy_SAD_Panel extends JPanel{
	public int[][] map;
	public int first_show=1;
	public int width;
	public int row;
	public int col;
	public int diy_success=-1;
	public int diy_shortsave=-1;
	
	public Diy_SAD_Panel(){
		this.setSize(820,520);
		this.setLayout(null);
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO �Զ����ɵķ������
		super.paintComponent(g);
		if(first_show==1){
			Toolkit tool=this.getToolkit();
			Image image=tool.getImage("e:/workplace/��2/src/�����ļ�/diy_start.gif");
			g.drawImage(image,0,0,820,500, this);
		}else if(diy_success==1){//�ɹ�
			Toolkit tool=this.getToolkit();
			Image image=tool.getImage("e:/workplace/��2/src/�����ļ�/diysuccess.gif");
			g.drawImage(image,0,0,820,500, this);
		}else if(diy_success==0){//ʧ�ܣ�û��ͨ��
			Toolkit tool=this.getToolkit();
			Image image=tool.getImage("e:/workplace/��2/src/�����ļ�/diyfail.gif");
			g.drawImage(image,0,0,820,500, this);
			
		}else if(diy_shortsave==1){//��ʱ����
		//	System.out.println("test======");
			Toolkit tool=this.getToolkit();
			Image image=tool.getImage("e:/workplace/��2/src/�����ļ�/diyshort.gif");
			g.drawImage(image,0,0,820,500, this);
	
		}else{
		//	System.out.println("test");
			for(int i=0;i<row;i++){
				for(int j=0;j<col;j++){
					if(map[i][j]==0){//�ϰ���
						g.setColor(Color.blue);
						g.fillRect(j*width,i*width,width,width);
					}else { //ͨ��
				//		System.out.println("0");
						g.setColor(Color.yellow);
						g.fillRect(j*width,i*width,width,width);
					}
				}
			}
		}
	}
	
	public void Show_SuccessOrFail(int select){
		first_show--;
		if(select==1){//�ɹ�
			diy_success=1;
		}else if(select==0){//ʧ��
			diy_success=0;
		}
		this.repaint();
	}
	
	public void Show_ShortSave(){
		first_show--;
		diy_shortsave=1;
		this.repaint();
	}
	
	public void Restart_Success(){
		first_show--;
		diy_success=-1;
		diy_shortsave=-1;
	}
	
	public void Paint_Map(int[][] diy_map,int num){
		row=500/num;
		col=800/num;
		if(row%2==0)row++;
		if(col%2==0)col++;
		map=new int[row][col];
		map=diy_map;
		first_show--;
		width=num;
		this.repaint();
	}
}
