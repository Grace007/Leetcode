package algorithm;

import java.util.HashMap;

/**
 * @author eli
 * @date 2019/4/25 21:44
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *     示例:
 *     给定 nums = [2, 7, 11, 15], target = 9
 *     因为 nums[0] + nums[1] = 2 + 7 = 9
 *     所以返回 [0, 1]
 */

public class A1_A_TwoSum {

    //时间复杂度：O(n^2), 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n)O(n) 的时间。因此时间复杂度为 O(n^2)。
    //空间复杂度：O(1)。
    public int[] twoSum1(int[] nums, int target) {
        for(int i = 0 ;i<nums.length ; i++){
            for(int j=i + 1  ; j<nums.length ; j++){
                if(nums[i]+nums[j] == target) return new int [] {i,j};
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if(map.containsKey(temp)) {
                int a = (int) map.get(temp);
                if (a != i ){   //防止 5 + 5 = 10 这种情况
                    return new int[] {i,a};
                }
            }
        }
        return null;


    }
}
