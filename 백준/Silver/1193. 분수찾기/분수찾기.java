import java.io.*;
import java.util.*;

public class Main {
	private static int X;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		X = Integer.parseInt(br.readLine());

		int line = 0;
		int count = 0;

		while (count < X) {
			line++;
			count = line * (line + 1) / 2;
		}

		if (line % 2 == 0) {
			int bunza = line - (count - X);
			int bunmo = 1 + (count - X);
			System.out.println(bunza + "/" + bunmo);

		} else {
			int bunza = 1 + (count - X);
			int bunmo = line - (count - X);
			System.out.println(bunza + "/" + bunmo);
		}
	}
}