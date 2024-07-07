package com.network.networkone;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.secret.manager.SecretManager;

public class NetworkOne {
    private String getKey() {
        try {
            return new SecretManager().accessSecretVersion();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }
    private String getLong() {
        return "-96.7970";
    }
    private String getLat() {
        return "32.7767";
    }
    public String fetch() throws URISyntaxException, InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(
                        new URI("https://api.openweathermap.org/data/2.5/weather?lat="+
                                this.getLat()+
                                "&lon="+
                                this.getLong()+
                                "&units=imperial&appid="+
                                this.getKey()))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
