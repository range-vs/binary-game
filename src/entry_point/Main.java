package entry_point;

import database.ConnectionPostgres;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ResourcesManager;
import windows.AutorizationController;

import java.io.IOException;

public class Main extends Application {

    private String connect;
    private String database;
    private String login;
    private String password;
    private String title;

    private void initLines(){
        connect = "jdbc:postgresql://127.0.0.1:5432/";
        database = "binary-game";
        login = "postgres";
        password = "lostworld";
        title = "Binary Game version 0.1";
    }

    @Override
    public void start(Stage primaryStage){
        initLines();
        if(!ConnectionPostgres.getInstance().init(connect + database,
                login,
                password)){
            Platform.exit();
            return;
        }
        if(!initAutorizationPage(primaryStage)){
            Platform.exit();
            return;
        }
    }


    private boolean initAutorizationPage(Stage primaryStage){
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../windows/autorization.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        if(!ResourcesManager.initStyles(scene)){
            return false;
        }
        AutorizationController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
        return true;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
