package framework.membership;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class StandardUserProfile {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String bankCardNo;
    private String phoneNumber;

    public StandardUserProfile(String firstName, String lastName, String username, String email, String address, String bankCardNo, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.bankCardNo = bankCardNo;
        this.phoneNumber = phoneNumber;
    }

    private StandardUserProfile(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.email = builder.email;
        this.address = builder.address;
        this.bankCardNo = builder.bankCardNo;
        this.phoneNumber = builder.phoneNumber;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private String address;
        private String bankCardNo;
        private String phoneNumber;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setUsername(String userName){
            this.username = userName;
            return this;
        }

        public StandardUserProfile build(){
            return new StandardUserProfile(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "StandardUserProfile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
