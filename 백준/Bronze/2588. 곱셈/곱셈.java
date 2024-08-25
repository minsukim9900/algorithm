import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int a,b,c,d,e;
		a = sc.nextInt();
		b = sc.nextInt();
		c = b % 10; // b 1의 자리 숫자
		d = ((b%100) / 10)*10; //b 10의 자리 숫자 * 10
		e = (b / 100)*100; //b 100의 자리 숫자 * 100
		
		System.out.println(a*c);
		System.out.println(a*d/10);
		System.out.println(a*e/100);
		System.out.println((a*c)+(a*d)+(a*e));
		
	}
	
}