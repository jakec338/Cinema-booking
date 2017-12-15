package Booking.pastBookings;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookingData {
    public SimpleIntegerProperty serialNo = new SimpleIntegerProperty();
    public SimpleStringProperty title = new SimpleStringProperty("");
    public SimpleStringProperty showDate = new SimpleStringProperty("");
    public SimpleStringProperty showTime = new SimpleStringProperty("");
    public SimpleStringProperty seat = new SimpleStringProperty("");


    public BookingData(){
        this(1,"","","","");
    }

    public BookingData(int serial, String tit,String sd, String st, String se){
        setSerialNo(serial);
        setTitle(tit);
        setShowDate(sd);
        setShowTime(st);
        setSeat(se);
    }

    public Integer getSerialNo(){
        return serialNo.get();
    }

    public void setSerialNo(Integer serialNum){
        serialNo.set(serialNum);
    }

    public String getTitle(){
        return title.get();
    }

    public void setTitle(String t){
        title.set(t);
    }

    public String getShowDate(){
        return showDate.get();
    }

    public void setShowDate(String sd){
        showDate.set(sd);
    }

    public String getShowTime(){
        return showTime.get();
    }

    public void setShowTime(String st){
        showTime.set(st);
    }

    public String getSeat(){
        return seat.get();
    }

    public void setSeat(String se){
        seat.set(se);
    }



}
