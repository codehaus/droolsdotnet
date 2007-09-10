package org.drools.integrationtests;

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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.Cell;
import org.drools.Cheese;
import org.drools.CheeseEqual;
import org.drools.Cheesery;
import org.drools.FactHandle;
//import org.drools.IndexedNumber;
import org.drools.IndexedNumber;
import org.drools.Person;
import org.drools.PersonInterface;
import org.drools.QueryResults;
import org.drools.RuleBase;
import org.drools.Sensor;
import org.drools.State;
import org.drools.TestParam;
import org.drools.WorkingMemory;
import org.drools.audit.WorkingMemoryFileLogger;
import org.drools.compiler.DrlParser;
import org.drools.compiler.DroolsError;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.compiler.ParserError;
import org.drools.compiler.RuleError;
import org.drools.event.ActivationCancelledEvent;
import org.drools.event.ActivationCreatedEvent;
import org.drools.event.AfterActivationFiredEvent;
import org.drools.event.AgendaEventListener;
import org.drools.event.BeforeActivationFiredEvent;
import org.drools.event.DebugAgendaEventListener;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.drools.event.DefaultAgendaEventListener;
import org.drools.integrationtests.helloworld.Message;
import org.drools.lang.DrlDumper;
import org.drools.lang.descr.PackageDescr;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.spi.ActivationGroup;
import org.drools.spi.AgendaGroup;
import org.drools.xml.XmlDumper;

/**
 * This contains the test cases for each engines implementation to execute.
 * All integration tests get added here, and will be executed for each engine type. 
 */
public abstract class IntegrationCases extends TestCase {

    /** Implementation specific subclasses must provide this. */
    protected abstract RuleBase getRuleBase() throws Exception;

    public void testGlobals() throws Exception {

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "globals_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );
        
        workingMemory.setGlobal( "string",
                                 "stilton" );        

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );

        workingMemory.fireAllRules();

        assertEquals( new Integer( 5 ),
                      list.get( 0 ) );
    }
    
   
    public void testFieldBiningsAndEvalSharing() throws Exception {        
        String drl = "test_FieldBindingsAndEvalSharing.drl";
        evalSharingTest( drl );
    }
    
    public void testFieldBiningsAndPredicateSharing() throws Exception {        
        String drl = "test_FieldBindingsAndPredicateSharing.drl";
        evalSharingTest( drl );
    }    

    private void evalSharingTest(String drl) throws DroolsParserException,
                                            IOException,
                                            Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( drl ) ) );
        final Package pkg = builder.getPackage();
        
        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );        
        WorkingMemory wm = ruleBase.newWorkingMemory();
        
        List list = new ArrayList();
        wm.setGlobal( "list", list );
        
        TestParam tp1 = new TestParam();
        tp1.setValue2( "boo" );
        wm.assertObject( tp1 );
        
        
        wm.fireAllRules();
        
        assertEquals(1, list.size());
    }



    public void testEmptyColumn() throws Exception {
        //pre build the package
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_EmptyColumn.drl" ) ) );
        final Package pkg = builder.getPackage();

        //add the package to a rulebase
        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );

        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );

        workingMemory.fireAllRules();

        assertEquals( new Integer( 5 ),
                      list.get( 0 ) );
    }

    public void testExplicitAnd() throws Exception {
        final Reader reader = new InputStreamReader( 
                                   getClass().getResourceAsStream( "test_ExplicitAnd.drl" ) );
        final DrlParser parser = new DrlParser();
        final PackageDescr packageDescr = parser.parse( reader );

        //pre build the package
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackage( packageDescr );
        final Package pkg = builder.getPackage();

        //add the package to a rulebase
        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        //load up the rulebase

        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        List list = new ArrayList();
        workingMemory.setGlobal( "list", list );
        workingMemory.assertObject( new Message("hola") );
        
        workingMemory.fireAllRules();
        assertEquals(0, list.size());
    }
    
    public void testHelloWorld() throws Exception {

        //read in the source
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "HelloWorld.drl" ) );
        final DrlParser parser = new DrlParser();
        final PackageDescr packageDescr = parser.parse( reader );

        //pre build the package
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackage( packageDescr );
        final Package pkg = builder.getPackage();

        //add the package to a rulebase
        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        //load up the rulebase

        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        //go !            
        final Message message = new Message( "hola" );
        message.addToList( "hello" );
        message.setNumber( 42 );

        workingMemory.assertObject( message );
        workingMemory.assertObject( "boo" );
        workingMemory.fireAllRules();
        assertTrue( message.isFired() );
        assertEquals( message,
                      list.get( 0 ) );

    }

    public void testLiteral() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "literal_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final AgendaEventListener listener = new DefaultAgendaEventListener() {

            public void activationCreated(ActivationCreatedEvent event) {
                System.out.println( event );
            }

            public void activationCancelled(ActivationCancelledEvent event) {
                System.out.println( event );
            }

            public void beforeActivationFired(BeforeActivationFiredEvent event) {
                System.out.println( event );
            }

            public void afterActivationFired(AfterActivationFiredEvent event) {
                System.out.println( event );
            }
        };

        workingMemory.addEventListener( listener );

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );

        workingMemory.fireAllRules();

        assertEquals( "stilton",
                      list.get( 0 ) );
    }

    public void testLiteralWithBoolean() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "literal_with_boolean.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface bill = new Person( "bill",
                                           null,
                                           12 );
        bill.setAlive( true );
        workingMemory.assertObject( bill );
        workingMemory.fireAllRules();

        assertEquals( bill,
                      list.get( 0 ) );
    }

    public void testPropertyChangeSupport() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_PropertyChange.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final State state = new State( "initial" );
        workingMemory.assertObject( state,
                                    true );
        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );

        state.setFlag( true );
        assertEquals( 1,
                      list.size() );

        workingMemory.fireAllRules();
        assertEquals( 2,
                      list.size() );

        state.setState( "finished" );
        workingMemory.fireAllRules();
        assertEquals( 3,
                      list.size() );

    }

    public void testBigDecimal() throws Exception {

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "big_decimal_and_comparable.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface bill = new Person( "bill",
                                           null,
                                           12 );
        bill.setBigDecimal( new BigDecimal( "42" ) );
        workingMemory.assertObject( new BigDecimal( "43" ) );
        workingMemory.assertObject( bill );
        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );
    }

    public void testCell() throws Exception {
        final Cell cell1 = new Cell( 9 );
        final Cell cell = new Cell( 0 );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "evalmodify.drl" ) ) );

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( builder.getPackage() );

        final WorkingMemory memory = ruleBase.newWorkingMemory();
        memory.assertObject( cell1 );
        memory.assertObject( cell );
        memory.fireAllRules();
        assertEquals( 9,
                      cell.getValue() );
    }

    public void testOr() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "or_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese cheddar = new Cheese( "cheddar",
                                     5 );
        final FactHandle h = workingMemory.assertObject( cheddar );

        workingMemory.fireAllRules();

        //just one added
        assertEquals( "got cheese",
                      list.get( 0 ) );
        assertEquals( 1,
                      list.size() );

        workingMemory.retractObject( h );
        workingMemory.fireAllRules();

        //still just one
        assertEquals( 1,
                      list.size() );

        workingMemory.assertObject( new Cheese( "stilton",
                                                5 ) );
        workingMemory.fireAllRules();

        //now have one more
        assertEquals( 2,
                      list.size() );

    }

    public void testQuery() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "simple_query_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese stilton = new Cheese( "stinky",
                                     5 );
        workingMemory.assertObject( stilton );
        final QueryResults results = workingMemory.getQueryResults( "simple query" );
        assertEquals( 1,
                      results.size() );
    }

    public void testEval() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "eval_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        workingMemory.setGlobal( "five",
                                 new Integer( 5 ) );

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );
        workingMemory.fireAllRules();

        assertEquals( stilton,
                      list.get( 0 ) );
    }
    
    public void testJaninoEval() throws Exception {
        PackageBuilderConfiguration config = new PackageBuilderConfiguration();
        config.setCompiler( PackageBuilderConfiguration.JANINO );
        final PackageBuilder builder = new PackageBuilder(config);
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "eval_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        workingMemory.setGlobal( "five",
                                 new Integer( 5 ) );

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );
        workingMemory.fireAllRules();

        assertEquals( stilton,
                      list.get( 0 ) );
    }    

    public void testEvalMore() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "eval_rule_test_more.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.assertObject( "foo" );
        workingMemory.fireAllRules();

        assertEquals( "foo",
                      list.get( 0 ) );
    }

    public void testReturnValue() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "returnvalue_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        workingMemory.setGlobal( "two",
                                 new Integer( 2 ) );

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface peter = new Person( "peter",
                                            null,
                                            12 );
        workingMemory.assertObject( peter );
        final PersonInterface jane = new Person( "jane",
                                           null,
                                           10 );
        workingMemory.assertObject( jane );

        workingMemory.fireAllRules();

        assertEquals( jane,
                      list.get( 0 ) );
        assertEquals( peter,
                      list.get( 1 ) );
    }

    public void testPredicate() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "predicate_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        workingMemory.setGlobal( "two",
                                 new Integer( 2 ) );

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface peter = new Person( "peter",
                                            null,
                                            12 );
        workingMemory.assertObject( peter );
        final PersonInterface jane = new Person( "jane",
                                           null,
                                           10 );
        workingMemory.assertObject( jane );

        workingMemory.fireAllRules();

        assertEquals( jane,
                      list.get( 0 ) );
        assertEquals( peter,
                      list.get( 1 ) );
    }

    public void testNot() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "not_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        final FactHandle stiltonHandle = workingMemory.assertObject( stilton );
        final Cheese cheddar = new Cheese( "cheddar",
                                     7 );
        final FactHandle cheddarHandle = workingMemory.assertObject( cheddar );
        workingMemory.fireAllRules();

        assertEquals( 0,
                      list.size() );

        workingMemory.retractObject( stiltonHandle );

        workingMemory.fireAllRules();

        assertEquals( 4,
                      list.size() );
        Assert.assertTrue( list.contains( new Integer( 5 ) ) );
        Assert.assertTrue( list.contains( new Integer( 6 ) ) );
        Assert.assertTrue( list.contains( new Integer( 7 ) ) );
        Assert.assertTrue( list.contains( new Integer( 8 ) ) );
    }

    public void testNotWithBindings() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "not_with_bindings_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final Rule rule = pkg.getRules()[0];
        assertTrue( rule.isValid() );
        assertEquals( 0,
                      builder.getErrors().length );
        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        final FactHandle stiltonHandle = workingMemory.assertObject( stilton );
        final Cheese cheddar = new Cheese( "cheddar",
                                     7 );
        final FactHandle cheddarHandle = workingMemory.assertObject( cheddar );

        final PersonInterface paul = new Person( "paul",
                                           "stilton",
                                           12 );
        workingMemory.assertObject( paul );
        workingMemory.fireAllRules();

        assertEquals( 0,
                      list.size() );

        workingMemory.retractObject( stiltonHandle );

        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );
    }

    public void testNullBehaviour() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "null_behaviour.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final PersonInterface p1 = new Person( "michael",
                                         "food",
                                         40 );
        final PersonInterface p2 = new Person( null,
                                         "drink",
                                         30 );
        workingMemory.assertObject( p1 );
        workingMemory.assertObject( p2 );

        workingMemory.fireAllRules();

    }

    public void testNullConstraint() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "null_constraint.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        final List foo = new ArrayList();
        workingMemory.setGlobal( "messages",
                                 foo );

        final PersonInterface p1 = new Person( null,
                                         "food",
                                         40 );

        workingMemory.assertObject( p1 );

        workingMemory.fireAllRules();
        assertEquals( 1,
                      foo.size() );

    }

    public void testExists() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "exists_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese cheddar = new Cheese( "cheddar",
                                     7 );
        final FactHandle cheddarHandle = workingMemory.assertObject( cheddar );
        workingMemory.fireAllRules();

        assertEquals( 0,
                      list.size() );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        final FactHandle stiltonHandle = workingMemory.assertObject( stilton );
        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );

        final Cheese brie = new Cheese( "brie",
                                  5 );
        final FactHandle brieHandle = workingMemory.assertObject( brie );
        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );
    }

    public void testExists2() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_exists.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese cheddar   = new Cheese( "cheddar", 7 );
        final Cheese provolone = new Cheese( "provolone", 5); 
        final Person edson     = new Person("Edson", "cheddar");
        final Person bob       = new Person("Bob",   "muzzarela");
        
        workingMemory.assertObject( cheddar );
        workingMemory.fireAllRules();
        assertEquals( 0,
                      list.size() );

        workingMemory.assertObject( provolone );
        workingMemory.fireAllRules();
        assertEquals( 0,
                      list.size() );

        workingMemory.assertObject( edson );
        workingMemory.fireAllRules();
        assertEquals( 1,
                      list.size() );

        workingMemory.assertObject( bob );
        workingMemory.fireAllRules();
        assertEquals( 1,
                      list.size() );
    }

    public void testWithInvalidRule() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "invalid_rule.drl" ) ) );
        final Package pkg = builder.getPackage();
        //Mark: please check if the conseqeuence/should/shouldn't be built
        //        Rule badBoy = pkg.getRules()[0];
        //        assertFalse(badBoy.isValid());

        RuntimeException runtime = null;
        //this should ralph all over the place.
        final RuleBase ruleBase = getRuleBase();
        try {
            ruleBase.addPackage( pkg );
            fail( "Should have thrown an exception as the rule is NOT VALID." );
        } catch ( final RuntimeException e ) {
            assertNotNull( e.getMessage() );
            runtime = e;
        }
        assertTrue( builder.getErrors().length > 0 );

        final String pretty = builder.printErrors();
        assertFalse( pretty.equals( "" ) );
        assertEquals( pretty,
                      runtime.getMessage() );

    }

    public void testErrorLineNumbers() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "errors_in_rule.drl" ) ) );
        final Package pkg = builder.getPackage();

        final DroolsError err = builder.getErrors()[0];
        final RuleError ruleErr = (RuleError) err;
        assertNotNull( ruleErr.getDescr() );
        assertTrue( ruleErr.getLine() != -1 );

        assertEquals( 3,
                      builder.getErrors().length );

        //check that its getting it from the ruleDescr
        assertEquals( ruleErr.getLine(),
                      ruleErr.getDescr().getLine() );
        //check the absolute error line number (there are more).
        assertEquals( 11,
                      ruleErr.getLine() );

        //now check the RHS, not being too specific yet, as long as it has the rules line number, not zero
        final RuleError rhs = (RuleError) builder.getErrors()[2];
        assertTrue( rhs.getLine() > 7 ); //not being too specific - may need to change this when we rework the error reporting

    }

    public void testErrorsParser() throws Exception {
        final DrlParser parser = new DrlParser();
        assertEquals( 0,
                      parser.getErrors().size() );
        parser.parse( new InputStreamReader( getClass().getResourceAsStream( "errors_parser_multiple.drl" ) ) );
        assertTrue( parser.hasErrors() );
        assertTrue( parser.getErrors().size() > 0 );
        assertTrue( parser.getErrors().get( 0 ) instanceof ParserError );
        final ParserError first = ((ParserError) parser.getErrors().get( 0 ));
        assertTrue( first.getMessage() != null );
        assertFalse( first.getMessage().equals( "" ) );
    }

    public void testFunction() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_FunctionInConsequence.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );

        workingMemory.fireAllRules();

        assertEquals( new Integer( 5 ),
                      list.get( 0 ) );
    }

    public void testDynamicFunction() throws Exception {
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_DynamicFunction1.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );

        workingMemory.fireAllRules();

        assertEquals( new Integer( 5 ),
                      list.get( 0 ) );

        // Check a function can be removed from a package.
        // Once removed any efforts to use it should throw an Exception
        pkg.removeFunction( "addFive" );

        final Cheese cheddar = new Cheese( "cheddar",
                                     5 );
        workingMemory.assertObject( cheddar );

        try {
            workingMemory.fireAllRules();
            fail( "Function should have been removed and NoClassDefFoundError thrown from the Consequence" );
        } catch ( final NoClassDefFoundError e ) {
        }

        // Check a new function can be added to replace an old function
        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_DynamicFunction2.drl" ) ) );

        ruleBase.addPackage( builder.getPackage() );

        final Cheese brie = new Cheese( "brie",
                                  5 );
        workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        assertEquals( new Integer( 6 ),
                      list.get( 1 ) );

        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_DynamicFunction3.drl" ) ) );

        ruleBase.addPackage( builder.getPackage() );

        final Cheese feta = new Cheese( "feta",
                                  5 );
        workingMemory.assertObject( feta );

        workingMemory.fireAllRules();

        assertEquals( new Integer( 5 ),
                      list.get( 2 ) );

    }

    public void testAssertRetract() throws Exception {
        //postponed while I sort out KnowledgeHelperFixer
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "assert_retract.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface person = new Person( "michael",
                                             "cheese" );
        person.setStatus( "start" );
        workingMemory.assertObject( person );
        workingMemory.fireAllRules();

        assertEquals( 5,
                      list.size() );
        assertTrue( list.contains( "first" ) );
        assertTrue( list.contains( "second" ) );
        assertTrue( list.contains( "third" ) );
        assertTrue( list.contains( "fourth" ) );
        assertTrue( list.contains( "fifth" ) );

    }

    public void testWithExpanderDSL() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        final Reader source = new InputStreamReader( getClass().getResourceAsStream( "rule_with_expander_dsl.drl" ) );
        final Reader dsl = new InputStreamReader( getClass().getResourceAsStream( "test_expander.dsl" ) );
        builder.addPackageFromDrl( source,
                                   dsl );

        //the compiled package
        final Package pkg = builder.getPackage();
        assertTrue( pkg.isValid() );
        assertEquals( null,
                      pkg.getErrorSummary() );
        //Check errors
        final String err = builder.printErrors();
        assertEquals( "",
                      err );

        assertEquals( 0,
                      builder.getErrors().length );

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );

        final WorkingMemory wm = ruleBase.newWorkingMemory();
        wm.assertObject( new Person( "Bob",
                                     "http://foo.bar" ) );
        wm.assertObject( new Cheese( "stilton",
                                     42 ) );

        final List messages = new ArrayList();
        wm.setGlobal( "messages",
                      messages );
        wm.fireAllRules();

        //should have fired
        assertEquals( 1,
                      messages.size() );

    }

    public void testWithExpanderMore() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        final Reader source = new InputStreamReader( getClass().getResourceAsStream( "rule_with_expander_dsl_more.drl" ) );
        final Reader dsl = new InputStreamReader( getClass().getResourceAsStream( "test_expander.dsl" ) );
        builder.addPackageFromDrl( source,
                                   dsl );

        //the compiled package
        final Package pkg = builder.getPackage();
        assertTrue( pkg.isValid() );
        assertEquals( null,
                      pkg.getErrorSummary() );
        //Check errors
        final String err = builder.printErrors();
        assertEquals( "",
                      err );
        assertEquals( 0,
                      builder.getErrors().length );

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );

        final WorkingMemory wm = ruleBase.newWorkingMemory();
        wm.assertObject( "rage" );
        wm.assertObject( new Integer( 66 ) );

        final List messages = new ArrayList();
        wm.setGlobal( "messages",
                      messages );
        wm.fireAllRules();

        //should have NONE, as both conditions should be false.
        assertEquals( 0,
                      messages.size() );

        wm.assertObject( "fire" );
        wm.fireAllRules();

        //still no firings
        assertEquals( 0,
                      messages.size() );

        wm.assertObject( new Integer( 42 ) );

        wm.fireAllRules();

        //YOUR FIRED
        assertEquals( 1,
                      messages.size() );

    }

    public void testPredicateAsFirstColumn() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "predicate_as_first_column.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese mussarela = new Cheese( "Mussarela",
                                       35 );
        workingMemory.assertObject( mussarela );
        final Cheese provolone = new Cheese( "Provolone",
                                       20 );
        workingMemory.assertObject( provolone );

        workingMemory.fireAllRules();

        Assert.assertEquals( "The rule is being incorrectly fired",
                             35,
                             mussarela.getPrice() );
        Assert.assertEquals( "Rule is incorrectly being fired",
                             20,
                             provolone.getPrice() );
    }

    public void testSalience() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "salience_rule_test.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface person = new Person( "Edson",
                                             "cheese" );
        workingMemory.assertObject( person );

        workingMemory.fireAllRules();

        Assert.assertEquals( "Two rules should have been fired",
                             2,
                             list.size() );
        Assert.assertEquals( "Rule 3 should have been fired first",
                             "Rule 3",
                             list.get( 0 ) );
        Assert.assertEquals( "Rule 2 should have been fired second",
                             "Rule 2",
                             list.get( 1 ) );

    }

    public void testNoLoop() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "no-loop.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        Assert.assertEquals( "Should not loop  and thus size should be 1",
                             1,
                             list.size() );

    }

    public void testConsequenceException() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_ConsequenceException.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese brie = new Cheese( "brie",
                                  12 );
        workingMemory.assertObject( brie );

        try {
            workingMemory.fireAllRules();
            fail( "Should throw an Exception from the Consequence" );
        } catch ( final Exception e ) {
            assertEquals( "this should throw an exception",
                          e.getCause().getMessage() );
        }
    }

    public void testFunctionException() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_FunctionException.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese brie = new Cheese( "brie",
                                  12 );
        workingMemory.assertObject( brie );

        try {
            workingMemory.fireAllRules();
            fail( "Should throw an Exception from the Function" );
        } catch ( final Exception e ) {
            assertEquals( "this should throw an exception",
                          e.getCause().getMessage() );
        }
    }

    public void testEvalException() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_EvalException.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese brie = new Cheese( "brie",
                                  12 );

        try {
            workingMemory.assertObject( brie );
            workingMemory.fireAllRules();
            fail( "Should throw an Exception from the Eval" );
        } catch ( final Exception e ) {
            assertEquals( "this should throw an exception",
                          e.getCause().getMessage() );
        }
    }

    public void testPredicateException() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_PredicateException.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese brie = new Cheese( "brie",
                                  12 );

        try {
            workingMemory.assertObject( brie );
            workingMemory.fireAllRules();
            fail( "Should throw an Exception from the Predicate" );
        } catch ( final Exception e ) {
            assertEquals( "this should throw an exception",
                          e.getCause().getMessage() );
        }
    }

    public void testReturnValueException() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_ReturnValueException.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese brie = new Cheese( "brie",
                                  12 );

        try {
            workingMemory.assertObject( brie );
            workingMemory.fireAllRules();
            fail( "Should throw an Exception from the ReturnValue" );
        } catch ( final Exception e ) {
            assertEquals( "this should throw an exception",
                          e.getCause().getMessage() );
        }
    }

    public void testAgendaGroups() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_AgendaGroups.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        assertEquals( 7,
                      list.size() );

        assertEquals( "group3",
                      list.get( 0 ) );
        assertEquals( "group4",
                      list.get( 1 ) );
        assertEquals( "group3",
                      list.get( 2 ) );
        assertEquals( "MAIN",
                      list.get( 3 ) );
        assertEquals( "group1",
                      list.get( 4 ) );
        assertEquals( "group1",
                      list.get( 5 ) );
        assertEquals( "MAIN",
                      list.get( 6 ) );

        workingMemory.setFocus( "group2" );
        workingMemory.fireAllRules();

        assertEquals( 8,
                      list.size() );
        assertEquals( "group2",
                      list.get( 7 ) );
    }
    
    public void testDumpers() throws Exception {
        final DrlParser parser = new DrlParser();
        final PackageDescr pkg = parser.parse( new InputStreamReader( getClass().getResourceAsStream( "test_Dumpers.drl" ) ) );        
        
        PackageBuilder builder = new PackageBuilder();
        builder.addPackage(pkg );

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( builder.getPackage() );
        WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        assertEquals( 3,
                      list.size() );
        assertEquals( "3 1",
                      list.get( 0 ) );
        assertEquals( "MAIN",
                      list.get( 1 ) );
        assertEquals( "1 1",
                      list.get( 2 ) );
        
        final DrlDumper drlDumper = new DrlDumper();
        final String drlResult = drlDumper.dump( pkg );
        builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( drlResult ) );
        
        ruleBase = getRuleBase();
        ruleBase.addPackage( builder.getPackage() );
        workingMemory = ruleBase.newWorkingMemory();

        list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        assertEquals( 3,
                      list.size() );
        assertEquals( "3 1",
                      list.get( 0 ) );
        assertEquals( "MAIN",
                      list.get( 1 ) );
        assertEquals( "1 1",
                      list.get( 2 ) );        
        
        final XmlDumper xmlDumper = new XmlDumper();
        final String xmlResult = xmlDumper.dump( pkg );
        builder = new PackageBuilder();
        builder.addPackageFromXml( new StringReader( xmlResult ) );
        
        ruleBase = getRuleBase();
        ruleBase.addPackage( builder.getPackage() );
        workingMemory = ruleBase.newWorkingMemory();

        list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        assertEquals( 3,
                      list.size() );
        assertEquals( "3 1",
                      list.get( 0 ) );
        assertEquals( "MAIN",
                      list.get( 1 ) );
        assertEquals( "1 1",
                      list.get( 2 ) );               
    }
    

    public void testXorGroups() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_ActivationGroups.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        workingMemory.assertObject( brie );

        final ActivationGroup activationGroup0 = workingMemory.getAgenda().getActivationGroup( "activation-group-0" );
        assertEquals( 2,
                      activationGroup0.size() );

        final ActivationGroup activationGroup3 = workingMemory.getAgenda().getActivationGroup( "activation-group-3" );
        assertEquals( 1,
                      activationGroup3.size() );

        final AgendaGroup agendaGroup3 = workingMemory.getAgenda().getAgendaGroup( "agenda-group-3" );
        assertEquals( 1,
                      agendaGroup3.size() );

        final AgendaGroup agendaGroupMain = workingMemory.getAgenda().getAgendaGroup( "MAIN" );
        assertEquals( 3,
                      agendaGroupMain.size() );

        workingMemory.clearAgendaGroup( "agenda-group-3" );
        assertEquals( 0,
                      activationGroup3.size() );
        assertEquals( 0,
                      agendaGroup3.size() );

        workingMemory.fireAllRules();

        assertEquals( 0,
                      activationGroup0.size() );

        assertEquals( 2,
                      list.size() );
        assertEquals( "rule0",
                      list.get( 0 ) );
        assertEquals( "rule2",
                      list.get( 1 ) );

    }

    public void testDuration() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Duration.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        final FactHandle brieHandle = workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 0,
                      list.size() );

        // sleep for 300ms
        Thread.sleep( 300 );

        // now check for update
        assertEquals( 1,
                      list.size() );

    }

    public void testDurationWithNoLoop() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Duration_with_NoLoop.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        final FactHandle brieHandle = workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 0,
                      list.size() );

        // sleep for 300ms
        Thread.sleep( 300 );

        // now check for update
        assertEquals( 1,
                      list.size() );
    }

    public void testFireRuleAfterDuration() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_FireRuleAfterDuration.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        final FactHandle brieHandle = workingMemory.assertObject( brie );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 0,
                      list.size() );

        // sleep for 300ms
        Thread.sleep( 300 );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 2,
                      list.size() );

    }

    public void testContainsCheese() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_ContainsCheese.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese stilton = new Cheese( "stilton",
                                     12 );
        final FactHandle brieHandle = workingMemory.assertObject( stilton );

        final Cheesery cheesery = new Cheesery();
        cheesery.getCheeses().add( stilton );
        workingMemory.assertObject( cheesery );

        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );

        assertEquals( stilton,
                      list.get( 0 ) );
    }

    public void testStaticFieldReference() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_StaticField.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheesery cheesery = new Cheesery();
        cheesery.setStatus( Cheesery.SELLING_CHEESE );
        workingMemory.assertObject( cheesery );

        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );

        assertEquals( cheesery,
                      list.get( 0 ) );
    }

    public void testDynamicRuleAdditions() throws Exception {
        Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1.drl" ) );

        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg1 = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg1 );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        workingMemory.setGlobal( "total",
                                 new Integer( 0 ) );

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        // Adding person in advance. There is no Person() object
        // type node in memory yet, but the rule engine is supposed
        // to handle that correctly
        final PersonInterface bob = new Person( "bob",
                                          "stilton" );
        bob.setStatus( "Not evaluated" );
        workingMemory.assertObject( bob );

        final Cheese stilton = new Cheese( "stilton",
                                     5 );
        workingMemory.assertObject( stilton );

        final Cheese cheddar = new Cheese( "cheddar",
                                     5 );
        workingMemory.assertObject( cheddar );
        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );

        assertEquals( "stilton",
                      list.get( 0 ) );

        reader = new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic2.drl" ) );
        builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg2 = builder.getPackage();
        ruleBase.addPackage( pkg2 );

        assertEquals( 3,
                      list.size() );

        assertEquals( "stilton",
                      list.get( 0 ) );

        assertTrue( "cheddar".equals( list.get( 1 ) ) || "cheddar".equals( list.get( 2 ) ) );

        assertTrue( "stilton".equals( list.get( 1 ) ) || "stilton".equals( list.get( 2 ) ) );

        list.clear();

        reader = new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic3.drl" ) );
        builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg3 = builder.getPackage();
        ruleBase.addPackage( pkg3 );

        // Package 3 has a rule working on Person instances.
        // As we added person instance in advance, rule should fire now
        workingMemory.fireAllRules();

        Assert.assertEquals( "Rule from package 3 should have been fired",
                             "match Person ok",
                             bob.getStatus() );

        assertEquals( 1,
                      list.size() );

        assertEquals( bob,
                      list.get( 0 ) );

        reader = new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic4.drl" ) );
        builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg4 = builder.getPackage();
        ruleBase.addPackage( pkg4 );

        Assert.assertEquals( "Rule from package 4 should have been fired",
                             "Who likes Stilton ok",
                             bob.getStatus() );

        assertEquals( 2,
                      list.size() );

        assertEquals( bob,
                      list.get( 1 ) );

    }

    public void testDynamicRuleRemovals() throws Exception {

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1.drl" ) ) );
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic2.drl" ) ) );
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic3.drl" ) ) );
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic4.drl" ) ) );
        final Package pkg = builder.getPackage();

        org.drools.reteoo.ReteooRuleBase reteooRuleBase = null;
        org.drools.leaps.LeapsRuleBase leapsRuleBase = null;
        final RuleBase ruleBase = getRuleBase();
        //org.drools.reteoo.RuleBaseImpl ruleBase = new org.drools.reteoo.RuleBaseImpl();
        if ( ruleBase instanceof org.drools.reteoo.ReteooRuleBase ) {
            reteooRuleBase = (org.drools.reteoo.ReteooRuleBase) ruleBase;
        } else if ( ruleBase instanceof org.drools.leaps.LeapsRuleBase ) {
            leapsRuleBase = (org.drools.leaps.LeapsRuleBase) ruleBase;
        }
        ruleBase.addPackage( pkg );

        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface bob = new Person( "bob",
                                          "stilton" );
        bob.setStatus( "Not evaluated" );
        workingMemory.assertObject( bob );

        final Cheese stilton1 = new Cheese( "stilton",
                                      5 );
        workingMemory.assertObject( stilton1 );

        final Cheese stilton2 = new Cheese( "stilton",
                                      3 );
        workingMemory.assertObject( stilton2 );

        final Cheese stilton3 = new Cheese( "stilton",
                                      1 );
        workingMemory.assertObject( stilton3 );

        final Cheese cheddar = new Cheese( "cheddar",
                                     5 );
        workingMemory.assertObject( cheddar );
        //        
        //        workingMemory.get
        //        
        //        workingMemory.fireAllRules();

        assertEquals( 11,
                      workingMemory.getAgenda().getActivations().length );

        if ( reteooRuleBase != null ) {
            reteooRuleBase.removeRule( "org.drools.test",
                                       "Who likes Stilton" );
            assertEquals( 8,
                          workingMemory.getAgenda().getActivations().length );

            reteooRuleBase.removeRule( "org.drools.test",
                                       "like cheese" );

            final Cheese muzzarela = new Cheese( "muzzarela",
                                           5 );
            workingMemory.assertObject( muzzarela );

            assertEquals( 4,
                          workingMemory.getAgenda().getActivations().length );

            reteooRuleBase.removePackage( "org.drools.test" );

            assertEquals( 0,
                          workingMemory.getAgenda().getActivations().length );
        } else if ( leapsRuleBase != null ) {
            leapsRuleBase.removeRule( "org.drools.test",
                                      "Who likes Stilton" );
            assertEquals( 8,
                          workingMemory.getAgenda().getActivations().length );

            leapsRuleBase.removeRule( "org.drools.test",
                                      "like cheese" );

            final Cheese muzzarela = new Cheese( "muzzarela",
                                           5 );
            workingMemory.assertObject( muzzarela );

            assertEquals( 4,
                          workingMemory.getAgenda().getActivations().length );

            leapsRuleBase.removePackage( "org.drools.test" );

            assertEquals( 0,
                          workingMemory.getAgenda().getActivations().length );

        }
    }

    public void testDynamicRuleRemovalsUnusedWorkingMemory() throws Exception {

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1.drl" ) ) );
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic2.drl" ) ) );
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic3.drl" ) ) );
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic4.drl" ) ) );
        final Package pkg = builder.getPackage();

        org.drools.reteoo.ReteooRuleBase reteooRuleBase = null;
        org.drools.leaps.LeapsRuleBase leapsRuleBase = null;
        final RuleBase ruleBase = getRuleBase();
        //org.drools.reteoo.RuleBaseImpl ruleBase = new org.drools.reteoo.RuleBaseImpl();
        if ( ruleBase instanceof org.drools.reteoo.ReteooRuleBase ) {
            reteooRuleBase = (org.drools.reteoo.ReteooRuleBase) ruleBase;
        } else if ( ruleBase instanceof org.drools.leaps.LeapsRuleBase ) {
            leapsRuleBase = (org.drools.leaps.LeapsRuleBase) ruleBase;
        }
        ruleBase.addPackage( pkg );

        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        if ( reteooRuleBase != null ) {
            assertEquals( 1,
                          reteooRuleBase.getPackages().length );
            assertEquals( 4,
                          reteooRuleBase.getPackages()[0].getRules().length );

            reteooRuleBase.removeRule( "org.drools.test",
                                       "Who likes Stilton" );
            assertEquals( 3,
                          reteooRuleBase.getPackages()[0].getRules().length );

            reteooRuleBase.removeRule( "org.drools.test",
                                       "like cheese" );
            assertEquals( 2,
                          reteooRuleBase.getPackages()[0].getRules().length );

            reteooRuleBase.removePackage( "org.drools.test" );
            assertEquals( 0,
                          reteooRuleBase.getPackages().length );
        } else if ( leapsRuleBase != null ) {
            assertEquals( 1,
                          leapsRuleBase.getPackages().length );
            assertEquals( 4,
                          leapsRuleBase.getPackages()[0].getRules().length );

            leapsRuleBase.removeRule( "org.drools.test",
                                      "Who likes Stilton" );
            assertEquals( 3,
                          leapsRuleBase.getPackages()[0].getRules().length );

            leapsRuleBase.removeRule( "org.drools.test",
                                      "like cheese" );
            assertEquals( 2,
                          leapsRuleBase.getPackages()[0].getRules().length );

            leapsRuleBase.removePackage( "org.drools.test" );
            assertEquals( 0,
                          leapsRuleBase.getPackages().length );
        }
    }

    public void testNullValuesIndexing() throws Exception {
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_NullValuesIndexing.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg1 = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg1 );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        // Adding person with null name and likes attributes
        final PersonInterface bob = new Person( null,
                                          null );
        bob.setStatus( "P1" );
        final PersonInterface pete = new Person( null,
                                           null );
        bob.setStatus( "P2" );
        workingMemory.assertObject( bob );
        workingMemory.assertObject( pete );

        workingMemory.fireAllRules();

        Assert.assertEquals( "Indexing with null values is not working correctly.",
                             "OK",
                             bob.getStatus() );
        Assert.assertEquals( "Indexing with null values is not working correctly.",
                             "OK",
                             pete.getStatus() );

    }

    public void testSerializable() throws Exception {    	
    	
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Serializable.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg = builder.getPackage();

        assertEquals( 0,
                      builder.getErrors().length );

        RuleBase ruleBase = getRuleBase();//RuleBaseFactory.newRuleBase();

        ruleBase.addPackage( pkg );

        final byte[] ast = serializeOut( ruleBase );
        ruleBase = (RuleBase) serializeIn( ast );
        final Rule[] rules = ruleBase.getPackages()[0].getRules();
        assertEquals( 4,
                      rules.length );

        assertEquals( "match Person 1",
                      rules[0].getName() );
        assertEquals( "match Person 2",
                      rules[1].getName() );
        assertEquals( "match Person 3",
                      rules[2].getName() );
        assertEquals( "match Integer",
                      rules[3].getName() );        
        
        WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        
        workingMemory.setGlobal( "list", new ArrayList() );
        
        workingMemory.assertObject( new Integer(5) );        
        
        final byte[] wm = serializeOut( workingMemory );

        workingMemory = ruleBase.newWorkingMemory( new ByteArrayInputStream( wm ) );
        
        assertEquals( 1, workingMemory.getObjects().size() );
        assertEquals( new Integer( 5 ) , workingMemory.getObjects().get(0) );
        
        assertEquals( 1, workingMemory.getAgenda().agendaSize() );
        
        workingMemory.fireAllRules();
        
        List list = ( List ) workingMemory.getGlobal( "list" );
        
        assertEquals( 1, list.size() );
        assertEquals( new Integer( 4 ), list.get( 0 ) );
        
        assertEquals( 2, workingMemory.getObjects().size() );
        assertEquals( new Integer( 5 ) , workingMemory.getObjects().get(0) );
        assertEquals( "help" , workingMemory.getObjects().get(1) );        
    }

    public void testLogicalAssertions() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertions.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                  12 );
        final FactHandle brieHandle = workingMemory.assertObject( brie );

        final Cheese provolone = new Cheese( "provolone",
                                       12 );
        final FactHandle provoloneHandle = workingMemory.assertObject( provolone );

        workingMemory.fireAllRules();

        assertEquals( 3,
                      list.size() );

        assertEquals( 3,
                      workingMemory.getObjects().size() );

        workingMemory.retractObject( brieHandle );

        assertEquals( 2,
                      workingMemory.getObjects().size() );

        workingMemory.retractObject( provoloneHandle );

        assertEquals( 0,
                      workingMemory.getObjects().size() );
    }

    public void testLogicalAssertionsBacking() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsBacking.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final Cheese cheese1 = new Cheese( "c",
                                     1 );
        final Cheese cheese2 = new Cheese( cheese1.getType(),
                                     1 );
        List list;

        final FactHandle h1 = workingMemory.assertObject( cheese1 );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( cheese1.getType().getClass() );
        assertEquals( 1,
                      list.size() );
        //probably dangerous, as contains works with equals, not identity
        assertEquals( cheese1.getType(),
                      list.get( 0 ) );
        //FactHandle ht = workingMemory.getFactHandle(c1.getType());

        final FactHandle h2 = workingMemory.assertObject( cheese2 );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( cheese1.getType().getClass() );
        assertEquals( 1,
                      list.size() );
        assertEquals( cheese1.getType(),
                      list.get( 0 ) );

        workingMemory.retractObject( h1 );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( cheese1.getType().getClass() );
        assertEquals( "cheese-type " + cheese1.getType() + " was retracted, but should not. Backed by cheese2 => type.",
                      1,
                      list.size() );
        assertEquals( "cheese-type " + cheese1.getType() + " was retracted, but should not. Backed by cheese2 => type.",
                      cheese1.getType(),
                      list.get( 0 ) );

        workingMemory.retractObject( h2 );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( cheese1.getType().getClass() );
        assertEquals( "cheese-type " + cheese1.getType() + " was not retracted, but should have. Neither  cheese1 => type nor cheese2 => type is true.",
                      0,
                      list.size() );
    }

    public void testLogicalAssertionsSelfreferencing() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsSelfreferencing.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        List list;

        final String b = new String( "b" );
        final String a = new String( "a" );

        workingMemory.setGlobal( "b",
                                 b );

        FactHandle h1 = workingMemory.assertObject( a );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( a.getClass() );
        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( a ) );
        assertTrue( list.contains( b ) );

        workingMemory.retractObject( h1 );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( a.getClass() );
        assertEquals( "b was retracted, but it should not have. Is backed by b => b being true.",
                      1,
                      list.size() );
        assertEquals( "b was retracted, but it should not have. Is backed by b => b being true.",
                      b,
                      list.get( 0 ) );

        h1 = workingMemory.getFactHandle( b );
        workingMemory.retractObject( h1 );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( a.getClass() );
        assertEquals( 0,
                      list.size() );
    }

    public void testLogicalAssertionsLoop() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsLoop.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        List list;

        final List l = new ArrayList();
        final String a = new String( "a" );
        workingMemory.setGlobal( "a",
                                 a );
        workingMemory.setGlobal( "l",
                                 l );

        workingMemory.fireAllRules();
        list = workingMemory.getObjects( a.getClass() );
        assertEquals( "a still asserted.",
                      0,
                      list.size() );
        assertEquals( "Rule has not fired (looped) expected number of times",
                      10,
                      l.size() );
    }

    public void testLogicalAssertionsNoLoop() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsNoLoop.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        List list;

        final List l = new ArrayList();
        final String a = new String( "a" );
        workingMemory.setGlobal( "a",
                                 a );
        workingMemory.setGlobal( "l",
                                 l );

        workingMemory.fireAllRules();
        list = workingMemory.getObjects( a.getClass() );
        assertEquals( "a still in WM",
                      0,
                      list.size() );
        assertEquals( "Rule should not loop",
                      1,
                      l.size() );
    }

    public void testLogicalAssertions2() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertions2.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List events = new ArrayList();

        workingMemory.setGlobal( "events",
                                 events );

        final Sensor sensor = new Sensor( 80,
                                    80 );
        final FactHandle handle = workingMemory.assertObject( sensor );

        // everything should be normal
        workingMemory.fireAllRules();

        final List list = workingMemory.getObjects();

        assertEquals( "Only sensor is there",
                      1,
                      list.size() );
        assertEquals( "Only one event",
                      1,
                      events.size() );

        // problems should be detected
        sensor.setPressure( 200 );
        sensor.setTemperature( 200 );
        workingMemory.modifyObject( handle,
                                    sensor );

        workingMemory.fireAllRules();
        assertEquals( "Only sensor is there",
                      1,
                      list.size() );
        assertEquals( "Exactly six events",
                      6,
                      events.size() );
    }

    public void testLogicalAssertionsNot() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsNot.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        List list;

        final String a = new String( "a" );
        final Integer i = new Integer( 1 );
        workingMemory.setGlobal( "i",
                                 i );

        workingMemory.fireAllRules();
        list = workingMemory.getObjects();
        assertEquals( "i was not asserted by not a => i.",
                      1,
                      list.size() );
        assertEquals( "i was not asserted by not a => i.",
                      i,
                      list.get( 0 ) );

        final FactHandle h = workingMemory.assertObject( a );
        //no need to fire rules, assertion alone removes justification for i, so it should be retracted.
        //workingMemory.fireAllRules();
        list = workingMemory.getObjects();
        assertEquals( "a was not asserted or i not retracted.",
                      1,
                      list.size() );
        assertEquals( "a was asserted.",
                      a,
                      list.get( 0 ) );
        assertFalse( "i was not rectracted.",
                     list.contains( i ) );

        //no rules should fire, but nevertheless...
        //workingMemory.fireAllRules();
        assertEquals( "agenda should be empty.",
                      0,
                      workingMemory.getAgenda().agendaSize() );

        workingMemory.retractObject( h );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects();
        assertEquals( "i was not asserted by not a => i.",
                      1,
                      list.size() );
        assertEquals( "i was not asserted by not a => i.",
                      i,
                      list.get( 0 ) );
    }

    public void testLogicalAssertionsNotPingPong() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsNotPingPong.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        //workingMemory.addEventListener(new DebugAgendaEventListener());
        //workingMemory.addEventListener(new DebugWorkingMemoryEventListener());

        final List list;

        final List l = new ArrayList();

        final String s = new String( "s" );
        final Integer i = new Integer( 1 );
        workingMemory.setGlobal( "i",
                                 i );
        workingMemory.setGlobal( "s",
                                 s );
        workingMemory.setGlobal( "l",
                                 l );

        workingMemory.fireAllRules();

        //not sure about desired state of working memory. 
        assertEquals( "Rules have not fired (looped) expected number of times",
                      10,
                      l.size() );
    }

    public void testLogicalAssertionsDynamicRule() throws Exception {
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsDynamicRule.drl" ) ) );
        final Package pkg = builder.getPackage();

        org.drools.reteoo.ReteooRuleBase reteooRuleBase = null;
        org.drools.leaps.LeapsRuleBase leapsRuleBase = null;
        final RuleBase ruleBase = getRuleBase();
        if ( ruleBase instanceof org.drools.reteoo.ReteooRuleBase ) {
            reteooRuleBase = (org.drools.reteoo.ReteooRuleBase) ruleBase;
        } else if ( ruleBase instanceof org.drools.leaps.LeapsRuleBase ) {
            leapsRuleBase = (org.drools.leaps.LeapsRuleBase) ruleBase;
        }
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        //        workingMemory.addEventListener(new org.drools.event.DebugAgendaEventListener());
        //        workingMemory.addEventListener(new org.drools.event.DebugWorkingMemoryEventListener());

        final Cheese c1 = new Cheese( "a",
                                1 );
        final Cheese c2 = new Cheese( "b",
                                2 );
        final Cheese c3 = new Cheese( "c",
                                3 );
        List list;

        workingMemory.assertObject( c1 );
        final FactHandle h = workingMemory.assertObject( c2 );
        workingMemory.assertObject( c3 );
        workingMemory.fireAllRules();
        list = workingMemory.getObjects( c1.getType().getClass() );
        assertEquals( 2,
                      list.size() );
        assertFalse( list.contains( c1.getType() ) );
        assertTrue( list.contains( c2.getType() ) );
        assertTrue( list.contains( c3.getType() ) );

        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsDynamicRule2.drl" ) );
        builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg2 = builder.getPackage();
        ruleBase.addPackage( pkg2 );

        workingMemory.fireAllRules();

        list = workingMemory.getObjects( c1.getType().getClass() );
        assertEquals( 3,
                      list.size() );
        assertTrue( list.contains( c1.getType() ) );
        assertTrue( list.contains( c2.getType() ) );
        assertTrue( list.contains( c3.getType() ) );

        assertEquals( "org.drools.test",
                      ruleBase.getPackages()[0].getName() );
        assertEquals( "org.drools.test2",
                      ruleBase.getPackages()[1].getName() );
        assertEquals( "rule1",
                      ruleBase.getPackages()[0].getRules()[0].getName() );
        assertEquals( "rule2",
                      ruleBase.getPackages()[1].getRules()[0].getName() );

        if ( reteooRuleBase != null ) {
            reteooRuleBase.removeRule( ruleBase.getPackages()[0].getName(),
                                       ruleBase.getPackages()[0].getRules()[0].getName() );
        } else if ( leapsRuleBase != null ) {
            leapsRuleBase.removeRule( ruleBase.getPackages()[0].getName(),
                                      ruleBase.getPackages()[0].getRules()[0].getName() );
        }

        assertEquals( 0,
                      ruleBase.getPackages()[0].getRules().length );
        assertEquals( 1,
                      ruleBase.getPackages()[1].getRules().length );
        assertEquals( "org.drools.test2",
                      ruleBase.getPackages()[1].getName() );
        assertEquals( "rule2",
                      ruleBase.getPackages()[1].getRules()[0].getName() );

        list = workingMemory.getObjects( c1.getType().getClass() );
        assertEquals( "remove of rule should retract objects logically asserted based on the rule",
                      2,
                      list.size() );
        assertTrue( "remove of rule should retract objects logically asserted based on the rule",
                    list.contains( c1.getType() ) );
        assertTrue( "remove of rule should retract objects logically asserted based on the rule",
                    list.contains( c2.getType() ) );
        assertFalse( "remove of rule should retract objects logically asserted based on the rule",
                     list.contains( c3.getType() ) );

        c2.setPrice( 3 );
        workingMemory.modifyObject( h,
                                    c2 );
        list = workingMemory.getObjects( c1.getType().getClass() );
        assertEquals( "remove of rule should remove one justification for c2 -> type",
                      1,
                      list.size() );
        assertFalse( "remove of rule should remove one justification for c2 -> type",
                     list.contains( c2.getType() ) );
        assertTrue( "remove of rule should remove one justification for c2 -> type",
                    list.contains( c1.getType() ) );

        if ( reteooRuleBase != null ) {
            reteooRuleBase.removeRule( ruleBase.getPackages()[1].getName(),
                                       ruleBase.getPackages()[1].getRules()[0].getName() );
        } else if ( leapsRuleBase != null ) {
            leapsRuleBase.removeRule( ruleBase.getPackages()[1].getName(),
                                      ruleBase.getPackages()[1].getRules()[0].getName() );
        }
        assertEquals( 0,
                      ruleBase.getPackages()[0].getRules().length );
        assertEquals( 0,
                      ruleBase.getPackages()[1].getRules().length );
        list = workingMemory.getObjects( c1.getType().getClass() );
        assertEquals( 0,
                      list.size() );
    }

    public void testLogicalAssertionsModifyEqual() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LogicalAssertionsModifyEqual.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        List l;
        final FactHandle h = workingMemory.assertObject( new Integer( 2 ) );
        assertEquals( 1,
                      workingMemory.getObjects().size() );

        workingMemory.fireAllRules();
        assertEquals( 2,
                      workingMemory.getObjects().size() );
        l = workingMemory.getObjects( CheeseEqual.class );
        assertEquals( 1,
                      l.size() );
        assertEquals( 3,
                      ((CheeseEqual) l.get( 0 )).getPrice() );

        workingMemory.retractObject( h );
        assertEquals( 0,
                      workingMemory.getObjects().size() );

        try {
            final java.lang.reflect.Field field = workingMemory.getClass().getDeclaredField( "logicalAssertMap" );
            field.setAccessible( true );
            final java.util.Map m = (java.util.Map) field.get( workingMemory );
            field.setAccessible( false );
            assertEquals( "logicalAssertMap should be empty",
                          0,
                          m.size() );
        } catch ( final NoSuchFieldException e ) {
            //is probably non-reteoo engine
        }
    }

    public void testEmptyRule() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_EmptyRule.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.fireAllRules();

        assertTrue( list.contains( "fired1" ) );
        assertTrue( list.contains( "fired2" ) );
    }

    public void testjustEval() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_NoColumns.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.fireAllRules();

        assertTrue( list.contains( "fired1" ) );
        assertTrue( list.contains( "fired3" ) );
    }
    
    public void testOrWithBinding() throws Exception {

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( 
               getClass().getResourceAsStream( "test_OrWithBindings.drl" ) ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();

        final List list = new ArrayList();
        workingMemory.setGlobal( "results",
                                 list );

        workingMemory.assertObject( new Integer(42) );
        workingMemory.assertObject( "hola" );
        workingMemory.fireAllRules();

        assertTrue( list.contains( "hola" ) );
        assertEquals(1, list.size() );        
        
    }

    protected Object serializeIn(final byte[] bytes) throws IOException,
                                            ClassNotFoundException {
        final ObjectInput in = new ObjectInputStream( new ByteArrayInputStream( bytes ) );
        final Object obj = in.readObject();
        in.close();
        return obj;
    }

    protected byte[] serializeOut(final Object obj) throws IOException {
        // Serialize to a byte array
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutput out = new ObjectOutputStream( bos );
        out.writeObject( obj );
        out.close();

        // Get the bytes of the serialized object
        final byte[] bytes = bos.toByteArray();
        return bytes;
    }

    public void testJoinNodeModifyObject() throws Exception {
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_JoinNodeModifyObject.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg1 = builder.getPackage();

        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg1 );
        final WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        
        final List orderedFacts = new ArrayList(); 
        final List errors = new ArrayList();
        
        workingMemory.setGlobal( "orderedNumbers", orderedFacts );
        workingMemory.setGlobal( "errors", errors );
        
        final int MAX = 5;
        for (int i=1 ; i<=MAX; i++) {
            IndexedNumber n = new IndexedNumber(i, MAX - i + 1);
            workingMemory.assertObject(n);
        }
        workingMemory.fireAllRules();
        
        Assert.assertTrue( "Processing generated errors: "+errors.toString(), 
                           errors.isEmpty());
        
        for(int i=1 ; i<=MAX; i++) {
            IndexedNumber n = (IndexedNumber) orderedFacts.get( i-1 );
            Assert.assertEquals( "Fact is out of order", i, n.getIndex() );
        }
    }

}
