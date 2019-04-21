package itemexchange.domain;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
    @OneToMany(/*fetch = FetchType.EAGER, */cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Item> items = new HashSet<>();
    
    
}
