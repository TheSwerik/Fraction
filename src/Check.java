public class Check {
    public static void main(String[] args) {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(2, 3);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(a.add(b).toString());
    }
}