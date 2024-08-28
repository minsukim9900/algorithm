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
		StringTokenizer st, sy;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N];
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+1;
		}
		
		for(int i=0; i<M; i++) {
			sy = new StringTokenizer(br.readLine());
			int I = Integer.parseInt(sy.nextToken())-1; //0부터 시작하기 때문에 값이 들어오면 1보다 작은 방으로 보내야 돼 그래서 토큰화 하고 -1시켰습니다.
			int J = Integer.parseInt(sy.nextToken())-1; //위와 동일
			int K = arr[I]; // I랑 J랑 위치를 바꾸려면 변수가 하나 더 있어야 하고 그 변수가 I랑 J랑 두 개중에 같으면 되서 이렇게 설정 해놨습니다.
			
			arr[I] = arr[J];
			arr[J] = K;
			
			
			
		}
		
		
		
		for(int i=0; i<arr.length; i++) {
			sb.append(arr[i] + " ");
		}
		
		bw.write(sb.toString());
		bw.close();
	}
}