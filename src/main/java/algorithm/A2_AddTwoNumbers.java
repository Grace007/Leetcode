package algorithm;

/**
 * @author eli
 * @date 2019/4/25 22:03
 */
/*

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    示例：
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807
*/


public class A2_AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    //int最大值是int的最大值是2147483647

    /**
     *  因为int,long溢出问题,提交失败
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //判断临界条件
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        //保存临时节点
        ListNode listNode1 = l1;
        ListNode listNode2 = l2;

        //获得这两个的长度
        //num1,num2,total使用long类型,因为int可能会溢出,提交不过
        //事实证明Long也会溢出,0-0...
        int length1=0;
        int length2=0;
        Long num1=0l;
        Long num2=0l;
        while(listNode1 != null){
            num1 = num1 + listNode1.val* new Double(Math.pow(10,length1)).longValue();
            length1++;
            listNode1 = listNode1.next;
        }
        while(listNode2 != null){
            num2 =  num2 + listNode2.val* new Double(Math.pow(10,length2)).longValue();
            length2++;
            listNode2 = listNode2.next;
        }
        Long total = num1+num2;
        ListNode listNode_total = null;
        ListNode listNode_temp = null;
        int i=1;
        if(total == 0) return l1; //边界条件
        while (total >= 1){
            int temp = (int) (total % 10);
            total = total / 10;
            if(i == 1) {
                listNode_total = new ListNode(temp);
            }else if(i == 2){
                listNode_total.next = new ListNode(temp);
                listNode_temp = listNode_total.next;
            }else{
                listNode_temp.next = new ListNode(temp);
                listNode_temp = listNode_temp.next;
            }
            i++;
        }
        return listNode_total;
    }

    /**
     * 模拟进位
     * 时间复杂度:O(max(m+n))
     * 空间复杂度:O(max(m+n))
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //初始化一个头结点
        ListNode result = new ListNode(0);
        //进位标志carry
        int carry = 0;
        //保存临时节点
        ListNode listNode1 = l1;
        ListNode listNode2 = l2;
        ListNode temp = result;

        while(listNode1 != null || listNode2 != null){
            int sum = ((listNode1 != null)?  listNode1.val : 0 )+ ((listNode2 != null) ? listNode2.val : 0 ) + carry;
            if(sum>=10) carry = 1;
            else carry = 0;
            // 这就等于把新的元素赋值给了listNode
            temp.next = new ListNode(sum % 10);
            temp = temp.next;

            if(listNode1 != null) listNode1 = listNode1.next;
            if(listNode2 != null) listNode2 = listNode2.next;
        }
        //判断最后的进位,边界条件
        if(carry ==1 ){
            temp.next = new ListNode(1);
        }

        return result.next;
    }

    public static void main(String[] args) {

        //ListNode node = new ListNode(2);
        //node.next = new ListNode(4);
        //node.next.next = new ListNode(9);
        //
        //ListNode node1 = new ListNode(5);
        //node1.next = new ListNode(6);
        //node1.next.next = new ListNode(9);

        //int溢出,提交失败
        //long溢出,提交失败
        ListNode node = new ListNode(9);
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(9);
        node1.next.next = new ListNode(9);
        node1.next.next.next = new ListNode(9);
        node1.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        node1.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);

        //ListNode result = new A2_AddTwoNumbers().addTwoNumbers(node, node1);
        ListNode result = new A2_AddTwoNumbers().addTwoNumbers2(node, node1);
        while(result != null){
            System.out.println("result.val = " + result.val);
            result = result.next;
        }
        //System.out.println(214748364+9);

    }
}
