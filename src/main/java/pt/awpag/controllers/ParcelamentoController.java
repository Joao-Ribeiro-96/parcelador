package pt.awpag.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.awpag.controllers.representationmodel.ParcelamentoModel;
import pt.awpag.domain.exceptions.NegocioException;
import pt.awpag.domain.models.Parcelamento;
import pt.awpag.domain.repository.ParcelamentoRepository;
import pt.awpag.domain.services.ParcelamentoService;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoService parcelamentoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Parcelamento> listar (){
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId){
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamento -> ResponseEntity.ok(modelMapper.map(parcelamento, ParcelamentoModel.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento){
        return parcelamentoService.cadastrar(parcelamento);
    }

    @PutMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> atualizar(@Valid @PathVariable Long parcelamentoId, @RequestBody Parcelamento parcelamento){
        if (!parcelamentoRepository.existsById(parcelamentoId)){
            return ResponseEntity.notFound().build();
        }
        parcelamento.setId(parcelamentoId);
        parcelamento = parcelamentoRepository.save(parcelamento);
        return ResponseEntity.ok(parcelamento);
    }

    @ExceptionHandler(NegocioException.class)
    public ProblemDetail handleNegocio(NegocioException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ex.getMessage());
        problemDetail.setType(URI.create("https://vaite.fude/regradenegocio"));

        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(NegocioException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso em uso.");
        problemDetail.setType(URI.create("https://vaite.fude/regradenegocio"));

        return problemDetail;
    }


}
