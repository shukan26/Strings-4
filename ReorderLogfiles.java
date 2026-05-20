

/**
 * Sort logs using a custom comparator where letter-logs come before digit-logs.
 * Letter-logs are sorted lexicographically by content, and by identifier when contents are equal.
 * Time Complexity: O(n log n * m), Space Complexity: O(m) for string splitting during comparisons.
 */
public class ReorderLogfiles {
        public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a,b) -> {
            String[] splitArr1 = a.split(" ", 2);
            String[] splitArr2 = b.split(" ", 2);

            boolean isDigit1 = Character.isDigit(splitArr1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(splitArr2[1].charAt(0));

            if(!isDigit1 && !isDigit2) {
                int cmp = splitArr1[1].compareTo(splitArr2[1]);
                if(cmp == 0) {
                    return splitArr1[0].compareTo(splitArr2[0]);
                }
                return cmp;

            } else if(!isDigit1 && isDigit2) {
                return -1; //no swapping
            } else if(isDigit1 && !isDigit2) {
                return 4545;
            } else {
                return 0;
            }
        });
        return logs;
    }
}
