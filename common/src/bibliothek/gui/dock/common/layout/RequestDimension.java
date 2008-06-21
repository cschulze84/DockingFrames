package bibliothek.gui.dock.common.layout;

import java.awt.Dimension;

import bibliothek.gui.dock.common.intern.CDockable;

/**
 * Represents the dimensions a {@link CDockable} would like to have.
 * @author Benjamin Sigg
 */
public class RequestDimension implements Cloneable{
    /** the preferred width */
    private int width;
    /** whether the preferred width is set */
    private boolean widthSet;
    /** the preferred height */
    private int height;
    /** whether the preferred height is set */
    private boolean heightSet;
    
    /**
     * Creates a new request dimension where width and height are not set.
     */
    public RequestDimension(){
        // nothing
    }
    
    /**
     * Creates a new request dimension copying all fields from <code>original</code>.
     * @param original the original from which to copy all settings
     */
    public RequestDimension( RequestDimension original ){
        this.width = original.width;
        this.widthSet = original.widthSet;
        this.height = original.height;
        this.heightSet = original.heightSet;
    }
    
    /**
     * Creates a new request taking with and height from <code>source</code>.
     * @param size the size of this dimension
     * @see #RequestDimension(int, int)
     */
    public RequestDimension( Dimension size ){
        this( size.width, size.height );
    }
    
    /**
     * Creates a new request dimension where width and height are set.
     * @param width the initial value of width
     * @param height the initial value of height
     * @see #setWidth(int)
     * @see #setHeight(int)
     */
    public RequestDimension( int width, int height ){
        setWidth( width );
        setHeight( height );
    }
    
    /**
     * Creates a new request dimension where either width or height is set.
     * @param value the value for the width or the height
     * @param valueIsWidth if <code>true</code> then <code>value</code> is
     * considered to be the width, else <code>value</code> is considered
     * to be the height
     */
    public RequestDimension( int value, boolean valueIsWidth ){
        if( valueIsWidth )
            setWidth( value );
        else
            setHeight( height );
    }
    
    /**
     * Sets the width of this dimension. This also changes the
     * result of {@link #isWidthSet()} to <code>true</code>.
     * @param width the new width
     */
    public void setWidth( int width ) {
        this.width = width;
        this.widthSet = true;
    }
    
    /**
     * Gets the width of this dimension, clients should first check
     * {@link #isWidthSet()}.
     * @return the width of this dimension
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Tells whether the {@link #getWidth() width} was set or is invalid.
     * @return <code>true</code> if the width is valid
     */
    public boolean isWidthSet() {
        return widthSet;
    }
    
    /**
     * Deletes the width of this dimension. The result of {@link #isWidthSet()}
     * will be <code>false</code>.
     */
    public void deleteWidth(){
        width = 0;
        widthSet = false;
    }
    
    /**
     * Sets the height of this dimension. This also changes the 
     * result of {@link #isHeightSet()} to <code>true</code>.
     * @param height the new height
     */
    public void setHeight( int height ) {
        this.height = height;
        this.heightSet = true;
    }
    
    /**
     * Gets the height of this dimension, clients should first check
     * {@link #isHeightSet()}.
     * @return the height of this dimension
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Tells whether the {@link #setHeight(int) height} of this dimension was
     * or is invalid.
     * @return <code>true</code> if the height is valid
     */
    public boolean isHeightSet() {
        return heightSet;
    }
    
    /**
     * Deletes the height of this dimension. The result of
     * {@link #isHeightSet()} will change to <code>false</code>.
     */
    public void deleteHeight(){
        this.height = 0;
        this.heightSet = false;
    }
    
    @Override
    public RequestDimension clone(){
        try{
            return (RequestDimension)super.clone();
        }
        catch( CloneNotSupportedException ex ){
            // that should not happen
            throw new RuntimeException( ex );
        }
    }
    
    @Override
    public String toString() {
        return getClass() + 
            "[width=" + (widthSet ? String.valueOf( width ) : "" ) +
            ", height=" + (heightSet ? String.valueOf( height ) : "" ) +
            "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + ( heightSet ? 1231 : 1237 );
        result = prime * result + width;
        result = prime * result + ( widthSet ? 1231 : 1237 );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if( this == obj )
            return true;
        if( obj == null )
            return false;
        if( getClass() != obj.getClass() )
            return false;
        final RequestDimension other = (RequestDimension)obj;
        if( height != other.height )
            return false;
        if( heightSet != other.heightSet )
            return false;
        if( width != other.width )
            return false;
        if( widthSet != other.widthSet )
            return false;
        return true;
    }
    
    
}
