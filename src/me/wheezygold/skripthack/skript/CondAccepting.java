package me.wheezygold.skripthack.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.wheezygold.skripthack.SKU;
import org.bukkit.event.Event;

@SuppressWarnings("unused")
@SKU.data(
        name = "Accepting Registrations",
        desc = "Checks to see if skript is accepting any new registrations.",
        example = "if skript is accepting new syntax:"
)
public class CondAccepting extends Condition {

    static {
        Skript.registerCondition(CondAccepting.class, "skript [is](accepting|allowing) [new] (registrations|syntax)");
    }

    @Override
    public boolean check(Event event) {
        return Skript.isAcceptRegistrations();
    }

    @Override
    public String toString(Event event, boolean b) {
        return "skript [is](accepting|allowing) [new] (registrations|syntax)";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
