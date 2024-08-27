import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[] arr1 = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<arr1.length; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int v = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 0; i<arr1.length; i++) {
			if (arr1[i] == v) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}