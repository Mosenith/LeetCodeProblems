import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveZeroSumConsecutiveNodesFromLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    private static void printListNode(ListNode myNode) {
        while (myNode != null) {
            System.out.println(myNode.val);
            myNode = myNode.next;
        }
        System.out.println("~~~~~~~~");
    }
    public static void main(String[] args) {
//        ListNode myNode = new ListNode(5, new ListNode(-3,
//                new ListNode(-4, new ListNode(1, new ListNode(6,
//                        new ListNode(-2, new ListNode(-5)))))));

        ListNode myNode = new ListNode(1, new ListNode(2,
                new ListNode(-3, new ListNode(3, new ListNode(1)))));

        removeZeroSumSublists3(myNode);
    }

    // ***************** 1st Method ******************
    // Approach 1: covert linkedList to list and Loop through list
    // Check elements sum up to 0 & remove them
    // Runtime  : 16ms          -> + 5.39%
    // Memory   : 44.12MB       -> + 38.96%
    public static ListNode removeZeroSumSublists(ListNode head) {
        List<Integer> ls = new ArrayList<>();

        while (head != null) {
            ls.add(head.val);
            head = head.next;
        }

        for(int i=0; i<ls.size(); i++) {
            if(i > 0) {
                int prevI = i-1;
                int curSum = ls.get(i);
                while(prevI >= 0) {
                    curSum += ls.get(prevI);
                    if(curSum == 0) {
                        break;
                    }
                    --prevI;
                }
                // delete element from ls
                while(prevI >= 0 && prevI != i) {
                    ls.set(prevI++,0);
                }
                if(prevI>=0) {
                    ls.set(i,0);
                }
            }
        }

        ListNode ans = new ListNode(0);
        ListNode dummy = ans;
        // read the ls to ListNode
        for(int n : ls) {
            if(n != 0) {
                dummy.next = new ListNode(n);
                dummy = dummy.next;
            }
        }
        return ans.next;
    }
    //  ***************** End of 1st Method ******************

    // ***************** 2nd Method ******************
    // Approach 2: Loop through a copy of listNode head while keeping the sum of each node
    // If at one point sum=0, move head to cur.next & recursive head again
    // Otherwise keep looping cur = cur.next and after exit loop (cur=null),
    // recursive head.next by assigning head.next=recursive(head.next) cos the leftest element of head can't sum up to 0
    // Runtime  : 1ms          -> + 100.00%
    // Memory   : 42.57MB      -> + 98.15%
    public static ListNode removeZeroSumSublists2(ListNode head) {
        if (head == null) return head;
        ListNode cur = head;
        int running_sum = 0;
        while (cur != null) {
            running_sum += cur.val;
            if (running_sum == 0) {
                head = cur.next;
                return removeZeroSumSublists(head);
            }
            cur = cur.next;
        }
        head.next = removeZeroSumSublists(head.next);
        return head;
    }
    //  ***************** End of 2nd Method ******************

    // ***************** 3rd Method ******************
    // Approach 3: Use HashMap last with key-cumulative sum of node values, value-corresponding node
    // Iterate through linkedList, add each node.val to s
    // 1st loop -> To locate the starting point of each sequence of nodes with a sum of 0
    // 2nd loop -> To remove Zero-Sum Sequences and track ans sequence
    // Runtime  : 10ms          -> + 83.62%
    // Memory   : 46.58MB       -> + 13.04%
    public static ListNode removeZeroSumSublists3(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        Map<Integer, ListNode> last = new HashMap<>();
        int s = 0;
        ListNode cur = dummy;
        printListNode(cur);

        // key = 0, its value will point to the head of ans
        while (cur != null) {
            s += cur.val;
            last.put(s, cur);
            cur = cur.next;
        }
        s = 0;
        cur = dummy;

        // Get ans head and track its sequence in map
        while (cur != null) {
            s += cur.val;
            cur.next = last.get(s).next;
            cur = cur.next;
        }
        return dummy.next;
    }
    //  ***************** End of 3rd Method ******************

}
