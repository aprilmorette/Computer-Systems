/* Computer Systems ALU, April Duff */
import java.util.*;

public class ALU {
    static void printMenu() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                           4-bit MIPS ALU                               ");
        System.out.println("       Operations: AND, OR, ADD, SUB, SLT, Overflow/Zero Detection      ");
        System.out.println("         Please enter in this format: Opcode operand1 operand2          ");
        System.out.println("                       Enter EXIT to exit program                       ");
        System.out.println("------------------------------------------------------------------------");
    }
    static int and(int x, int y) {
        return x & y;
    }
    static int or (int x, int y) {
        return x | y;
    }
    static int add(int x, int y) {
        return x + y;
    }
    static int sub(int x, int y) {
        return x - y;
    }
    static int slt(int x, int y) {
        return (x < y) ? 0 : 1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            printMenu();
            String opcode = sc.next();
            while (!(opcode.equals("EXIT"))) {
                int operand1 = Integer.parseInt(sc.next(), 2);
                int operand2 = Integer.parseInt(sc.next(), 2);
                int result = 0; int overflow = 0; int zero = 0;
                switch (opcode) {
                    case "AND":
                        result = and(operand1, operand2);
                        break;
                    case "OR":
                        result = or(operand1, operand2);
                        break;
                    case "ADD":
                        result = add(operand1, operand2);
                        if ((result) > 15) {     // overflow
                            result -= 16;
                            overflow = 1;
                        }
                        break;
                    case "SUB":
                        result = sub(operand1, operand2);
                        break;
                    case "SLT":
                        result = slt(operand1, operand2);
                        break;
                }
                zero = (result == 0) ? 1 : 0;
                /* converts result (decimal) to binary string */
                String binary = Integer.toBinaryString(result);
                while (binary.length() < 4) {
                    binary = "0" + binary;
                }
                if (opcode.equals("SLT")) {
                    System.out.println(opcode + ": " + binary);
                } else {
                    System.out.println(opcode + ": " + binary + ", " + overflow + ", " + zero);
                }
                printMenu();
                opcode = sc.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
