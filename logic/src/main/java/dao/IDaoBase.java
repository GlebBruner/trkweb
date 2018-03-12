package dao;

import java.util.List;

public interface IDaoBase<T> {

    public boolean create(T entity);
    public T read(int id);
    public boolean update(T entity);
    public boolean delete(int id);
    public List<T> getAll() ;
}
