package delivery.app.DTO;

public class LunchRequestDTO {
	
	private Long mainCourseId;
	private Long dessertId;
	
	public Long getMainCourseId() {
		return mainCourseId;
	}
	public void setMainCourseId(Long mainCourseId) {
		this.mainCourseId = mainCourseId;
	}
	public Long getDessertId() {
		return dessertId;
	}
	public void setDessertId(Long dessertId) {
		this.dessertId = dessertId;
	}
	public LunchRequestDTO(Long mainCourseId, Long dessertId) {
		super();
		this.mainCourseId = mainCourseId;
		this.dessertId = dessertId;
	}
	public LunchRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

}
