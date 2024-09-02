import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int AsNum1 [] = new int[3];
		String num1 = st.nextToken();
		for(int i =0; i<3; i++) {
			AsNum1[i]=num1.charAt(i)-'0';
		}
		
		int temp1 = AsNum1[0];
		AsNum1[0] = AsNum1[2];
		AsNum1[2] = temp1;
		
		int a = AsNum1[0]*100+AsNum1[1]*10+AsNum1[2];
		
		int AsNum2 [] = new int[3];
		String num2 = st.nextToken();
		for(int i =0; i<3; i++) {
			AsNum2[i]=num2.charAt(i)-'0';
		}
		
		int temp2 = AsNum2[0];
		AsNum2[0] = AsNum2[2];
		AsNum2[2] = temp2;
		
		int b = AsNum2[0]*100+AsNum2[1]*10+AsNum2[2];
		
		if(a>=b) {
			System.out.println(a);
		}
		else {
			System.out.println(b);
		}
		
	}
}