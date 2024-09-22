import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(0);
		nums.add(1);

		if (N == 0 || N == 1) {
			System.out.println(N);
		} else {
			for(int i = 2; i<=N; i++) {
				nums.add(nums.get(i-1)+nums.get(i-2));
			}
			System.out.println(nums.get(N));
		}

	}

}