import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> mInt = new LinkedList<>();
		Queue<Integer> tempfront = new LinkedList<>();

		for (int m = 0; m < N; m++) { // 들어올 명령 수
			String options = br.readLine();

			// 앞으로 x 값을 저장할 때
			if (options.length() > 9 && options.substring(0, 10).equals("push_front")) {

				// 비어있으면 그냥 삽입
				if (mInt.isEmpty()) {
					mInt.add(Integer.parseInt(options.substring(11)));
				} else { // 비어있지 않으면 전에 있던 값들을 tempfront 큐에 보내고 mInt 큐에 입력 값을 삽입 그 후 tempfront에 넣은 값들을 대입
					int tempsize = mInt.size();
					for (int i = 0; i < tempsize; i++) {
						tempfront.add(mInt.poll());
					}
					mInt.add(Integer.parseInt(options.substring(11)));
					for (int i = 0; i < tempsize; i++) {
						mInt.add(tempfront.poll());
					}
				}
			}
			// 뒤로 x 값을 저장할 때 그냥 mInt 큐에 삽입하면 됨.
			else if (options.length() > 9 && options.substring(0, 9).equals("push_back")) {
				mInt.add(Integer.parseInt(options.substring(10)));
			}

			else if (options.equals("pop_front")) {
				if (mInt.isEmpty()) { // 덱에 정수가 없는 경우 -1
					sb.append(-1).append("\n");//System.out.println(-1);
				} else {
					//System.out.println(mInt.poll());
					sb.append(mInt.poll()).append("\n");
				}
			}

			else if (options.equals("pop_back")) {
				if (mInt.isEmpty()) { // 덱에 정수가 없는 경우 -1
					sb.append(-1).append("\n");//System.out.println(-1);
				} else if (mInt.size() == 1) {
					sb.append(mInt.poll()).append("\n");//System.out.println(mInt.poll());
				} else {
					int temp = mInt.size() - 1;
					for (int i = 0; i < temp; i++) {
						tempfront.add(mInt.poll());
					}
					sb.append(mInt.poll()).append("\n");//System.out.println(mInt.poll());
					for (int i = 0; i < temp; i++) {
						mInt.add(tempfront.poll());
					}
				}
			}

			else if (options.equals("size")) {
				sb.append(mInt.size()).append("\n");//System.out.println(mInt.size());
			}

			else if (options.equals("empty")) {
				if (mInt.isEmpty()) {
					sb.append(1).append("\n");//System.out.println(1);
				} else {
					sb.append(0).append("\n"); //System.out.println(0);
				}
			}

			else if (options.equals("front")) {
				if (mInt.isEmpty()) { // 덱에 정수가 없는 경우 -1
					sb.append(-1).append("\n");//System.out.println(-1);
				} else {
					sb.append(mInt.peek()).append("\n");//System.out.println(mInt.peek());
				}
			} else if (options.equals("back")) {
				if (mInt.isEmpty()) { // 덱에 정수가 없는 경우 -1
					sb.append(-1).append("\n");//System.out.println(-1);
				} else if (mInt.size() == 1) {
					sb.append(mInt.peek()).append("\n");//System.out.println(mInt.peek());
				} else {
					int temp = mInt.size() - 1;
					for (int i = 0; i < temp; i++) {
						tempfront.add(mInt.poll());
					}
					sb.append(mInt.peek()).append("\n");//System.out.println(mInt.peek());
					tempfront.add(mInt.poll());
					int temp2 = tempfront.size();
					for (int i = 0; i < temp2; i++) {
						mInt.add(tempfront.poll());
					}
				}
			}
		}
		
		System.out.println(sb.toString());

	}
}