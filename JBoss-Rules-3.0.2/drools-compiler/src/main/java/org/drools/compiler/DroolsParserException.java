package org.drools.compiler;

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

import org.drools.CheckedDroolsException;

public class DroolsParserException extends CheckedDroolsException {
    /**
     * 
     */
    private static final long serialVersionUID = 5478531718450682607L;

    /**
     * @see java.lang.Exception#Exception()
     */
    public DroolsParserException() {
        super();
    }

    /**
     * @see java.lang.Exception#Exception(String message)
     */
    public DroolsParserException(final String message) {
        super( message );
    }

    /**
     * @see java.lang.Exception#Exception(String message, Throwable cause)
     */
    public DroolsParserException(final String message,
                                 final Throwable cause) {
        super( message );
    }

    /**
     * @see java.lang.Exception#Exception(Throwable cause)
     */
    public DroolsParserException(final Throwable cause) {
        super( cause );
    }

}