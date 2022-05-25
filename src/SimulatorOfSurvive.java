import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulatorOfSurvive {
    public static void main(String[] args) {
        SimulatorOfSurvive simulatorOfSurvive = new SimulatorOfSurvive();

        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        while (input.hasNext()) {
            String line = input.nextLine();
            lines.add(line);

            if (line.equals("exit")) {
                lines.remove(lines.get(lines.size() - 1));
                break;
            }
        }

        AttackInDistance attackInDistance = new AttackInDistance();
        attackInDistance.attack(simulatorOfSurvive.simulateLiveList(lines));

    }

    public List<Live> simulateLiveList(List<String> lines) {
        List<Live> liveList = new ArrayList<>();
        Hero hero = new Hero();
        Enemy enemy;
        List<Enemy> enemyList = new ArrayList<>();

        for (String line : lines) {
            String[] lineArray = line.split(" ");

            if (lineArray[0].equals("Resources")) {
                hero.setDistanceToResource(Integer.parseInt(lineArray[2]));
                hero.setPositionToBunker(0);
                liveList.add(hero);
            } else if (lineArray[0].equals("Hero") && lineArray[3].equals("hp")) {
                hero.setHealth(Integer.parseInt(lineArray[2]));
            } else if (lineArray[0].equals("Hero") && lineArray[1].equals("attack")) {
                hero.setAttackPoint(Integer.parseInt(lineArray[3]));
            } else if (lineArray[2].equals("Enemy")) {
                enemy = new Enemy();
                enemy.setName(lineArray[0]);
                enemyList.add(enemy);
            } else if (lineArray[3].equals("hp") && SurvivorUtil.containsName(enemyList, lineArray[0])) {
                enemy = SurvivorUtil.getEnemyByName(enemyList, lineArray[0]);
                enemy.setHealth(Integer.parseInt(lineArray[2]));
            } else if (lineArray[1].equals("attack") && SurvivorUtil.containsName(enemyList, lineArray[0])) {
                enemy = SurvivorUtil.getEnemyByName(enemyList, lineArray[0]);
                enemy.setAttackPoint(Integer.parseInt(lineArray[3]));
            } else if (lineArray[0].equals("There")) {
                enemy = SurvivorUtil.getEnemyByName(enemyList, lineArray[3]);
                if (enemy.getDistanceToResource() != null) {
                    Enemy enemy2 = new Enemy();
                    enemy2.setName(enemy.getName());
                    enemy2.setHealth(enemy.getHealth());
                    enemy2.setAttackPoint(enemy.getAttackPoint());
                    enemy2.setDistanceToResource(hero.getDistanceToResource()-Integer.parseInt(lineArray[6]));
                    enemy2.setPositionToBunker(Integer.parseInt(lineArray[6]));
                    enemyList.add(enemy2);
                } else {
                    enemy.setDistanceToResource(hero.getDistanceToResource()-Integer.parseInt(lineArray[6]));
                    enemy.setPositionToBunker(Integer.parseInt(lineArray[6]));
                }
            }
        }
        System.out.println(enemyList);
        liveList.addAll(enemyList);
        liveList.forEach(System.out::println);
        return liveList;
    }

}
