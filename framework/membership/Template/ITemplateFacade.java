package framework.membership.Template;

import framework.membership.StandardUserProfile;

/**
 * Created by Duong Truong on 2/3/2018.
 */
public interface ITemplateFacade {
    boolean SignUp(String username, String password);
    boolean login(String username, String password);
    boolean updateProfile(StandardUserProfile profile);
}
