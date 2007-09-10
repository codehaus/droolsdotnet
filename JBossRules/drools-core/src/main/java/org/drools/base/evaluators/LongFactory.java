package org.drools.base.evaluators;

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

import org.drools.base.BaseEvaluator;
import org.drools.spi.Evaluator;

public class LongFactory {
    public static Evaluator getLongEvaluator(final int operator) {
        switch ( operator ) {
            case Evaluator.EQUAL :
                return LongEqualEvaluator.INSTANCE;
            case Evaluator.NOT_EQUAL :
                return LongNotEqualEvaluator.INSTANCE;
            case Evaluator.LESS :
                return LongLessEvaluator.INSTANCE;
            case Evaluator.LESS_OR_EQUAL :
                return LongLessOrEqualEvaluator.INSTANCE;
            case Evaluator.GREATER :
                return LongGreaterEvaluator.INSTANCE;
            case Evaluator.GREATER_OR_EQUAL :
                return LongGreaterOrEqualEvaluator.INSTANCE;
            default :
                throw new RuntimeException( "Operator '" + operator + "' does not exist for LongEvaluator" );
        }
    }

    static class LongEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = 952936509469163071L;
        public final static Evaluator INSTANCE         = new LongEqualEvaluator();

        private LongEqualEvaluator() {
            super( Evaluator.LONG_TYPE,
                   Evaluator.EQUAL );
        }

        public boolean evaluate(final Object object1,
                                final Object object2) {
            if ( object1 == null ) {
                return object2 == null;
            }
            return ((Number) object1).equals( object2 );
        }

        public String toString() {
            return "Long ==";
        }
    }

    static class LongNotEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = -6007396753250538232L;
        public final static Evaluator INSTANCE         = new LongNotEqualEvaluator();

        private LongNotEqualEvaluator() {
            super( Evaluator.LONG_TYPE,
                   Evaluator.NOT_EQUAL );
        }

        public boolean evaluate(final Object object1,
                                final Object object2) {
            if ( object1 == null ) {
                return object2 != null;
            }
            return !((Number) object1).equals( object2 );
        }

        public String toString() {
            return "Long !=";
        }
    }

    static class LongLessEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = -4364068749553989563L;
        public final static Evaluator INSTANCE         = new LongLessEvaluator();

        private LongLessEvaluator() {
            super( Evaluator.LONG_TYPE,
                   Evaluator.LESS );
        }

        public boolean evaluate(final Object object1,
                                final Object object2) {
            return ((Number) object1).longValue() < ((Number) object2).longValue();
        }

        public String toString() {
            return "Long <";
        }
    }

    static class LongLessOrEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = -6047195686511631405L;
        public final static Evaluator INSTANCE         = new LongLessOrEqualEvaluator();

        private LongLessOrEqualEvaluator() {
            super( Evaluator.LONG_TYPE,
                   Evaluator.LESS_OR_EQUAL );
        }

        public boolean evaluate(final Object object1,
                                final Object object2) {
            return ((Number) object1).longValue() <= ((Number) object2).longValue();
        }

        public String toString() {
            return "Long <=";
        }
    }

    static class LongGreaterEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long     serialVersionUID = -6931144987779205475L;
        public final static Evaluator INSTANCE         = new LongGreaterEvaluator();

        private LongGreaterEvaluator() {
            super( Evaluator.LONG_TYPE,
                   Evaluator.GREATER );
        }

        public boolean evaluate(final Object object1,
                                final Object object2) {
            return ((Number) object1).longValue() > ((Number) object2).longValue();
        }

        public String toString() {
            return "Long >";
        }
    }

    static class LongGreaterOrEqualEvaluator extends BaseEvaluator {
        /**
         * 
         */
        private static final long      serialVersionUID = 4533604086739451238L;
        private final static Evaluator INSTANCE         = new LongGreaterOrEqualEvaluator();

        private LongGreaterOrEqualEvaluator() {
            super( Evaluator.LONG_TYPE,
                   Evaluator.GREATER_OR_EQUAL );
        }

        public boolean evaluate(final Object object1,
                                final Object object2) {
            return ((Number) object1).longValue() >= ((Number) object2).longValue();
        }

        public String toString() {
            return "Long >=";
        }
    }

}