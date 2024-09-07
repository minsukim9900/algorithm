import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int count = 1;
		
		int C = N;
		
		while(C >= B) {
			count++;
			C/=B;
		}
		
		char[] room = new char[count];
		
		for(int i = room.length-1; i>=0; i--) {
			if((N%B)>=10 && (N%B)<=35) {
				room[i] = (char)((N%B)-10+'A');
				N = N/B;
			}
			else {
				room[i]=(char)((N%B)+'0');
				N = N/B;
			}
		}
		
		for(int i = 0; i<room.length; i++) {
			System.out.print(room[i]);
		}
		
	}
}