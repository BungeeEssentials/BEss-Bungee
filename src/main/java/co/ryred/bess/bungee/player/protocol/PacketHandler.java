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

import co.ryred.bess.bungee.player.Player;
import co.ryred.bess.bungee.player.protocol.packet.*;
import co.ryred.bess.util.LogsUtil;
import lombok.Getter;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 27/08/2015.
 */
public class PacketHandler
{

	@Getter
	private final Player player;

	public PacketHandler( Player player )
	{
		this.player = player;
	}

	public void handle( PlayerPosition position )
	{
		LogsUtil.info( getPlayer().getName() + " CALLED POSITION." );
	}

	public void handle( PlayerOnGround playerOnGround )
	{
		LogsUtil.info( getPlayer().getName() + " CALLED ONGROUND." );
	}

	public void handle( PlayerLook playerLook )
	{
		LogsUtil.info( getPlayer().getName() + " CALLED LOOK." );
	}

	public void handle( PlayerPosLook playerPosLook )
	{
		LogsUtil.info( getPlayer().getName() + " CALLED POSITIONLOOK." );
	}

	public void handle( HeldItemChange heldItemChange )
	{
		LogsUtil.info( getPlayer().getName() + " CALLED ITEMCHANGE." );
	}

	public void handle( Animation animation )
	{
		LogsUtil.info( getPlayer().getName() + " CALLED ARM SWING (ANIMATION)." );
	}

}
