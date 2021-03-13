import edu.duke.*;

public class TestCaesarCipher
{
    public static void main(String[] args)
    {
        FileResource myFile = new FileResource();
        String encryptedMessage = myFile.asString();

        CaesarCipher theCipher = new CaesarCipher(17);
        String encrypt = theCipher.encrypt(encryptedMessage);
        System.out.println("Encrypted Message");
        System.out.println("-----------------");
        System.out.println(encrypt);
        System.out.println("\n");

        System.out.println("Decrypted Message");
        System.out.println("-----------------");
        String decrypt = theCipher.decrypt(encrypt);
        System.out.println(decrypt);
        System.out.println("\n");

        System.out.println("Encrypt One Character");
        System.out.println("----------------------");
        char toEncrypt = 'B';
        char encryptLetter = theCipher.encryptLetter(toEncrypt);
        System.out.println("Encrypted character: " + encryptLetter);
        char decryptLetter = theCipher.decryptLetter(encryptLetter);
        System.out.println("Decrypted Letter: " + decryptLetter);
    }
}
