package execution.fantastic.collector.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;

import java.io.File;

@JsonDeserialize(builder = CollectorDto.CollectorDtoBuilder.class)
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

    // boilerplate constructor, getter and builder below

    CollectorDto(File file, String messageId, String providerId) {
        this.file = file;
        this.messageId = messageId;
        this.providerId = providerId;
    }

    public static CollectorDtoBuilder builder() {
        return new CollectorDtoBuilder();
    }

    public File getFile() {
        return this.file;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getProviderId() {
        return this.providerId;
    }

    @com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class CollectorDtoBuilder {
        private File file;
        private String messageId;
        private String providerId;

        CollectorDtoBuilder() {
        }

        public CollectorDtoBuilder file(File file) {
            this.file = file;
            return this;
        }

        public CollectorDtoBuilder messageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public CollectorDtoBuilder providerId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public CollectorDto build() {
            return new CollectorDto(this.file, this.messageId, this.providerId);
        }

        public String toString() {
            return "CollectorDto.CollectorDtoBuilder(file=" + this.file + ", messageId=" + this.messageId + ", providerId=" + this.providerId + ")";
        }
    }
}
