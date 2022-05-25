import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class Hero extends Live {
    private final String name = "Hero";
    private Integer health;
    private Integer attackPoint;
    private Integer distanceToResource;
    private Integer positionToBunker;
}
