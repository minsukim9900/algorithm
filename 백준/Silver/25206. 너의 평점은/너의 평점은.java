import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double [] credit = new double [20];
		double [] chgrade = new double [20];
		
		for(int i = 0; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String subject = st.nextToken();
			credit[i] = Double.parseDouble(st.nextToken());
			String grade = st.nextToken();
			if(grade.equals("A+")) {
				chgrade[i]=4.5;
			}
			else if(grade.equals("A0")) {
				chgrade[i]=4.0;
			}
			else if(grade.equals("B+")) {
				chgrade[i]=3.5;
			}
			else if(grade.equals("B0")) {
				chgrade[i]=3.0;
			}
			else if(grade.equals("C+")) {
				chgrade[i]=2.5;
			}
			else if(grade.equals("C0")) {
				chgrade[i]=2.0;
			}
			else if(grade.equals("D+")) {
				chgrade[i]=1.5;
			}
			else if(grade.equals("D0")) {
				chgrade[i]=1.0;
			}
			else if(grade.equals("F")) {
				chgrade[i]=0;
			}
			else if(grade.equals("P")) {
				chgrade[i]=0;
				credit[i]=0;
			}
		}
		
		double sum = 0;
		double creditsum = 0;
		
		for(int i = 0; i < 20; i++) {
			sum += credit[i]*chgrade[i];
			creditsum += credit[i];
		}
		
		double gradgpa = sum / creditsum;
		System.out.println(gradgpa);
		
		
		
	}
}

