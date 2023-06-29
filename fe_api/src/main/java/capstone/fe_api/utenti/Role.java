package capstone.fe_api.utenti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="ruoli")
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;

    @ManyToMany
    @JoinTable(name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "ruolo_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id"))

    @JsonIgnore
    private Set<User> utenti = new LinkedHashSet<>();

    public Role(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ruolo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
