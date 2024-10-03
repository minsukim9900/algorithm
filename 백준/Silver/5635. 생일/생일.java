import java.io.*;
import java.time.chrono.MinguoChronology;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		String[] names = new String[n];
		int[] births = new int[n];
		
		
		for(int i = 0; i<n; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String day = st.nextToken();
			if(day.length()==1) {
				day = "0" + day;
			}
			String month = st.nextToken();
			if(month.length()==1) {
				month = "0" + month;
			}
			String years = st.nextToken();
			int birth = Integer.parseInt(years + month+ day);
			names[i] = name;
			births[i]= birth;
			
		}
		
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n-1; j++) {
					if(births[j] > births[j+1]) {
						int temp1 = births[j];
						births[j] = births[j+1];
						births[j+1] = temp1;
						
						String temp2 = names[j];
						names[j] = names[j+1];
						names[j+1] = temp2;
					}
				}
				
			}
	
		
		System.out.println(names[n-1]);
		System.out.println(names[0]);

	}
}