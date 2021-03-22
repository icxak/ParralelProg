package matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogicCorrectnessTest {


    @Test
    void multiplyMatrixMatrix() throws InterruptedException {

        int[][] a, b, c;
        int dim = 3;
        // https://lh3.googleusercontent.com/proxy/Mw3PRo8eosYkC09qnlvumV1HJBpk98UzXRCOMMapwaWaSp9_FOJkBebklCv8VTH5xVSqmpvq8maI6nxlDcg5fLJwl2BmwrZBsjm3v0ACa-sZvhNUgKmmtdRzWTRh4CoGoQ
        a = new int[][]{{5, 3, -7}, {-1, 6, -3}, {2, -4, 1}};

        b = new int[][]{{4, -1, 3}, {4, -2, -6}, {2, 0, 3}};


        c = new int[dim][dim];

        Logic.MultiplyMatrixMatrix(a, b, c, dim, 3);

        var answer = new int[][]{{18, -11, -24}, {14, -11, -48}, {-6, 6, 33}};

        Main.PrintMatrix(c, dim);
        System.out.println("correct: ");
        Main.PrintMatrix(answer, dim);

        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {

                assertEquals(answer[i][j], c[i][j]);
            }
        }


    }

    @Test
    void multiplyMatrixNumber() throws InterruptedException {
        int[][] a, answer;
        int dim = 3;
        a = new int[][]{{5, -1, 3}, {4, -2, -6}, {2, 0, 3}};
        int num = 3;
        answer = new int[][]{{num*5, -1*num, 3*num}, {4*num, -2*num, -6*num}, {2*num, 0*num, 3*num}};


        System.out.println("\tCheck correctness multiplying matrix and number..");

        Logic.MultiplyMatrixNumber(a, num, dim, 3);

        System.out.println("\tMy answer: ");
        Main.PrintMatrix(a, dim);
        System.out.println("\tcorrect: ");
        Main.PrintMatrix(answer, dim);

        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {

                assertEquals(answer[i][j], a[i][j]);
            }
        }

    }

    @Test
    void transpose() throws InterruptedException {

        int[][] b, bt;
        int dim = 3;

        b = new int[][]{{4, -1, 3}, {4, -2, -6}, {2, 0, 3}};

        bt = new int[dim][dim];

        Logic.Transpose(b, bt, dim, 3);

        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {

                assertEquals(b[i][j], bt[j][i]);
            }
        }
    }
}