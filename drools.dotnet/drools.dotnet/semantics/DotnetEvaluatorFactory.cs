/** 
* Copyright (c) 2007, Ritu Jain, Chinmay Nagarkar and Sahi Technologies Pvt Ltd
* All rights reserved.
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the Sahi Technologies Pvt. Ltd./Esahi.com  nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
using System;
using System.Collections.Generic;
using System.Text;
using org.drools.spi;
using java.lang;
using org.drools.dotnet.evaluator;

namespace org.drools.dotnet.semantics
{
    public class DotnetEvaluatorFactory 
    {
        private static readonly DotnetEvaluatorFactory INSTANCE = new DotnetEvaluatorFactory();
        public static DotnetEvaluatorFactory getInstance()
        {
            return INSTANCE;
        }

        public static Evaluator getEvaluator(java.lang.Integer type, string oper) 
        {
            Evaluator evaluator = null;
            if ( oper.Equals( "==" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.EQUAL) );
            } else if ( oper.Equals( "!=" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.NOT_EQUAL) );
            } else if ( oper.Equals( "<" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.LESS));
            } else if ( oper.Equals( "<=" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.LESS_OR_EQUAL ));
            } else if ( oper.Equals( ">" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.GREATER));
            } else if ( oper.Equals( ">=" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.GREATER_OR_EQUAL ));
            } else if ( oper.Equals( "contains" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.CONTAINS));
            } else if ( oper.Equals( "matches" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.MATCHES ));
            } else if ( oper.Equals( "excludes" ) ) {
                evaluator = getEvaluator( type,
                                          new Integer(Evaluator.__Fields.EXCLUDES));
            } else {
                throw new IllegalArgumentException( "Unknown oper: '" + oper + "'" );
            }

            return evaluator;
        }

        public static Evaluator getEvaluator(Integer typeObj,
                                              Integer operObj)
        {
            int type = typeObj.intValue();
            int oper = operObj.intValue();
            switch (type)
            {
                case Evaluator.__Fields.STRING_TYPE:
                    return StringFactory.getStringEvaluator(oper);
                case Evaluator.__Fields.OBJECT_TYPE:
                    return ObjectFactory.getObjectEvaluator(oper);
                case Evaluator.__Fields.SHORT_TYPE:
                    return ShortFactory.getShortEvaluator(oper);
                case Evaluator.__Fields.INTEGER_TYPE:
                    return IntegerFactory.getIntegerEvaluator(oper);
                case Evaluator.__Fields.BOOLEAN_TYPE:
                    return BooleanFactory.getBooleanEvaluator(oper);
                case Evaluator.__Fields.DOUBLE_TYPE:
                    return DoubleFactory.getDoubleEvaluator(oper);
                case Evaluator.__Fields.CHAR_TYPE:
                    return CharacterFactory.getCharacterEvaluator(oper);
                case Evaluator.__Fields.BYTE_TYPE:
                    return ByteFactory.getByteEvaluator(oper);
                case Evaluator.__Fields.FLOAT_TYPE:
                    return FloatFactory.getFloatEvaluator(oper);
                case Evaluator.__Fields.LONG_TYPE:
                    return LongFactory.getLongEvaluator(oper);
                case Evaluator.__Fields.DATE_TYPE:
                    return DateFactory.getDateEvaluator(oper);
                case Evaluator.__Fields.ARRAY_TYPE:
                    return ArrayFactory.getArrayEvaluator(oper);
                default:
                    throw new RuntimeException("Type '" + type + "' does not exist for BaseEvaluator.__FieldsFactory");
            }
        }
    }
}


    

    