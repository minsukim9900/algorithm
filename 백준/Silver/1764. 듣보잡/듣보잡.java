import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<String> person = new HashSet<>();
		ArrayList<String> personEarsEyes = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String human = br.readLine();
			person.add(human);
		}
		int count = 0;
		for(int i = 0; i<M; i++) {
			String human = br.readLine();
			if(person.contains(human)) {
				count++;
				personEarsEyes.add(human);
			}
			person.add(human);
		}

		personEarsEyes.sort(null);
		sb.append(count).append("\n");
		for(int i = 0; i<personEarsEyes.size(); i++) {
			sb.append(personEarsEyes.get(i)).append("\n");
		}
		System.out.println(sb.toString());
	}
}