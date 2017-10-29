# skript-hack
Provides syntax registering post-skript loading.

# Commands

##### /skript-hack <true/false> : Toggles to open/close Skript's registrations.

# Skript Syntax

```vbs
Enable Hack - (enable|start)[ the] skript(-| )hack
Disable Hack - (disable|stop)[ the] skript(-| )hack
```

# Developer API

##### skript-hack provides an API for developers to load their syntax after Skript is done accepting new syntax. This could be used in the case of a server owner trying to load a skript addon via an external plugin loader.

```
Methods:

SkriptHack.enableRegistrations() - Enables the SkriptHack allowing for new registrations.

SkriptHack.disableRegistrations() - Disables the SkriptHack denying any new registrations.

````

```java
//Example Use
if (Skript.isAcceptRegistrations()) {
  //Load your syntax
} else {
    if (Bukkit.getPluginManager().getPlugin("skript-hack") == null) { //Make sure the user has this plugin.
        //Disable your plugin, or inform them that you support this plugin.
    }
    try {
        SkriptHack.enableRegistrations(); //Enable's Skript's registrations so your syntax can load [post skript-init]
        //Load your syntax
        SkriptHack.disableRegistrations(); //Closes Skript's registrations
    } catch (NoSuchFieldException | IllegalAccessException e) {
        e.printStackTrace();
    }
}
```
