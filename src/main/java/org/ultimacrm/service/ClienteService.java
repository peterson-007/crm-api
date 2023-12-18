package org.ultimacrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ultimacrm.dto.ClienteDTO;
import org.ultimacrm.models.Cliente;
import org.ultimacrm.models.Contato;
import org.ultimacrm.models.Endereco;
import org.ultimacrm.repositories.ClienteRepository;
import org.ultimacrm.repositories.ContatoRepository;
import org.ultimacrm.repositories.EnderecoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void salvarCliente(ClienteDTO clienteDTO) {

        // Validação personalizada usando CpfValidator
        if (cpfExistsInDatabase(clienteDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDataDeNascimento(clienteDTO.getDataDeNascimento());
        cliente.setIdadeAtual(clienteDTO.getIdadeAtual());
        cliente.setGenero(clienteDTO.getGenero());

        clienteRepository.save(cliente);
    }

    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return new ClienteDTO(cliente);
    }

    public ClienteDTO getClienteByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);

        return new ClienteDTO(cliente);
    }

    private boolean cpfExistsInDatabase(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    private Cliente converterClienteDTO(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setIdadeAtual(clienteDTO.getIdadeAtual());
        cliente.setGenero(clienteDTO.getGenero());

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

    public void salvarClienteComContatoEEndereco(ClienteDTO clienteDTO) {
        Cliente cliente = converterClienteDTO(clienteDTO);

        // Salvando o Cliente
        clienteRepository.save(cliente);

        // Salvando os Contatos associados ao Cliente
        for (Contato contato : cliente.getContatos()) {
            contatoRepository.save(contato);
        }

        // Salvando os Enderecos associados ao Cliente
        for (Endereco endereco : cliente.getEnderecos()) {
            enderecoRepository.save(endereco);
        }
    }

}