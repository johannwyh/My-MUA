package src.mua.NameSpace;

import java.util.HashMap;

import src.mua.Values.NUM;
import src.mua.Values.VALUE;
import src.mua.Exception.RuntimeError;

public class Space {
    private HashMap<String, VALUE> ValueMap = new HashMap<String, VALUE>();
    public VALUE RET;
    public boolean is_stopped;
    private Space parent;

    public Space() {
        RET = null;
        is_stopped = false;
        parent = null;
        setHere("pi", new NUM(3.14159));
    }

    public Space(Space parent) {
        RET = null;
        is_stopped = false;
        this.parent = parent;
        setHere("pi", new NUM(3.14159));
    }

    public HashMap<String, VALUE> getLocal() {
        return this.ValueMap;
    }

    public void clearLocal() {
        this.ValueMap.clear();
    }

    public Space getMain() {
        if (parent == null) return this;
        else return parent.getMain();
    }

    public boolean existsHere(String name) {
        return ValueMap.containsKey(name);
    }
    public boolean existsSomewhere(String name) {
        if (existsHere(name)) return true;
        else if (parent != null) return parent.existsSomewhere(name);
        else return false;
    }

    public VALUE getHere(String name) {
        return ValueMap.get(name);
    }
    public VALUE getSomewhere(String name) {
        if (existsHere(name)) return getHere(name);
        else if (parent != null) return parent.getSomewhere(name);
        else return null;
    }

    public void setHere(String name, VALUE val) {
        ValueMap.put(name, val);
    }
    public void setMain(String name, VALUE val) {
        getMain().setHere(name, val);
    }

    public boolean eraseHere(String name) {
        if (existsHere(name)) {
            ValueMap.remove(name);
            return true;
        } else {
            return false;
        }
    }
    public boolean eraseSomewhere(String name) {
        if (eraseHere(name)) return true;
        else if (parent != null) return parent.eraseSomewhere(name);
        else return false;
    }

    public void clear() {
        ValueMap.clear();
    }

    public boolean isStopped() { return is_stopped; }
    public void setStopped(boolean v) { is_stopped = v; }
    public VALUE getRET() throws RuntimeError {
        if (!is_stopped) throw new RuntimeError("** Not Stopped Yet, Cannot Return.");
        else return RET;
    }
    public void setRET(VALUE r) throws RuntimeError {
        if (is_stopped) throw new RuntimeError("** Function has stopped, cannot reset the return value.");
        else RET = r;
    }

    public void debug() {
        for(String key : ValueMap.keySet()) {
            System.out.println(key + " : " + ValueMap.get(key).tostr());
        }
    }
}