/**
 * Converts the string into an integer by handling whitespace, sign, digits, and overflow conditions.
 * Uses iterative parsing while checking overflow before multiplying and adding the next digit.
 * Time Complexity: O(n), where n is the length of the string. Space Complexity: O(1).
 */
public class AtoI {

        public int myAtoi(String s) {
        s = s.trim(); 

        if(s.length()==0) return 0; 
        char c = s.charAt(0);
        if(!Character.isDigit(c) && c != '+' && c != '-') return 0; 

        boolean flag = true; 

        if(c == '-') flag = false;
        int result = 0;
        int limit = Integer.MAX_VALUE / 10;

        for(int i = 0; i < s.length() ; i++) {
            c = s.charAt(i);
            if(Character.isDigit(c)) {
                if(result > limit) {
                    if(flag) return Integer.MAX_VALUE;
                    else return Integer.MIN_VALUE;
                } else if(result == limit) {
                    if(c -'0' >= 8 ) {
                        if(flag) return Integer.MAX_VALUE;
                        else return Integer.MIN_VALUE;
                    }
                }
                result = result *10 + c - '0';
            } else {
                if(i != 0)
                    break;
            }
        }
        if(flag) return result;
        else return -result;
        
    }
    
}
