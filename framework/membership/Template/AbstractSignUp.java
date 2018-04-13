package framework.membership.Template;

import framework.membership.Proxy.*;
import framework.membership.Proxy.Validate.StringType;


/**
 * Created by Duong Truong on 2/2/2018.
 */
public abstract class AbstractSignUp {
    IProxyFacade proxy = ProxyFacadeImp.getInstance();
    public final boolean SignUp(String username, String password){
        boolean isUserExist = checkUserExist(username);
        boolean isValidatePassword = validatePassword(password);
        if(isUserExist) System.out.println("User exist!!");
        if(!isValidatePassword) System.out.println("Password is not strong enough");
        if(isUserExist || !isValidatePassword) return false;
        else{
            String hashPassword = hashPassword(password);
            saveUser(username,hashPassword);
            return true;
        }
    }

    protected abstract void saveUser(String username, String hashPassword);

    private String hashPassword(String password) {
        return proxy.getHashString(password);
    }


    private boolean validatePassword(String password) {
       return proxy.validateString(StringType.Password,password);
    }
    protected abstract boolean checkUserExist(String username);
}
