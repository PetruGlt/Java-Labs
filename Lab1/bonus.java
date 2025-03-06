import java.util.*;

public class Bonus {
    public static int[][] generateGraph(int n) {
        int[][] matrix = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                    matrix[j][i] = 0;
                } else {
                    matrix[i][j] = Math.random() <= 0.5 ? 1 : 0;
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
        return matrix;
    }
    private static void printMatrix(int[][] matrix,int n){
        // partea de sus
        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            System.out.printf("  %2d", i);
        }
        System.out.println();

        System.out.print("   \u250C");
        for (int i = 0; i < n - 1; i++) {
            System.out.print("\u2500\u2500\u2500\u252C");
        }
        System.out.println("\u2500\u2500\u2500\u2510");


        for (int i = 0; i < n; i++) {
            System.out.printf("%2d \u2502", i);
            for (int j = 0; j < n; j++) {
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
                for (int j = 0; j < n - 1; j++) {
                    System.out.print("\u2500\u2500\u2500\u253C");
                }
                System.out.println("\u2500\u2500\u2500\u2524");
            }
        }

        System.out.print("   \u2514");
        for (int i = 0; i < n - 1; i++) {
            System.out.print("\u2500\u2500\u2500\u2534");
        }
        System.out.println("\u2500\u2500\u2500\u2518");
    }

    private static boolean canAdd(int[][] matrix, int i, List<Integer> currentStateOfClique) {
        for(int j : currentStateOfClique){
            if(matrix[j][i] == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean findClique(int[][] matrix, int k, int start, List<Integer> currentStateOfClique) {
        if(currentStateOfClique.size() == k){
            return true;
        }

        for(int i=start; i<matrix.length; i++){
            if(canAdd(matrix,i,currentStateOfClique)) {
                currentStateOfClique.add(i);
//                System.out.println(currentStateOfClique);
                if(findClique(matrix,k,i+1,currentStateOfClique)){
                    return true;
                }
                currentStateOfClique.remove(currentStateOfClique.size()-1);
            }
        }
        return false;
    }

    public static boolean hasClique(int[][] matrix, int k) {
        return findClique(matrix, k, 0, new ArrayList<>());
    }

    public static int[][] complementaryMatrix(int[][] matrix) {
        int n = matrix.length;
        int[][] complementaryMatrix = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i != j){
                    complementaryMatrix[i][j] = matrix[i][j]==1 ? 0 : 1;
                }
            }
        }
        return complementaryMatrix;
    }

    public static boolean hasStableSet(int[][] matrix, int k) {
       int[][] complementaryMatrix = complementaryMatrix(matrix);
       return hasClique(complementaryMatrix, k);
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        int[][] matrix = generateGraph(n);

        boolean cliqueExists = hasClique(matrix, k);
        boolean stableSetExists = hasStableSet(matrix, k);

        printMatrix(matrix, n);
        System.out.println("Clique of size "+ k +" : " + cliqueExists);
        System.out.println("Stable set of size "+ k +" : " + stableSetExists);

    }
}