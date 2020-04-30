
import java.util.Objects;

//asdasd
public abstract class Account {
    private String username;
    private String password;
    private AccountStatus status;
    private Person person;

    public Account(String username, String password,Person person) {
        this.username = username;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
        this.person = person;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    
    
    
}
