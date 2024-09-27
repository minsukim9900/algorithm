import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < T; i++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		
		arr.sort(null);
		
		for( int i = 0; i<arr.size(); i++) {
			sb.append(arr.get(i)).append("\n");
		}
		System.out.println(sb.toString());
	}

}