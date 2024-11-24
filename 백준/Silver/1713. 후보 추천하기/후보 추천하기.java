import java.io.*;
import java.util.*;

public class Main {
	private static int N, student;
	private static int[] frame;
	private static int min, minSugge, minOrder;
	private static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		student = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int idx = 0;
		int[][] cntSt = new int[student + 1][2];
		frame = new int[N];
		check = new boolean[student+1];

		for (int i = 1; i <= student; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!check[num]) cntSt[num][0] = i;// 몇번째 추천을 받았는가?
			check[num] = true;
			cntSt[num][1]++; // 몇 번 추천을 받았는가?
			put(cntSt, num, idx++);
		}
		Arrays.sort(frame);
		for(int i : frame) {
			if(i == 0) continue;
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());

	}

	public static void put(int[][] cntSt,int num, int idx) {
		minSugge = cntSt[frame[0]][1];
		minOrder = cntSt[frame[0]][0];
		min = frame[0];
		int frame_idx = 0;
		for(int i = 0; i<frame.length; i++) {
			if(frame[i] == num) return;
		}
		
		if (idx < N) {
			frame[idx] = num;
		}
		else {
			// 추천이 가장 많은 사람이 앞에 오게
			// 같다면 가장 나중에 추천 받은 사람이 앞에 오게s
			// 꽉 차 있으면 추천수가 가장 낮고, 가장 먼저 들어온 사람이 out
			// frame에 나가지면 받았던 추천수는 0으로 초기화
			
			
			for(int i = 0; i<frame.length; i++) {
				
				if(minSugge > cntSt[frame[i]][1]) {
					minSugge = cntSt[frame[i]][1];
					minOrder = cntSt[frame[i]][0];
					min = frame[i];
					frame_idx = i;
				}else if(minSugge == cntSt[frame[i]][1]) {
					
					if(minOrder > cntSt[frame[i]][0] ) {
						minOrder = cntSt[frame[i]][0];
						min = frame[i];
						frame_idx = i;
					}
					
				}
				
			}
			cntSt[min][0] = 0;
			cntSt[min][1] = 0;
			check[min] = false;
			frame[frame_idx] = num;
		}
	}
}
