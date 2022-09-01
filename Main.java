import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Введите выражение в арабском (1+1) или римском формате (X+V) от 1(I) до 10(X):");
        String userInput = scanner.nextLine();
        System.out.println("Ваше выражение: " + userInput.toUpperCase());
        Expression exp = new Expression();
        exp.x = userInput;
        exp.calc();
        if (exp.x.contains("i")||exp.x.contains("x")||exp.x.contains("v")){
            System.out.println("Результат для римских цифр: "+exp.x.toUpperCase()+" = "+exp.calc().toUpperCase());
        } else{
            System.out.println("Результат для арабских цифр: "+exp.x+" = "+exp.calc());
        }
    }
}
class Expression {
    String x;
    int num1, num2;
    char op;
    public String calc() {

        char[] ch = new char[10];
        for (int i = 0; i < x.length(); i++) {
            ch[i] = x.charAt(i);
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
            } catch (InputMismatchException e) {
                throw new InputMismatchException("Cтрока не является математической операцией");
            }
        }
        String charString = String.valueOf(ch);
        String[] chapters = charString.split("[+-/*]");
        String strnum1 = chapters[0];
        String strnum2 = null;
        try {
            strnum2 = chapters[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Cтрока не является математической операцией");
        }
        if (chapters.length > 2) {
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(0);
        }
        strnum1 = strnum1.trim();
        strnum2 = strnum2.trim();
        strnum1 = strnum1.toLowerCase();
        strnum2 = strnum2.toLowerCase();
        boolean checkStr1 = false;
        boolean checkStr2 = false;

        if (strnum1.contains("i")||strnum1.contains("x")||strnum1.contains("v")){
            checkStr1 = true;
        }
        if (strnum2.contains("i")||strnum2.contains("x")||strnum2.contains("v")){
            checkStr2 = true;
        }
        try {
            if ((checkStr1 && checkStr2) == true){
                // if (strnum2.contains("i")||strnum2.contains("x")||strnum2.contains("v")){
                num1 = romNum(strnum1);
                num2 = romNum(strnum2);
                //System.out.println("Результат для римских цифр: "+strnum1.toUpperCase()+ op + strnum2.toUpperCase()+" = "+resultRom);
            } else {
                num1 = Integer.parseInt(strnum1);
                num2 = Integer.parseInt(strnum2);
            }
        } catch (NumberFormatException e){
            throw new NumberFormatException("Используются одновременно разные системы счисления");
        }
        if (num1 > 10 || num2 > 10){
            System.out.println("Значение больше 10, читайте условие для ввода данных.");
            System.exit(0);
        }
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Ошибка : " + e);
                    System.out.println("На 0 делить нельзя!");
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный знак операции");
        }
        if ((checkStr1&&checkStr2)==true){
            String resultRom = numRom(result);
            return resultRom;
        } else {
            String resultx = Integer.toString(result);
            return resultx;
        }
    }
    static String numRom(int numArab) {
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
    static int romNum (String rom) throws InputMismatchException {
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
        return 0;
    }
}