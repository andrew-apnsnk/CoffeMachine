import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine(500, 500, 500, 500, 500);
        machine.askForCommand();
        while (true) {
            String command = scanner.next();
            if (command.equals("exit")) {
                break;
            }
            machine.enterCommand(command);
        }
    }
}
