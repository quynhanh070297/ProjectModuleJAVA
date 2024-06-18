package ra.business.entity;

import ra.business.config.InputMethods;
import ra.business.implement.DepartmentImplement;
import ra.business.implement.EmployeeImplement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

import static ra.business.implement.CustomerImplement.customerList;

public class Customer implements Serializable
{
   private Integer customerId;
   private String customerName;
   private LocalDate birthday;
   private boolean sex;
   private String address;
   private String email;
   private String phoneNumber;
   private String note;
   private byte priority;

   public Customer()
   {
   }

   public Customer(Integer customerId, String customerName, LocalDate birthday, boolean sex, String address, String email, String phoneNumber, String note, byte priority)
   {
      this.customerId = customerId;
      this.customerName = customerName;
      this.birthday = birthday;
      this.sex = sex;
      this.address = address;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.note = note;
      this.priority = priority;
   }

   public Integer getCustomerId()
   {
      return customerId;
   }

   public void setCustomerId(Integer customerId)
   {
      this.customerId = customerId;
   }

   public String getCustomerName()
   {
      return customerName;
   }

   public void setCustomerName(String customerName)
   {
      this.customerName = customerName;
   }

   public LocalDate getBirthday()
   {
      return birthday;
   }

   public void setBirthday(LocalDate birthday)
   {
      this.birthday = birthday;
   }

   public boolean isSex()
   {
      return sex;
   }

   public void setSex(boolean sex)
   {
      this.sex = sex;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }

   public String getNote()
   {
      return note;
   }

   public void setNote(String note)
   {
      this.note = note;
   }

   public byte getPriority()
   {
      return priority;
   }

   public void setPriority(byte priority)
   {
      this.priority = priority;
   }

   public void inputCustomer(int id)
   {
      this.customerId = id;
      System.out.println("Mời nhập Tên khách hàng");
      this.customerName = InputMethods.getString();
      System.out.println("Mời nhập Ngày sinh khách hàng");
      this.birthday = InputMethods.getLocalDate();
      System.out.println("Mời nhập giới tính");
      this.sex = InputMethods.getBoolean();
      System.out.println("Moi nhap dia chi");
      this.address = InputMethods.getString();
      System.out.println("Moi nhap dia chi Email");
      this.email = InputMethods.getString();
      System.out.println("Moi nhap So dien thoai");
      this.phoneNumber = InputMethods.getString();
      System.out.println("Moi nhap ghi chu");
      this.note = InputMethods.getString();
      //Độ ưu tiên của khách hàng bằng độ ưu tiên của dự án.
      //Mặc định là 1 - Khách hàng tiêm năng
      this.priority = 1;
   }

   public void displayCustomer(){
      System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      System.out.printf("|ID : %-3s | Customer Name : %-15s | BirthDay : %-3s | Sex : %-5s | Địa chỉ : %-10s| Email : %-20s | Phone Number : %-10s | Note : %-15s | Priority : %-10s |\n",
              this.customerId, this.customerName, this.birthday,this.sex?"Nam":"Nữ",this.address,this.email, this.phoneNumber,this.note,this.priority);
   }
   public Integer getIdCustomer(){
      int max = customerList.stream().mapToInt(Customer::getCustomerId).max().orElse(0);
      return max+1;
   }
}
