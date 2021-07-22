package educenter.tasks.basics;

import static java.lang.Math.sqrt;
import java.util.Scanner;

/**
 * The QuadraticEquation class allows you to solve a quadratic equation of the form ax ^ 2 + bx + c = 0.
 * The calculation of the discriminant is performed by the nested static class Discriminant.
 * Compilation creates one .class file for each class.
 * @author Valentin Timoshkin
 */
public class QuadraticEquation {

    private QuadraticEquation() {}
    
    static class Discriminant {
        /**
         * Calculates the discriminant of a quadratic equation of the form ax ^ 2 + bx + c = 0.
         * @param a
         * @param b
         * @param c
         * @return discriminant value
         */
        static double countDiscriminant (double a, double b, double c) {
            return b * b - 4 * a * c;
        }
    }
    
    /**
     * Solves a linear equation of the form bx + c = 0.
     * @param b
     * @param c 
     */
    public static void solveLinear (double b, double c) {
        if (b == 0) {
            if (c == 0) {
                System.out.println("Infinite number of roots");
            } else {
                System.out.println("No roots");
            }
        } else {
            double root = -c / b;
            System.out.println("There is 1 root: x = " + root);
        }
    }
    /**
     * Solves a quadratic equation of the form ax ^ 2 + bx + c = 0.
     * @param a
     * @param b
     * @param c 
     */
    public static void solveQuadratic (double a, double b, double c) {
        if (a == 0) {
            QuadraticEquation.solveLinear(b, c);
        } else {
            double discriminant = QuadraticEquation.Discriminant.countDiscriminant(a, b, c);
            if (discriminant < 0) {
                System.out.println("No roots");
            }
            if (discriminant == 0) {
                double root = -b / (2 * a);
                System.out.println("There is 1 root: x = " + root);
            }
            if (discriminant > 0) {
                double root1 = (-b + sqrt(discriminant))/ (2 * a);
                double root2 = (-b - sqrt(discriminant))/ (2 * a);
                System.out.println("There are 2 roots: x1 = " + root1 + ", x2 = " + root2);
            }
        }
    }
    
    public static void main(String[] args) {
        double a, b, c;
        System.out.println("Input the coefficients a, b and c of the quadratic equation ax ^ 2 + bx + c = 0:");
        Scanner in = new Scanner(System.in);
        a = in.nextDouble();
        b = in.nextDouble();
        c = in.nextDouble();
        QuadraticEquation.solveQuadratic(a, b, c);
    }
}
