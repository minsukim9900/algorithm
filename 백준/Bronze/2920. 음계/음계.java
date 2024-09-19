import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] note = new int[8];
		int[] ascending = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] descending = { 8, 7, 6, 5, 4, 3, 2, 1 };

		for (int i = 0; i < 8; i++) {
			note[i] = Integer.parseInt(st.nextToken());
		}

		if (note[0] == 8) {
			int i =0;
			while (i<=8) {
				if ( i<8 && note[i] == descending[i]) {
					i++;
				}
				else if(i==8) {
					System.out.println("descending");
					break;
				}
				else {
					System.out.println("mixed");
					break;
				}
				
			}
		} else if(note[0]==1){
			int j =0;
			while (j<=8) {
				if (j<8 && note[j] == ascending[j]) {
					j++;
				}else if(j==8){
					System.out.println("ascending");
					break;
				}
				else {
					System.out.println("mixed");
					break;
				}
			}
		}
		else {
			System.out.println("mixed");
		}
	}
}