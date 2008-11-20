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
package bibliothek.gui.dock.common.intern;

import bibliothek.gui.Dockable;
import bibliothek.gui.dock.DockFactory;
import bibliothek.gui.dock.action.DockActionSource;
import bibliothek.gui.dock.common.CStation;
import bibliothek.gui.dock.common.MultipleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;

/**
 * Represents a {@link bibliothek.gui.dock.common.intern.CDockable}.
 * @author Benjamin Sigg
 */
public interface CommonDockable extends Dockable{
	/**
	 * Gets the model of this dockable.
	 * @return the model
	 */
	public CDockable getDockable();
	
	/**
	 * Gets the model of this dockable as station.
	 * @return the model, may be <code>null</code>
	 */
	public CStation getStation();
	
	/**
	 * Gets the {@link DockActionSource} which shows the close-action.
	 * @return the action source
	 */
	public DockActionSource getClose();
	
	/**
	 * Gets the identifier of the {@link DockFactory} which can store and load
	 * this dockable. {@link CommonDockable}s which show a {@link SingleCDockable}
	 * should return {@link CommonSingleDockableFactory#BACKUP_FACTORY_ID} and
	 * the others should return the id of {@link MultipleCDockable#getFactory()}.
	 * @return the name of the factory
	 */
	public String getFactoryID();
}
