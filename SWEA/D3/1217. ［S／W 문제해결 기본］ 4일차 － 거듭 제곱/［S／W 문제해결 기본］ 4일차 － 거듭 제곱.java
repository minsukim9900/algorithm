import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
        
		Scanner sc = new Scanner(System.in);

		for(int t = 1; t<=10; t++) {
			int i = sc.nextInt();
			int num = sc.nextInt();
			int depth = sc.nextInt();
			System.out.println("#"+t+" "+factorial(num, depth));
		}
	}
	
	private static int factorial(int num, int depth) {
		if(depth == 1) {
			return num;
		}else if(depth == 0) {
			return 1;
		}
		
		int tmp = factorial(num, depth/2);
		
		if(depth % 2 == 0) {
			return tmp * tmp;
		}else {
			return num * tmp * tmp;
		}
		
	}

}
