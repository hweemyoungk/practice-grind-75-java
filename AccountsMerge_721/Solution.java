package AccountsMerge_721;

import java.util.*;

/*Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.



Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:

Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]


Constraints:

1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.

[4/9/2024]Time up
17%/12% (...)
n = #accounts
m = #emails
O(mlog(m))/O(m) (?)
*/

class Solution {
    private final Map<String, GraphGroup> graphGroupMap = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Draw undirected graph
        for (List<String> account : accounts) {
            String name = account.get(0);
            if (!graphGroupMap.containsKey(name)) graphGroupMap.put(name, new GraphGroup());
            GraphGroup graphGroup = graphGroupMap.get(name);
            Node dummy = new Node(null);
            Node prev = dummy;
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                Node curr = graphGroup.emailNodeMap.get(email);
                if (curr == null) {
                    curr = new Node(email);
                    graphGroup.emailNodeMap.put(email, curr);
                }
                // Undirected
                prev.neighbors.add(curr);
                curr.neighbors.add(prev);
                prev = curr;
            }
            for (var neighbor : dummy.neighbors) {
                neighbor.neighbors.remove(dummy);
            }
        }
        List<List<String>> ans = new LinkedList<>();
        for (var e1 : graphGroupMap.entrySet()) {
            String name = e1.getKey();
            GraphGroup graphGroup = e1.getValue();
            Set<Node> visited = new HashSet<>();
            for (var e2 : graphGroup.emailNodeMap.entrySet()) {
                Node node = e2.getValue();
                if (visited.contains(node)) continue;
                // New graph
                Graph graph = new Graph(name);
                Set<Node> currNodes = new HashSet<>();
                currNodes.add(node);
                while (!currNodes.isEmpty()) {
                    Set<Node> nextNodes = new HashSet<>();
                    for (var curr : currNodes) {
                        visited.add(curr);
                        graph.sortedNodes.add(curr);
                        for (var neighbor : curr.neighbors) {
                            if (visited.contains(neighbor)) continue;
                            neighbor.neighbors.remove(curr);
                            nextNodes.add(neighbor);
                        }
                        curr.neighbors.clear();
                    }
                    currNodes = nextNodes;
                }
                List<String> account = new LinkedList<>();
                account.add(name);
                for (var sortedNode : graph.sortedNodes) {
                    account.add(sortedNode.email);
                }
                ans.add(account);
            }
        }
        return ans;
    }
}

class GraphGroup {
    Map<String, Node> emailNodeMap = new TreeMap<>();
}

class Graph {
    String name;
    Set<Node> sortedNodes = new TreeSet<>(Comparator.comparing(node -> node.email));

    public Graph(String name) {
        this.name = name;
    }
}

class Node {
    String email;
    Set<Node> neighbors = new HashSet<>();

    public Node(String email) {
        this.email = email;
    }
}
