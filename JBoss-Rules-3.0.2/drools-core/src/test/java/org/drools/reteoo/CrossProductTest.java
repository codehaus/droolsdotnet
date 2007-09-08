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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.base.ClassObjectType;
import org.drools.rule.Column;
import org.drools.rule.Declaration;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.spi.Consequence;
import org.drools.spi.KnowledgeHelper;
import org.drools.spi.ObjectType;

public class CrossProductTest extends TestCase {
    public void test1() throws Exception {
        final ObjectType list1ObjectType = new ClassObjectType( String.class );
        final ObjectType list2ObjectType = new ClassObjectType( String.class );

        final Rule rule = new Rule( "rule-1" );

        final Column list1Column = new Column( 0,
                                               list1ObjectType,
                                               "s1" );
        final Column list2Column = new Column( 1,
                                               list2ObjectType,
                                               "s2" );

        rule.addPattern( list1Column );
        rule.addPattern( list2Column );

        final Declaration s1Declaration = rule.getDeclaration( "s1" );
        final Declaration s2Declaration = rule.getDeclaration( "s2" );

        final List values = new ArrayList();

        rule.setConsequence( new Consequence() {

            /**
             * 
             */
            private static final long serialVersionUID = -276571982531422972L;

            public void evaluate(final KnowledgeHelper knowledgeHelper,
                                 final WorkingMemory workingMemory) throws Exception {
                final String s1 = (String) knowledgeHelper.get( s1Declaration );
                final String s2 = (String) knowledgeHelper.get( s2Declaration );
                values.add( new String[]{s1, s2} );
            }

        } );

        final Package pkg = new Package( "org.drools" );
        pkg.addRule( rule );

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        workingMemory.assertObject( "F1" );
        workingMemory.assertObject( "F2" );
        workingMemory.assertObject( "F3" );
        workingMemory.assertObject( "F4" );

        workingMemory.fireAllRules();

        // A full cross product is 16, this is just 12
        assertEquals( 12,
                      values.size() );
    }

}