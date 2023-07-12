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
public class UserUpdatePayload {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String name;
    @NotNull(message = "Il cognome è obbligatorio")
    String surname;
    @Email(message = "Non hai inserito un indirizzo email valido")
    String email;
    UUID roleId;

    public UserUpdatePayload(@NotNull(message = "Il nome è obbligatorio") String name, @NotNull(message = "Il cognome è obbligatorio") String surname, String email, UUID roleId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roleId = roleId;
    }

    public UserUpdatePayload(@NotNull(message = "Il nome è obbligatorio") String name, @NotNull(message = "Il cognome è obbligatorio") String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
