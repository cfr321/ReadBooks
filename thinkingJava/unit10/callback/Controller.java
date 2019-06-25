package thinkingJava.unit10.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/24 17:40
 * @Description:
 */
public class Controller {
    private List<Event> eventList=new ArrayList<>();

    public void addEvent(Event event){
        eventList.add(event);
    }
    public void run(){
        while (eventList.size()>0){
            for (Event event: new ArrayList<>(eventList)){
                if (event.ready()) {
                    event.action();
                    eventList.remove(event);
                }
            }
        }
    }
}
