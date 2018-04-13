package framework.membership.Proxy.Hasing;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import framework.membership.Config;

/**
 * Created by Duong Truong on 2/1/2018.
 */
public class ArgonHash implements IHashing {
    private Argon2 argon2 = Argon2Factory.create(Config.SALT_SEED,15);
    @Override
    public String getHashString(String input) {
        return argon2.hash(2, 65536, 1,input);
    }

    @Override
    public boolean verifyStringByHash(String input, String hashed) {
        return argon2.verify(input,hashed);
    }
}
