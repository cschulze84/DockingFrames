/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2009 Benjamin Sigg
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * CH - Switzerland
 */
package bibliothek.gui.dock.station.stack.tab;

import java.awt.Dimension;
import java.awt.Rectangle;

import bibliothek.gui.dock.station.stack.tab.layouting.Side;

/**
 * The default {@link AxisConversion} assumes:
 * <ul>
 *  <li>the model is a line at the top of some rectangle</li>
 *  <li>the view is a line at one side of some rectangle</li>
 * </ul>
 * @author Benjamin Sigg
 */
public class DefaultAxisConversion implements AxisConversion{
	
	/** side of the view */
	private Side side;
	
	/** available space for the view */
	private Rectangle space;
	
	/**
	 * Creates a new axis converter
	 * @param space the space available for the view, not <code>null</code>
	 * @param side the side of the available <code>space</code> at which the 
	 * view hangs, not <code>null</code>.
	 */
	public DefaultAxisConversion( Rectangle space, Side side ){
		if( space == null )
			throw new IllegalArgumentException( "space must not be null" );
		
		if( side == null )
			throw new IllegalArgumentException( "side must not be null" );
		
		this.side = side;
		this.space = space;
	}

	public Dimension modelToView( Dimension size ){
		switch( side ){
			case TOP:
			case BOTTOM:
				return new Dimension( size );
			case LEFT:
			case RIGHT:
				return new Dimension( size.height, size.width );
			default:
				throw new IllegalStateException( "unknown side: " + side );
		}
	}

	public Rectangle modelToView( Rectangle bounds ){
		switch( side ){
			case TOP:
				return new Rectangle( space.x+bounds.x, space.y+bounds.y, bounds.width, bounds.height );
			case BOTTOM:
				return new Rectangle( space.x+bounds.x, space.y+space.height-bounds.height-bounds.y, bounds.width, bounds.height );
			case LEFT:
				return new Rectangle( space.x+bounds.y, space.y+bounds.x, bounds.height, bounds.width );
			case RIGHT:
				return new Rectangle( space.x+space.width-bounds.height-bounds.y, space.y+bounds.x, bounds.height, bounds.width );
			default:
				throw new IllegalStateException( "unknown side: " + side );	
		}
	}

	public Dimension viewToModel( Dimension size ){
		switch( side ){
			case TOP:
			case BOTTOM:
				return new Dimension( size );
			case LEFT:
			case RIGHT:
				return new Dimension( size.height, size.width );
			default:
				throw new IllegalStateException( "unknown side: " + side );
		}
	}

	public Rectangle viewToModel( Rectangle bounds ){
		switch( side ){
			case TOP:
				return new Rectangle( bounds.x-space.x, bounds.y-space.y, bounds.width, bounds.height );
			case BOTTOM:
				return new Rectangle( bounds.x-space.x, bounds.y+bounds.height-space.y-space.height, bounds.width, bounds.height );
			case LEFT:
				return new Rectangle( space.x-bounds.y, bounds.x-space.y, bounds.height, bounds.width );
			case RIGHT:
				return new Rectangle( bounds.y-space.x-space.width+bounds.height, bounds.x-space.y, bounds.height, bounds.width );
			default:
				throw new IllegalStateException( "unknown side: " + side );
		}
	}
}