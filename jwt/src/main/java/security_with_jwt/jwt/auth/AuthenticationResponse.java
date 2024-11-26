package security_with_jwt.jwt.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
@Builder
public class AuthenticationResponse {
    private String token;
}
