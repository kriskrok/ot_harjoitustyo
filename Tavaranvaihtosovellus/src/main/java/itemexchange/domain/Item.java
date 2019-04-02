package itemexchange.domain;

public class Item {
    public String name;
    public User owner;
    
    public Item(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
    
    @Override
    public String toString() {
        return "'" + name + "'" + " listed by: " + owner;
    }
    
}
