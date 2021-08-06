package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CLIService {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private UserService userService = null;

    private static void showMenu() {
        System.out.println("Available commands:");
        System.out.println("0 -- exit");
        System.out.println("1 -- create tracks");
        System.out.println("2 -- list tracks");
        System.out.println("3 -- create customers");
        System.out.println("4 -- list customers");
    }

    public CLIService(UserService userService) {
        this.userService = userService;
    }

    public void run() {
        showMenu();
        listenInput();
    }

    private void listenInput() {
        while (true) {
            try {
                handleInput(reader.readLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleInput(String input) {
        switch (input) {
            case "0": System.exit(0);
            case "1":
                userService.createTracks();
                break;
            case "2":
                userService.listTracks();
                break;
            case "3":
                userService.createCustomers();
                break;
            case "4":
                userService.listCustomers();
                break;

            default:
                throw new IllegalArgumentException("Unsupported command.");
        }
    }
}
