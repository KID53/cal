package cal;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;


/**
 * @author YDK 生成中缀表达式
 *
 */
public class yunsuan{
	int num;//式子中数字小于num

	public String jiafenshu(String a) {//将真分数转化为假分数
		String b[] = a.split("'");
		String c[] = b[1].split("/");
        int d = Integer.parseInt(b[0]);
        int e = Integer.parseInt(c[0]);//分子
        int f = Integer.parseInt(c[1]);//分母
        int g = d * f + e;
        String h = g + "/" + f;
        return h;
	}
	
	public String Shu() {//生成整数或分数
		Random rand = new Random();
		int a = rand.nextInt(num - 1);
		int b = rand.nextInt(4);//分子
		int c = rand.nextInt(6) + b;//分母
		String d = Integer.toString(a);
		if(b >= 3) {
			if(d.equals("0")) {
				d = b + "/" + c;
			}
			else 
				d = a + "'" + b + "/" + c;
		}
		if(d.equals("0")) d = Integer.toString(1);
		return d;
}

	public String fuhao() {//随机生成运算符号
		Random rand = new Random();
		int a = rand.nextInt(4);
		String str = null;
		if(a == 0)
			str = "+";
		else if(a == 1)
			str = "-";
		else if(a == 2)
			str = "*";
		else 
			str = "/";
		return str;
	}
	
    public String timu(){//生成表达式
		 String str = "";
		 String str1=""; 
		 do { 
			str = "";
			str1 = "";
		 int t = 0;
		 Random rand = new Random();
		 t = rand.nextInt(3) + 2;
		 String[] number = new String[t];//存放数字
		 String[] symbol = new String[t-1];//存放运算符号
		 String[] total = new String[4*t-3];//存放式子
		 String[] total1 = new String[4*t-3];
		 for(int i = 0;i < t;i++) {
			 number[i] = Shu();
		 }
		 for(int i = 0;i < t-1;i++) {
			 symbol[i] = fuhao();
		 }
		 for(int i = 0;i < 4*t - 3;i++) {
			 if(i%4 == 0) {
				 total[i] = number[i/4];
				 total1[i] = number[i/4];
			 }
			 else if(i%4 == 2) {
				 int k = (i+2)/4 - 1;
				 total[i] = symbol[k];
				 total1[i] = symbol[k];
			 }
			 else {total[i] = " ";
			       total1[i] = " ";
			 }
		 }
		 for(int i = 0;i < 4*t - 3;i++) {
			 if(total1[i].contains("'")) {
				total1[i] = jiafenshu(total1[i]);
			 }
		 }
		 if(t == 4) {
			 if((symbol[1] == "*"||symbol[1] == "/") && (symbol[0] == "+"||symbol[0] == "-")) {
				                      total[0] = "(" + total[0];
                                      total[4] = total[4] + ")"; 
                                      total1[0] = "(" + total1[0];
                                      total1[4] = total1[4] + ")"; 
			                                    }
			 if((symbol[1] == "*"||symbol[1] == "-") && (symbol[2] == "+"||symbol[2] == "-")) {
				 total[8] = "(" + total[8];
                 total[12] = total[12] + ")";
                 total1[8] = "(" + total1[8];
                 total1[12] = total1[12] + ")";
			 }
		 }
		 for(int i = 0;i < 4*t - 3;i++) {
			 str = str + total[i];
			 str1 = str1 + total1[i];
		 }
		 }while(Count.count(str1).contains("-"));
		 // System.out.println(str);
		 return str+" ="+";"+str1;
	}
    
    public static void create(int d,yunsuan a) throws Exception{
    	String s="";
		FileWriter fw = new FileWriter("D:/cal/Exercises.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		FileWriter fw1 = new FileWriter("D:/cal/Answers.txt");
		BufferedWriter bw1 = new BufferedWriter(fw1);
    	for(int i=0; i < d ; i++) {
    		String[] path = a.timu().split(";");
    		bw.write(i+". "+path[0]);
    		bw.newLine();
    		bw.flush();
    		
    		bw1.write(i+". " + Count.count(path[1]));
    		bw1.newLine();
    		bw1.flush();
    		
    		// System.out.println(i+". "+path[0])
			//System.out.println(i+". " + Count.count(path[1]));
    	}
    	bw.close();
    	bw1.close();
    }
}
