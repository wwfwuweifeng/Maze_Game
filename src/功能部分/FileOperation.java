package ���ܲ���;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class FileOperation {
	
		 public void write(Vector<String> input_file,String filename){
			 String filepath="d:/����2��Ϸ�ļ�/"+filename;
	//		 String filepath=filename;
			 try {
				 File file=new File(filepath);
				 if(file.exists()){
					 
				 }else{
					 file.createNewFile();
					 System.out.println("�ļ��Ѵ�������ʼд��");
				 }
				 
				BufferedWriter writer=new BufferedWriter(new FileWriter(file));
				for(int i=0;i<input_file.size();i++){
					writer.write(input_file.get(i));
					writer.newLine();
				}
				writer.flush();
				writer.close();
			} catch (Exception e) {
				System.out.println("д�����");
				System.exit(0);
			}
		 }
		
	 public Vector<String> read(String filename){
		 String filepath="d:/����2��Ϸ�ļ�/"+filename;
	//	 String filepath=filename;
	//	 System.out.println(filepath);
			Vector<String> file_out=new Vector<String>();
			String s_single;
			 try {
				 File file=new File(filepath);
				 if(file.exists()){
				//	 System.out.println("�ļ��Ѵ��ڣ���ʼ��ȡ");
				 }else{
					 file.createNewFile();
				//	 System.out.println("�ļ��Ѵ�������ʼ��ȡ");
				 }
				BufferedReader reader=new BufferedReader(new FileReader(file));
				s_single=reader.readLine();
				while(s_single!=null){
					file_out.add(s_single);
					s_single=reader.readLine();
				}
				reader.close();
		//		System.out.println("��ȡ���");
			} catch (Exception e) {
				System.out.println("��ȡ����");
				System.exit(0);
			}
			 return file_out;
		 }
	 
	 	public  void CreateDir(String destDirName) {  
	        File dir = new File(destDirName);  
	        if (dir.exists()) {  
	      //      System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�Ŀ��Ŀ¼�Ѿ�����");  
	        }else{  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //����Ŀ¼  
	        if (dir.mkdirs()) {  
	        //    System.out.println("����Ŀ¼" + destDirName + "�ɹ���");  
	        } else {  
	      //      System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�");  
	        }
	        }
	    }  
	 	
	 	public  void CreateStart() {  
	 		String	destDirName="D:/����2��Ϸ�ļ�";
	        File dir = new File(destDirName);  
	        if (dir.exists()) {  
	      //      System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�Ŀ��Ŀ¼�Ѿ�����");  
	        }else{  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //����Ŀ¼  
	        if (dir.mkdirs()) {  
	        //    System.out.println("����Ŀ¼" + destDirName + "�ɹ���");  
	        } else {  
	      //      System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�");  
	        }
	        }
	    }  
	 	public void DeletDir(File dirname){
	 		 
	 		if(dirname.exists()){
	 		if(dirname.isDirectory()){//Ϊ�ļ���
	 			File[] file_son=dirname.listFiles();
	 			for(int i=0;i<file_son.length;i++){
	 				DeletDir(file_son[i]);
	 			}
	 			dirname.delete();
	 		}else{
	 			dirname.delete();
	 		}
	 		}else{
	 			System.out.println("Ҫɾ�����ļ��в�����");
	 		}
	 	}
}
