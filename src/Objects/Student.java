package Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Myles on 4/27/17.
 */
public class Student implements Serializable {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private double aidAmount;

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAidAmount() {
        return aidAmount;
    }

    public void setAidAmount(double aidAmount) {
        this.aidAmount = aidAmount;
    }

    public boolean checkFinancialAid(double amount)
    {
        if(getAidAmount() >= amount)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void changeFinancialAid(double amount)
    {
        if(checkFinancialAid(amount))
        {
            double newAmount = getAidAmount() - amount;
            setAidAmount(newAmount);
        }
    }

}
