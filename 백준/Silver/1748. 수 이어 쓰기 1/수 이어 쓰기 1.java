import java.io.*;
import java.util.*;

public class Main {



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String N = br.readLine();
		int tmp = N.length();
		int[] ten = {0,1,10,100,1000,10000,100000,1000000,10000000,100000000};
		int sum = 0;
		for(int i = 0; i<tmp; i++) {
			sum += 9 *ten[i]*i;
		}
		int num = (Integer.parseInt(N)-ten[tmp]+1)*tmp;
		System.out.println(sum+num);
		
		
	}
}