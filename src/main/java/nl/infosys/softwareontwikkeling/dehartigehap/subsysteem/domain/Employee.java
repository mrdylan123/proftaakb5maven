/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

/**
 *
 * @author Maikel
 */
public class Employee {
    private String name, email, loginName, loginPassword, zipCode,
                street, address, city, phonenumber, nationality;
    private int employeeId, wage;
    private Date dateOfBirth,employmentDate;

    public Employee(String name, String email, String loginName, 
            String loginPassword, String street, String address, String city, 
            String phonenumber, String nationality, int employeeId, int wage, 
            String zipCode, Date dateOfBirth, Date employmentDate) {
        this.name = name;
        this.email = email;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.street = street;
        this.address = address;
        this.city = city;
        this.phonenumber = phonenumber;
        this.nationality = nationality;
        this.employeeId = employeeId;
        this.wage = wage;
        this.zipCode = zipCode;
        this.dateOfBirth = dateOfBirth;
        this.employmentDate = employmentDate;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public String getName()
    {
        return name;
    }
}

