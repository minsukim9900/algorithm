import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int nLight = Integer.parseInt(br.readLine());// 스위치 갯수
		int[] situation = new int[nLight + 1]; // 스위치 배열 생성
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i < situation.length; i++) {
			situation[i] = Integer.parseInt(st.nextToken()); // 스위치 배열 넣기
		}

		int totalStudent = Integer.parseInt(br.readLine()); // 총 학생 수

		for (int i = 0; i < totalStudent; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken()); // 성별
			int select_index = Integer.parseInt(st.nextToken()); // 스위치 정보
			if (student == 1) { // 남학생일 경우 선택한 index의 배수
				for (int j = 1; j < situation.length; j++) {
					if (j % select_index == 0) {
						if (situation[j] == 0) {
							situation[j] = 1;
						} else {
							situation[j] = 0;
						}
					}
				}

			} else { // 여학생일 경우
				if(situation[select_index] == 0) {
					situation[select_index] = 1;
				}
				else {
					situation[select_index] = 0;
				}
				for (int j = 1; j < nLight / 2; j++) {
					if (select_index + j > nLight || select_index - j < 1) {
						break;
					}
					if(situation[select_index-j]==situation[select_index+j]) {
						if (situation[select_index-j] == 0) {
							situation[select_index-j] = 1;
						} else {
							situation[select_index-j] = 0;
						}
						if (situation[select_index+j] == 0) {
							situation[select_index+j] = 1;
						} else {
							situation[select_index+j] = 0;
						}
					}
					else {
						break;
					}
				}
			}
		}

		for (int i = 1; i < situation.length; i++) {
			System.out.print(situation[i] + " ");
			if((i%20) == 0) {
				System.out.println();
			}
		}

	}
}