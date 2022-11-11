package za.co.rmb.dto;

public class SideDto {

    private String sideId;
    private String description;

    public String getSideId() {
        return sideId;
    }

    public void setSideId(String sideId) {
        this.sideId = sideId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Side{" +
                "sideId='" + sideId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
