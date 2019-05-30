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

public class Level2_2 extends Task {

    private Integer num;
    private TextField input;
    private boolean style;

    @Override
    public Pair generateTask() {
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        Label info = new Label("Даны два числа, представленные в двоичной форме. \nТребуется вставить число, чтобы оно \nудовлетворяло условию, отображённому ниже:");
        info.setStyle("-fx-text-fill:white");
        info.setTextAlignment(TextAlignment.CENTER);
        info.setFont(new Font(23));
        GridPane.setHalignment(info, HPos.CENTER);

        VBox.setMargin(info, new Insets(80, 10, 0, 10));
        //flowPane.getChildren().add(info);
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(((int) (Math.random() * 483) + 1)); // число
        nums.add(nums.get(0) + 1); // искомое число
        nums.add(nums.get(0) + 2); // второе число
        num = nums.get(1);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        for(int i = 0;i<3;i++){
            if(i == 1){
                Label l = new Label(" < ");
                l.setFont(new Font(16));
                l.setStyle("-fx-text-fill:white");
                hBox.getChildren().add(l);
                input = new TextField();
                input.setPrefWidth(150);
                hBox.getChildren().add(input);
                l = new Label("\u2082 < ");
                l.setFont(new Font(16));
                l.setStyle("-fx-text-fill:white");
                hBox.getChildren().add(l);
            } else {
                String binNum = String.format("%8s", Integer.toBinaryString(nums.get(i))).replace(' ', '0');
                Label l1 = new Label(binNum + "\u2082");
                l1.setFont(new Font(16));
                l1.setStyle("-fx-text-fill:white");
                hBox.getChildren().add(l1);
            }
        }
        flowPane.getChildren().add(hBox);
        style = false;
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if(style){
                input.setStyle("-fx-text-box-border: black ;" +
                        "-fx-focus-color: blue ;");
            }
            if (newValue.length() != 0) {
                boolean result = newValue.matches("([0-1]+)");
                if(!result){
                    input.setText(oldValue);
                    return;
                }
            }
        });
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
        Integer n = toDecimal(input.getText());
        if(n.equals(num)){
            return 1;
        }
        return 0;
    }

    private int toDecimal(String base2) {
        char[] chars = base2.toCharArray();
        int result = 0;
        int mult = 1;
        for (int i = base2.length()-1; i >= 0; i--) {
            if (chars[i]=='1') {
                result += mult;
            }
            mult*=2;
        }
        return result;
    }


}
