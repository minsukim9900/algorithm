import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int h, m, setm, a, b;
		h = sc.nextInt();
		m = sc.nextInt();
		setm = sc.nextInt();
		
		a = (h+((m+setm)/60)) % 24;
		b = ((m+setm)%60);
		System.out.println(a+" "+b);
	}
}