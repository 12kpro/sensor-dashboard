package capstone.fe_api.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))

    @JsonIgnore
    private Set<User> users = new LinkedHashSet<>();

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ruolo{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                '}';
    }
}
