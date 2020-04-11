package com.example.testclientregister;

class AppointMntModel {
    String StartAppointMntDate;
    int state;// if 0 means that date is not available, if 1 means it's available

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStartAppointMntDate() {
        return StartAppointMntDate;
    }

    public void setStartAppointMntDate(String startAppointMntDate) {
        StartAppointMntDate = startAppointMntDate;
    }
}
