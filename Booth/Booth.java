/* Computer Systems, April Duff */
import java.util.*;
import java.lang.StringBuffer;

class Booth {
    static void printMenu() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                           4-bit Booth Algorithm                        ");
        System.out.println("------------------------------------------------------------------------");
    }
    static String pad(String num) {
        switch(num.length()) {
            case 4:
                break;
            case 3:
                num = addChar(num, '0', 0);
                break;
            case 2:
                num = addChar(num, '0', 0);
                num = addChar(num, '0', 0);
                break;
            case 1:
                num = addChar(num, '0', 0);
                num = addChar(num, '0', 0);
                num = addChar(num, '0', 0);
                break;
        }
        return num;
    }
    static String add(String acc, String m) {
        int accumulator = Integer.parseInt(acc, 2);
        int multiplicand = Integer.parseInt(m, 2);
        int sum = accumulator + multiplicand;
        String result = pad(Integer.toBinaryString(sum));
        String output = result;
        if (result.length() > 4) {
            StringBuilder builder = new StringBuilder(result);
            for (int i = result.length(); i > 4; i--) {
                builder.deleteCharAt(0);
                output = builder.toString();
            }
        }
        return output;
    }
    static String twosComplement(String num) {
        StringBuilder complement = new StringBuilder(num.length());
        boolean one = false;
        for (int i = num.length() - 1; i >= 0; i--) {
            char bit = num.charAt(i);
            if (bit == '1' && !one) {
                one = true;
                complement.insert(0, bit);
            } else if (one) {
                complement.insert(0, bit == '0' ? '1' : '0');
            } else {
                complement.insert(0, bit);
            }
        }
        return complement.toString();
    }
    static String addChar(String s, char c, int position) {
        StringBuffer buffer = new StringBuffer(s);
        buffer.insert(position, c);
        return buffer.toString();
    }
    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          try {
            printMenu();
            System.out.print("Enter multiplicand: ");
            String multiplicand = sc.next();
            System.out.print("Enter multiplier: ");
            String originalMultiplier = sc.next();
            String multiplier = originalMultiplier;
            int n = 0;      // 4-bit (iterations)
            char lsb = '0';     // least significant bit
            String accumulator = "0000";
            System.out.print("Iteration " + n + " -----------> ");
            if (multiplicand.charAt(0) == '1') {
                if (multiplier.charAt(0) == '1') {
                    System.out.print("Multiplicand: " + multiplicand + " (-" + Integer.parseInt(multiplicand, 2));
                    System.out.print(") | Multiplier: " + originalMultiplier + " (-" + Integer.parseInt(originalMultiplier, 2));
                    System.out.println(") | Product: " + accumulator + " " + multiplier);
                } else if (multiplier.charAt(0) == '0') {
                    System.out.print("Multiplicand: " + multiplicand + " (-" + Integer.parseInt(multiplicand, 2));
                    System.out.print(") | Multiplier: " + originalMultiplier + " (" + Integer.parseInt(originalMultiplier, 2));
                    System.out.println(") | Product: " + accumulator + " " + multiplier);
                }
            } else if (multiplicand.charAt(0) == '0') {
                if (multiplier.charAt(0) == '1') {
                    System.out.print("Multiplicand: " + multiplicand + " (" + Integer.parseInt(multiplicand, 2));
                    System.out.print(") | Multiplier: " + originalMultiplier + " (-" + Integer.parseInt(originalMultiplier, 2));
                    System.out.println(" | Product: " + accumulator + " " + multiplier);
                } else if (multiplier.charAt(0) == '0') {
                    System.out.print("Multiplicand: " + multiplicand + " (" + Integer.parseInt(multiplicand, 2));
                    System.out.print(") | Multiplier: " + originalMultiplier + " (" + Integer.parseInt(originalMultiplier, 2));
                    System.out.println(" | Product: " + accumulator + " " + multiplier);
                }
            }
            

            while (n <= 3) {  
                if (multiplier.charAt(3) == '0' && lsb == '1') {
                    accumulator = add(accumulator, multiplicand);
                } else if (multiplier.charAt(3) == '1' && lsb == '0') {
                    accumulator = add(accumulator, twosComplement(multiplicand));
                }
                // arithmetic shift right
                lsb = multiplier.charAt(3);
                multiplier = pad(addChar(multiplier.substring(0, 3), accumulator.charAt(3), 0));
                accumulator = pad(addChar(accumulator.substring(0, 3), accumulator.charAt(0), 0));
                n++;
                System.out.print("Iteration " + n + " -----------> ");
                System.out.println("Multiplicand: " + multiplicand + " | Multiplier: " + originalMultiplier + " | Product: " + accumulator + " " + multiplier);
            }
            
            String product = accumulator + multiplier;
            boolean negative = false;
            if (accumulator.charAt(0) == '1') {     // if it is a negative number
                product = twosComplement(product);
                negative = true;
            }
            System.out.print("Product: " + accumulator + " " + multiplier);
            if (!negative) {
                System.out.println(" (" + Integer.parseInt(product, 2) + ")");
            } else {
                System.out.println(" (-" + Integer.parseInt(product, 2) + ")");
            }
            sc.close();

          } catch (Exception e) {
            e.printStackTrace();
          }
    }
}