package com.daghlas.miniprn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessToken {

    /**
     * access_token: e7jeOJzAlT6zOWO52mbGmIbMU8sG
     * <p>
     * expires_in: 3599
     * <p>
     * SnR2Z0h3b1l3YW8ydmd2NWJDajVBOWdxQ0dyUUtGREk6anB5QXE5OUFpUUNKVDFicQ
     */

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;

    public AccessToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
