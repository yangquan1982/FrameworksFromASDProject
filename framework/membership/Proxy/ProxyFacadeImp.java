package framework.membership.Proxy;

import framework.membership.Proxy.Encryption.EncryptionFactory;
import framework.membership.Proxy.Encryption.IEncryption;
import framework.membership.Proxy.Hasing.HashingFactory;
import framework.membership.Proxy.Hasing.IHashing;
import framework.membership.Proxy.Validate.IValidate;
import framework.membership.Proxy.Validate.StringType;
import framework.membership.Proxy.Validate.ValidateFactory;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class ProxyFacadeImp implements IProxyFacade {
    private IEncryption encryption;
    private IHashing hashing;
    private IValidate validate;

    private ProxyFacadeImp(){
        encryption = EncryptionFactory.getEncrytionInstance();
        hashing = HashingFactory.getHashInstance();
        validate = ValidateFactory.getValidateInstance();
    }

    public static IProxyFacade getInstance(){
        return new ProxyFacadeImp();
    }
    @Override
    public String getHashString(String input) {
        return hashing.getHashString(input);
    }

    @Override
    public boolean verifyStringByHash(String input, String hashed) {
        return hashing.verifyStringByHash(input,hashed);
    }

    @Override
    public String encryptString(String input) {
        return encryption.encryptString(input);
    }

    @Override
    public String decryptString(String input) {
        return encryption.decryptString(input);
    }

    @Override
    public boolean validateString(StringType stringType, String input) {
        return validate.validateString(stringType,input);
    }
}
