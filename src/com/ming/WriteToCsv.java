package com.ming;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class WriteToCsv {
	public   BufferedWriter bw;
	static ChineseName chineseName=new ChineseName();
	static IdCardGenerator g = new IdCardGenerator();  
	
	public WriteToCsv() {
		String path="D:\\data\\success.csv";
		File file=new File(path);
		try {
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
			bw.write("姓名"+","+"身份证"+","+"手机号"+ "\r\n");
			bw.flush();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public  void WriteCsv(String name,String idcard,String mobile){
		try {
			bw.write(name+","+idcard+","+mobile+ "\r\n");
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WriteToCsv csv=new WriteToCsv();
		for(int i=0;i<5000;i++){
			csv.WriteCsv(chineseName.getName(), g.generate(), (18812340000L+i)+"");
		}
		csv.close();
	}
	
	public void close(){
		try {
			System.out.println("读写完成");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GeneratorData(){
		String name=chineseName.getName();
		String idcard=g.generate();
	}
	
	public void GetInfoFromDatabase(){
		String sql="SELECT * FROM profiles WHERE created_at > '2016-07-05' LIMIT 8000";
	}
}
