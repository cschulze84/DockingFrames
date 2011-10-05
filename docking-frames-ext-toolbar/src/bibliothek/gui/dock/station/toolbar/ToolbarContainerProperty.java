package bibliothek.gui.dock.station.toolbar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import bibliothek.gui.DockStation;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.ToolbarContainerDockStation;
import bibliothek.gui.dock.layout.AbstractDockableProperty;
import bibliothek.gui.dock.layout.DockableProperty;
import bibliothek.util.Path;
import bibliothek.util.Version;
import bibliothek.util.xml.XElement;

/**
 * Describes the location of a child of a {@link ToolbarContainerDockStation}.
 * @author Benjamin Sigg
 */
public class ToolbarContainerProperty  extends AbstractDockableProperty{
	/** the index of the child */
	private int index;
	
	/** the placeholder for the child, can be <code>null</code> */
	private Path placeholder;
	
	/** where the child is in respect to the center of the station */
	private Position position;
	
	/**
	 * Creates a new, empty {@link ToolbarProperty}.
	 */
	public ToolbarContainerProperty(){
		// nothing
	}
	
	/**
	 * Creates a new property.
	 * @param index the index of a child of a {@link DockStation}
	 * @param position where the child is in respect to the center of the station, not <code>null</code>
	 * @param placeholder the name of the child, can be <code>null</code>
	 */
	public ToolbarContainerProperty( int index, Position position, Path placeholder ){
		this.index = index;
		this.position = position;
		this.placeholder = placeholder;
	}
	
	public DockableProperty copy(){
		ToolbarContainerProperty copy = new ToolbarContainerProperty( index, position, placeholder );
		DockableProperty successor = getSuccessor();
		if( successor != null ){
			copy.setSuccessor( successor.copy() );
		}
		return copy;
	}

	@Override
	public String getFactoryID(){
		return ToolbarPropertyFactory.ID;
	}
	
	/**
	 * Gets the location of the {@link Dockable} in its list. 
	 * @return the index
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * Gets the name of the {@link Dockable}.
	 * @return the name, can be <code>null</code>
	 */
	public Path getPlaceholder(){
		return placeholder;
	}
	
	/**
	 * Tells in which list the {@link Dockable} appears.
	 * @return the position, must not be <code>null</code>
	 */
	public Position getPosition(){
		return position;
	}
	
	public void store( DataOutputStream out ) throws IOException{
		Version.write( out, Version.VERSION_1_1_1 );
		out.writeInt( index );
		if( placeholder == null ){
			out.writeBoolean( false );
		}
		else{
			out.writeBoolean( true );
			out.writeUTF( placeholder.toString() );
		}
		switch( position ){
			case CENTER:
				out.write( 0 );
				break;
			case EAST:
				out.write( 1 );
				break;
			case WEST:
				out.write( 2 );
				break;
			case NORTH:
				out.write( 3 );
				break;
			case SOUTH:
				out.write( 4 );
				break;
			default:
				throw new IllegalStateException( "unknown type of position: " + position );
		}
	}

	public void load( DataInputStream in ) throws IOException{
		Version version = Version.read( in );
		if( !version.equals( Version.VERSION_1_1_1 )){
			throw new IOException( "data from an unknown version: " + version );
		}
		index = in.readInt();
		if( in.readBoolean() ){
			placeholder = new Path( in.readUTF() );
		}
		else{
			placeholder = null;
		}
		
		int item = in.read();
		switch( item ){
			case 0:
				position = Position.CENTER;
				break;
			case 1:
				position = Position.EAST;
				break;
			case 2:
				position = Position.WEST;
				break;
			case 3:
				position = Position.NORTH;
				break;
			case 4:
				position = Position.SOUTH;
				break;
			default:
				throw new IllegalStateException( "unknown type of position: " + item );
		}
	}
	
	public void store( XElement element ){
		element.addElement( "index" ).setInt( index );
		if( placeholder != null ){
			element.addElement( "placeholder" ).setString( placeholder.toString() );
		}
		element.addElement( "position" ).setString( position.name() );
	}

	public void load( XElement element ){
		XElement xindex = element.getElement( "index" );
		XElement xplaceholder = element.getElement( "placeholder" );
		XElement xposition = element.getElement( "position" );
		
		if( xindex != null ){
			index = xindex.getInt();
		}
		if( xplaceholder != null ){
			placeholder = new Path( xplaceholder.getString() );
		}
		else{
			placeholder = null;
		}
		
		position = Position.valueOf( xposition.getString() );
	}
}
