package two.dev.gestao.http.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import two.dev.gestao.http.model.Product;
import two.dev.gestao.http.repositorys.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Product findOne(UUID id) throws Exception {
        Optional<Product> productFind = this.productRepository.findById(id);
        if (productFind.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return productFind.get();
    }

    public Product update(UUID id, Product product) throws Exception {
        Product updated = this.findOne(id);
        updated.setDescription(product.getDescription());
        updated.setPrice_buy(product.getPrice_buy());
        updated.setPrice_sell(product.getPrice_sell());
        return this.create(product);
    }
}
