package mediator;

public class StyleGuiMediator implements Mediator {

    private StyleCreator styleCreator;
    private GuiTested guiTested;

    public StyleCreator getStyleCreator() {
        return styleCreator;
    }

    public void setStyleCreator(StyleCreator styleCreator) {
        this.styleCreator = styleCreator;
    }

    public GuiTested getGuiTested() {
        return guiTested;
    }

    public void setGuiTested(GuiTested guiTested) {
        this.guiTested = guiTested;
    }

    @Override
    public void send(LevelStyle ls, Colleague coll) {
        if(coll == guiTested){
            StyleData sd = styleCreator.createStyle(ls);
            guiTested.notify(sd);
        }else{
            // not using
        }
    }
}
