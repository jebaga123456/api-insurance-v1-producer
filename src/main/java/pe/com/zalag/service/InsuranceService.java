package pe.com.zalag.service;

import io.smallrye.mutiny.Uni;
import pe.com.zalag.dto.InsuranceRequestDto;

public interface InsuranceService {

  Uni<Void> create(InsuranceRequestDto insuranceRequestDto);
}
