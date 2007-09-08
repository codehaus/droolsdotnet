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

import org.drools.common.DefaultFactHandle;
import org.drools.util.BaseMultiLinkedListNode;
import org.drools.util.LinkedList;

/**
 * 
 * <code>ObjectMatches</code> maintains a reference to its <code>FactHandleImpl</code> and a <code>LinkedList</code> of <code>TupleMatch</code>es.
 * 
 * @see TupleMatch
 * @see LinkedList
 * 
 * @author <a href="mailto:mark.proctor@jboss.com">Mark Proctor</a>
 * @author <a href="mailto:bob@werken.com">Bob McWhirter</a>
 *
 */
public class ObjectMatches extends BaseMultiLinkedListNode {
    private LinkedList              list;

    private final DefaultFactHandle handle;

    /**
     * Constructs an ObjectMatches which maintain a reference to its <code>FactHandleImpl</code> with an empty <code>LinkedList</code>.
     *  
     * @param handle
     */
    public ObjectMatches(final DefaultFactHandle handle) {
        this.list = new LinkedList();
        this.handle = handle;
    }

    /**
     * Adds a matched <code>ReteTuple</code>, which is then wrapped in a <code>TupleMatch</code> which is added to 
     * the <code>LinkedList</code> and then returned.
     * 
     * @param tuple
     * @return
     */
    TupleMatch add(final ReteTuple tuple) {
        final TupleMatch tupleMatch = new TupleMatch( tuple,
                                                      this );

        this.list.add( tupleMatch );

        return tupleMatch;
    }
    
    void add(final TupleMatch tupleMatch) {
        this.list.add( tupleMatch );
    }

    /**
     * Removes the <code>TupleMatch</code> as the underlying <code>ReteTuple</code> has been retracted and no longer matches.
     * 
     * @param tupleMatch
     */
    void remove(final TupleMatch tupleMatch) {
        this.list.remove( tupleMatch );
    }

    /**
     * Return <code>FactHandleImpl</code> that this provides the <code>TupleMatch</code>es for.
     * @return
     */
    public DefaultFactHandle getFactHandle() {
        return this.handle;
    }

    /**
     * Return the first <code>TupleMatch</code> that this <code>FactHandleImpl</code> matches.
     * 
     * @return the <code>TupleMatch</code>
     */
    public TupleMatch getFirstTupleMatch() {
        return (TupleMatch) this.list.getFirst();
    }

    /**
     * Return the last <code>TupleMatch</code> that this <code>FactHandleImpl</code> matches.
     * 
     * @return the <code>TupleMatch</code>
     */
    public TupleMatch getLastTupleMatch() {
        return (TupleMatch) this.list.getLast();
    }

    public int size() {
        return this.list.size();
    }

    public boolean hasMatches() {
        return !this.list.isEmpty();
    }
}
