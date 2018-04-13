package framework.membership.Proxy.Validate;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class ValidateFactory {
    private ValidateFactory(){

    }

    public static IValidate getValidateInstance(){
        return new ValidateImplement();
    }
}
