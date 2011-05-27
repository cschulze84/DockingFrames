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
package bibliothek.gui.dock.control;

import bibliothek.gui.DockStation;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.control.relocator.Merger;
import bibliothek.gui.dock.control.relocator.VetoableDockRelocatorListener;

/**
 * The {@link DockRelocator} is responsible for executing and managing the basic drag and drop
 * operations. 
 * @author Benjamin Sigg
 */
public interface DockRelocator {
	/**
	 * Adds a listener to this manager. The listener will be informed whenever
	 * a {@link Dockable} is moved.
	 * @param listener the new listener
	 */
	public void addVetoableDockRelocatorListener( VetoableDockRelocatorListener listener );

	/**
	 * Removes a listener from this manager.
	 * @param listener the listener to remove
	 */
	public void removeVetoableDockRelocatorListener( VetoableDockRelocatorListener listener );
	
    /**
     * Tells whether dockables can only be dragged through their title or not.
     * @return <code>true</code> if a Dockable must be dragged through their
     * titles, <code>false</code> if every part of the dockable can be
     * grabbed by the mouse.
     * @see #setDragOnlyTitel(boolean)
     */
    public boolean isDragOnlyTitel();
    
    /**
     * Tells whether dockables can only be dragged through their title or not. 
     * @param dragOnlyTitel <code>true</code> if a Dockable must be dragged through its
     * title, <code>false</code> if every part of the dockable can be
     * grabbed by the mouse.
     */
    public void setDragOnlyTitel( boolean dragOnlyTitel );

    /**
     * Tells whether dropping a {@link Dockable} over itself should result in 
     * canceling the drag and drop operation.
     * @return <code>true</code> if dragging over itself is prevented
     */
    public boolean isPreventMoveover();
    
    /**
     * Tells this {@link DockRelocator} that dropping a {@link Dockable} over itself should
     * result in canceling the drag and drop operation.
     * @param prevent whether to prevent self merging or not
     */
    public void setPreventMoveover( boolean prevent );
    
    /**
     * Gets the distance the user must move the mouse in order to begin a 
     * drag operation.
     * @return the distance in pixel
     */
    public int getDragDistance();

    /**
     * Sets the distance the user must move the mouse in order to begin a 
     * drag operation.
     * @param dragDistance the distance in pixel
     */
    public void setDragDistance( int dragDistance );
    
    /**
     * Gets an algorithm useful for merging two {@link DockStation}s.
     * @return the algorithm, can be <code>null</code>
     */
    public Merger getMerger();
    
    /**
     * Sets an algorithm for merging two {@link DockStation}s.
     * @param merger the new algorithm, can be <code>null</code>
     */
    public void setMerger( Merger merger );
    
    /**
     * Adds a mode to this relocator, a mode can be activated or deactivated
     * when the user presses a button like "ctrl" or "shift" during a 
     * drag and drop operation.
     * @param mode the new mode, not <code>null</code>
     */
    public void addMode( DockRelocatorMode mode );
    
    /**
     * Removes a mode that has earlier been added to this relocator.
     * @param mode the mode to remove
     */
    public void removeMode( DockRelocatorMode mode );
    
    /**
     * Tells whether the user has currently grabbed a dockable and moves
     * the dockable around.
     * @return <code>true</code> if a Dockable is currently dragged
     */
    public boolean isOnMove();
    
    /**
     * Tells whether this relocator currently puts a Dockable. A Dockable
     * is put as soon as the user releases the mouse.
     * @return <code>true</code> if a Dockable is moved
     */
    public boolean isOnPut();    

    /**
     * Creates a device with which drag&amp;drop operations concerning
     * <code>dockable</code> can be initiated and executed.
     * @param dockable the dockable which might be moved
     * @return the new remote
     */
    public DirectRemoteRelocator createDirectRemote( Dockable dockable );
    
    /**
     * Creates a device with which drag&amp;drop operations concerning
     * <code>dockable</code> can be initiated and executed.
     * @param dockable the dockable which might be moved
     * @param forceDrag if this flag is set to <code>true</code>, then dragging will always start even
     * if one of the usual conditions is not met. I.e. dragging will start even if <code>dockable</code>
     * does not have a parent of even if the parent does not allow dragging. This flag should be used
     * with caution.
     * @return the new remote
     */
    public DirectRemoteRelocator createDirectRemote( Dockable dockable, boolean forceDrag );
    
    /**
     * Creates a device with which drag&amp;drop operations concerning
     * <code>dockable</code> can be initiated and executed.
     * @param dockable the dockable which might be moved
     * @return the new remote
     */
    public RemoteRelocator createRemote( Dockable dockable );
    
    /**
     * Creates a device with which drag&amp;drop operations concerning
     * <code>dockable</code> can be initiated and executed.
     * @param dockable the dockable which might be moved
     * @param forceDrag if this flag is set to <code>true</code>, then dragging will always start even
     * if one of the usual conditions is not met. I.e. dragging will start even if <code>dockable</code>
     * does not have a parent of even if the parent does not allow dragging. This flag should be used
     * with caution.
     * @return the new remote
     */
    public RemoteRelocator createRemote( Dockable dockable, boolean forceDrag );
}
