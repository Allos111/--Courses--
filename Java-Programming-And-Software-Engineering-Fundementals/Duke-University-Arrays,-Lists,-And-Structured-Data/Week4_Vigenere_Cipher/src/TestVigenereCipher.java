import edu.duke.*;

public class TestVigenereCipher
{
    public static void main(String[] args)
    {
        FileResource myFile = new FileResource();
        String message = myFile.asString();
        int[] thisKey = {17, 14, 12, 4};

        VigenereCipher thisVigenere = new VigenereCipher(thisKey);
        String encrypted = thisVigenere.encrypt(message);
        System.out.println("Encrypted Message");
        System.out.println("-----------------");
        System.out.println(encrypted);
        System.out.println("\n");
        System.out.println("Decrypted Message");
        System.out.println("-----------------");
        String decrypted = thisVigenere.decrypt(encrypted);
        System.out.println(decrypted);

    }
}
