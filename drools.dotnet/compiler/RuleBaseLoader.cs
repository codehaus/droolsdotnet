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
using System.IO;
using org.drools.dotnet.util;

namespace org.drools.dotnet.compiler
{
    class RuleBaseLoader
    {
        private org.drools.compiler.RuleBaseLoader _javaRuleBaseLoader = org.drools.compiler.RuleBaseLoader.getInstance();

        public RuleBaseLoader getInstance()
        {
            return new RuleBaseLoader();
        }

        public RuleBase loadFromStream(Stream drl) {
               return new RuleBase(_javaRuleBaseLoader.loadFromReader(
                                DroolsDotnetUtil.getJavaStringReaderFromSystemStream(drl)));
        }

        public RuleBase loadFromString(string drl)
        {
            return new RuleBase(_javaRuleBaseLoader.loadFromReader(
                             new java.io.StringReader(drl)));
        }

        public RuleBase loadFromString(String drl, string dsl)
        {
            return new RuleBase(_javaRuleBaseLoader.loadFromReader(
                             new java.io.StringReader(drl), new java.io.StringReader(dsl)));
        }
    /**
     * Create a new RuleBase from the drl and dsl source.
     * Uses the current default engine type.
     */
        public RuleBase loadFromStream(Stream drl,
                                            Stream dsl)        
        {
            return new RuleBase(_javaRuleBaseLoader.loadFromReader(
                                    DroolsDotnetUtil.getJavaStringReaderFromSystemStream(drl), DroolsDotnetUtil.getJavaStringReaderFromSystemStream(dsl)));
        }

        public void setDefaultEngineType(int type) {
            _javaRuleBaseLoader.setDefaultEngineType(type);
        }

    }
}
