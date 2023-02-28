package two.dev.gestao.http.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import two.dev.gestao.http.model.Stock;
import two.dev.gestao.http.repositorys.StocksRepository;

public class StockService {
    @Autowired
    StocksRepository repository;

    public Iterable<Stock> findAll() {
        return this.repository.findAll();
    }

    public Stock create(Stock entity) {
        return this.repository.save(entity);
    }

    public Stock find(UUID id) throws Exception {
        Optional<Stock> stock = this.repository.findById(id);
        if (stock.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return stock.get();
    }

    public Stock update(UUID id, Stock entity) throws Exception {
        Stock updateStockEntity = this.find(id);
        updateStockEntity.setQuantidadeAtual(entity.getQuantidadeAtual());
        updateStockEntity
                .setQuantidadeMaxima(updateStockEntity.getQuantidadeMaxima() - updateStockEntity.getQuantidadeAtual());
        updateStockEntity
                .setQuantidadeMinima(updateStockEntity.getQuantidadeAtual() - updateStockEntity.getQuantidadeMinima());
        updateStockEntity.setStatus(updateStockEntity.atualizaStatus(updateStockEntity.getQuantidadeAtual()));
        return this.create(updateStockEntity);
    }

    public ResponseEntity<Object> deleted(UUID id) throws Exception {
        Stock deleted = this.find(id);
        this.repository.delete(deleted);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}