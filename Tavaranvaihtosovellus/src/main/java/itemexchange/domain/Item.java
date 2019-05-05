package itemexchange.domain;

public class Item {
    private Integer id;
    private String name;
    private String description;
    private Integer ownerId;
    
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer id) {
        this.ownerId = id;
    }
    
    //    @Override
    //    public String toString() {
    //        return "'" + name + "'" + " listed by: " + owner.getName();
    //    }
    
}
