package me.wheezygold.skripthack.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.wheezygold.skripthack.SKU;
import me.wheezygold.skripthack.SkriptHack;
import org.bukkit.event.Event;

@SuppressWarnings("unused")
@SKU.data(
        name = "Disable Skript Registrations",
        desc = "Closes Skript's registrations for any new syntax elements.",
        example = "disable skript-hack"
)
public class EffDisableHack extends Effect {

    static {
        Skript.registerEffect(EffDisableHack.class, "(disable|stop)[ the] skript(-| )hack");
    }

    @Override
    protected void execute(Event event) {
        try {
            SkriptHack.getInstance().hackSkript(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Skript.error("Unable to hack skript!");
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "(disable|stop)[ the] skript(-| )hack";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
