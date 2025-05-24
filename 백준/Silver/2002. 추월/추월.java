import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static String[] carInfo;
	private static HashMap<String, Integer> in = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		carInfo = new String[N];

		for (int i = 0; i < N; i++) {
			carInfo[i] = br.readLine();
			in.put(carInfo[i], i);
		}

		int cnt = 0;
		int min = 0;

		for (int i = 0; i < N; i++) {
			String currCar = br.readLine();
			int preOrder = in.get(currCar);

			if (preOrder != 0) {
				cnt++;
			}

			for (String car : carInfo) {
				int order = in.get(car);
				if (order > preOrder) {
					in.put(car, order - 1);
				}
			}
		}
		System.out.println(cnt);
	}
}