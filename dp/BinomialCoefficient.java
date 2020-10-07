/**
 * ******* Created  on 7/10/20 6:15 PM*******
 */

import java.io.*;
import java.util.*;

public class BinomialCoefficient implements Runnable {

    private static final int MAX = (int) (1E5 + 5);
    private static final int MOD = (int) (1E9 + 7);
    private static final long Inf = (long) (1E14 + 10);
    private static final double eps = (double) (1E-9);

    /**
     *
     * @throws IOException
     */

    long[] fact = new long[MAX];
    long[] invFact = new long[MAX];
    long[][] C = new long[1005][1005];
    public void pascalinit(){
        for(int n=1; n <=1000;n++) {
            C[n][0] = C[n][n]=1;
            for(int k =1; k < n;k++) {
                C[n][k] =( C[n-1][k-1] + C[n-1][k])%MOD;
            }
        }
    }

    public void init(){
        fact[0] =1; invFact[0]=1;
        for(int i=1;i<MAX;i++)
            fact[i] = (fact[i-1] *1L*i) %MOD;
        for(int i=1;i<MAX;i++)
            invFact[i] =pow(fact[i], MOD-2)%MOD;
    }
    private long pow(long a, long b) {
        long res =1;
        while (b >0){
            if(b%2 ==1){
                res = (res *a)%MOD;
            }
            a =(a *a)%MOD;
            b/=2;
        }
        return res;
    }
    public long cnk(int n , int k){
        return  fact[n]*invFact[k]%MOD * invFact[n-k]%MOD;
    }

    private void solve() throws IOException {
        pascalinit();
        init();
        writer.println(C[400][260]);
        writer.println(cnk(400,260));
    }

    public static void main(String[] args) throws IOException {
        try (Input reader = new StandardInput(); PrintWriter writer = new PrintWriter(System.out)) {
            new BinomialCoefficient().run();
        }
    }

    StandardInput reader;
    PrintWriter writer;

    @Override
    public void run() {
        try {
            reader = new StandardInput();
            writer = new PrintWriter(System.out);
            solve();
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    interface Input extends Closeable {
        String next() throws IOException;

        String nextLine() throws IOException;

        default int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        default long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        default double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        default int[] readIntArray() throws IOException {
            return readIntArray(nextInt());
        }

        default int[] readIntArray(int size) throws IOException {
            int[] array = new int[size];
            for (int i = 0; i < array.length; i++) {
                array[i] = nextInt();
            }
            return array;
        }

        default long[] readLongArray(int size) throws IOException {
            long[] array = new long[size];
            for (int i = 0; i < array.length; i++) {
                array[i] = nextLong();
            }
            return array;
        }
    }

    private static class StandardInput implements Input {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        private StringTokenizer stringTokenizer;

        @Override
        public void close() throws IOException {
            reader.close();
        }

        @Override
        public String next() throws IOException {
            if (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                stringTokenizer = new StringTokenizer(reader.readLine());
            }
            return stringTokenizer.nextToken();
        }

        @Override
        public String nextLine() throws IOException {
            return reader.readLine();
        }
    }

}
