
package itemexchange.domain;

import java.util.*;

public class Inventory {
    private Map<String, ArrayList<Item>> inventory;
    
    public Inventory() {
        this.inventory = new HashMap<>();
    }
    
    public void addItem(Item item, String user) {
        if (item == null || user.isEmpty()) {
            return;
        }
        if (!inventory.containsKey(user)) {
            inventory.put(user, new ArrayList<>());
        }
        inventory.get(user).add(item);
    }
    
    public ArrayList<Item> getUserItems(String name) {
        return inventory.getOrDefault(name, new ArrayList<>());
    }
    
    public ArrayList<String> getUsers() {
        return new ArrayList<>(inventory.keySet());
    }
    
    public Map<String, ArrayList<Item>> getInventory() {
        return inventory;
    }

    
}
