package org.ultimacrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ultimacrm.dto.ClienteDTO;
import org.ultimacrm.dto.request.PedidoRequest;
import org.ultimacrm.models.*;
import org.ultimacrm.repositories.ClienteRepository;
import org.ultimacrm.repositories.EntregaRepository;
import org.ultimacrm.repositories.PedidoRepository;
import org.ultimacrm.repositories.ProdutoRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoRepository produtoRepository;
    private final EntregaRepository entregaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService, ProdutoRepository produtoRepository, EntregaRepository entregaRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.produtoRepository = produtoRepository;
        this.entregaRepository = entregaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void cadastrarPedido(PedidoRequest pedidoRequest){

        ClienteDTO clienteDTO = clienteService.getClienteByCpf(pedidoRequest.getCpf());

        //Converter ClienteDTO para Cliente
        Cliente cliente = converterClienteDTO(clienteDTO);

        // Obter produtos escolhidos
        List<Produto> produtosEscolhidos = obterProdutosEscolhidos(pedidoRequest.getIdsProdutos());

        //Calcular o valor total do pedido
        double valorTotal = calcularValorTotal(produtosEscolhidos);

        Pedido pedido = criarPedido();

        //Associar pedido ao cliente
        pedido.setCliente(cliente);
        //setar os demais atributos do pedido
        pedido.setProdutos(produtosEscolhidos);
        pedido.setValorPedido(valorTotal);
        pedido.setStatus(Pedido.StatusPedido.EM_PROCESSAMENTO);

        //Salva o pedido no banco e armazena o pedido salvo na variável p/ utilizar o ID gerado no banco
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        //Associa os produtos escolhidos ao pedido NO BANCO DE DADOS(tabela PedidoProduto N:N)
        associarProdutosAoPedido(pedidoSalvo.getId(), produtosEscolhidos);

        //CRIAR ENTREGA
        Entrega entrega = criarEntrega(pedidoSalvo);

        //Associa com a instância atual do cliente
        entregaRepository.save(entrega);
    }

    private Pedido criarPedido() {
        Pedido pedido = new Pedido();

        // Obtém a data e hora atuais
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        // Define um formato desejado para a data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Formata a data e hora
        String dataHoraFormatada = dataHoraAtual.format(formatter);

        // Converte a string formatada de volta para LocalDateTime
        LocalDateTime dataHoraFormatadaObjeto = LocalDateTime.parse(dataHoraFormatada, formatter);

        // Define a data e hora no pedido
        pedido.setDatahoraCriacao(dataHoraFormatadaObjeto);

        return pedido;
    }


    private Cliente converterClienteDTO(ClienteDTO clienteDTO) {

        //Retorna cliente com ID
        Cliente cliente =  clienteRepository.findByCpf(clienteDTO.getCpf());;

        // Criação da lista de Contatos
        List<Contato> contatos = clienteDTO.getContatos().stream()
                .map(contatoDTO -> {
                    Contato contato = new Contato();
                    contato.setTelefone(contatoDTO.getTelefone());
                    contato.setEmail(contatoDTO.getEmail());
                    contato.setCliente(cliente); // Associação inversa ao Cliente
                    return contato;
                })
                .collect(Collectors.toList());

        cliente.setContatos(contatos);

        // Criação da lista de Enderecos
        List<Endereco> enderecos = clienteDTO.getEnderecos().stream()
                .map(enderecoDTO -> {
                    Endereco endereco = new Endereco();
                    endereco.setRua(enderecoDTO.getRua());
                    endereco.setNumero(enderecoDTO.getNumero());
                    endereco.setComplemento(enderecoDTO.getComplemento());
                    endereco.setBairro(enderecoDTO.getBairro());
                    endereco.setCidade(enderecoDTO.getCidade());
                    endereco.setCep(enderecoDTO.getCep());
                    endereco.setCliente(cliente); // Associação inversa ao Cliente
                    return endereco;
                })
                .collect(Collectors.toList());

        cliente.setEnderecos(enderecos);

        return cliente;
    }

    public List<Produto> obterProdutosEscolhidos(List<Long> idsProdutos) {
        List<Produto> produtosEscolhidos = new ArrayList<>();
        for (Long idProduto : idsProdutos) {
            produtoRepository.findById(idProduto).ifPresent(produtosEscolhidos::add);
        }
        return produtosEscolhidos;
    }

    private double calcularValorTotal(List<Produto> produtos) {
        return produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
    }

    public void associarProdutosAoPedido(Long pedidoId, List<Produto> produtos) {

        for (Produto produto : produtos) {
            pedidoRepository.associarProdutosAoPedido(pedidoId, produto.getId());
        }
    }

    private Entrega criarEntrega(Pedido pedido) {
        Entrega entrega = new Entrega();
        entrega.setEntregador("Zé");
        entrega.setStatus(Entrega.StatusEntrega.PENDENTE);
        entrega.setPedido(pedido);
        return entrega;
    }

}
