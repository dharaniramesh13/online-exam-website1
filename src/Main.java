
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8081), 0
        );

        server.createContext("/", exchange -> {

            String uri = exchange.getRequestURI().getPath();

            if (uri.equals("/")) {
                uri = "/index.html";
            }

            Path file = Path.of("public", uri.substring(1));

            if (Files.exists(file) && !Files.isDirectory(file)) {

                byte[] response = Files.readAllBytes(file);

                String type = "text/plain";

                if (uri.endsWith(".html")) {
                    type = "text/html; charset=UTF-8";
                } else if (uri.endsWith(".css")) {
                    type = "text/css; charset=UTF-8";
                } else if (uri.endsWith(".js")) {
                    type = "application/javascript; charset=UTF-8";
                }

                exchange.getResponseHeaders().set(
                    "Content-Type", type
                );

                exchange.sendResponseHeaders(200, response.length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }

            } else {

                String message = "404 - File Not Found";
                byte[] response = message.getBytes("UTF-8");

                exchange.sendResponseHeaders(404, response.length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            }

        });

        server.start();

        System.out.println("Online Examination Server Started!");
        System.out.println("Open http://localhost:8081");
    }
}
