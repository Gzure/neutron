/*
 * Copyright (C) 2015 IBM, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.neutron.e2etest;

import java.io.OutputStreamWriter;

import java.lang.Thread;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;

public class NeutronIPSECPoliciesTests {
    String base;

    public NeutronIPSECPoliciesTests(String base) {
        this.base = base;
    }

    public void ipsecPolicy_collection_get_test() {
        String url = base + "/vpn/ipsecpolicies";
        ITNeutronE2E.test_fetch(url, "IPSEC Policy Collection GET failed");
    }

    public void singleton_ipsecPolicy_create_test() {
        String url = base + "/vpn/ipsecpolicies";
        String content = " { \"ipsecpolicy\": { \"name\": \"ipsecpolicy1\"," +
            "\"transform_protocol\": \"esp\"," +
            "\"auth_algorithm\": \"sha1\"," +
            "\"encapsulation_mode\": \"tunnel\"," +
            "\"encryption_algorithm\": \"aes-128\"," +
            "\"pfs\": \"group5\"," +
            "\"tenant_id\": \"ccb81365fe36411a9011e90491fe1330\"," +
            "\"lifetime\": { \"units\": \"seconds\", \"value\": 7200 }," +
            "\"id\": \"5291b189-fd84-46e5-84bd-78f40c05d69c\"," +
            "\"description\": \"\" } }";
        ITNeutronE2E.test_create(url, content, "IPSEC Policy POST failed");
    }

    public void ipsecPolicy_update_test() {
        String url = base + "/vpn/ipsecpolicies/5291b189-fd84-46e5-84bd-78f40c05d69c";
        String content = " { \"ipsecpolicy\": { \"name\": \"ipsecpolicy1\"," +
            "\"transform_protocol\": \"esp\"," +
            "\"auth_algorithm\": \"sha1\"," +
            "\"encapsulation_mode\": \"tunnel\"," +
            "\"encryption_algorithm\": \"aes-128\"," +
            "\"pfs\": \"group14\"," +
            "\"tenant_id\": \"ccb81365fe36411a9011e90491fe1330\"," +
            "\"lifetime\": { \"units\": \"seconds\", \"value\": 3600 }," +
            "\"id\": \"5291b189-fd84-46e5-84bd-78f40c05d69c\"," +
            "\"description\": \"\" } }";
        ITNeutronE2E.test_modify(url, content, "IPSEC Policy PUT failed");
    }

    public void ipsecPolicy_element_get_test() {
        String url = base + "/vpn/ipsecpolicies/5291b189-fd84-46e5-84bd-78f40c05d69c";
        ITNeutronE2E.test_fetch(url, true, "IPSEC Policy Element GET failed");
    }

    public void ipsecPolicy_delete_test() {
        String url = base + "/vpn/ipsecpolicies/5291b189-fd84-46e5-84bd-78f40c05d69c";
        ITNeutronE2E.test_delete(url, "IPSEC Policy DELETE failed");
    }

    public void ipsecPolicy_element_negative_get_test() {
        String url = base + "/vpn/ipsecpolicies/5291b189-fd84-46e5-84bd-78f40c05d69c";
        ITNeutronE2E.test_fetch(url, false, "IPSEC Policy Element Negative GET failed");
    }

    public static void runTests(String base) {
        NeutronIPSECPoliciesTests ipsec_policy_tester = new NeutronIPSECPoliciesTests(base);
        ipsec_policy_tester.ipsecPolicy_collection_get_test();

        ipsec_policy_tester.singleton_ipsecPolicy_create_test();
        ipsec_policy_tester.ipsecPolicy_update_test();
        ipsec_policy_tester.ipsecPolicy_element_get_test();
        ipsec_policy_tester.ipsecPolicy_delete_test();
        ipsec_policy_tester.ipsecPolicy_element_negative_get_test();
    }
}

