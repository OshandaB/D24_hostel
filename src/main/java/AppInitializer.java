import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.D24_hostel.config.SessionFactoryConfig;
import org.hibernate.Session;


import java.net.URL;

public class AppInitializer extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL resource = AppInitializer.class.getResource("/assests/login.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        stage.setTitle("D24_Hostel");

        stage.centerOnScreen();
        stage.show();
//        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
    }
}
