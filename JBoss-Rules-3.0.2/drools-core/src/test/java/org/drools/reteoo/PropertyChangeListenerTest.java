package org.drools.reteoo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import junit.framework.TestCase;

import org.drools.base.ClassObjectType;

public class PropertyChangeListenerTest extends TestCase {
    public void test1() {
        final ReteooRuleBase ruleBase = new ReteooRuleBase();

        final ReteooWorkingMemory workingMemory = (ReteooWorkingMemory) ruleBase.newWorkingMemory();

        final Rete rete = ruleBase.getRete();

        final ObjectTypeNode objectTypeNode = new ObjectTypeNode( 1,
                                                                  new ClassObjectType( State.class ),
                                                                  rete );

        objectTypeNode.attach();

        final MockObjectSink sink = new MockObjectSink();
        objectTypeNode.addObjectSink( sink );

        final State a = new State( "go" );
        workingMemory.assertObject( a,
                                    true );

        assertEquals( 1,
                      sink.getAsserted().size() );
        assertEquals( 0,
                      sink.getModified().size() );

        a.setState( "stop" );

        assertEquals( 1,
                      sink.getModified().size() );
    }

    public static class State {
        private final PropertyChangeSupport changes = new PropertyChangeSupport( this );

        private String                      state;

        public State(final String state) {
            this.state = state;
        }

        public String getState() {
            return this.state;
        }

        public void setState(final String newState) {
            final String oldState = this.state;
            this.state = newState;
            this.changes.firePropertyChange( "state",
                                             oldState,
                                             newState );
        }

        public void addPropertyChangeListener(final PropertyChangeListener l) {
            this.changes.addPropertyChangeListener( l );
        }

        public void removePropertyChangeListener(final PropertyChangeListener l) {
            this.changes.removePropertyChangeListener( l );
        }
    }
}
