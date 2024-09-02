import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int chess [] = {1,1,2,2,2,8};
		int whitchess[] = new int [6];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<whitchess.length; i++) {
			whitchess[i]= Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<whitchess.length; i++) {
			System.out.print(chess[i]-whitchess[i]+" ");
		}
	}
}