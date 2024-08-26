import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int first, second, third;
		first = sc.nextInt();
		second = sc.nextInt();
		third = sc.nextInt();
		
		if ((first == second) && (first == third)) {
			System.out.println(10000+(first*1000));
		}
		else if (first == second || first == third) {
			System.out.println(1000+(first*100));
		}
		else if (second == third) {
			System.out.println(1000+(second*100));
		}
		
		else if ((first != second) && (first != third) && (second != third)) {
			if(first >second && first > third) {
				System.out.println(first *100);
			}
			else if(second > first && second > third) {
				System.out.println(second*100);
			}
			else {
				System.out.println(third*100);
			}
		}
	}
}