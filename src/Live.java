import lombok.Data;

@Data
public abstract class Live {
    private String name;
    private Integer health;
    private Integer attackPoint;
    private Integer distanceToResource;
    private Integer positionToBunker;
}
