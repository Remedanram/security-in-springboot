package security_with_jwt.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="8a2f118e83c9302a4ffdc6e4d91d0c48856d657c7c3b674cdde831b1f7d2dff1";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T>claimsResolver){

        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
   public String generateToken(
           Map<String,Object> extraClaims,
           UserDetails userDetails
   ){
   return
           Jwts
           .builder()
           .setClaims(extraClaims)
           .setSubject(userDetails.getUsername())
           .setIssuedAt(new Date(System.currentTimeMillis()))
           .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*24))
           .signWith(getSignInKey(), SignatureAlgorithm.HS256)
           .compact();

   }
//   public boolean isTokenValid(String token,UserDetails userDetails){
//        final String username=extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && isTokenExpired(token);
//   }
public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
}

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyByte= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);

    }
}
