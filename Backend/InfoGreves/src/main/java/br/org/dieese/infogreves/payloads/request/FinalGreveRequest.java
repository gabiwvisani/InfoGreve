package br.org.dieese.infogreves.payloads.request;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
@Validated
public class FinalGreveRequest {
    @NotNull(message = "A data de início não pode ser nula.")
    private LocalDate dataFim;
    @NotNull(message = "A informação de se a greve teve sucesso não pode ser nula.")
    private Boolean greveTeveSucesso;
    private String resolucaoAcordo;
}
