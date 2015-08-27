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

package co.ryred.bess.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;

import java.util.Scanner;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 26/08/2015.
 */
public class BEssPlugin extends Plugin
{

	@Override
	public void onLoad()
	{

		try {

			// YES I KNOW THIS IS DIRTY. :(
			// CBA to make a shade resource transformer to replace it.

			String build;
			try {
				build = new Scanner( BEssPlugin.class.getResourceAsStream( "BUILD.txt" ), "UTF-8" ).useDelimiter( "\\A" ).next();
			} catch ( Exception e ) {
				build = ";";
			}

			PluginDescription pdf = getDescription();
			pdf.setVersion( pdf.getVersion().replace( "[[[env.MASTER_BUILD]]]", build ) );

		} catch ( Exception e ) {}

	}

}
