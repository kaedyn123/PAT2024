package BackEnd;

public class Users {

    private String name;
    private String username;
    private String password;

    public Users(String inName, String inUsername, String inPassword) {
        this.name = inName;
        this.username = inUsername;
        this.password = inPassword;
    }

    private void setName(String inName) {
        this.name = inName;
    }

    private void setUsername(String inUsername) {
        this.username = inUsername;
    }

    private void setPassword(String inPassword) {
        this.password = inPassword;
    }

    private String getName() {
        return this.name;
    }

    private String getUsername() {
        return this.username;
    }

    private String getPassword() {
        return this.password;
    }
}
