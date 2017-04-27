package Objects;

/**
 * Created by Myles on 4/25/17.
 */
public class BuyDemand {

    private String professor;
    private String buyDemand;
    private String course;

    public BuyDemand(String professor, String buyDemand, String course) {
        this.professor = professor;
        this.buyDemand = buyDemand;
        this.course = course;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getBuyDemand() {
        return buyDemand;
    }

    public void setBuyDemand(String buyDemand) {
        this.buyDemand = buyDemand;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
