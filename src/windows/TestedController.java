package windows;

import database.ConnectionPostgres;
import generator_task.Task;
import generator_task.factory_method.CreatorTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

public class TestedController {

    @FXML
    private BorderPane startPane;
    @FXML
    private ScrollPane testPane;
    @FXML
    private BorderPane finalPane;
    @FXML
    private Label startPaneLabelInfo;
    @FXML
    private Button startPaneButtonStart;
    @FXML
    private Label testPaneCurrentTime;
    @FXML
    private Label testPaneCountTest;
    @FXML
    private BorderPane testPaneCenterPane;
    @FXML
    private Button testPaneButtonAnswer;
    @FXML
    private Label finalPaneInfo;
    @FXML
    private Button finalPaneCloseBtn;
    private Integer level;
    private Stage thisStage;
    private Timer timer;
    private long countSeconds;
    private int countTests;
    private int currentTest;
    private Task currentTask;
    private int correctTest;
    private String login;


    public void initialize(){
        countSeconds = 0;
        countTests = 0;
        currentTest = 0;
        currentTask = null;
        correctTest = 0;
        initStyles();
    }

    private void initStyles(){
        startPaneButtonStart.setId("btn-start");
        testPaneButtonAnswer.setId("btn-other");
        finalPaneCloseBtn.setId("btn-start");
    }


    public void initStartPane(Integer level, String text){
        this.level = level + 1;
        startPaneLabelInfo.setText(text);
    }

    public void setStage(Stage stage) {
        this.thisStage = stage;
        thisStage.setOnCloseRequest(event -> {
            if(currentTest == 0){
                return;
            }
            else if(currentTest <= countTests) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Сообщение");
                alert.setHeaderText("Предупреждение");
                alert.setContentText("Вы проходите тестирование. Если вы сейчас выйдите в главное меню,\n" +
                        "то прогресс будет утерян. Выйти?");
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    System.out.println("out");
                    timer.cancel();
                }else{
                    event.consume();
                }
            }
        });
    }


    public void runTest(ActionEvent actionEvent) {
        startPane.setVisible(false);
        TimerTask tick = new TimerTask() {
            @Override
            public void run() {
                long sec = ++countSeconds;
                long hours = sec / 3600;
                sec -= 3600 * hours;
                long minutes = sec / 60;
                sec -= 60 * minutes;
                long _sec = sec;
                Platform.runLater(() -> testPaneCurrentTime.setText((hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes)
                        + ":" + (_sec < 10 ? "0" + _sec : _sec)));
            }
        };
        timer = new Timer();


        countTests = (int) (Math.random() * (6 * level) + (5 * level));
        currentTest++;
        testPaneCountTest.setText(currentTest+"/"+countTests);
        currentTask = CreatorTask.createTask((int) (Math.random() * (level * 3)) + 0);
        testPaneCenterPane.setCenter(currentTask.generateTask());

        timer.schedule(tick, 0, 1000);
        testPane.setVisible(true);
    }

    public void runAnswer(ActionEvent actionEvent) {
        switch(currentTask.answer()){
            case 1:
                System.out.println("YES");
                correctTest++;
                break;

            case -1:
                return;
        }
        currentTest++;
        testPaneCountTest.setText(currentTest+"/"+countTests);
        if(currentTest <= countTests) {
            currentTask = CreatorTask.createTask((int) (Math.random() * (level * 3)) + 0);
            testPaneCenterPane.setCenter(currentTask.generateTask());
        }
        else{
            timer.cancel();
            System.out.println("End");
            testPane.setVisible(false);
            finalPaneInfo.setText("Ваш результат: \n"+
                    "Время: " + testPaneCurrentTime.getText() +
                    "\nОбщее количество заданий: " + countTests +
                    "\nКоличество заданий, которые решены правильно: " + correctTest +
                    "\nПосмотреть свой результат, а так же узнать лучшие можно в разделе" +
                    "\n\"Лучшие результаты\".\nВаш результат был добавлен в общую таблицу.\n");
            try {
                addNewResult();
            } catch (SQLException e) {
                e.printStackTrace();
                Platform.exit();
            }
            finalPane.setVisible(true);
        }
    }

    public void endTesting(ActionEvent actionEvent) {
        thisStage.close();
    }

    private void addNewResult() throws SQLException {
        Statement st = ConnectionPostgres.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT id FROM accounts WHERE name = '" + login + "';");
        rs.next();
        int idUser = rs.getBigDecimal(1).intValue();
        rs.close();
        rs = st.executeQuery("SELECT id FROM desc_levels WHERE level = '" + (level - 1) + "';");
        rs.next();
        int idLevel = rs.getBigDecimal(1).intValue();
        rs.close();
        st.close();
        st = ConnectionPostgres.getInstance().getConnection().createStatement();
        String query = "INSERT INTO best_results (\"time\", \"count\", id_accounts, id_level) VALUES ('" + testPaneCurrentTime.getText() + "', " + correctTest + ", " +
                idUser + ", " + idLevel + ");";
        st.executeUpdate(query);
        st.close();
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
