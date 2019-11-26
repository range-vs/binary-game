package mediator;

public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator m){
        mediator = m;
    }

    public void send(LevelStyle ls){
        mediator.send(ls, this);
    }

    public abstract void notify(StyleData sd);
}
