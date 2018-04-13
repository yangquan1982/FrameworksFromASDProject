package framework.membership.Proxy.Encryption;

import framework.membership.Config;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class EncryptionFactory {
    private EncryptionFactory(){}

    public static IEncryption getEncrytionInstance(){
        String choice = Config.ENCRYPTE_TYPE;
        switch (choice){
            case "Jasypt": return new Jasypt();
            case "Cipher":{
                try {
                    return new CipherEncryption();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            default: return null;
        }
        return null;
    }


}
