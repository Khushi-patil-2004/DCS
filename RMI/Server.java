// Server.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Arithmetic Server started...");

            ArithmeticService service = new ArithmeticServiceImpl();
            registry.rebind("ArithmeticService", service);

            System.out.println("Server is ready!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
