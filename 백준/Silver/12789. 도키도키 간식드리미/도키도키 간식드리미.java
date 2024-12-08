import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] preLine;
	private static Stack<Integer> line = new Stack<>();
	private static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		preLine = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			preLine[i] = Integer.parseInt(st.nextToken());
		}

		process();
		if (check) {
			System.out.println("Nice");
		} else {
			System.out.println("Sad");
		}
	}

	private static void process() {
		int num = 1;
		
		for (int i = 0; i < N; i++) {
			
			while(!line.isEmpty()) {
				if(line.peek() == num) {
					line.pop();
					num++;
				}else {
					break;
				}
			}

			if (num == preLine[i]) {
				num++;
				continue;
			}

			line.push(preLine[i]);

		}
		while(!line.isEmpty()) {
			if(line.peek() == num) {
				line.pop();
				num++;
			}else {
				break;
			}
		}
		if (num >= N)

		{
			check = true;
		}
	}

}
