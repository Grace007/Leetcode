package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eli
 * @date 2019-04-29 15:02
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 *
 * 第三种解题思路: O(m+n) 的算法解决，用两个指针分别指向两个数组，比较指针下的元素大小，一共移动次数为 (m+n + 1)/2，便是中位数。
 */
public class A4_C_MedianofTwoSortedArrays {

    /**
     * 提交成功了!,但是并没有符合题目要求的时间复杂度
     * 合为一个数组,并且排序,再找中位数
     * 复杂度:时间复杂度O(m+n) , 空间复杂度:O(m+n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List list = new ArrayList<Integer>();

        int i=0,j=0;
        //1.合为一个顺序的list,仍是有序的
        while (i<nums1.length && j<nums2.length){
            if(nums1[i] < nums2[j])
                list.add(nums1[i++]);
            else
                list.add(nums2[j++]);
        }
        if(i == nums1.length){
            while(j<nums2.length){
                list.add(nums2[j++]);
            }
        }else if(j == nums2.length){
            while(i<nums1.length){
                list.add(nums1[i++]);
            }
        }
        //2.
        if(list.size() % 2 == 1)
            return Double.valueOf(list.get((list.size())/2).toString()) ;
        else
            return  ( Double.valueOf(list.get(list.size() / 2).toString())  + Double.valueOf( list.get(list.size() / 2 - 1).toString()))  / 2;

    }

    /**
     * 第二种解法:将问题转化为两个有序数组，找出其中的第K大的数
     * 复杂度:时间复杂度: O(log(m + n))- 空间复杂度: O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0){
            // 求nums2的中位数
            return nums2.length % 2 == 0 ? (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0 : nums2[nums2.length / 2];
        }
        if(nums2 == null || nums2.length == 0){
            return nums1.length % 2 == 0 ? (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2.0 : nums1[nums1.length / 2];
        }
        int len = nums1.length + nums2.length;
        return len % 2 == 0 ? (topK(nums1,nums2,0,0,len/2)+topK(nums1,nums2,0,0,len/2+1))/2.0 : topK(nums1,nums2,0,0,len/2 + 1);

    }


    /**
     * 首先，我们理解什么中位数：指的是该数左右个数相等。
     * 比如：odd : [1,| 2 |,3]，2 就是这个数组的中位数，左右两边都只要 1 位；
     * even: [1,| 2, 3 |,4]，2,3 就是这个数组的中位数，左右两边 1 位；
     * 那么，现在我们有两个数组：
     * num1: [a1,a2,a3,...an]
     * nums2: [b1,b2,b3,...bn]
     * [nums1[:left1],nums2[:left2] | nums1[left1:], nums2[left2:]]
     * 只要保证左右两边 个数 相同，中位数就在 | 这个边界旁边产生。
     *
     * 如何找边界值，我们可以用二分法，我们先确定 num1 取 m1 左半边，那么 num2 取 m2 = (m+n+1)/2 - m1 的左半边，找到合适的 m1，就用二分法找。
     * 当 [ [a1],[b1,b2,b3] | [a2,..an],[b4,...bn] ]
     * 我们只需要比较 b3 和 a2 的关系的大小，就可以知道这种分法是不是准确的！
     *
     * 例如：我们令：
     * nums1 = [-1,1,3,5,7,9]
     * nums2 =[2,4,6,8,10,12,14,16]
     * 当 m1 = 4,m2 = 3
     * median = (num1[m1] + num2[m2])/2
     *
     * 时间复杂度：O(log(min(m,n)))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // 1.确保n1<n2
        if (n1>n2)
            return findMedianSortedArrays(nums2, nums1);
        int k = (n1 + n2 + 1)/2;
        int left = 0;
        int right = n1;
        //
        while(left < right){
            int m1 = left +(right - left)/2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2-1])
                left = m1 + 1;
            else
                right = m1;
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1-1],
            m2 <= 0 ? Integer.MIN_VALUE : nums2[m2-1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min( m1 >= n1 ? Integer.MAX_VALUE :nums1[m1],
            m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5;

    }

    // 找两个有序数组的topk小的数
    public int topK(int[] nums1,int[] nums2,int start1,int start2,int k){
        if(start1 >= nums1.length){
            return nums2[start2 + k - 1];
        }
        if(start2 >= nums2.length){
            return nums1[start1 + k - 1];
        }

        if(k == 1){
            return Math.min(nums1[start1] , nums2[start2]);
        }

        if(start1 + k / 2 > nums1.length){ // 肯定不会在nums2的前 k / 2
            return topK(nums1,nums2,start1,start2 + k / 2,k - k / 2);
        }else if(start2 + k / 2 > nums2.length){
            return topK(nums1,nums2,start1 + k / 2,start2,k - k / 2);
        }

        int mid1 = nums1[start1 + k / 2 - 1];
        int mid2 = nums2[start2 + k / 2 - 1];
        if(mid1 > mid2){ // 移除nums2的前k/2
            return topK(nums1,nums2,start1,start2 + k / 2,k - k / 2);
        }else {
            return topK(nums1,nums2,start1 + k / 2,start2,k - k/2);
        }
    }


    public static void main(String[] args) throws Exception {
        //int[] A = {1, 2, 5, 8, 44, 45, 45};
        //int[] B = {1, 2, 3, 4, 5, 6, 7, 23, 23, 23, 33, 44, 45, 45, 56, 77, 5555};
        int[] A = {1, 2};
        int[] B = {3, 4};
        System.out.println(new A4_C_MedianofTwoSortedArrays().findMedianSortedArrays(A, B));

    }
}
