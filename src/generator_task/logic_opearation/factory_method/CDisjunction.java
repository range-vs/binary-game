package generator_task.logic_opearation.factory_method;

import generator_task.logic_opearation.Disjunction;
import generator_task.logic_opearation.LogicOperation;

public class CDisjunction extends CLogicMethod {
    @Override
    public LogicOperation create() {
        return new Disjunction();
    }
}
