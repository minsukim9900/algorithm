import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> arr = new ArrayList<>();
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			arr.add(Integer.parseInt(br.readLine()));
			sum += arr.get(i);
		}
		
		System.out.println(sum/5);
		arr.sort(null);
		System.out.println(arr.get(2));
		
	}
}