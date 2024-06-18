package ra.run.menuUser;

import ra.business.config.InputMethods;
import ra.business.implement.AdminImplement;
import ra.business.implement.EmployeeImplement;
import ra.business.implement.UsersImplement;

public class AdminMenu
{

    public static void adminManager()
    {
        UsersImplement usersImplement = new UsersImplement() ;
        AdminImplement adminImplement = new AdminImplement() ;
        boolean isExit = true;
        byte choice;
        do {
            System.out.println("┏-------------------------- ADMIN ------------------------------┓");
            System.out.println("|  1.Quản trị tài khoản         |" +  "  2.Báo cáo thống kê           | ");
            System.out.println("|  3.Đăng xuất ?                |" +  "  4.Quay lại ?                 | ");
            System.out.println("|  5.Đổi mật khẩu?                                              | ");
            System.out.println("┗---------------------------------------------------------------┛");
            System.out.print("Mời bạn nhập lựa chọn :");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    acountManager(usersImplement,adminImplement);

                    break;
                case 2:
                    reportList(adminImplement);
                    break;
                case 3:
                    UsersImplement.logout();
                    isExit = false;
                    break;
                case 4:
                    isExit = false;
                    break;
                case 5:
                    UsersImplement.changePassword();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (isExit);
    }
    public static void acountManager(UsersImplement usersImplement, AdminImplement adminImplement){
        byte choice;
        do {
            System.out.println("┏------------------ QUẢN TRI TÀI KHOAN -------------------------┓");
            System.out.println("|  1.Thêm mới tài khoản         |" +  " 2. Xem danh sách tài khoản    | ");
            System.out.println("|  3.Khoá mở tài khoản          |" +  " 4.Quay lại                    | ");
            System.out.println("┗---------------------------------------------------------------┛");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn nhập lựa chọn :");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    usersImplement.register();
                    break;
                case 2:
                    usersImplement.read();
                    break;
                case 3:
                    adminImplement.openOrBlockUser();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }
    public static void reportList(AdminImplement adminImplement ){
        boolean isExit = true;
        byte choice;
        do {
            System.out.println("┏--------------------------  BÁO CÁO THỐNG KÊ   ----------------------------┓");
            System.out.println("|1.Thống kê số lượng khách hàng      |" +  " 2.Thống kê số lượng hợp đồng        | ");
            System.out.println("|3.Xem danh sách dự án theo hợp đồng |" +  " 4.Quay lại                          | ");
            System.out.println("┗---------------------------------------------------------------------------┛");
            System.out.print("Mời bạn nhập lựa chọn :");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    adminImplement.statisticsCustomers();
                    break;
                case 2:
                    adminImplement.statisticsContract();
                    break;
                case 3:
                    adminImplement.displayProjectByContract();
                    break;
                case 4:
                    isExit = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (isExit);
    }

}
