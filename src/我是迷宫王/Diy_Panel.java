package 我是迷宫王;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.CardLayout;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JPanel;

import 算法.DFSMap;
import 算法.Diy_DFS;
import 算法.Prim_Map;

public class Diy_Panel extends JPanel{
	public Diy_Map diy_map=new Diy_Map();
	public Diy_SAD_Panel diy_sad=new Diy_SAD_Panel();
	public CardLayout card=new CardLayout();
	public int first_set=1;
	public int width;
	public Diy_DFS diydfs;
	public int[][] see_diy;
	public DFSMap dfs=new DFSMap();
	public AudioClip sound = null;
	
	
	public Diy_Panel() {
		// TODO 自动生成的构造函数存根
		this.setSize(820,520);
		this.setLayout(card);
		this.add("1",diy_sad);
		this.add("2",diy_map);
		card.show(this,"1");
		File file1 = new File("e:/workplace/软工2/src/配置文件/8411.wav");
		//AudioClip sound1 = null;
		try {
			sound = Applet.newAudioClip(file1.toURL());
	//		System.out.println("test");
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void See_Finish_Map(int[][] finish_map,int old_width){
		
		diy_sad.Paint_Map(finish_map,  old_width);
		card.show(this,"1");
	}
	
	public void See_Map(){
		diy_map.Diy_ShuZu();
		diy_sad.Paint_Map(diy_map.map,width);
		card.show(this,"1");
	}
	
	public void See_PrimMap(){
		diydfs=new Diy_DFS();
		diy_map.Diy_ShuZu();
	//	diy_map.print_map();
		see_diy=new int[diy_map.row][diy_map.col];
		see_diy=diydfs.Diy_sunfa(diy_map.map, diy_map.row,diy_map.col);
		diy_sad.Paint_Map(see_diy,width);
		card.show(this,"1");
	}
	
	public void Continue_DFS(){
	//	diy_map.Set_TGB(width);
		diy_map.Diy_DFS_Map(diy_sad.map);
	//	System.out.println("see地图");
//		for(int i=0;i<diy_map.row;i++){
//			for(int j=0;j<diy_map.col;j++){
//				System.out.print(see_diy[i][j]+" ");
//			}
//			System.out.println();
//		}
//		diy_map.print_map();
		card.show(this,"2");
	}
	public void Diymap(int num){
		if(first_set==1){
			first_set--;
			diy_map.Set_TGB(num);
			width=num;
	//		System.out.println("test");
		}
		card.show(this,"2");
	}
	
	public void Continue_Diy(int[][] continue_map,int continue_width ){
		Diymap(continue_width);
		diy_map.Diy_DFS_Map(continue_map);
//		diy_map.print_map();
//		System.out.println("=============");
		card.show(this,"2");
	}
	
	public boolean Save_Diy(){
		diy_map.Diy_ShuZu();
		if(dfs.DFS_Judge(diy_map.row, diy_map.col, diy_map.map)){//成功
			diy_sad.Show_SuccessOrFail(1);
			card.show(this,"1");
			return true;
		}else{//失败
			diy_sad.Show_SuccessOrFail(0);
			card.show(this,"1");
			return false;
		}
		
	}
	
	public void short_save(){
		diy_sad.Show_ShortSave();
		card.show(this,"1");
	}
	
	public int[][] Get_DiyMap(){
		diy_map.Diy_ShuZu();
		return diy_map.map;
	}
}
