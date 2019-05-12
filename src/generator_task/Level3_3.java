package generator_task;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Level3_3 extends Task {

    private Long number;
    private TextField input;
    private boolean style;

    @Override
    public FlowPane generateTask() {
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        Label info = new Label("Дано число, представленное в двоичной форме.\n Требуется записать факториал этого числа, также, в двоичной форме:");
        info.setStyle("-fx-text-fill:white");
        info.setTextAlignment(TextAlignment.CENTER);
        info.setWrapText(true);
        info.setFont(new Font(23));
        info.setAlignment(Pos.CENTER);
        VBox.setMargin(info, new Insets(80, 10, 0, 10));
        flowPane.getChildren().add(info);
        int num = ((int) (Math.random() * 11) + 1); // число

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        String binNum = String.format("%8s", Integer.toBinaryString(num).replace(' ', '0'));
        number = factorial(num);
        Label l1 = new Label(binNum + "\u2082 = ");
        l1.setFont(new Font(23));
        l1.setStyle("-fx-text-fill:white");
        pane.getChildren().add(l1);
        input = new TextField();
        input.setPrefWidth(200);
        pane.getChildren().add(input);
        Label l2 = new Label("\u2082");
        l2.setFont(new Font(23));
        l2.setStyle("-fx-text-fill:white");
        pane.getChildren().add(l2);
        flowPane.getChildren().add(pane);

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
        Long n = (long)toDecimal(input.getText());
        if(n.equals(number)){
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

    private long factorial(int num){
        long result = 1;
        for(int i = 2;i<=num;i++){
            result *= i;
        }
        return result;
    }

}
