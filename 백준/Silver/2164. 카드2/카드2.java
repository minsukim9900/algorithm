import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Queue<Integer> cards = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 1; i<=N; i++) {
			cards.add(i);
		}
		
		int result = 0;
		while(true) {
			if(cards.isEmpty()) {
				break;
			}
			else {
				if(cards.isEmpty()) {
					break;
				}
				result = cards.poll();
				if(cards.isEmpty()) {
					break;
				}
				cards.add(cards.poll());
			}
		}
		System.out.println(result);
	}
}