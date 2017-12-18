package Booking.filmList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class defines the FilmData and creates setters and getters as necessary
 */
public class FilmData {
	public SimpleIntegerProperty serialNo = new SimpleIntegerProperty();
	public SimpleStringProperty title = new SimpleStringProperty("");
	public SimpleStringProperty director = new SimpleStringProperty("");
	public SimpleStringProperty description = new SimpleStringProperty("");
	public SimpleStringProperty imageURL = new SimpleStringProperty("");
	public SimpleStringProperty seat = new SimpleStringProperty("");
    public SimpleStringProperty showTime = new SimpleStringProperty("");
    public SimpleStringProperty showDate = new SimpleStringProperty("");



    public FilmData(){
		this(1,"","","","","","","");
	}


	public FilmData(int serial, String tit, String dir, String des, String ima, String se, String st, String sd){
		setSerialNo(serial);
		setDirector(dir);
		setTitle(tit);
		setDescription(des);
		setImageURL(ima);
	}

    /**
     * Setter for Film SerialNo
     */
	public void setSerialNo(Integer serialNum){
		serialNo.set(serialNum);
	}

    /**
     * Getter for Film title
     */
	public String getTitle(){
		return title.get();
	}

    /**
     * Setter for Film Title
     */
	public void setTitle(String t){
		title.set(t);
	}

    /**
     * Getter for Film Director
     */
	public String getDirector(){
		return director.get();
	}

    /**
     * Setter for Film Director
     */
	public void setDirector(String d){
		director.set(d);
	}

    /**
     * Getter for Film Description
     */
	public String getDescription(){
		return description.get();
	}

    /**
     * Setter for Film Description
     */
	public void setDescription(String d){
		description.set(d);
	}

    /**
     * Getter for ImageURL
     */
	public String getImageURL(){return imageURL.get();}

	/**
     * Setter for ImageURL
     */
	public void setImageURL(String i) {
		this.imageURL.set(i);
	}

    /**
     * Getter for Seat
     */
    public String getSeat(){return seat.get();}

    /**
     * Setter for Seat
     */
    public void setSeat(String se) {
        this.seat.set(se);
    }

    /**
     * Getter for ShowTime
     */
    public String getShowTime(){return showTime.get();}

    /**
     * Setter for ShowTime
     */
    public void setShowTime(String st) {
        this.showTime.set(st);
    }

    /**
     * Getter for ImageURL
     */
    public String getShowDate(){return showDate.get();}

    /**
     * Setter for ImageURL
     */
    public void setShowDate(String sd) {
        this.showDate.set(sd);
    }
}
