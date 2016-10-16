package com.ryt.web.entity.pili;

import com.ryt.web.common.MessageConfig;
import com.ryt.web.entity.pili.Auth.MacKeys;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.entity.stream.StStream.StreamList;

public class Pili {

    private Auth mAuth;
    private String mHubName;
    private String access_key;
    private String secret_key;
    //private API api = new API();
    
    public Pili(){
    	
    }
    public Pili(final String ak, final String sk, final String hubName) {
    	this.access_key = ak;
    	this.secret_key = sk;
        MacKeys macKeys = new MacKeys(ak, sk);
        mAuth = new Auth(macKeys);
        if (hubName == null) {
            throw new IllegalArgumentException(MessageConfig.NULL_HUBNAME_EXCEPTION_MSG);
        }
        mHubName = hubName;
    }
    
    public String getAccess_key() {
		return access_key;
	}


	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}


	public String getSecret_key() {
		return secret_key;
	}


	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}
	
	public String getmHubName() {
		return mHubName;
	}

	public void setmHubName(String mHubName) {
		this.mHubName = mHubName;
	}
	
	public Auth getmAuth() {
		return mAuth;
	}
	public void setmAuth(Auth mAuth) {
		this.mAuth = mAuth;
	}
	/*
	public API getApi() {
		return api;
	}
	public void setApi(API api) {
		this.api = api;
	}
	*/
	public StStream createStream() throws PiliException {
        return API.createStream(mAuth, mHubName, null, null, null);
    }

    public StStream createStream(String title, String publishKey, String publishSecurity) throws PiliException {
        return API.createStream(mAuth, mHubName, title, publishKey, publishSecurity);
    }

    public StStream getStream(String streamId) throws PiliException {
        return API.getStream(mAuth, streamId);
    }

    public StreamList listStreams() throws PiliException {
        return API.listStreams(mAuth, mHubName, null, 0);
    }

    public StreamList listStreams(String marker, long limit) throws PiliException {
        return API.listStreams(mAuth, mHubName, marker, limit);
    }

}
