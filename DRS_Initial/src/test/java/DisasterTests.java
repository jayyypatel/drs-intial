package harsh.drs_initial;

import harsh.drs_initial.Disaster;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class DisasterTests {

    private Disaster disaster;

    @BeforeAll
    public static void setUpClass() {
        // Setup before all tests if needed
    }

    @AfterAll
    public static void tearDownClass() {
        // Cleanup after all tests if needed
    }

    @BeforeEach
    void setUp() {
        disaster = new Disaster("Fire", "New York", 8, "Large fire in downtown area");
    }

    @AfterEach
    void tearDown() {
        // Cleanup after each test if needed
    }

    @Test
    @DisplayName("Test Disaster constructor and getters")
    void testConstructorAndGetters() {
        assertEquals("Fire", disaster.getDisasterType());
        assertEquals("New York", disaster.getLocation());
        assertEquals(8, disaster.getSeverity());
        assertEquals("Large fire in downtown area", disaster.getDescription());
        assertEquals("High Priority", disaster.getPriority());
        assertTrue(disaster.getTimestamp() > 0);
    }

    @Test
    @DisplayName("Test setters")
    void testSetters() {
        disaster.setDisasterType("Flood");
        disaster.setLocation("Miami");
        disaster.setSeverity(5);
        disaster.setDescription("Coastal flooding");

        assertEquals("Flood", disaster.getDisasterType());
        assertEquals("Miami", disaster.getLocation());
        assertEquals(5, disaster.getSeverity());
        assertEquals("Coastal flooding", disaster.getDescription());
    }

    @ParameterizedTest
    @CsvSource({
        "Fire, 7, High Priority",
        "Fire, 6, Medium Priority",
        "Flood, 5, Medium Priority",
        "Flood, 4, Medium Priority",
        "Earthquake, 6, High Priority",
        "Earthquake, 5, Medium Priority",
        "Hurricane, 3, Low Priority"
    })
    @DisplayName("Test priority assignment")
    void testPriorityAssignment(String type, int severity, String expectedPriority) {
        Disaster testDisaster = new Disaster(type, "Test Location", severity, "Test Description");
        assertEquals(expectedPriority, testDisaster.getPriority());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String expected = "Disaster Report: [Type=Fire, Location='New York', Severity=8, Description='Large fire in downtown area', Priority='High Priority']";
        assertEquals(expected, disaster.toString());
    }

    @Test
    @DisplayName("Test log content")
    void testLogContent() {
        String expectedLog = "Use fire extinguishers if possible, and evacuate the building immediately.\n" +
                "In case of a medical emergency: Administer first aid, and keep the injured calm until help arrives.\n" +
                "Fire department is on the way.\n" +
                "Hospital dispatched an ambulance.\n" +
                "Police dispatched to maintain safety.\n";
        assertEquals(expectedLog, disaster.getLogs());
    }

    @Test
    @DisplayName("Test CSV file writing")
    void testCsvFileWriting() {
        List<String> lines = readLastNLinesFromFile("disasterRequest.csv", 3);
        assertTrue(lines.contains("Fire department is on the way:,New York,High Priority,Fire"));
        assertTrue(lines.contains("Hospital dispatched an ambulance:,New York,High Priority,Fire"));
        assertTrue(lines.contains("Police dispatched to location:,New York,High Priority,Fire"));
    }

    private List<String> readLastNLinesFromFile(String filePath, int n) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                if (lines.size() > n) {
                    lines.remove(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
