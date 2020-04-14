package cal;

import java.io.File;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		int flag=1;
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		yunsuan xx = new yunsuan();
		String inStr="aaa";
		File file=null;
		int a=0;
		int b=0;
		int c=0;
		while(flag==1){
			System.out.println("请输入命令,1:生成题目,2:校验答案是否正确 ,3:退出");
			if(sc.hasNextInt()){
				b = sc.nextInt();
			}
				switch(b) 
				{
					case 1:
						System.out.println("请输入数值以限定生成题目数值范围,格式:-r 10");
						if(sc1.hasNextLine()){
							inStr = sc1.nextLine();
						}
						String[] path = inStr.split(" ");
						if(path.length>1) {
							xx.num=Integer.parseInt(path[1]);
						}
						System.out.println("请输入数值以限定生成题目数量,格式:-n 10");
						if(sc1.hasNextLine()){
							inStr = sc1.nextLine();
						}
						path = inStr.split(" ");
						if(path.length>1) {
							c=Integer.parseInt(path[1]);
						}
					try {
						yunsuan.create(c,xx);
					} catch (Exception e) {
						e.printStackTrace();
					}
						break;
					case 2:
						System.out.println("请输入校验文件,格式:exercisefile.txt answerfile.txt");
						if(sc1.hasNextLine()){
							inStr = sc1.nextLine();
						}
					try {
						Check.check(inStr);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
					case 3:
						flag=0;
						break;
					default:
						System.out.println("格式错误,请重新输入!");
				}
			} 
	}
}
