import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();
		int[] count =new int[8001];
		double sum = 0;
		for(int i = 0; i<N; i++) {
			arr.add(Integer.parseInt(br.readLine()));
			sum += arr.get(i);
			count[4000+arr.get(i)]++;
		}
		arr.sort(null);
		
		int max = 0;
		for(int i = 0; i<count.length; i++) {
			if(count[i]>max) {
				max = count[i];
			}
		}
		for(int i = 0; i<count.length; i++) {
			if(count[i]==max) {
				temp.add(i-4000);
			}
		}
		temp.sort(null);
		int mode = 0;
		if(temp.size()==1) {
			mode = temp.get(0);
		}
		else {
			mode = temp.get(1);
		}
		
		int range =  (arr.get(N-1)) - (arr.get(0));
		System.out.println(Math.round(sum/N));
		System.out.println(arr.get(N/2));
		System.out.println(mode);
		System.out.println(range);
	}
}
