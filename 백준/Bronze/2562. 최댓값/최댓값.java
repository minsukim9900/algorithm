import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int arr[] = new int [9];
		for (int i = 0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int max = arr[0];
		int findmax = 0;
		for (int i = 0; i<arr.length; i++) {
			if(arr[i]>=max) {
				max = arr[i];
				findmax = i+1;
			}
		}
			sb.append(max).append("\n").append(findmax);
			bw.write(sb.toString());
			bw.close();
		}
	}