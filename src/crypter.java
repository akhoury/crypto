

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class crypter {

    Cipher ecipher;
    Cipher dcipher;

    crypter(SecretKey key, String algorithm) {
        try {
            ecipher = Cipher.getInstance(algorithm);
            dcipher = Cipher.getInstance(algorithm);
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchPaddingException e) {
            System.out.println("EXCEPTION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("EXCEPTION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            System.out.println("EXCEPTION: InvalidKeyException");
        }
    }


   
    @SuppressWarnings("hiding")
	public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);

        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public String decrypt(String str) {

        try {

            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }



    public static void testUsingSecretKey() {
        try {

            System.out.println();
            System.out.println("+----------------------------------------+");
            System.out.println("|  -- Test Using Symetric Key Method--    |");
            System.out.println("+----------------------------------------+");
            System.out.println();

            String secretString = "DEAD BEEF MOTHA FUCKA ";

            SecretKey desKey       = KeyGenerator.getInstance("AES").generateKey();

            crypter desEncrypter = new crypter(desKey, desKey.getAlgorithm());
            String desEncrypted       = desEncrypter.encrypt(secretString); 
            String desDecrypted       = desEncrypter.decrypt(desEncrypted);

            // Print out values
            System.out.println(desKey.getAlgorithm() + " Encryption algorithm");
            System.out.println("    Original String  : " + secretString);
            System.out.println("    Encrypted String : " + desEncrypted);
            System.out.println("    Decrypted String : " + desDecrypted);
            System.out.println();
         ///.   System.out.println(desKey.getEncoded());
            
            
            byte[] bytes = desKey.getEncoded();
            String s = new String(bytes);
            
            
            System.out.println(s);
          //  System.out.println(bytes);
            
            
            SecretKey kkk = new SecretKeySpec(s.getBytes(), "AES");
            
            System.out.println("");
          //  System.out.println(kkk.getEncoded());
            byte[] byytes = kkk.getEncoded();
            String ss = new String(byytes);
            System.out.println(ss);
           // System.out.println(byytes);
            
            
            
            
            

        } catch (NoSuchAlgorithmException e) {
        }
    }


  
    public static void main(String[] args) {
        testUsingSecretKey();
    }

}

