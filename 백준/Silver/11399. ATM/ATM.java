import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		ArrayList<Integer> arr1= new ArrayList<>();
		ArrayList<Integer> arr2 = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr1.add(Integer.parseInt(st.nextToken()));
		}
		
		arr1.sort(null);
		
		int temp = 0;
		for(int i = 0; i<arr1.size(); i++) {
			arr2.add(arr1.get(i)+temp);
			temp += arr1.get(i);
		}
		
		int sum = 0;
		for(int i = 0; i<arr2.size(); i++) {
			sum+=arr2.get(i);
		}
		
		System.out.println(sum);
		
		
	}
}