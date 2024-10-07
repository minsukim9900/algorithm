import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	private static Stack<Integer> score = new Stack<>();
	private static Stack<Integer> time = new Stack<>();
	private static int totalScore = 0;
	
	static void homework(int process, int subject_score, int subject_time) {
		
		if (process == 1) {
			int subjectScore = subject_score;
			int subjectTime = subject_time - 1;
			if (subjectTime == 0) {
				totalScore += subjectScore;
			} else {
				score.push(subjectScore);
				time.push(subjectTime);
			}
		} else if (process == 0) {
			if (score.isEmpty()) {
				return;
			} else {
				int tmp = time.pop() - 1;
				if (tmp == 0) {
					totalScore += score.pop();
				} else {
					time.push(tmp);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int process = Integer.parseInt(st.nextToken());
			int subjectScore = 0;
			int subjectTime = 0;
			if(process == 1) {
			subjectScore = Integer.parseInt(st.nextToken());
			subjectTime = Integer.parseInt(st.nextToken());
			}
			homework(process, subjectScore, subjectTime);

		}

		System.out.println(totalScore);

	}
}