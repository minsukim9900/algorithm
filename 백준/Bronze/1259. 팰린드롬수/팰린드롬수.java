import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		while(true) {
			String nums = br.readLine();
			ArrayList<Character> arr1 = new ArrayList<>();
			if(!nums.equals("0")) {
				for(int i = nums.length()-1; i>=0; i--) {
					arr1.add(nums.charAt(i));
				}
				String compnums = "";
				for(int i = 0; i<nums.length(); i++) {
					compnums+=arr1.get(i);
				}
				if(nums.equals(compnums)) {
					System.out.println("yes");
				}
				else {
					System.out.println("no");
				}
			}
			else {
				break;
			}
		}
			
	}
}