
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8080), 0
        );

        server.createContext("/", exchange -> {

            String html = """
                <html>
                <head>
                    <title>Online Examination System</title>
                </head>
                <body>
                    <h1>Online Examination System</h1>
                    <p>Java Server is Running Successfully!</p>
                    <p>Open the website files in the public folder
                    through the project server.</p>
                </body>
                </html>
                """;

            exchange.getResponseHeaders().set(
                "Content-Type", "text/html; charset=UTF-8"
            );

            byte[] response = html.getBytes("UTF-8");

            exchange.sendResponseHeaders(200, response.length);

            exchange.getResponseBody().write(response);
            exchange.close();
        });

        server.start();

        System.out.println("Server Started Successfully!");
        System.out.println("Open http://localhost:8080");
    }
}
