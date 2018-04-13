package framework.membership.Proxy.Encryption;

import framework.membership.Config;
import org.jasypt.util.text.StrongTextEncryptor;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class Jasypt implements IEncryption{
    private StrongTextEncryptor textEncryptor;
    public Jasypt(){
        textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(Config.ENCRYPTE_KEY);
    }
    @Override
    public String encryptString(String input) {
        return textEncryptor.encrypt(input);
    }

    @Override
    public String decryptString(String input) {
        return textEncryptor.decrypt(input);
    }
}
