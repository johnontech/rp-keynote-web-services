package com.nationwide.nf.rp.util;

/**
 * This class contains Web Service configuration properties.
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  -------------------  -------------------------------------------------
 * 5/25/2017   J. Jorgensen         Initial version.
 */
public class WebServiceConfiguration {
    private String endpoint;
    private String trustStoreFile;
    private String trustStorePassword;
    private String soaUserName;
    private String soaPassword;

    public void setSoaUserName(String soaUserName) {
        this.soaUserName = soaUserName;
    }

    public void setSoaPassword(String soaPassword) {
        this.soaPassword = soaPassword;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setTrustStoreFile(String trustStoreFile) {
        this.trustStoreFile = trustStoreFile;
    }

    public String getTrustStoreFile() {
        return trustStoreFile;
    }

    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public String getSoaUserName() {
        return soaUserName;
    }

    public String getSoaPassword() {
        return soaPassword;
    }
}
