import javax.swing.SwingUtilities;

import controller.MainController;

public class App {
     public static void main(String[] args){
        Runnable run = () -> {
            new MainController();
        };
        SwingUtilities.invokeLater(run);
    }
}
