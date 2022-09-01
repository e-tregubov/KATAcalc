import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Numbers {

    // проверяет диапазон арабского числа
    static boolean isArabic(String string) { return string.matches("^[1-9]$|^10$"); }

    // проверяет диапазон римского числа
    static boolean isRoman(String string) { return string.matches("^IX$|^IV$|^V?I{0,3}$|^X$"); }

    // возвращает арабское число из римского
    public static int toArabic(String roman) {
            int result = 0;
            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
            int i = 0;

            while ((roman.length() > 0) && (i < romanNumerals.size())) {
                    RomanNumeral symbol = romanNumerals.get(i);
                if (roman.startsWith(symbol.name())) {
                    result += symbol.getValue();
                    roman = roman.substring(symbol.name().length());
                }
                else i++;
            }

            if (roman.length() > 0) { throw new IllegalArgumentException(roman + " - невозможно конвертировать в римские цифры!"); }
            return result;
    }

    // возвращает римское число из арабского
    public static String toRoman(int number) {
        if (number < 1) { throw new IllegalArgumentException(number + " - римское число не может быть меньше единицы!"); }
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else i++;
        }
        return sb.toString();
    }

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100);
        private final int value;
        RomanNumeral(int value) { this.value = value; }
        public int getValue() { return value; }
        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

}