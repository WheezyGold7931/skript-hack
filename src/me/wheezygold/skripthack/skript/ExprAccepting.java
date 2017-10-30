package me.wheezygold.skripthack.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skripthack.SKU;
import org.bukkit.event.Event;

@SuppressWarnings("unused")
@SKU.data(
        name = "Skript Registration State",
        desc = "Returns the state of skript's registration.",
        example = "set {var} to skript's registration state"
)
public class ExprAccepting extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprAccepting.class, Boolean.class, ExpressionType.SIMPLE, "skript['s] (registration|syntax) [current] state");
    }

    @Override
    protected Boolean[] get(Event event) {
        return new Boolean[] {Skript.isAcceptRegistrations()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "skript{'s] (registration|syntax) [current] state";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
