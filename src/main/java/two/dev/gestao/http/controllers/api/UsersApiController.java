package two.dev.gestao.http.controllers.api;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/api/v1/users")
public class UsersApiController {

    @GetMapping
    public String index() {
        return "Hello word";
    }
}
