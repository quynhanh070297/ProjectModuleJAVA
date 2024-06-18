package ra.business.implement;

import ra.business.IGeneric.IContract;
import ra.business.config.InputMethods;
import ra.business.entity.Contract;
import ra.business.entity.Customer;
import ra.business.entity.Department;
import ra.business.entity.Users;
import ra.data.IOFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ra.business.implement.CustomerImplement.customerList;
import static ra.business.implement.UsersImplement.userList;

public class ContractImplement implements IContract
{
    public static List<Contract> contractList;

    static
    {
        contractList = IOFile.readObjectFromFile(IOFile.PATH_CONTRACT);
    }

    @Override
    public void read()
    {if (contractList.isEmpty()){
        System.out.println("chua co hop dong nao dc ki");
    }else {
        System.out.println("Danh sach hop dong la : ");
        contractList.forEach(Contract::displayContract);
    }}

    @Override
    public void create()
    {
        Contract contract = new Contract();
        contract.inputContract();
        contractList.add(contract);
        contract.autoSetPriority();
        IOFile.writeObjectToFile(contractList, IOFile.PATH_CONTRACT);

    }

    @Override
    public void update()
    {if (contractList.isEmpty()){
        System.out.println("chua co hop dong nao dc ki");
    }else {

        boolean isExit = true;
        System.out.println("Danh sach");
        contractList.forEach(Contract::displayContract);
        while (isExit)
        {
            System.out.println("Mời bạn nhập vào ID muốn sửa");
            int inputIdUpdate = InputMethods.getInteger();
            if (finByID(inputIdUpdate) == null)
            {
                System.out.println("ID bạn nhập vào không tồn tại");
            } else
            {
                boolean isSubMenuExit = true;
                while (isSubMenuExit)
                {
                    System.out.println("Chọn trường bạn cần sửa \n" +
                            "| 1 : Description                  |\n" +
                            "| 2 : Contract Name                |\n" +
                            "| 3 : Employee ID                  |\n" +
                            "| 4 : Customer ID                  |\n" +
                            "| 5 : Created Date                 |\n" +
                            "| 6 : Expiry Date                  |\n" +
                            "| 7 : Total Amount                 |\n" +
                            "| 0 : Thoát                        |");
                    System.out.println("Mời bạn chọn");
                    byte choice = InputMethods.getByte();
                    switch (choice)
                    {
                        case 1:
                            System.out.println("Mời bạn nhập Description mới :");
                            finByID(inputIdUpdate).setDescription(InputMethods.getString());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 2:
                            System.out.println("Mời bạn nhập Contract Name mới :");
                            finByID(inputIdUpdate).setContractName(InputMethods.getString());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 3:
                            System.out.println("Mời bạn nhập Employee ID mới :");
                            finByID(inputIdUpdate).setEmployeeId(updateEmployeeID());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 4:
                            System.out.println("Mời bạn nhập Customer ID mới :");
                            finByID(inputIdUpdate).setCustomerId(updateCustomerID());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 5:
                            System.out.println("Mời bạn nhập Created Date mới :");
                            finByID(inputIdUpdate).setCreatedDate(InputMethods.getLocalDate());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 6:
                            System.out.println("Mời bạn nhập Expiry Date mới :");
                            finByID(inputIdUpdate).setExpiryDate(InputMethods.getLocalDate());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 7:
                            System.out.println("Mời bạn nhập Total Amount mới :");
                            finByID(inputIdUpdate).setTotalAmount(InputMethods.getFloat());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 0:
                            finByID(inputIdUpdate).autoSetPriority();
                            IOFile.writeObjectToFile(contractList,IOFile.PATH_CONTRACT);
                            isSubMenuExit = false;

                            break;
                        default:
                            System.err.println("Lựa chọn sai ");
                    }
                }
            }
            System.out.println("Bạn muốn tiếp tục cập nhật? (Nhập '1' để tiếp tục, '0' để thoát)");
            int continueChoice = InputMethods.getInteger();
            if (continueChoice == 0)
            {
                isExit = false;
            }

        }
        IOFile.writeObjectToFile(contractList, IOFile.PATH_CONTRACT);}
    }

    private Integer updateCustomerID()
    {
        System.out.println("Danh sach khach hang");
        customerList.forEach(Customer::displayCustomer);
        System.out.println("Moi ban nhap id khach hang moi");
        do
        {
            for (Customer customer : customerList)
            {
                if (InputMethods.getInteger() == customer.getCustomerId())
                {
                    return customer.getCustomerId();
                }
            }
            System.out.println("Nhap sai moi nhap lai");
        } while (true);
    }

    private Integer updateEmployeeID()
    {
        System.out.println("Danh sach Nhan vien");
        userList.forEach(Users::displayUser);
        System.out.println("Moi ban nhap id khach hang moi");
        do
        {
            for (Users users : userList)
            {
                if (InputMethods.getInteger() == users.getUserId())
                {
                    return users.getUserId();
                }
            }
            System.out.println("Nhap sai moi nhap lai");
        } while (true);
    }

    @Override
    public void delete()
    {
        //Dự án không nên xoá.
    }

    @Override
    public Contract finByID(Integer integer)
    {

        return contractList.stream().filter(contract -> Objects.equals(contract.getContractId(), integer)).findFirst().orElse(null);
    }

    @Override
    public void findContractByName()
    {
        // Tìm hợp đồng ưu tiên
        System.out.println("Nhập tên hợp đồng muốn tìm ");
        String contractName = InputMethods.getString();
        boolean check = false;
        for (Contract contract : contractList)
        {
            if (contract.getContractName().contains(contractName))
            {
                System.out.println("Danh sách hợp dồng");
                contract.displayContract();
                check = true;
            }
        }
        if (!check)
        {
            System.out.println("Không có hợp đồng muốn tìm");
        }
    }
}
