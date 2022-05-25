import lombok.Data;

@Data
public class Enemy extends Live {
    private String name;
    private Integer health;
    private Integer attackPoint;
    private Integer distanceToResource;
    private Integer positionToBunker;
}
