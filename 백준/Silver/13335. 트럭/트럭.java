import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭 수
		int w = Integer.parseInt(st.nextToken()); // 다리의 길이
		int L = Integer.parseInt(st.nextToken()); // 다리의 최대하중

		// 다리 만들기
		Queue<Integer> bridge = new ArrayDeque<>();
		for (int i = 0; i < w; i++) {
			bridge.add(0);
		}

		st = new StringTokenizer(br.readLine());

		// 배열에 트럭 넣기
		int[] trucks = new int[n];
		for (int i = 0; i < n; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}

		int truck = 0; // 트럭의 인덱스 번호
		int loadWeight = 0; // 다리에 올라온 트럭 하중
		int time = 0;
		while (truck < n) {
			
			// 앞에 있는 값 빼 loadWeight에서도 빼
			loadWeight -= bridge.poll();
			time++;
			if(loadWeight + trucks[truck] >L) {
				bridge.add(0);
			}
			else {
				bridge.add(trucks[truck]);
				loadWeight += trucks[truck];
				truck++;
			}
			
			
			
			if(truck >=n) {
				break;
			}
		}
		time += w;
		System.out.println(time);
	}
}