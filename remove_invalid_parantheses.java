// Time Complexity : O(n * 2^n) in the worst case because we may generate all possible strings
// Space Complexity : O(2^n) for the queue and visited set
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use BFS so that strings are explored level by level based on the number of removed parentheses.
// As soon as we find a valid string at a level, we stop generating deeper levels because they require more removals.
// A HashSet prevents processing the same string multiple times.

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        Queue<String> q = new LinkedList<>();
        q.add(s);

        HashSet<String> set = new HashSet<>();
        set.add(s);

        boolean flag = false;

        while(!q.isEmpty() && !flag) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                String curr = q.poll();

                // valid string found
                if(isValid(curr)) {
                    result.add(curr);
                    flag = true;
                } else if(!flag) {

                    // remove one parenthesis at every position
                    for(int k = 0; k < curr.length(); k++) {
                        if(Character.isAlphabetic(curr.charAt(k))) continue;

                        String baby = curr.substring(0, k) + curr.substring(k + 1);

                        if(!set.contains(baby)) {
                            q.add(baby);
                            set.add(baby);
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;

        for(char c : s.toCharArray()) {
            if(Character.isAlphabetic(c)) continue;

            if(c == '(') {
                count++;
            } else {
                count--;
            }

            if(count < 0) return false;
        }

        return count == 0;
    }
}
