package ra.business.IGeneric;

import ra.business.entity.Users;

import java.util.List;

public interface IUsers extends ICrud<Users, Integer>
{
        int MANAGER_CODE =8888;
        int ADMIN_CODE =9999;
        Users login(String username,String password);
        void register();
}
