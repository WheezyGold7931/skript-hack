package me.wheezygold.skripthack.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.wheezygold.skripthack.SKU;
import me.wheezygold.skripthack.SkriptHack;
import org.bukkit.event.Event;

@SuppressWarnings("unused")
@SKU.data(
        name = "Open Skript Registrations",
        desc = "Opens Skript's registrations to load any new syntax, for the provided time.",
        example = "enable skript-hack for 1 second"
)
public class EffOpenHack extends Effect {

    static {
        Skript.registerEffect(EffOpenHack.class, "(enable|open) [the] skript(-| )hack for %timespan%");
    }

    private Expression<Timespan> timspan;

    @Override
    protected void execute(Event event) {
        int seconds = Math.round(timspan.getSingle(event).getTicks_i() / 20);
        if (seconds > 60) {
            Skript.warning("You cannot keep registration open for over 60 seconds! Defaulting to 60 seconds.");
            seconds = 60;
        }
        try {
            SkriptHack.getInstance().hackSkript(true);
            SkriptHack.getInstance().getServer().getScheduler().runTaskLater(SkriptHack.getInstance(), () -> {
                try {
                    SkriptHack.getInstance().hackSkript(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }, 20 * seconds); //Not just going to use ticks so stfu.
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Skript.error("Unable to hack skript!");
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "enable skript-hack for %timespan%";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        timspan = (Expression<Timespan>) expressions[0];
        return true;
    }
}
