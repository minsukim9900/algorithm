import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] nums;
	private static long[][] sumTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N][N];
		sumTree = new long[N * 4][N * 4];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				nums[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		initRow(1, 0, N - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());

			if (order == 1) {
				int queryRowLeft = Integer.parseInt(st.nextToken()) - 1;
				int queryColLeft = Integer.parseInt(st.nextToken()) - 1;
				int queryRowRight = Integer.parseInt(st.nextToken()) - 1;
				int queryColRight = Integer.parseInt(st.nextToken()) - 1;

				sb.append(sumRowQuery(1, 0, N - 1, queryRowLeft, queryRowRight, queryColLeft, queryColRight))
						.append("\n");

			} else {
				int row = Integer.parseInt(st.nextToken()) - 1;
				int col = Integer.parseInt(st.nextToken()) - 1;
				int value = Integer.parseInt(st.nextToken());
				updateRow(1, 0, N - 1, row, col, value);
			}
		}

		System.out.println(sb.toString());
	}

	private static void initRow(int nodeR, int rowLeft, int rowRight) {
		if (rowLeft != rowRight) {
			int mid = (rowLeft + rowRight) >> 1;
			int next = nodeR << 1;
			initRow(next, rowLeft, mid);
			initRow(next + 1, mid + 1, rowRight);
		}

		initColumn(nodeR, rowLeft, rowRight, 1, 0, N - 1);
	}

	private static void initColumn(int nodeR, int rowLeft, int rowRight, int nodeC, int colLeft, int colRight) {

		if (colLeft == colRight) {

			if (rowLeft == rowRight) {
				sumTree[nodeR][nodeC] = nums[rowLeft][colLeft];
			} else {
				int nextR = nodeR << 1;
				sumTree[nodeR][nodeC] = sumTree[nextR][nodeC] + sumTree[nextR + 1][nodeC];
			}

			return;
		}
		int nextC = nodeC << 1;
		int midColumn = (colLeft + colRight) >> 1;

		initColumn(nodeR, rowLeft, rowRight, nextC, colLeft, midColumn);
		initColumn(nodeR, rowLeft, rowRight, nextC + 1, midColumn + 1, colRight);

		sumTree[nodeR][nodeC] = sumTree[nodeR][nextC] + sumTree[nodeR][nextC + 1];
	}

	private static void updateRow(int nodeR, int rowLeft, int rowRight, int targetRow, int targetColumn, int value) {
		if (targetRow < rowLeft || rowRight < targetRow) {
			return;
		}

		if (rowLeft != rowRight) {
			int mid = (rowLeft + rowRight) >> 1;
			int next = nodeR << 1;
			updateRow(next, rowLeft, mid, targetRow, targetColumn, value);
			updateRow(next + 1, mid + 1, rowRight, targetRow, targetColumn, value);
		}
		
		updateColumn(nodeR, rowLeft, rowRight, 1, 0, N - 1, targetRow, targetColumn, value);
	}

	private static void updateColumn(int nodeR, int rowLeft, int rowRight, int nodeC, int colLeft, int colRight,
			int targetRow, int targetColumn, int value) {

		if (targetColumn < colLeft || colRight < targetColumn) {
			return;
		}

		if (colLeft == colRight) {

			if (rowLeft == rowRight) {
				sumTree[nodeR][nodeC] = value;
			} else {
				int nextR = nodeR << 1;
				sumTree[nodeR][nodeC] = sumTree[nextR][nodeC] + sumTree[nextR + 1][nodeC];
			}

			return;
		}

		int midColumn = (colLeft + colRight) >> 1;
		int nextC = nodeC << 1;

		updateColumn(nodeR, rowLeft, rowRight, nextC, colLeft, midColumn, targetRow, targetColumn, value);
		updateColumn(nodeR, rowLeft, rowRight, nextC + 1, midColumn + 1, colRight, targetRow, targetColumn, value);

		sumTree[nodeR][nodeC] = sumTree[nodeR][nextC] + sumTree[nodeR][nextC + 1];
	}

	private static long sumRowQuery(int nodeR, int rowLeft, int rowRight, int queryRowLeft, int queryRowRight,
			int queryColLeft, int queryColRight) {

		if (queryRowRight < rowLeft || rowRight < queryRowLeft) {
			return 0;
		}

		if (queryRowLeft <= rowLeft && rowRight <= queryRowRight) {
			return sumColumnQuery(nodeR, 1, 0, N - 1, queryColLeft, queryColRight);
		}

		int mid = (rowLeft + rowRight) >> 1;
		int next = nodeR << 1;

		long leftSum = sumRowQuery(next, rowLeft, mid, queryRowLeft, queryRowRight, queryColLeft, queryColRight);
		long rightSum = sumRowQuery(next + 1, mid + 1, rowRight, queryRowLeft, queryRowRight, queryColLeft,
				queryColRight);

		return leftSum + rightSum;
	}

	private static long sumColumnQuery(int nodeR, int nodeC, int colLeft, int colRight, int queryColLeft,
			int queryColRight) {
		if (queryColRight < colLeft || colRight < queryColLeft) {
			return 0;
		}

		if (queryColLeft <= colLeft && colRight <= queryColRight) {
			return sumTree[nodeR][nodeC];
		}

		int mid = (colLeft + colRight) >> 1;
		int next = nodeC << 1;

		long leftSum = sumColumnQuery(nodeR, next, colLeft, mid, queryColLeft, queryColRight);
		long rightSum = sumColumnQuery(nodeR, next + 1, mid + 1, colRight, queryColLeft, queryColRight);

		return leftSum + rightSum;
	}
}