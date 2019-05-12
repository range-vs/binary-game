package windows;

import database.ConnectionPostgres;
import entity.Level;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import entity.LevelComparator;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Pair;
import utils.ResourcesManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

public class MainController {

    @FXML
    private VBox levelsBox;
    @FXML
    private VBox eduBox;
    private String loginCurrentUser;

    public void initialize(){
        try {
            initLevelButtons();
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.exit();
            return;
        }
        initEduButtons();
    }

    public void setLoginCurrentUser(String loginCurrentUser) {
        this.loginCurrentUser = loginCurrentUser;
    }

    public void showBestResult(ActionEvent actionEvent) {
        Pair p = ResourcesManager.initPage(getClass(), "best_result.fxml");
        if(p == null){
            Platform.exit();
            return;
        }
        Stage st = (Stage)p.getFirst();
        //st.setResizable(false);
        st.initModality(Modality.APPLICATION_MODAL);
        st.showAndWait();
    }

    private void initLevelButtons() throws SQLException {
        Statement st = ConnectionPostgres.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT level, name, description FROM desc_levels;");
        TreeSet<Level> levels = new TreeSet<Level>(new LevelComparator());
        while (rs.next()) {
            levels.add(new Level(rs.getBigDecimal(1).intValue(), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        st.close();
        for(Level p: levels){
            Button btn = new Button("Уровень \"" + p.getName() + "\"");
            levelsBox.getChildren().add(btn);
            btn.setMnemonicParsing(false);
            VBox.setMargin(btn, new Insets(20, 0, 0, 20));
            btn.setUserData(new Pair(p.getLevel(), p.getText()));
            btn.setOnAction(actionEvent -> runTesting(actionEvent));
            btn.setId("btn-other");
        }
    }

    private void initEduButtons(){
        Button btn = new Button("Двоичная арифметика");
        eduBox.getChildren().add(btn);
        btn.setMnemonicParsing(false);
        VBox.setMargin(btn, new Insets(20, 0, 0, 20));
        btn.setOnAction(actionEvent -> openEdu(actionEvent));
        btn.setId("btn-other");
    }

    public void runTesting(ActionEvent actionEvent) {
        Pair p = ResourcesManager.initPage(getClass(), "tested.fxml");
        if(p == null){
            Platform.exit();
            return;
        }
        Stage st = (Stage)p.getFirst();
        TestedController controller = (TestedController)p.getSecond();
        if(!ResourcesManager.initStyles(st.getScene())){
            Platform.exit();
            return;
        }
        //st.setResizable(false);
        st.initModality(Modality.APPLICATION_MODAL);
        controller.setStage(st);
        Pair l = (Pair)(((Button)actionEvent.getSource()).getUserData());
        controller.initStartPane((Integer)l.getFirst(), (String)l.getSecond());
        controller.setLogin(loginCurrentUser);
        st.setMinHeight(400);
        st.setMinWidth(600);
        st.setTitle("Тестирование: " + ((Button)actionEvent.getSource()).getText());
        st.showAndWait();
    }

    public void openEdu(ActionEvent actionEvent) {
        Pair p = ResourcesManager.initPage(getClass(), "education.fxml");
        if(p == null){
            Platform.exit();
            return;
        }
        Stage st = (Stage)p.getFirst();
        if(!ResourcesManager.initStyles(st.getScene())){
            Platform.exit();
            return;
        }
        //st.setMaximized(true);
        st.initModality(Modality.APPLICATION_MODAL);
        st.showAndWait();
    }

}
