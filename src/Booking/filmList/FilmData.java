package Booking.filmList;

public class FilmData {
		
		private String title;
		private String director;
		
		public FilmData(){
			
			// Default values
			this.title = "";
			this.director = "";
		}
		
		public FilmData(String title, String director){
			this.title = title;
			this.director = director;
		}
		
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}
	
}
