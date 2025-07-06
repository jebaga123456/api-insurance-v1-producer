package pe.com.zalag.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import pe.com.zalag.dto.InsuranceRequestDto;
import pe.com.zalag.service.InsuranceService;

@Path("/v1/insurance")
public class InsuranceResource {

  private static final Logger LOG = Logger.getLogger(InsuranceResource.class);

  @Inject
  InsuranceService insuranceService;

  @POST
  public Uni<Response> create(InsuranceRequestDto request) {
    return insuranceService
        .create(request)
        .replaceWith(Response.accepted().build());
  }
}
