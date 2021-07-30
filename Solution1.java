// Solution involves memorised DP and a little recursion.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.math.*;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(reader.readLine());
        long[] nums=new long[t];
        String[] inputs=reader.readLine().split(" ");
        for (int i=0;i<t;i++){
            nums[i]=Long.parseLong(inputs[i]);
        }
        HashMap<Long, Long> d=new HashMap<>();
        d.put((long) 1, (long) 1);
        d.put((long) 2, (long) 3);


        boolean[] prime=new boolean[1000001];
        Arrays.fill(prime, true);

        List<Long> primes=new ArrayList<Long>();
        primes.add((long) 2);
        prime[1]=false;
        for (int i=4;i<1000001;i+=2){
            prime[i] = false;
        }

        for (int i=3;i*i<1000001;i+=2){
            if (prime[i]==true) {
                primes.add((long) i);
                for (int j = i * i; j < 1000001; j += i) {
                    prime[j] = false;
                }
            }
        }

        /*long r=(long) Math.pow(10,12);
        long limit=(long) (Math.sqrt(r));
        long low=limit;
        long high=low+limit;
        while (low <= r) {
            if (high >= r)
                high = r;
            boolean[] mark = new boolean[(int) (limit + 1)];
            Arrays.fill(mark, true);
            for (long i : primes) {
                long lowlimit = (long) (Math.floor(low / i) * i);
                if (lowlimit < low)
                    lowlimit += i;
                for (long j = lowlimit; j < high + 1; j += i)
                    mark[(int) (j - low)] = false;
            }
            for (long i = low; i < high + 1; i++) {
                if (mark[(int) (i - low)])
                    primes.add(i);
            }

            low += limit;
            high += limit;
        }*/



        /*for (long i=3; i<1001;i++){
            d.put(i, func(i, d, primes));
        }*/


        long summ=0;
        for (long p:nums) {
            summ += func(p, d, primes);
        }
        System.out.println(summ);

    }


    private static long func(long n, HashMap<Long, Long> d, List<Long> primes) {
        if (primes.contains(n)){                                            //when n is prime, simply return n+1
            d.put(n,n+1);
            return n + 1;
        }
        if (d.containsKey(n)) {                                             
            return d.get(n);
        }
        long su = 0;
        boolean flag=false;
        for (long i=2; i<=(Math.sqrt(n))+1; i++) {
            if (n % i == 0) {
                flag=true;
                long groups = n / i;
                su += Math.max(func(i, d, primes) * groups + 1, func(groups, d, primes) * i + 1);       //main recursive step
                if (d.containsKey(n)){
                    d.put(n,Math.max(d.get(n),su));
                }
                else{
                    d.put(n,su);
                }
            }
            su=0;
        }
        if (su==0 && flag==false){
            d.put(n,n+1);
        }
        return d.get(n);
    }
}
