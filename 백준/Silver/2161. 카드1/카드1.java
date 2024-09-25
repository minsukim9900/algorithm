import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> card	= new LinkedList<>();
		
		for(int i =1; i<=N; i++) {
			card.add(i);
		}
		
		int temp = 0;
		for(int i = 0; i<N-1; i++) {
			sb.append(card.poll()).append(" ");
			temp = card.poll();
			card.add(temp);
		}
		
		sb.append(card.poll());
		
		System.out.println(sb.toString());
	}
}