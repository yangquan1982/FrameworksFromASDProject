package framework.membership;

import java.util.UUID;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class StandardMember extends Member {
    private String username;
    private String password;
    private StandardUserProfile profile;

    @Override
    public String generateId() {
        UUID id = UUID.randomUUID();
        return id.toString();
    }

    public StandardUserProfile getProfile() {
        return profile;
    }

    public void setProfile(StandardUserProfile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveProfileToData(StandardUserProfile profile){
        saveOneData("id",this.getId());
        saveOneData("username",this.getUsername());
        saveOneData("password",this.getPassword());
        saveOneData("firstName",profile.getFirstName());
        saveOneData("lastName",profile.getLastName());
        saveOneData("fullName",profile.getFirstName() + " " + profile.getLastName());
        saveOneData("address",profile.getAddress());
        saveOneData("email",profile.getEmail());
        saveOneData("bankCardNo",profile.getBankCardNo());
        saveOneData("phone",profile.getPhoneNumber());
        saveOneData("username",profile.getUsername());
    }
}
