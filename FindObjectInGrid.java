/****
 * Uses a binary-search style approach with HOTTER/COLDER feedback to independently locate
 * the correct column and row of the hidden object by comparing relative distances.
 * Time Complexity: O(log A) where A is the number of rows/columns searched. Space Complexity: O(1).
 */
import java.util.*;

public class FindObjectInGrid {

    enum Response {
        HOTTER, COLDER, SAME, EXACT;
    }

    public class GFG {

        private int prevRow = -1;
        private int prevCol = -1;

        private final int targetRow = 4;
        private final int targetCol = 2;

        public static void main(String[] args) {
            GFG finder = new GFG();
            int[][] grid = {
                {'o', 'o', 'o'},
                {'o', 'o', 'o'},
                {'o', 'o', 'o'},
                {'o', 'o', 'o'},
                {'o', 'o', 'x'}
            };

            int[] ans = finder.findObject(grid);
            System.out.println("Object found at: [" + ans[0] + ", " + ans[1] + "]");
        }

        public int[] findObject(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            // Reset oracle before column search
            prevRow = -1;
            prevCol = -1;
            int col = binarySearchColumn(0, cols);

            // Reset oracle before row search
            prevRow = -1;
            prevCol = -1;
            int row = binarySearchRow(col, rows);

            return new int[]{row, col};
        }

        public Response getResponse(int r, int c) {
            if (r == targetRow && c == targetCol) return Response.EXACT;

            if (prevRow == -1 && prevCol == -1) {
                prevRow = r;
                prevCol = c;
                return Response.HOTTER;
            }

            int prevDist = Math.abs(prevRow - targetRow) + Math.abs(prevCol - targetCol);
            int newDist = Math.abs(r - targetRow) + Math.abs(c - targetCol);

            Response res;
            if (newDist == 0) {
                res = Response.EXACT;
            } else if (newDist < prevDist) {
                res = Response.HOTTER;
            } else if (newDist > prevDist) {
                res = Response.COLDER;
            } else {
                res = Response.SAME;
            }

            prevRow = r;
            prevCol = c;

            return res;
        }

        private int binarySearchColumn(int fixedRow, int n) {
            int low = 0, high = n - 1;

            while (low < high) {
                int mid = low + (high - low) / 2;

                Response midRes = getResponse(fixedRow, mid);
                if (midRes == Response.EXACT) return mid;

                Response rightRes = getResponse(fixedRow, mid + 1);
                if (rightRes == Response.EXACT) return mid + 1;

                if (rightRes == Response.HOTTER) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }

        private int binarySearchRow(int fixedCol, int m) {
            int low = 0, high = m - 1;

            while (low < high) {
                int mid = low + (high - low) / 2;

                Response midRes = getResponse(mid, fixedCol);
                if (midRes == Response.EXACT) return mid;

                Response downRes = getResponse(mid + 1, fixedCol);
                if (downRes == Response.EXACT) return mid + 1;

                if (downRes == Response.HOTTER) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }
    }
}
