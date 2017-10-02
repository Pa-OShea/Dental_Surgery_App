package Models;

import java.io.Serializable;
import java.util.Objects;

public class Procedure implements Serializable, Comparable {
    private static int nextProcNo = 1;
    private int procNo;
    private String procName;
    private double procCost;

    public Procedure(){
        this.procNo = nextProcNo++;
    };

    public Procedure(String procName, double procCost){
        this.procNo = nextProcNo++;
        setProcName(procName);
        setProcCost(procCost);
    }

    public int getProcNo() {
        return procNo;
    }

    public void setProcNo(int procNo){
        this.procNo = procNo;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public double getProcCost() {
        return procCost;
    }

    public void setProcCost(double procCost) {
        this.procCost = procCost;
    }

    public String toString(){
        return getProcName();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Procedure){
            Procedure check = (Procedure) o;
            if (Objects.equals(check.getProcName(), this.getProcName())){
                return 0; //true
            }else {
                return 1;
            }
        }else{
            throw new ClassCastException("Not a Procedure");
        }
    }
}
