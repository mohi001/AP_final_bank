package Server;

import java.io.Serializable;

public class User implements Serializable {
    private String identityNumber , email , password , phone;

    public User(String identityNumber, String email, String password, String phone) {
        this.identityNumber = identityNumber;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
