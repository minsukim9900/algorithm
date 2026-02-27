import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int order = 0;

		PriorityQueue<Person> pq = new PriorityQueue<>(
				(a, b) -> a.age == b.age ? Integer.compare(a.order, b.order) : Integer.compare(a.age, b.age));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();

			pq.add(new Person(name, order++, age));
		}
		
		while(!pq.isEmpty()) {
			Person ps = pq.poll();
			sb.append(ps.age).append(" ").append(ps.name).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static class Person {
		String name;
		int order;
		int age;

		public Person(String name, int order, int age) {
			this.name = name;
			this.order = order;
			this.age = age;
		}
	}
}