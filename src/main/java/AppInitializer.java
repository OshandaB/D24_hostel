import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.net.URL;

public class AppInitializer extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL resource = AppInitializer.class.getResource("/assests/Dashboard.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        stage.setTitle("Super Market");

        stage.centerOnScreen();
        stage.show();
//        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
    }
}
