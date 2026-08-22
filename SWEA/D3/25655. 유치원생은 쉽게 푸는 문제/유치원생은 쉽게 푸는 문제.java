import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int X = Integer.parseInt(br.readLine());

			if (X == 0) {
				sb.append(1).append("\n");
				continue;
			}

			if (X == 1) {
				sb.append(0).append("\n");
				continue;
			}

			List<Integer> arr = new ArrayList<>();

			while (X != 0) {
				if (X % 2 == 0) {
					X -= 2;
					arr.add(8);
				} else {
					X -= 1;
					arr.add(4);
				}
			}

			for (int i = 0; i < arr.size(); i++) {
				sb.append(arr.get(i));
			}

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}
