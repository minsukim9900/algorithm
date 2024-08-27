import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st, sy;
	
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());		
		int x = Integer.parseInt(st.nextToken());
		
		sy = new StringTokenizer(br.readLine());
		
		int arr[] = new int[N];
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(sy.nextToken());
			if(arr[i] < x) {
				sb.append(arr[i]+" ");
			}
		}
		bw.write(sb.toString());
		bw.close();
		
		
	}
}