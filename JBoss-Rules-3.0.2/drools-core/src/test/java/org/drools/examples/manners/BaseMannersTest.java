package org.drools.examples.manners;

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
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import junit.framework.TestCase;

import org.drools.WorkingMemory;
import org.drools.base.ClassFieldExtractor;
import org.drools.base.ClassObjectType;
import org.drools.base.EvaluatorFactory;
import org.drools.rule.BoundVariableConstraint;
import org.drools.rule.Column;
import org.drools.rule.Declaration;
import org.drools.rule.InvalidRuleException;
import org.drools.rule.LiteralConstraint;
import org.drools.rule.Not;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.spi.Consequence;
import org.drools.spi.ConsequenceException;
import org.drools.spi.Evaluator;
import org.drools.spi.FieldConstraint;
import org.drools.spi.FieldExtractor;
import org.drools.spi.FieldValue;
import org.drools.spi.KnowledgeHelper;
import org.drools.spi.MockField;
import org.drools.spi.Tuple;

public abstract class BaseMannersTest extends TestCase {
    /** Number of guests at the dinner (default: 16). */
    private final int       numGuests  = 16;

    /** Number of seats at the table (default: 16). */
    private final int       numSeats   = 16;

    /** Minimum number of hobbies each guest should have (default: 2). */
    private final int       minHobbies = 2;

    /** Maximun number of hobbies each guest should have (default: 3). */
    private final int       maxHobbies = 3;

    protected Package       pkg;

    private ClassObjectType contextType;
    private ClassObjectType guestType;
    private ClassObjectType seatingType;
    private ClassObjectType lastSeatType;
    private ClassObjectType countType;
    private ClassObjectType pathType;
    private ClassObjectType chosenType;
    private Evaluator       objectEqualEvaluator;
    private Evaluator       objectNotEqualEvaluator;
    private Evaluator       integerEqualEvaluator;
    private Evaluator       integerNotEqualEvaluator;
    private Evaluator       booleanEqualEvaluator;
    private Evaluator       booleanNotEqualEvaluator;

    protected void setUp() throws Exception {
        this.contextType = new ClassObjectType( Context.class );
        this.guestType = new ClassObjectType( Guest.class );
        this.seatingType = new ClassObjectType( Seating.class );
        this.lastSeatType = new ClassObjectType( LastSeat.class );
        this.countType = new ClassObjectType( Count.class );
        this.pathType = new ClassObjectType( Path.class );
        this.chosenType = new ClassObjectType( Chosen.class );

        this.integerEqualEvaluator = EvaluatorFactory.getInstance().getEvaluator( Evaluator.INTEGER_TYPE,
                                                                                  Evaluator.EQUAL );
        this.integerNotEqualEvaluator = EvaluatorFactory.getInstance().getEvaluator( Evaluator.INTEGER_TYPE,
                                                                                     Evaluator.NOT_EQUAL );

        this.objectEqualEvaluator = EvaluatorFactory.getInstance().getEvaluator( Evaluator.OBJECT_TYPE,
                                                                                 Evaluator.EQUAL );
        this.objectNotEqualEvaluator = EvaluatorFactory.getInstance().getEvaluator( Evaluator.OBJECT_TYPE,
                                                                                    Evaluator.NOT_EQUAL );

        this.booleanEqualEvaluator = EvaluatorFactory.getInstance().getEvaluator( Evaluator.BOOLEAN_TYPE,
                                                                                  Evaluator.EQUAL );
        this.booleanNotEqualEvaluator = EvaluatorFactory.getInstance().getEvaluator( Evaluator.BOOLEAN_TYPE,
                                                                                     Evaluator.NOT_EQUAL );

        this.pkg = new Package( "Miss Manners" );
        this.pkg.addRule( getAssignFirstSeatRule() );
        this.pkg.addRule( getFindSeating() );
        this.pkg.addRule( getPathDone() );
        this.pkg.addRule( getMakePath() );
        this.pkg.addRule( getContinueProcessing() );
        this.pkg.addRule( getAreWeDone() );
        this.pkg.addRule( getAllDone() );

    }

    /**
     * <pre>
     *    rule assignFirstSeat() {
     *        Context context;
     *        Guest guest;
     *        Count count;
     *        when {
     *            context : Context( state == Context.START_UP )
     *            guest : Guest()
     *            count : Count()
     *        } then {
     *            String guestName = guest.getName();
     *            drools.assert( new Seating( count.getValue(), 1, true, 1, guestName, 1, guestName) );
     *            drools.assert( new Path( count.getValue(), 1, guestName ) );
     *            count.setCount(  count.getValue() + 1 );
     *   
     *            System.out.println( &quot;seat 1 &quot; + guest.getName() + &quot; );
     *   
     *            context.setPath( Context.ASSIGN_SEATS );
     *        }
     *    } 
     * </pre>
     * 
     * 
     * @return
     * @throws IntrospectionException
     * @throws InvalidRuleException
     */
    private Rule getAssignFirstSeatRule() throws IntrospectionException,
                                         InvalidRuleException {
        final Rule rule = new Rule( "assignFirstSeat" );

        // -----------
        // context : Context( state == Context.START_UP )
        // -----------
        final Column contextColumn = new Column( 0,
                                                 this.contextType,
                                                 "context" );

        contextColumn.addConstraint( getLiteralConstraint( contextColumn,
                                                           "state",
                                                           new Integer( Context.START_UP ),
                                                           this.integerEqualEvaluator ) );

        rule.addPattern( contextColumn );

        final Declaration contextDeclaration = rule.getDeclaration( "context" );

        // -----------
        // guest: Guest()
        // -----------
        final Column guestColumn = new Column( 1,
                                               this.guestType,
                                               "guest" );

        rule.addPattern( guestColumn );

        final Declaration guestDeclaration = rule.getDeclaration( "guest" );

        // ------------
        // count : Count()
        // ------------
        final Column countColumn = new Column( 2,
                                               this.countType,
                                               "count" );

        rule.addPattern( countColumn );

        final Declaration countDeclaration = rule.getDeclaration( "count" );

        final Consequence consequence = new Consequence() {

            public void evaluate(KnowledgeHelper drools,
                                 WorkingMemory workingMemory) throws ConsequenceException {
                try {
                    Rule rule = drools.getRule();
                    Tuple tuple = drools.getTuple();

                    Guest guest = (Guest) drools.get( guestDeclaration );
                    Context context = (Context) drools.get( contextDeclaration );
                    Count count = (Count) drools.get( countDeclaration );

                    String guestName = guest.getName();

                    Seating seating = new Seating( count.getValue(),
                                                   0,
                                                   true,
                                                   1,
                                                   guestName,
                                                   1,
                                                   guestName );

                    drools.assertObject( seating );

                    Path path = new Path( count.getValue(),
                                          1,
                                          guestName );

                    drools.assertObject( path );

                    count.setValue( count.getValue() + 1 );
                    drools.modifyObject( tuple.get( countDeclaration ),
                                         count );

                    context.setState( Context.ASSIGN_SEATS );
                    drools.modifyObject( tuple.get( contextDeclaration ),
                                         context );
                    System.out.println( "assign first seat :  " + seating + " : " + path );

                } catch ( Exception e ) {
                    e.printStackTrace();
                    throw new ConsequenceException( e );
                }
            }

        };

        rule.setConsequence( consequence );

        return rule;
    }

    /**
     * <pre>
     *    rule findSeating() {
     *       Context context;
     *       int seatingId, seatingPid;
     *       String seatingRightGuestName, leftGuestName;
     *       Sex rightGuestSex;
     *       Hobby rightGuestHobby;
     *       Count count;
     *       
     *       when {
     *           context : Context( state == Context.ASSIGN_SEATS )
     *           Seating( seatingId:id, seatingPid:pid, pathDone == true 
     *                    seatingRightSeat:rightSeat seatingRightGuestName:rightGuestName )
     *           Guest( name == seatingRightGuestName, rightGuestSex:sex, rightGuestHobby:hobby )
     *           Guest( leftGuestName:name , sex != rightGuestSex, hobby == rightGuestHobby )
     *    
     *           count : Count()
     *    
     *           not ( Path( id == seatingId, guestName == leftGuestName) )
     *           not ( Chosen( id == seatingId, guestName == leftGuestName, hobby == rightGuestHobby) )
     *       } then {
     *           int newSeat = rightSeat + 1;
     *           drools.assert( new Seating( coung.getValue(), rightSeat, rightSeatName, leftGuestName, newSeat, countValue, id, false );
     *           drools.assert( new Path( countValue, leftGuestName, newSeat );
     *           drools.assert( new Chosen( id, leftGuestName, rightGuestHobby ) );
     *    
     *           System.out.println( &quot;seat &quot; + rightSeat + &quot; &quot; + rightSeatName + &quot; &quot; + leftGuestName );
     *    
     *           count.setCount(  countValue + 1 );
     *           context.setPath( Context.MAKE_PATH );
     *       }
     *    } 
     * </pre>
     * 
     * @return
     * @throws IntrospectionException
     * @throws InvalidRuleException
     */
    private Rule getFindSeating() throws IntrospectionException,
                                 InvalidRuleException {
        final Rule rule = new Rule( "findSeating" );

        // ---------------
        // context : Context( state == Context.ASSIGN_SEATS )
        // ---------------
        final Column contextColumn = new Column( 0,
                                                 this.contextType,
                                                 "context" );

        contextColumn.addConstraint( getLiteralConstraint( contextColumn,
                                                           "state",
                                                           new Integer( Context.ASSIGN_SEATS ),
                                                           this.integerEqualEvaluator ) );

        rule.addPattern( contextColumn );

        final Declaration contextDeclaration = rule.getDeclaration( "context" );

        // -------------------------------
        // Seating( seatingId:id, seatingPid:pid, pathDone == true
        // seatingRightSeat:rightSeat seatingRightGuestName:rightGuestName )
        // -------------------------------
        final Column seatingColumn = new Column( 1,
                                                 this.seatingType );

        setFieldDeclaration( seatingColumn,
                             "id",
                             "seatingId" );

        setFieldDeclaration( seatingColumn,
                             "pid",
                             "seatingPid" );

        seatingColumn.addConstraint( getLiteralConstraint( seatingColumn,
                                                           "pathDone",
                                                           new Boolean( true ),
                                                           this.booleanEqualEvaluator ) );

        setFieldDeclaration( seatingColumn,
                             "rightSeat",
                             "seatingRightSeat" );

        setFieldDeclaration( seatingColumn,
                             "rightGuestName",
                             "seatingRightGuestName" );

        rule.addPattern( seatingColumn );

        final Declaration seatingIdDeclaration = rule.getDeclaration( "seatingId" );
        final Declaration seatingPidDeclaration = rule.getDeclaration( "seatingPid" );
        final Declaration seatingRightGuestNameDeclaration = rule.getDeclaration( "seatingRightGuestName" );
        final Declaration seatingRightSeatDeclaration = rule.getDeclaration( "seatingRightSeat" );
        // --------------
        // Guest( name == seatingRightGuestName, rightGuestSex:sex,
        // rightGuestHobby:hobby )
        // ---------------
        final Column rightGuestColumn = new Column( 2,
                                                    this.guestType );

        rightGuestColumn.addConstraint( getBoundVariableConstraint( rightGuestColumn,
                                                                    "name",
                                                                    seatingRightGuestNameDeclaration,
                                                                    this.objectEqualEvaluator ) );

        setFieldDeclaration( rightGuestColumn,
                             "sex",
                             "rightGuestSex" );

        setFieldDeclaration( rightGuestColumn,
                             "hobby",
                             "rightGuestHobby" );

        rule.addPattern( rightGuestColumn );

        final Declaration rightGuestSexDeclaration = rule.getDeclaration( "rightGuestSex" );
        final Declaration rightGuestHobbyDeclaration = rule.getDeclaration( "rightGuestHobby" );

        // ----------------
        // Guest( leftGuestName:name , sex != rightGuestSex, hobby ==
        // rightGuestHobby )
        // ----------------
        final Column leftGuestColumn = new Column( 3,
                                                   this.guestType );

        setFieldDeclaration( leftGuestColumn,
                             "name",
                             "leftGuestName" );

        leftGuestColumn.addConstraint( getBoundVariableConstraint( leftGuestColumn,
                                                                   "sex",
                                                                   rightGuestSexDeclaration,
                                                                   this.objectNotEqualEvaluator ) );

        leftGuestColumn.addConstraint( getBoundVariableConstraint( rightGuestColumn,
                                                                   "hobby",
                                                                   rightGuestHobbyDeclaration,
                                                                   this.objectEqualEvaluator ) );
        rule.addPattern( leftGuestColumn );
        final Declaration leftGuestNameDeclaration = rule.getDeclaration( "leftGuestName" );

        // ---------------
        // count : Count()
        // ---------------
        final Column count = new Column( 4,
                                         this.countType,
                                         "count" );

        rule.addPattern( count );

        final Declaration countDeclaration = rule.getDeclaration( "count" );

        // --------------
        // not ( Path( id == seatingId, guestName == leftGuestName) )
        // --------------
        final Column notPathColumn = new Column( 5,
                                                 this.pathType );

        notPathColumn.addConstraint( getBoundVariableConstraint( notPathColumn,
                                                                 "id",
                                                                 seatingIdDeclaration,
                                                                 this.integerEqualEvaluator ) );

        notPathColumn.addConstraint( getBoundVariableConstraint( notPathColumn,
                                                                 "guestName",
                                                                 leftGuestNameDeclaration,
                                                                 this.objectEqualEvaluator ) );
        final Not notPath = new Not();
        notPath.addChild( notPathColumn );
        rule.addPattern( notPath );
        // ------------
        // not ( Chosen( id == seatingId, guestName == leftGuestName, hobby ==
        // rightGuestHobby ) )
        // ------------
        final Column notChosenColumn = new Column( 6,
                                                   this.chosenType );

        notChosenColumn.addConstraint( getBoundVariableConstraint( notChosenColumn,
                                                                   "id",
                                                                   seatingIdDeclaration,
                                                                   this.integerEqualEvaluator ) );

        notChosenColumn.addConstraint( getBoundVariableConstraint( notChosenColumn,
                                                                   "guestName",
                                                                   leftGuestNameDeclaration,
                                                                   this.objectEqualEvaluator ) );

        notChosenColumn.addConstraint( getBoundVariableConstraint( notChosenColumn,
                                                                   "hobby",
                                                                   rightGuestHobbyDeclaration,
                                                                   this.objectEqualEvaluator ) );

        final Not notChosen = new Not();
        notChosen.addChild( notChosenColumn );

        rule.addPattern( notChosen );

        // ------------
        // int newSeat = rightSeat + 1;
        // drools.assert( new Seating( coung.getValue(), rightSeat,
        // rightSeatName, leftGuestName, newSeat, countValue, id, false );
        // drools.assert( new Path( countValue, leftGuestName, newSeat );
        // drools.assert( new Chosen( id, leftGuestName, rightGuestHobby ) );
        // 
        // System.out.println( "seat " + rightSeat + " " + rightSeatName + " " +
        // leftGuestName );
        //
        // count.setCount( countValue + 1 );
        // context.setPath( Context.MAKE_PATH );
        // ------------
        final Consequence consequence = new Consequence() {

            public void evaluate(KnowledgeHelper drools,
                                 WorkingMemory workingMemory) throws ConsequenceException {
                try {
                    Rule rule = drools.getRule();
                    Tuple tuple = drools.getTuple();

                    Context context = (Context) drools.get( contextDeclaration );
                    Count count = (Count) drools.get( countDeclaration );
                    int seatId = ((Integer) drools.get( seatingIdDeclaration )).intValue();
                    int seatingRightSeat = ((Integer) drools.get( seatingRightSeatDeclaration )).intValue();

                    String leftGuestName = (String) drools.get( leftGuestNameDeclaration );
                    String rightGuestName = (String) drools.get( seatingRightGuestNameDeclaration );
                    Hobby rightGuestHobby = (Hobby) drools.get( rightGuestHobbyDeclaration );

                    Seating seating = new Seating( count.getValue(),
                                                   seatId,
                                                   false,
                                                   seatingRightSeat,
                                                   rightGuestName,
                                                   seatingRightSeat + 1,
                                                   leftGuestName );
                    drools.assertObject( seating );

                    Path path = new Path( count.getValue(),
                                          seatingRightSeat + 1,
                                          leftGuestName );

                    drools.assertObject( path );

                    Chosen chosen = new Chosen( seatId,
                                                leftGuestName,
                                                rightGuestHobby );

                    drools.assertObject( chosen );
                    count.setValue( count.getValue() + 1 );

                    //                    if ( count.getValue() == 5 ) {
                    //                        drools.retractObject( tuple.getFactHandleForDeclaration( countDeclaration ) );
                    //                    } else {
                    //                        drools.modifyObject( tuple.getFactHandleForDeclaration( countDeclaration ),
                    //                                             count );                        
                    //                    }

                    drools.modifyObject( tuple.get( countDeclaration ),
                                         count );

                    context.setState( Context.MAKE_PATH );
                    drools.modifyObject( tuple.get( contextDeclaration ),
                                         context );

                    System.err.println( "find seating : " + seating + " : " + path + " : " + chosen );

                } catch ( Exception e ) {
                    e.printStackTrace();
                    throw new ConsequenceException( e );
                }
            }

        };

        rule.setConsequence( consequence );

        return rule;
    }

    /**
     * <pre>
     *    rule makePath() {
     *        Context context;
     *        int seatingId, seatingPid, pathSeat;
     *        String pathGuestName;
     *   
     *        when {
     *            Context( state == Context.MAKE_PATH )
     *            Seating( seatingId:id, seatingPid:pid, pathDone == false )
     *            Path( id == seatingPid, pathGuestName:guest, pathSeat:seat )
     *            (not Path( id == seatingId, guestName == pathGuestName )
     *        } else {
     *            drools.assert( new Path( seatingId, pathSeat, pathGuestName ) );
     *   
     *        }
     *    } 
     * </pre>
     * 
     * @return
     * @throws IntrospectionException
     * @throws InvalidRuleException
     */
    private Rule getMakePath() throws IntrospectionException,
                              InvalidRuleException {
        final Rule rule = new Rule( "makePath" );

        // -----------
        // context : Context( state == Context.MAKE_PATH )
        // -----------
        final Column contextColumn = new Column( 0,
                                                 this.contextType );

        contextColumn.addConstraint( getLiteralConstraint( contextColumn,
                                                           "state",
                                                           new Integer( Context.MAKE_PATH ),
                                                           this.integerEqualEvaluator ) );

        rule.addPattern( contextColumn );

        // ---------------
        // Seating( seatingId:id, seatingPid:pid, pathDone == false )
        // ---------------
        final Column seatingColumn = new Column( 1,
                                                 this.seatingType );

        setFieldDeclaration( seatingColumn,
                             "id",
                             "seatingId" );

        setFieldDeclaration( seatingColumn,
                             "pid",
                             "seatingPid" );

        seatingColumn.addConstraint( getLiteralConstraint( seatingColumn,
                                                           "pathDone",
                                                           new Boolean( false ),
                                                           this.booleanEqualEvaluator ) );

        rule.addPattern( seatingColumn );

        final Declaration seatingIdDeclaration = rule.getDeclaration( "seatingId" );
        final Declaration seatingPidDeclaration = rule.getDeclaration( "seatingPid" );

        // -----------
        // Path( id == seatingPid, pathGuestName:guestName, pathSeat:seat )
        // -----------
        final Column pathColumn = new Column( 2,
                                              this.pathType );

        pathColumn.addConstraint( getBoundVariableConstraint( pathColumn,
                                                              "id",
                                                              seatingPidDeclaration,
                                                              this.integerEqualEvaluator ) );

        setFieldDeclaration( pathColumn,
                             "guestName",
                             "pathGuestName" );

        setFieldDeclaration( pathColumn,
                             "seat",
                             "pathSeat" );

        rule.addPattern( pathColumn );

        final Declaration pathGuestNameDeclaration = rule.getDeclaration( "pathGuestName" );
        final Declaration pathSeatDeclaration = rule.getDeclaration( "pathSeat" );
        // -------------
        // (not Path( id == seatingId, guestName == pathGuestName )
        // -------------
        final Column notPathColumn = new Column( 3,
                                                 this.pathType );

        notPathColumn.addConstraint( getBoundVariableConstraint( notPathColumn,
                                                                 "id",
                                                                 seatingIdDeclaration,
                                                                 this.integerEqualEvaluator ) );
        notPathColumn.addConstraint( getBoundVariableConstraint( notPathColumn,
                                                                 "guestName",
                                                                 pathGuestNameDeclaration,
                                                                 this.objectEqualEvaluator ) );

        final Not not = new Not();

        not.addChild( notPathColumn );

        rule.addPattern( not );

        // ------------
        // drools.assert( new Path( id, pathName, pathSeat ) );
        // ------------
        final Consequence consequence = new Consequence() {

            public void evaluate(KnowledgeHelper drools,
                                 WorkingMemory workingMemory) throws ConsequenceException {
                try {
                    Rule rule = drools.getRule();
                    Tuple tuple = drools.getTuple();

                    int id = ((Integer) drools.get( seatingIdDeclaration )).intValue();
                    int seat = ((Integer) drools.get( pathSeatDeclaration )).intValue();
                    String guestName = (String) drools.get( pathGuestNameDeclaration );

                    Path path = new Path( id,
                                          seat,
                                          guestName );

                    drools.assertObject( path );

                    //System.out.println( "make path : " + path );
                } catch ( Exception e ) {
                    e.printStackTrace();
                    throw new ConsequenceException( e );
                }
            }

        };

        rule.setConsequence( consequence );

        return rule;
    }

    /**
     * 
     * <pre>
     * rule pathDone() { 
     *     Context context; Seating seating; 
     *     when { 
     *         context : Context( state == Context.MAKE_PATH ) 
     *         seating : Seating( pathDone == false ) 
     *     } then { 
     *         seating.setPathDone( true ); 
     *         context.setName( Context.CHECK_DONE ); 
     *     } 
     * }
     * </pre>
     * 
     * @return
     * @throws IntrospectionException
     * @throws InvalidRuleException
     */
    private Rule getPathDone() throws IntrospectionException,
                              InvalidRuleException {
        final Rule rule = new Rule( "pathDone" );

        // -----------
        // context : Context( state == Context.MAKE_PATH )
        // -----------
        final Column contextColumn = new Column( 0,
                                                 this.contextType,
                                                 "context" );

        contextColumn.addConstraint( getLiteralConstraint( contextColumn,
                                                           "state",
                                                           new Integer( Context.MAKE_PATH ),
                                                           this.integerEqualEvaluator ) );

        rule.addPattern( contextColumn );
        final Declaration contextDeclaration = rule.getDeclaration( "context" );

        // ---------------
        // seating : Seating( pathDone == false )
        // ---------------
        final Column seatingColumn = new Column( 1,
                                                 this.seatingType,
                                                 "seating" );

        seatingColumn.addConstraint( getLiteralConstraint( seatingColumn,
                                                           "pathDone",
                                                           new Boolean( false ),
                                                           this.booleanEqualEvaluator ) );

        rule.addPattern( seatingColumn );

        final Declaration seatingDeclaration = rule.getDeclaration( "seating" );

        // ------------
        // context.setName( Context.CHECK_DONE );
        // seating.setPathDone( true );
        // ------------
        final Consequence consequence = new Consequence() {

            public void evaluate(KnowledgeHelper drools,
                                 WorkingMemory workingMemory) throws ConsequenceException {
                try {
                    Rule rule = drools.getRule();
                    Tuple tuple = drools.getTuple();

                    Context context = (Context) drools.get( contextDeclaration );
                    Seating seating = (Seating) drools.get( seatingDeclaration );

                    seating.setPathDone( true );

                    if ( seating.getId() == 6 ) {
                        System.out.println( "pause" );
                    }
                    drools.modifyObject( tuple.get( seatingDeclaration ),
                                         seating );

                    context.setState( Context.CHECK_DONE );
                    drools.modifyObject( tuple.get( contextDeclaration ),
                                         context );
                    //System.out.println( "path done" + seating );
                } catch ( Exception e ) {
                    throw new ConsequenceException( e );
                }
            }

        };

        rule.setConsequence( consequence );

        return rule;
    }

    /**
     * <pre>
     * rule areWeDone() { 
     *     Context context; LastSeat lastSear; 
     *     when { 
     *         context : Context( state == Context.CHECK_DONE ) 
     *         LastSeat( lastSeat: seat )
     *         Seating( rightSeat == lastSeat ) 
     *     } then { 
     *         context.setState(Context.PRINT_RESULTS ); 
     *     } 
     * }
     * </pre>
     * 
     * @return
     * @throws IntrospectionException
     * @throws InvalidRuleException
     */
    private Rule getAreWeDone() throws IntrospectionException,
                               InvalidRuleException {
        final Rule rule = new Rule( "areWeDone" );

        // -----------
        // context : Context( state == Context.CHECK_DONE )
        // -----------
        final Column contextColumn = new Column( 0,
                                                 this.contextType,
                                                 "context" );

        contextColumn.addConstraint( getLiteralConstraint( contextColumn,
                                                           "state",
                                                           new Integer( Context.CHECK_DONE ),
                                                           this.integerEqualEvaluator ) );

        rule.addPattern( contextColumn );
        final Declaration contextDeclaration = rule.getDeclaration( "context" );

        // ---------------
        // LastSeat( lastSeat: seat )
        // ---------------
        final Column lastSeatColumn = new Column( 1,
                                                  this.lastSeatType );

        setFieldDeclaration( lastSeatColumn,
                             "seat",
                             "lastSeat" );

        rule.addPattern( lastSeatColumn );
        final Declaration lastSeatDeclaration = rule.getDeclaration( "lastSeat" );
        // -------------
        // Seating( rightSeat == lastSeat )
        // -------------
        final Column seatingColumn = new Column( 2,
                                                 this.seatingType,
                                                 null );

        seatingColumn.addConstraint( getBoundVariableConstraint( seatingColumn,
                                                                 "rightSeat",
                                                                 lastSeatDeclaration,
                                                                 this.integerEqualEvaluator ) );

        rule.addPattern( seatingColumn );

        // ------------
        // context.setName( Context.PRINT_RESULTS );
        // ------------
        final Consequence consequence = new Consequence() {

            public void evaluate(KnowledgeHelper drools,
                                 WorkingMemory workingMemory) throws ConsequenceException {
                try {
                    Rule rule = drools.getRule();
                    Tuple tuple = drools.getTuple();

                    Context context = (Context) drools.get( contextDeclaration );
                    context.setState( Context.PRINT_RESULTS );

                    drools.modifyObject( tuple.get( contextDeclaration ),
                                         context );

                    System.err.println( "We Are Done!!!" );
                } catch ( Exception e ) {
                    throw new ConsequenceException( e );
                }
            }

        };

        rule.setConsequence( consequence );

        return rule;
    }

    /**
     * <pre>
     * rule continue() { 
     *     Context context; 
     *     when { 
     *         context : Context( state == Context.CHECK_DONE ) 
     *     } then { 
     *         context.setState( Context.ASSIGN_SEATS ); 
     *     } 
     * }
     * </pre>
     * @return
     * @throws IntrospectionException
     * @throws InvalidRuleException
     */
    private Rule getContinueProcessing() throws IntrospectionException,
                                        InvalidRuleException {
        final Rule rule = new Rule( "continueProcessng" );

        // -----------
        // context : Context( state == Context.CHECK_DONE )
        // -----------
        final Column contextColumn = new Column( 0,
                                                 this.contextType,
                                                 "context" );

        contextColumn.addConstraint( getLiteralConstraint( contextColumn,
                                                           "state",
                                                           new Integer( Context.CHECK_DONE ),
                                                           this.integerEqualEvaluator ) );

        rule.addPattern( contextColumn );
        final Declaration contextDeclaration = rule.getDeclaration( "context" );

        // ------------
        // context.setName( Context.ASSIGN_SEATS );
        // ------------
        final Consequence consequence = new Consequence() {

            public void evaluate(KnowledgeHelper drools,
                                 WorkingMemory workingMemory) throws ConsequenceException {
                try {
                    Rule rule = drools.getRule();
                    Tuple tuple = drools.getTuple();

                    Context context = (Context) drools.get( contextDeclaration );
                    context.setState( Context.ASSIGN_SEATS );

                    drools.modifyObject( tuple.get( contextDeclaration ),
                                         context );

                    //System.out.println( "continue processing" );
                } catch ( Exception e ) {
                    e.printStackTrace();
                    throw new ConsequenceException( e );
                }
            }

        };

        rule.setConsequence( consequence );

        return rule;
    }

    /**
     * <pre>
     * rule all_done() { 
     *     Context context; 
     *     when { 
     *         context : Context( state == Context.PRINT_RESULTS ) 
     *     } then {
     *     } 
     * }
     * </pre>
     * 
     * @return
     * @throws IntrospectionException
     * @throws InvalidRuleException
     */
    private Rule getAllDone() throws IntrospectionException,
                             InvalidRuleException {
        final Rule rule = new Rule( "alldone" );

        // -----------
        // context : Context( state == Context.PRINT_RESULTS )
        // -----------
        final Column contextColumn = new Column( 0,
                                                 this.contextType );

        contextColumn.addConstraint( getLiteralConstraint( contextColumn,
                                                           "state",
                                                           new Integer( Context.PRINT_RESULTS ),
                                                           this.integerEqualEvaluator ) );

        rule.addPattern( contextColumn );
        final Declaration contextDeclaration = rule.getDeclaration( "context" );

        // ------------
        //     
        // ------------
        final Consequence consequence = new Consequence() {

            public void evaluate(KnowledgeHelper drools,
                                 WorkingMemory workingMemory) throws ConsequenceException {
                try {
                    System.err.println( "all done" );
                } catch ( Exception e ) {
                    throw new ConsequenceException( e );
                }
            }

        };

        rule.setConsequence( consequence );

        return rule;
    }

    /**
     * Convert the facts from the <code>InputStream</code> to a list of
     * objects.
     */
    protected List getInputObjects(final InputStream inputStream) throws IOException {
        final List list = new ArrayList();

        final BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ) );

        String line;
        while ( (line = br.readLine()) != null ) {
            if ( line.trim().length() == 0 || line.trim().startsWith( ";" ) ) {
                continue;
            }
            final StringTokenizer st = new StringTokenizer( line,
                                                            "() " );
            final String type = st.nextToken();

            if ( "guest".equals( type ) ) {
                if ( !"name".equals( st.nextToken() ) ) {
                    throw new IOException( "expected 'name' in: " + line );
                }
                final String name = st.nextToken();
                if ( !"sex".equals( st.nextToken() ) ) {
                    throw new IOException( "expected 'sex' in: " + line );
                }
                final String sex = st.nextToken();
                if ( !"hobby".equals( st.nextToken() ) ) {
                    throw new IOException( "expected 'hobby' in: " + line );
                }
                final String hobby = st.nextToken();

                final Guest guest = new Guest( name,
                                               Sex.resolve( sex ),
                                               Hobby.resolve( hobby ) );

                list.add( guest );
            }

            if ( "last_seat".equals( type ) ) {
                if ( !"seat".equals( st.nextToken() ) ) {
                    throw new IOException( "expected 'seat' in: " + line );
                }
                list.add( new LastSeat( new Integer( st.nextToken() ).intValue() ) );
            }

            if ( "context".equals( type ) ) {
                if ( !"state".equals( st.nextToken() ) ) {
                    throw new IOException( "expected 'state' in: " + line );
                }
                list.add( new Context( st.nextToken() ) );
            }
        }
        inputStream.close();

        return list;
    }

    private InputStream generateData() {
        final String LINE_SEPARATOR = System.getProperty( "line.separator" );

        final StringWriter writer = new StringWriter();

        final int maxMale = this.numGuests / 2;
        final int maxFemale = this.numGuests / 2;

        int maleCount = 0;
        int femaleCount = 0;

        // init hobbies
        final List hobbyList = new ArrayList();
        for ( int i = 1; i <= this.maxHobbies; i++ ) {
            hobbyList.add( "h" + i );
        }

        final Random rnd = new Random();
        for ( int i = 1; i <= this.numGuests; i++ ) {
            char sex = rnd.nextBoolean() ? 'm' : 'f';
            if ( sex == 'm' && maleCount == maxMale ) {
                sex = 'f';
            }
            if ( sex == 'f' && femaleCount == maxFemale ) {
                sex = 'm';
            }
            if ( sex == 'm' ) {
                maleCount++;
            }
            if ( sex == 'f' ) {
                femaleCount++;
            }

            final List guestHobbies = new ArrayList( hobbyList );

            final int numHobbies = this.minHobbies + rnd.nextInt( this.maxHobbies - this.minHobbies + 1 );
            for ( int j = 0; j < numHobbies; j++ ) {
                final int hobbyIndex = rnd.nextInt( guestHobbies.size() );
                final String hobby = (String) guestHobbies.get( hobbyIndex );
                writer.write( "(guest (name n" + i + ") (sex " + sex + ") (hobby " + hobby + "))" + LINE_SEPARATOR );
                guestHobbies.remove( hobbyIndex );
            }
        }
        writer.write( "(last_seat (seat " + this.numSeats + "))" + LINE_SEPARATOR );

        writer.write( LINE_SEPARATOR );
        writer.write( "(context (state start))" + LINE_SEPARATOR );

        return new ByteArrayInputStream( writer.getBuffer().toString().getBytes() );
    }

    public static int getIndex(final Class clazz,
                               final String name) throws IntrospectionException {
        final PropertyDescriptor[] descriptors = Introspector.getBeanInfo( clazz ).getPropertyDescriptors();
        for ( int i = 0; i < descriptors.length; i++ ) {
            if ( descriptors[i].getName().equals( name ) ) {
                return i;
            }
        }
        return -1;
    }

    private FieldConstraint getLiteralConstraint(final Column column,
                                                 final String fieldName,
                                                 final Object fieldValue,
                                                 final Evaluator evaluator) throws IntrospectionException {
        final Class clazz = ((ClassObjectType) column.getObjectType()).getClassType();

        final FieldExtractor extractor = new ClassFieldExtractor( clazz,
                                                                  fieldName );

        final FieldValue field = new MockField( fieldValue );

        return new LiteralConstraint( field,
                                      extractor,
                                      evaluator );
    }

    private void setFieldDeclaration(final Column column,
                                     final String fieldName,
                                     final String identifier) throws IntrospectionException {
        final Class clazz = ((ClassObjectType) column.getObjectType()).getClassType();

        final FieldExtractor extractor = new ClassFieldExtractor( clazz,
                                                                  fieldName );

        column.addDeclaration( identifier,
                               extractor );
    }

    private FieldConstraint getBoundVariableConstraint(final Column column,
                                                       final String fieldName,
                                                       final Declaration declaration,
                                                       final Evaluator evaluator) throws IntrospectionException {
        final Class clazz = ((ClassObjectType) column.getObjectType()).getClassType();

        final FieldExtractor extractor = new ClassFieldExtractor( clazz,
                                                                  fieldName );

        return new BoundVariableConstraint( extractor,
                                            declaration,
                                            evaluator );
    }
}