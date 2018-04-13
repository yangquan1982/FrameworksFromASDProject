package framework.membership.Proxy.Encryption;

import framework.membership.Config;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class CipherEncryption implements IEncryption {
    private Cipher c;
    private Key key;

    public CipherEncryption() throws Exception {
        c = Cipher.getInstance("AES");
        key = generateKey();
    }

    @Override
    public String encryptString(String input) {
        try {
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(input.getBytes());
            String encryptedValue = new BASE64Encoder().encode(encVal);
            return encryptedValue;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decryptString(String input) {
        try {
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(input);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Key generateKey() throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] result;
        byte[] salt = Config.ENCRYPTE_KEY.getBytes();
        int lenght = salt.length;
        if(lenght < 16){
            byte[] newSalt = new byte[16];
            System.arraycopy(salt,0,newSalt,0,lenght);
            result = newSalt;
        }
        else result = salt;
        Key key = new SecretKeySpec(result, "AES");
        return key;
    }

}
