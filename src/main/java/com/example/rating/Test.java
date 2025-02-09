package com.example.rating;

public class Test {

    public static void main(String[] args) {
        pending(9);
    }



    private static int pending(int n)
    {
        return n <= 1 ? n : 2 * ( 1+n/2-pending(n/2));


    }
}
