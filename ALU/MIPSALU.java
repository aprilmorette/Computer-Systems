import java.util.Scanner;

public class MIPSALU {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from the command line
        String opcode = scanner.next();
        int operand1 = scanner.nextInt();
        int operand2 = scanner.nextInt();

        // Simulate the ALU operation
        int result = 0;
        boolean overflow = false;
        boolean zero = false;

        switch (opcode) {
            case "ADD":
                result = add(operand1, operand2);
                overflow = checkOverflow(operand1, operand2, result);
                zero = checkZero(result);
                break;
            case "SUB":
                result = sub(operand1, operand2);
                overflow = checkOverflow(operand1, operand2, result);
                zero = checkZero(result);
                break;
            case "AND":
                result = and(operand1, operand2);
                overflow = false;
                zero = checkZero(result);
                break;
            case "OR":
                result = or(operand1, operand2);
                overflow = false;
                zero = checkZero(result);
                break;
            case "SLT":
                result = slt(operand1, operand2);
                overflow = false;
                zero = result == 0;
                break;
            default:
                System.out.println("Invalid opcode");
                System.exit(1);
        }

        // Display the output
        System.out.println("Opcode: " + opcode);
        System.out.println("Result: " + result);
        System.out.println("Overflow: " + overflow);
        System.out.println("Zero: " + zero);
        sc.close();
    }

    private static int add(int operand1, int operand2) {
        int result = operand1 + operand2;
        return result;
    }

    private static int sub(int operand1, int operand2) {
        int result = operand1 - operand2;
        return result;
    }

    private static int and(int operand1, int operand2) {
        int result = operand1 & operand2;
        return result;
    }

    private static int or(int operand1, int operand2) {
        int result = operand1 | operand2;
        return result;
    }

    private static int slt(int operand1, int operand2) {
        int result = operand1 < operand2 ? 1 : 0;
        return result;
    }

    private static boolean checkOverflow(int operand1, int operand2, int result) {
        boolean overflow = (operand1 ^ operand2 ^ result) < 0;
        return overflow;
    }

    private static boolean checkZero(int result) {
        return result == 0;
    }
}