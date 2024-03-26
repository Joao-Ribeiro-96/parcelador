package pt.awpag.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.awpag.domain.exceptions.NegocioException;
import pt.awpag.domain.models.Cliente;
import pt.awpag.domain.models.Parcelamento;
import pt.awpag.domain.repository.ClienteRepository;
import pt.awpag.domain.repository.ParcelamentoRepository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final CadastroClienteService cadastroClienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento) {

       if(novoParcelamento.getId() != null)
       {
           throw new NegocioException("O parcelamento não pode possuir um código. ");
       }

       Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());

       novoParcelamento.setCliente(cliente);

        novoParcelamento.setDataCriacao(OffsetDateTime.now());
        return parcelamentoRepository.save(novoParcelamento);
    }
}
