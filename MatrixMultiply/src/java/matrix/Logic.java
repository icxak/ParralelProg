package matrix;

public class Logic {


    public static void MultiplyMatrixMatrix(int[][] a, int[][] b, int[][] c, int dim, int cores)
            throws InterruptedException {

        System.out.println("*****Start of multiplying matrices");
        System.out.println("*****dim = " + dim + " threads = " + cores);

        var bt = new int[dim][dim];

        // Оптимизация доступа к кэшу https://habr.com/ru/sandbox/32747/
        Transpose(b, bt, dim, cores);

        var threads = new Thread[cores];

        for (int i = 0; i < cores; i++) {

            int k = i;

            threads[i] = new Thread(() -> {
                synchronized (threads) {
                    System.out.println("MM Thread #" + k + " starts");
                }
                for (int x = k; x < dim; x += cores) {

                    for (int y = 0; y < dim; y++) {

                        for (int z = 0; z < dim; z++) {

                            c[x][y] += a[x][z] * bt[y][z];


                        }
                    }
                }

                synchronized (threads) {
                    System.out.println("MM Thread #" + k + " ends");
                }
            });


            threads[i].start();
        }

        for (var i : threads) {
            i.join();
        }

        System.out.println("*****End of multiplying matrices\n");
    }

    public static void MultiplyMatrixNumber(int[][] a, int num, int dim, int cores) throws InterruptedException {

        System.out.println("*****Start of multiplying matrix and number");
        System.out.println("*****dim = " + dim + " threads = " + cores);

        var threads = new Thread[cores];

        for (int i = 0; i < cores; i++) {
            int k = i;
            threads[i] = new Thread(() -> {

                synchronized (threads) {
                    System.out.println("MN Thread #" + k + " starts");
                }

                for (int x = k; x < dim; x += cores) {

                    for (int y = 0; y < dim; y++) {

                        a[x][y] *= num;
                    }
                }
                synchronized (threads) {
                    System.out.println("MN Thread #" + k + " ends");
                }


            });

            threads[i].start();
        }

        for (var i : threads) {
            i.join();
        }

        System.out.println("*****End of multiplying matrix and number\n");
    }

    public static void Transpose(int[][] m, int[][] mt, int n, int cores) throws InterruptedException {
        System.out.println("\t*****Start of transposing a matrix");

        var threads = new Thread[cores];

        for (int i = 0; i < cores; i++) {
            int k = i;
            threads[i] = new Thread(() -> {
                synchronized (threads) {
                    System.out.println("\tT Thread #" + k + " starts");
                }
                for (int j = k; j < n; j += cores) {
                    for (int l = 0; l < n; l++) {

                        mt[l][j] = m[j][l];
                    }
                }

                synchronized (threads) {
                    System.out.println("\tT Thread #" + k + " ends");
                }

            });

            threads[i].start();
        }

        for (var i : threads) {
            i.join();
        }

        System.out.println("\t*****End of transposing a matrix\n");

    }
}
