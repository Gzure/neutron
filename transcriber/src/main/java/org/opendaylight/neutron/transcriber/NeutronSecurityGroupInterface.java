/*
 * Copyright (c) 2014, 2015 Red Hat, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.neutron.transcriber;

import java.util.List;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.neutron.spi.INeutronSecurityGroupCRUD;
import org.opendaylight.neutron.spi.NeutronSecurityGroup;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.rev150712.Neutron;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.secgroups.rev150712.security.groups.attributes.SecurityGroups;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.secgroups.rev150712.security.groups.attributes.security.groups.SecurityGroup;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.secgroups.rev150712.security.groups.attributes.security.groups.SecurityGroupBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NeutronSecurityGroupInterface
        extends AbstractNeutronInterface<SecurityGroup, SecurityGroups, NeutronSecurityGroup>
        implements INeutronSecurityGroupCRUD {
    private static final Logger LOGGER = LoggerFactory.getLogger(NeutronSecurityGroupInterface.class);

    NeutronSecurityGroupInterface(DataBroker db) {
        super(db);
    }

    @Override
    protected List<SecurityGroup> getDataObjectList(SecurityGroups groups) {
        return groups.getSecurityGroup();
    }

    protected NeutronSecurityGroup fromMd(SecurityGroup group) {
        final NeutronSecurityGroup answer = new NeutronSecurityGroup();
        if (group.getName() != null) {
            answer.setName(group.getName());
        }
        if (group.getTenantId() != null) {
            answer.setTenantID(group.getTenantId());
        }
        if (group.getUuid() != null) {
            answer.setID(group.getUuid().getValue());
        }
        return answer;
    }

    @Override
    protected SecurityGroup toMd(NeutronSecurityGroup securityGroup) {
        final SecurityGroupBuilder securityGroupBuilder = new SecurityGroupBuilder();
        if (securityGroup.getName() != null) {
            securityGroupBuilder.setName(securityGroup.getName());
        }
        if (securityGroup.getTenantID() != null) {
            securityGroupBuilder.setTenantId(toUuid(securityGroup.getTenantID()));
        }
        if (securityGroup.getID() != null) {
            securityGroupBuilder.setUuid(toUuid(securityGroup.getID()));
        } else {
            LOGGER.warn("Attempting to write neutron securityGroup without UUID");
        }

        return securityGroupBuilder.build();
    }

    @Override
    protected SecurityGroup toMd(String uuid) {
        final SecurityGroupBuilder securityGroupBuilder = new SecurityGroupBuilder();
        securityGroupBuilder.setUuid(toUuid(uuid));
        return securityGroupBuilder.build();
    }

    @Override
    protected InstanceIdentifier<SecurityGroup> createInstanceIdentifier(SecurityGroup securityGroup) {
        return InstanceIdentifier.create(Neutron.class).child(SecurityGroups.class).child(SecurityGroup.class,
                securityGroup.getKey());
    }

    @Override
    protected InstanceIdentifier<SecurityGroups> createInstanceIdentifier() {
        return InstanceIdentifier.create(Neutron.class).child(SecurityGroups.class);
    }
}
