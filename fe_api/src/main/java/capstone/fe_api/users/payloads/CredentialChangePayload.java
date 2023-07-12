package capstone.fe_api.users.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CredentialChangePayload {
    @NotNull(message = "Lo user name è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String username;
    @NotNull(message = "La password è obbligatoria")
    String password;

    public CredentialChangePayload(@NotNull(message = "Lo user name è obbligatorio") String username, @NotNull(message = "La password è obbligatoria") String password) {
        this.username = username;
        this.password = password;
    }
}
