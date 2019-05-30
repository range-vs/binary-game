package generator_task;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import utils.Pair;

import java.util.ArrayList;

public class Level2_1 extends Task {

    private char[] bits;
    private TextField[] inputs;
    private boolean[] styles;

    @Override
    public Pair generateTask() {
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        Label info = new Label("Вставить на место пропущенных битов недостающие значения так, \nчтобы результат арифметической операции \"умножение\" " +
                "\nмежду операндами стал корректным:");
        info.setStyle("-fx-text-fill:white");
        info.setTextAlignment(TextAlignment.CENTER);
        info.setFont(new Font(23));
        GridPane.setHalignment(info, HPos.CENTER);

        VBox.setMargin(info, new Insets(80, 10, 0, 10));
        //flowPane.getChildren().add(info);
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 0;i<2;i++) {
            nums.add(((int) (Math.random() * 110) + 1)); // число
        }
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        bits = new char[2];
        inputs = new TextField[2];
        for(int i = 0;i<nums.size();i++) {
            String binNum = String.format("%8s", Integer.toBinaryString(nums.get(i))).replace(' ', '0');
            int split = (int) (Math.random() * binNum.length()) + 0;
            bits[i] = binNum.charAt(split);
            Label l1 = new Label(binNum.substring(0, split));
            l1.setFont(new Font(16));
            l1.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l1);
            inputs[i] = new TextField();
            inputs[i].setPrefWidth(30);
            hBox.getChildren().add(inputs[i]);
            Label l2 = new Label(binNum.substring(split + 1) + "\u2082");
            l2.setFont(new Font(16));
            l2.setStyle("-fx-text-fill:white");
            hBox.getChildren().add(l2);
            if(i == 0){
                Label l = new Label(" * ");
                l.setFont(new Font(16));
                l.setStyle("-fx-text-fill:white");
                hBox.getChildren().add(l);
            } else{
                Label l = new Label(" = ");
                l.setFont(new Font(16));
                l.setStyle("-fx-text-fill:white");
                hBox.getChildren().add(l);
            }
        }
        Label l3 = new Label(String.format("%8s", Integer.toBinaryString(nums.get(0) * nums.get(1))).replace(' ', '0') + "\u2082");
        l3.setFont(new Font(16));
        l3.setStyle("-fx-text-fill:white");
        hBox.getChildren().add(l3);
        flowPane.getChildren().add(hBox);
        styles = new boolean[2];
        for(int i = 0;i<2;i++) {
            styles[i] = false;
            final int _i = i;
            inputs[i].textProperty().addListener((observable, oldValue, newValue) -> {
                if (styles[_i]) {
                    inputs[_i].setStyle("-fx-text-box-border: black ;" +
                            "-fx-focus-color: blue ;");
                }
                if (newValue.length() > 1) {
                    inputs[_i].setText(oldValue);
                } else {
                    if (newValue.length() != 0) {
                        if (newValue.charAt(0) != '0' && newValue.charAt(0) != '1') {
                            inputs[_i].setText(oldValue);
                        }
                    }
                }
            });
        }
        return new Pair(flowPane, info);
    }

    @Override
    public int answer() {
        int errors = 0;
        int valid = 0;
        for(int i = 0;i<2;i++) {
            if(inputs[i].getText().length() == 0){
                styles[i] = true;
                inputs[i].setStyle("-fx-text-box-border: red ;" +
                        "-fx-focus-color: red ;");
                errors++;
                continue;
            }
            if(inputs[i].getText().charAt(0) == bits[i]){
                valid++;
            }
        }
        if(errors > 0){
            return -1;
        } else if(valid == 2){
            return 1;
        }else{
            return 0;
        }
    }

}
