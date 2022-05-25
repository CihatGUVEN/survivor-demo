import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AttackInDistance {

    public void attack(List<Live> liveList) {
        Hero hero = new Hero();
        List<Enemy> enemyList = new ArrayList<>();

        for (Live live : liveList) {
            if (live instanceof Hero) {
                hero = (Hero) live;
            }
        }
        for (Live live : liveList) {
            if (live instanceof Enemy) {
                Enemy enemy = (Enemy) live;
                enemyList.add(enemy);
            }
        }

        List<Enemy> sortedEnemyList = enemyList.stream()
                .sorted(Comparator.comparing(Enemy::getPositionToBunker))
                .collect(Collectors.toList());

        System.out.println(hero.getName() + " started journey with " + hero.getHealth() + " HP!");

        for (Enemy enemy : sortedEnemyList) {

            if (hero.getHealth() < 0) {
                break;
            }
            fight(hero, enemy);
        }
        if (hero.getHealth() > 0) {
            System.out.println(hero.getName() + " Survived!");
        }
    }

    public void fight(Hero hero, Enemy enemy) {

        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            enemy.setHealth(enemy.getHealth() - hero.getAttackPoint());
            if(enemy.getHealth() > 0) {
                hero.setHealth(hero.getHealth() - enemy.getAttackPoint());
            }
        }
        if (enemy.getHealth() <= 0) {
            System.out.println(hero.getName() + " defeated " + enemy.getName() + " with "
                    + hero.getHealth() + " HP remaining");
            hero.setDistanceToResource(enemy.getDistanceToResource());
            hero.setPositionToBunker(enemy.getPositionToBunker());
        } else {
            System.out.println(enemy.getName() + " defeated " + hero.getName() + " with "
                    + enemy.getHealth() + " HP remaining");
            System.out.println(hero.getName() + " is Dead!! Last seen at position "
                    + enemy.getPositionToBunker() + "!!");
        }
    }
}
