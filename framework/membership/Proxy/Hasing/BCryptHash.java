package framework.membership.Proxy.Hasing;

import framework.membership.Config;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class BCryptHash implements IHashing {
    @Override
    public String getHashString(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt(Config.SALT_SEED));
    }

    @Override
    public boolean verifyStringByHash(String input, String hashed) {
        return BCrypt.checkpw(input,hashed);
    }
}
