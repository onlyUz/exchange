package service;

import company.*;

import java.util.ArrayList;
import java.util.List;

import static service.Data.*;

//负责将Data中的数据封装到Employee集合中，同时提供相关操作Employee[]的方法。
public class ListOperation {
    private List<Employee> employees;

    // 将Data中的数据封装到Employee集合中
    public ListOperation() {
        employees = new ArrayList<>();
        for (int i = 0; i < EMPLOYEES.length; i++) {
            // 获取员工类型
            int type = Integer.parseInt((Data.EMPLOYEES[i][0]));
            // 获取员工的4个基本信息
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);

            Equipment equipment;
            double bonus;
            int stock;

            switch (type) {
                case Data.EMPLOYEE:
                    employees.add(new Employee(id, name, age, salary));
                    break;
                case Data.PROGRAMMER:
                    equipment = createEquipment(i);
                    employees.add(new Programmer(id, name, age, salary, equipment));
                    break;
                case Data.DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    employees.add(new Designer(id, name, age, salary, equipment, bonus));
                    break;
                case Data.ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
                    employees.add(new Architect(id, name, age, salary, equipment, bonus, stock));
                    break;
            }
        }

    }
    // 将设备属性添加到employee中
    private Equipment createEquipment(int index) {
        int type = Integer.parseInt(Data.EQIPMENTS[index][0]);
        String model = Data.EQIPMENTS[index][1];
        switch (type) {
            case Data.PC: //21
                String display = Data.EQIPMENTS[index][2];
                return new PC(model, display);
            case Data.NOTEBOOK: //22
                double price = Double.parseDouble(Data.EQIPMENTS[index][2]);
                return new NotBook(model, price);
            case Data.PRINTER: //23
                return new Printer(Data.EQIPMENTS[index][1], Data.EQIPMENTS[index][2]);
        }
        return null;
    }

    // 获取所有员工
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // 获取员工id
    public Employee getEmployee(int id) throws TeamException {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new TeamException("找不到指定的员工");

    }

}