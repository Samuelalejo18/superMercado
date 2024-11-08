package superMercado.api.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class DemoController {

    /// rutas protegidas
    @PostMapping("/sc")
    public String sc() {
        return "Hello World";
    }
}
