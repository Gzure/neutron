/*
 * Copyright (C) 2015 IBM, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.neutron.e2etest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.lang.Thread;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;

public class NeutronRouterTests {
    String base;

    public NeutronRouterTests(String base) {
        this.base = base;
    }

    public void router_collection_get_test() {
        String url = base + "/routers";
        ITNeutronE2E.test_fetch(url, "Router Collection GET failed");
    }

    //TODO handle SB check
    public void singleton_router_create_test() {
        String url = base + "/routers";
        String content = "{ \"router\": { " +
            "\"status\": \"ACTIVE\", " +
            "\"external_gateway_info\": { " +
                "\"network_id\": \"8ca37218-28ff-41cb-9b10-039601ea7e6b\" }, " +
            "\"name\": \"another_router\", " +
            "\"admin_state_up\": true, " +
            "\"tenant_id\": \"9bacb3c5d39d41a79512987f338cf177\", " +
            "\"id\": \"8604a0de-7f6b-409a-a47c-a1cc7bc77b2e\" } } ";
        ITNeutronE2E.test_create(url, content, "Singleton Router Post Failed NB");
    }

    //TODO handle SB check
    public void router_add_interface_test() {
        String url = base + "/routers/8604a0de-7f6b-409a-a47c-a1cc7bc77b2e/add_router_interface";
        String content = "{ " +
            "\"subnet_id\": \"3b80198d-4f7b-4f77-9ef5-774d54e17126\", " +
            "\"port_id\": \"d8a4cc85-ad78-46ac-b5a1-8e04f16fa51e\", " +
            "\"tenant_id\": \"9bacb3c5d39d41a79512987f338cf177\", " +
            "\"id\": \"8604a0de-7f6b-409a-a47c-a1cc7bc77b2e\"}";
        ITNeutronE2E.test_modify(url, content, "Add Interface to Router Put Failed NB");
    }
}