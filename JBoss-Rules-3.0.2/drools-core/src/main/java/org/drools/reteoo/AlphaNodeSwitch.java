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

package org.drools.reteoo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.drools.WorkingMemory;
import org.drools.common.InternalFactHandle;
import org.drools.rule.LiteralConstraint;

/**
 * AlphaNodeSwitch
 * A LiteralConstraint wrapper to be used on alpha node hashing algorithm
 *
 * @author <a href="mailto:tirelli@post.com">Edson Tirelli</a>
 *
 * Created: 04/03/2006
 */
public class AlphaNodeSwitch
    implements
    Serializable {
    /**
     * 
     */
    private static final long       serialVersionUID = 3574628117634649074L;
    private final LiteralConstraint constraint;
    private final Map               alphaSwitch;

    public AlphaNodeSwitch(final LiteralConstraint constraint) {
        this.constraint = constraint;
        this.alphaSwitch = new HashMap();
    }

    public void addAlphaNode(final AlphaNode node) {
        final LiteralConstraint constraint = (LiteralConstraint) node.getConstraint();
        this.alphaSwitch.put( constraint.getField().getValue(),
                              node );
    }

    public boolean removeAlphaNode(final AlphaNode node) {
        final LiteralConstraint constraint = (LiteralConstraint) node.getConstraint();
        return this.alphaSwitch.remove( constraint.getField().getValue() ) != null;
    }

    public AlphaNode getNode(final WorkingMemory workingMemory,
                             final InternalFactHandle handle) {
        final Object value = this.constraint.getFieldExtractor().getValue( handle.getObject() );
        return (AlphaNode) this.alphaSwitch.get( value );
    }

    public int getSwitchCount() {
        return this.alphaSwitch.size();
    }

    public Collection getAllNodes() {
        return this.alphaSwitch.values();
    }

    public boolean equals(final Object otherConstraint) {
        if ( this == otherConstraint ) {
            return true;
        }

        if ( (otherConstraint != null) && (otherConstraint instanceof AlphaNodeSwitch) ) {
            final AlphaNodeSwitch other = (AlphaNodeSwitch) otherConstraint;
            if ( (this.constraint.getEvaluator().getOperator() == other.constraint.getEvaluator().getOperator()) && (this.constraint.getFieldExtractor().getIndex() == other.constraint.getFieldExtractor().getIndex()) ) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.constraint.getEvaluator().getOperator() * 17 + this.constraint.getFieldExtractor().getIndex();

    }

}
