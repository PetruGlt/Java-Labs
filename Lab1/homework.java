import java.util.*;

public class Graph {

    public static int[][] generateGraph(int n, int k) {
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
                if (i != j) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                }
            }
        }

        for(int i : clique) {
            for(int j=0;j<n;j++){
                if(!clique.contains(j)){
                    matrix[i][j]=0;
                    matrix[j][i]=0;
                }
            }
        }
    }

    public static void generateStableSet(int[][] matrix, int n, int k, List<Integer> clique, List<Integer> stableSet) {
        List<Integer> availableVertices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!clique.contains(i)) {
                availableVertices.add(i);
            }
        }

        Random random = new Random();
        List<Integer> potentialStableSet = new ArrayList<>();

        while (!availableVertices.isEmpty()) {
            int vertex = availableVertices.remove(random.nextInt(availableVertices.size()));

            if (!isConnectedToStableSet(matrix, vertex, stableSet)) {
                stableSet.add(vertex);
            } else {
                potentialStableSet.add(vertex);
            }

            if (stableSet.size() == k) break;
        }

        while (stableSet.size() < k && !potentialStableSet.isEmpty()) {
            stableSet.add(potentialStableSet.remove(0));
        }

        if (stableSet.size() < k && k == (n / 2 + 1)) {
            stableSet.add(clique.get(0));
        }

        System.out.println("Stable Set: " + stableSet);

        for (int i : stableSet) {
            for (int j : stableSet) {
                matrix[i][j] = 0;
                matrix[j][i] = 0;
            }
        }
    }

    private static boolean isConnectedToStableSet(int[][] matrix, int vertex, List<Integer> stableSet) {
        for(int value : stableSet){
            if(matrix[vertex][value]==1){
                return true;
            }
        }
        return false;
    }

    public static int vertexDegree(int[][] matrix, int n , int vertex){
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=matrix[vertex][i];
        }
        return sum;
    }

    public static int numberOfEdges(int matrix[][],int n){
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(matrix[i][j]==1){
                    count++;
                }
            }
        }
        return count;
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

    public static void main(String[] args) {

        List<Integer> clique = new ArrayList<>();
        List<Integer> stableSet = new ArrayList<>();

        //1.
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        // Daca n este par si k > n/2, nu putem avea simultan clica si multime stabila de dimensiunea k
        if((n%2==0) && k > n/2) {
                System.out.println("The second parameter(representing k) should be <= " + (n/2) );
                return;
        }

        // Daca n este impar si k > partea superioara a lui(n/2) , nu putem avea simultan clica si multime stabila de dimensiunea k
        if((n%2 == 1) && k > n/2+1){
            System.out.println("The second parameter(representing k) should be <= " + ((n/2)+1) );
            return;
        }

        long startTime = System.nanoTime();
        //2.
        int[][] matrix= generateGraph(n, k);

        generateClique(matrix, n, k, clique);
        generateStableSet(matrix,n,k,clique,stableSet);

        //3.
        if(n<30) {
            printMatrix(matrix, n);
        }
        //4.
        System.out.println("Number of edges: " + numberOfEdges(matrix,n));

        //5.
        int min = n;
        int max = 0;
        for(int i = 0;i<n;i++){
            if(vertexDegree(matrix,n,i)<min){
                min = vertexDegree(matrix,n,i);
            }

            if(vertexDegree(matrix,n,i)>max){
                max = vertexDegree(matrix,n,i);
            }
        }
        System.out.println("\u0394(G): "+max);
        System.out.println("\u03B4(G): "+min);
        int sum=0;
        for(int i = 0;i<n;i++){
            sum+=vertexDegree(matrix,n,i);
        }
//        System.out.println(sum);
        //6.
        if(sum == 2*numberOfEdges(matrix,n)){
            System.out.println("True: sum(degreeVertices) = 2*m");
        }
//        System.out.println(numberOfEdges(matrix, n));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1_000_000;
        System.out.println("Execution time: " + duration+"ms");
    }
}
/*
    petru10@PetruGlt-MRC-WX0:~/IdeaProjects/Java-Labs/Lab1$ java -Xms4G -Xmx4G homework.java 30023 23
    Clique vertices: [14114, 22408, 5732, 23328, 15377, 24624, 18936, 10760, 99, 1566, 11660, 24287, 6263, 14691, 26254, 27316, 9011, 12856, 25188, 11243, 20224, 11627, 20617]
    Stable Set: [22153, 28862, 22176, 3465, 12370, 8538, 20702, 10015, 7670, 28385, 17136, 10588, 11540, 12810, 29304, 24243, 6966, 10433, 10197, 3653, 3941, 315, 19473]
    Number of edges: 224976511
    Δ(G): 15319
    δ(G): 22
    True: sum(degreeVertices) = 2*m
    Execution time: 51898ms
*/

    /*
petru10@PetruGlt-MRC-WX0:~/IdeaProjects/Java-Labs/Lab1$ java -Xms4G -Xmx4G homework.java 10 4
Clique vertices: [1, 8, 2, 7]
Stable Set: [9, 6, 5, 0]
      0   1   2   3   4   5   6   7   8   9
   ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
 0 │ △ │ △ │ △ │ ▲ │ △ │ △ │ △ │ △ │ △ │ △ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 1 │ △ │ △ │ ▲ │ △ │ △ │ △ │ △ │ ▲ │ ▲ │ △ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 2 │ △ │ ▲ │ △ │ △ │ △ │ △ │ △ │ ▲ │ ▲ │ △ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 3 │ ▲ │ △ │ △ │ △ │ ▲ │ ▲ │ ▲ │ △ │ △ │ ▲ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 4 │ △ │ △ │ △ │ ▲ │ △ │ △ │ △ │ △ │ △ │ ▲ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 5 │ △ │ △ │ △ │ ▲ │ △ │ △ │ △ │ △ │ △ │ △ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 6 │ △ │ △ │ △ │ ▲ │ △ │ △ │ △ │ △ │ △ │ △ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 7 │ △ │ ▲ │ ▲ │ △ │ △ │ △ │ △ │ △ │ ▲ │ △ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 8 │ △ │ ▲ │ ▲ │ △ │ △ │ △ │ △ │ ▲ │ △ │ △ │
   ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
 9 │ △ │ △ │ △ │ ▲ │ ▲ │ △ │ △ │ △ │ △ │ △ │
   └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
Number of edges: 12
Δ(G): 5
δ(G): 1
True: sum(degreeVertices) = 2*m
Execution time: 32ms
*/