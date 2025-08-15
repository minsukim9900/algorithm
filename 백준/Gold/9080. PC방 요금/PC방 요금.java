import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			String[] infos = st.nextToken().split(":");
			int useTime = Integer.parseInt(st.nextToken());

			int currTime = Integer.parseInt(infos[0]) * 60 + Integer.parseInt(infos[1]);

			int hour = 0;
			while (useTime > 0) {
				if (currTime < 180) {
					if (useTime > 240) {
						useTime -= 480 - currTime;
						currTime = 481;
						hour += 5;
					} else {
						hour += (useTime + 59) / 60;
						useTime = 0;
					}
					continue;
				}

				if (currTime >= 60 * 22) {
					if (useTime > 240) {
						useTime -= ((24 * 60) - currTime + 480);
						currTime = 481;
						hour += 5;
					} else {
						hour += (useTime + 59) / 60;
						useTime = 0;
					}
					continue;
				}

				useTime -= 60;
				currTime += 60;
				hour++;
			}
			System.out.println(hour * 1000);
		}
	}
}