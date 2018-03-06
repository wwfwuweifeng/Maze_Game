package 功能部分;

import java.io.File;
import java.util.Vector;

public class File_Function {
	public FileOperation file_operation=new FileOperation();
	public Basic_Function basic=new Basic_Function();
	
	//注册时为用户创建对应的文件及文件夹,还有加入用户名单
	public void Add_NewUser(String single_user){
		int rank=0;
		Vector<String> user=new Vector<String>();
		user=file_operation.read("users_order");
		user.add(single_user);
		file_operation.write(user, "users_order");
		user.clear();
		user=file_operation.read("users_score");
		user.add(basic.Get_Text(single_user, basic.address_name)+"-0"+"-"+(user.size()+1)+"-");
		rank=user.size();
		file_operation.write(user, "users_score");
		user.clear();
		user=file_operation.read("users_successtimes");
		user.add(basic.Get_Text(single_user, basic.address_name)+"-0"+"-"+(user.size()+1)+"-");
		file_operation.write(user, "users_successtimes");
		String filepath="D:/课设2游戏文件/"+basic.Get_Text(single_user, basic.address_name);
		file_operation.CreateDir(filepath);
		file_operation.CreateDir(filepath+"/diymap/finish");
		file_operation.CreateDir(filepath+"/diymap/continue");
		user.clear();
		user.add(0+"-"+rank+"-暂无-");
		user.add("continue");
		user.add("finish");
		file_operation.write(user, basic.Get_Text(single_user, basic.address_name)+"/information");
	}
	
	public void Delete_Map(String path,String mapwidth){
		
		String filepath="d:/课设2游戏文件/"+basic.Get_Text(path, 1)+"/diymap/"+basic.Get_Text(path, 2)+"/"+basic.Get_Text(path, 3);//wo-diymap-finish-name;
		File file=new File(filepath);
	//	System.out.println(filepath);
		file_operation.DeletDir(file);
		Vector<String> map=new Vector<String>();
		map=file_operation.read(basic.Get_Text(path, 1)+"/information");
		String mapname=basic.Get_Text(path, 3)+"    "+mapwidth;
		for(int i=0;i<map.size();i++){
			if(mapname.equals(map.get(i))){
				map.remove(i);
				break;
			}
		}
		file_operation.write(map,basic.Get_Text(path, 1)+"/information");
	}
	
	public void Save_DiyMap(String mappath,int[][] map ,int width,int select){//1表示是完成的，0表示是暂时的
		int row=500/width;//name-mapname-
		int col=800/width;
		if(row%2==0)row++;
		if(col%2==0)col++;
		Vector<String> diymap=new Vector<String>();
		for(int i=0;i<row;i++){
			String single_row="";
			for(int j=0;j<col;j++){
				single_row=single_row+map[i][j]+"-";
			}
			diymap.add(single_row);
		}
		if(select==1){//finish
			String filepath="d:/课设2游戏文件/"+basic.Get_Text(mappath, 1)+"/diymap/finish/"+basic.Get_Text(mappath, 2);
			file_operation.CreateDir(filepath);
			String filepath_2=basic.Get_Text(mappath, 1)+"/diymap/finish/"+basic.Get_Text(mappath, 2);
			file_operation.write(diymap,filepath_2+"/mapshuzu");
			diymap.clear();
			String mapinformation=basic.Get_Text(mappath, 2)+"-"+basic.Get_Text(mappath, 1)+"-"+width+"-暂无-暂无-";
			diymap.add(mapinformation);
			file_operation.write(diymap, filepath_2+"/mapinformation");
			diymap.clear();
			diymap=file_operation.read(basic.Get_Text(mappath, 1)+"/information");
			for(int i=0;i<diymap.size();i++){
				if(diymap.get(i).equals(basic.Get_Text(mappath, 2)+"    "+width)){
					diymap.remove(i);
					String filepath_3="d:/课设2游戏文件/"+basic.Get_Text(mappath, 1)+"/diymap/continue/"+basic.Get_Text(mappath, 2);
					File file=new File(filepath_3);
					file_operation.DeletDir(file);
					break;
				}
			}
			diymap.add(basic.Get_Text(mappath, 2)+"    "+width);
			file_operation.write(diymap, basic.Get_Text(mappath, 1)+"/information");
		}else{//continue
			String filepath="d:/课设2游戏文件/"+basic.Get_Text(mappath, 1)+"/diymap/continue/"+basic.Get_Text(mappath, 2);
			file_operation.CreateDir(filepath);
			String filepath_2=basic.Get_Text(mappath, 1)+"/diymap/continue/"+basic.Get_Text(mappath, 2);
			file_operation.write(diymap,filepath_2+"/mapshuzu");
			diymap.clear();
			diymap=file_operation.read(basic.Get_Text(mappath, 1)+"/information");
			for(int i=0;i<diymap.size();i++){
				if(diymap.get(i).equals("finish")){
					diymap.insertElementAt(basic.Get_Text(mappath, 2)+"    "+width, i);
					break;
				}
				if(diymap.get(i).equals(basic.Get_Text(mappath, 2)+"    "+width)){
					break;
				}
			}
			file_operation.write(diymap, basic.Get_Text(mappath, 1)+"/information");
		}
		
	}
	
	public void Updata_UserScore(String user){
		int new_score=Integer.parseInt(basic.Get_Text(user, 2));
		String name=basic.Get_Text(user, 1);//更新分数有2个范围，总的，还有自己的，然后总的要更新排名
		Vector<String> users=new Vector<String>();
		users=file_operation.read("users_score");
		int old_score=0;
		for(int i=0;i<users.size();i++){
			if(name.equals(basic.Get_Text(users.get(i),1))){
				old_score=Integer.parseInt(basic.Get_Text(users.get(i),2));
				users.remove(i);
				break;
			}
		}
		int rank = 0;
		new_score=new_score+old_score;
		int flag=1;
		for(int i=0;i<users.size();i++){
			if(new_score>Integer.parseInt(basic.Get_Text(users.get(i),2))){
				users.insertElementAt(name+"-"+new_score+"-",i);
				rank=i+1;
				flag--;
				break;
			}
		}
		if(flag==1){
			rank=users.size()+1;
			users.add(name+"-"+new_score+"-");
		}
		file_operation.write(users,"users_score");
		users.clear();
		users=file_operation.read(name+"/information");
		String s=new_score+"-"+rank+"-"+basic.Get_Text(users.get(0),3)+"-";
		users.remove(0);
		users.insertElementAt(s,0);
		file_operation.write(users,name+"/information");
	}

	public void Upada_Successtimes(String user){
		
		Vector<String> users=new Vector<String>();
		users=file_operation.read("users_successtimes");
		int times=0;
		int flag=1;
		for(int i=0;i<users.size();i++){
			if(user.equals(basic.Get_Text(users.get(i), 1))){
				times=Integer.parseInt(basic.Get_Text(users.get(i), 2))+1;
				users.remove(i);
				break;
			}
		}
		for(int i=0;i<users.size();i++){
			if(times>Integer.parseInt(basic.Get_Text(users.get(i), 2))){
				users.insertElementAt(user+"-"+times+"-",i);
				flag--;
				break;
			}
		}
		if(flag==1){
			users.add(user+"-"+times+"-");
		}
		file_operation.write(users, "users_successtimes");
	}
	
	public void Updata_Single_Time(String user){
		String name=basic.Get_Text(user, 1);
		String new_time=basic.Get_Text(user, 2);
		Vector<String> users=new Vector<String>();
		users=file_operation.read(name+"/information");
		String old_time=basic.Get_Text(users.get(0),3);
		String s;
		if(old_time.equals("暂无")){
			s=name+"-"+basic.Get_Text(users.get(0), 2)+"-"+new_time+"-";
		}else if(Double.parseDouble(new_time)<Double.parseDouble(old_time)){
			s=name+"-"+basic.Get_Text(users.get(0), 2)+"-"+new_time+"-";
		}else{
			s=users.get(0);
		}
		users.remove(0);
		users.insertElementAt(s, 0);
		file_operation.write(users,name+"/information");
	}
	
	public void Updata_Map_Time(String s_name){
		//设计者-地图名-挑战者-时间-
		String user_name=basic.Get_Text(s_name, 1);
		String map_name=basic.Get_Text(s_name, 2);
		String chellenge_name=basic.Get_Text(s_name, 3);
		double time=Double.parseDouble(basic.Get_Text(s_name, 4));
		Vector<String> map_information=new Vector<String>();
		map_information=file_operation.read(user_name+"/diymap/finish/"+map_name+"/mapinformation");
		String s;
		int flag=1;
		if(basic.Get_Text(map_information.get(0), 5).equals("暂无")||(time<Double.parseDouble(basic.Get_Text(map_information.get(0), 5)))){
			s=map_name+"-"+user_name+"-"+basic.Get_Text(map_information.get(0), 3)+"-"+chellenge_name+"-"+time+"-";
			map_information.remove(0);
			map_information.insertElementAt(s, 0);
		}
		for(int i=1;i<map_information.size();i++){
			if(chellenge_name.equals(basic.Get_Text(map_information.get(i), 1))){
				flag--;
				if(time<Double.parseDouble(basic.Get_Text(map_information.get(i), 2))){
					String t=chellenge_name+"-"+time+"-";
					map_information.remove(i);
					map_information.insertElementAt(t, i);
					break;
				}
			}
		}
		if(flag==1){
			map_information.add(chellenge_name+"-"+time+"-");
		}
		file_operation.write(map_information,user_name+"/diymap/finish/"+map_name+"/mapinformation");
		
	}
}
