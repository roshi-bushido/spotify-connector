package org.mule.modules.spotify.automation;

import org.junit.Before;
import org.mule.modules.spotify.SpotifyConnector;
import org.mule.tools.devkit.ctf.mockup.ConnectorDispatcher;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

public abstract class AbstractTestCase {
	
	private SpotifyConnector connector;
	private ConnectorDispatcher<SpotifyConnector> dispatcher;
	
	
	protected SpotifyConnector getConnector() {
		return connector;
	}


	protected ConnectorDispatcher<SpotifyConnector> getDispatcher() {
		return dispatcher;
	}

	@Before
	public void init() throws Exception {
		
		//Initialization for single-test run
        ConnectorTestContext.initialize(SpotifyConnector.class, false);
		
		//Context instance
		ConnectorTestContext<SpotifyConnector> context = ConnectorTestContext.getInstance(SpotifyConnector.class);
		
		//Connector dispatcher
		dispatcher = context.getConnectorDispatcher();
		
		connector = dispatcher.createMockup();
		
	}

}
