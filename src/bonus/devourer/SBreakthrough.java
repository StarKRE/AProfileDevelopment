package bonus.devourer;

import bonus.bonuses.Bonus;
import heroes.abstractHero.hero.Hero;
import heroes.abstractHero.skills.Skill;
import javafx.scene.image.ImageView;
import managment.playerManagement.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class SBreakthrough extends Bonus{

    private static final Logger log = LoggerFactory.getLogger(SBreakthrough.class);

    private static final double DAMAGE = 350.0;

    public SBreakthrough(String name, int id, ImageView sprite) {
        super(name, id, sprite);
    }

    @Override
    public final void use() {
        final Player currentPlayer = playerManager.getCurrentTeam().getCurrentPlayer();
        final Player opponentPlayer = playerManager.getOpponentATeam().getCurrentPlayer();

        final Hero opponentHero = opponentPlayer.getHero();
        final List<Skill> opponentSkills = opponentHero.getCollectionOfSkills();
        for (final Skill skill: opponentSkills){
            final boolean levelReached = opponentHero.getLevel() >= skill.getRequiredLevel();
            if (skill.isReady() && levelReached){
                if (currentPlayer.getHero().getDamage(DAMAGE)){
                    log.info("-" + DAMAGE + " HP");
                    actionManager.getEventEngine().handle();
                }
                skill.reset();
            }
        }
    }
}