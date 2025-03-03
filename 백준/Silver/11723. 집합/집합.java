import java.io.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[21];
	
		
		
		
		String method;
		for (int i = 0; i < N; i++) {
			method = br.readLine();
			if(method.length()>3 && method.substring(0,3).equals("add")) {
				if(arr[Integer.parseInt(method.substring(4))]==0) {
					arr[Integer.parseInt(method.substring(4))] =1;					
				}
			}
			else if(method.length()>3 && method.substring(0,5).equals("check")) {
				if(arr[Integer.parseInt(method.substring(6))]==1) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
			else if(method.length()>6 && method.substring(0,6).equals("toggle")) {
				if(arr[Integer.parseInt(method.substring(7))]==1) {
					arr[Integer.parseInt(method.substring(7))] = 0;
				}
				else {
					arr[Integer.parseInt(method.substring(7))] = 1;
				}
			}
			else if(method.length()>6 && method.substring(0,6).equals("remove")){
				if(arr[Integer.parseInt(method.substring(7))]==1) {
					arr[Integer.parseInt(method.substring(7))] =0;					
				}
			}
			else if(method.equals("all")) {
				for(int j = 1; j<arr.length; j++) {
					arr[j] =1;
				}
			}
			else if(method.equals("empty")) {
				for(int j = 1; j<arr.length; j++) {
					arr[j] =0;
				}
			}
		}
		System.out.println(sb.toString());
	}
}