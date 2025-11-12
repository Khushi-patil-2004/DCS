// ArithmeticServiceImpl.java
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ArithmeticServiceImpl extends UnicastRemoteObject implements ArithmeticService {

    protected ArithmeticServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public int divide(int a, int b) throws RemoteException {
        if (b == 0) throw new RemoteException("Cannot divide by zero!");
        return a / b;
    }
}
