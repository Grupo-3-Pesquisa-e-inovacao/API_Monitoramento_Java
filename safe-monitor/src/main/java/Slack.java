import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Slack {
    private static HttpClient client = HttpClient.newHttpClient();
    private static  final String url = "https://hooks.slack.com/services/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";

    public static void enviarMensagem(JSONObject content) throws IOException,InterruptedException{
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
