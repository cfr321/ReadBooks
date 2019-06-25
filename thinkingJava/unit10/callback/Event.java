package thinkingJava.unit10.callback;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/24 17:36
 * @Description:
 */
public abstract class Event {

    private long shutTime;
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime=delayTime;
    }

    public void start(){
        this.shutTime=System.currentTimeMillis()+delayTime;
    }

    public boolean ready(){
        return System.currentTimeMillis()>=shutTime;
    }

    public abstract void action();
}
