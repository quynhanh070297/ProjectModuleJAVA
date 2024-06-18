package ra.business.IGeneric;

import ra.business.entity.Employee;

public interface IEmployee extends ICrud<Employee,Integer>
{
     void findEmployeeByName();
}
