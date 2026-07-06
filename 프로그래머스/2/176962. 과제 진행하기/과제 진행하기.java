import java.util.*;

class Solution {

    public String[] solution(String[][] plans) {

        Arrays.sort(plans, Comparator.comparingInt(o -> getTime(o[1])));

        Stack<String[]> stack = new Stack<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < plans.length - 1; i++) {

            stack.push(plans[i]);

            int currTime = getTime(plans[i][1]);
            int nextTime = getTime(plans[i + 1][1]);

            int freeTime = nextTime - currTime;

            while (!stack.isEmpty() && freeTime > 0) {

                String[] task = stack.pop();

                int playTime = Integer.parseInt(task[2]);

                if (playTime <= freeTime) {
                    freeTime -= playTime;
                    result.add(task[0]);
                } else {
                    task[2] = String.valueOf(playTime - freeTime);
                    stack.push(task);
                    break;
                }
            }
        }

        stack.push(plans[plans.length - 1]);

        while (!stack.isEmpty()) {
            result.add(stack.pop()[0]);
        }

        return result.toArray(new String[0]);
    }

    private int getTime(String time) {

        String[] split = time.split(":");

        return Integer.parseInt(split[0]) * 60
                + Integer.parseInt(split[1]);
    }
}