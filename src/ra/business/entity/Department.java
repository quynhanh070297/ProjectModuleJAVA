package ra.business.entity;

import ra.business.config.InputMethods;
import ra.business.implement.DepartmentImplement;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable
{
    private Integer departmentId;
    private String departmentName;
    private String description;

    public Department()
    {
    }

    public Department(Integer departmentId, String departmentName, String description)
    {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
    }

    public Integer getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId)
    {
        this.departmentId = departmentId;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void inputDepartment()
    {

        this.departmentId = getInputDepartmentId(DepartmentImplement.departmentList);
        this.departmentName = getInputDepartmentName(DepartmentImplement.departmentList);
        this.description = getInputDescription();
    }

    public void displayDepartment()
    {
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("|ID : %-3s | Department Name %-15s  | Description %-15s  |\n", departmentId, departmentName,description);
    }

    private Integer getInputDepartmentId(List<Department> departmentList)
    {

        //Nếu list chưa có phần tử nào thì đây chính là phần tử đầu tiên
        if (departmentList.isEmpty())
        {
            return 1;
        } else
        {
            int maxId = departmentList.get(0).getDepartmentId();
            for (Department department : departmentList)
            {
                //Tìm ra id lớn nhất hiện có trong list employee
                if (department.getDepartmentId() > maxId)
                    maxId = department.getDepartmentId();
            }
            return ++maxId;
        }

    }

    private String getInputDepartmentName(List<Department> departmenList)
    {
        System.out.println("moi ban nhap ten phong ban");
        while (true)
        {
            this.departmentName = InputMethods.getString();
            if (!departmentName.trim().isBlank())
            {
                if (departmenList.stream().anyMatch(users -> users.getDepartmentName().equals(departmentName)))
                {
                    System.out.println("Tên của bạn bị trùng, mời bạn nhập lại:");
                } else
                {
                    return departmentName;
                }
            }
            System.err.println("khong duoc de trong");
        }
    }

    private String getInputDescription()
    {
        System.out.println("Moi ban nhap vao mieu ta");
        return InputMethods.getString();
    }

}
