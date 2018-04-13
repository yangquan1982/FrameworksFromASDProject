package framework.membership.Proxy.Hasing;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class MD5 implements IHashing {
    @Override
    public String getHashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch(NoSuchAlgorithmException ex) {
        }

        return "";
    }

    @Override
    public boolean verifyStringByHash(String input, String hashed) {
        return getHashString(input).equals(hashed);
    }
}
