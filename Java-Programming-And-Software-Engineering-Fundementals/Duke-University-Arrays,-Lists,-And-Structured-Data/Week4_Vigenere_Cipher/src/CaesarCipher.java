public class CaesarCipher
{
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;

    public CaesarCipher(int key)
    {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }

    private char transformLetter(char myCh, String from, String to)
    {
        int currentIndex = from.indexOf(myCh);

        if (currentIndex != -1)
        {
            return to.charAt(currentIndex);
        }
        return myCh;
    }

    public char encryptLetter(char myCh)
    {
        return transformLetter(myCh, alphabet, shiftedAlphabet);
    }

    public char decryptLetter(char myCh)
    {
        return transformLetter(myCh, shiftedAlphabet, alphabet);
    }

    private String transform(String input, String from, String to)
    {
        StringBuilder encryptedMessage = new StringBuilder(input);

        for (int i = 0; i < encryptedMessage.length(); i++)
        {
            char myCh = encryptedMessage.charAt(i);
            myCh = transformLetter(myCh, from, to);
            encryptedMessage.setCharAt(i, myCh);
        }
        //The String inside the StringBuilder is my answer
        return encryptedMessage.toString();
    }

    public String encrypt(String input)
    {
        return transform(input, alphabet, shiftedAlphabet);
    }

    public String decrypt(String input)
    {
        return transform(input, shiftedAlphabet, alphabet);
    }

    public String toString()
    {
        return "" + theKey;
    }

}
