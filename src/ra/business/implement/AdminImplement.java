package ra.business.implement;

import ra.business.IGeneric.IAdmin;
import ra.business.config.InputMethods;
import ra.business.entity.Contract;
import ra.business.entity.Employee;
import ra.business.entity.Project;
import ra.business.entity.Users;
import ra.data.IOFile;

import static ra.business.implement.ContractImplement.contractList;
import static ra.business.implement.CustomerImplement.customerList;
import static ra.business.implement.ProjectImplement.projectList;
import static ra.business.implement.UsersImplement.userList;

public class AdminImplement implements IAdmin
{
    @Override
    public void addUser()
    {
        UsersImplement usersImplement = new UsersImplement();
        usersImplement.create();


    }

    @Override
    public void displayUser()
    {
        System.out.println("Danh sach nguoi dung hien tai");
        IOFile.readObjectFromFile(IOFile.USER_PATH);
        EmployeeImplement.employeeList.forEach(Employee::displayEmployee);

    }

    @Override
    public void openOrBlockUser()
    {
        System.out.println("Danh sách người dùng hiện tại");
        userList.forEach(Users::displayUser);

        System.out.println("Mời bạn nhập ID Người dùng bạn muốn update trạng thái");
        int updateId = InputMethods.getInteger();
        boolean isExit = true;
            for (Users users : userList)
            {
                if (users.getUserId() == updateId)
                {
                    System.out.println("Mời nhập trạng thái muốn update: ");
                    users.setStatus(InputMethods.getBoolean());
                    System.out.println("Update thành công !");
                    IOFile.writeObjectToFile(userList,IOFile.USER_PATH);
                    isExit = false;
                    break;
                }
            }
            if (isExit){
                System.out.println("Không tồn tại ID, hoặc nhập sai ID");
            }
    }

    @Override
    public void statisticsCustomers()
    {

        System.out.println("So khach hang hien tai la :" + customerList.size());
    }

    @Override
    public void statisticsContract()
    {

        System.out.println("So hop dong da ky la" + contractList.size());
    }

    @Override
    public void displayProjectByContract()
    {
        System.out.println("Mời bạn nhập ID hợp đồng muốn xem dự án ");
        int contractId = InputMethods.getInteger();
        for (Contract contract : contractList)
        {
            if (contract.getContractId().equals(contractId))
            {
                for (Project project : projectList)
                {
                    if (project.getContractId().equals(contractId)){
                        System.out.println("Các dự án thuộc Hợp đồng " + contractId +":");
                        project.displayProject();
                    }

                }
            }
        }
        if (contractList.stream().noneMatch(contract->contract.getContractId().equals(contractId))){
            System.out.println(" Khong co hop dong nao co so ID nay");
        }
    }
}

