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

import java.io.Serializable;

import org.drools.spi.PropagationContext;

/**
 * Receiver of propagated <code>ReteTuple</code>s from a
 * <code>TupleSource</code>.
 * 
 * @see TupleSource
 * 
 * @author <a href="mailto:mark.proctor@jboss.com">Mark Proctor</a>
 * @author <a href="mailto:bob@werken.com">Bob McWhirter</a>
 */
interface TupleSink
    extends
    Serializable {

    /**
     * Assert a new <code>ReteTuple</code>.
     * 
     * @param tuple
     *            The <code>ReteTuple</code> to propagate.
     * @param context
     *             The <code>PropagationContext</code> of the <code>WorkingMemory<code> action            
     * @param workingMemory
     *            the <code>WorkingMemory</code> session.
     */
    void assertTuple(ReteTuple tuple,
                     PropagationContext context,
                     ReteooWorkingMemory workingMemory);

    void retractTuple(ReteTuple tuple,
                      PropagationContext context,
                      ReteooWorkingMemory workingMemory);

    void modifyTuple(ReteTuple tuple,
                     PropagationContext context,
                     ReteooWorkingMemory workingMemory);

}
