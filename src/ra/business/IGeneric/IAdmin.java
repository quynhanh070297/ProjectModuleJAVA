package ra.business.IGeneric;

public interface IAdmin
{
    // Thêm mới tài khoản
    void addUser();

    //Xem danh sách tài khoản
    void displayUser();

    //Khóa mở tài khoản
    void openOrBlockUser();

    //Thống kê số lượng khách hàng
    void statisticsCustomers();

    //Thống kê số lượng hợp đồng
    void statisticsContract();

    //Xem danh sách dự án theo hợp đồng
    void displayProjectByContract();
}
