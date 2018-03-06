package Œ“ «√‘π¨Õı;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Diy_Map extends JPanel{
	public JToggleButton[] togs=new JToggleButton[20000];
	public int width;
	public int num_order=0;
	public int[][] map;
	public int row,col;
	
	public Diy_Map(){
		this.setSize(820,520);
		this.setLayout(null);
	}
	
	public void Set_TGB(int num){
		
		width=num;
		row=500/width;
		col=800/width;
		if(row%2==0)row++;
		if(col%2==0)col++;
		map=new int[row][col];
		num_order=0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				togs[num_order]=new JToggleButton();
				this.add(togs[num_order]);
				togs[num_order].setBounds(j*width, i*width,width,width);
				if(num_order==col||num_order==col+1){
					togs[num_order].setBackground(Color.black);
					togs[num_order].setSelected(true);
					togs[num_order].setEnabled(false);
				}else{
				togs[num_order].setBackground(Color.red);
				togs[num_order].setSelected(false);
				togs[num_order].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						togs[num_order]=new JToggleButton();
						if(togs[num_order].isSelected()){
							togs[num_order].setBackground(Color.blue);
						}else{
							togs[num_order].setBackground(Color.red);
						}
						
					}
				});
				}
				num_order++;
				
			}
		}
	}
	
	public void Diy_ShuZu(){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(togs[i*col+j].isSelected()){
					map[i][j]=1;
				}else{
					map[i][j]=0;
				}
			}
		}
	//	print_map();
	}
	
	public void Restart_Diy(){
	//	map=new int[row][col];
		int num=0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(num==col||num==col+1){
					togs[num].setSelected(true);
					map[i][j]=1;
					num++;
				}else{
					togs[num].setSelected(false);
					map[i][j]=0;
					num++;
				}
			}
		}
	}
	
	public void Diy_DFS_Map(int[][] diydfs){
	//	map=new int[row][col];
		map=diydfs;
//		for(int i=0;i<row;i++){
//			for(int j=0;j<col;j++){
//				map[i][j]=diydfs[i][j];
//			}
//		}
		int num=0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(map[i][j]==1){
					togs[num].setSelected(true);
				}else{
					togs[num].setSelected(false);
				}
				num++;
			}
		}
//		Diy_ShuZu();
//		print_map();
	}
	public void print_map(){
		System.out.println(" diymap=====================");
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
