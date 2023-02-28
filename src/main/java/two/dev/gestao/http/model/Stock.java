package two.dev.gestao.http.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Integer quantidadeMaxima;

    @Column
    private Integer quantidadeAtual;

    @Column
    private Integer quantidadeMinima;

    @Column
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    public Boolean quantMaximaExcedida(Integer quantAtual) {
        return (quantAtual > this.getQuantidadeMaxima()) ? true : false;
    }

    public Boolean quantMinimaExcedida(Integer quantAtual) {
        return (quantAtual < this.getQuantidadeMinima() && this.getQuantidadeMinima() > 0) ? true : false;
    }

    public String atualizaStatus(Integer quantAtual) {
        if (this.quantMaximaExcedida(quantAtual)) {
            return "Quantidade maxima excedida";
        } else if (this.quantMinimaExcedida(quantAtual)) {
            return "Abaixo da quantidade Minima";
        } else if (this.getQuantidadeMinima() == 0) {
            return "Estoque vazio";
        }
        return "Em estoque";
    }
}
