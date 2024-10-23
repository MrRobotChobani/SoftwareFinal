package Tests;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Prescription {
    
    // Attributes and stuff
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Getters and Setters to allow the tests to access these attributes
    
    public int getPrescID() {
        return prescID;
    }

    public void setPrescID(int prescID) {
        this.prescID = prescID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSphere() {
        return sphere;
    }

    public void setSphere(float sphere) {
        this.sphere = sphere;
    }

    public float getAxis() {
        return axis;
    }

    public void setAxis(float axis) {
        this.axis = axis;
    }

    public float getCylinder() {
        return cylinder;
    }

    public void setCylinder(float cylinder) {
        this.cylinder = cylinder;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getOptometrist() {
        return optometrist;
    }

    public void setOptometrist(String optometrist) {
        this.optometrist = optometrist;
    }

    public ArrayList<String> getPostRemarks() {
        return postRemarks;
    }

    public void setPostRemarks(ArrayList<String> postRemarks) {
        this.postRemarks = postRemarks;
    }

    // Function to add a prescription to a TXT file based on if valid or not
    public boolean addPrescription() {
        if (!validatePrescription()) {
            System.out.println("Prescription validation failed.");
            return false;  // Prescription is not valid
        }

        try (FileOutputStream fos = new FileOutputStream("presc.txt", true);
             PrintWriter writer = new PrintWriter(fos)) {
            writer.println(toString());
            System.out.println("Prescription added successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  // Return false if file writing fails
    }

 // Function to add a remark to a TXT file
    public boolean addRemark(String remark, String category) {
        if (!validateRemark(remark, category)) {
            System.out.println("Remark validation failed.");
            return false;  // Validation failed
        }

        try (FileOutputStream fos = new FileOutputStream("remark.txt", true);
             PrintWriter writer = new PrintWriter(fos)) {
            writer.println("Category: " + category + " | Remark: " + remark);
            System.out.println("Remark added successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  // Return false if file writing fails
    }
    
    
    

    // Private method to validate prescription information
    private boolean validatePrescription() {
        if (firstName.length() < 4 || firstName.length() > 15) {
            System.out.println("First name validation failed.");
            return false;
        }
        if (lastName.length() < 4 || lastName.length() > 15) {
            System.out.println("Last name validation failed.");
            return false;
        }
        if (address.length() < 20) {
            System.out.println("Address validation failed.");
            return false;
        }
        if (sphere < -20.00 || sphere > 20.00) {
            System.out.println("Sphere validation failed.");
            return false;
        }
        if (cylinder < -4.00 || cylinder > 4.00) {
            System.out.println("Cylinder validation failed.");
            return false;
        }
        if (axis < 0 || axis > 180) {
            System.out.println("Axis validation failed.");
            return false;
        }
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            System.out.println("Optometrist name validation failed.");
            return false;
        }
        if (examinationDate == null || !validateDate(examinationDate)) {
            System.out.println("Examination date validation failed.");
            return false;
        }
        return true;  // Return true only if all fields are valid
    }
    
    
    private boolean validateDate(Date examinationDate) {
        // Ensure date is not in the future
        Date currentDate = new Date();
        if (examinationDate.after(currentDate)) {
            System.out.println("Examination date is in the future.");
            return false;  // Return false if the date is in the future
        }

        // Format the date to DD/MM/YY and ensure it's in the correct format
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false);  // Ensure strict parsing
        try {
            String formattedDate = sdf.format(examinationDate);
            sdf.parse(formattedDate);  // This will throw ParseException if the date is invalid
            System.out.println("Date is valid: " + formattedDate);
            return true;
        } catch (Exception e) {
            System.out.println("Date format validation failed.");
            return false;
        }
    }
    
    // Private method to validate the remark before adding
    private boolean validateRemark(String remark, String category) {
        String[] words = remark.split(" ");
        
        if (words.length < 6 || words.length > 20) {
            System.out.println("Word count validation failed.");
            return false;
        }

        if (!Character.isUpperCase(words[0].charAt(0))) {
            System.out.println("First word capitalization validation failed.");
            return false;
        }

        if (!category.equalsIgnoreCase("Client") && !category.equalsIgnoreCase("Optometrist")) {
            System.out.println("Category validation failed.");
            return false;
        }

        if (postRemarks.size() >= 2) {
            System.out.println("Max remarks exceeded.");
            return false;
        }

        postRemarks.add(remark);
        return true;
    }
    
    
    
    public String toString() {
        return "Prescription ID=" + prescID + ", Name=" + firstName + " " + lastName + 
               ", Address=" + address + ", Sphere=" + sphere + ", Cylinder=" + cylinder +
               ", Axis=" + axis + ", Examination Date=" + examinationDate + 
               ", Optometrist=" + optometrist;
    }
}
