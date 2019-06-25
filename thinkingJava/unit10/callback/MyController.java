package thinkingJava.unit10.callback;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/24 17:57
 * @Description:
 */
public class MyController extends Controller {
    private boolean light=false;

    public static void main(String[] args) {
        MyController myController=new MyController();
        Event[] events={
        myController.new LightOn(200),
        myController.new LightOff(300)
        };
        myController.addEvent(myController.new Restart(1000,events));
        myController.run();
    }

    public class Restart extends Event{

        Event[] events;

        public Restart(long delayTime, Event[] events) {
            super(delayTime);
            this.events = events;
        }

        @Override
        public void action() {
            for (Event event : events) {
                event.start();
                addEvent(event);
            }
            start();
            addEvent(this);
        }
    }
    public class LightOn extends  Event{
        public LightOn(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            light=true;
            System.out.println("light on");
        }
    }

    public class LightOff extends Event{

        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light=false;
            System.out.println("light off");
        }
    }
}
