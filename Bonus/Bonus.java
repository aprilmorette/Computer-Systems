import java.io.*;

public class Bonus {
    private static char encrypt(int key, BufferedReader in, int charValue) {
        char outputChar;
        if (charValue < 32 || charValue > 126) {
            outputChar = (char)charValue;
        }
        charValue += key;
        if (charValue > 126) {
            charValue -= 95;
        }
        if (charValue < 32) {
            charValue += 95;
        }
        outputChar = (char)charValue;
        return outputChar;
    }
    public static void main(String[] args) {
        try {
            File input = new File(args[0]);
            BufferedReader in = new BufferedReader(new FileReader(input));

            if (args.length > 2) { // if given an outfile
                File outFile = new File(args[2]); 
                PrintWriter out = new PrintWriter(outFile);
                while ((charValue = in.read()) != -1) {
                    output += encrypt(key, in, charValue);
                }
                out.write(output);
                in.close();
                out.close();
            } else { // not given an outfile, so prints on the screen
                while ((charValue = in.read()) != -1) {
                    output += encrypt(key, in, charValue);
                }
                System.out.println("Encrypted text: " + output);
                in.close();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect argument");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Cannot access file(s)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
