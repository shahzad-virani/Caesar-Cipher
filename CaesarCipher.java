import java.util.Scanner;

//A Java Program to illustrate Caesar Cipher Technique
class CaesarCipher
{
	private static int key;
	
	public static int takeKeyInput(){
		Scanner sc = new Scanner(System.in);
		
		key = sc.nextInt();
		
		return key;
	}
    // Encrypts text using a shift od s
    public static StringBuffer encrypt(StringBuffer text, int s)
    {
        StringBuffer result= new StringBuffer();
 
        for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }
    
    public static StringBuffer decrypt(StringBuffer text, int s) {
    	
    	StringBuffer originalText = new StringBuffer();
    	
    	s=26-s;
    	
    	for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 65) % 26 + 65);
                originalText.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 97) % 26 + 97);
                originalText.append(ch);
            }
        }
    	
    	
    	return originalText;
    	
    }
 
    // Driver code
    public static void main(String[] args)
    {
        StringBuffer text = new StringBuffer("HelloWorld");
        int key = takeKeyInput();
        System.out.println("Text  : " + text);
        System.out.println("Shift : " + key);
        StringBuffer cipher = encrypt(text,key);
        System.out.println("Cipher: " + cipher);
        System.out.println("Original Text: " + decrypt(cipher, key));
    }
}
