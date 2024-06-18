package ra.business.implement;

import org.mindrot.jbcrypt.BCrypt;
import ra.business.IGeneric.IUsers;
import ra.business.config.InputMethods;
import ra.business.config.Role;
import ra.business.entity.Contract;
import ra.business.entity.Customer;
import ra.business.entity.Project;
import ra.business.entity.Users;
import ra.data.IOFile;
import ra.run.menuUser.MainMenu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ra.business.implement.ContractImplement.contractList;
import static ra.business.implement.CustomerImplement.customerList;
import static ra.business.implement.ProjectImplement.projectList;
import static ra.run.menuUser.MainMenu.currentUser;
import static ra.run.menuUser.MainMenu.user;

public class UsersImplement implements IUsers
{
    public static byte choice()
    {
        System.out.println("Mời bạn nhập lựa chọn :");
        return InputMethods.getByte();
    }
    // Hàm chọn dùng chung cho tất cả

    public static List<Users> userList;

    static
    {

        userList = IOFile.readObjectFromFile(IOFile.USER_PATH);

    }

    @Override
    public Users login(String username, String password)
    {
        Users userLogin = getUserFromUsername(username);
        if (userLogin == null)
        {
            return null;
        }
        boolean checkLogin = BCrypt.checkpw(password, userLogin.getPassword());
        // kiem tra mat khau khop hay khong
        if (checkLogin)
        {
            return userLogin;
        }
        return null;
    }

    @Override
    public void register()
    {
        Users user = new Users();
        user.inputUser();
        System.out.println("Dang ky thanh cong");
        userList.add(user);
        IOFile.writeObjectToFile(userList, IOFile.USER_PATH);
    }

    private Users getUserFromUsername(String username)
    {
        return userList.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public void read()
    {
        System.out.println("Danh sach nguoi dung la : ");
        userList.forEach(Users::displayUser);
    }

    @Override
    public void create()
    {
        System.out.println("Mời bạn nhập vào số nguoi dung muốn thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 0; i < quantity; i++)
        {
            Users user = new Users();
            user.inputUser();
            userList.add(user);
            IOFile.writeObjectToFile(userList, IOFile.USER_PATH);
        }
    }

    @Override
    public void update()
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

                    System.out.println("chọn trường bạn cần sửa \n" +
                            "|1: Tên        |\n" +
                            "|2: Mật khẩu   |\n" +
                            "|3: Quyền      |\n" +
                            "|4: Trạng thái |\n" +
                            "|0: Thoát      |");
                    System.out.println("mời bạn chọn");
                    byte choice = InputMethods.getByte();
                    switch (choice)
                    {
                        case 1:
                            System.out.println("Moi ban nha ten moi :");
                            finByID(inputIdUpdate).setUsername(InputMethods.getString());
                            break;
                        case 2:
                            finByID(inputIdUpdate).setPassword(InputMethods.getString());
                            break;
                        case 3:
                            // finByID(inputIdUpdate).setRole();
                            break;
                        case 4:
                            finByID(inputIdUpdate).setStatus(InputMethods.getBoolean());
                            break;
                        case 0:
                            isExit = false;
                            break;
                        default:
                            System.err.println("lựa chọn sai ");
                    }
                }
            }
        }
        IOFile.writeObjectToFile(userList, IOFile.USER_PATH);
    }

    @Override
    public void delete()
    {
        while (true)
        {
            System.out.println("Mời bạn nhập vào ID muốn xóa");
            int IDDelete = InputMethods.getInteger();

            if (finByID(IDDelete) == null)
            {
                System.out.println("id bạn nhập vào chưa đúng");
            } else
            {
                userList.remove(finByID(IDDelete));
                IOFile.writeObjectToFile(projectList, IOFile.PATH_PROJECT);
            }

        }
    }

    @Override
    public Users finByID(Integer inputId)
    {
        for (Users users : userList)
        {
            if (inputId == users.getUserId())
            {
                return users;
            }
        }
        return null;
    }

    public static void logout()
    {
        user = null;
        currentUser.clear();
        IOFile.writeObjectToFile(currentUser, IOFile.CURRENT_USER_PATH);
    }

    // Tao 1 ham xem thong tin hop dong dung chung cho tat ca
    public static void changePassword()
    {
//        System.out.println("Bạn có chắc chắn muốn đổi mật khẩu : ");
//        System.out.println("Mời bạn nhập mật khẩu cũ");
//        String oldPassword = InputMethods.getString();
//        for (Users users : userList)
//        {
//        }

        userList.stream().filter(users -> users.getUserId() == user.getUserId())
                .findFirst().orElse(null).setPassword(BCrypt.hashpw(user.getInputPassword(), BCrypt.gensalt(5)));
        IOFile.writeObjectToFile(userList, IOFile.USER_PATH);
        System.out.println("Thay đổi thành công");
    }
}

