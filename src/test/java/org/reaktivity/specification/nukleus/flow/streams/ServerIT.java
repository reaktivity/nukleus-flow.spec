/**
 * Copyright 2016-2021 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.specification.nukleus.flow.streams;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

public class ServerIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("server", "org/reaktivity/specification/nukleus/flow/streams/server");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "${server}/client.connected/client",
        "${server}/client.connected/server"})
    public void shouldConnect() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${server}/client.sent.data/client",
        "${server}/client.sent.data/server"})
    public void shouldSendClientData() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${server}/client.received.data/client",
        "${server}/client.received.data/server"})
    public void shouldReceiveClientData() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${server}/client.sent.and.received.data/client",
        "${server}/client.sent.and.received.data/server"})
    public void shouldSendAndReceiveClientData() throws Exception
    {
        k3po.finish();
    }
}
