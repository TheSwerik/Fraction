public class Fraction {
    private long numerator;
    private long denominator;

    // put in numerator and denominator directly:
    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    // convert decimal to fraction:
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

    // Operations with other Fraction:
    public Fraction add(Fraction newFraction) {
        long lcm = lcm(this.denominator, newFraction.getDenominator());
        this.numerator = (this.numerator / lcm) * this.numerator / lcm + (newFraction.getNumerator() / lcm) * newFraction.getNumerator();
        this.denominator *= (this.denominator / lcm);
        return this.reduce();
    }

    public Fraction subtract(Fraction newFraction) {
        return this.add(new Fraction(-newFraction.getNumerator(), newFraction.getDenominator()));
    }

    public Fraction multiply(Fraction newFraction) {
        this.numerator *= newFraction.getNumerator();
        this.denominator *= newFraction.getDenominator();
        return this.reduce();
    }

    public Fraction divide(Fraction newFraction) {
        this.numerator *= newFraction.getDenominator();
        this.denominator *= newFraction.getNumerator();
        return this.reduce();
    }

    // Operations with primitive Types:
    public Fraction add(long number) {
        this.numerator += number * this.denominator;
        return this.reduce();
    }

    public Fraction subtract(long number) {
        this.numerator -= number * this.denominator;
        return this.reduce();
    }

    public Fraction multiply(long number) {
        this.numerator *= number;
        return this.reduce();
    }

    public Fraction divide(long number) {
        this.denominator *= number;
        return this.reduce();
    }

    //reducing:
    public Fraction reduce() {
        for (int i = 2; i <= Math.min(this.numerator, this.denominator); i++) {
            if (((double) this.numerator) / i == (int) ((double) this.numerator / i) &&
                    ((double) this.denominator) / i == (int) ((double) this.denominator / i)) {
                this.numerator /= i;
                this.denominator /= i;
                i = 2;
            }
        }
        return this;
    }

    //getter:
    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    //Utils:
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a;
            a = b;
            b = t % b;
        }
        return a;
    }

    private long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        } else {
            return Math.abs(number1 * number2) / gcd(number1, number2);
        }
    }
}
