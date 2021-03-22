package matrix;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LogicTimeTest {
    Object o = new Object();

    @Test
    void multiplyMatrixMatrix() throws InterruptedException {

        StringBuilder sb = new StringBuilder();

        var dims = new int[]{3163, 5000};

        for(int dim : dims) {
            var coresArr = new int[]{Runtime.getRuntime().availableProcessors(),
                    64, 128,256,512};

            for (int cores : coresArr) {

 //               synchronized (o) {

                    var a = new int[dim][dim];
                    var b = new int[dim][dim];

                    var c = new int[dim][dim];

                    Main.Randomize(a, b, dim, cores);

                    long s = System.currentTimeMillis();

                    Logic.MultiplyMatrixMatrix(a, b, c, dim, cores);

                    long e = System.currentTimeMillis();

                    sb.append("dim =  ").append(dim)
                            .append(" numOfThreads = ").append(cores).append(" time = ").
                            append(e - s).append(" ms\n");
   //             }
            }

        }

        try(FileOutputStream fos=new FileOutputStream("mm-output.txt");
            PrintStream printStream = new PrintStream(fos))
        {
            printStream.println(sb.toString());
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    @Test
    void multiplyMatrixNumber() throws InterruptedException {
        StringBuilder sb = new StringBuilder();

        var dims = new int[]{3163, 5000,10000};

        for(int dim : dims) {
            var coresArr = new int[]{Runtime.getRuntime().availableProcessors(),
                    64, 128, 1000};

            for (int cores : coresArr) {


 //               synchronized (o) {

                    var a = new int[dim][dim];
                    var b = new int[dim][dim];


                    Main.Randomize(a, b, dim, cores);

                    long s = System.currentTimeMillis();

                    Logic.MultiplyMatrixNumber(a, 5, dim, cores);

                    long e = System.currentTimeMillis();

                    sb.append("dim =  ").append(dim)
                            .append(" numOfThreads = ").append(cores).append(" time = ").
                            append(e - s).append(" ms\n");
 //               }
            }

        }

        try(FileOutputStream fos=new FileOutputStream("mn-output.txt");
            PrintStream printStream = new PrintStream(fos))
        {
            printStream.println(sb.toString());
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}