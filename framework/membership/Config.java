package framework.membership;

/**
 * Created by Duong Truong on 1/31/2018.
 */
public class Config {
    // Hashing configuration
    public static final String HASH_TYPE = "MD5"; //MD5,Argon,BCrypt
    public static final int SALT_SEED = 10;

    //Encryption configuration
    public static final String ENCRYPTE_TYPE = "Cipher";//Jasypt,Cipher
    public static final String ENCRYPTE_KEY = "myPassword";

    //Validation configuration
    public static final int PASSWORD_STRENGTH = 0; //0-10
}
