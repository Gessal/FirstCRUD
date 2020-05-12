package dao;

import java.util.List;

public interface CrudDAO<T> {
    T find(Long id);
    void save (T model);
    void update (T model);
    void delete (Long id);
    List<T> findAll();
}
