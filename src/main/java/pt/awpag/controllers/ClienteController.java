package pt.awpag.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.awpag.domain.exceptions.NegocioException;
import pt.awpag.domain.models.Cliente;
import pt.awpag.domain.repository.ClienteRepository;
import pt.awpag.domain.services.CadastroClienteService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/clientes")
@AllArgsConstructor
@RestController
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final CadastroClienteService cadastroClienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
//                    entityManager
//                    .createQuery("from Cliente", Cliente.class)
//                    .getResultList();
    }

//    @GetMapping("/clientes")
//    public List<Cliente> listarPorNome(@RequestParam String nome) {
//        return clienteRepository.findAllByNome(nome);
//    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente adicionar(@RequestBody @Valid Cliente cliente) {
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = cadastroClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(clienteRepository.existsById(id)){
            cadastroClienteService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

}