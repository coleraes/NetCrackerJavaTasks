package educenter.tasks.basics.quadraticequation;

import static java.lang.Math.sqrt;
import java.util.Scanner;

/**
 * Класс QuadraticEquation позволяет решать квадратные уравнения вида ax^2 + bx + c = 0.
 * Вычисление дискриминанта осуществляет вложенный статический класс Discriminant.
 * При компиляции для каждого класса создается один .class файл.
 * @author Valentin Timoshkin
 */
public class QuadraticEquation {

    private QuadraticEquation() {}
    
    static class Discriminant {
        /**
         * Вычисляет дискриминант квадратного уравнения вида ax^2 + bx + c = 0.
         * @param a
         * @param b
         * @param c
         * @return значение дискриминанта
         */
        static double countDiscriminant (double a, double b, double c) {
            return b * b - 4 * a * c;
        }
    }
    
    /**
     * Находит решения линейного уравнение вида bx + c = 0.
     * @param b
     * @param c 
     */
    public static void solveLinear (double b, double c) {
        if (b == 0) {
            if (c == 0) {
                System.out.println("Бесконечное множество решений");
            } else {
                System.out.println("Нет решений");
            }
        } else {
            double root = -c / b;
            System.out.println("Решение уравнения: x = " + root);
        }
    }
    /**
     * Находит решения квадратного уравнения вида ax^2 + bx + c = 0.
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
                System.out.println("Нет решений");
            }
            if (discriminant == 0) {
                double root = -b / (2 * a);
                System.out.println("Решение уравнения: x = " + root);
            }
            if (discriminant > 0) {
                double root1 = (-b + sqrt(discriminant))/ (2 * a);
                double root2 = (-b - sqrt(discriminant))/ (2 * a);
                System.out.println("Решения уравнения: x1 = " + root1 + ", x2 = " + root2);
            }
        }
    }
    
    public static void main(String[] args) {
        double a, b, c;
        System.out.println("Введите коэффициенты a, b и c квадратного уравнения ax^2 + bx + c = 0:");
        Scanner in = new Scanner(System.in);
        a = in.nextDouble();
        b = in.nextDouble();
        c = in.nextDouble();
        QuadraticEquation.solveQuadratic(a, b, c);
    }
}
