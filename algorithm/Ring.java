package pr;
import java.util.*;
public class Ring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] process = new int[n];
        System.out.println("Enter process IDs in ring order:");
        for (int i = 0; i < n; i++) {
            process[i] = sc.nextInt();
        }

        System.out.print("Enter initiator process ID: ");
        int initiator = sc.nextInt();

        List<Integer> election = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (process[i] == initiator) {
                start = i;
                break;
            }
        }
        System.out.println("\nProcess " + initiator + " starts election...");
        for (int i = 0; i < n; i++) {
            int idx = (start + i) % n;
            election.add(process[idx]);
        }

        int coordinator = Collections.max(election);
        System.out.println("Election message passed: " + election);
        System.out.println("Process " + coordinator + " becomes the new coordinator.");

        sc.close();
    }
}

