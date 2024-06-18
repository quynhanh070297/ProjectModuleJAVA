package ra.business.entity;

import org.mindrot.jbcrypt.BCrypt;
import ra.business.config.InputMethods;
import ra.business.config.Role;
import ra.business.implement.UsersImplement;

import java.io.Serializable;
import java.util.List;

import static ra.business.IGeneric.IUsers.ADMIN_CODE;
import static ra.business.IGeneric.IUsers.MANAGER_CODE;

public class Users implements Serializable
{
    private int userId;
    private String username;
    private String password;
    private Role role;
    private boolean status;

    public Users()
    {
    }

    public Users(int userId, String username, String password, Role role, boolean status)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void inputUser()
    {
        this.userId = getInputUserId(UsersImplement.userList);
        this.username = getInputUsername(UsersImplement.userList);
        this.password = getInputPassword();
        this.setPassword(BCrypt.hashpw(this.getPassword(), BCrypt.gensalt(5)));
        this.role = getInputRole();
        this.status = getInputStatus();
    }

    public void displayUser()
    {
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.printf("|ID : %-3s | Name : %-15s | Quyen User : %-15s | Trang thai : %-5s |\n",
                userId, username, role, status ? "Hoat Dong" : "Khong Hoat dong");
    }

    private int getInputUserId(List<Users> usersList)
    {
        //Nếu list chưa có phần tử nào thì đây chính là phần tử đầu tiên
        if (usersList.isEmpty())
        {
            return 1;
        } else
        {
            int maxId = usersList.get(usersList.size()-1).getUserId();
            for (Users user : usersList)
            {
                //Tìm ra id lớn nhất hiện có trong list employee
                if (user.getUserId() > maxId)
                    maxId = user.getUserId();
            }
            return ++maxId;
        }
    }

    private String getInputUsername(List<Users> usersList)
    {
        //Tối thiểu 6 kí tự , ko có kí tự đặc biệt , ko để trống, ko trùng lặp
        String regex = "^[a-zA-Z0-9]{6,}$";
        System.out.println("Mời bạn nhập vào tên đăng nhập:");
        while (true)
        {




            String userInputName = InputMethods.getString();
            if (!userInputName.matches(regex))
            {
                System.out.println("Tên của bạn không thể ít hơn 6 kí tự và không có kí tự đặc biệt");
            } else
            {
                if (usersList.stream().anyMatch(users -> users.getUsername().equals(userInputName)))
                {
                    System.out.println("Tên đăng nhập của bạn bị trùng, mời bạn nhập lại:");
                } else
                {
                    return userInputName;
                }
            }
        }
    }

    public String getInputPassword()
    {
        //Tối thiểu 8 kí tự, kí tự chữ, số
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
        System.out.println("mời bạn nhập mật khẩu");
        while (true)
        {
            String passwordInput = InputMethods.getString();
            if (!passwordInput.matches(regex))
            {
                System.out.println("Mật khẩu phải có cả số và chữ và lớn hơn 8 kí tự");
            } else
            {
                return passwordInput;
            }
        }
    }

    private Role getInputRole()
    {
        // Mac dinh la User
        System.out.println("Mời bạn nhập lựa chọn:");
        System.out.println("1. ADMIN");
        System.out.println("2. Quản lý");
        System.out.println("3. User");
        int option = InputMethods.getInteger();
        if (option == 1 || option == 2)
        {
            Role checkRoleUser = checkRole();
            if (checkRoleUser == null)
            {
                System.out.println("Mã không đúng,yêu cầu không đăng nhập trái phép");
                System.exit(0);
            } else
            {
                return checkRoleUser;
            }
        }
        return Role.ROLE_USER;
    }

    private Role checkRole()
    {
        System.out.println("Quyền này cần mã để thực thi");
        System.out.println("Mời bạn nhập mã");
        int option = InputMethods.getInteger();
        return switch (option)
        {
            case ADMIN_CODE -> Role.ROLE_ADMIN;
            case MANAGER_CODE -> Role.ROLE_MANAGER;
            default -> null;
        };
    }

    private boolean getInputStatus()
    {
        //Mac dinh la true
        return true;
    }
}
