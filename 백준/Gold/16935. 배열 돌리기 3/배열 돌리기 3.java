import java.io.*;
import java.util.*;

public class Main {
    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void swapR(int[] arr) {
        int tmp = arr[0];
        arr[0] = arr[3];
        arr[3] = arr[2];
        arr[2] = arr[1];
        arr[1] = tmp;
    }

    static void swapL(int[] arr) {
        int tmp = arr[0];
        arr[0] = arr[1];
        arr[1] = arr[2];
        arr[2] = arr[3];
        arr[3] = tmp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int[][] brr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> ops = new ArrayList<>(r);
        while (ops.size() < r) {
            String line = br.readLine();
            if (line == null || line.isEmpty()) continue;
            StringTokenizer st2 = new StringTokenizer(line);
            while (st2.hasMoreTokens() && ops.size() < r) {
                ops.add(Integer.parseInt(st2.nextToken()));
            }
        }

        int v = 0, h = 0, ro = 0;
        int[] pos = {0, 1, 2, 3};

        for (int i = 0; i < r; i++) {
            int t = ops.get(i);
            if (t == 1) {
                if (ro == 0 || ro == 2) {
                    v ^= 1;
                    swap(pos, 0, 3);
                    swap(pos, 1, 2);
                } else {
                    h ^= 1;
                    swap(pos, 0, 1);
                    swap(pos, 2, 3);
                }
            } else if (t == 2) {
                if (ro == 0 || ro == 2) {
                    h ^= 1;
                    swap(pos, 0, 1);
                    swap(pos, 2, 3);
                } else {
                    v ^= 1;
                    swap(pos, 0, 3);
                    swap(pos, 1, 2);
                }
            } else if (t == 3) {
                ro = (ro + 1) % 4;
            } else if (t == 4) {
                ro = (ro - 1);
                if (ro == -1) ro = 3;
            } else if (t == 5) {
                swapR(pos);
            } else if (t == 6) {
                swapL(pos);
            }
        }

        int nh = n / 2;
        int mh = m / 2;
        int[] bnv = {0, 0, nh, nh};
        int[] bmv = {0, mh, mh, 0};

        for (int i = 0; i < 4; i++) {
            int nv, mv;
            if (pos[i] == 0) {
                nv = 0; mv = 0;
            } else if (pos[i] == 1) {
                nv = 0; mv = mh;
            } else if (pos[i] == 2) {
                nv = nh; mv = mh;
            } else {
                nv = nh; mv = 0;
            }

            for (int j = 0; j < nh; j++) {
                for (int k = 0; k < mh; k++) {
                    int sourceJ = j;
                    int sourceK = k;
                    if (v == 1) sourceJ = nh - 1 - j;
                    if (h == 1) sourceK = mh - 1 - k;

                    brr[j + bnv[i]][k + bmv[i]] = arr[sourceJ + nv][sourceK + mv];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        
        if (ro == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    sb.append(brr[i][j]).append(' ');
                }
                sb.setLength(sb.length() - 1);
                sb.append('\n');
            }
        } else if (ro == 1) {
            for (int i = 0; i < m; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    sb.append(brr[j][i]).append(' ');
                }
                sb.setLength(sb.length() - 1);
                sb.append('\n');
            }
        } else if (ro == 2) {
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    sb.append(brr[i][j]).append(' ');
                }
                sb.setLength(sb.length() - 1);
                sb.append('\n');
            }
        } else if (ro == 3) {
            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    sb.append(brr[j][i]).append(' ');
                }
                sb.setLength(sb.length() - 1);
                sb.append('\n');
            }
        }
        System.out.print(sb);
    }
}