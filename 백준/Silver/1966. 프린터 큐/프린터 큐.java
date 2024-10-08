import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c_paper = Integer.parseInt(st.nextToken()); //문서의 갯수
			int index = Integer.parseInt(st.nextToken()); // 언제 나오는지 궁금해 하는 종이의 인덱스
			
			st = new StringTokenizer(br.readLine());
			Queue<Integer> papers = new ArrayDeque<>();
			Queue<Integer> indexTunnel = new ArrayDeque<>();
			Queue<Integer> tunnel1 = new ArrayDeque<>(); //문서 임시 터널
			Queue<Integer> tunnel2 = new ArrayDeque<>(); //인덱스 임시 터널
			
			for(int i = 0; i<c_paper; i++) {
				indexTunnel.add(i);
			}
			
			int max = 0;
			for (int p = 0; p < c_paper; p++) {
				int num = Integer.parseInt(st.nextToken());
				if(max<=num) {
					max = num;
				}
				papers.add(num);
			}
			
			int count = 0;
			int size = 0; //임시 터널들의 길이
			while(true) {
				
				if(indexTunnel.peek() != index &&papers.peek()==max) { //가장 큰 값인데 인덱스는 아닐 때
					count++;
					papers.poll();
					indexTunnel.poll();
					size = tunnel1.size();
					
					for(int j = 0; j<size; j++) {
						papers.add(tunnel1.poll()); // max보다 작고 앞에 있었던 종이들을 임시로 넣어놨던 공간
						indexTunnel.add(tunnel2.poll()); //max의 인덱스 앞에 있었던 인덱스들을 임시로 넣어놨던 공간
					}
					
					//max 값 재할당
					max = 0;
					size = papers.size(); //size를 papers size로 재할당
					for(int j = 0; j<size; j++) {
						int num = papers.poll();
						if(max < num) {
							max = num;
						}
						tunnel1.add(num); // 종이 임시터널에 넣으면서 max 값을 찾아
					}
					
					size = tunnel1.size();
					for(int j = 0; j<size; j++) {
						papers.add(tunnel1.poll()); // 임시터널에 있는 값들을 종이큐엤다가 다시 넣어
					}
					
				}
				else if(indexTunnel.peek() == index &&papers.peek()==max) {
					count++;
					System.out.println(count);
					break;
				}else {
					tunnel1.add(papers.poll()); // 문서 임시 터널에 max값 넣기
					tunnel2.add(indexTunnel.poll()); // 인덱스 임시 터널에 index 넣기
				}
			}
			
		}

	}
}