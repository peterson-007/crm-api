package org.ultimacrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ultimacrm.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Modifying
    @Query(value = "INSERT INTO PedidoProduto (pedido_id, produto_id) VALUES (:pedidoId, :produtoId)", nativeQuery = true)
    void associarProdutosAoPedido(Long pedidoId, Long produtoId);
}
