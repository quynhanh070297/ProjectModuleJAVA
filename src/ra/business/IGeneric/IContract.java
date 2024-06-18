package ra.business.IGeneric;

import ra.business.entity.Contract;

public interface IContract extends ICrud<Contract, Integer>
{
    void findContractByName();
}
