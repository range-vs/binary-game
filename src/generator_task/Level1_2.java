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

import java.util.ArrayList;

public class Level1_2 extends Task {

    private char bit;
    private TextField input;
    private boolean style;

    @Override
    public FlowPane generateTask() {
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        Label info = new Label("Вставить на место пропущенного бита недостающее \nзначение так, чтобы результат арифметической операции " +
                "\nмежду операндами стал корректным:");
        info.setStyle("-fx-text-fill:white");
        info.setTextAlignment(TextAlignment.CENTER);
        info.setWrapText(true);
        info.setFont(new Font(23));
        info.setAlignment(Pos.CENTER);
        VBox.setMargin(info, new Insets(80, 10, 0, 10));
        flowPane.getChildren().add(info);
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 0;i<2;i++) {
            nums.add(((int) (Math.random() * 350) + 1)); // число
        }
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        int index = (int) (Math.random() * 2) + 0; // номер числа, которое будет с пропуском
        String binNum = String.format("%8s", Integer.toBinaryString(nums.get(index))).replace(' ', '0');
        if(index == 0){
            int split = (int) (Math.random() * binNum.length()) + 0;
            bit = binNum.charAt(split);
            Label l1 = new Label(binNum.substring(0, split));
            l1.setFont(new Font(23));
            l1.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l1);
            input = new TextField(); input.setPrefWidth(30);
            hBox.getChildren().add(input);
            Label l2 = new Label(binNum.substring(split + 1) + "\u2082 " + (index == 0 ? "+ " : "- "));
            l2.setFont(new Font(23));
            l2.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l2);
            Label l3 = new Label(String.format("%8s", Integer.toBinaryString(nums.get(1))).replace(' ', '0') + "\u2082");
            l3.setFont(new Font(23));
            l3.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l3);
        }else {
            Label l3 = new Label(String.format("%8s", Integer.toBinaryString(nums.get(0))).replace(' ', '0') + "\u2082 "+ (index == 0 ? "+" : "-"));
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
        Label l4 = new Label(" = " + (index == 0 ? (nums.get(0) + nums.get(1)) : (nums.get(0) - nums.get(1))) + "\u2081\u2080");
        l4.setFont(new Font(23));
        l4.setStyle("-fx-text-fill:white");
        hBox.getChildren().add(l4);
        flowPane.getChildren().add(hBox);
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
        if(input.getText().charAt(0) == bit){
            return 1;
        }
        return 0;
    }
}
