package pr;
import java.util.*;
public class Bully {
    public static void main(String[] arg){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number of process:");
        int n = sc.nextInt();

        int [] process = new int[n];
        System.out.println("Enter process IDs:");
        for(int i=0;i<n;i++)
        {
            process[i] = sc.nextInt();
        }
        Arrays.sort(process);
        System.out.print("Enter initiator process ID: ");
        int initiator = sc.nextInt();

        System.out.println("\nProcess " + initiator + " detects coordinator failure.");
        System.out.println("Process " + initiator + " starts election...");
        int coordinator = initiator;
        boolean higherFound = false;
        for(int i=0 ; i < process.length ;i++)
        {
            if( process[i] > initiator ){
                System.out.println("election message sent from " + initiator + "->" + process[i]);
                System.out.println("OK message received from " + process[i]);
                coordinator = process[i];
                higherFound = true;
            }
        }
        if (!higherFound) {
            System.out.println("No higher processes found.");
            System.out.println("Process " + initiator + " becomes the new coordinator.");
        } else {
            System.out.println("\nProcess " + coordinator + " becomes the new coordinator.");
            for (int i = 0; i < process.length; i++) {
              if (process[i] != coordinator) {
              System.out.println("Coordinator message sent from " + coordinator + " → " + process[i]);
            }
}

        }

        sc.close();
    }
}
