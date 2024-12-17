package delivery.app.DTO;

import java.util.List;

public class OrderResponseDTO {
    private Long orderId;
    private Long lunchId;
    private Long beverageId;
    private Long waiterId;
    private List<OrderedAdditionalDTO> orderedAdditions;


    public OrderResponseDTO(Long orderId, Long lunchId, Long beverageId, Long waiterId, 
                            List<OrderedAdditionalDTO> orderedAdditions) {
        this.orderId = orderId;
        this.lunchId = lunchId;
        this.beverageId = beverageId;
        this.waiterId = waiterId;
        this.orderedAdditions = orderedAdditions;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getLunchId() {
        return lunchId;
    }

    public void setLunchId(Long lunchId) {
        this.lunchId = lunchId;
    }

    public Long getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(Long beverageId) {
        this.beverageId = beverageId;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }

    public List<OrderedAdditionalDTO> getOrderedAdditions() {
        return orderedAdditions;
    }

    public void setOrderedAdditions(List<OrderedAdditionalDTO> orderedAdditions) {
        this.orderedAdditions = orderedAdditions;
    }
}
