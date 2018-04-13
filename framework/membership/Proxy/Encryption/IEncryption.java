package framework.membership.Proxy.Encryption;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public interface IEncryption {
    public String encryptString(String input);

    public String decryptString(String input);
}
