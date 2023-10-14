package ru.vsu.cg.trianglerasterizer;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        //System.out.println(castToBits());
        castToBitsTest();
        ifTest();
        ternaryTest();
    }

    private static void castToBitsTest() {
        long start = System.nanoTime();
        for (long i = 0; i < 10_000_000_000L; i++) {
            castToBits();
        }
        long end = System.nanoTime();
        System.out.println("Bits test:" + ((end - start) / 1_000_000));
    }

    private static void ifTest() {
        long start = System.nanoTime();
        for (long i = 0; i < 10_000_000_000L; i++) {
            castToBits();
        }
        long end = System.nanoTime();
        System.out.println("If test:" + ((end - start) / 1_000_000));
    }

    private static void ternaryTest() {
        long start = System.nanoTime();
        for (long i = 0; i < 10_000_000_000L; i++) {
            castToBits();
        }
        long end = System.nanoTime();
        System.out.println("Ternary test:" + ((end - start) / 1_000_000));
    }

    private static double castToBits() {
        double d = -123.0D;
        long longBits = Double.doubleToRawLongBits(d);
        longBits &= ~(1L << 63);
        return Double.longBitsToDouble(longBits);
    }

    private static double ifCast() {
        double d = -123.0D;
        if (d < 0) {
            d = 0.0D;
        }
        return d;
    }

    private static double ternaryCast() {
        double d = -123.0D;
        d = (d < 0) ? 0.0D : d;
        return d;
    }


    private static String getBits(long l) {
        String str = "";
        for (int i = 0; i < 64; i++) {
            str = (l & (1L << i)) + str;
        }

        return str;
    }
}
