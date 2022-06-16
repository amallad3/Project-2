import java.util.ArrayList;
import java.util.Observable;
/*
 *@author Javier Gonzalez-Sanchez
 *This is the pseudo code provided by Professor Javier Gonzalez-Sanchez
 */

public class WhiteBoard extends Observable{
    private WhiteBoard instance;
    private ArrayList<Dot> dots = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();
    public void addDot(Dot d){
        dots.add(d);
        setChange(true);
        notifyObservers();
    }
    public void addLine(Line l){
        lines.add(l);
        setChange(true);
        notifyObservers();
    }

    private WhiteBoard(){}

    public WhiteBoard(){
        if(instance == null){
            instance = new WhiteBoard();
        }
        return instance;
    }
    public ArrayList<> getLines(){

    }

    public ArrayList<> getDots(){

    }
}
