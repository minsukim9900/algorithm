import java.io.*;
import java.util.*;

/**
* 기본 시간, 기본 요금, 단위 시간, 단위 요금
*/

class Solution {
    private static Map<Integer, int[]> map = new HashMap<>();
    public int[] solution(int[] fees, String[] records) {
        int idx = 0;
        
        for(int i = 0; i < records.length; i++) {
            String[] curr = records[i].split(" ");
            // 시간 변환
            int currTime = changeTimeValue(curr[0]);
            //System.out.println("현재 시각 : "+currTime);
            int currCarNum = Integer.parseInt(curr[1]);
            String currState = curr[2];
            
            if(map.containsKey(currCarNum)) {
                int[] car = map.get(currCarNum);
                               
                if(car[1] == 0) { // 차가 다시 들어올 경우
                    car[0] = currTime;
                    car[1] = 1;
                    map.put(currCarNum, car);
                } else { // 차가 나가는 경우
                    int tmp = car[0];                    
                    //System.out.println(tmp + " " + car[3]);
                    car[0] = (currTime - tmp);
                    //System.out.println(car[0] + " " + car[3]);
                    car[1] = 0;
                    car[4] += car[0];
                    //System.out.println(car[4] + " " + car[3]);
                  map.put(currCarNum, car);
                }
                
            } else {
                // 차가 처음 들어올 경우
                // 키 : 차 넘버, 값 : {주차했던 시간, 현재 상태(들어온거 1, 나간거 0), idx, 차 넘버,총 들어왔던 시간}
                map.put(currCarNum, new int[] {currTime, 1, idx++, currCarNum, 0 - fees[0]});
            }
        }
        
        ArrayList<int[]> arr = new ArrayList<>();
        for(Map.Entry<Integer, int[]> w : map.entrySet()) {
            arr.add(cal(w.getValue(), fees));
        }
        int[] answer = new int[arr.size()];
        Collections.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        for(int i = 0; i < answer.length; i++) {
            answer[i] = arr.get(i)[1];
        }
        return answer;
    }
    
    // 값 : {주차했던 시간, 현재 상태(들어온거 1, 나간거 0), idx, 차 넘버,총 들어왔던 시간}
    private static int[] cal(int[] arr, int[] fees) {
        int sum = fees[1];
        
        if(arr[1] == 1) {
            int tmp = arr[0];
            arr[0] = changeTimeValue("23:59") - tmp;
            arr[4] += arr[0];
        }
        if(arr[4] <= 0) {
            return new int[] {arr[3], fees[1]};
        }
        
        int tmp = arr[4] % fees[2];
        
        arr[4] -= tmp;
        if(tmp != 0) {
            arr[4] += fees[2];
        }
        
        sum += ((arr[4] / fees[2]) * fees[3]);
        return new int[] {arr[3], sum};
    }
    
    private static int changeTimeValue(String time) {
        String[] value = time.split(":");
        return Integer.parseInt(value[0]) * 60 + Integer.parseInt(value[1]);
    }
}