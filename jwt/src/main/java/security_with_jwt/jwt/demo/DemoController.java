package security_with_jwt.jwt.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
    @GetMapping

    public ResponseEntity<String>sayHello(){

        return ResponseEntity.ok("SECURED END POINT");
    }

}
