package service;

public interface Service {
    boolean add(Object o);
    Object getById(int id);
    Object getAll();
    boolean Update();
    boolean delete(int id);
}
