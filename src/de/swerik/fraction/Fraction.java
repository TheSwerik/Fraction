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

    /**
     * copies {@code newFraction} to this
     *
     * @param newFraction Fraction
     */
    private Fraction(Fraction newFraction) {
        this.numerator = newFraction.getNumerator();
        this.denominator = newFraction.getDenominator();
    }

    // Operations with other de.swerik.fraction.Fraction:

    /**
     * @param newFraction de.swerik.fraction.Fraction
     * @return this de.swerik.fraction.Fraction with {@code newFraction} added to it
     */
    public Fraction add(Fraction newFraction) {
        Fraction returnFraction = new Fraction(this);
        long lcm = lcm(returnFraction.denominator, newFraction.getDenominator());
        returnFraction.numerator = (lcm / returnFraction.denominator) * returnFraction.numerator + (lcm / newFraction.getDenominator()) * newFraction.getNumerator();
        returnFraction.denominator = lcm;
        return returnFraction.reduce();
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
        Fraction returnFraction = new Fraction(this);
        returnFraction.numerator *= newFraction.getNumerator();
        returnFraction.denominator *= newFraction.getDenominator();
        return returnFraction.reduce();
    }

    /**
     * @param newFraction de.swerik.fraction.Fraction
     * @return this de.swerik.fraction.Fraction divided by {@code newFraction}
     */
    public Fraction divide(Fraction newFraction) {
        Fraction returnFraction = new Fraction(this);
        returnFraction.numerator *= newFraction.getDenominator();
        returnFraction.denominator *= newFraction.getNumerator();
        return returnFraction.reduce();
    }

    // Operations with primitive Types:

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction multiplied by {@code number}
     */
    public Fraction add(long number) {
        Fraction returnFraction = new Fraction(this);
        returnFraction.numerator += number * returnFraction.denominator;
        return returnFraction.reduce();
    }

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction with {@code number} to it
     */
    public Fraction subtract(long number) {
        Fraction returnFraction = new Fraction(this);
        returnFraction.numerator -= number * returnFraction.denominator;
        return returnFraction.reduce();
    }

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction subtracted by {@code number}
     */
    public Fraction multiply(long number) {
        Fraction returnFraction = new Fraction(this);
        returnFraction.numerator *= number;
        return returnFraction.reduce();
    }

    /**
     * @param number long
     * @return this de.swerik.fraction.Fraction divided by {@code number}
     */
    public Fraction divide(long number) {
        Fraction returnFraction = new Fraction(this);
        returnFraction.denominator *= number;
        return returnFraction.reduce();
    }

    /**
     * @return the sqareroot of the Fraction
     */
    public Fraction sqrt() {
        Fraction returnFraction = new Fraction(this);
        Fraction tempA = new Fraction(Math.sqrt((double) returnFraction.numerator));
        Fraction tempB = new Fraction(Math.sqrt((double) returnFraction.denominator));
        tempA.divide(tempB);
        returnFraction.numerator = tempA.divide(tempB).getNumerator();
        returnFraction.denominator = tempA.divide(tempB).getDenominator();
        return returnFraction.reduce();
    }

    /**
     * @return the Fraction to the power of 2
     */
    public Fraction pow2() {
        Fraction returnFraction = new Fraction(this);
        returnFraction.numerator *= returnFraction.numerator;
        returnFraction.denominator *= returnFraction.denominator;
        return returnFraction.reduce();
    }

    /**
     * @param n long
     * @return the Fraction to the power of {@code n}
     */
    public Fraction pow(long n) {
        Fraction returnFraction = new Fraction(this);
        returnFraction.numerator = (long) Math.pow((double) returnFraction.numerator, (double) n);
        returnFraction.denominator = (long) Math.pow((double) returnFraction.denominator, (double) n);
        return returnFraction.reduce();
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
