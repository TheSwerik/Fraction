package de.swerik.fraction;

public class Check {
    public static void main(String[] args) {
        System.out.println(new Fraction(1, 1).sqrt());
        System.out.println(Math.sqrt(4 * 25 * 3));


        Fraction p = new Fraction(1, 6);
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(2, 3);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(a.add(b).toString());
        a = new Fraction(1, 2);
        b = new Fraction(2, 3);
        System.out.println(a.subtract(b).toString());
        a = new Fraction(1, 2);
        b = new Fraction(2, 3);
        System.out.println(a.multiply(b).toString());
        a = new Fraction(1, 2);
        b = new Fraction(2, 3);
        System.out.println(p.add(p.pow2().add(new Fraction(2 * 5, 3)).sqrt()));
        System.out.println("\t" + p + "\t" + p.pow2() + "\t" + p.pow2().add(new Fraction(2 * 13, 3)) + "\t" + new Fraction(2 * 13, 3) + "\t" + p.pow2().add(new Fraction(2 * 13, 3)).sqrt() + "\t" + p.add(p.pow2().add(new Fraction(2 * 13, 3)).sqrt()));
    }
}
