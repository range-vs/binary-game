package generator_task.logic_opearation;

public class Equvalense extends LogicOperation {

    public Equvalense() {
        operation = '\u2192';
        name = "Эквиваленция";
    }

    @Override
    public int calcuate(int a, int b) throws Exception {
        if ((a >= 0 && a <= 1) && (b >= 0 && b <= 1)) {
            if(a == 0 && b == 0) {
                return 1;
            }else if(a == 0 && b == 1) {
                return 0;
            }else if(a == 1 && b == 0) {
                return 0;
            }else if(a == 1 && b == 1) {
                return 1;
            }
        }
        throw new Exception("Неверные входные операнды");
    }
}
