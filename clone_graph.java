// Time Complexity : O(V + E) because we visit every node and edge once
// Space Complexity : O(V) for the HashMap and queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use BFS to traverse the graph and a HashMap to map each original node to its cloned node.
// For every node, we clone its neighbors and add them to the cloned node’s neighbor list.
// The HashMap prevents creating duplicate copies of the same node.

class Solution {
    HashMap<Node, Node> map;

    public Node cloneGraph(Node node) {
        if(node == null) return null;

        this.map = new HashMap<>();

        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()) {
            Node curr = q.poll();
            Node copyCurr = clone(curr);

            for(Node ne : curr.neighbors) {

                // visit unseen neighbor
                if(!map.containsKey(ne)) {
                    q.add(ne);
                }

                copyCurr.neighbors.add(clone(ne));
            }
        }

        return map.get(node);
    }

    private Node clone(Node node) {
        if(node == null) return null;

        if(map.containsKey(node)) return map.get(node);

        map.put(node, new Node(node.val));
        return map.get(node);
    }
}