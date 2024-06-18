package ra.business.implement;

import ra.business.IGeneric.IEmployee;
import ra.business.config.InputMethods;
import ra.business.entity.Department;
import ra.business.entity.Employee;
import ra.business.entity.Project;
import ra.business.entity.Users;
import ra.data.IOFile;

import java.util.ArrayList;
import java.util.List;

public class EmployeeImplement implements IEmployee
{
    public static List<Employee> employeeList;

    static
    {
        employeeList = IOFile.readObjectFromFile(IOFile.PATH_EMPLOYEE);
    }

    @Override
    public void read()
    {
        if (employeeList.isEmpty()){
            System.out.println("Danh sách nhân viên trống");
        }else {
        System.out.println("Danh sach nhân viên : ");
        employeeList.forEach(Employee::displayEmployee);
    }}

    @Override
    public void create()
    {
        System.out.println("Mời bạn nhập vào số NV ban muốn thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 0; i < quantity; i++)
        {
            Employee employee = new Employee();
            employee.inputEmployee();
            employeeList.add(employee);
            IOFile.writeObjectToFile(employeeList,IOFile.PATH_EMPLOYEE);
            System.out.println("them moi thanh cong");
        }
    }

    @Override
    public void update()
    {
        if (employeeList.isEmpty()){
            System.out.println("danh sach trong");
        }else {

        boolean isExit = true;
        while (isExit)
        {
            System.out.println("Mời bạn nhập vào ID muốn sửa");
            int inputIdUpdate = InputMethods.getInteger();
            if (finByID(inputIdUpdate) == null)
            {
                System.out.println("id bạn nhập vào chưa đúng");
            } else
            {

                while (isExit)
                {
                    System.out.println("chọn trường bạn cần sửa \n" +
                            "|1: Name         |\n" +
                            "|2: DepartmentId |\n" +
                            "|3: Birthday     |\n" +
                            "|4: Sex          |\n" +
                            "|5: DreatedDate  |\n" +
                            "|6: Salary       |\n" +
                            "|0: Thoát        |");
                    System.out.println("mời bạn chọn");
                    byte choice = InputMethods.getByte();
                    switch (choice)
                    {
                        case 1:
                            System.out.println("Moi ban nhap ten moi :");
                            finByID(inputIdUpdate).setEmployeeName(InputMethods.getString());
                            System.out.println("Update Thanh cong");
                            break;
                        case 2:
                            finByID(inputIdUpdate).setDepartmentId(updateDepartmentID());
                            System.out.println("Update Thanh cong");
                            break;
                        case 3:
                            System.out.println("Moi ban nhap gioi tinh muon update |True - Nam | False - Nu|");
                            finByID(inputIdUpdate).setSex(InputMethods.getBoolean());
                            System.out.println("Update Thanh cong");
                            break;
                        case 4:
                            System.out.println("Moi ban nhap ngay sinh muon update");
                            finByID(inputIdUpdate).setBirthday(InputMethods.getLocalDate());
                            System.out.println("Update Thanh cong");
                            break;
                        case 5:
                            System.out.println("Moi ban nhap ngay tham gia muon update");
                            finByID(inputIdUpdate).setCreatedDate(InputMethods.getLocalDate());
                            System.out.println("Update Thanh cong");
                            break;
                        case 6:
                            System.out.println("Moi ban nhap luong muon update");
                            finByID(inputIdUpdate).setSalary(InputMethods.getFloat());
                            System.out.println("Update Thanh cong");
                            break;
                        case 0:
                            IOFile.writeObjectToFile(employeeList,IOFile.PATH_EMPLOYEE);
                            System.out.println("up date thanh cong");
                            isExit = false;
                            break;
                        default:
                            System.err.println("lựa chọn sai ");
                    }
                }
            }
        }

    }}

    private Integer updateDepartmentID()
    {
        System.out.println("Danh sach phong ban");
        DepartmentImplement.departmentList.forEach(Department::displayDepartment);
        System.out.println("Moi ban nhap id phong ban moi");
        do
        {
            for (Department department : DepartmentImplement.departmentList)
            {
                if (InputMethods.getInteger() == department.getDepartmentId())
                {
                    return department.getDepartmentId();
                }
            }
            System.out.println("Nhap sai moi nhap lai");
        } while (true);
    }

    @Override
    public void delete()
    {
        if (employeeList.isEmpty())
        {
            System.out.println("danh sach trong");
        } else
        {
            boolean isExit = true;
            while (isExit)
            {
                employeeList.forEach(Employee::displayEmployee);
                System.out.println("Mời bạn nhập vào ID muốn xóa");
                int IDDelete = InputMethods.getInteger();

                if (finByID(IDDelete) == null)
                {
                    System.out.println("Id bạn nhập vào chưa đúng");
                } else
                {
                    employeeList.remove(finByID(IDDelete));
                    IOFile.writeObjectToFile(employeeList, IOFile.PATH_EMPLOYEE);
                    System.out.println("xoa thanh cong");
                    isExit = false;
                }
            }

        }
    }
    @Override
    public Employee finByID(Integer inputId)
    {

        for (Employee employee : employeeList)
        {
            if (inputId.equals(employee.getEmployeeId()))
            {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void findEmployeeByName()
    {
        System.out.println("Moi ban nhap ten nhan vien muon tim : ");
        String inputName = InputMethods.getString();
        boolean check = false;
        for (Employee employee : employeeList)
        {
            if (employee.getEmployeeName().contains(inputName))
            {
                employee.displayEmployee();
                check = true;
            }
        }
        if (!check)
        {
            System.out.println("khong tim thay ten muon tim");
        }
    }
}


