package utils;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ResourcesManager {

    private ResourcesManager(){}

    public static boolean initStyles(Scene scene){
        try {
            scene.getStylesheets().add("resources/css/styles.css");
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public static Image initImage(String name){
        Image img = null;
        try {
            img = new Image(name);
        }
        catch (Exception ex){
            System.out.println(ex);
            return null;
        }
        return img;
    }

    public static Pair initPage(Class<?> _class, String path){
        FXMLLoader fxmlLoader = new FXMLLoader(_class.getResource(path));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
        Stage st = new Stage();
        st.setScene(new Scene(root, Color.TRANSPARENT));
        if(!ResourcesManager.initStyles(st.getScene())){
            return null;
        }
        Object controller = fxmlLoader.getController();
        return new Pair(st, controller);
    }

}
