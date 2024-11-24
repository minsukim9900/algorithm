import java.io.*;
import java.util.*;

public class Main {

	private static int m, n, o, p, q, r, s, t, u, v, w;
	private static Queue<int[]> queue = new ArrayDeque<>();
	private static int[][][][][][][][][][][] tomatoBox;
	// 상, 하, 좌, 우, 위, 아래
	private static int[] dm = { -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] dn = { 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] dO = { 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] dp = { 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] dq = { 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] dr = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] ds = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] dt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0 };
	private static int[] du = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0 };
	private static int[] dv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0 };
	private static int[] dw = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		o = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		tomatoBox = new int[w][v][u][t][s][r][q][p][o][n][m];

		for (int iw = 0; iw < w; iw++) {
			for (int iv = 0; iv < v; iv++) {
				for (int iu = 0; iu < u; iu++) {
					for (int it = 0; it < t; it++) {
						for (int is = 0; is < s; is++) {
							for (int ir = 0; ir < r; ir++) {
								for (int iq = 0; iq < q; iq++) {
									for (int ip = 0; ip < p; ip++) {
										for (int io = 0; io < o; io++) {
											for (int in = 0; in < n; in++) {
												st = new StringTokenizer(br.readLine());
												for (int im = 0; im < m; im++) {
													int tmp = Integer.parseInt(st.nextToken());
													tomatoBox[iw][iv][iu][it][is][ir][iq][ip][io][in][im] = tmp;
													if (tmp == 1)
														queue.offer(new int[] { im, in, io, ip, iq, ir, is, it, iu, iv,
																iw, 0 });
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		int result = bfs();
		
		for (int iw = 0; iw < w; iw++) {
			for (int iv = 0; iv < v; iv++) {
				for (int iu = 0; iu < u; iu++) {
					for (int it = 0; it < t; it++) {
						for (int is = 0; is < s; is++) {
							for (int ir = 0; ir < r; ir++) {
								for (int iq = 0; iq < q; iq++) {
									for (int ip = 0; ip < p; ip++) {
										for (int io = 0; io < o; io++) {
											for (int in = 0; in < n; in++) {
												for (int im = 0; im < m; im++) {
													if(tomatoBox[iw][iv][iu][it][is][ir][iq][ip][io][in][im] == 0) {
														System.out.println(-1);
														return;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(result);
		

	}

	public static int bfs() {
		int max = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			max = curr[11];
			for (int i = 0; i < 22; i++) {
				int nm = curr[0] + dm[i];
				int nn = curr[1] + dn[i];
				int no = curr[2] + dO[i];
				int np = curr[3] + dp[i];
				int nq = curr[4] + dq[i];
				int nr = curr[5] + dr[i];
				int ns = curr[6] + ds[i];
				int nt = curr[7] + dt[i];
				int nu = curr[8] + du[i];
				int nv = curr[9] + dv[i];
				int nw = curr[10] + dw[i];
				if (nm >= 0 && nm < m && nn >= 0 && nn < n && no >= 0 && no < o && np >= 0 && np < p && nq >= 0
						&& nq < q && nr >= 0 && nr < r && ns >= 0 && ns < s && nt >= 0 && nt < t && nu >= 0 && nu < u
						&& nv >= 0 && nv < v && nw >= 0 && nw < w) {
					
					if (tomatoBox[nw][nv][nu][nt][ns][nr][nq][np][no][nn][nm] == 0) {
						queue.add(new int[] {nm,nn,no,np,nq,nr,ns,nt,nu,nv,nw, curr[11]+1});
						tomatoBox[nw][nv][nu][nt][ns][nr][nq][np][no][nn][nm] = 1;
					}
					
				}
			}
		}

		return max;

	}

}
