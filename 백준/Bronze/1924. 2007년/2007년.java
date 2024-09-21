import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int month = Integer.parseInt(st.nextToken());
		int mday = Integer.parseInt(st.nextToken());
		int result = sum_day(month, mday);
		
		switch(result) {
		case 0:
			System.out.println("SUN");
			break;
		case 1:
			System.out.println("MON");
			break;
		case 2:
			System.out.println("TUE");
			break;
		case 3:
			System.out.println("WED");
			break;
		case 4:
			System.out.println("THU");
			break;
		case 5:
			System.out.println("FRI");
			break;
		case 6:
			System.out.println("SAT");
			break;
		
		}
			
		
	}

	public static int sum_day(int num1, int num2) {
		int day = 0;
		if (num1 == 1) {
			day = num2 % 7;
		} else if (num1 == 2) {
			day = (31 + num2) % 7;
		} else if (num1 == 3) {
			day = (59 + num2) % 7;
		} else if (num1 == 4) {
			day = (90 + num2) % 7;
		} else if (num1 == 5) {
			day = (120 + num2) % 7;
		} else if (num1 == 6) {
			day = (151 + num2) % 7;
		} else if (num1 == 7) {
			day = (181 + num2) % 7;
		} else if (num1 == 8) {
			day = (212 + num2) % 7;
		} else if (num1 == 9) {
			day = (243 + num2) % 7;
		} else if (num1 == 10) {
			day = (273 + num2) % 7;
		} else if (num1 == 11) {
			day = (304 + num2) % 7;
		} else if (num1 == 12) {
			day = (334 + num2) % 7;
		}

		return day;
	}
}