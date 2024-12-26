package organization;
import java.util.*;

public class OorganizationManagement {
	
	    // Employee Class
	    static class Employee {
	        int id;
	        String name;
	        String department;
	        double salary;

	        public Employee(int id, String name, String department, double salary) {
	            this.id = id;
	            this.name = name;
	            this.department = department;
	            this.salary = salary;
	        }

	        @Override
	        public String toString() {
	            return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: $" + salary;
	        }
	    }

	    // Department Class
	    static class Department {
	        String departmentName;
	        List<Employee> employees;

	        public Department(String departmentName) {
	            this.departmentName = departmentName;
	            this.employees = new ArrayList<>();
	        }

	        public void addEmployee(Employee employee) {
	            employees.add(employee);
	        }

	        public void removeEmployee(int empId) {
	            employees.removeIf(e -> e.id == empId);
	        }

	        @Override
	        public String toString() {
	            return "Department: " + departmentName + ", Employees: " + employees.size();
	        }
	    }

	    public static void main(String[] args) {
	        Map<Integer, Employee> employees = new HashMap<>();
	        Map<String, Department> departments = new HashMap<>();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n=== Organization Management System ===");
	            System.out.println("1. Add Employee");
	            System.out.println("2. Display All Employees");
	            System.out.println("3. Search Employee by ID");
	            System.out.println("4. Update Employee Details");
	            System.out.println("5. Remove Employee");
	            System.out.println("6. Add Department");
	            System.out.println("7. Display All Departments");
	            System.out.println("8. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    // Add Employee
	                    System.out.print("Enter Employee ID: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Employee Name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter Employee Department: ");
	                    String department = scanner.nextLine();
	                    System.out.print("Enter Employee Salary: ");
	                    double salary = scanner.nextDouble();

	                    Employee employee = new Employee(id, name, department, salary);
	                    employees.put(id, employee);

	                    // Ensure the department exists
	                    departments.putIfAbsent(department, new Department(department));
	                    departments.get(department).addEmployee(employee);

	                    System.out.println("Employee added successfully!");
	                    break;

	                case 2:
	                    // Display All Employees
	                    if (employees.isEmpty()) {
	                        System.out.println("No employees found.");
	                    } else {
	                        System.out.println("\n--- Employee List ---");
	                        for (Employee e : employees.values()) {
	                            System.out.println(e);
	                        }
	                    }
	                    break;

	                case 3:
	                    // Search Employee by ID
	                    System.out.print("Enter Employee ID to search: ");
	                    int searchId = scanner.nextInt();
	                    if (employees.containsKey(searchId)) {
	                        System.out.println("Employee Found: " + employees.get(searchId));
	                    } else {
	                        System.out.println("Employee with ID " + searchId + " not found.");
	                    }
	                    break;

	                case 4:
	                    // Update Employee Details
	                    System.out.print("Enter Employee ID to update: ");
	                    int updateId = scanner.nextInt();
	                    if (employees.containsKey(updateId)) {
	                        scanner.nextLine(); // Consume newline
	                        System.out.print("Enter New Name (leave blank to keep unchanged): ");
	                        String newName = scanner.nextLine();
	                        System.out.print("Enter New Department (leave blank to keep unchanged): ");
	                        String newDepartment = scanner.nextLine();
	                        System.out.print("Enter New Salary (enter -1 to keep unchanged): ");
	                        double newSalary = scanner.nextDouble();

	                        Employee emp = employees.get(updateId);
	                        if (!newName.isEmpty()) emp.name = newName;
	                        if (!newDepartment.isEmpty() && !newDepartment.equals(emp.department)) {
	                            // Move employee to new department
	                            departments.get(emp.department).removeEmployee(updateId);
	                            emp.department = newDepartment;
	                            departments.putIfAbsent(newDepartment, new Department(newDepartment));
	                            departments.get(newDepartment).addEmployee(emp);
	                        }
	                        if (newSalary != -1) emp.salary = newSalary;

	                        System.out.println("Employee details updated successfully!");
	                    } else {
	                        System.out.println("Employee with ID " + updateId + " not found.");
	                    }
	                    break;

	                case 5:
	                    // Remove Employee
	                    System.out.print("Enter Employee ID to remove: ");
	                    int removeId = scanner.nextInt();
	                    if (employees.containsKey(removeId)) {
	                        Employee removedEmployee = employees.remove(removeId);
	                        departments.get(removedEmployee.department).removeEmployee(removeId);
	                        System.out.println("Employee removed successfully!");
	                    } else {
	                        System.out.println("Employee with ID " + removeId + " not found.");
	                    }
	                    break;

	                case 6:
	                    // Add Department
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Department Name: ");
	                    String depName = scanner.nextLine();
	                    if (!departments.containsKey(depName)) {
	                        departments.put(depName, new Department(depName));
	                        System.out.println("Department added successfully!");
	                    } else {
	                        System.out.println("Department already exists.");
	                    }
	                    break;

	                case 7:
	                    // Display All Departments
	                    if (departments.isEmpty()) {
	                        System.out.println("No departments found.");
	                    } else {
	                        System.out.println("\n--- Department List ---");
	                        for (Department dep : departments.values()) {
	                            System.out.println(dep);
	                        }
	                    }
	                    break;

	                case 8:
	                    // Exit
	                    System.out.println("Exiting... Thank you!");
	                    scanner.close();
	                    System.exit(0);

	                default:
	                    System.out.println("Invalid option! Please try again.");
	            }
	        }
	    }
	}



