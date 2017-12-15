package Booking.filmList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FilmData {
	public SimpleIntegerProperty serialNo = new SimpleIntegerProperty();
	public SimpleStringProperty title = new SimpleStringProperty("");
	public SimpleStringProperty director = new SimpleStringProperty("");
	public SimpleStringProperty description = new SimpleStringProperty("");
	public SimpleStringProperty imageURL = new SimpleStringProperty("");


	public FilmData(){
		this(1,"","","","");
	}

	public FilmData(int serial, String tit,String dir, String des, String ima){
		setSerialNo(serial);
		setDirector(dir);
		setTitle(tit);
		setDescription(des);
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

	public String getDirector(){
		return director.get();
	}

	public void setDirector(String d){
		director.set(d);
	}

	public String getDescription(){
		return description.get();
	}

	public void setDescription(String d){
		description.set(d);
	}

	public String getImageURL(){return imageURL.get();}

	public void setImageURL(String i) {
		this.imageURL.set(i);
	}
}
