import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int num1, num2, result;
    static char op;


    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение в арабском (1+1) или римском формате (X+V) от 1(I) до 10(X):");
        String uInput = scanner.nextLine();
        System.out.println("Ваше выражение: " + uInput);
        char[] ch = new char[10];
        for (int i = 0; i < uInput.length(); i++) {
            ch[i] = uInput.charAt(i);
            try {


                if (ch[i] == '+') {
                    op = '+';
                }
                if (ch[i] == '-') {
                    op = '-';
                }
                if (ch[i] == '*') {
                    op = '*';
                }
                if (ch[i] == '/') {
                    op = '/';
                }

            } catch (InputMismatchException e){
                throw new InputMismatchException("Cтрока не является математической операцией");
            }
        }

        /// разделить цифры до и после знака
        String charString = String.valueOf(ch);
        String[] chapters = charString.split("[+-/*]");
        String strnum1 = chapters[0];
        String strnum2 = null;
        try {
           strnum2 = chapters[1];
        } catch (ArrayIndexOutOfBoundsException e){
          throw new ArrayIndexOutOfBoundsException("Cтрока не является математической операцией");
          }
        if (chapters.length>2){
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(0);
            }
        strnum2 = strnum2.trim();


        /// Перевести в нижний регистр
        num1 = romNum(strnum1.toLowerCase());
        num2 = romNum(strnum2.toLowerCase());

        if (num1 < 0 && num2 < 0) {
            result = 0;


        } else {
            result = calc(num1, op, num2);
                if (num1 < 0 || num2 < 0){
                    System.out.println("используются одновременно разные системы счисления");
                    System.exit(0);
                }
            String resultRom = numRom(result);
            System.out.println("Результат для римских цифр: "+strnum1.toUpperCase()+" "+op+" "+strnum2.toUpperCase()+" = "+resultRom);
        }

        num1 = Integer.parseInt(strnum1);
        num2 = Integer.parseInt(strnum2);

        if (num1 > 10 || num2 > 10){
            System.out.println("Значение больше 10, читайте условие для ввода данных.");
            System.exit(0);
        }

        result = calc(num1, op, num2);
        System.out.println("Результат для арабских цифр: "+num1+" "+op+" "+num2+" = "+result);
    }

    public static int calc (int x1, char op1, int x2) {
        int result = 0;
        switch (op1) {
            case '+':
                result = x1 + x2;
                break;
            case '-':
                result = x1 - x2;
                break;
            case '*':
                result = x1 * x2;
                break;
            case '/':
                try {
                    result = x1 / x2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Ошибка : " + e);
                    System.out.println("На 0 делить нельзя!");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный знак операции");
        }
        return result;
    }


    private static String numRom (int numArab) {
        String[] rom = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        String s = null;
        try {
            s = rom[numArab];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("В римской системе нет отрицательных чисел");
        }
        return s;
        }

    private static int romNum (String rom) throws InputMismatchException {
        try {
            if (rom.equals("i")) {
                return 1;
            } else if (rom.equals("ii")) {
                return 2;
            } else if (rom.equals("iii")) {
                return 3;
            } else if (rom.equals("iv")) {
                return 4;
            } else if (rom.equals("v")) {
                return 5;
            } else if (rom.equals("vi")) {
                return 6;
            } else if (rom.equals("vii")) {
                return 7;
            } else if (rom.equals("viii")) {
                return 8;
            } else if (rom.equals("ix")) {
                return 9;
            } else if (rom.equals("x")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("используются одновременно разные системы счисления");
        }
        return -1;
    }
}