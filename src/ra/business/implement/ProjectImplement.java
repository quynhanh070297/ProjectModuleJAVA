package ra.business.implement;

import ra.business.IGeneric.IProject;
import ra.business.config.InputMethods;
import ra.business.entity.Department;
import ra.business.entity.Employee;
import ra.business.entity.Project;
import ra.data.IOFile;

import java.util.ArrayList;
import java.util.List;

import static ra.business.implement.ContractImplement.contractList;
import static ra.business.implement.EmployeeImplement.employeeList;

public class ProjectImplement implements IProject
{
    public static List<Project> projectList;

    static
    {
        projectList = IOFile.readObjectFromFile(IOFile.PATH_PROJECT);
    }

    @Override
    public void read()
    {
        if (projectList.isEmpty())
        {
            System.out.println("Hiện chưa có dự án nào");
        } else
        {
            System.out.println("Danh sách dự án :");
            projectList.forEach(Project::displayProject);

        }

    }

    @Override
    public void create()
    {
        System.out.println("Mời bạn nhập vào số Dự án ban muốn thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 0; i < quantity; i++)
        {
            Project project = new Project();
            project.inputProject();
            projectList.add(project);

        }
        IOFile.writeObjectToFile(projectList,IOFile.PATH_PROJECT);
    }
    public void update()
    {
        if (projectList.isEmpty())
        {
            System.out.println("ban chua co du an nao");
        } else
        {
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
                        System.out.println("Chọn trường bạn cần sửa :\n" +
                                "|1: Tên Dự Án           |\n" +
                                "|2: ID hợp đồng         |\n" +
                                "|3: ID Leader           |\n" +
                                "|4: Tổng số nhân viên   |\n" +
                                "|5: Ngày bắt đầu        |\n" +
                                "|6: Ngày kết thúc       |\n" +
                                "|7: Trạng thái          |\n" +
                                "|8: Miêu tả             |\n" +
                                "|9: Công nghệ sư dụng   |\n" +
                                "|0: Thoát               |");
                        System.out.println("mời bạn chọn");
                        byte choice = InputMethods.getByte();
                        switch (choice)
                        {
                            case 1:
                                System.out.println("Mời bạn nhập tên dự án mới :");
                                finByID(inputIdUpdate).setProjectName(InputMethods.getString());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 2:
                                System.out.println("Mời bạn nhập ID hợp đồng mới :");
                                finByID(inputIdUpdate).setDescription(updateNewProjectId());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 3:
                                System.out.println("Mời bạn nhập Leader mới :");
                                finByID(inputIdUpdate).setLeader_id(updateNewEmployeeId());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 4:
                                System.out.println("Mời bạn nhập tổng số nhân viên mới :");
                                finByID(inputIdUpdate).setTotalMember(InputMethods.getInteger());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 5:
                                System.out.println("Mời bạn nhập ngày bắt đầu mới :");
                                finByID(inputIdUpdate).setStartDate(InputMethods.getLocalDate());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 6:
                                System.out.println("Mời bạn nhập ngày kết thúc mới :");
                                finByID(inputIdUpdate).setEndDate(InputMethods.getLocalDate());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 7:
                                System.out.println("Mời bạn nhập trạng thái mới :");
                                finByID(inputIdUpdate).setStatus(InputMethods.getBoolean());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 8:
                                System.out.println("Mời bạn nhập công nghệ sư dụng mới :");
                                finByID(inputIdUpdate).setTechnology(InputMethods.getString());
                                System.out.println("Cập nhật thành công");
                                break;
                            case 0:
                                IOFile.writeObjectToFile(projectList, IOFile.PATH_PROJECT);
                                isExit = false;
                                break;
                            default:
                                System.err.println("lựa chọn sai ");
                        }
                    }
                }
            }
        }
    }

    private Integer updateNewEmployeeId()
    {
        System.out.println("Danh sach nhân viên");
        employeeList.forEach(Employee::displayEmployee);
        System.out.println("Mời bạn nhập id nhân viên mới");
        do
        {
            for (Employee employee : employeeList)
            {
                if (InputMethods.getInteger() == employee.getEmployeeId())
                {
                    return employee.getEmployeeId();
                }
            }
            System.out.println("Nhap sai moi nhap lai");
        } while (true);
    }

    private String updateNewProjectId()
    {
        System.out.println("Danh sach dự án");
        projectList.forEach(Project::displayProject);
        System.out.println("Mời bạn nhập tên dự án mới");
        do
        {
            for (Project project : projectList)
            {
                if (InputMethods.getInteger() == project.getProjectId())
                {
                    return project.getProjectName();
                }
            }
            System.out.println("Nhap sai moi nhap lai");
        } while (true);
    }


    @Override
    public void delete()
    {
        if (projectList.isEmpty())
        {
            System.out.println("ban chua co du an nao");
        } else
        {
            boolean isExit = true;
            read();
            while (isExit)
            {
                System.out.println("Mời bạn nhập vào ID muốn xóa");
                int idDelete = InputMethods.getInteger();

                if (finByID(idDelete) == null)
                {
                    System.out.println("Id bạn nhập vào chưa đúng");
                } else
                {
                    projectList.remove(finByID(idDelete));
                    IOFile.writeObjectToFile(projectList, IOFile.PATH_PROJECT);
                    isExit = false;
                }
            }
        }
    }

    @Override
    public Project finByID(Integer integer)
    {
        return projectList.stream().filter(project -> project.getProjectId() == integer).findFirst().orElse(null);
    }

    @Override
    public void updateProjectStatus()
    {
        if (projectList.isEmpty())
        {
            System.out.println(" Chua co du an nao");
        }
        else
        {

            System.out.println("Danh sách dự án hiện tại");
            projectList.forEach(Project::displayProject);

            System.out.println("Mời bạn nhập IDProject bạn muốn update trạng thái");
            int updateId = InputMethods.getInteger();
            boolean isExit = true;
            read();
            while (isExit)
            {
                for (Project project : projectList)
                {
                    if (project.getProjectId() == updateId)
                    {
                        System.out.println("Mời nhập trạng thái muốn update: ");
                        project.setStatus(InputMethods.getBoolean());
                        System.out.println("Update thành công !");
                        IOFile.writeObjectToFile(projectList,IOFile.PATH_PROJECT);
                        isExit = false;
                    }
                }
                System.out.println("Không tồn tại ID, hoặc nhập sai ID");
            }
        }
    }
}
