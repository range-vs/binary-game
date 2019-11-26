package mediator;

public class StyleData {

    private String startPaneButtonStartId;
    private String testPaneButtonAnswerId;
    private String finalPaneCloseBtnId;
    private String stackPaneRootColor;
    private String testPaneCenterPaneColor;

    public StyleData(String startPaneButtonStartId, String testPaneButtonAnswerId, String finalPaneCloseBtnId, String stackPaneRootColor, String testPaneCenterPaneColor) {
        this.startPaneButtonStartId = startPaneButtonStartId;
        this.testPaneButtonAnswerId = testPaneButtonAnswerId;
        this.finalPaneCloseBtnId = finalPaneCloseBtnId;
        this.stackPaneRootColor = stackPaneRootColor;
        this.testPaneCenterPaneColor = testPaneCenterPaneColor;
    }

    public String getStartPaneButtonStartId() {
        return startPaneButtonStartId;
    }

    public void setStartPaneButtonStartId(String startPaneButtonStartId) {
        this.startPaneButtonStartId = startPaneButtonStartId;
    }

    public String getTestPaneButtonAnswerId() {
        return testPaneButtonAnswerId;
    }

    public void setTestPaneButtonAnswerId(String testPaneButtonAnswerId) {
        this.testPaneButtonAnswerId = testPaneButtonAnswerId;
    }

    public String getFinalPaneCloseBtnId() {
        return finalPaneCloseBtnId;
    }

    public void setFinalPaneCloseBtnId(String finalPaneCloseBtnId) {
        this.finalPaneCloseBtnId = finalPaneCloseBtnId;
    }

    public String getStackPaneRootColor() {
        return stackPaneRootColor;
    }

    public void setStackPaneRootColor(String stackPaneRootColor) {
        this.stackPaneRootColor = stackPaneRootColor;
    }

    public String getTestPaneCenterPaneColor() {
        return testPaneCenterPaneColor;
    }

    public void setTestPaneCenterPaneColor(String testPaneCenterPaneColor) {
        this.testPaneCenterPaneColor = testPaneCenterPaneColor;
    }
}
