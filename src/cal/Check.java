package cal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Check {
	public static void check(String str) throws Exception {
		BufferedReader br1=null;
		String line= "";
		String line2= "(";
		String line3= "(";
		String line4=null;
		String line5=null;
		int a=0;
		int b=0;
		String[] path = str.split(" ");
		String[] path2 = null;
		FileWriter fw = new FileWriter("D:/cal/Grade.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		FileReader fr = new FileReader(path[0]);
		BufferedReader br = new BufferedReader(fr);
		
		if(path.length>1) {
			FileReader fr1 = new FileReader(path[1]);
			br1 = new BufferedReader(fr1);
		}
		for(int i=0;(line = br.readLine())!=null;i++) {
			path = line.split("=");
			if(path.length>1) {
				line4 = path[1];
			}
			path2 = br1.readLine().split("\\.");
			if(path2.length>1) {
				line5 = path2[1];
			}
			if(line4.equals(line5)){
				line2 = line2 + i+",";
				a++;
			}else {
				line3 = line3 + i+",";
				b++;
			}
		}
		line2=line2.substring(0,line2.length()-1);
		line3=line3.substring(0,line3.length()-1);
		bw.write("Correct: "+ a + line2 +")");
		bw.newLine();
		bw.write("Wrong: "+ b + line3 +")");
		bw.close();
	}	
}
