package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author eli
 * @date 2019/4/28 21:19
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *     示例 1:
 *     输入: "abcabcbb"
 *     输出: 3
 *     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *     示例 2:
 *     输入: "bbbbb"
 *     输出: 1
 *     解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *     示例 3:
 *     输入: "pwwkew"
 *     输出: 3
 *     解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 */

public class A3_B_LongestSubstringWithoutRepeatingCharacters {

    /**
     * 暴力穷举法
     * 时间复杂度:O(n3)
     * 空间复杂度:O(min(m, n))
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s == "") return 0;
        int result = 0;
        //两层循环,i为起始位置,j为终止位置
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1 ; j <= s.length() ; j++) {
                if(contains(s,i,j)) result = Math.max(result,j-i);
            }
        }

        return result;
    }

    public boolean contains(String s,int start,int end){
        //借助HashSet
        Set<Character> hashSet = new HashSet();
        for (int i = start; i < end; i++) {
            char temp = s.charAt(i);
            if(hashSet.contains(temp)){
                return false;
            }else{
                hashSet.add(temp);
            }
        }
        return true;
    }

    /**
     * 滑动窗口法:使用hashset作为窗口
     * i作为窗口起始游标,j作为窗口终止游标
     *
     * 复杂度分析
     * 时间复杂度：O(2n)=O(n)，在最糟糕的情况下，每个字符将被 ii 和 jj 访问两次。
     * 空间复杂度：O(min(m, n))，与之前的方法相同。滑动窗口法需要 O(k) 的空间，其中 k 表示 Set 的大小。而Set的大小取决于字符串 n 的大小以及字符集/字母 m 的大小。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int i=0,j=0,result=0;
        Set set = new HashSet<Character>();
        while(i < s.length() && j < s.length()){
            if(set.contains(s.charAt(j))){
                set.remove(s.charAt(i++));
            }else {
                set.add(s.charAt(j++));
                result = Math.max(result,j-i);
            }
        }
        return result;
    }

    /**
     * 优化的滑动窗口法:使用map,(data,索引)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int result=0;
        Map map = new HashMap<Character,Integer>();
        for(int i=0,j=0;j<s.length();j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max( (Integer) map.get(s.charAt(j)) ,i); //必须判断,因为可能多次重复,取最大的那个
            }
            result = Math.max(result,j-i+1);
            map.put(s.charAt(j),j + 1 ); //如果重复,map会覆盖前面的
        }
        return result;
    }
}
