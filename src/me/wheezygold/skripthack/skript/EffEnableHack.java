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
        name = "Enable Skript Registrations",
        desc = "Opens Skript's registrations for any new syntax elements.",
        example = "enable skript-hack"
)
public class EffEnableHack extends Effect {

    static {
        Skript.registerEffect(EffEnableHack.class, "(enable|start)[ the] skript(-| )hack");
    }

    @Override
    protected void execute(Event event) {
        try {
            SkriptHack.getInstance().hackSkript(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Skript.error("Unable to hack skript!");
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "(enable|start)[ the] skript(-| )hack";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
