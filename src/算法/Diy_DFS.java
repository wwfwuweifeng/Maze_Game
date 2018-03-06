package 算法;

import java.util.Vector;

public class Diy_DFS {
	public int[][] map;
	public int row,col;
	public int[][] visit;
	public int now_x,now_y;
	
//判断是否都访问过	
	public boolean Judge_Visit(){
	//	System.out.println("   "+visit[0][0]);
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(visit[i][j]==0&&map[i][j]==0){
					now_x=i;
					now_y=j;
					visit[i][j]=1;
			//		System.out.println(i+"  "+j+"   "+visit[i][j]);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean Go_AStep(){
		int num;
		int select;
		Vector<Integer> dir=new Vector<Integer>();
		dir.clear();
		dir.add(0);  //分别为上，下，左，右
		dir.add(1);
		dir.add(2);
		dir.add(3);
		while(dir.size()>0){
			num=(int)(Math.random()*10%dir.size());
			select=dir.get(num);
		//	System.out.println(select);
			dir.remove(num);
			if(select==0){//up
				if(now_x>1&&now_y>0&&now_y<col-1&&map[now_x-2][now_y]==0&&map[now_x-1][now_y-1]==0&&map[now_x-1][now_y+1]==0&&visit[now_x-1][now_y]==0){
//					visit[now_x-2][now_y]=1;
//					visit[now_x-1][now_y-1]=1;
//					visit[now_x-1][now_y+1]=1;
					map[now_x-1][now_y]=1;
					visit[now_x-1][now_y]=1;
					now_x--;
			//		System.out.println("up");
					return true;
				}
			}else if(select==1){//down
				if(now_x<row-2&&now_y>0&&now_y<col-1&&map[now_x+1][now_y-1]==0&&map[now_x+1][now_y]==0&&map[now_x+1][now_y+1]==0&&map[now_x+1][now_y]==0){
//					visit[now_x+2][now_y]=1;
//					visit[now_x+1][now_y-1]=1;
//					visit[now_x+1][now_y+1]=1;
					visit[now_x+1][now_y]=1;
					map[now_x+1][now_y]=1;
					now_x++;
			//		System.out.println("down");
				
					return true;
				}
			}else if(select==2){//left
				if(now_y>2&&now_x>0&&now_x<row-1&&map[now_x][now_y-2]==0&&map[now_x+1][now_y-1]==0&&map[now_x-1][now_y-1]==0&&map[now_x][now_y-1]==0){
//					visit[now_x][now_y-2]=1;
//					visit[now_x+1][now_y-1]=1;
//					visit[now_x-1][now_y-1]=1;
					visit[now_x][now_y-1]=1;
					map[now_x][now_y-1]=1;
					now_y--;
				//	System.out.println("left");
					return true;
				}
			}else if(select==3){//right
				if(now_y<col-2&&now_x>0&&map[now_x][now_y+2]==0&&now_x<row-1&&map[now_x+1][now_y+1]==0&&map[now_x-1][now_y+1]==0&&map[now_x][now_y+1]==0){
//					visit[now_x+1][now_y+1]=1;
//					visit[now_x-1][now_y+1]=1;
//					visit[now_x][now_y+2]=1;
					map[now_x][now_y+1]=1;
					visit[now_x][now_y+1]=1;
					now_y++;
				//	System.out.println("right");
				
					return true;
			}
		}
		//	System.out.println(dir.size());
	}
		//System.out.println("test");
		return false;
	}
	
	//更新是否已经访问过
	public void Updata_Visit(){
		visit=new int[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(map[i][j]==1||i==0||j==0||i==row-1||j==col-1){
					visit[i][j]=1;
				}else{
					visit[i][j]=0;
				}
			}
		}
	}
	
	public int[][] Diy_sunfa(int[][] diymap,int row_i,int col_j){
		row=row_i;
		col=col_j;
		map=new int[row][col];
		map=diymap;
		Updata_Visit();
		while(Judge_Visit()){
			while(Go_AStep()){
			//	System.out.println("step");
			}
		}
		return map;
	}
}
