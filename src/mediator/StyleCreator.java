package mediator;

import java.util.HashMap;

public class StyleCreator extends Colleague {



    public StyleCreator(Mediator m) {
        super(m);
        createData();
    }

    @Override
    public void notify(StyleData sd) {
        // not using
    }

    private HashMap<LevelStyle, StyleData> data;

    private void createData(){
        data = new HashMap<LevelStyle, StyleData>()
        {{
            put(LevelStyle.LOW_LEVEL, new StyleData("btn-start-low", "btn-other-low", "btn-start-low",
                    "-fx-background-color: rgb(0,128,0);", "-fx-background-color: rgb(0,128,0);"));
            put(LevelStyle.MEDIUM_LEVEL, new StyleData("btn-start-medium", "btn-other-medium", "btn-start-medium",
                    "-fx-background-color: rgb(128,0,128);", "-fx-background-color: rgb(128,0,128);"));
            put(LevelStyle.HIGH_LEVEL, new StyleData("btn-start-high", "btn-other-high", "btn-start-high",
                    "-fx-background-color: rgb(233,101,8);", "-fx-background-color: rgb(233,101,8);"));
        }};
    }

    public StyleData createStyle(LevelStyle ls){
        StyleData sd = data.get(ls);
        return sd;
    }
}
