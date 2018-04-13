package framework.membership.Proxy.Hasing;

/**
 * Created by Duong Truong on 2/1/2018.
 */
public interface IHashing {
    public String getHashString(String input);

    public boolean verifyStringByHash(String input, String hashed);
}
