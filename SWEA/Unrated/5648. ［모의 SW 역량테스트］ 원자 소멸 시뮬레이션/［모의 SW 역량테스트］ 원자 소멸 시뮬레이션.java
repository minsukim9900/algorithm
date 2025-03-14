import java.io.*;
import java.util.*;
 
public class Solution {
 
    /*
     * 두 개 이상의 원자가 충돌 할 경우 충돌한 원자들은 보유한 에너지를 모두 방출하고 소멸 원자들이 소멸되면서 방출하는 에너지의 총합을 구하라
     * 상 0 하 1 좌 2 우 3 원자들이 움직일 수 있는 좌표의 범위에 제한은 없다. 하지만 맵 밖에 나가면 그냥 다른 원자와 충돌이 나지
     * 않을 것이라고 판단 맵의 크기는 2001 * 2001로 잡는다. -1000 ~ 1000 N은 원자의 수
     */
 
    private static int N, total;
    private static int[][] delta = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
    private static ArrayList<int[]> atom;
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
 
            N = Integer.parseInt(br.readLine());
            total = 0;
 
            atom = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
 
                st = new StringTokenizer(br.readLine());
 
                int x = (Integer.parseInt(st.nextToken()) + 1000) << 1;
                int y = (Integer.parseInt(st.nextToken()) + 1000) << 1;
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
 
                atom.add(new int[] { y, x, dir, k });
 
            }
 
            simulate();
            sb.append("#" + t +" " + total + "\n");
 
        }
        System.out.println(sb.toString());
 
    }
 
    private static void simulate() {
 
        for (int t = 0; t <= 4000; t++) {
             
            ArrayList<int[]> filter = new ArrayList<>();
            if (atom.isEmpty()) {
                break;
            }
 
            int size = atom.size();
 
            for (int i = 0; i < size; i++) {
                int[] curr = atom.get(i);
 
                int nr = curr[0] + delta[curr[2]][0];
                int nc = curr[1] + delta[curr[2]][1];
 
                if (nr >= 0 && nr <= 4000 && nc >= 0 && nc <= 4000) {
                    filter.add(new int[] { nr, nc, curr[2], curr[3] });
                }
 
            }
 
            if (filter.isEmpty())
                break;
 
            Collections.sort(filter, new Comparator<int[]>() {
 
                @Override
                public int compare(int[] o1, int[] o2) {
 
                    if (o1[0] == o2[0])
                        return o1[1] - o2[1];
 
                    return o1[0] - o2[0];
 
                }
 
            });
             
             
            atom = survive(filter);
 
        }
 
    }
 
    private static ArrayList<int[]> survive(ArrayList<int[]> filter) {
         
        ArrayList<int[]> survive = new ArrayList<>();
 
        int size = filter.size();
 
        for (int i = 0; i < size; i++) {
 
            int[] curr = filter.get(i);
 
            if (i < size - 1 && curr[0] == filter.get(i + 1)[0] && curr[1] == filter.get(i + 1)[1]) {
 
                int sum = curr[3];
 
                int j = i + 1;
                 
                while (j < filter.size() && curr[0] == filter.get(j)[0] && curr[1] == filter.get(j)[1]) {
                    sum += filter.get(j)[3];
                    j++;
                }
 
                total += sum;
                 
                i = j - 1;
 
            } else {
                survive.add(curr);
            }
 
        }
 
        return survive;
    }
 
}