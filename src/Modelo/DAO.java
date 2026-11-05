package Modelo; // Updated package

import java.util.List;

/**
 * Generic interface for Data Access Objects (DAO).
 */
public interface DAO<T> {
    T getById(String id);
    List<T> getAll();
    void save(T t);
    void delete(T t);
}