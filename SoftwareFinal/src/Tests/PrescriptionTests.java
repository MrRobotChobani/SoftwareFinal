package Tests;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;

import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;



public class PrescriptionTests {
	
	
    @BeforeEach
    void setUp() throws IOException {
        // Create files if they dont exist
        File prescFile = new File("presc.txt");
        File remarkFile = new File("remark.txt");
        
        if (!prescFile.exists()) {
            prescFile.createNewFile();  // Create prescription file
        }
        
        if (!remarkFile.exists()) {
            remarkFile.createNewFile();  // Create remark file
        }
    }
	
	
	

    // --- Test cases for addPrescription() ---
	
	
	
	//          TEST 1 (ADD PRESCRIPTION VALID)

    @Test
    void testAddPrescriptionValid() throws IOException, ParseException {
        // First valid test data
        Prescription pres1 = new Prescription();
        pres1.setFirstName("Johnny");
        pres1.setLastName("Dorky");
        pres1.setAddress("1234 Elm Street, Apt 5B, Springfield, 12345, USA");
        pres1.setSphere(-2.5f);
        pres1.setCylinder(-1.0f);
        pres1.setAxis(90.0f);
        pres1.setOptometrist("OptometristName");

        // Date for first test
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date validDate1 = sdf.parse("15/10/23");
        pres1.setExaminationDate(validDate1);  // Set a valid examination date

        // Test first valid prescription
        assertTrue(pres1.addPrescription(), "Valid prescription should be added");

        // Check if the first data is written to the file
        assertTrue(Files.lines(Paths.get("presc.txt"))
                .anyMatch(line -> line.contains("John Doe")), "Prescription should be written to file");

        // Second valid test data
        Prescription pres2 = new Prescription();  // Create a new Prescription object
        pres2.setFirstName("Janey");
        pres2.setLastName("Smith");
        pres2.setAddress("5678 Oak Drive, Apt 10, Metropolis, 67890, USA");
        pres2.setSphere(1.0f);
        pres2.setCylinder(0.5f);
        pres2.setAxis(180.0f);
        pres2.setOptometrist("OptometristJones");

        // Date for second test
        Date validDate2 = sdf.parse("20/10/23");
        pres2.setExaminationDate(validDate2);  // Set a valid examination date for second test

        // Test second valid prescription
        assertTrue(pres2.addPrescription(), "Valid prescription should be added (2nd test)");

        // Check if second data is written to the file
        assertTrue(Files.lines(Paths.get("presc.txt"))
                .anyMatch(line -> line.contains("Jane Smith")), "Prescription should be written to file (2nd test)");
    }
    
    
    
	//          TEST 2 (ADD PRESCRIPTION INVALID  FIRST NAME)
    
    @Test
    void testAddPrescriptionInvalidFirstName() throws IOException {
        Prescription pres = new Prescription();
        pres.setFirstName("Jo");  // Invalid first name
        pres.setLastName("Dorky");
        pres.setAddress("1234 Elm Street, Apt 5B, Springfield, 12345, USA");
        pres.setSphere(-2.5f);
        pres.setCylinder(-1.0f);
        pres.setAxis(90.0f);
        pres.setOptometrist("OptometristName");

        // Invalid first name
        assertFalse(pres.addPrescription(), "Invalid first name should fail");

        // Second invalid test data
        pres.setFirstName("Ab");  // Invalid again
        assertFalse(pres.addPrescription(), "Invalid first name should fail (2nd test)");

        // check file for both invalid attempts
        assertFalse(Files.lines(Paths.get("presc.txt"))
                .anyMatch(line -> line.contains("Jo Doe")), "Invalid prescription should not be written to file");
    }

    
    
    
    //           TEST 3 (ADD PRESCRIPTION INVALID LAST NAME)
    
    @Test
    void testAddPrescriptionInvalidLastName() throws IOException {
        Prescription pres = new Prescription();
        pres.setFirstName("Johnny");
        pres.setLastName("D");  // Invalid last name
        pres.setAddress("1234 Elm Street, Apt 5B, Springfield, 12345, USA");
        pres.setSphere(-2.5f);
        pres.setCylinder(-1.0f);
        pres.setAxis(90.0f);
        pres.setOptometrist("OptometristName");

        // Invalid last name
        assertFalse(pres.addPrescription(), "Invalid last name should fail");

        // Second invalid test data
        pres.setLastName("A");  // Another invalid case
        assertFalse(pres.addPrescription(), "Invalid last name should fail (2nd test)");

        // check file for both invalid attempts
        assertFalse(Files.lines(Paths.get("presc.txt"))
                .anyMatch(line -> line.contains("John D")), "Invalid prescription should not be written to file");
    }

    
    
    
    
    
    //           TEST 4 (ADD PRESCRIPTION INVALID SPHERE)    
    @Test
    void testAddPrescriptionInvalidSphere() throws IOException {
        Prescription pres = new Prescription();
        pres.setFirstName("Johnny");
        pres.setLastName("Dorky");
        pres.setAddress("1234 Elm Street, Apt 5B, Springfield, 12345, USA");
        pres.setSphere(-25.0f);  // Invalid sphere
        pres.setCylinder(-1.0f);
        pres.setAxis(90.0f);
        pres.setOptometrist("OptometristName");

        // Invalid sphere
        assertFalse(pres.addPrescription(), "Invalid sphere should fail");

        // Second invalid test data
        pres.setSphere(30.0f);  // Out of range
        assertFalse(pres.addPrescription(), "Invalid sphere should fail (2nd test)");

        // check file for both invalid attempts
        assertFalse(Files.lines(Paths.get("presc.txt"))
                .anyMatch(line -> line.contains("John Doe")), "Invalid prescription should not be written to file");
    }

    
    
    
    
    
    //           TEST 5 (ADD PRESCRIPTION INVALID AXIS)    
    @Test
    void testAddPrescriptionInvalidAxis() throws IOException {
        Prescription pres = new Prescription();
        pres.setFirstName("Johnny");
        pres.setLastName("Dorky");
        pres.setAddress("1234 Elm Street, Apt 5B, Springfield, 12345, USA");
        pres.setSphere(-2.5f);
        pres.setCylinder(-1.0f);
        pres.setAxis(200.0f);  // Invalid axis

        // Invalid axis
        assertFalse(pres.addPrescription(), "Invalid axis should fail");

        // Second invalid test data
        pres.setAxis(-10.0f);  // Another invalid value
        assertFalse(pres.addPrescription(), "Invalid axis should fail (2nd test)");

        // check file for both invalid attempts
        assertFalse(Files.lines(Paths.get("presc.txt"))
                .anyMatch(line -> line.contains("John Doe")), "Invalid prescription should not be written to file");
    }

    
    
    
    
    //           TEST 6 (ADD PRESCRIPTION INVALID OPT. NAME)    
    @Test
    void testAddPrescriptionInvalidOptometristName() throws IOException {
        Prescription pres = new Prescription();
        pres.setFirstName("Johnny");
        pres.setLastName("Dorky");
        pres.setAddress("1234 Elm Street, Apt 5B, Springfield, 12345, USA");
        pres.setSphere(-2.5f);
        pres.setCylinder(-1.0f);
        pres.setAxis(90.0f);
        pres.setOptometrist("Opt");  // Invalid optometrist name

        // Invalid optometrist name
        assertFalse(pres.addPrescription(), "Invalid optometrist name should fail");

        // Second invalid test data
        pres.setOptometrist("Dr");  // Another invalid case
        assertFalse(pres.addPrescription(), "Invalid optometrist name should fail (2nd test)");

        
        // check file for both invalid attempts
        assertFalse(Files.lines(Paths.get("presc.txt"))
                .anyMatch(line -> line.contains("John Doe")), "Invalid prescription should not be written to file");
    }
    
    
    

    // --- Test cases for addRemark() ---

    
    
    //           TEST 7 (ADD REMARK VALID)    
    @Test
    void testAddRemarkValid() throws IOException {
        Prescription pres = new Prescription();
        String validRemark1 = "Great service provided by the optometrist.";
        String validRemark2 = "Excellent feedback received from the client.";
        String category = "Client";

        // First valid remark
        assertTrue(pres.addRemark(validRemark1, category), "Valid remark should be added");

        // Second valid remark
        assertTrue(pres.addRemark(validRemark2, "Optometrist"), "Second valid remark should be added");

        // check if remarks were written to the file
        assertTrue(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(validRemark1)), "Remark 1 should be written to file");

        assertTrue(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(validRemark2)), "Remark 2 should be written to file");
    }

    
    
    //           TEST 8 (ADD REMARK TOO MANY REMARKS)    
    @Test
    void testAddRemarkInvalidTooManyRemarks() throws IOException {
        Prescription pres = new Prescription();
        pres.addRemark("First valid remark by optometrist.", "Optometrist");
        pres.addRemark("Second valid remark by client.", "Client");

        // Third remark should fail
        String thirdRemark = "Third remark should fail.";
        assertFalse(pres.addRemark(thirdRemark, "Client"), "Adding third remark should fail");

        // Check that third remark was not added to the file
        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(thirdRemark)), "Third remark should not be written to file");
    }
    
    
    
    
    // 		 TEST 9 (ADD REMARK TOO FEW WORDS) 
    @Test
    void testAddRemarkInvalidWordCountTooFew() throws IOException {
        Prescription pres = new Prescription();
        String invalidRemark1 = "Too short.";  // less than 6 words
        String invalidRemark2 = "Only four words here.";  // Also too short

        // First invalid remark
        assertFalse(pres.addRemark(invalidRemark1, "Client"), "Remark with too few words should fail");

        // Second invalid remark
        assertFalse(pres.addRemark(invalidRemark2, "Optometrist"), "Remark with too few words should fail");

        // check neither remark is written to file
        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(invalidRemark1)), "Too short remark should not be written to file");

        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(invalidRemark2)), "Too short remark should not be written to file");
    }

    
    
    
    
    // 		 TEST 10 (ADD REMARK TOO MANY WORDS) 
    @Test
    void testAddRemarkInvalidWordCountTooMany() throws IOException {
        Prescription pres = new Prescription();
        String invalidRemark1 = "This remark is far too long and exceeds the maximum word count, making it invalid lol. I'm just using random words at this point";
        String invalidRemark2 = "This is another invalid remark that contains far too many words in the sentence making it way longer than twenty words.";

        // First invalid remark
        assertFalse(pres.addRemark(invalidRemark1, "Client"), "Remark with too many words should fail");

        // Second invalid remark
        assertFalse(pres.addRemark(invalidRemark2, "Optometrist"), "Remark with too many words should fail");

        // check neither remark is written to file
        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(invalidRemark1)), "Too long remark should not be written to file");

        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(invalidRemark2)), "Too long remark should not be written to file");
    }

    
    
    // 		 TEST 11 (ADD REMARK FIRST WORD IS NOT CAPS)
    @Test
    void testAddRemarkInvalidFirstWordNotCapitalized() throws IOException {
        Prescription pres = new Prescription();
        String invalidRemark1 = "this is not capitalized properly.";
        String invalidRemark2 = "another failure in capitalization.";

        // First invalid remark
        assertFalse(pres.addRemark(invalidRemark1, "Client"), "First word not capitalized should fail");

        // Second invalid remark
        assertFalse(pres.addRemark(invalidRemark2, "Optometrist"), "First word not capitalized should fail");

        // check neither remark is written to file
        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(invalidRemark1)), "Not capitalized remark should not be written to file");

        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(invalidRemark2)), "Not capitalized remark should not be written to file");
    }

    
    
    
    // 		 TEST 12 (ADD REMARK CATEGORY INVALID)
    @Test
    void testAddRemarkInvalidCategory() throws IOException {
        Prescription pres = new Prescription();
        String validRemark = "Great service provided by optometrist.";
        String invalidCategory1 = "Customer";  // Invalid category
        String invalidCategory2 = "Visitor";  // Another invalid category

        // First invalid category
        assertFalse(pres.addRemark(validRemark, invalidCategory1), "Remark with invalid category should fail");

        // Second invalid category
        assertFalse(pres.addRemark(validRemark, invalidCategory2), "Remark with invalid category should fail");

        // check remarks with invalid categories arent written to file
        assertFalse(Files.lines(Paths.get("remark.txt"))
                .anyMatch(line -> line.contains(validRemark)), "Remark with invalid category should not be written to file");
    }
}
