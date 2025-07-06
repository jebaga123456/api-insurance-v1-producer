package pe.com.zalag.service.impl;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import pe.com.zalag.dto.InsuranceRequestDto;
import pe.com.zalag.dto.InsuranceResponseDto;
import pe.com.zalag.model.Insurance;
import pe.com.zalag.service.InsuranceService;
import java.util.UUID;

@ApplicationScoped
public class InsuranceServiceImpl implements InsuranceService {

  @Channel("insurance-emitter")
  Emitter<Insurance> emitter;

  /**
   * Metodo que crea un Zip si no existe, pero si existe la obtiene.
   *
   * @param insuranceRequestDto Objeto a recibir
   * @return Objeto creado u obtenido
   */
  @Override
  public Uni<Void> create(InsuranceRequestDto insuranceRequestDto) {
    return Uni.createFrom().voidItem()
        .invoke(() -> {
          var insurance = toInsuranceAvro(insuranceRequestDto);
          var message = Message.of(insurance)
              .addMetadata(
                  OutgoingKafkaRecordMetadata.builder()
                      .withKey(UUID.randomUUID().toString())
                      .build()
              );
          emitter.send(message);
        });
  }

  private Insurance toInsuranceAvro(InsuranceRequestDto request) {
    return new Insurance(request.policeNumber(),request.holderNumber(),request.type(),request.coverage(),request.active());
  }

  private InsuranceResponseDto requestToResponse(InsuranceRequestDto request) {
    return new InsuranceResponseDto(request.policeNumber(),request.holderNumber(),request.coverage(),request.active(),request.type());
  }
}
