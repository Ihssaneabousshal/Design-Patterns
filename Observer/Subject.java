import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Subject extends Observable implements Runnable  {

    // this line is no longer necessary as Observable class already contains a list to store observers
    // private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public Subject(int val) {
        this.setState(val);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // replace notifyAllObservers with setChanged() + notifyObservers(state);
        // notifyAllObservers();
        setChanged();
        notifyObservers(this.getState());
    }

    public void subscribe(Observer observer){
        this.addObserver(observer);
        // Observable class already implements addObserver method
        // observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        this.deleteObserver(observer);
        // Observable class already implements removeObserver method
        //observers.remove(observer);
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                this.setState((this.getState() + 1) % 60);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            return;
        }
    }


}
