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

import java.beans.IntrospectionException;
import java.util.HashSet;
import java.util.Set;

import org.drools.Cheese;
import org.drools.DroolsTestCase;
import org.drools.FactException;
import org.drools.base.ClassFieldExtractor;
import org.drools.base.EvaluatorFactory;
import org.drools.common.DefaultFactHandle;
import org.drools.common.PropagationContextImpl;
import org.drools.rule.LiteralConstraint;
import org.drools.rule.Rule;
import org.drools.spi.Evaluator;
import org.drools.spi.FieldExtractor;
import org.drools.spi.FieldValue;
import org.drools.spi.MockField;
import org.drools.spi.PropagationContext;

public class AlphaNodeTest extends DroolsTestCase {

    public void testAttach() throws Exception {
        final MockObjectSource source = new MockObjectSource( 15 );

        final AlphaNode alphaNode = new AlphaNode( 2,
                                                   null,
                                                   source );
        assertEquals( 2,
                      alphaNode.getId() );
        assertLength( 0,
                      source.getObjectSinksAsList() );
        alphaNode.attach();
        assertLength( 1,
                      source.getObjectSinksAsList() );
        assertSame( alphaNode,
                    source.getObjectSinks().getLastObjectSink() );
    }

    public void testMemory() {
        final ReteooWorkingMemory workingMemory = new ReteooWorkingMemory( new ReteooRuleBase() );

        final AlphaNode alphaNode = new AlphaNode( 2,
                                                   null,
                                                   null );

        final Set memory = (HashSet) workingMemory.getNodeMemory( alphaNode );

        assertNotNull( memory );
    }

    public void testLiteralConstraintAssertObjectWithMemory() throws Exception {
        final ReteooWorkingMemory workingMemory = new ReteooWorkingMemory( new ReteooRuleBase() );
        final Rule rule = new Rule( "test-rule" );
        final PropagationContext context = new PropagationContextImpl( 0,
                                                                       PropagationContext.ASSERTION,
                                                                       null,
                                                                       null );

        final MockObjectSource source = new MockObjectSource( 15 );

        final ClassFieldExtractor extractor = new ClassFieldExtractor( Cheese.class,
                                                                       "type" );

        final FieldValue field = new MockField( "cheddar" );

        final Evaluator evaluator = EvaluatorFactory.getEvaluator( Evaluator.OBJECT_TYPE,
                                                                   Evaluator.EQUAL );
        final LiteralConstraint constraint = new LiteralConstraint( field,
                                                                    extractor,
                                                                    evaluator );

        // With Memory
        final AlphaNode alphaNode = new AlphaNode( 2,
                                                   constraint,
                                                   source );

        final MockObjectSink sink = new MockObjectSink();
        alphaNode.addObjectSink( sink );

        final Cheese cheddar = new Cheese( "cheddar",
                                           5 );
        final DefaultFactHandle f0 = (DefaultFactHandle) workingMemory.assertObject( cheddar );

        // check sink is empty
        assertLength( 0,
                      sink.getAsserted() );

        // check alpha memory is empty 
        final Set memory = (Set) workingMemory.getNodeMemory( alphaNode );
        assertLength( 0,
                      memory );

        // object should assert as it passes text
        alphaNode.assertObject( f0,
                                context,
                                workingMemory );

        assertLength( 1,
                      sink.getAsserted() );
        assertLength( 1,
                      memory );
        Object[] list = (Object[]) sink.getAsserted().get( 0 );
        assertSame( cheddar,
                    workingMemory.getObject( (DefaultFactHandle) list[0] ) );
        assertTrue( "Should contain 'cheddar handle'",
                    memory.contains( f0 ) );

        final Cheese stilton = new Cheese( "stilton",
                                           6 );
        final DefaultFactHandle f1 = new DefaultFactHandle( 1,
                                                            stilton );

        // object should NOT assert as it does not pass test
        alphaNode.assertObject( f1,
                                context,
                                workingMemory );

        assertLength( 1,
                      sink.getAsserted() );
        assertLength( 1,
                      memory );
        list = (Object[]) sink.getAsserted().get( 0 );
        assertSame( cheddar,
                    workingMemory.getObject( (DefaultFactHandle) list[0] ) );
        assertTrue( "Should contain 'cheddar handle'",
                    memory.contains( f0 ) );

    }

    /*
     * dont need to test with and without memory on this, as it was already done
     * on the previous two tests. This just test AlphaNode With a different
     * Constraint type.
     */
    public void testReturnValueConstraintAssertObject() throws Exception {
        final ReteooWorkingMemory workingMemory = new ReteooWorkingMemory( new ReteooRuleBase() );
        final Rule rule = new Rule( "test-rule" );
        final PropagationContext context = new PropagationContextImpl( 0,
                                                                       PropagationContext.ASSERTION,
                                                                       null,
                                                                       null );

        final MockObjectSource source = new MockObjectSource( 15 );

        final FieldExtractor extractor = new ClassFieldExtractor( Cheese.class,
                                                                  "type" );

        final FieldValue field = new MockField( "cheddar" );

        final Evaluator evaluator = EvaluatorFactory.getEvaluator( Evaluator.OBJECT_TYPE,
                                                                   Evaluator.EQUAL );
        final LiteralConstraint constraint = new LiteralConstraint( field,
                                                                    extractor,
                                                                    evaluator );

        final AlphaNode alphaNode = new AlphaNode( 2,
                                                   constraint,
                                                   source );
        final MockObjectSink sink = new MockObjectSink();
        alphaNode.addObjectSink( sink );

        final Cheese cheddar = new Cheese( "cheddar",
                                           5 );

        final DefaultFactHandle f0 = (DefaultFactHandle) workingMemory.assertObject( cheddar );

        assertLength( 0,
                      sink.getAsserted() );

        // object should assert as it passes text
        alphaNode.assertObject( f0,
                                context,
                                workingMemory );

        assertLength( 1,
                      sink.getAsserted() );
        final Object[] list = (Object[]) sink.getAsserted().get( 0 );
        assertSame( cheddar,
                    workingMemory.getObject( (DefaultFactHandle) list[0] ) );

        final Cheese stilton = new Cheese( "stilton",
                                           6 );
        f0.setObject( stilton );

        sink.getAsserted().clear();

        // object should not assert as it does not pass text
        alphaNode.assertObject( f0,
                                context,
                                workingMemory );

        assertLength( 0,
                      sink.getAsserted() );
    }

    public void testRetractObject() throws Exception {
        final ReteooWorkingMemory workingMemory = new ReteooWorkingMemory( new ReteooRuleBase() );
        final Rule rule = new Rule( "test-rule" );
        final PropagationContext context = new PropagationContextImpl( 0,
                                                                       PropagationContext.ASSERTION,
                                                                       null,
                                                                       null );

        final MockObjectSource source = new MockObjectSource( 15 );

        final FieldExtractor extractor = new ClassFieldExtractor( Cheese.class,
                                                                  "type" );

        final FieldValue field = new MockField( "cheddar" );

        final Evaluator evaluator = EvaluatorFactory.getEvaluator( Evaluator.OBJECT_TYPE,
                                                                   Evaluator.EQUAL );
        final LiteralConstraint constraint = new LiteralConstraint( field,
                                                                    extractor,
                                                                    evaluator );

        final AlphaNode alphaNode = new AlphaNode( 2,
                                                   constraint,
                                                   source );
        final MockObjectSink sink = new MockObjectSink();
        alphaNode.addObjectSink( sink );

        final Cheese cheddar = new Cheese( "cheddar",
                                           5 );

        final DefaultFactHandle f0 = new DefaultFactHandle( 0,
                                                            cheddar );

        // check alpha memory is empty
        final Set memory = (Set) workingMemory.getNodeMemory( alphaNode );
        assertLength( 0,
                      memory );

        // object should assert as it passes text
        alphaNode.assertObject( f0,
                                context,
                                workingMemory );

        assertLength( 1,
                      memory );

        final DefaultFactHandle f1 = new DefaultFactHandle( 1,
                                                            "cheese" );

        // object should NOT retract as it doesn't exist
        alphaNode.retractObject( f1,
                                 context,
                                 workingMemory );

        assertLength( 0,
                      sink.getRetracted() );
        assertLength( 1,
                      memory );
        assertTrue( "Should contain 'cheddar handle'",
                    memory.contains( f0 ) );

        // object should retract as it does exist
        alphaNode.retractObject( f0,
                                 context,
                                 workingMemory );

        assertLength( 1,
                      sink.getRetracted() );
        assertLength( 0,
                      memory );
        final Object[] list = (Object[]) sink.getRetracted().get( 0 );
        assertSame( f0,
                    list[0] );

    }

    public void testUpdateNewNode() throws FactException,
                                   IntrospectionException {
        // An AlphaNode with memory should not try and repropagate from its
        // source
        // Also it should only update the latest tuple sinky

        final ReteooWorkingMemory workingMemory = new ReteooWorkingMemory( new ReteooRuleBase() );
        final Rule rule = new Rule( "test-rule" );
        final PropagationContext context = new PropagationContextImpl( 0,
                                                                       PropagationContext.ASSERTION,
                                                                       null,
                                                                       null );

        final MockObjectSource source = new MockObjectSource( 1 );

        final FieldExtractor extractor = new ClassFieldExtractor( Cheese.class,
                                                                  "type" );

        final FieldValue field = new MockField( "cheddar" );

        final Evaluator evaluator = EvaluatorFactory.getEvaluator( Evaluator.OBJECT_TYPE,
                                                                   Evaluator.EQUAL );
        final LiteralConstraint constraint = new LiteralConstraint( field,
                                                                    extractor,
                                                                    evaluator );

        final AlphaNode alphaNode = new AlphaNode( 2,
                                                   constraint,
                                                   source );

        alphaNode.attach();

        final MockObjectSink sink1 = new MockObjectSink();
        alphaNode.addObjectSink( sink1 );

        // Assert a single fact which should be in the AlphaNode memory and also
        // propagated to the
        // the tuple sink
        final Cheese cheese = new Cheese( "cheddar",
                                          0 );
        final DefaultFactHandle handle1 = new DefaultFactHandle( 1,
                                                                 cheese );

        source.propagateAssertObject( handle1,
                                      context,
                                      workingMemory );

        assertLength( 1,
                      sink1.getAsserted() );

        // Attach a new tuple sink
        final MockObjectSink sink2 = new MockObjectSink();
        alphaNode.addObjectSink( sink2 );

        // Tell the alphanode to update the new node. Make sure the first sink1
        // is not updated
        // likewise the source should not do anything
        alphaNode.updateNewNode( workingMemory,
                                 context );

        assertLength( 1,
                      sink1.getAsserted() );
        assertLength( 1,
                      sink2.getAsserted() );
        assertEquals( 0,
                      source.getUdated() );
    }
}