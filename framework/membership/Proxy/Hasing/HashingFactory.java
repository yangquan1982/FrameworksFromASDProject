package framework.membership.Proxy.Hasing;

import framework.membership.Config;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class HashingFactory {
    private HashingFactory(){

    }

    public static IHashing getHashInstance(){
        String chose = Config.HASH_TYPE;
        switch (chose){
            case "MD5": {
                return new MD5();
            }
            case "Argon":{
                return new ArgonHash();
            }
            case "BCrypt":{
                return new BCryptHash();
            }
            default: return null;
        }
    }
}
