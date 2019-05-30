package generator_task;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import utils.Pair;

public class Level1_1 extends Task {

    private char bit;
    private TextField input;
    private boolean style;

    @Override
    public Pair generateTask() {
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        Label info = new Label("Вставить на место пропущенного бита \nнедостающее значение так, чтобы равенство стало верным:");
        info.setStyle("-fx-text-fill:white");
        info.setTextAlignment(TextAlignment.CENTER);
        info.setFont(new Font(23));
        GridPane.setHalignment(info, HPos.CENTER);

        VBox.setMargin(info, new Insets(80, 10, 0, 10));
        int number = (int) (Math.random() * 1000) + 1; // число
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        int index = (int) (Math.random() * 2) + 0; // номер числа, которое будет с пропуском
        String binNum = String.format("%8s", Integer.toBinaryString(number)).replace(' ', '0');
        if(index == 0){
            int split = (int) (Math.random() * binNum.length()) + 0;
            bit = binNum.charAt(split);
            Label l1 = new Label(binNum.substring(0, split));
            l1.setFont(new Font(23));
            l1.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l1);
            input = new TextField(); input.setPrefWidth(30);
            hBox.getChildren().add(input);
            Label l2 = new Label(binNum.substring(split + 1) + "\u2082 = ");
            l2.setFont(new Font(23));
            l2.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l2);
            Label l3 = new Label(number + "\u2081\u2080");
            l3.setFont(new Font(23));
            l3.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l3);
        }else {
            Label l3 = new Label(number + "\u2081\u2080 = ");
            l3.setFont(new Font(23));
            l3.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l3);
            int split = (int) (Math.random() * binNum.length()) + 0;
            bit = binNum.charAt(split);
            Label l1 = new Label(binNum.substring(0, split));
            l1.setFont(new Font(23));
            l1.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l1);
            input = new TextField(); input.setPrefWidth(30);
            hBox.getChildren().add(input);
            Label l2 = new Label(binNum.substring(split + 1) + "\u2082");
            l2.setFont(new Font(23));
            l2.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l2);
        }
        style = false;
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if(style){
                input.setStyle("-fx-text-box-border: black ;" +
                        "-fx-focus-color: blue ;");
            }
            if (newValue.length() > 1) {
                input.setText(oldValue);
            } else{
                if(newValue.length() != 0) {
                    if (newValue.charAt(0) != '0' && newValue.charAt(0) != '1') {
                        input.setText(oldValue);
                    }
                }
            }
        });
        flowPane.getChildren().add(hBox);
        return new Pair(flowPane, info);
    }

    @Override
    public int answer() {
        if(input.getText().length() == 0){
            style = true;
            input.setStyle("-fx-text-box-border: red ;" +
                    "-fx-focus-color: red ;");
            return -1;
        }
        if(input.getText().charAt(0) == bit){
            return 1;
        }
        return 0;
    }

}
