import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = n % 2 == 1 ? odd(n) : n % 4 == 0 ? fourmul(n) : even(n);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static int[][] odd(int n){
		int[][] num = new int[n][n];
		int x = 0, y = num.length / 2;
		for(int i = 1; i <= n * n; i++) {
			num[x][y] = i;
			if(i % n == 0) {
				x++;
			}
			else {
				x--;
				y++;
				if(x < 0) {
					x = n - 1;
				}
				if(y > n - 1) {
					y = 0;
				}
			}
		}
		return num;
	}
	
	public static int[][] even(int n){
		int[][] num = new int[n][n];
		
		int[][] arr = odd(n / 2);
		
		for(int i = 0; i < n / 2; i++) {
			for(int j = 0; j < n / 2; j++) {
				if(j < (n / 4)) {
					num[i][j] = 3;
				}
			}
		}
		num[n / 4][0] = 0;
		num[n / 4][n / 4] = 3;
		
		for(int i = n / 2; i < n; i++) {
			for(int j = 0; j < n / 2; j++) {
				if(num[i - n / 2][j] == 0) {
					num[i][j] = 3;
				}
			}
		}
		
		for(int i = 0; i < n / 2; i++) {
			for(int j = n / 2; j < n; j++) {
				num[i][j] = n - (n / 4 - 1) - 1 < j ? 1 : 2;
			}
		}
		
		for(int i = n / 2; i < n; i++) {
			for(int j = n / 2; j < n; j++) {
				num[i][j] = num[i - n / 2][j] == 1 ? 2 : 1;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				num[i][j] *= (n * n / 4);
			}
		}
		
		int x = 0, y = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				num[i][j] += arr[x][y];
				y++;
				if(y >= n / 2) {
					y = 0;
				}
			}
			x++;
			if(x >= n / 2) {
				x = 0;
			}
		}
		
		return num;
	}
	
	public static int[][] fourmul(int n){
		int[][] num = new int[n][n];
		
		int count = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				num[i][j] = count++;
			}
		}
		
		int temp1 = 25 * n / 100, temp2 = 50 * n / 100;
		for(int i = 0; i < temp1; i++) {
			for(int j = temp1; j < temp1 + temp2; j++) {
				swap(num, i, j, n);
			}
		}
		
		return num;
	}
	
	public static void swap(int[][] num, int temp1, int temp2, int size) {
		int temp = num[temp1][temp2];
		
		num[temp1][temp2] = num[size - 1 - temp1][size - 1 - temp2];
		num[size - 1 - temp1][size - 1 - temp2] = temp;
		
		temp = num[temp2][temp1];
		num[temp2][temp1] = num[size - 1 - temp2][size - 1 - temp1];
		num[size - 1 - temp2][size - 1 - temp1] = temp;
	}
}