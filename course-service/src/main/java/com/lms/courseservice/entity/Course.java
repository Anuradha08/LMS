package com.lms.courseservice.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "courseName")
    private String courseName;

    @NotNull(message = "Duration From is mandatory")
    @Column(name="durationFrom")
    private Date durationFrom;

    @NotNull(message = "Duration To is mandatory")
    @Column(name="durationTo")
    private Date durationTo;

    @NotBlank(message = "Description is mandatory")
    @Column(name="description")
    private String description;

    @NotBlank(message = "Technology is mandatory")
    @Column(name="technology")
    private String technology;

    @NotBlank(message = "URL is mandatory")
    @Column(name="launchurl")
    private String launchURL;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(Date durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Date getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(Date durationTo) {
        this.durationTo = durationTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getLaunchURL() {
        return launchURL;
    }

    public void setLaunchURL(String launchURL) {
        this.launchURL = launchURL;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", durationFrom=" + durationFrom +
                ", durationTo=" + durationTo +
                ", description='" + description + '\'' +
                ", technology='" + technology + '\'' +
                ", launchURL='" + launchURL + '\'' +
                '}';
    }
}
