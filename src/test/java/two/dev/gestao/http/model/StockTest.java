package two.dev.gestao.http.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockTest {
    private Stock stock;

    @BeforeEach
    public void setup() {
        stock = new Stock();
        stock.setId(UUID.randomUUID());
        stock.setQuantidadeAtual(5);
        stock.setQuantidadeMaxima(10);
        stock.setQuantidadeMinima(2);
        stock.setStatus(stock.atualizaStatus(stock.getQuantidadeAtual()));
    }

    @Test
    public void testQuantMaximaExcedida() {
        assertTrue(stock.quantMaximaExcedida(15));
    }

    @Test
    public void testQuantidadeMaximaNaoFoiExcedida() {
        assertFalse(stock.quantMaximaExcedida(8));
    }

    @Test
    public void testQuantMinimaExcedida() {
        assertTrue(stock.quantMinimaExcedida(1));
    }

    @Test
    public void testQuantidadeMinimaNaoFoiExcedida() {
        assertFalse(stock.quantMinimaExcedida(4));
    }

    @Test
    public void testMensagemParaStatusQuantidadeMaximaExcedida() {
        assertEquals("Quantidade maxima excedida", stock.atualizaStatus(15));
    }

    @Test
    public void testMensagemParaStatusAbaixoDaQuantidadeMinima() {
        assertEquals("Abaixo da quantidade Minima", stock.atualizaStatus(1));
    }

    @Test
    public void testMensagemParaStatusTudoOk() {
        assertEquals("Em estoque", stock.atualizaStatus(5));
    }

    @Test
    public void testMensagemParaStatusEstoqueVazio() {
        stock.setQuantidadeMinima(0);
        assertEquals("Estoque vazio", stock.atualizaStatus(0));
    }
}