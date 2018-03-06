package 算法;

import java.awt.Point;
import java.util.Stack;
import java.util.Vector;

public class DFSMap {
	//获得最终的迷宫数组
	public int[][] GetDFSMap(int difficult){
		int row=500/difficult;
		int col=820/difficult;
		int[][] Map=new int[row][col];
		int[][] result=new int[row][col];
		Map=GetArray(row, col);
		//不断进行循环，直到生成符合条件的数组
		result=DFS_Build(row, col, Map);
		while(result==null){
			Map=GetArray(row, col);
			result=DFS_Build(row, col, Map);
		}
//		for(int i=0;i<row;i++){
//			for(int j=0;j<col;j++){
//				System.out.print(result[i][j]+" ");
//			}
//			System.out.println();
//		}
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
		return result;
	}
	//用于获取随机数组，由0和1构成
	public int[][] GetArray(int row,int col){
		int[][] array=new int [row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if((i==0||i==row-1)&&j<(2*col/3)){
					array[i][j]=0;
				}else if(j==0){
					array[i][j]=0;
				}else{
					array[i][j]=((int)(Math.random()*10))%2;
			//		System.out.println(array[i][j]);
				}
			}
		}
		array[1][0]=1;
		array[1][1]=1;
//		array[row-1][col-2]=1;
//		array[row-2][col-2]=1;
		return array;
	}
	
	//用自己改编的深度优先搜索生成的迷宫
	public int[][] DFS_Build(int row,int col, int[][] map){
		boolean[][] map_visit=new boolean[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				map_visit[i][j]=false;
			}
		}
		Stack<Point> stack=new Stack<Point>();
		map_visit[1][0]=true;
		Point p=new Point();
		p.x=1;//x表示row
		p.y=0;//y表示col
		stack.push(p);
		Point t=new Point();
	//	t=p;
		while(!stack.isEmpty()){
			//按照右，下，左，上的顺序
			t=stack.peek();
			if(t.y!=(col-1)&&!map_visit[t.x][t.y+1]){//往右
				map_visit[t.x][t.y+1]=true;
				if(map[t.x][t.y+1]==1){//无障碍物
					t.y=t.y+1;
					if(t.y==(col-1)){
						return map;
					}
					stack.push(t);
				}
			}else if(t.x!=(row-1)&&!map_visit[t.x+1][t.y]){//往下
				map_visit[t.x+1][t.y]=true;
				if(map[t.x+1][t.y]==1){
					t.x=t.x+1;
					if(t.x==(row-1)){
						return map;
					}
					stack.push(t);
					
				}
			}else if(t.y!=0&&!map_visit[t.x][t.y-1]){//往左
				map_visit[t.x][t.y-1]=true;
				if(map[t.x][t.y-1]==1){
					t.y=t.y-1;
					stack.push(t);
				}
			}else if(t.x!=0&&!map_visit[t.x-1][t.y]){//往上
				map_visit[t.x-1][t.y]=true;
				if(map[t.x-1][t.y]==1){
					t.x=t.x-1;
					if(t.x==0){
						return map;
					}
					stack.push(t);
				}
			}else{
				int select=((int)(Math.random()*10))%3;
				if(select==0&&t.y!=(col-1)&&map[t.x][t.y+1]==0){//向右打通墙
					map_visit[t.x][t.y+1]=true;
					map[t.x][t.y+1]=1;
					t.y=t.y+1;
					if(t.y==(col-1)){
						return map;
					}
					stack.push(t);
				}else if(select==1&&t.x!=0&&map[t.x-1][t.y]==0&&t.y>col/2){//向上打通
					map_visit[t.x-1][t.y]=true;
					map[t.x-1][t.y]=1;
						t.x=t.x-1;
						if(t.x==0){
							return map;
						}
						stack.push(t);
				}else if(select==2&&t.x!=(row-1)&&map[t.x+1][t.y]==0&&t.y>col/2){//向下打通
					map_visit[t.x+1][t.y]=true;
					map[t.x+1][t.y]=1;
						t.x=t.x+1;
						if(t.x==(row-1)){
							return map;
						}
						stack.push(t);
				}else{
					stack.pop();
				}
				
			}
		}
		return null;
	}
	
	//判断是否有路径
	public boolean DFS_Judge(int row,int col,int[][] map){
		boolean[][] map_visit=new boolean[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				map_visit[i][j]=false;
			}
		}
		Stack<Point> stack=new Stack<Point>();
		map_visit[1][0]=true;
		Point p=new Point();
		p.x=1;//x表示row
		p.y=0;//y表示col
		stack.push(p);
		Point t=new Point();
	//	t=p;
		while(!stack.isEmpty()){
			//按照右，下，左，上的顺序
			t=stack.peek();
			if(t.y!=(col-1)&&!map_visit[t.x][t.y+1]){//往右
				map_visit[t.x][t.y+1]=true;
				if(map[t.x][t.y+1]==1){//无障碍物
					t.y=t.y+1;
					if(t.y==(col-1)){
						return true;
					}
					stack.push(new Point(t.x, t.y));
				}
			}else if(t.x!=(row-1)&&!map_visit[t.x+1][t.y]){//往下
				map_visit[t.x+1][t.y]=true;
				if(map[t.x+1][t.y]==1){
					t.x=t.x+1;
					if(t.x==(row-1)){
						return true;
					}
					stack.push(new Point(t.x, t.y));
				}
			}else if(t.y!=0&&!map_visit[t.x][t.y-1]){//往左
				map_visit[t.x][t.y-1]=true;
				if(map[t.x][t.y-1]==1){
					t.y=t.y-1;
					if(t.y==0){
						return true;
					}
					stack.push(new Point(t.x, t.y));
				}
			}else if(t.x!=0&&!map_visit[t.x-1][t.y]){//往上
				map_visit[t.x-1][t.y]=true;
				if(map[t.x-1][t.y]==1){
					t.x=t.x-1;
					if(t.x==0){
						return true;
					}
					stack.push(new Point(t.x, t.y));
				}
			}else{
				stack.pop();
			}
	}
		return false;
}
	//深度优先搜索，返回结果路径的点
	public Vector<Point> DFS_Find(int row,int col,int[][] map){
		boolean[][] map_visit=new boolean[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				map_visit[i][j]=false;
			}
		}
		Vector<Point> stack=new Vector<Point>();
		map_visit[1][0]=true;
		Point p=new Point();
		p.x=1;//x表示row
		p.y=0;//y表示col
		stack.add(p);
		Point t=new Point();
	//	t=p;
		while(!stack.isEmpty()){
			//按照右，下，左，上的顺序
			t=stack.get(stack.size()-1);
			if(t.y!=(col-1)&&!map_visit[t.x][t.y+1]){//往右
				map_visit[t.x][t.y+1]=true;
				if(map[t.x][t.y+1]==1){//无障碍物
					t.y=t.y+1;
					if(t.y==(col-1)){
						stack.add(new Point(t.x, t.y));
						return stack;
					}
					stack.add(new Point(t.x, t.y));
				}
			}else if(t.x!=(row-1)&&!map_visit[t.x+1][t.y]){//往下
				map_visit[t.x+1][t.y]=true;
				if(map[t.x+1][t.y]==1){
					t.x=t.x+1;
					if(t.x==(row-1)){
						stack.add(new Point(t.x, t.y));
						return stack;
					}
					stack.add(new Point(t.x, t.y));
				}
			}else if(t.y!=0&&!map_visit[t.x][t.y-1]){//往左
				map_visit[t.x][t.y-1]=true;
				if(map[t.x][t.y-1]==1){
					t.y=t.y-1;
					if(t.y==0){
						stack.add(new Point(t.x, t.y));
						return stack;
					}
					stack.add(new Point(t.x, t.y));
				}
			}else if(t.x!=0&&!map_visit[t.x-1][t.y]){//往上
				map_visit[t.x-1][t.y]=true;
				if(map[t.x-1][t.y]==1){
					t.x=t.x-1;
					if(t.x==0){
						stack.add(new Point(t.x, t.y));
						return stack;
					}
					stack.add(new Point(t.x, t.y));
				}
			}else{
				stack.remove(stack.size()-1);
			}
	}
		return stack;
}
}
