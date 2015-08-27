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

package co.ryred.bess.bungee.player.protocol;

import co.ryred.bess.bungee.player.protocol.packet.*;
import com.google.common.base.Preconditions;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.protocol.BadPacketException;
import net.md_5.bungee.protocol.ProtocolConstants;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 27/08/2015.
 */
public enum Protocol
{

	// Undef
	GAME
			{

				{

					//TO_CLIENT.registerPacket( 0x00, KeepAlive.class );
					//TO_CLIENT.registerPacket( 0x01, Login.class );
					//TO_CLIENT.registerPacket( 0x02, Chat.class );
					//TO_CLIENT.registerPacket( 0x07, Respawn.class );
					//TO_CLIENT.registerPacket( 0x38, PlayerListItem.class );
					//TO_CLIENT.registerPacket( 0x3A, TabCompleteResponse.class );
					//TO_CLIENT.registerPacket( 0x3B, ScoreboardObjective.class );
					//TO_CLIENT.registerPacket( 0x3C, ScoreboardScore.class );
					//TO_CLIENT.registerPacket( 0x3D, ScoreboardDisplay.class );
					//TO_CLIENT.registerPacket( 0x3E, Team.class );
					//TO_CLIENT.registerPacket( 0x3F, PluginMessage.class );
					//TO_CLIENT.registerPacket( 0x40, Kick.class );
					//TO_CLIENT.registerPacket( 0x45, Title.class );
					//TO_CLIENT.registerPacket( 0x46, SetCompression.class );
					//TO_CLIENT.registerPacket( 0x47, PlayerListHeaderFooter.class );

					TO_SERVER.registerPacket( 0x03, PlayerOnGround.class );
					TO_SERVER.registerPacket( 0x04, PlayerPosition.class );
					TO_SERVER.registerPacket( 0x05, PlayerLook.class );
					TO_SERVER.registerPacket( 0x06, PlayerPosLook.class );
					TO_SERVER.registerPacket( 0x09, HeldItemChange.class );
					TO_SERVER.registerPacket( 0x0A, Animation.class );

				}
			};

	/*========================================================================*/
	public static final int MAX_PACKET_ID = 0xFF;
	public static List<Integer> supportedVersions = Arrays.asList(
			ProtocolConstants.MINECRAFT_1_7_2,
			ProtocolConstants.MINECRAFT_1_7_6,
			ProtocolConstants.MINECRAFT_1_8
	);
	/*========================================================================*/
	public final DirectionData TO_SERVER = new DirectionData( ProtocolConstants.Direction.TO_SERVER );
	public final DirectionData TO_CLIENT = new DirectionData( ProtocolConstants.Direction.TO_CLIENT );

	@RequiredArgsConstructor
	public class DirectionData
	{

		@Getter
		private final ProtocolConstants.Direction direction;
		private final TObjectIntMap<Class<? extends SaidPacket>> packetMap = new TObjectIntHashMap<>( MAX_PACKET_ID );
		private final Class<? extends SaidPacket>[] packetClasses = new Class[ MAX_PACKET_ID ];
		private final Constructor<? extends SaidPacket>[] packetConstructors = new Constructor[ MAX_PACKET_ID ];

		public boolean hasPacket(int id)
		{
			return id < MAX_PACKET_ID && packetConstructors[id] != null;
		}

		public final SaidPacket createPacket(int id)
		{
			if ( id > MAX_PACKET_ID )
			{
				throw new BadPacketException( "Packet with id " + id + " outside of range " );
			}
			if ( packetConstructors[id] == null )
			{
				throw new BadPacketException( "No packet with id " + id );
			}

			try
			{
				return packetConstructors[id].newInstance();
			} catch ( ReflectiveOperationException ex )
			{
				throw new BadPacketException( "Could not construct packet with id " + id, ex );
			}
		}

		protected final void registerPacket(int id, Class<? extends SaidPacket> packetClass)
		{
			try
			{
				packetConstructors[id] = packetClass.getDeclaredConstructor();
			} catch ( NoSuchMethodException ex )
			{
				throw new BadPacketException( "No NoArgsConstructor for packet class " + packetClass );
			}
			packetClasses[id] = packetClass;
			packetMap.put( packetClass, id );
		}

		protected final void unregisterPacket(int id)
		{
			packetMap.remove( packetClasses[id] );
			packetClasses[id] = null;
			packetConstructors[id] = null;
		}

		final int getId(Class<? extends SaidPacket> packet)
		{
			Preconditions.checkArgument( packetMap.containsKey( packet ), "Cannot get ID for packet " + packet );

			return packetMap.get( packet );
		}
	}

}
