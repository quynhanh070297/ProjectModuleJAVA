package ra.business.IGeneric;

import ra.business.entity.Customer;
import ra.business.entity.Project;

public interface ICustomer extends ICrud<Customer,Integer>
{
    void findCustomerByName();

   void readContract();
}
