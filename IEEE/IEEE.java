import java.util.*;

class IEEE {
    /* converts num to single-precision binary */
    public static String convertToBinary(int num) {
        String binary = String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0');
        return binary.charAt(0) + " " + binary.substring(1, 9) + " " + binary.substring(9);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // retrieve user inputs
            System.out.print("Enter operand1 (decimal): ");
            String operand1 = sc.next();
            System.out.print("\nEnter operand2 (decimal): ");
            String operand2 = sc.next();
            System.out.print("\nEnter operator (add or mult): ");
            String operator = sc.next();
            // convert string operand inputs to float then to single-precision binary
            float op1 = Float.parseFloat(operand1);
            float op2 = Float.parseFloat(operand2);
            String binOp1 = convertToBinary(Float.floatToIntBits(op1));
            String binOp2 = convertToBinary(Float.floatToIntBits(op2));
            System.out.println("operand1: " + binOp1);
            System.out.println("operand2: " + binOp2);
            // execute addition or multiplication
            if (operator.equals("add")) {
                float sum = op1 + op2;
                String bSum = convertToBinary(Float.floatToIntBits(sum));
                System.out.println("sum: " + bSum);
            } else if (operator.equals("mult")) {
                float product = op1 * op2;
                String bProduct = convertToBinary(Float.floatToIntBits(product));
                System.out.println("product: " + bProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}