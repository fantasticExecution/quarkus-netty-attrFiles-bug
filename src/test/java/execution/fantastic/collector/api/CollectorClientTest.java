package execution.fantastic.collector.api;

import execution.fantastic.collector.model.CollectorDto;
import execution.fantastic.collector.resource.CollectorWiremock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(CollectorWiremock.class)
class CollectorClientTest {

    @RestClient
    CollectorClient collectorClient;

    static Path tempDir;

    /**
     * Finds the temp directory of the jvm via creating a temp file and retrieving the parent directory of it.
     *
     * @throws IOException if no temporary file could be created
     */
    @BeforeAll
    static void getTempDir() throws IOException {
        Path tempFilePath = Files.createTempFile("jvm_tempfile_helper_", ".tmp"); // create temp file to get reference to jvm temp dir
        tempFilePath.toFile().deleteOnExit(); // cleanup file after test is finished
        tempDir = tempFilePath.getParent(); // get dir of temp file
    }

    @Test
    void testSendFile() {
        long attrFilesBeforeTest = getCountOfAttrTempFiles();

        collectorClient.sendFile(
            CollectorDto.builder()
                    .file(Path.of("src", "test", "resources", "testfile").toFile())
                    .messageId(UUID.randomUUID().toString())
                    .providerId("provider1")
                    .build()
        );

        long attrFilesAfterTest = getCountOfAttrTempFiles();

        assertEquals(attrFilesBeforeTest, attrFilesAfterTest, "some Attr_ temp files were not deleted after the request");
    }

    private long getCountOfAttrTempFiles(){
        File[] attrFiles = tempDir.toFile().listFiles((dir, name) -> name.startsWith("Attr_"));
        if (attrFiles == null ) {
            throw new IllegalStateException("could not list Attr_ files because the directory to search was not valid");
        }
        return attrFiles.length;
    }
}
