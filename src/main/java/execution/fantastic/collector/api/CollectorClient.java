package execution.fantastic.collector.api;

import execution.fantastic.collector.model.CollectorDto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "collector-client")
public interface CollectorClient {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/message")
    void sendFile(CollectorDto collectorDto);
}
