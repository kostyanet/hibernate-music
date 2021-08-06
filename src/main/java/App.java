import services.CLIService;
import services.UserService;

public class App {

    public static void main(String[] args) {
        UserService userService = new UserService();
        CLIService cliService = new CLIService(userService);
        cliService.run();
    }
}
