import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.awt.Desktop;

public class Client {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter first number: ");
            int a = sc.nextInt();

            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            String urlStr = "http://localhost:8000/add?x=" + a + "&y=" + b;
            System.out.println("Sending request to: " + urlStr);

            // Open result in default browser (once)
            Desktop.getDesktop().browse(new URL(urlStr).toURI());

            // Optional: Print server response in console
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            System.out.println("\nResponse from Web Service:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
