package ra.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile
{
    public static final String PATH_EMPLOYEE = "/Users/hoanganh/QAhocJAVA/CRM-Project/src/ra/data/employee.txt";
    public static final String PATH_PROJECT = "/Users/hoanganh/QAhocJAVA/CRM-Project/src/ra/data/project.txt";
    public static final String PATH_CUSTOMER = "/Users/hoanganh/QAhocJAVA/CRM-Project/src/ra/data/customer.txt";
    public static final String PATH_DEPARTMENT = "/Users/hoanganh/QAhocJAVA/CRM-Project/src/ra/data/department.txt";
    public static final String PATH_CONTRACT = "/Users/hoanganh/QAhocJAVA/CRM-Project/src/ra/data/contract.txt";
    public static final String USER_PATH = "/Users/hoanganh/QAhocJAVA/CRM-Project/src/ra/data/user.txt";
    public static final String CURRENT_USER_PATH ="/Users/hoanganh/QAhocJAVA/CRM-Project/src/ra/data/currentuser.txt";
    public static <T> void writeObjectToFile(List<T> list, String path) {
        FileOutputStream fos = null; // output là đầu ra nên cái FileOutputStream nó là ghi file (đại diện cho file nào dựa vào path)
        ObjectOutputStream oos = null; // đối tượng ObjectOutputStream dành việc ghi object vào file
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(list);

        } catch (IOException e) {

        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }

        }
    }

    public static <T> List<T> readObjectFromFile(String path) {
        // Input là đầu vào nên nó sẽ là lấy dữ liệu vào chương trình (đại diện cho file thông đường dẫn path)
        FileInputStream fis = null;
        // ObjectInputStream nó là đối tượng dùng để làm việc đọc file
        ObjectInputStream ois = null;
        List<T> list = new ArrayList<>();
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);

            list = (List<T>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
         e.getStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
