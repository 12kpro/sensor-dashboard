package capstone.fe_api.users.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserCreatePayload {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String name;
    @NotNull(message = "Il cognome è obbligatorio")
    String surname;
    @NotNull(message = "Lo user name è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String username;
    @Email(message = "Non hai inserito un indirizzo email valido")
    String email;
    @NotNull(message = "La password è obbligatoria")
    String password;
    UUID roleId;

    public UserCreatePayload(@NotNull(message = "Il nome è obbligatorio") String name, @NotNull(message = "Il cognome è obbligatorio") String surname, @NotNull(message = "Lo user name è obbligatorio") String username, String email, @NotNull(message = "La password è obbligatoria") String password, UUID roleId) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public UserCreatePayload(@NotNull(message = "Il nome è obbligatorio") String name, @NotNull(message = "Il cognome è obbligatorio") String surname, @NotNull(message = "Lo user name è obbligatorio") String username, String email, @NotNull(message = "La password è obbligatoria") String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
