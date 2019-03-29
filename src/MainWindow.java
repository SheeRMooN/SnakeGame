import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(415,440);
        setLocation(500,500 );
        add(new GameFild());
        setVisible(true);
        System.out.println("Начало Игры");
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}


