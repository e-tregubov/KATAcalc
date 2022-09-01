import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("\nКалькулятор: ");
        System.out.print("Результат: " + calc(new Scanner(System.in).nextLine()) + "\n");
    }

    public static String calc(String input) {
        String[] part = input.split(" ");
        if (part.length != 3) throw new IllegalArgumentException(input + " возможна только одна операция [+-*/]");
        if (!part[1].matches("^[-+/*]$")) throw new IllegalArgumentException(input + " неправильная операция [+-*/]");

        if (Numbers.isArabic(part[0]) && Numbers.isArabic(part[2]))
            return "" + result(Integer.parseInt(part[0]), Integer.parseInt(part[2]), part[1]);

        if (Numbers.isRoman(part[0]) && Numbers.isRoman(part[2]))
            return Numbers.toRoman(result(Numbers.toArabic(part[0]), Numbers.toArabic(part[2]), part[1]));

        else throw new IllegalArgumentException(input + " некорректный ввод чисел [1-10][I-X]");
    }

    // возвращает результат операции двух чисел
    private static int result(int num1, int num2, String action) {
        return switch (action) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            default -> num1 / num2;
        };
    }

}