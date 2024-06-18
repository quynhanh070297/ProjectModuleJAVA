package ra.run.menuUser;

import ra.business.config.InputMethods;
import ra.business.implement.CustomerImplement;
import ra.business.implement.UsersImplement;

public class CustomerMenu
{
    public static void customerManager(CustomerImplement customerImplement)
    {
        boolean isExit = true;
        byte choice;
        do {
            System.out.println("┏----------------------------- CUSTOMER ----------------------------┓");
            System.out.println("|  1.Xem Hợp đồng                 |" +  "  2.Xem dự án                    | ");
            System.out.println("|  3.Xem thông tin tài khoản      |" +  "  4.Đổi mật khẩu                 | ");
            System.out.println("|  5.Xem thông tin cá nhân        |" +  "  6.Đăng xuất                    | ");
            System.out.println("|  7.Tro lai ?                                                      | ");
            System.out.println("┗-------------------------------------------------------------------┛");
            System.out.print("Mời bạn nhập lựa chọn :");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    customerImplement.readContract();
                    break;
                case 2:
                    customerImplement.readProject();
                    break;
                case 3:
                    break;
                case 4:
                    UsersImplement.changePassword();
                    break;
                case 5:
                    customerImplement.readMyInformation();
                    break;
                case 6:
                    UsersImplement.logout();
                    isExit = false;
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (isExit);
    }
    
}
