import java.util.Arrays;

public class Solution {

    /**
     * 1 2 3 4
     * 12 13 14  5
     * 11   16 15 6
     * 10  9  8  7
     * @param args
     */
    public static void main(String[] args) {

        int n = 6;
        int count = 0;
        int row = 0;
        int col = 0;
        int res[][] = new int[n][n];
        while (count < n * n) {
            // 从左到右打印
            while (col < n && res[row][col] == 0) {
                res[row][col] = count+1;
                count++;
                col++;
            }
            row++;
            col--;

            //从上到下打印
            while (row < n && res[row][col] == 0) {
                res[row][col] = count+1;
                count++;
                row++;
            }
            col--;
            row--;

            //从右到左打印
            while (col >= 0 && res[row][col] == 0) {
                res[row][col] = count+1;
                count++;
                col--;
            }
            row--;
            col++;

            //从下到上打印
            while (row >= 0 && res[row][col] == 0) {
                res[row][col] = count+1;
                count++;
                row--;
            }
            col++;
            row++;
        }
        Arrays.stream(res).forEach(
                arr ->{
                    for (int i=0;i<n;i++){
                        System.out.print(arr[i]+" ");
                    }
                    System.out.println();
                }
        );
    }
}
