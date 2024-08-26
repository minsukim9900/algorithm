import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for (int i = 0; i < (N/4) ; i++) {
		String ts = "long ";
		System.out.print(ts);
		}
		System.out.println("int");
	}
}