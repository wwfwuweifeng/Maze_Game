package 算法;

import java.awt.Point;
import java.util.Vector;

public class Prim_Map {
	public int[][] map;
	public int row;
	public int col;
	public Vector<Point> wall_list=new Vector<Point>();
	
	public int[][] Get_Prim_Map(int row,int col,int select){//select==1,表示是自然生成，否则表示不是自然生成
		this.row=row;
		this.col=col;
		if(select==1){
		map=new int[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				map[i][j]=0;  //都设为墙
			}
		}
		wall_list.clear();
		map[1][0]=1;
		map[1][1]=1;
		wall_list.add(new Point(1, 2));
		wall_list.add(new Point(2, 1));
		}
		Point t=new Point();
		int num;
		while(wall_list.size()>0){
			num=(int)(Math.random()*wall_list.size())%wall_list.size();
			t=wall_list.get(num);
			if(t.x%2==0){
				if(Judge_WallorRoad(t.x-1, t.y)){ //往上
					map[t.x][t.y]=1;
					map[t.x-1][t.y]=1;
					AddWall(t.x-1, t.y);
					
				}else if(Judge_WallorRoad(t.x+1, t.y)){
					map[t.x][t.y]=1;
					map[t.x+1][t.y]=1;
					AddWall(t.x+1, t.y);
				}
				wall_list.remove(num);
				
			}else if(t.y%2==0){
				if(Judge_WallorRoad(t.x, t.y-1)){//往左
					map[t.x][t.y]=1;
					map[t.x][t.y-1]=1;
					AddWall(t.x, t.y-1);
					
				}else if(Judge_WallorRoad(t.x, t.y+1)){
					map[t.x][t.y]=1;
					map[t.x][t.y+1]=1;
					AddWall(t.x, t.y+1);
				}
					
				wall_list.remove(num);
				
			}else{
				wall_list.remove(num);
			}
		}
		if(select==1){
			map[row-2][col-1]=1;
		}
		return map;
	}
	
	public int[][] Diy_PrimMap(int[][] diymap,int row_i,int col_j){
		map=new int[row_i][col_j];
//		int[][] prim_visit=new int[row_i][col_j];
		row=row_i;
		col=col_j;
		map=diymap;
//		for(int i=0;i<row;i++){
//			for(int j=0;j<col;j++){
//				if(diymap[i][j]==1||i==0||j==0){
//				prim_visit[i][j]=1;	
//				}else{
//					prim_visit[i][j]=0;
//				}
//				
//			}
//		}
		wall_list.clear();
//		while(!Judge_AllVisit(prim_visit)){
//		for(int i=1;i<row_i;i++){
//			for(int j=1;j<col_j;j++){
//					if(map[i][j]==0&&prim_visit[i][j]==0){//为墙，且未访问过
//						wall_list.add(new Point(i, j));
//				//		if(map[i+1][j-1])
//						break;
//					}
//					if(wall_list.size()==1){
//						break;
//					}
//			}
//		}
		wall_list.add(new Point(1, 2));
		wall_list.add(new Point(row_i-2,2));
		wall_list.add(new Point(2, col_j-2));
		wall_list.add(new Point(row_i-2,col_j-3));
		Point t=new Point();
		int num;
		while(wall_list.size()>0){
			num=(int)(Math.random()*wall_list.size())%wall_list.size();
			t=wall_list.get(num);
			if(t.x%2==0){
				if(Judge_WallorRoad(t.x-1, t.y)&&map[t.x-2][t.y]!=1){ //往上
					map[t.x][t.y]=1;
					map[t.x-1][t.y]=1;
	//				prim_visit[t.x][t.y]=1;
	//				prim_visit[t.x-1][t.y]=1;
					AddWall(t.x-1, t.y);
					
				}else if(Judge_WallorRoad(t.x+1, t.y)&&map[t.x+2][t.y]!=1){
					map[t.x][t.y]=1;
					map[t.x+1][t.y]=1;
		//			prim_visit[t.x][t.y]=1;
	//				prim_visit[t.x+1][t.y]=1;
					AddWall(t.x+1, t.y);
				}
				wall_list.remove(num);
		//		prim_visit[t.x][t.y]=1;
			}else if(t.y%2==0){
				if(Judge_WallorRoad(t.x, t.y-1)&&map[t.x][t.y-2]!=1){//往左
					map[t.x][t.y]=1;
					map[t.x][t.y-1]=1;
		//			prim_visit[t.x][t.y]=1;
		//			prim_visit[t.x][t.y-1]=1;
					AddWall(t.x, t.y-1);
					
				}else if(Judge_WallorRoad(t.x, t.y+1)&&map[t.x][t.y+2]!=1){
					map[t.x][t.y]=1;
					map[t.x][t.y+1]=1;
					AddWall(t.x, t.y+1);
		//			prim_visit[t.x][t.y]=1;
		//			prim_visit[t.x][t.y+1]=1;
				}	
				wall_list.remove(num);
		//		prim_visit[t.x][t.y]=1;
				
			}else{
				wall_list.remove(num);
		//		prim_visit[t.x][t.y]=1;
			}
		}	
		return map;
	}
	public boolean Judge_AllVisit(int[][] visit){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(visit[i][j]==0){
					return false;
				}
			}
		}
		return true;
	}
	public boolean Judge_WallorRoad(int x,int y){
		if(x<=0||x>=row-1||y<=0||y>=col-1||map[x][y]==1){//为通路
			return false;
		}else{
			return true;
		}
	}
	
	public void AddWall(int x,int y){
		if(Judge_WallorRoad(x-1, y)){//up
			if(!wall_list.contains(new Point(x-1,y))){
				wall_list.add(new Point(x-1, y));
			}
		}
		if(Judge_WallorRoad(x+1, y)){//down
			if(!wall_list.contains(new Point(x+1,y))){
				wall_list.add(new Point(x+1, y));
			}
		}
		if(Judge_WallorRoad(x, y-1)){//left
			if(!wall_list.contains(new Point(x,y-1))){
				wall_list.add(new Point(x, y-1));
			}
		}
		if(Judge_WallorRoad(x, y+1)){//right
			if(!wall_list.contains(new Point(x,y+1))){
				wall_list.add(new Point(x, y+1));
			}
		}
	}
}
