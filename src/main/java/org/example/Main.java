package org.example;

import java.util.*;
import java.lang.Math;

public class Main {
    static int fac(int a){
        int ans=1;
        for(int i=1;i<=a;++i)
            ans*=i;
        return ans;
    }
    static void main() {

        Scanner scan = new Scanner(System.in);
        Random  rand = new Random();
        Map<String,Integer> map = new HashMap<String,Integer>();

        int n=5;
        int p=5;
        int k=1;
        int ans=0;
        while(n/(Math.pow(p,k))>0){
            ans+=n/(Math.pow(p,k));
            k++;
        }
        System.out.println((ans));

        ans=0;
        n=fac(n);

        while(n%p==0){

            n/=p;
            ans+=1;
        }
        System.out.println(ans);


        ArrayList<Integer> a = new ArrayList();





    }
}
