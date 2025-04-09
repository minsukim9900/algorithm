import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K, rs;
	private static int[][] board;
	private static ArrayList<int[]> orders;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		rs = 987654321;
		board = new int[N][M];
		orders = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());

			int sr = r - s;
			int sc = c - s;

			int er = r + s;
			int ec = c + s;

			int n = er - sr + 1;
			int m = ec - sc + 1;
			int tmp = (Math.min(n, m) >> 1);

			orders.add(new int[] { sr, sc, er, ec, tmp });
		}

		selectProcedure(0, 0, new int[K]);
		System.out.println(rs);
	}

	private static void selectProcedure(int depth, int visited, int[] rs) {
		if (depth == K) {
			
			int[][] copy = new int[N][M];
			for(int i = 0; i<N; i++) {
				copy[i] = board[i].clone();
			}
			
			simulate(rs);
			
			board = copy;
		} else {

			for (int i = 0; i < K; i++) {
				if ((visited & (1 << i)) != 0) {
					continue;
				}
				rs[depth] = i;
				selectProcedure(depth + 1, visited |= (1 << i), rs);
				visited ^= (1 << i);
			}
		}
	}

	private static void simulate(int[] select) {

		for (int i = 0; i < select.length; i++) {
			int[] order = orders.get(select[i]);
			int sr = order[0];
			int sc = order[1];
			int er = order[2];
			int ec = order[3];

			for (int j = 0; j < order[4]; j++) {
				rotate(sr + j, sc + j, er - j, ec - j);
			}
			
		}
		rs = Math.min(rs, cal());
	}

	private static ArrayList<Integer> rotate(int sr, int sc, int er, int ec) {
		ArrayList<Integer> arr = new ArrayList<>();

		for (int i = ec; i >= sc; i--) {
			arr.add(board[sr][i]);
		}
		for (int i = sr + 1; i <= er; i++) {
			arr.add(board[i][sc]);
		}
		for (int i = sc + 1; i <= ec; i++) {
			arr.add(board[er][i]);
		}
		for (int i = er - 1; i > sr; i--) {
			arr.add(board[i][ec]);
		}

		int idx = 1;

		for (int i = ec; i >= sc; i--) {
			board[sr][i] = arr.get(idx);
			idx = (idx + 1) % arr.size();
		}
		for (int i = sr + 1; i <= er; i++) {
			board[i][sc] = arr.get(idx);
			idx = (idx + 1) % arr.size();
		}
		for (int i = sc + 1; i <= ec; i++) {
			board[er][i] = arr.get(idx);
			idx = (idx + 1) % arr.size();
		}
		for (int i = er - 1; i > sr; i--) {
			board[i][ec] = arr.get(idx);
			idx = (idx + 1) % arr.size();
		}

		return arr;
	}
	
	private static int cal() {
		int min = 987654321;
		
		for(int r = 0; r<N; r++) {
			int sum = 0;
			for(int c= 0;c<M; c++) {
				sum += board[r][c];
			}
			min = Math.min(min, sum);
		}
		return min;
	}
}