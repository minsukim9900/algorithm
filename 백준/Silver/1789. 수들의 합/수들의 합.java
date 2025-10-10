import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		long N = Long.parseLong(br.readLine());
		
		long sum = 0L;
		long num = 1L;
		long count = 0L;
		while(true) {
			if(sum > N)	{
				count--;
				break;
			}
			sum += num++;
			count++;
		}
		System.out.println(count);
	}

}
