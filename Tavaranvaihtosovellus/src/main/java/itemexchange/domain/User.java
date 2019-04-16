package itemexchange.domain;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractPersistable<Long> {
    private String name;
    private String username;
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Item> items = new ArrayList<>();
    
    
}
