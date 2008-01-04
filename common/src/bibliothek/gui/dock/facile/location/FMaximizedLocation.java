/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
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
package bibliothek.gui.dock.facile.location;

import bibliothek.gui.dock.facile.FContentArea;
import bibliothek.gui.dock.facile.FControl;
import bibliothek.gui.dock.facile.FLocation;
import bibliothek.gui.dock.facile.intern.FDockable.ExtendedMode;
import bibliothek.gui.dock.layout.DockableProperty;

/**
 * A location representing the maximized state.
 * @author Benjamin Sigg
 */
public class FMaximizedLocation extends FLocation {
	@Override
	public ExtendedMode findMode(){
		return ExtendedMode.MAXIMIZED;
	}

	@Override
	public DockableProperty findProperty( DockableProperty successor ){
		return null;
	}

	@Override
	public String findRoot(){
		return FContentArea.getCenterIdentifier( FControl.CONTENT_AREA_STATIONS_ID );
	}
	
	@Override
	public FLocation aside() {
	    return this;
	}
	
	@Override
    public String toString() {
        return "[maximized]";
    }
}