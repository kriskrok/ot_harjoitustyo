package itemexchange.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item extends AbstractPersistable<Long> {
    private String name;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    private User owner;
    
    
    @Override
    public String toString() {
        return "'" + name + "'" + " listed by: " + owner.getName();
    }
    
}
