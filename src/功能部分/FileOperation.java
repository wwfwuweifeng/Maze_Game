package 功能部分;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class FileOperation {
	
		 public void write(Vector<String> input_file,String filename){
			 String filepath="d:/课设2游戏文件/"+filename;
	//		 String filepath=filename;
			 try {
				 File file=new File(filepath);
				 if(file.exists()){
					 
				 }else{
					 file.createNewFile();
					 System.out.println("文件已创建，开始写入");
				 }
				 
				BufferedWriter writer=new BufferedWriter(new FileWriter(file));
				for(int i=0;i<input_file.size();i++){
					writer.write(input_file.get(i));
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (Exception e) {
				System.out.println("写入出错");
				System.exit(0);
			}
		 }
		
	 public Vector<String> read(String filename){
		 String filepath="d:/课设2游戏文件/"+filename;
	//	 String filepath=filename;
	//	 System.out.println(filepath);
			Vector<String> file_out=new Vector<String>();
			String s_single;
			 try {
				 File file=new File(filepath);
				 if(file.exists()){
				//	 System.out.println("文件已存在，开始读取");
				 }else{
					 file.createNewFile();
				//	 System.out.println("文件已创建，开始读取");
				 }
				BufferedReader reader=new BufferedReader(new FileReader(file));
				s_single=reader.readLine();
				while(s_single!=null){
					file_out.add(s_single);
					s_single=reader.readLine();
				}
				reader.close();
		//		System.out.println("读取完成");
			} catch (Exception e) {
				System.out.println("读取出错");
				System.exit(0);
			}
			 return file_out;
		 }
	 
	 	public  void CreateDir(String destDirName) {  
	        File dir = new File(destDirName);  
	        if (dir.exists()) {  
	      //      System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
	        }else{  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //创建目录  
	        if (dir.mkdirs()) {  
	        //    System.out.println("创建目录" + destDirName + "成功！");  
	        } else {  
	      //      System.out.println("创建目录" + destDirName + "失败！");  
	        }
	        }
	    }  
	 	
	 	public  void CreateStart() {  
	 		String	destDirName="D:/课设2游戏文件";
	        File dir = new File(destDirName);  
	        if (dir.exists()) {  
	      //      System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
	        }else{  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //创建目录  
	        if (dir.mkdirs()) {  
	        //    System.out.println("创建目录" + destDirName + "成功！");  
	        } else {  
	      //      System.out.println("创建目录" + destDirName + "失败！");  
	        }
	        }
	    }  
	 	public void DeletDir(File dirname){
	 		 
	 		if(dirname.exists()){
	 		if(dirname.isDirectory()){//为文件夹
	 			File[] file_son=dirname.listFiles();
	 			for(int i=0;i<file_son.length;i++){
	 				DeletDir(file_son[i]);
	 			}
	 			dirname.delete();
	 		}else{
	 			dirname.delete();
	 		}
	 		}else{
	 			System.out.println("要删除的文件夹不存在");
	 		}
	 	}
}
