package windows;

import database.ConnectionPostgres;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.ResourcesManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountController {

    @FXML
    private Label headerText;
    @FXML
    private ImageView imageViewLogo;
    @FXML
    private TextField textLogin;
    @FXML
    private TextField textPasswordShow;
    @FXML
    private PasswordField textPasswordHide;
    @FXML
    private Label labelPassword;
    @FXML
    private Button testAccount;
    private int typeOperation;
    private boolean status;
    private Stage thisStage;

    public void initialize(){
        typeOperation = -1;
        status = false;
        initStyles();
    }


    private void initStyles(){
        testAccount.setId("btn-start");
    }

    public void initAutorization(){
        typeOperation = 1;
        headerText.setText("Авторизация пользователя");
        testAccount.setText("Войти");
        Image img = ResourcesManager.initImage("resources/images/logo-a.png");
        if(img == null){
            Platform.exit();
        }
        imageViewLogo.setImage(img);
    }

    public void initRegistration(){
        typeOperation = 2;
        headerText.setText("Регистрация нового пользователя");
        testAccount.setText("Зарегистрироваться");
        Image img = ResourcesManager.initImage("resources/images/logo-r.png");
        if(img == null){
            Platform.exit();
        }
        imageViewLogo.setImage(img);
    }

    public void run(MouseEvent mouseEvent) throws SQLException {
        status = false;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Ошибка");
        alert.setTitle("Сообщение");
        if(typeOperation == 1){
            if(findAccount(1)){
                status = true;
                thisStage.close();
                return;
            }
            alert.setContentText("Пользователь с таким логином или паролем не найден!");
            alert.showAndWait();
        }else if(typeOperation == 2){
            if(textLogin.getText().length() == 0 || textPasswordShow.getText().length() == 0){
                alert.setContentText("Поле логина или пароля не заполнено!");
                alert.showAndWait();
                return;
            }
            else if(findAccount(1)){
                alert.setContentText("Пользователь с таким логином уже существует!");
                alert.showAndWait();
                return;
            }
            Statement st = ConnectionPostgres.getInstance().getConnection().createStatement();
            String query = "INSERT INTO accounts (name, pass) VALUES ('" + textLogin.getText() + "', '" + getCurrentPassword() + "');";
            st.executeUpdate(query);
            textLogin.setText("");
            textPasswordShow.setText("");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Пользователь успешно добавлен");
            alert.setHeaderText("Успех");
            alert.showAndWait();
            st.close();
        }
    }

    private boolean findAccount(int typeFind) throws SQLException {
        Statement st = ConnectionPostgres.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT name, pass FROM accounts;");
        String pass = getCurrentPassword();
        boolean result = false;
        while (rs.next()) {
            if(typeFind == 1) {
                if (textLogin.getText().equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
                    result = true;
                    break;
                }
            }else if(typeFind == 2){
                if (textLogin.getText().equals(rs.getString(1))) {
                    result = true;
                    break;
                }
            }
        }
        rs.close();
        st.close();
        return result;
    }

    public String getCurrentPassword(){
        if(textPasswordShow.isVisible()){
            return textPasswordShow.getText();
        }
        return textPasswordHide.getText();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStage(Stage stage) {
        this.thisStage = stage;
    }

    public void resetStateText(ActionEvent actionEvent) {
        if(labelPassword.getText().equals("Показать пароль")){
            labelPassword.setText("Скрыть пароль");
            textPasswordShow.setText(textPasswordHide.getText());
            textPasswordHide.setVisible(false);
            textPasswordShow.setVisible(true);
        }else{
            labelPassword.setText("Показать пароль");
            textPasswordHide.setText(textPasswordShow.getText());
            textPasswordShow.setVisible(false);
            textPasswordHide.setVisible(true);
        }
    }

    public String getTextLogin() {
        return textLogin.getText();
    }
}
