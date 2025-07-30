import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("Test3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		// ---------여기에 코드를 작성하세요.---------------//

		int K = Integer.parseInt(br.readLine());
		int mw = 0;
		int Windex = 0;
		int mh = 0;
		int Hindex = 0;
		
		int[] info = new int[6];
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			info[i] = len;
			if (dir < 3) {
				if(mh < len) {
					Hindex = i;
					mh = len;
				}
			} else {
				if(mw < len) {
					Windex = i;
					mw = len;
				}
			}
		}
		
		int hl = (Hindex - 1 + 6) % 6;
		int wl = (Windex - 1 + 6) % 6;
		int hr = (Hindex + 1) % 6;
		int wr = (Windex + 1) % 6;
		
		int tmp = Math.abs(info[hl] - info[hr]) * Math.abs(info[wl] - info[wr]);
		System.out.println(((mh * mw) - tmp ) * K);
	}

}
