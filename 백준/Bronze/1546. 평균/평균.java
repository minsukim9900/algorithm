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
		
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double score[] = new double [N];
		for(int i = 0; i<score.length; i++) {
			score[i] = Double.parseDouble(st.nextToken());
		}
		
		double max = score[0];
		for(int i = 0; i<N; i++) {
			if(max < score[i]) {
				max = score[i];
			}
		}
		double sum = 0;
		for(int i = 0; i<score.length; i++) {
			score[i] = score[i]/max*100;
			sum = sum + score[i];
		}
		
		double deg = sum / N;
		
		System.out.println(deg);
		
		
	}
}