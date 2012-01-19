package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bibliothek.gui.DockFrontend;
import bibliothek.gui.DockStation;
import bibliothek.gui.Dockable;
import bibliothek.gui.Orientation;
import bibliothek.gui.dock.ComponentDockable;
import bibliothek.gui.dock.ExpandableToolbarItemStrategy;
import bibliothek.gui.dock.ScreenDockStation;
import bibliothek.gui.dock.ToolbarContainerDockStation;
import bibliothek.gui.dock.ToolbarGroupDockStation;
import bibliothek.gui.dock.action.ActionContentModifier;
import bibliothek.gui.dock.action.ActionGuard;
import bibliothek.gui.dock.action.DefaultDockActionSource;
import bibliothek.gui.dock.action.DockActionSource;
import bibliothek.gui.dock.action.LocationHint;
import bibliothek.gui.dock.action.actions.SimpleButtonAction;
import bibliothek.gui.dock.frontend.FrontendEntry;
import bibliothek.gui.dock.station.support.PlaceholderStrategy;
import bibliothek.gui.dock.station.support.PlaceholderStrategyListener;
import bibliothek.gui.dock.toolbar.expand.DefaultExpandableToolbarItemStrategy;
import bibliothek.gui.dock.toolbar.expand.ExpandedState;
import bibliothek.gui.dock.util.DockUtilities;
import bibliothek.gui.dock.util.DockUtilities.DockVisitor;
import bibliothek.util.Path;

public class CloseButtonInCore {
	public static void main( String[] args ){
		JFrame frame = new JFrame();

		final DockFrontend frontend = new DockFrontend( frame );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setBounds( 20, 20, 400, 400 );

		frontend.getDockProperties().set( ExpandableToolbarItemStrategy.STRATEGY, new DefaultExpandableToolbarItemStrategy(){
			public boolean isEnabled( Dockable item, ExpandedState state ){
				return super.isEnabled( item, state ) && state != ExpandedState.EXPANDED;
			}
		});
		frontend.getController().addActionGuard( new ToolbarGroupClosing( frontend ) );
		
		JPanel pane = new JPanel( new BorderLayout() );
		frame.add( pane );
		
		ScreenDockStation screen = new ScreenDockStation( frame );
		frontend.addRoot( "screen", screen );

		ToolbarContainerDockStation toolbarStationWest = new ToolbarContainerDockStation( Orientation.VERTICAL );
		ToolbarContainerDockStation toolbarStationNorth = new ToolbarContainerDockStation( Orientation.HORIZONTAL );

		frontend.addRoot( "west", toolbarStationWest );
		frontend.addRoot( "north", toolbarStationNorth );

		pane.add( toolbarStationWest.getComponent(), BorderLayout.WEST );
		pane.add( toolbarStationNorth.getComponent(), BorderLayout.NORTH );

		final ComponentDockable dockable1 = createDockable( "1", "One" );
		final ComponentDockable dockable2 = createDockable( "2", "Two" );
		final ComponentDockable dockable3 = createDockable( "3", "Three" );
		final ComponentDockable dockable4 = createDockable( "4", "Four" );
		
		/*
		 * This is how a PlaceholderStrategy would look like. It just needs a method that takes a Dockable,
		 * and tells what placeholder this dockable has.
		 * You could store the placeholder directly in the ComponentDockable and make a cast...
		 */
		frontend.getDockProperties().set( PlaceholderStrategy.PLACEHOLDER_STRATEGY, new PlaceholderStrategy(){
			@Override
			public void uninstall( DockStation station ){
				// ignore
			}
			
			@Override
			public void removeListener( PlaceholderStrategyListener listener ){
				// ignore	
			}
			
			@Override
			public boolean isValidPlaceholder( Path placeholder ){
				return true;
			}
			
			@Override
			public void install( DockStation station ){
				// ignore
			}
			
			@Override
			public Path getPlaceholderFor( Dockable dockable ){
				String postfix = null;
				if( dockable == dockable1 ){
					postfix = "dock1";
				}
				else if( dockable == dockable2 ){
					postfix = "dock2";
				}
				else if( dockable == dockable3 ){
					postfix = "dock3";
				}
				else if( dockable == dockable4 ){
					postfix = "dock4";
				}
				else{
					return null;
				}
				
				return new Path( "custom", postfix );
			}
			
			@Override
			public void addListener( PlaceholderStrategyListener listener ){
				// ignore	
			}
		} );

		frontend.setDefaultHideable( false );
		
		frontend.addDockable( "d1", dockable1 );
		frontend.addDockable( "d2", dockable2 );
		frontend.addDockable( "d3", dockable3 );
		frontend.addDockable( "d4", dockable4 );

		toolbarStationWest.drop( dockable1 );
		dockable1.getDockParent().drop( dockable2 );
		dockable1.getDockParent().drop( dockable3 );
		dockable1.getDockParent().drop( dockable4 );

		JButton openAll = new JButton( "open all closed buttons" );
		pane.add( openAll, BorderLayout.CENTER );
		openAll.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent e ){
				for( Dockable dockable : frontend.listDockables() ){
					frontend.show( dockable );
				}
			}
		} );
		
		screen.setShowing( true );
		frame.setVisible( true );
	}

	private static ComponentDockable createDockable( String small, String large ){
		final ComponentDockable dockable = new ComponentDockable();
		dockable.setComponent( new JButton( small ), ExpandedState.SHRUNK );
		dockable.setComponent( new JButton( large ), ExpandedState.STRETCHED );
		return dockable;
	}
	
	public static class CloseIcon implements Icon{
		private Color color;
		
		public CloseIcon( Color color ){
			this.color = color;
		}
		
		public int getIconWidth(){
			return 8;
		}
		
		public int getIconHeight(){
			return 8;
		}
		
		@Override
		public void paintIcon( Component c, Graphics g, int x, int y ){
			g.setColor( color );
			g.drawLine( x+2, y+2, x+7, y+7 );
			g.drawLine( x+3, y+2, x+7, y+6 );
			g.drawLine( x+2, y+3, x+6, y+7 );
			
			g.drawLine( x+2, y+7, x+7, y+2 );
			g.drawLine( x+3, y+7, x+6, y+2 );
			g.drawLine( x+2, y+6, x+7, y+3 );
		}
	}
	
	public static class ToolbarGroupClosing extends SimpleButtonAction implements ActionGuard{
		private DockFrontend frontend;
		
		public ToolbarGroupClosing( DockFrontend frontend ){
			this.frontend = frontend;
			
			setText( "Close" );
			setTooltip( "Close this toolbar" );
			setIcon( ActionContentModifier.NONE_HOVER, new CloseIcon( Color.RED ) );
			setIcon( new CloseIcon( Color.LIGHT_GRAY ) );
		}
		
		@Override
		public void action( Dockable dockable ){
			DockUtilities.visit( dockable, new DockVisitor(){
				@Override
				public void handleDockable( Dockable dockable ){
					FrontendEntry entry = frontend.getFrontendEntry( dockable );
					if( entry != null ){
						entry.updateLocation();
					}
				}
			} );
			frontend.hide( dockable );
		}
		
		@Override
		public boolean react( Dockable dockable ){
			return dockable instanceof ToolbarGroupDockStation;
		}

		@Override
		public DockActionSource getSource( Dockable dockable ){
			return new DefaultDockActionSource( new LocationHint( LocationHint.ACTION_GUARD, LocationHint.RIGHT_OF_ALL ), this );
		}
		
	}
}