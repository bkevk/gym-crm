package com.bk.core.model;

public class Trainer extends User {
    private String specialization;
    private Long userId;

    public Trainer() {
        super();
    }

    public Trainer(String firstName, String lastName, boolean isActive, String specialization, Long userId) {
        super(firstName, lastName, isActive);
        this.specialization = specialization;
        this.userId = userId;
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "Trainer{" +
                "userId=" + userId +
                ", specialization='" + specialization + '\'' +
                ", " + super.toString() +
                '}';
    }
}
