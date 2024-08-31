package br.org.dieese.infogreves.payloads.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
@Validated
public class GreveRequest {
    @NotNull(message = "A data de início não pode ser nula.")
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @Size( max = 255, message = "O motivo deve ter entre 5 e 255 caracteres.")
    private String motivo;
    @Size( max = 255, message = "O motivo deve ter entre 5 e 255 caracteres.")
    private String categoria;
    @Size( max = 255, message = "O motivo deve ter entre 5 e 255 caracteres.")
    private String sindicatoResponsavel;
    @Min(value = 1, message = "O número de trabalhadores deve ser pelo menos 1.")
    @Max(value = 1000000, message = "O número de trabalhadores deve ser no máximo 1.000.000.")
    private int numeroTrabalhadores;
    private String local;
    private Boolean greveTeveSucesso;
    private String resolucaoAcordo;

}
