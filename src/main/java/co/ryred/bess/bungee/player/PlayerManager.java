/*
 * http://ryred.co/
 * ace[at]ac3-servers.eu
 *
 * =================================================================
 *
 * Copyright (c) 2015, Cory Redmond
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *  Neither the name of BEss-Bungee nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package co.ryred.bess.bungee.player;

import co.ryred.bess.bungee.BEssPlugin;
import co.ryred.bess.util.LogsUtil;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 27/08/2015.
 */
public class PlayerManager implements Listener
{

	public static PlayerManager __INSTANCE__;
	private final BEssPlugin plugin;
	private final ConcurrentHashMap<UUID, Player> uuidMap;
	private final ConcurrentHashMap<String, Player> nameMap;

	public PlayerManager( BEssPlugin plugin )
	{
		this.plugin = plugin;
		this.uuidMap = new ConcurrentHashMap<UUID, Player>();
		this.nameMap = new ConcurrentHashMap<String, Player>();

		new ConnectionManager( plugin );
		plugin.getProxy().getPluginManager().registerListener( plugin, this );

		LogsUtil._D( "Registered & init'd the PlayerManager" );
	}

	public static PlayerManager get() {
		if( __INSTANCE__ == null ) throw new IllegalStateException("Not initialised.");
		return __INSTANCE__;
	}

	public static void initialize( BEssPlugin plugin ) {
		if( __INSTANCE__ != null ) throw new IllegalStateException("Already initialised.");
		__INSTANCE__ = new PlayerManager( plugin );
	}

	@EventHandler( priority = Byte.MAX_VALUE )
	private void onJoin( PostLoginEvent e ) {

		Player player = new Player( e.getPlayer() );
		uuidMap.put( e.getPlayer().getUniqueId(), player );
		nameMap.put( e.getPlayer().getName(), player );

	}

	@EventHandler( priority = Byte.MAX_VALUE )
	private void onJoin( PlayerDisconnectEvent e ) {

		uuidMap.remove( e.getPlayer().getUniqueId() );
		nameMap.remove( e.getPlayer().getName() );

	}

	public Player getPlayer( UUID uuid ) {
		return uuidMap.get( uuid );
	}

	public Player getPlayer( String name ) {
		return nameMap.get( name );
	}

	public Player getPlayer( ProxiedPlayer proxiedPlayer ) {
		Player player;
		if( ( player = uuidMap.get( proxiedPlayer.getUniqueId() ) ) == null )
			player = nameMap.get( proxiedPlayer.getName() );
		return player;
	}

}
