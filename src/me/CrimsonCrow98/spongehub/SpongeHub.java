package me.CrimsonCrow98.spongehub;

import org.spongepowered.api.plugin.Plugin;

import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;

@Plugin(id = "spongehub", name = "Sponge Hub", version = "1.0")
public class SpongeHub {
	
	public SpongeHub spongehub = this;
	 @Inject
		Game game;
	 
	 @Listener
	    public void onGameInitializationEvent(GameInitializationEvent event) {		 
	        CommandSpec commandHub = CommandSpec.builder()
	                .description(Text.of("Sends player back to the hub."))
	                .executor(new CommandExecutor() {
	                    @Override
	                    public CommandResult execute(CommandSource commandSource, CommandContext commandContext) {
	                    	if(commandSource instanceof Player){
	                    		Player player = (Player) commandSource;
	                    		
	                    		game.getServer().getConsole().sendMessage(Text.of("[SpongeHub] Sending " + player.getName() + " back to the hub!"));
	                    		// Create the channel
	                    		

	                    		// A one-liner to send a player to a server
	                    		game.getChannelRegistrar().createRawChannel(spongehub, "BungeeCord").sendTo(player, buf -> buf.writeString("Connect").writeString("lobby"));
	                    	}
	                        return CommandResult.success();
	                    }
	                })
	                .build();
	        
	        Sponge.getCommandManager().register(this, commandHub, "hub");
	 }
}
