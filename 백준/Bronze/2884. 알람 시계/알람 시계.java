import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int a, b;
		a = sc.nextInt();
		b = sc.nextInt();
		if ( a == 0 && b < 45) {
			a = 24;
			System.out.println((a-1)+" "+(b+15));
		}
		else if (b >= 45) {
			System.out.println(a+" "+(b-45));
		}
		else if (b < 45) {
			System.out.println((a-1)+" "+(b+15));
		}
	}
}