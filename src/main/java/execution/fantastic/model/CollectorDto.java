package execution.fantastic.model;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;

import java.io.File;
import java.time.Instant;

public class CollectorDto {

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private File file;

    @FormParam("messageId")
    @PartType(MediaType.TEXT_PLAIN)
    private String messageId;

    @FormParam("providerId")
    @PartType(MediaType.TEXT_PLAIN)
    private String providerId;

    @FormParam("receivedDateTime")
    @PartType(MediaType.TEXT_PLAIN)
    private Instant receivedDateTime;

}
