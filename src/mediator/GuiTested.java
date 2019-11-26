package mediator;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GuiTested extends Colleague {

    private Button startPaneButtonStart;
    private Button testPaneButtonAnswer;
    private Button finalPaneCloseBtn;
    public StackPane stackPaneRoot;
    private GridPane testPaneCenterPane;

    public Button getStartPaneButtonStart() {
        return startPaneButtonStart;
    }

    public void setStartPaneButtonStart(Button startPaneButtonStart) {
        this.startPaneButtonStart = startPaneButtonStart;
    }

    public Button getTestPaneButtonAnswer() {
        return testPaneButtonAnswer;
    }

    public void setTestPaneButtonAnswer(Button testPaneButtonAnswer) {
        this.testPaneButtonAnswer = testPaneButtonAnswer;
    }

    public Button getFinalPaneCloseBtn() {
        return finalPaneCloseBtn;
    }

    public void setFinalPaneCloseBtn(Button finalPaneCloseBtn) {
        this.finalPaneCloseBtn = finalPaneCloseBtn;
    }

    public StackPane getStackPaneRoot() {
        return stackPaneRoot;
    }

    public void setStackPaneRoot(StackPane stackPaneRoot) {
        this.stackPaneRoot = stackPaneRoot;
    }

    public GridPane getTestPaneCenterPane() {
        return testPaneCenterPane;
    }

    public void setTestPaneCenterPane(GridPane testPaneCenterPane) {
        this.testPaneCenterPane = testPaneCenterPane;
    }

    public GuiTested(Mediator m) {
        super(m);
    }

    @Override
    public void notify(StyleData sd) {
        if(sd != null){
            startPaneButtonStart.setId(sd.getStartPaneButtonStartId());
            testPaneButtonAnswer.setId(sd.getTestPaneButtonAnswerId());
            finalPaneCloseBtn.setId(sd.getFinalPaneCloseBtnId());
            stackPaneRoot.setStyle(sd.getStackPaneRootColor());
            testPaneCenterPane.setStyle(sd.getTestPaneCenterPaneColor());
        }
    }
}
