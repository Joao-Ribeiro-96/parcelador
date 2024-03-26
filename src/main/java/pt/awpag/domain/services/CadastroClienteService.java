package pt.awpag.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.awpag.domain.exceptions.NegocioException;
import pt.awpag.domain.models.Cliente;
import pt.awpag.domain.repository.ClienteRepository;

@Service
@AllArgsConstructor
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar (Cliente cliente){
        if(clienteRepository.existsByEmail(cliente.getEmail())){
            throw new NegocioException("Email já cadastrado");

        }
        return clienteRepository.save(cliente);
    }

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }


}
