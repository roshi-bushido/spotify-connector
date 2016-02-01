package org.mule.modules.spotify.config;

import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;

@ConnectionManagement(friendlyName = "Spotify Configuration")
public class ConnectorConfig {
	private ClientCredentials clientCredentials;
	private Api api;
    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String clientId, @Password String clientSecret) throws ConnectionException {
    	try {
            this.api = Api.builder().clientId(clientId).clientSecret(clientSecret).build();
            final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();
            this.clientCredentials = request.get();
            this.api.setAccessToken(clientCredentials.getAccessToken());
    	} catch(Exception e) {
    		throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, e.getMessage(), e.getLocalizedMessage(), e);
    	}
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
    	this.clientCredentials = null;
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        return this.clientCredentials != null;
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "spotify-connection";
    }

	public String getToken() {
		return this.clientCredentials.getAccessToken();
	}

	public Api getApi() {
		return api;
	}
	
}