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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int bucket[] = new int[N+1];
		for(int i=1; i<bucket.length; i++) {
			bucket[i]=i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); //처음 인덱스
			int b = Integer.parseInt(st.nextToken()); //끝 인덱스
			
			while(a<b) {
				int temp = bucket[a];
				bucket[a] = bucket[b];
				bucket[b] = temp;
				
				a++;
				b--;
			}			
			
						
		}
		
		for(int i=1; i<bucket.length; i++) {
			sb.append(bucket[i]).append(" ");
		}
		bw.write(sb.toString());
		bw.close();
	}
}