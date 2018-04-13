package framework.common;

public interface LogFunctor<T,E,R> {
    public void preMessage(T action);
    public  void postMessage(E element1, R element2);
}
