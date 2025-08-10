import java.util.*;
import java.io.*;

public class Main {
    static FastScanner fs = new FastScanner();
    static PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        Node[] pp = new Node[4];
        for (int i=0;i<4;i++){
            pp[i] = new Node(fs.nextLong(), fs.nextLong());
        }

        int[] flag = new int[4];
        flag[0] = ccw(pp[0], pp[1], pp[2]);
        flag[1] = ccw(pp[0], pp[1], pp[3]);

        flag[2] = ccw(pp[2], pp[3], pp[0]);
        flag[3] = ccw(pp[2], pp[3], pp[1]);

        boolean c1 = Math.min(pp[0].x, pp[1].x) <= Math.max(pp[2].x, pp[3].x);
        boolean c2 = Math.min(pp[2].x, pp[3].x) <= Math.max(pp[0].x, pp[1].x);
        boolean c3 = Math.min(pp[0].y, pp[1].y) <= Math.max(pp[2].y, pp[3].y);
        boolean c4 = Math.min(pp[2].y, pp[3].y) <= Math.max(pp[0].y, pp[1].y);

        if (flag[0] * flag[1] == 0 && flag[2] * flag[3] == 0){
            if (c1 && c2 && c3 && c4) pw.print(1);
            else pw.print(0);
        }
        else{
            if (flag[0] * flag[1] <= 0 && flag[2] * flag[3] <= 0) pw.print(1);
            else pw.print(0);
        }
        
        pw.close();
    }

    static int ccw(Node n1, Node n2, Node n3){
        long s = (n1.x * n2.y + n2.x * n3.y + n3.x * n1.y) - (n1.y * n2.x + n2.y * n3.x + n3.y * n1.x);
        if (s > 0) return 1;
        else if (s < 0) return -1;
        else return 0;
    }
    
    static class Node {
        long x, y;
        
        public Node(long x, long y){
            this.x = x;
            this.y = y;
        }
    }


    static void sort(int[] a) {
        ArrayList<Integer> L = new ArrayList<>();
        for (int i : a)
            L.add(i);
        Collections.sort(L);
        for (int i = 0; i < a.length; i++)
            a[i] = L.get(i);
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}