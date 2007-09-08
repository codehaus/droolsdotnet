package org.drools.xml;

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

import java.util.HashSet;

import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.AttributeDescr;
import org.drools.lang.descr.FunctionDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.lang.descr.QueryDescr;
import org.drools.lang.descr.RuleDescr;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author mproctor
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
class RuleHandler extends BaseAbstractHandler
    implements
    Handler {
    RuleHandler(final XmlPackageReader xmlPackageReader) {
        this.xmlPackageReader = xmlPackageReader;

        if ( (this.validParents == null) && (this.validPeers == null) ) {
            this.validParents = new HashSet();
            this.validParents.add( PackageDescr.class );

            this.validPeers = new HashSet();
            this.validPeers.add( null );
            this.validPeers.add( FunctionDescr.class );
            this.validPeers.add( RuleDescr.class );
            this.validPeers.add( QueryDescr.class );

            this.allowNesting = false;
        }
    }

    public Object start(final String uri,
                        final String localName,
                        final Attributes attrs) throws SAXException {
        this.xmlPackageReader.startConfiguration( localName,
                                             attrs );

        final String ruleName = attrs.getValue( "name" );

        if ( ruleName == null || ruleName.trim().equals( "" ) ) {
            throw new SAXParseException( "<rule> requires a 'name' attribute",
                                         this.xmlPackageReader.getLocator() );
        }

        final RuleDescr ruleDescr = new RuleDescr( ruleName.trim() );

        return ruleDescr;
    }

    public Object end(final String uri,
                      final String localName) throws SAXException {
        final Configuration config = this.xmlPackageReader.endConfiguration();

        final RuleDescr ruleDescr = (RuleDescr) this.xmlPackageReader.getCurrent();

        final AndDescr lhs = ruleDescr.getLhs();

        if ( lhs == null ) {
            throw new SAXParseException( "<rule> requires a LHS",
                                         this.xmlPackageReader.getLocator() );
        }

        final Configuration rhs = config.getChild( "rhs" );
        if ( rhs == null ) {
            throw new SAXParseException( "<rule> requires a <rh> child element",
                                         this.xmlPackageReader.getLocator() );
        }

        ruleDescr.setConsequence( rhs.getText() );

        final Configuration[] attributes = config.getChildren( "rule-attribute" );
        for ( int i = 0, length = attributes.length; i < length; i++ ) {
            final String name = attributes[i].getAttribute( "name" );
            if ( name == null || name.trim().equals( "" ) ) {
                throw new SAXParseException( "<rule-attribute> requires a 'name' attribute",
                                             this.xmlPackageReader.getLocator() );
            }

            final String value = attributes[i].getAttribute( "value" );

            ruleDescr.addAttribute( new AttributeDescr( name,
                                                        value ) );
        }

        this.xmlPackageReader.getPackageDescr().addRule( ruleDescr );

        return null;
    }

    public Class generateNodeFor() {
        return RuleDescr.class;
    }
}