package ra.business.entity;

import ra.business.config.InputMethods;
import ra.business.config.Role;

import java.io.Serializable;
import java.time.LocalDate;

import static ra.business.IGeneric.IUsers.ADMIN_CODE;
import static ra.business.IGeneric.IUsers.MANAGER_CODE;
import static ra.business.implement.ContractImplement.contractList;
import static ra.business.implement.EmployeeImplement.employeeList;
import static ra.business.implement.ProjectImplement.projectList;

public class Project implements Serializable
{
    private Integer projectId;
    private String projectName;
    private Integer contractId;
    private Integer leader_id;
    private int totalMember;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;
    private String description;
    private String technology;

    public Project()
    {
    }

    public Project(Integer projectId, String projectName, Integer contractId, Integer leader_id, int totalMember, LocalDate startDate, LocalDate endDate, boolean status, String description, String technology)
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.contractId = contractId;
        this.leader_id = leader_id;
        this.totalMember = totalMember;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
        this.technology = technology;
    }


    public void inputProject()
    {
        this.projectId = getInputIdProject();
        this.projectName = getInputProjectName();
        this.contractId = getInputContractId();
        this.leader_id = getInputLeaderId();
        this.totalMember = getInputTotalMember();
        this.startDate = getInputStartDate();
        this.endDate = getInputEndDate();
        this.status = getInputStatus();
        this.description = getInputDescription();
        this.technology = getInputtechnology();
    }

    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId = projectId;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public Integer getContractId()
    {
        return contractId;
    }

    public void setContractId(Integer contractId)
    {
        this.contractId = contractId;
    }

    public Integer getLeader_id()
    {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id)
    {
        this.leader_id = leader_id;
    }

    public int getTotalMember()
    {
        return totalMember;
    }

    public void setTotalMember(int totalMember)
    {
        this.totalMember = totalMember;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTechnology()
    {
        return technology;
    }

    public void setTechnology(String technology)
    {
        this.technology = technology;
    }

    private String getInputtechnology()
    {
        System.out.println("Moi ban nhap cong nghe su dung :");
        return InputMethods.getString();
    }

    private String getInputDescription()
    {
        System.out.println("Moi ban nhap mieu ta :");
        return InputMethods.getString();
    }

    private boolean getInputStatus()
    {
        System.out.println("Moi ban nhap trang thai hop dong");
        return InputMethods.getBoolean();
    }

    private LocalDate getInputEndDate()
    {
        System.out.println("Moi ban nhap ngay ket thuc");
        return InputMethods.getLocalDate();
    }

    private LocalDate getInputStartDate()
    {
        System.out.println("Moi ban nhap ngay bat dau");
        return InputMethods.getLocalDate();
    }

    private int getInputTotalMember()
    {
        System.out.println("Moi ban nhap tong so nhan vien tham gia");
        return InputMethods.getInteger();
    }

    private Integer getInputLeaderId()
    {
        System.out.println("Danh sách nhân viên");
        employeeList.forEach(Employee::displayEmployee);
        System.out.println("Mời bạn chọn ID nhân viên muốn nhập ");
        int inputId = InputMethods.getInteger();
        return employeeList.stream().filter(employee -> employee.getEmployeeId() == inputId).findFirst().orElse(null).getEmployeeId();

    }

    private Integer getInputContractId()
    {
        System.out.println("Danh sach hop dong :");
        contractList.forEach(Contract::displayContract);
        System.out.println("Mời nhập ID ");
        int inputId = InputMethods.getInteger();
        return contractList.stream().filter(contract -> contract.getContractId() == inputId).findFirst().orElse(null).getContractId();
    }

    private String getInputProjectName()
    {// Khong duoc de trong
        System.out.println("Moi ban nhap ten du an");
        return InputMethods.getString();
    }


    public void displayProject()
    {
        System.out.printf("------------------------------------------------\n" +
                        "| Project ID:       %-15d|\n" +
                        "| Project Name:     %-15s|\n" +
                        "| Contract ID:      %-15d|\n" +
                        "| Leader ID:        %-15d|\n" +
                        "| Total Members:    %-15d|\n" +
                        "| Start Date:       %-15s|\n" +
                        "| End Date:         %-15s|\n" +
                        "| Status:           %-15s|\n" +
                        "| Description:      %-15s|\n" +
                        "| Technology:       %-15s|\n" +
                        "------------------------------------------------\n",
                this.projectId, this.projectName, this.contractId, this.leader_id, this.totalMember,
                this.startDate, this.endDate, this.status ? "Active" : "Waiting", this.description, this.technology);
    }

    ;

    public Integer getInputIdProject()
    {
        //ID tu tang
        int max = projectList.stream().mapToInt(Project::getProjectId).max().orElse(0);
        return max + 1;
    }


}
