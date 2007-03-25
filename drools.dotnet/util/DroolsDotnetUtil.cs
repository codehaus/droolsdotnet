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
using java.io;
using System.IO;

namespace org.drools.dotnet.util
{
    public class DroolsDotnetUtil
    {
        public static java.io.StringReader getJavaStringReaderFromSystemStream(System.IO.Stream stream)
        {
            System.IO.StreamReader reader = new System.IO.StreamReader(stream);
            StringBuilder bldr = new StringBuilder();
            String str = null;
            while ((str = reader.ReadLine()) != null)
            {
                bldr.Append(str);
                bldr.Append("\n");
            }
            String ruleFile = bldr.ToString();
            return new java.io.StringReader(ruleFile);
        }

        public static java.io.InputStream getJavaInputStreamFromSystemStream(System.IO.Stream stream)
        {
            byte[] buffer = new byte[stream.Length];
            stream.Read(buffer, 0, buffer.Length);
            java.io.ByteArrayInputStream byteStreams = new java.io.ByteArrayInputStream(buffer);
            return byteStreams;
         }

        
/*        public static void WriteIntoFileStreamFromJavaObjectOutput(java.io.ObjectOutput objOutStream, Stream stream)
        {
            ObjectOutputStream outStream = (java.io.ObjectOutputStream) objOutStream;
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            
            stream.Write(buffer, 0, buffer.Length);
        }

        public static java.io.ObjectInput ReadIntoJavaObjectInputFromFileStream(Stream stream)
        {
            byte[] buffer = new byte[stream.Length];
            stream.Read(buffer, 0, buffer.Length);
            ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer);
            ObjectInput inpStream = new ObjectInputStream(byteStream);

            inpStream.read(buffer);
            return inpStream;
        }
        */
    }
}
