package matrix;

import java.util.Random;

public class Main {


    public static void main(String[] args) {




        try {

            int dim = Integer.parseInt(args[0]);
            int cores = Integer.parseInt(args[1]);

            var a = new int[dim][dim];
            var b = new int[dim][dim];
            var c = new int[dim][dim];


            Randomize(a, b, dim, cores);

            long s = System.currentTimeMillis();

            Logic.MultiplyMatrixNumber(a, 7, dim, cores);


            long e = System.currentTimeMillis();

            System.out.println("Matrix*Number time: " + (e - s) + " ms");

            //

            s = System.currentTimeMillis();

            Logic.MultiplyMatrixMatrix(a, b, c, dim, cores);

            e = System.currentTimeMillis();

            System.out.println("Matrix*Matrix time: " + (e - s) + " ms");





        }catch (Exception ex) {
            System.out.println("Input program args: dimension and number of threads.");
            System.out.println(ex.getMessage());
        }

    }

    static void Randomize(int[][] a, int[][] b, int dim, int cores) throws InterruptedException {
        Random rnd = new Random();


        var threads = new Thread[cores];

        for (int i = 0; i < cores; i++) {
            int k = i;
            threads[i] = new Thread(() -> {
                for (int j = k; j < dim; j += cores) {
                    for (int l = 0; l < dim; l++) {
                        a[j][l] = rnd.nextInt() % 10;
                        b[j][l] = rnd.nextInt() % 10;
                    }
                }
            });

            threads[i].start();
        }

        for (var i : threads) {
            i.join();
        }

    }

    static void PrintMatrix(int[][] m, int dim) {
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
