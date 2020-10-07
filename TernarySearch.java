package lib;/**
 * ******* Created  on 7/10/20 4:55 PM*******
 *  https://www.spoj.com/problems/KOPC12A/
 */

import java.io.*;
import java.util.*;

public class TernarySearch implements Runnable {

    private static final int MAX = (int) (1E6 + 5);
    private static final int MOD = (int) (1E9 + 7);
    private static final long Inf = (long) (1E14 + 10);
    private static final double eps = (double) (1E-9);

    double ternarySearch(double l, double r){
        while (r - l > eps){
            double m1 = l + (r-l)/3;
            double m2 = r - (r-l)/3;

            double f1 = f(m1);
            double f2 = f(m2);
            if(f1 < f2)
                l  = m1 ;
            else
                r = m2;
        }
        return f(l);
    }

    private double f(double m1) {
        return 0.0;
    }
    private void solve() throws IOException {
        int t = 1;
        t = reader.nextInt();
        while (t-- > 0) {
            int n = reader.nextInt();
            int[] h = new int[n];
            int[] c = new int[n];
            for(int i=0;i< n;i++)
                h[i] = reader.nextInt();
            for(int i=0;i<n;i++)
                c[i] = reader.nextInt();

            int l =0, r = MAX;
            while (r-l>2){
                int mid1 = l + (r-l)/3;
                int mid2 = r - (r-l)/3;

                long val1 = cost(mid1,h,c);
                long val2 = cost(mid2,h,c);

                if(val1 < val2){
                    r=mid2;
                }else{
                    l= mid1;
                }
            }
            long mn = Math.min(cost(l+1, h,c), cost(l+2, h,c));
            writer.println(Math.min(cost(l,h,c), mn));
        }
    }

    private long cost(int mid, int[] h, int[] c) {
        long cost =0;
        for(int i=0;i<h.length;i++){
            cost += Math.abs(h[i] - mid)*1L*c[i];
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        try (Input reader = new StandardInput(); PrintWriter writer = new PrintWriter(System.out)) {
            new TernarySearch().run();
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
