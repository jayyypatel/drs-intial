package harsh.drs_initial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author harsh
 */
public class Disaster {

    private String disasterType;
    private String location;
    private int severity;
    private String description;
    private long timestamp; // To track when the disaster was reported
    private String priority;
    private String logs;

    // Constructor
    public Disaster(String disasterType, String location, int severity, String description) {
        this.disasterType = disasterType;
        this.location = location;
        this.severity = severity;
        this.description = description;
        this.timestamp = System.currentTimeMillis(); // Capture the time of creation
        if (disasterType.equals("Fire") && severity >= 7) {
            this.priority = "High Priority";  // Severe fire, requires immediate response
        } else if (disasterType.equals("Flood") && severity >= 5) {
            this.priority = "Medium Priority";  // Flood with medium severity
        } else if (disasterType.equals("Earthquake") && severity >= 6) {
            this.priority = "High Priority";  // Severe earthquake
        } else if (severity < 4) {
            this.priority = "Low Priority";   // Low severity, may not require immediate action
        }else{
            this.priority = "Medium Priority";
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("disasterRequest.csv", true))) {
            if (disasterType.equalsIgnoreCase("Fire")) {
                this.logs = "Use fire extinguishers if possible, and evacuate the building immediately.\n"
                        + "In case of a medical emergency: Administer first aid, and keep the injured calm until help arrives.\n"
                        + "Fire department is on the way.\n"
                        + "Hospital dispatched an ambulance.\n"
                        + "Police dispatched to maintain safety.\n";
                writer.write("Fire department is on the way:," + location + "," + priority + "," + disasterType +"\n");
                writer.write("Hospital dispatched an ambulance:," + location + "," + priority + "," + disasterType +"\n");
                writer.write("Police dispatched to location:," + location + "," + priority + "," + disasterType +"\n");
            } else if (disasterType.equalsIgnoreCase("Flood")) {
                this.logs = "Move to higher ground, avoid waterlogged areas, and follow evacuation orders.\n"
                        + "Fire department is on the way.\n"
                        + "Police dispatched to maintain safety.\n";
                writer.write("Fire department is on the way," + location + "," + priority + "," + disasterType +"\n");
                writer.write("Police dispatched," + location + "," + priority + "," + disasterType +"\n");
            } else if (disasterType.equalsIgnoreCase("Earthquake")) {
                this.logs = "Drop, cover, and hold on. Stay away from windows and heavy objects.\n"
                        + "Fire department is on the way.\n"
                        + "Hospital dispatched an ambulance.\n"
                        + "Police dispatched to maintain safety.\n";
                writer.write("Fire department is on the way," + location + "," + priority + "," + disasterType +"\n");
                writer.write("Hospital dispatched an ambulance," + location + "," + priority + "," + disasterType +"\n");
                writer.write("Police dispatched to location," + location + "," + priority + "," + disasterType +"\n");
            } else if (disasterType.equalsIgnoreCase("Hurricane")) {
                this.logs = "Drop, cover, and hold on. Stay away from windows and heavy objects.\n"
                        + "Fire department is on the way.\n"
                        + "Hospital dispatched an ambulance.\n"
                        + "Police dispatched to maintain safety.\n";
                writer.write("Fire department is on the way," + location + "," + priority + "," + disasterType +"\n");
                writer.write("Hospital dispatched an ambulance," + location + "," + priority + "," + disasterType +"\n");
                writer.write("Police dispatched," + location + "," + priority + "," + disasterType +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    // Getters and Setters
    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }
    
    public String getLogs() {
        return logs;
    }
    
    public String getPriority() {
        return priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public long getTimestamp() { return timestamp; }

    // toString method
    @Override
    public String toString() {
        return "Disaster Report: [" +
                "Type=" + disasterType +
                ", Location='" + location + '\'' +
                ", Severity=" + severity +
                ", Description='" + description + '\'' +
                ", Priority='" + priority + '\'' +
                ']';
    }
}