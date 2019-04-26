package itemexchange.domain;

public class Item {
    private Integer id;
    private String name;
    private User owner;
    
    public Item() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    //    @Override
    //    public String toString() {
    //        return "'" + name + "'" + " listed by: " + owner.getName();
    //    }
    
}
