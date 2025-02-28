
public class Main {

    public static int digitSum(int number) {
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        if(sum>9)
            return digitSum(sum);
        else
            return sum;
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] languages = {"C","C++","C#","Python","Go","Rust","JavaScript", "PHP", "Swift","Java"};

        int n = (int) (Math.random() * 1_000_000);
        n = n*3;
        int binaryNumber = Integer.parseInt("10101",2);
        int hexNumber = Integer.parseInt("FF",16);
        n = (n+binaryNumber+hexNumber)*6;
//        System.out.println(n);

//        System.out.println(digitSum(n));

        System.out.println("Willy-nilly, this semester I will learn "+languages[digitSum(n)]);
        }
    }
