package generator_task.logic_opearation.factory_method;

import generator_task.logic_opearation.Implication;
import generator_task.logic_opearation.LogicOperation;

public class CImplication extends CLogicMethod {
    @Override
    public LogicOperation create() {
        return new Implication();
    }
}
