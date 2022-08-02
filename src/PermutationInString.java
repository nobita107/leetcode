import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> destCharFrequency = new HashMap<>();
        Map<Character, Integer> srcCharFrequency = new HashMap<>();
        int len = s1.length();
        if(s2.length() < len){
            return false;
        }
        for (char c :s1.toCharArray()) {
            destCharFrequency.compute(c, (k,v) -> v==null?1:v+1);
        }
        for (int i = 0; i < len; i++) {
            srcCharFrequency.compute(s2.charAt(i), (k,v) -> v==null?1:v+1);
        }

        for (int wStart = 0; wStart <= s2.length() - len; wStart++) {
            if(isPermutation(srcCharFrequency, destCharFrequency)){
                return true;
            }
            srcCharFrequency.compute(s2.charAt(wStart), (k, v) -> v==1?null:v-1);
            if(wStart + len < s2.length()){
                srcCharFrequency.compute(s2.charAt(wStart + len), (k,v) -> v==null?1:v+1);
            }
        }
        return false;
    }
    public boolean isPermutation(Map<Character, Integer> m1, Map<Character, Integer> m2){
        for (Character c: m1.keySet()) {
            if(!m2.containsKey(c) || !m2.get(c).equals(m1.get(c))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationInString permutationInString = new PermutationInString();
        String s1 = "ab", s2 = "a";
        System.out.println(permutationInString.checkInclusion(s1, s2));
    }
}
