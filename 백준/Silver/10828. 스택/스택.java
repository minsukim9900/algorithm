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
				index++;
			}
			else if(method[i].equals("pop")) {
				if(index<= 0) {
					sb.append(-1).append("\n");
				}
				else {
					index--;
					sb.append(arr[index]).append("\n");
				}
			}
			else if(method[i].equals("size")) {
				sb.append(index).append("\n");
			}
			else if(method[i].equals("empty")) {
				if(index <= 0) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
			else if(method[i].equals("top")) {
				if(index <=0) {
					sb.append(-1).append("\n");
				}
				else {
					--index;
					sb.append(arr[index]).append("\n");
					index++;
				}
			}
			

		}
		System.out.println(sb.toString());
		
	}

}