package itemexchange.domain;

public class User {
    private String name;
    private String username;
    
    
    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }
    
    @Override
    public String toString() {
        return username;
    }
    
}
