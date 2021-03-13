import edu.duke.*;

public class TestCaesarCracker
{
    public static void main(String[] args)
    {
        FileResource myFile = new FileResource();
        String message = myFile.asString();
        CaesarCracker thisCracker = new CaesarCracker('a');

        System.out.println("getKey()");
        System.out.println("--------");
        int thisKey = thisCracker.getKey(message);
        System.out.println("The key is " + thisKey);
        System.out.println("\n");

        System.out.println("Caesar Cracker");
        System.out.println("---------------");
        String decrypt = thisCracker.decrypt(message);
        System.out.println("Decrypted Message");
        System.out.println("-----------------");
        System.out.println(decrypt);
        System.out.println("\n");

        System.out.println("");
    }

}
