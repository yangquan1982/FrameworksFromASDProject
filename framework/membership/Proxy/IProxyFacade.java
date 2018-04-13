package framework.membership.Proxy;

import framework.membership.Proxy.Validate.StringType;

/**
 * Created by Duong Truong on 2/1/2018.
 */
public interface IProxyFacade {
    public String getHashString(String input);

    public boolean verifyStringByHash(String input, String hashed);

    public String encryptString(String input);

    public String decryptString(String input);

    public boolean validateString(StringType stringType, String input);

}
