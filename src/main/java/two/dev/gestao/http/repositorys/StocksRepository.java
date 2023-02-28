package two.dev.gestao.http.repositorys;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import two.dev.gestao.http.model.Stock;

public interface StocksRepository extends CrudRepository<Stock, UUID> {

}
