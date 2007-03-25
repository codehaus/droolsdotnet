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
using org.drools.dotnet.rule;
using org.drools.common;
using org.drools.reteoo;
using System.IO;
using org.drools.dotnet.util;

namespace org.drools.dotnet
{
    public class RuleBase
    {
        internal org.drools.RuleBase _javaRuleBase;

        public RuleBase(org.drools.RuleBase ruleBase)
        {
            this._javaRuleBase = ruleBase;
        }

        public org.drools.RuleBase GetUnderlyingJavaObject()
        {
            return _javaRuleBase;
        }

        public void AddPackage(Package pkg)
        {
            _javaRuleBase.addPackage(pkg._javaPackage);
        }

        public WorkingMemory NewWorkingMemory()
        {
            return new WorkingMemory(_javaRuleBase.newWorkingMemory());
        }


        public void Save(Stream stream)
        {
            //FileOutputStream _javastream = new FileOutputStream(filePath);
            java.io.ByteArrayOutputStream byteStream = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutput objOut = new DotnetObjectOutputStream(byteStream);
            ((ReteooRuleBase)this._javaRuleBase).writeExternal(objOut);
            stream.Write(byteStream.toByteArray(), 0, byteStream.toByteArray().Length);
            byteStream.close();
        }

        public void Load(Stream stream)
        {
            //FileInputStream stream = new FileInputStream(filePath);
            java.io.InputStream _javaStream = DroolsDotnetUtil.getJavaInputStreamFromSystemStream(stream);
            java.io.ObjectInput objInp = new java.io.ObjectInputStream(_javaStream);
            ((ReteooRuleBase)this._javaRuleBase).readExternal(objInp);
            _javaStream.close();

        }
    }
}
