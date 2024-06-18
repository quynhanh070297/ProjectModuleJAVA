package ra.run.menuUser;

import ra.business.config.InputMethods;
import ra.business.config.Role;
import ra.business.entity.Users;
import ra.business.implement.CustomerImplement;
import ra.business.implement.UsersImplement;
import ra.data.IOFile;

import java.util.List;


public class MainMenu
{
    public static UsersImplement usersImplement = new UsersImplement();
    public static List<Users> currentUser;
    // Chua user dang dang nhap

   public static Users user;
    // Mang lay tu IOFile ve, neu user chua dang xuat se luu o day
    static
    {
        currentUser=IOFile.readObjectFromFile(IOFile.CURRENT_USER_PATH);
    }

    public static void main(String[] args) {
        //Check IOFile lay ve
        if (!currentUser.isEmpty()){
            user = currentUser.get(0);
        }else {user=null;}
        // Neu no co thi check quyen va dang nhap
        if (user!=null)  checkRoleUser(user);
        byte choice;
        do {
            System.out.println("\u001B[32m");
            System.out.println("┏-------------------------\u001B[34m WELCOME ❤️\u001B[32m --------------------------┓");
            System.out.println("|  Bạn đã có tài khoản ?        |" +  "  Bạn chưa có tài khoản?       | ");
            System.out.println("|  1.Đăng Nhập ◀️               |" +  "  2.Đăng ký ◀️                 | ");
            System.out.println("┗---------------------------------------------------------------┛\u001B[34m");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn nhập lựa chọn :\u001B[38m");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    longIn();
                    break;
                case 2:
                    usersImplement.register();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }

    public static void longIn (){
        do{
            System.out.println("\u001B[32m┏--------------------------\u001B[34m LOGIN ✌ \u001B[32m----------------------------┓");
            System.out.println("| Nhap username :                                               |");
            System.out.println("┗---------------------------------------------------------------┛\u001B[34m");

            String username  = InputMethods.getString();
            System.out.println("\u001B[32m┏--------------------------\u001B[34m LOGIN ✌ \u001B[32m----------------------------┓");
            System.out.println("| Nhap passwork :                                               |");
            System.out.println("┗---------------------------------------------------------------┛\u001B[34m");
            String password  = InputMethods.getString();
            Users userLogin = usersImplement.login(username,password);
           if (userLogin==null){
            System.err.println(" Tài khoản hoặc mật khẩu không chính xác 🤦 ");
            System.out.println("\u001B[32m┏-------------------------- \u001B[34mLOGIN ✌ \u001B[32m----------------------------┓");
            System.out.println("| 1.Tiếp tục đăng nhập       | 2. Đăng ký Tài khoản  ◀️         |");
            System.out.println("| 3.Trở lại                  | 4. Thoát                  ️      |");
            System.out.println("┗---------------------------------------------------------------┛\u001B[34m");
            System.out.println("------ Nhập lựa chọn --------");
           byte choice = InputMethods.getByte();
            switch (choice){
               case 1:
                   usersImplement.login(username,password);
                   break;
               case 2:
                   usersImplement.register();
                   break;
                case 3:
                    return;
                default:
                   System.err.println("Nhập lựa chọn không chính xác ❌");
            }
        }else {
            checkRoleUser(userLogin);
            }
        } while (true);
    }
    public static void checkRoleUser(Users userLogin){
        CustomerImplement customerImplement = new CustomerImplement();
        if (userLogin.getRole().equals(Role.ROLE_ADMIN)){
            user = userLogin;
            currentUser.add(user);
            IOFile.writeObjectToFile(currentUser,IOFile.CURRENT_USER_PATH);
            AdminMenu.adminManager();
        }else if (userLogin.getRole().equals(Role.ROLE_USER)){
            if (!userLogin.isStatus()){
                System.err.println("Tài khoản đã bị khoá ❌,vui lòng liên hệ ADMIN (09837465263)");
            }else {
                user = userLogin;
                currentUser.add(user);
                IOFile.writeObjectToFile(currentUser,IOFile.CURRENT_USER_PATH);
                CustomerMenu.customerManager(customerImplement);
            }
        }else {
            if (!userLogin.isStatus()){
                System.err.println("Tài khoản đã bị khoá ❌,vui lòng liên hệ ADMIN (09837465263)");
            }else {
            user = userLogin;
            currentUser.add(user);
            IOFile.writeObjectToFile(currentUser,IOFile.CURRENT_USER_PATH);
            ManagerMenu.roleManager();}
        }

    }
}


