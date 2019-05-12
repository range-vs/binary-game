package windows;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Pair;
import utils.ResourcesManager;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class AutorizationController {

    @FXML
    private ImageView imageViewLogo;
    @FXML
    private Button btnAutorization;
    @FXML
    private Button btnRegistration;
    private Stage thisStage;


    public void initialize(){
        Image img = ResourcesManager.initImage("resources/images/logo.png");
        if(img == null){
            Platform.exit();
        }
        imageViewLogo.setImage(img);
        initStyles();
    }

    private void initStyles(){
        btnAutorization.setId("btn-start");
        btnRegistration.setId("btn-start");
        imageViewLogo.setId("image-logo");
    }

    public void clickEaster(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.google.ru/search?newwindow=1&ei=zdvNXIOuE6GMmwXukbbYDw&q=what+binary+game%3F&oq=what+binary+game%3F&gs_l=psy-ab.3...4891.9417..9664...1.0..0.124.1829.10j8......0....1..gws-wiz.......0i71j35i39j0i131j0j0i203j0i22i30j33i22i29i30j33i160.Zs9JvrCdYvw").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void openPage(MouseEvent mouseEvent) {
        Pair p = ResourcesManager.initPage(getClass(), "account.fxml");
        if(p == null){
            Platform.exit();
            return;
        }
        Stage st = (Stage)p.getFirst();
        st.setResizable(false);
        AccountController controller = (AccountController)p.getSecond();
        st.initModality(Modality.APPLICATION_MODAL);

        Button btn = (Button)mouseEvent.getSource();
        if(btn == btnAutorization){
            st.setTitle("Авторизация");
            controller.initAutorization();
        }else{
            st.setTitle("Регистрация");
            controller.initRegistration();
        }
        controller.setStage(st);
        st.showAndWait();
        if(controller.isStatus()){
            System.out.println("ok");
            p = ResourcesManager.initPage(getClass(), "main.fxml");
            if(p == null){
                Platform.exit();
                return;
            }
            st = (Stage)p.getFirst();
            st.setMinWidth(600);
            st.setMinHeight(400);
            MainController contr = (MainController)p.getSecond();
            if(!ResourcesManager.initStyles(st.getScene())){
                Platform.exit();
                return;
            }
            contr.setLoginCurrentUser(controller.getTextLogin());
            st.setTitle(controller.getTextLogin());
            thisStage.close();
            st.show();
        }
    }

    public void setStage(Stage stage) {
        this.thisStage = stage;
    }
}
