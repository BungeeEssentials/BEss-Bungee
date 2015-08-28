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
import co.ryred.bess.bungee.player.protocol.Protocol;
import co.ryred.bess.bungee.player.protocol.SaidPacket;
import co.ryred.bess.util.LogsUtil;
import de.inventivegames.packetlistener.handler.PacketHandler;
import de.inventivegames.packetlistener.handler.ReceivedPacket;
import de.inventivegames.packetlistener.handler.SentPacket;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import net.md_5.bungee.protocol.PacketWrapper;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 27/08/2015.
 */
public class ConnectionManager extends PacketHandler
{

	@Getter
	private final BEssPlugin plugin;

	public ConnectionManager( BEssPlugin plugin ) {
		super( plugin );
		this.plugin = plugin;
	}

	@Override
	public void onSend( SentPacket sentPacket )
	{

	}

	@Override
	public void onReceive( ReceivedPacket receivedPacket )
	{

		try {
			if ( PacketWrapper.class.isAssignableFrom( receivedPacket.getSourcePacket().getClass() ) ) {
				PacketWrapper pw = (PacketWrapper) receivedPacket.getSourcePacket();

				if ( receivedPacket.getPlayer() == null ) return;

				ByteBuf buf = pw.buf.copy();

				final int id = SaidPacket.readVarInt( buf );

				if( !Protocol.GAME.TO_SERVER.hasPacket( id ) )
					return;

				SaidPacket packet = Protocol.GAME.TO_SERVER.createPacket( id );
				LogsUtil._D( "RECEIVED PACKET ID " + id );
				packet.read( buf );

				try {
					packet.handle( PlayerManager.get().getPlayer( receivedPacket.getPlayer() ).getConnection() );
				} catch ( Exception e ) {
					e.printStackTrace();
				}

				buf = null;

			}
		} catch (Exception e) {
			if( LogsUtil._D() ) e.printStackTrace();
		}

	}

}
