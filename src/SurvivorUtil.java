import java.util.List;

public class SurvivorUtil {

    public static boolean containsName(final List<Enemy> list, final String name) {
        return list.stream().map(Enemy::getName).anyMatch(name::equals);
    }

    public static Enemy getEnemyByName(final List<Enemy> list, final String name) {
        return list.stream().filter(enemy -> enemy.getName().equals(name)).findFirst().get();
    }
}
