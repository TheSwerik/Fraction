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
        long gcd = gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
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
        return (number1 == 0 || number2 == 0) ? 0 : Math.abs(number1 * number2) / gcd(number1, number2);
    }
}
