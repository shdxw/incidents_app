package com.example.myapplication;

import java.time.LocalDateTime;

public class State {
    private String sysName;
    private String status;
    private int ticketID;
    private String reportedBy;
    private LocalDateTime nowDate;
    private LocalDateTime lastDate;
    private String description;

    public State(String sysName, String status, int ticketID, LocalDateTime nowDate, LocalDateTime lastDate, String description) {
        this.sysName = sysName;
        this.status = status;
        this.ticketID = ticketID;
        this.nowDate = nowDate;
        this.lastDate = lastDate;
        this.description = description;
    }

    public String getSysName() {
        return sysName;
    }

    public String getStatus() {
        return status;
    }

    public int getTicketID() {
        return ticketID;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public LocalDateTime getNowDate() {
        return nowDate;
    }

    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "State{" +
                "sysName='" + sysName + '\'' +
                ", status='" + status + '\'' +
                ", ticketID=" + ticketID +
                ", reportedBy='" + reportedBy + '\'' +
                ", nowDate=" + nowDate +
                ", lastDate=" + lastDate +
                ", description='" + description + '\'' +
                '}';
    }
}
