import java.util.*;

public class Graph {
    public static void main(String[] args) {
        int[] vertices;
        List<Integer> clique = new ArrayList<>();
        List<Integer> stableSet = new ArrayList<>();

        int[][] matrix= new int[30][30];
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        if(n%2==1 || k > n/2){
            if(k>n/2+1){
                System.out.println("The second parameter(representing k) should be <= " + (n/2+1) );
                return;
            }
        }

        matrix = generateGraph(n, k);

        generateClique(matrix, n, k, clique);

        printMatrix(matrix, n);

    }



    public static void generateClique(int[][] matrix, int n, int k, List<Integer> clique) {

        Random random = new Random();
        while(clique.size() < k) {
            int vertex = random.nextInt(n);
            if(!clique.contains(vertex)){
                clique.add(vertex);
            }
        }
        System.out.println("Clique vertices: " + clique);
        for(int i : clique) {
            for(int j : clique) {
                if (i == j) {
                    matrix[i][j] = 0;
                    matrix[j][i] = 0;
                } else {
                    matrix[i][j] = 1;
                }
            }
        }

    }

    private static void printMatrix(int[][] matrix,int n){
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int[][] generateGraph(int n, int k) {
        int[][] matrix = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<k;j++) {
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

}