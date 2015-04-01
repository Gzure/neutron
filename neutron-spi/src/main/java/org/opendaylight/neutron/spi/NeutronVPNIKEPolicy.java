/*
 * Copyright IBM Corporation, 2015.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.neutron.spi;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class NeutronVPNIKEPolicy implements Serializable {
    private static final long serialVersionUID = 1L;

    // See OpenStack Network API v2.0 Reference for description of
    // annotated attributes

    @XmlElement (name = "id")
    String id;

    @XmlElement (name = "tenant_id")
    String tenantID;

    @XmlElement (name = "name")
    String name;

    @XmlElement (name = "description")
    String description;

    @XmlElement (name = "auth_algorithm")
    String authAlgorithm;

    @XmlElement (name = "encryption_algorithm")
    String encryptionAlgorithm;

    @XmlElement (name = "phase1_negotiation_mode")
    String phase1NegotiationMode;

    @XmlElement (name = "pfs")
    String perfectForwardSecrecy;

    @XmlElement (name = "ike_version")
    String ikeVersion;

    @XmlElement (name = "lifetime")
    NeutronVPNLifetime lifetime;

    public NeutronVPNIKEPolicy() {
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getTenantID() {
        return tenantID;
    }

    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthAlgorithm() {
        return authAlgorithm;
    }

    public void setAuthAlgorithm(String authAlgorithm) {
        this.authAlgorithm = authAlgorithm;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public String getPhase1NegotiationMode() {
        return phase1NegotiationMode;
    }

    public void setPhase1NegotiationMode(String phase1NegotiationMode) {
        this.phase1NegotiationMode = phase1NegotiationMode;
    }

    public String getPerfectForwardSecrecy() {
        return perfectForwardSecrecy;
    }

    public void setPerfectForwardSecrecy(String perfectForwardSecrecy) {
        this.perfectForwardSecrecy = perfectForwardSecrecy;
    }

    public String getIkeVersion() {
        return ikeVersion;
    }

    public void setIkeVersion(String ikeVersion) {
        this.ikeVersion = ikeVersion;
    }

    public NeutronVPNLifetime getLifetime() {
        return lifetime;
    }

    public void setLifetime(NeutronVPNLifetime lifetime) {
        this.lifetime = lifetime;
    }

}
