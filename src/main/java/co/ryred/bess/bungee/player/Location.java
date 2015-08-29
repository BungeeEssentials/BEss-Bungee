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

import co.ryred.bess.util.NumberConversions;

/**
 * Represents a 3-dimensional position in a world
 */
public class Location implements Cloneable
{

	private String world;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;

	/**
	 * Constructs a new Location with the given coordinates
	 *
	 * @param world The world in which this location resides
	 * @param x     The x-coordinate of this new location
	 * @param y     The y-coordinate of this new location
	 * @param z     The z-coordinate of this new location
	 */
	public Location( final String world, final double x, final double y, final double z )
	{
		this( world, x, y, z, 0, 0 );
	}

	/**
	 * Constructs a new Location with the given coordinates and direction
	 *
	 * @param world The world in which this location resides
	 * @param x     The x-coordinate of this new location
	 * @param y     The y-coordinate of this new location
	 * @param z     The z-coordinate of this new location
	 * @param yaw   The absolute rotation on the x-plane, in degrees
	 * @param pitch The absolute rotation on the y-plane, in degrees
	 */
	public Location( final String world, final double x, final double y, final double z, final float yaw, final float pitch )
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	/**
	 * Safely converts a double (location coordinate) to an int (block
	 * coordinate)
	 *
	 * @param loc Precise coordinate
	 * @return Block coordinate
	 */
	public static int locToBlock( double loc )
	{
		return NumberConversions.floor( loc );
	}

	/**
	 * Gets the world that this location resides in
	 *
	 * @return World that contains this location
	 */
	public String getWorld()
	{
		return world;
	}

	/**
	 * Sets the world that this location resides in
	 *
	 * @param world New world that this location resides in
	 */
	public void setWorld( String world )
	{
		this.world = world;
	}

	/**
	 * Gets the x-coordinate of this location
	 *
	 * @return x-coordinate
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Sets the x-coordinate of this location
	 *
	 * @param x X-coordinate
	 */
	public void setX( double x )
	{
		this.x = x;
	}

	/**
	 * Gets the floored value of the X component, indicating the block that
	 * this location is contained with.
	 *
	 * @return block X
	 */
	public int getBlockX()
	{
		return locToBlock( x );
	}

	/**
	 * Gets the y-coordinate of this location
	 *
	 * @return y-coordinate
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * Sets the y-coordinate of this location
	 *
	 * @param y y-coordinate
	 */
	public void setY( double y )
	{
		this.y = y;
	}

	/**
	 * Gets the floored value of the Y component, indicating the block that
	 * this location is contained with.
	 *
	 * @return block y
	 */
	public int getBlockY()
	{
		return locToBlock( y );
	}

	/**
	 * Gets the z-coordinate of this location
	 *
	 * @return z-coordinate
	 */
	public double getZ()
	{
		return z;
	}

	/**
	 * Sets the z-coordinate of this location
	 *
	 * @param z z-coordinate
	 */
	public void setZ( double z )
	{
		this.z = z;
	}

	/**
	 * Gets the floored value of the Z component, indicating the block that
	 * this location is contained with.
	 *
	 * @return block z
	 */
	public int getBlockZ()
	{
		return locToBlock( z );
	}

	/**
	 * Gets the yaw of this location, measured in degrees.
	 * <ul>
	 * <li>A yaw of 0 or 360 represents the positive z direction.
	 * <li>A yaw of 180 represents the negative z direction.
	 * <li>A yaw of 90 represents the negative x direction.
	 * <li>A yaw of 270 represents the positive x direction.
	 * </ul>
	 * Increasing yaw values are the equivalent of turning to your
	 * right-facing, increasing the scale of the next respective axis, and
	 * decreasing the scale of the previous axis.
	 *
	 * @return the rotation's yaw
	 */
	public float getYaw()
	{
		return yaw;
	}

	/**
	 * Sets the yaw of this location, measured in degrees.
	 * <ul>
	 * <li>A yaw of 0 or 360 represents the positive z direction.
	 * <li>A yaw of 180 represents the negative z direction.
	 * <li>A yaw of 90 represents the negative x direction.
	 * <li>A yaw of 270 represents the positive x direction.
	 * </ul>
	 * Increasing yaw values are the equivalent of turning to your
	 * right-facing, increasing the scale of the next respective axis, and
	 * decreasing the scale of the previous axis.
	 *
	 * @param yaw new rotation's yaw
	 */
	public void setYaw( float yaw )
	{
		this.yaw = yaw;
	}

	/**
	 * Gets the pitch of this location, measured in degrees.
	 * <ul>
	 * <li>A pitch of 0 represents level forward facing.
	 * <li>A pitch of 90 represents downward facing, or negative y
	 * direction.
	 * <li>A pitch of -90 represents upward facing, or positive y direction.
	 * </ul>
	 * Increasing pitch values the equivalent of looking down.
	 *
	 * @return the incline's pitch
	 */
	public float getPitch()
	{
		return pitch;
	}

	/**
	 * Sets the pitch of this location, measured in degrees.
	 * <ul>
	 * <li>A pitch of 0 represents level forward facing.
	 * <li>A pitch of 90 represents downward facing, or negative y
	 * direction.
	 * <li>A pitch of -90 represents upward facing, or positive y direction.
	 * </ul>
	 * Increasing pitch values the equivalent of looking down.
	 *
	 * @param pitch new incline's pitch
	 */
	public void setPitch( float pitch )
	{
		this.pitch = pitch;
	}

	/**
	 * Adds the location by another.
	 *
	 * @param vec The other location
	 * @return the same location
	 * @throws IllegalArgumentException for differing worlds
	 */
	public Location add( Location vec )
	{
		if ( vec == null || vec.getWorld() != getWorld() ) {
			throw new IllegalArgumentException( "Cannot add Locations of differing worlds" );
		}

		x += vec.x;
		y += vec.y;
		z += vec.z;
		return this;
	}

	/**
	 * Adds the location by another. Not world-aware.
	 *
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 * @return the same location
	 */
	public Location add( double x, double y, double z )
	{
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	/**
	 * Subtracts the location by another.
	 *
	 * @param vec The other location
	 * @return the same location
	 * @throws IllegalArgumentException for differing worlds
	 */
	public Location subtract( Location vec )
	{
		if ( vec == null || vec.getWorld() != getWorld() ) {
			throw new IllegalArgumentException( "Cannot add Locations of differing worlds" );
		}

		x -= vec.x;
		y -= vec.y;
		z -= vec.z;
		return this;
	}

	/**
	 * Subtracts the location by another. Not world-aware and
	 * orientation independent.
	 *
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param z Z coordinate
	 * @return the same location
	 */
	public Location subtract( double x, double y, double z )
	{
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	/**
	 * Gets the magnitude of the location, defined as sqrt(x^2+y^2+z^2). The
	 * value of this method is not cached and uses a costly square-root
	 * function, so do not repeatedly call this method to get the location's
	 * magnitude. NaN will be returned if the inner result of the sqrt()
	 * function overflows, which will be caused if the length is too long. Not
	 * world-aware and orientation independent.
	 *
	 * @return the magnitude
	 */
	public double length()
	{
		return Math.sqrt( NumberConversions.square( x ) + NumberConversions.square( y ) + NumberConversions.square( z ) );
	}

	/**
	 * Gets the magnitude of the location squared. Not world-aware and
	 * orientation independent.
	 *
	 * @return the magnitude
	 */
	public double lengthSquared()
	{
		return NumberConversions.square( x ) + NumberConversions.square( y ) + NumberConversions.square( z );
	}

	/**
	 * Get the distance between this location and another. The value of this
	 * method is not cached and uses a costly square-root function, so do not
	 * repeatedly call this method to get the location's magnitude. NaN will
	 * be returned if the inner result of the sqrt() function overflows, which
	 * will be caused if the distance is too long.
	 *
	 * @param o The other location
	 * @return the distance
	 * @throws IllegalArgumentException for differing worlds
	 */
	public double distance( Location o )
	{
		return Math.sqrt( distanceSquared( o ) );
	}

	/**
	 * Get the squared distance between this location and another.
	 *
	 * @param o The other location
	 * @return the distance
	 * @throws IllegalArgumentException for differing worlds
	 */
	public double distanceSquared( Location o )
	{
		if ( o == null ) {
			throw new IllegalArgumentException( "Cannot measure distance to a null location" );
		}
		else if ( o.getWorld() == null || getWorld() == null ) {
			throw new IllegalArgumentException( "Cannot measure distance to a null world" );
		}
		else if ( o.getWorld() != getWorld() ) {
			throw new IllegalArgumentException( "Cannot measure distance between " + getWorld() + " and " + o.getWorld() );
		}

		return NumberConversions.square( x - o.x ) + NumberConversions.square( y - o.y ) + NumberConversions.square( z - o.z );
	}

	/**
	 * Performs scalar multiplication, multiplying all components with a
	 * scalar. Not world-aware.
	 *
	 * @param m The factor
	 * @return the same location
	 */
	public Location multiply( double m )
	{
		x *= m;
		y *= m;
		z *= m;
		return this;
	}

	/**
	 * Zero this location's components. Not world-aware.
	 *
	 * @return the same location
	 */
	public Location zero()
	{
		x = 0;
		y = 0;
		z = 0;
		return this;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		final Location other = (Location) obj;

		if ( this.world != other.world && ( this.world == null || !this.world.equals( other.world ) ) ) {
			return false;
		}
		if ( Double.doubleToLongBits( this.x ) != Double.doubleToLongBits( other.x ) ) {
			return false;
		}
		if ( Double.doubleToLongBits( this.y ) != Double.doubleToLongBits( other.y ) ) {
			return false;
		}
		if ( Double.doubleToLongBits( this.z ) != Double.doubleToLongBits( other.z ) ) {
			return false;
		}
		if ( Float.floatToIntBits( this.pitch ) != Float.floatToIntBits( other.pitch ) ) {
			return false;
		}
		if ( Float.floatToIntBits( this.yaw ) != Float.floatToIntBits( other.yaw ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 3;

		hash = 19 * hash + ( this.world != null ? this.world.hashCode() : 0 );
		hash = 19 * hash + (int) ( Double.doubleToLongBits( this.x ) ^ ( Double.doubleToLongBits( this.x ) >>> 32 ) );
		hash = 19 * hash + (int) ( Double.doubleToLongBits( this.y ) ^ ( Double.doubleToLongBits( this.y ) >>> 32 ) );
		hash = 19 * hash + (int) ( Double.doubleToLongBits( this.z ) ^ ( Double.doubleToLongBits( this.z ) >>> 32 ) );
		hash = 19 * hash + Float.floatToIntBits( this.pitch );
		hash = 19 * hash + Float.floatToIntBits( this.yaw );
		return hash;
	}

	@Override
	public String toString()
	{
		return "Location{" + "world=" + world + ",x=" + x + ",y=" + y + ",z=" + z + ",pitch=" + pitch + ",yaw=" + yaw + '}';
	}

	@Override
	public Location clone()
	{
		try {
			return (Location) super.clone();
		} catch ( CloneNotSupportedException e ) {
			throw new Error( e );
		}
	}

}