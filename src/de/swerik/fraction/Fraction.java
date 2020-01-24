package de.swerik.fraction;

public class Fraction {
    private long numerator;
    private long denominator;

    /**
     * Creates a de.swerik.fraction.Fraction from {@code numerator} and {@code denominator}
     *
     * @param numerator   long
     * @param denominator long
     */
    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    /**
     * Creates a de.swerik.fraction.Fraction from {@code decimal}
     *
     * @param decimal long
     */
    public Fraction(double decimal) {
        long denom = 1;
        long digitsDec = (decimal + "").length() - (decimal + "").indexOf('.');
        for (int i = 0; i <= digitsDec; i++) {
            decimal *= 10;
            denom *= 10;
        }

        this.numerator = Math.round(decimal);
        this.denominator = denom;
        this.reduce();
    }

    // Operations with other de.swerik.fraction.Fraction:

    /**
     * @param newFraction de.swerik.fraction.Fraction
     * @return this de.swerik.fraction.Fraction with {@code newFraction} added to it
     */
    public Fraction add(Fraction newFraction) {
        long lcm = lcm(this.denominator, newFraction.getDenominator());
//        System.out.println((lcm / this.denominator) * this.numerator);
//        System.out.println((lcm / newFraction.getDenominator()) * newFraction.getNumerator());
        this.numerator = (lcm / this.denominator) * this.numerator + (lcm / newFraction.getDenominator()) * newFraction.getNumerator();
//        System.out.println(this.numerator);
        this.denominator = lcm;
        return this.reduce();
    }

    /**
     * @param newFraction de.swerik.fraction.Fraction
     * @return this de.swerik.fraction.Fraction subtracted by {@code newFraction}
     */
    public Fraction subtract(Fraction newFraction) {
        return this.add(new Fraction(-newFraction.getNumerator(), newFraction.getDenominator()));
    }

    /**
     * @param newFraction de.swerik.fraction.Fraction
     * @return this de.swerik.fraction.Fraction multiplied by {@code newFraction}
     */
    public Fraction multiply(Fraction newFraction) {
        this.numerator *= newFraction.getNumerator();
        this.denominator *= newFraction.getDenominator();
        return this.reduce();
    }

    /**
     * @param newFraction de.swerik.fraction.Fraction
     * @return this de.swerik.fraction.Fraction divided by {@code newFraction}
     */
    public Fraction divide(Fraction newFraction) {
        this.numerator *= newFraction.getDenominator();
        this.denominator *= newFraction.getNumerator();
        return this.reduce();
    }

    // Operations with primitive Types:

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction multiplied by {@code number}
     */
    public Fraction add(long number) {
        this.numerator += number * this.denominator;
        return this.reduce();
    }

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction with {@code number} to it
     */
    public Fraction subtract(long number) {
        this.numerator -= number * this.denominator;
        return this.reduce();
    }

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction subtracted by {@code number}
     */
    public Fraction multiply(long number) {
        this.numerator *= number;
        return this.reduce();
    }

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction divided by {@code number}
     */
    public Fraction divide(long number) {
        this.denominator *= number;
        return this.reduce();
    }

    /**
     * reduces this de.swerik.fraction.Fraction as much as possible
     *
     * @return this de.swerik.fraction.Fraction but reduced
     */
    public Fraction reduce() {
        long gcd = gcd(this.numerator, this.denominator);
        if (gcd != 0) {
            this.numerator /= gcd;
            this.denominator /= gcd;
        }
        if (this.denominator < 0) {
            this.denominator = -this.denominator;
            this.numerator = -this.numerator;
        }
        return this;
    }

    //getter:

    /**
     * @return the current numerator as long
     */
    public long getNumerator() {
        return numerator;
    }

    /**
     * @return the current denominator as long
     */
    public long getDenominator() {
        return denominator;
    }

    /**
     * @return de.swerik.fraction.Fraction as String in the format: n/d
     */
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    //Utils:

    /**
     * @param number1 long
     * @param number2 long
     * @return the greatest common divisor of {@code number1} and {@code number2}
     */
    private long gcd(long number1, long number2) {
        while (number2 != 0) {
            long t = number1;
            number1 = number2;
            number2 = t % number2;
        }
        return number1;
    }

    /**
     * @param number1 long
     * @param number2 long
     * @return the least common multiple of {@code number1} and {@code number2}
     */
    private long lcm(long number1, long number2) {
        return (number1 == 0 || number2 == 0) ? 0 : Math.abs(number1 * number2) / gcd(number1, number2);
    }
}
