import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Stack<Integer> score = new Stack<>();
		Stack<Integer> time = new Stack<>();

		int homework = 0;
		int totalScore = 0;
		int N = Integer.parseInt(br.readLine());
		int totalTime = N;

		out: for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 과제 여부 확인 ( 1이면 과제가 있고, 0이면 과제가 들어오지 않음)
			int process = Integer.parseInt(st.nextToken());
			if (process == 1) {
				int subjectScore = Integer.parseInt(st.nextToken());
				int subjectTime = Integer.parseInt(st.nextToken()) - 1;
				if(subjectTime == 0) {
					totalScore += subjectScore;
				}
				else {
					score.push(subjectScore);
					time.push(subjectTime);
				}
				totalTime--;
			} else if (process == 0) {
				if(score.isEmpty()) {
					continue;
				}
				else {
					int tmp = time.pop();
					tmp--;
					if(totalTime >=0  && tmp == 0) {
						totalScore += score.pop();
					}
					else {
						time.push(tmp);
					}
					totalTime--;
				}
			}

		}

		if ( !score.isEmpty() && !time.isEmpty() && time.peek() == 0 && totalTime>=0) {
			totalScore += score.pop();
		}
		// System.out.println(score + " " + time);
		System.out.println(totalScore);

	}
}