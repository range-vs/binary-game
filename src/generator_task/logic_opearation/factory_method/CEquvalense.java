package generator_task.logic_opearation.factory_method;

import generator_task.logic_opearation.Equvalense;
import generator_task.logic_opearation.LogicOperation;

public class CEquvalense extends CLogicMethod {
    @Override
    public LogicOperation create() {
        return new Equvalense();
    }
}
