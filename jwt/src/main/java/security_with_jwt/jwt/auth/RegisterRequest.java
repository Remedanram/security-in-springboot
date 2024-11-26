package security_with_jwt.jwt.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
