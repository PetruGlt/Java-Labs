import java.util.*;

public class bonus {
    public static int[][] generateGraph(int n, int m) {
        int[][] matrix = new int[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    {
                        matrix[i][j] = Math.random() <= 0.5 ? 1 : 0;
                    }
                }
            }
            return matrix;
        }

    private static void printMatrix(int[][] matrix,int n, int m) {
        // partea de sus
        System.out.print("   ");
        for (int i = 0; i < m; i++) {
            System.out.printf("  %2d", i);
        }
        System.out.println();

        System.out.print("   \u250C");
        for (int i = 0; i < m - 1; i++) {
            System.out.print("\u2500\u2500\u2500\u252C");
        }
        System.out.println("\u2500\u2500\u2500\u2510");


        for (int i = 0; i < n; i++) {
            System.out.printf("%2d \u2502", i);
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 1) {
                    System.out.print(" \u25b2 \u2502");
                }
                else {
                    System.out.print(" \u25b3 \u2502");
                }
            }
            System.out.println();


            if (i < n - 1) {
                System.out.print("   \u251C");
                for (int j = 0; j < m - 1; j++) {
                    System.out.print("\u2500\u2500\u2500\u253C");
                }
                System.out.println("\u2500\u2500\u2500\u2524");
            }
        }

        System.out.print("   \u2514");
        for (int i = 0; i < m - 1; i++) {
            System.out.print("\u2500\u2500\u2500\u2534");
        }
        System.out.println("\u2500\u2500\u2500\u2518");
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);

        int[][] matrix = generateGraph(n, m) ;

        printMatrix(matrix,n,m);
    }
}