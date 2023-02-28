package two.dev.gestao.http.controllers.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import two.dev.gestao.http.model.Product;
import two.dev.gestao.http.service.ProductService;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductsApiController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<Product> findAll() {
        return this.productService.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return this.productService.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) throws Exception {
        return this.productService.update(id, product);
    }

}
