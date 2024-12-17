package delivery.app.DTO;


public class LunchResponseDTO {

    private Long lunchId;
    private Long mainCourseId;
    private Long dessertId;

    public LunchResponseDTO(Long lunchId, Long mainCourseId, Long dessertId) {
        this.lunchId = lunchId;
        this.mainCourseId = mainCourseId;
        this.dessertId = dessertId;
    }

    public Long getLunchId() {
        return lunchId;
    }

    public void setLunchId(Long lunchId) {
        this.lunchId = lunchId;
    }

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
}
