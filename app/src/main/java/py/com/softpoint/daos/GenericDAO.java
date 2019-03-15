package py.com.softpoint.daos;


/**
*
* @param <T>
*/
public interface GenericDAO<T> {

    public long guardar(T t) throws Exception;
    public boolean modificar (T t) throws Exception;
    public boolean borrar(T t) throws Exception;
    public T buscar(Long id);

}
