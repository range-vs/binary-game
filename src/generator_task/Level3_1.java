package generator_task;

import generator_task.logic_opearation.LogicOperation;
import generator_task.logic_opearation.factory_method.CreatorLogicOperation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Level3_1 extends Task {

    private int indexBtn;
    private Label input;
    private boolean style;
    private int [] nums;
    private int result;

    @Override
    public FlowPane generateTask() {
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        Label info = new Label("Дано логическое выражение.\n Требуется записать верныую логическую операцию,\nчтобы результат выражения был корректным::");
        info.setStyle("-fx-text-fill:white");
        info.setTextAlignment(TextAlignment.CENTER);
        info.setWrapText(true);
        info.setFont(new Font(23));
        info.setAlignment(Pos.CENTER);
        VBox.setMargin(info, new Insets(80, 10, 0, 10));
        flowPane.getChildren().add(info);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        nums = new int[]{(int) (Math.random() * 2) + 0, (int) (Math.random() * 2) + 0};
        Label l1 = new Label(String.valueOf(nums[0]));
        l1.setStyle("-fx-text-fill:white");
        l1.setFont(new Font(23));
        hBox.getChildren().add(l1);
        LogicOperation logicOperation = CreatorLogicOperation.createOperation((int) (Math.random() * 4) + 0);
        input = new Label(" ");
        input.setStyle("-fx-text-fill:white;-fx-background-color: black;");
        input.setFont(new Font(23));
        HBox.setMargin(input, new Insets(0, 10, 0, 10));
        hBox.getChildren().add(input);
        Label l2 = new Label(String.valueOf(nums[1]));
        l2.setStyle("-fx-text-fill:white");
        l2.setFont(new Font(23));
        hBox.getChildren().add(l2);
        Label l3 = null;
        try {
            result = logicOperation.calcuate(nums[0], nums[1]);
            l3 = new Label(" = " + result);
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
        l3.setStyle("-fx-text-fill:white");
        l3.setFont(new Font(23));
        hBox.getChildren().add(l3);
        flowPane.getChildren().add(hBox);

        HBox hBoxButtons = new HBox();
        hBoxButtons.setAlignment(Pos.CENTER);
        for(int i = 0;i<4;i++){
            LogicOperation lo = CreatorLogicOperation.createOperation(i);
            Button btn = new Button(String.valueOf(lo.getOperation()));
            btn.setUserData(i);
            btn.setId("btn-other");
            btn.setTooltip(new Tooltip(lo.getName()));
            btn.setOnAction(this::setSymbol);
            HBox.setMargin(btn, new Insets(0, 10, 0, 10));
            hBoxButtons.getChildren().add(btn);
        }
        flowPane.getChildren().add(hBoxButtons);

        style = false;
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if(style){
                input.setStyle("-fx-border-color: black ;-fx-background-color: black;-fx-text-fill:white;");
            }
        });
        return flowPane;
    }

    @Override
    public int answer() {
        if(input.getText().equals(" ")){
            style = true;
            input.setStyle("-fx-border-color: red ;");
            return -1;
        }
        LogicOperation lo = CreatorLogicOperation.createOperation(indexBtn);
        try {
            if(lo.calcuate(nums[0], nums[1]) == result){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
        return 0;
    }

    public void setSymbol(ActionEvent actionEvent) {
        input.setText(((Button)actionEvent.getSource()).getText());
        indexBtn = (int)((Button)actionEvent.getSource()).getUserData();
    }


}
