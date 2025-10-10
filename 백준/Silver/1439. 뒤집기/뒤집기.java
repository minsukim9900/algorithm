import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		int[] count = new int[2];
		int pre = -1;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) - '0' != pre) {
				pre = str.charAt(i) - '0';
				count[pre]++;
			}
		}
		System.out.println(Math.min(count[0], count[1]));
	}
}
