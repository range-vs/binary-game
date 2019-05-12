package generator_task;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Level2_3 extends Task {

    private Integer count;
    private TextField input;
    private boolean style;

    @Override
    public FlowPane generateTask() {
        count = new Integer(0);
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        Label info = new Label("Дано число, представленное в двоичной форме.\n Требуется записать количество \nзначащих нулей этого числа:");
        info.setStyle("-fx-text-fill:white");
        info.setTextAlignment(TextAlignment.CENTER);
        info.setWrapText(true);
        info.setFont(new Font(23));
        info.setAlignment(Pos.CENTER);
        VBox.setMargin(info, new Insets(80, 10, 0, 10));
        flowPane.getChildren().add(info);
        int num = ((int) (Math.random() * 1000) + 1); // число
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        String binNum = String.format("%8s", Integer.toBinaryString(num).replace(' ', '0'));
        String bitNumNoZero = Integer.toBinaryString(num);
        for(int i = 0;i<bitNumNoZero.length();i++){
            if(bitNumNoZero.charAt(i) == '0'){
                count++;
            }
        }
        Label l1 = new Label(binNum + "\u2082");
        l1.setFont(new Font(23));
        l1.setStyle("-fx-text-fill:white");
        hBox.getChildren().add(l1);
        flowPane.getChildren().add(hBox);
        input = new TextField();
        input.setPrefWidth(50);
        flowPane.getChildren().add(input);
        style = false;
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if(style){
                input.setStyle("-fx-text-box-border: black ;" +
                        "-fx-focus-color: blue ;");
            }
            if (newValue.length() != 0) {
                boolean result = newValue.matches("([0-9]+)");
                if(!result){
                    input.setText(oldValue);
                    return;
                }
            }
        });
        return flowPane;
    }

    @Override
    public int answer() {
        if(input.getText().length() == 0){
            style = true;
            input.setStyle("-fx-text-box-border: red ;" +
                    "-fx-focus-color: red ;");
            return -1;
        }
        Integer n = Integer.parseInt(input.getText());
        if(n.equals(count)){
            return 1;
        }
        return 0;
    }

}
