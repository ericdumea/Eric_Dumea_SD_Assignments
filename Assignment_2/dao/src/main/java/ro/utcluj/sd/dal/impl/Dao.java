package ro.utcluj.sd.dal.impl;

public interface Dao <T> {

    T find(int id);

    void delete(T objectToDelete);

    void update(T objectToUpdate);

    int insert(T objectToCreate);

    void deleteById(int id);

}
