package 功能部分;

import java.util.Vector;

public class Basic_Function {
	
	public FileOperation file_operation=new FileOperation();
	public int address_account=1,address_name=2,address_pass=3;
	
	//返回计时器的文本格式
	public String Get_LbTime(String time){
		String s1=time.substring(0,time.indexOf("."));
		String s2=time.substring(time.indexOf(".")+1);
		int num_1=Integer.parseInt(s1)*10;
		int num_2=Integer.parseInt(s2)+1;
		int num=num_1+num_2;
		return num/10+"."+num%10;
	}
	
	public String Get_LbSize(int width){
		
		int row=500/width;
		int col=800/width;
		if(row%2==0)row++;
		if(col%2==0)col++;
		return row+"*"+col;
	}
	
	//单人模式中的分数
	public String Get_LbScore(int width,String old_score,String time){
		int row=500/width;
		int col=800/width;
		if(row%2==0)row++;
		if(col%2==0)col++;
		int new_score=(int) (row*col/Double.parseDouble(time))*(20/width);
		new_score=new_score+Integer.parseInt(old_score);
		return new_score+"";
	}
	
	//双人模式中的分数面板
	public String Get_LbLeveScore(int width){
		
		return (int)(400/width)+"";
	}
	
	public String Get_P1orP2Score(String add_score,String old_score){
		
		int new_score=Integer.parseInt(add_score)+Integer.parseInt(old_score);
		
		return new_score+"";
	}
	
	
	//用于从一长串字符串中，提取对应位置的字符串
	public String Get_Text(String first_s,int address){
			int i=address;
			String t=first_s;
			while(i>1)
			{
				i--;
				t=t.substring(t.indexOf('-')+1);
			}
			t=t.substring(0, t.indexOf('-'));
			
			return t;
		}
	

	//判断登录信息是否正确
	public String Login_Judge(String tx_get){
			
			Vector<String> user_order=new Vector<String>();
			user_order=file_operation.read("users_order");
			String single_user;
			String user_information="";
			for(int i=0;i<user_order.size();i++)
			{
				//账号-密码-类型
				single_user=Get_Text(user_order.get(i),address_account)+"-"+Get_Text(user_order.get(i),address_pass)+"-";
				if(tx_get.equals(single_user)){
					user_information=Get_Text(user_order.get(i),address_name);
					return user_information;
				}
			}
			return "no";
		}
	
	
	//注册的提交
	public int Register_Submit(String rigester){
		
		rigester.replace(" ", "");
		String[] rigester_s=rigester.split("-");
		for(int i=0;i<rigester_s.length;i++){
			if(rigester_s[i].equals("")){
				return 0;//信息未填写完整
			}
		}
		Vector<String> user_name=new Vector<String>();
		user_name=file_operation.read("users_order");
		
		if(rigester_s[2].equals(rigester_s[3])){
			for(int i=0;i<user_name.size();i++){
				if(rigester_s[0].equals(Get_Text(user_name.get(i),address_account))){
					return 2;//该账号已存在
				}
			}
			for(int i=0;i<user_name.size();i++){
				if(rigester_s[1].equals(Get_Text(user_name.get(i), address_name))){
					return 3;//该用户名已被注册
				}
			}
			return 4;//符合条件
			
		}else{
			return 1;//两次密码不一致
		}
			
	}	

	//获取验证码
	public String Get_Random(){
		
		int count=4;
		String s="";
		while(count>0){
			int num=48+(int)(Math.random()*75);
			if(num<=57||(num>=65&&num<=90)||(num>=97&&num<=122)){
				s=s+((char)num);
				count--;
			}
		}
		return s;
		}
	

	public int Judge_ChangePass(String user){
		Vector<String> file_getname=new Vector<String>();
		String[] single_user=user.split("-");
		file_getname=file_operation.read("users_order");
		int address = 0;
		for(int i=0;i<single_user.length;i++){
			if(single_user[i].equals("")){
				return 1;//信息没填写完整
			}
		}
		
		int flag_1=0;
		for(int i=0;i<file_getname.size();i++){
			if(Get_Text(file_getname.get(i),address_account).equals(single_user[0])){
				flag_1=1;
				break;
			}
		}
		if(flag_1!=1){
			return 2;   //账户不存在
		}
		String name=single_user[0]+"-"+single_user[1];
		String name_2="";
		int flag_2=0;
		for(int i=0;i<file_getname.size();i++){
			name_2=Get_Text(file_getname.get(i),address_account)+"-"+Get_Text(file_getname.get(i),address_name);
			if(name.equals(name_2)){
				address=i;
				flag_2=1;
				break;
			}
		}
		if(flag_2!=1){
			return 3;//姓名错误
		}
		
		if(!single_user[2].equals(single_user[3])){
			return 4; //两次密码不一致
		}
		String user_change=single_user[0]+"-"+single_user[1]+"-"+single_user[2]+"-";
		file_getname.remove(address);
		file_getname.insertElementAt(user_change,address);
		file_operation.write(file_getname, "users_order");
		return 5; //一切正确
	}
	
	public String Get_UserInformation(String username){
		String path=username+"/information";
		Vector<String> information=new Vector<String>();
		information=file_operation.read(path);
		return information.get(0);
	}
	
	public Vector<String> Get_MyMap(String username){
		Vector<String> mapname=new Vector<String>();
		String path=username+"/information";
		mapname=file_operation.read(path);
		mapname.remove(0);
		return mapname;
	}
	
	public boolean Build_NewMap(String mapname){
		Vector<String> information=new Vector<String>();
		String path=Get_Text(mapname, 1)+"/information";
		information=file_operation.read(path);
		String name=Get_Text(mapname, 2);
		int j=0;
		for(int i=0;i<information.size();i++){
			j=information.get(i).indexOf(" ");
			if(j!=-1&&name.equals(information.get(i).substring(0,j))){
				return false;
			}
		}
		return true; //该地图名字符合要求	
	}


	public Vector<String> Get_UserName(String myselef){
		Vector<String> user_first=new Vector<String>();
		user_first=file_operation.read("users_score");
		Vector<String> username=new Vector<String>();
		for(int i=0;i<user_first.size();i++){
			if(!myselef.equals(Get_Text(user_first.get(i), 1))){
				username.add(Get_Text(user_first.get(i),1));
			}
		}
		
		return username;
	}

	public String Get_MapInformation(String select){
		
		String[] name=select.split("-");
		Vector<String> mapinformation=new Vector<String>();
		mapinformation=file_operation.read(name[1]+"/diymap/finish/"+name[2]+"/mapinformation");
		for(int i=1;i<mapinformation.size();i++){
			if(name[0].equals(Get_Text(mapinformation.get(i),1))){
				return mapinformation.get(0)+Get_Text(mapinformation.get(i),2)+"-";
			}
		}
		return mapinformation.get(0)+"暂无-";
		
	}

	public int[][] Get_DiyMap(String map,int select){
		String s;
		if(select==1){
			s="/diymap/finish/";
		}else{
			s="/diymap/continue/";
		}
		String mappath=Get_Text(map, 1)+s+Get_Text(map, 2)+"/mapshuzu";//设计师-迷宫名字-难度
		Vector<String> mapshuzu=new Vector<String>();
		mapshuzu=file_operation.read(mappath);
		int width=Integer.parseInt(Get_Text(map, 3));
		int row_i=500/width;
		int col_j=800/width;
		if(row_i%2==0)row_i++;
		if(col_j%2==0)col_j++;
		int[][] diymap=new int[row_i][col_j];
		for(int i=0;i<mapshuzu.size();i++){
			String[] row_shuzu=mapshuzu.get(i).split("-");
			for(int j=0;j<col_j;j++){
				diymap[i][j]=Integer.parseInt(row_shuzu[j]);
			}
		}
		return diymap;
	}
	
}
