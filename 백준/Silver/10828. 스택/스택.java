import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		String[] method = new String[N];
		for (int i = 0; i < N; i++) {
			method[i] = br.readLine();
			if (method[i].contains("push")) {
				count++;
			}

		}

		int[] arr = new int[count];
		int index = 0;

		for (int i = 0; i < method.length; i++) {

			if (method[i].contains("push")) {
				arr[index] = Integer.parseInt(method[i].substring(5));
				index++; // 이것은 다음 인덱스를 불러오겠다는거야
			}
			else if(method[i].equals("pop")) {
				if(index<= 0) {
					System.out.println(-1);
				}
				else {
					index--;
					System.out.println(arr[index]);
				}
			}
			else if(method[i].equals("size")) {
				System.out.println(index);
			}
			else if(method[i].equals("empty")) {
				if(index <= 0) {
					System.out.println(1);
				}
				else {
					System.out.println(0);
				}
			}
			else if(method[i].equals("top")) {
				if(index <=0) {
					System.out.println(-1);
				}
				else {
					--index;
					System.out.println(arr[index]);
					index++;
				}
			}
			
			

		}
		
	}

}