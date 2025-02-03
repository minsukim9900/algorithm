import java.io.*;
import java.util.*;

class Solution {
    
    private static int visited;
    private static int cnt = 1;
    private static ArrayList<Integer> tmp = new ArrayList<>();
    private static HashMap<String, Integer> air = new HashMap<>();
    private static HashMap<Integer, String> num = new HashMap<>();
    private static boolean check = false;
    
    private static class InfoTicket implements Comparable<InfoTicket>{
	        String to;
	        int idx;
	        
	        private InfoTicket(String to, int idx) {
	            this.to = to;
	            this.idx = idx;
	        }

			@Override
			public int compareTo(InfoTicket o) {
				return this.to.compareTo(o.to);
			}
        
	    }
    
    private static List<InfoTicket>[] adj;
    
    public String[] solution(String[][] tickets) {
       
        int ticket = (1 << (tickets.length)) - 1;
        
        init(tickets);
        
        
        adj = new ArrayList[cnt];
        for(int i = 1; i < cnt; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            int idx = air.get(from);
            adj[idx].add(new InfoTicket(to, i));
        }
        
        for(int i = 1; i < cnt; i++) {
            Collections.sort(adj[i]);
        }
        
        int start = air.get("ICN");
        dfs(start, visited, ticket, 0);
        
        String[] result = new String[tmp.size()];
        
        for(int i = 0; i<tmp.size(); i++) {
            result[i] = num.get(tmp.get(i));
        }
        
        return result;
        
    }
    
    private static void dfs(int start, int visited, int ticket, int currIdx) {
        
        if(visited == ticket) {
            tmp.add(start);
            check = true;
            return;
        }else {
            
            if(tmp.size() == currIdx) {
                tmp.add(start);
            }else {
                tmp.set(currIdx, start);
            }
            
            
            for(InfoTicket i : adj[start]) {
                if(!check) {
                    String to = i.to;
                    int idx = i.idx;
                    if((visited & (1<<idx)) == 0) {
                        visited |= (1<<idx);
                        dfs(air.get(to), visited, ticket, currIdx+1);
                        visited ^= (1<<idx);
                    }
                    
                }
            }
            
        }
        
    }
    
    
    private static void init(String[][] tickets) {
        
        Set<String> port = new HashSet<>();
        
        for(int i = 0; i < tickets.length; i++) {
            
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            if(!port.contains(from)) {
                port.add(from);
                air.put(from, cnt++);
                num.put(cnt-1, from);
            }
            
            if(!port.contains(to)) {
                port.add(to);
                air.put(to, cnt++);
                num.put(cnt-1, to);
            }
            
        }
    }
    
    
}