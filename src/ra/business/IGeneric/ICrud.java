package ra.business.IGeneric;

import java.util.List;

public interface ICrud <E , T>
{
     void read ();
     void create ();
     void update();
     void delete();
     E finByID(T t);

}
