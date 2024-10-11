package hr.autorepair.shop.echo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EchoController {

    @GetMapping("/echo")
    public ResponseEntity<Void> echo(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/echo-authenticated")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> echoAuthenticated(){
        return ResponseEntity.ok().build();
    }

}
