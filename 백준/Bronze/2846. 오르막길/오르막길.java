import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] hill = new int[N];
		int[] uphill = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			hill[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			if ((i + 1) != N) {
				uphill[i] = hill[i + 1] - hill[i];
			}
		}
		
		int sum = 0;
		int max = -1;
		for (int j = 0; j < uphill.length; j++) {
			if (uphill[j] > 0) {
				sum += uphill[j];
			} else if (uphill[j] <= 0) {
				if (max < sum) {
					max = sum;
				}
				sum = 0;
				continue;
			}
		}
		System.out.println(max);

	}
}