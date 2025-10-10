import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String[] str = br.readLine().split("-");

		long answer = 0;
		boolean isFirst = true;
		for (String s : str) {
			String[] x = s.split("\\+");
			long sum = 0L;

			for (int i = 0; i < x.length; i++) {
				sum += Integer.parseInt(x[i]);
			}

			if (isFirst) {
				isFirst = false;
				answer += sum;
			} else {
				answer -= sum;
			}
		}
		
		System.out.println(answer);
	}
}
