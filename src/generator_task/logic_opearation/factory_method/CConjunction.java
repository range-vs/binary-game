package generator_task.logic_opearation.factory_method;

import generator_task.logic_opearation.Conjunction;
import generator_task.logic_opearation.LogicOperation;

public class CConjunction extends CLogicMethod {
    @Override
    public LogicOperation create() {
        return new Conjunction();
    }
}
