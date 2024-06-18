package ra.business.entity;

import ra.business.config.InputMethods;
import ra.business.implement.CustomerImplement;
import ra.business.implement.EmployeeImplement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static ra.business.implement.ContractImplement.contractList;
import static ra.business.implement.CustomerImplement.customerList;
import static ra.business.implement.EmployeeImplement.employeeList;

public class Contract implements Serializable
{
    private Integer contractId;
    private String contractName;
    private Integer employeeId;
    private Integer customerId;
    private LocalDate createdDate;
    private LocalDate expiryDate;
    private double totalAmount;
    private String description;
    private byte priority;

    public Contract()
    {
    }

    public Contract(Integer contractId, String contractName, Integer employeeId, Integer customerId, LocalDate createdDate, LocalDate expiryDate, double totalAmount, String description, byte priority)
    {
        this.contractId = contractId;
        this.contractName = contractName;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.totalAmount = totalAmount;
        this.description = description;
        this.priority = priority;
    }

    public Integer getContractId()
    {
        return contractId;
    }

    public void setContractId(Integer contractId)
    {
        this.contractId = contractId;
    }

    public String getContractName()
    {
        return contractName;
    }

    public void setContractName(String contractName)
    {
        this.contractName = contractName;
    }

    public Integer getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId)
    {
        this.employeeId = employeeId;
    }

    public Integer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }

    public LocalDate getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDate getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    public double getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public byte getPriority()
    {
        return priority;
    }

    public void setPriority(byte priority)
    {
        this.priority = priority;
    }

    public void inputContract()
    {
        this.contractId = getInputIdContract();
        System.out.println("Moi ban nhap ten hop dong");
        this.contractName = InputMethods.getString();
        this.employeeId = getInputEmployeeId();
        this.customerId = getInputCustomerId();
        System.out.println("Moi ban nhap Ngay ky hop dong");
        this.createdDate = InputMethods.getLocalDate();

        System.out.println("Moi ban nhap Ngay het han");
        this.expiryDate = InputMethods.getLocalDate();

        System.out.println("Moi ban nhap chi phi du an hop dong");
        this.totalAmount = InputMethods.getFloat();

        System.out.println("Moi ban nhap mieu ta hop dong");
        this.description = InputMethods.getString();
        autoSetPriority();
    }

    public void autoSetPriority()
    {
        //Độ ưu tiên khách hàng :
        // Khách hàng cấp 3 : VIP - 2 khách hàng có tổng tiền dự án lớn nhất
        // Khách hàng cấp 2 : Triển Vọng - Khách hàng có tổng tiền/ tổng tiền trung bình
        // Khách hàng cấp 1 : Tiềm năng - Khách còn lại
        contractList.sort((o1, o2) -> Double.compare(o1.getTotalAmount(), o2.getTotalAmount()));
        // Sắp xếp theo Comparerator => Sort theo tổng tiền.
        contractList.get(contractList.size()-1).setPriority((byte) 3);
        if (contractList.size()>5){
        contractList.get(contractList.size() - 2).setPriority((byte) 3);
        }
        // Set 2 thằng cuối cùng có độ ưu tiên bằng 3.
        //Biến Mid là biến ở giữa.
        if (contractList.size()>5){
        int mid = contractList.size() / 2;
        for (int i = 0; i <= contractList.size() - 3; i++)
        {
            if (i < mid)
            {
                //nhỏ hơn Mid thì độ ưu tiên bằng 1
                contractList.get(i).setPriority((byte) 1);
            } else
            {
                //Lớn hơn Mid thì độ ưu tiên bằng 2
                contractList.get(i).setPriority((byte) 2);
            }
        }
        }
        else {
            for (int i = 0; i < contractList.size()-1; i++)
            {
                contractList.get(i).setPriority((byte) 1);
            }
        }
    }


    private Integer getInputEmployeeId()
    {
        employeeList.forEach(Employee::displayEmployee);
        System.out.println("Mời bạn nhập ID Nhân viên phụ trách ");
        while (true)
        {

            int inputId = InputMethods.getInteger();
            for (Employee employee : employeeList)
            {
                if (employee.getEmployeeId() == inputId)
                {
                   return employee.getEmployeeId();

                }
            }
                System.out.println("Không tìm thấy ID mời nhập lại");
        }
    }

    private Integer getInputCustomerId()
    {
        customerList.forEach(Customer::displayCustomer);
        System.out.println("Mời bạn nhập ID Nhân viên phụ trách ");
        while (true)
        {
            int inputId = InputMethods.getInteger();
            for (Customer customer : customerList)
            {
                if (customer.getCustomerId() == inputId)
                {
                    return customer.getCustomerId();
                }
            }
            System.out.println("Nhap sai moi nhap lai");
        }
    }


    public void displayContract()
    {
        System.out.printf("===========================Contract==========================\n" +
                        "| Contract ID:   %-40d|\n" +
                        "| Contract Name: %-40s|\n" +
                        "| Employee ID:   %-40d|\n" +
                        "| Customer ID:   %-40d|\n" +
                        "| Created Date:  %-40s|\n" +
                        "| Expiry Date:   %-40s|\n" +
                        "| Total Amount:  %-40f|\n" +
                        "| Description:   %-40s|\n" +
                        "| Priority:      %-40s|\n",
                contractId, contractName, employeeId, customerId, createdDate,
                expiryDate, totalAmount, description, (priority==1?"Khach hang trien vong":(priority==2?"Khách hàng ưu tiên":"Khách hàng VIP")));
    }

    public Integer getInputIdContract()
    {
        int max = contractList.stream().mapToInt(Contract::getContractId).max().orElse(0);
        return max + 1;
    }

}
