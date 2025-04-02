import java.io.*;
import java.util.*;

public class Main {

	private static int arr;
	private static int size, M;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		M = Integer.parseInt(br.readLine());
		arr = 0;

		for (int i = 0; i < M; i++) {

			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();

			if (order.equals("all") || order.equals("empty")) {
				select(order, 0);

			} else {
				int data = Integer.parseInt(st.nextToken());
				select(order, data);
			}

		}

		System.out.println(sb.toString());

	}

	private static void select(String order, int data) {

		switch (order) {
		case "add":
			add(data);
			break;
		case "remove":
			remove(data);
			break;
		case "check":
			check(data);
			break;
		case "toggle":
			toggle(data);
			break;
		case "all":
			all();
			break;
		case "empty":
			empty();
			break;
		}

	}

	private static void add(int data) {
		arr |= (1 << data);
	}

	private static void check(int data) {
        
		if ((arr & (1 << data)) == 0) {
			sb.append(0 + "\n");
		} else {
			sb.append(1 + "\n");
		}

	}

	private static void remove(int data) {
		arr &= ~(1 << data);
	}

	private static void toggle(int data) {
		arr ^= (1 << data);
	}

	private static void all() {
		arr = (1 << 21) - 1;
	}

	private static void empty() {
		arr = 0;
	}

}
