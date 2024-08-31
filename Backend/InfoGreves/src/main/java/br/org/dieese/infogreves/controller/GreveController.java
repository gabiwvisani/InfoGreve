package br.org.dieese.infogreves.controller;

import br.org.dieese.infogreves.domain.Greve;
import br.org.dieese.infogreves.payloads.request.FinalGreveRequest;
import br.org.dieese.infogreves.payloads.request.GreveRequest;
import br.org.dieese.infogreves.service.GreveService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;


@RestController
@Slf4j
public class GreveController {

    private final GreveService  greveService;
    @Autowired
    public GreveController(GreveService greveService){
        this.greveService = greveService;
    }

    @Operation(summary = "Cadastrar Greve")
    @PostMapping("/cadastrar/greve")
    public ResponseEntity<?> cadastrarEnvio(@Valid @RequestBody GreveRequest greveRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            String greveId = greveService.cadastrarGreve(greveRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(greveId);
        } catch (Exception e) {
            log.error("Erro ao cadastrar greve: ", e);
                return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "Buscar Greves")
    @GetMapping("/buscar/greves")
    public ResponseEntity<?> buscarTodos(){
        try {
            List<Greve> listaComTodos = greveService.buscarTodasGreves();
            return ResponseEntity.ok(listaComTodos);
        } catch (IllegalArgumentException e) {
            log.error("Erro ao buscar greve: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "Buscar Greve por id")
    @GetMapping("/buscar/greve/{id}")
    public ResponseEntity<?> buscarGrevePorId(@PathVariable String id) {
        try {
            Greve greve = greveService.buscarGrevePorId(id);
            return ResponseEntity.ok(greve);
        } catch (Exception e) {
            log.error("Erro ao buscar greve por id: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "Adicionar dados no final da greve.")
    @PatchMapping("atualizar/fim/greve/{id}")
    public ResponseEntity<?> atualizarDadosFinalGreve(
            @PathVariable String id,@Valid
            @RequestBody FinalGreveRequest request
            , BindingResult result)  {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            Greve greve = greveService.alterarDadosFinalGreve(request, id);
            return ResponseEntity.status(HttpStatus.OK).body(greve);
        }catch(Exception e) {
            log.error("Erro ao registrar dados finais de greve: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "Atualizar dados da greve.")
    @PutMapping("atualizar/greve/{id}")
    public ResponseEntity<?> atualizarGreve( @PathVariable String id,@Valid @RequestBody GreveRequest greveRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            Greve greve = greveService.alterarDadosGreve(greveRequest, id);
            return ResponseEntity.status(HttpStatus.OK).body(greve);
        }catch(Exception e) {
            log.error("Erro ao editar greve: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @Operation(summary = "Deletar registro da greve.")
    @DeleteMapping("/deletar/greve/{id}")
    public ResponseEntity<?> deletarGreve(@PathVariable String id){
        try {
            greveService.deletarGreve(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e) {
            log.error("Erro ao deletar greve: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
