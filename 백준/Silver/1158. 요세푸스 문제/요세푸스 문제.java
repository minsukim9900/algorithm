import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> circle = new LinkedList<>();
		int[] temp = new int[N];
		
		for (int i = 1; i <= N; i++) {
			circle.add(i);
		}
		
		int count = 0;
		int i = 0;
		while(true) {
			if(circle.isEmpty()) {
				break;
			}
			else if(count==K-1){
				temp[i]=(circle.poll());
				i++;
				count=0;
			}
			else {
				count++;
				circle.add(circle.poll());
			}
		}
		sb.append("<");
		for(int j = 0; j<temp.length-1; j++) {
			sb.append(temp[j]).append(", ");
		}
		sb.append(temp[temp.length-1]);
		sb.append(">");
		
		System.out.println(sb.toString());
	}
}