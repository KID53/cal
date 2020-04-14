package cal;
import java.util.Stack;


/**
 * @author YDK �����׺���ʽ
 *
 */
public class Count {
	public static void main(String[] args) {

	}
	public static String count(String str1) {
		//System.out.println(str1);
		String str=Transform.creatSuffixExepression(str1);
		char x='b';
		boolean haveNum= false;//�Ƿ�������
		boolean haveFraction= false;//�Ƿ�Ϊ����
		int tmp=0;//��������Ҳ�䵱��ĸ
		int numer=0;//����
		int div=0;//���Լ��
		Num a=null;
		Num b=null;
		Num c=null;
		// System.out.println(str);
		Stack<Num> number= new Stack<>();
		for(int i=0; i<str.length();i++) {
			x=str.charAt(i);
			if(isNum(x)) {
					haveNum=true;
					tmp=tmp*10+(x-'0');
				}else {
					if(haveNum) {
						haveNum= false;
						if(isSpace(x)) {
							if(haveFraction) {
								Num xx= new Num(numer,tmp);
								number.push(xx);//�������ջ
								tmp=0;
							}else {
								Num xx= new Num(tmp,1);
								number.push(xx);//��������ջ
								tmp=0;
							}
						}else {
							haveFraction= true;
							numer=tmp;
							tmp=0;
							continue;
						}
					}
					if(x=='+') { //���²��������ַ������ջ�е������������м���
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c = add(a,b);
					}else if(x=='-') {
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c=subtract(a, b);
					}else if(x=='*') {
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c=mul(a, b);
					}else if(x=='/') {
						b = number.peek();
						number.pop();
						a = number.peek();
						number.pop();
						c=division(a, b);
					}else if(x==' ') {
						if(haveFraction) {
							haveFraction = false;
						}
						continue;
					}
					number.push(c);
				}
			}
		if (number.size() > 1) {
			System.out.println("����"); //������в�������������Ӧ��������ջ�ڻ��ж��Ԫ�أ���˵������
		}
		Num z=number.peek();
		if (z.numerator != 0) {               //�����Ӳ�Ϊ0����Է��ӷ�ĸԼ�� 
			div = gcd(c.denominator, c.numerator);
			z.numerator /= div;
			z.denominator /= div;
		}
		number.pop();
		// System.out.println(z.numerator+"/"+z.denominator);
		return Count.transform(z.numerator, z.denominator);
	}
	
	public static boolean isNum(char x) { //�Ƿ�Ϊ����
		return(x>='0'&&x<='9');
	}
	
	public static Num add(Num a, Num b) { //�ӷ�
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.denominator + b.numerator * a.denominator;
		c.denominator = a.denominator * b.denominator;
		return c;
	}
	
	public static Num subtract(Num a, Num b) { //����
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.denominator - b.numerator * a.denominator;
		c.denominator = a.denominator * b.denominator;
		return c;
	}
	
	public static Num mul(Num a, Num b) { //�˷�
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.numerator;
		c.denominator = a.denominator * b.denominator;
		return c;
	}
	
	public static Num division(Num a, Num b) { //����
		Num c= new Num(1,1);
		c.numerator = a.numerator * b.denominator;
		c.denominator = a.denominator * b.numerator;
		return c;
	}
	
	public static int gcd(int a,int b) { //շת����������Լ��
		{
		    int c=a % b;
		    while(c!=0)
		    {
		        
		        a = b;
		        b = c;
		        c = a % b;
		    }
		    return b;
		}
	}
	public static boolean isSpace(char x) {
		return(x==' '||x=='*'||x=='+'||x=='-'); //�жϲ�Ϊ��������;
	}
	
	public static String transform(int i, int j) {
		if(i>=j) {
			if(j==1) {
				return i+"";
			}
			return i/j+"'"+i%j+"/"+j;
		}else {
			return i+"/"+j;
		}
	}
}
