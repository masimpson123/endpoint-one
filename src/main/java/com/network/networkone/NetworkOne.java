package com.network.networkone;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class NetworkOne {
    public String getKey() {
        return "ae90bbba41d65b1f047a019e0a55de96";
    }
    public String getLong() {
        return "-96.7970";
    }
    public String getLat() {
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
