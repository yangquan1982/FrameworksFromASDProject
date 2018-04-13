package framework.membership.Template;

import framework.membership.Proxy.IProxyFacade;
import framework.membership.Proxy.ProxyFacadeImp;
import framework.membership.StandardUserProfile;

import static framework.membership.Proxy.Validate.StringType.*;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public abstract class AbstractUpdateProfile {
    IProxyFacade proxy = ProxyFacadeImp.getInstance();

    public final boolean updateProfile(StandardUserProfile profile){
        StandardUserProfile profileFromDatabase = loadProfileFromDatabase();
        if(profileFromDatabase != null){
            profileFromDatabase = decryptProfile(profileFromDatabase);
            showProfile(profileFromDatabase);
        }

        StandardUserProfile profileFromInput = profile;
        boolean validate = validateProfile(profileFromInput);
        if(!validate) {
            handleInvalidProfile();
            return false;
        }

        StandardUserProfile profileEncrypted = encryptProfile(profileFromInput);

        saveProfileToDatabase(profileEncrypted);

        //for demo
        StandardUserProfile debugProfile = decryptProfile(profileEncrypted);
        debugShowProfileAfterDecrypte(debugProfile);

        return true;
    }

    protected abstract void debugShowProfileAfterDecrypte(StandardUserProfile debugProfile);

    private StandardUserProfile decryptProfile(StandardUserProfile profileFromDatabase) {
        String decrypted = proxy.decryptString(profileFromDatabase.getBankCardNo());
        profileFromDatabase.setBankCardNo(decrypted);
        return profileFromDatabase;
    }

    protected abstract boolean saveProfileToDatabase(StandardUserProfile profileEncrypted);

    private StandardUserProfile encryptProfile(StandardUserProfile profileFromInput) {
        String encrypted = proxy.encryptString(profileFromInput.getBankCardNo());

        profileFromInput.setBankCardNo(encrypted);
        return profileFromInput;
    }

    protected abstract void handleInvalidProfile();

    private boolean validateProfile(StandardUserProfile profileFromInput) {
        String email = profileFromInput.getEmail();
        if(profileFromInput.getUsername() == null) return false;

        if(email != null){
            if(!proxy.validateString(Email,email)) return false;
        }

        String phone = profileFromInput.getPhoneNumber();
        if(phone != null){
            if(!proxy.validateString(Phone,phone)) return false;
        }

        String bankCardNo = profileFromInput.getBankCardNo();
        if(bankCardNo != null){
            if(!proxy.validateString(Digit,bankCardNo)) return false;
        }


        return true;
    }



    protected abstract void showProfile(StandardUserProfile profileFromDatabase);

    protected abstract StandardUserProfile loadProfileFromDatabase();
}
