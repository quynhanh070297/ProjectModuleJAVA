package ra.business.implement;

import ra.business.IGeneric.IDepartment;
import ra.business.config.InputMethods;
import ra.business.entity.Department;
import ra.business.entity.Employee;
import ra.business.entity.Users;
import ra.data.IOFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ra.business.implement.ContractImplement.contractList;
import static ra.business.implement.EmployeeImplement.employeeList;

public class DepartmentImplement implements IDepartment
{
    public static List<Department> departmentList;

    static
    {
        departmentList = IOFile.readObjectFromFile(IOFile.PATH_DEPARTMENT);
    }


    @Override
    public void read()
    {
        System.out.println("Danh sach phong ban");
        departmentList.forEach(Department::displayDepartment);

    }

    @Override
    public void create()
    {
        System.out.println("Mời bạn nhập vào số phong ban muốn thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 0; i < quantity; i++)
        {
            Department department = new Department();
            department.inputDepartment();
            departmentList.add(department);
            IOFile.writeObjectToFile(departmentList,IOFile.PATH_DEPARTMENT);
        }

    }

    @Override
    public void update()
    {
        boolean isExit = true;
        while (isExit)
        {
            departmentList.forEach(Department::displayDepartment);
            System.out.println("Mời bạn nhập vào ID muốn sửa");
            int inputIdUpdate = InputMethods.getInteger();
            if (finByID(inputIdUpdate) == null)
            {
                System.out.println("id bạn nhập vào chưa đúng");
            } else
            {
                while (isExit)
                {
                    System.out.println("Chọn trường bạn cần sửa :\n" +
                            "|1: Tên Phòng Ban       |\n" +
                            "|2: Miêu tả             |\n" +
                            "|0: Thoát               |");
                    System.out.println("mời bạn chọn");
                    byte choice = InputMethods.getByte();
                    switch (choice)
                    {
                        case 1:
                            System.out.println("Mời bạn nhập tên phòng ban mới :");
                            finByID(inputIdUpdate).setDepartmentName(InputMethods.getString());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 2:
                            System.out.println("Mời bạn nhập miêu tả mới :");
                            finByID(inputIdUpdate).setDescription(InputMethods.getString());
                            System.out.println("Cập nhật thành công");
                            break;
                        case 0:
                            IOFile.writeObjectToFile(departmentList,IOFile.PATH_DEPARTMENT);
                            isExit = false;
                            break;
                        default:
                            System.err.println("lựa chọn sai ");
                    }
                }
            }
        }
    }

    @Override
    public void delete()
    {
        System.out.println("Mời bạn nhập vào ID muốn xóa");
            int IDDelete = InputMethods.getInteger();

            if (finByID(IDDelete) == null)
            {
                System.out.println("Id bạn nhập vào chưa đúng");
            } else
            {
                if (employeeList.stream().anyMatch(contract -> contract.getDepartmentId().equals(finByID(IDDelete).getDepartmentId()))){
                    System.out.println("Không thể xoá phòng ban đã có nhân viên");
                }else{
                departmentList.remove(finByID(IDDelete));
                System.out.println("Xoa thanh cong");
                IOFile.writeObjectToFile(departmentList,IOFile.PATH_DEPARTMENT);}
            }
    }

    @Override
    public Department finByID(Integer inputId)
    {
        for (Department department : departmentList)
        {
            if (Objects.equals(inputId, department.getDepartmentId()))
            {
                return department;
            }
        }
        return null;
    }
}
