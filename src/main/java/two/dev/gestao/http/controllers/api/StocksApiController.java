package two.dev.gestao.http.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/stocks")
public class StocksApiController {

    @GetMapping()
    public String index() {
        return "stocks index";
    }

}
