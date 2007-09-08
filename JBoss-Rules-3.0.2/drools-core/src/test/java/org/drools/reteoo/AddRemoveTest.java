package org.drools.reteoo;

/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.drools.DroolsTestCase;
import org.drools.RuleBase;
import org.drools.base.ClassObjectType;

public class AddRemoveTest extends DroolsTestCase {
    public void testAdd() {
        /*
         * create a RuleBase with a single ObjectTypeNode we attach a
         * MockObjectSink so we can detect assertions and retractions
         */
        final Rete rete = new Rete();
        final ObjectTypeNode objectTypeNode = new ObjectTypeNode( 0,
                                                                  new ClassObjectType( Object.class ),
                                                                  rete );
        objectTypeNode.attach();

        final MockObjectSink sink = new MockObjectSink();
        objectTypeNode.addObjectSink( sink );
        final RuleBase ruleBase = new ReteooRuleBase();
        final ReteooWorkingMemory workingMemory = (ReteooWorkingMemory) ruleBase.newWorkingMemory();

        // objectTypeNode.
    }

}