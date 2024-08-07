package com.secret.manager;

import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import java.io.IOException;
import java.util.zip.CRC32C;
import java.util.zip.Checksum;

public class SecretManager {

        public static String accessSecretVersion() throws IOException {
            String projectId = "endpoint-one";
            String secretId = "open-weather-key";
            String versionId = "1";
            return accessSecretVersion(projectId, secretId, versionId);
        }

        // Access the payload for the given secret version if one exists. The version
        // can be a version number as a string (e.g. "5") or an alias (e.g. "latest").
        public static String accessSecretVersion(String projectId, String secretId, String versionId)
                throws IOException {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests. After completing all of your requests, call
            // the "close" method on the client to safely clean up any remaining background resources.
            try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
                SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, versionId);

                // Access the secret version.
                AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);

                // Verify checksum. The used library is available in Java 9+.
                // If using Java 8, you may use the following:
                // https://github.com/google/guava/blob/e62d6a0456420d295089a9c319b7593a3eae4a83/guava/src/com/google/common/hash/Hashing.java#L395
                byte[] data = response.getPayload().getData().toByteArray();
                Checksum checksum = new CRC32C();
                checksum.update(data, 0, data.length);
                if (response.getPayload().getDataCrc32C() != checksum.getValue()) {
                    System.out.printf("Data corruption detected.");
                    return "";
                }

                return response.getPayload().getData().toStringUtf8();
            }
        }
}
