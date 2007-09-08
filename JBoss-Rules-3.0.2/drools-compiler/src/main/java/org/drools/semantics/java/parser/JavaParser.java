// $ANTLR 3.0ea8 /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g 2006-04-10 01:50:29
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

package org.drools.semantics.java.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedNotSetException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MismatchedTreeNodeException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class JavaParser extends Parser {
    public static final String[] tokenNames        = new String[]{"<invalid>", "<EOR>", "<DOWN>", "<UP>", "LBRACK", "RBRACK", "IDENT", "DOT", "STAR", "LCURLY", "SEMI", "RCURLY", "COMMA", "LPAREN", "RPAREN", "ASSIGN", "COLON", "PLUS_ASSIGN",
            "MINUS_ASSIGN", "STAR_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", "SR_ASSIGN", "BSR_ASSIGN", "SL_ASSIGN", "BAND_ASSIGN", "BXOR_ASSIGN", "BOR_ASSIGN", "QUESTION", "LOR", "LAND", "BOR", "BXOR", "BAND", "NOT_EQUAL", "EQUAL", "LT", "GT", "LE",
            "GE", "SL", "SR", "BSR", "PLUS", "MINUS", "DIV", "MOD", "INC", "DEC", "BNOT", "LNOT", "NUM_INT", "CHAR_LITERAL", "STRING_LITERAL", "NUM_FLOAT", "WS", "SL_COMMENT", "ML_COMMENT", "DECIMAL_LITERAL", "HEX_LITERAL", "OCTAL_LITERAL",
            "DIGITS", "EXPONENT_PART", "FLOAT_TYPE_SUFFIX", "ESCAPE_SEQUENCE", "OCTAL_DIGIT", "UNICODE_CHAR", "HEX_DIGIT", "\'void\'", "\'boolean\'", "\'byte\'", "\'char\'", "\'short\'", "\'int\'", "\'float\'", "\'long\'", "\'double\'",
            "\'private\'", "\'public\'", "\'protected\'", "\'static\'", "\'transient\'", "\'final\'", "\'abstract\'", "\'native\'", "\'threadsafe\'", "\'synchronized\'", "\'volatile\'", "\'strictfp\'", "\'class\'", "\'extends\'", "\'interface\'",
            "\'implements\'", "\'this\'", "\'super\'", "\'throws\'", "\'if\'", "\'else\'", "\'for\'", "\'while\'", "\'do\'", "\'break\'", "\'continue\'", "\'return\'", "\'switch\'", "\'throw\'", "\'case\'", "\'default\'", "\'try\'", "\'finally\'",
            "\'catch\'", "\'instanceof\'", "\'true\'", "\'false\'", "\'null\'", "\'new\'"};
    public static final int      COMMA             = 12;
    public static final int      SR_ASSIGN         = 22;
    public static final int      MINUS             = 44;
    public static final int      LOR               = 29;
    public static final int      BNOT              = 49;
    public static final int      INC               = 47;
    public static final int      MOD               = 46;
    public static final int      OCTAL_LITERAL     = 60;
    public static final int      PLUS_ASSIGN       = 17;
    public static final int      QUESTION          = 28;
    public static final int      HEX_LITERAL       = 59;
    public static final int      BOR               = 31;
    public static final int      DOT               = 7;
    public static final int      SR                = 41;
    public static final int      FLOAT_TYPE_SUFFIX = 63;
    public static final int      RCURLY            = 11;
    public static final int      LCURLY            = 9;
    public static final int      CHAR_LITERAL      = 52;
    public static final int      BOR_ASSIGN        = 27;
    public static final int      STRING_LITERAL    = 53;
    public static final int      ASSIGN            = 15;
    public static final int      LE                = 38;
    public static final int      RPAREN            = 14;
    public static final int      STAR_ASSIGN       = 19;
    public static final int      NUM_INT           = 51;
    public static final int      LPAREN            = 13;
    public static final int      HEX_DIGIT         = 67;
    public static final int      ML_COMMENT        = 57;
    public static final int      PLUS              = 43;
    public static final int      SL_COMMENT        = 56;
    public static final int      BAND              = 33;
    public static final int      MINUS_ASSIGN      = 18;
    public static final int      NOT_EQUAL         = 34;
    public static final int      BAND_ASSIGN       = 25;
    public static final int      DIGITS            = 61;
    public static final int      DECIMAL_LITERAL   = 58;
    public static final int      IDENT             = 6;
    public static final int      MOD_ASSIGN        = 21;
    public static final int      WS                = 55;
    public static final int      BXOR_ASSIGN       = 26;
    public static final int      LT                = 36;
    public static final int      BSR               = 42;
    public static final int      GT                = 37;
    public static final int      BSR_ASSIGN        = 23;
    public static final int      SL_ASSIGN         = 24;
    public static final int      LAND              = 30;
    public static final int      LBRACK            = 4;
    public static final int      NUM_FLOAT         = 54;
    public static final int      SEMI              = 10;
    public static final int      GE                = 39;
    public static final int      LNOT              = 50;
    public static final int      DIV_ASSIGN        = 20;
    public static final int      UNICODE_CHAR      = 66;
    public static final int      DEC               = 48;
    public static final int      EQUAL             = 35;
    public static final int      ESCAPE_SEQUENCE   = 64;
    public static final int      EOF               = -1;
    public static final int      OCTAL_DIGIT       = 65;
    public static final int      RBRACK            = 5;
    public static final int      COLON             = 16;
    public static final int      SL                = 40;
    public static final int      DIV               = 45;
    public static final int      STAR              = 8;
    public static final int      BXOR              = 32;
    public static final int      EXPONENT_PART     = 62;

    public JavaParser(final TokenStream input) {
        super( input );
    }

    public String[] getTokenNames() {
        return JavaParser.tokenNames;
    }

    private final List identifiers = new ArrayList();

    public List getIdentifiers() {
        return this.identifiers;
    }

    public static final CommonToken IGNORE_TOKEN = new CommonToken( null,
                                                                    0,
                                                                    99,
                                                                    0,
                                                                    0 );
    private final List                    errors       = new ArrayList();

    private String                  source       = "unknown";

    public void setSource(final String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    public void reportError(final RecognitionException ex) {
        // if we've already reported an error and have not matched a token
        // yet successfully, don't report any errors.
        if ( this.errorRecovery ) {
            //System.err.print("[SPURIOUS] ");
            return;
        }
        this.errorRecovery = true;

        this.errors.add( ex );
    }

    /** return the raw RecognitionException errors */
    public List getErrors() {
        return this.errors;
    }

    /** Return a list of pretty strings summarising the errors */
    public List getErrorMessages() {
        final List messages = new ArrayList();
        for ( final Iterator errorIter = this.errors.iterator(); errorIter.hasNext(); ) {
            messages.add( createErrorMessage( (RecognitionException) errorIter.next() ) );
        }
        return messages;
    }

    /** return true if any parser errors were accumulated */
    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    /** This will take a RecognitionException, and create a sensible error message out of it */
    public String createErrorMessage(final RecognitionException e) {
        final StringBuffer message = new StringBuffer();
        message.append( this.source + ":" + e.line + ":" + e.charPositionInLine + " " );
        if ( e instanceof MismatchedTokenException ) {
            final MismatchedTokenException mte = (MismatchedTokenException) e;
            message.append( "mismatched token: " + e.token + "; expecting type " + JavaParser.tokenNames[mte.expecting] );
        } else if ( e instanceof MismatchedTreeNodeException ) {
            final MismatchedTreeNodeException mtne = (MismatchedTreeNodeException) e;
            message.append( "mismatched tree node: " + mtne.foundNode + "; expecting type " + JavaParser.tokenNames[mtne.expecting] );
        } else if ( e instanceof NoViableAltException ) {
            final NoViableAltException nvae = (NoViableAltException) e;
            message.append( "Unexpected token '" + e.token.getText() + "'" );
            /*
             message.append("decision=<<"+nvae.grammarDecisionDescription+">>"+
             " state "+nvae.stateNumber+
             " (decision="+nvae.decisionNumber+
             ") no viable alt; token="+
             e.token);
             */
        } else if ( e instanceof EarlyExitException ) {
            final EarlyExitException eee = (EarlyExitException) e;
            message.append( "required (...)+ loop (decision=" + eee.decisionNumber + ") did not match anything; token=" + e.token );
        } else if ( e instanceof MismatchedSetException ) {
            final MismatchedSetException mse = (MismatchedSetException) e;
            message.append( "mismatched token '" + e.token + "' expecting set " + mse.expecting );
        } else if ( e instanceof MismatchedNotSetException ) {
            final MismatchedNotSetException mse = (MismatchedNotSetException) e;
            message.append( "mismatched token '" + e.token + "' expecting set " + mse.expecting );
        } else if ( e instanceof FailedPredicateException ) {
            final FailedPredicateException fpe = (FailedPredicateException) e;
            message.append( "rule " + fpe.ruleName + " failed predicate: {" + fpe.predicateText + "}?" );
        }
        return message.toString();
    }

    // $ANTLR start declaration
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:139:1: declaration : modifiers typeSpec variableDefinitions ;
    public void declaration() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:143:17: ( modifiers typeSpec variableDefinitions )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:143:17: modifiers typeSpec variableDefinitions
            {
                this.following.push( JavaParser.FOLLOW_modifiers_in_declaration59 );
                modifiers();
                this.following.pop();

                this.following.push( JavaParser.FOLLOW_typeSpec_in_declaration61 );
                typeSpec();
                this.following.pop();

                this.following.push( JavaParser.FOLLOW_variableDefinitions_in_declaration63 );
                variableDefinitions();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end declaration

    // $ANTLR start typeSpec
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:149:1: typeSpec : ( classTypeSpec | builtInTypeSpec );
    public void typeSpec() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:150:11: ( classTypeSpec | builtInTypeSpec )
            int alt1 = 2;
            final int LA1_0 = this.input.LA( 1 );
            if ( LA1_0 == JavaParser.IDENT ) {
                alt1 = 1;
            } else if ( (LA1_0 >= 68 && LA1_0 <= 76) ) {
                alt1 = 2;
            } else {
                final NoViableAltException nvae = new NoViableAltException( "149:1: typeSpec : ( classTypeSpec | builtInTypeSpec );",
                                                                      1,
                                                                      0,
                                                                      this.input );

                throw nvae;
            }
            switch ( alt1 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:150:11: classTypeSpec
                {
                    this.following.push( JavaParser.FOLLOW_classTypeSpec_in_typeSpec79 );
                    classTypeSpec();
                    this.following.pop();

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:151:11: builtInTypeSpec
                {
                    this.following.push( JavaParser.FOLLOW_builtInTypeSpec_in_typeSpec84 );
                    builtInTypeSpec();
                    this.following.pop();

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end typeSpec

    // $ANTLR start classTypeSpec
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:156:1: classTypeSpec : identifier ( LBRACK RBRACK )* ;
    public void classTypeSpec() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:157:17: ( identifier ( LBRACK RBRACK )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:157:17: identifier ( LBRACK RBRACK )*
            {
                this.following.push( JavaParser.FOLLOW_identifier_in_classTypeSpec97 );
                identifier();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:157:28: ( LBRACK RBRACK )*
                loop2 : do {
                    int alt2 = 2;
                    final int LA2_0 = this.input.LA( 1 );
                    if ( LA2_0 == JavaParser.LBRACK ) {
                        alt2 = 1;
                    }

                    switch ( alt2 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:157:29: LBRACK RBRACK
                        {
                            match( this.input,
                                   JavaParser.LBRACK,
                                   JavaParser.FOLLOW_LBRACK_in_classTypeSpec100 );
                            match( this.input,
                                   JavaParser.RBRACK,
                                   JavaParser.FOLLOW_RBRACK_in_classTypeSpec103 );

                        }
                            break;

                        default :
                            break loop2;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end classTypeSpec

    // $ANTLR start builtInTypeSpec
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:162:1: builtInTypeSpec : builtInType ( LBRACK RBRACK )* ;
    public void builtInTypeSpec() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:163:17: ( builtInType ( LBRACK RBRACK )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:163:17: builtInType ( LBRACK RBRACK )*
            {
                this.following.push( JavaParser.FOLLOW_builtInType_in_builtInTypeSpec118 );
                builtInType();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:163:29: ( LBRACK RBRACK )*
                loop3 : do {
                    int alt3 = 2;
                    final int LA3_0 = this.input.LA( 1 );
                    if ( LA3_0 == JavaParser.LBRACK ) {
                        alt3 = 1;
                    }

                    switch ( alt3 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:163:30: LBRACK RBRACK
                        {
                            match( this.input,
                                   JavaParser.LBRACK,
                                   JavaParser.FOLLOW_LBRACK_in_builtInTypeSpec121 );
                            match( this.input,
                                   JavaParser.RBRACK,
                                   JavaParser.FOLLOW_RBRACK_in_builtInTypeSpec124 );

                        }
                            break;

                        default :
                            break loop3;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end builtInTypeSpec

    // $ANTLR start type
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:168:1: type : ( identifier | builtInType );
    public void type() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:169:17: ( identifier | builtInType )
            int alt4 = 2;
            final int LA4_0 = this.input.LA( 1 );
            if ( LA4_0 == JavaParser.IDENT ) {
                alt4 = 1;
            } else if ( (LA4_0 >= 68 && LA4_0 <= 76) ) {
                alt4 = 2;
            } else {
                final NoViableAltException nvae = new NoViableAltException( "168:1: type : ( identifier | builtInType );",
                                                                      4,
                                                                      0,
                                                                      this.input );

                throw nvae;
            }
            switch ( alt4 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:169:17: identifier
                {
                    this.following.push( JavaParser.FOLLOW_identifier_in_type139 );
                    identifier();
                    this.following.pop();

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:170:17: builtInType
                {
                    this.following.push( JavaParser.FOLLOW_builtInType_in_type144 );
                    builtInType();
                    this.following.pop();

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end type

    // $ANTLR start builtInType
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:174:1: builtInType : ('void'|'boolean'|'byte'|'char'|'short'|'int'|'float'|'long'|'double');
    public void builtInType() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:175:9: ( ('void'|'boolean'|'byte'|'char'|'short'|'int'|'float'|'long'|'double'))
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:175:17: ('void'|'boolean'|'byte'|'char'|'short'|'int'|'float'|'long'|'double')
            {
                if ( (this.input.LA( 1 ) >= 68 && this.input.LA( 1 ) <= 76) ) {
                    this.input.consume();
                    this.errorRecovery = false;
                } else {
                    final MismatchedSetException mse = new MismatchedSetException( null,
                                                                             this.input );
                    recoverFromMismatchedSet( this.input,
                                              mse,
                                              JavaParser.FOLLOW_set_in_builtInType156 );
                    throw mse;
                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end builtInType

    // $ANTLR start identifier
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:188:1: identifier : IDENT ( DOT IDENT )* ;
    public void identifier() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:189:17: ( IDENT ( DOT IDENT )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:189:17: IDENT ( DOT IDENT )*
            {
                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_identifier209 );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:189:24: ( DOT IDENT )*
                loop5 : do {
                    int alt5 = 2;
                    final int LA5_0 = this.input.LA( 1 );
                    if ( LA5_0 == JavaParser.DOT ) {
                        alt5 = 1;
                    }

                    switch ( alt5 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:189:26: DOT IDENT
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_identifier214 );
                            match( this.input,
                                   JavaParser.IDENT,
                                   JavaParser.FOLLOW_IDENT_in_identifier216 );

                        }
                            break;

                        default :
                            break loop5;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end identifier

    // $ANTLR start identifierStar
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:192:1: identifierStar : IDENT ( DOT IDENT )* ( DOT STAR )? ;
    public void identifierStar() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:193:17: ( IDENT ( DOT IDENT )* ( DOT STAR )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:193:17: IDENT ( DOT IDENT )* ( DOT STAR )?
            {
                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_identifierStar230 );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:194:17: ( DOT IDENT )*
                loop6 : do {
                    int alt6 = 2;
                    final int LA6_0 = this.input.LA( 1 );
                    if ( LA6_0 == JavaParser.DOT ) {
                        final int LA6_1 = this.input.LA( 2 );
                        if ( LA6_1 == JavaParser.IDENT ) {
                            alt6 = 1;
                        }

                    }

                    switch ( alt6 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:194:19: DOT IDENT
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_identifierStar236 );
                            match( this.input,
                                   JavaParser.IDENT,
                                   JavaParser.FOLLOW_IDENT_in_identifierStar238 );

                        }
                            break;

                        default :
                            break loop6;
                    }
                } while ( true );

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:195:17: ( DOT STAR )?
                int alt7 = 2;
                final int LA7_0 = this.input.LA( 1 );
                if ( LA7_0 == JavaParser.DOT ) {
                    alt7 = 1;
                } else if ( LA7_0 == -1 ) {
                    alt7 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "195:17: ( DOT STAR )?",
                                                                          7,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt7 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:195:19: DOT STAR
                    {
                        match( this.input,
                               JavaParser.DOT,
                               JavaParser.FOLLOW_DOT_in_identifierStar247 );
                        match( this.input,
                               JavaParser.STAR,
                               JavaParser.FOLLOW_STAR_in_identifierStar249 );

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end identifierStar

    // $ANTLR start modifiers
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:202:1: modifiers : ( modifier )* ;
    public void modifiers() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:203:17: ( ( modifier )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:203:17: ( modifier )*
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:203:17: ( modifier )*
                loop8 : do {
                    int alt8 = 2;
                    final int LA8_0 = this.input.LA( 1 );
                    if ( (LA8_0 >= 77 && LA8_0 <= 88) ) {
                        alt8 = 1;
                    }

                    switch ( alt8 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:203:19: modifier
                        {
                            this.following.push( JavaParser.FOLLOW_modifier_in_modifiers270 );
                            modifier();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop8;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end modifiers

    // $ANTLR start modifier
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:208:1: modifier : ('private'|'public'|'protected'|'static'|'transient'|'final'|'abstract'|'native'|'threadsafe'|'synchronized'|'volatile'|'strictfp');
    public void modifier() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:209:9: ( ('private'|'public'|'protected'|'static'|'transient'|'final'|'abstract'|'native'|'threadsafe'|'synchronized'|'volatile'|'strictfp'))
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:209:17: ('private'|'public'|'protected'|'static'|'transient'|'final'|'abstract'|'native'|'threadsafe'|'synchronized'|'volatile'|'strictfp')
            {
                if ( (this.input.LA( 1 ) >= 77 && this.input.LA( 1 ) <= 88) ) {
                    this.input.consume();
                    this.errorRecovery = false;
                } else {
                    final MismatchedSetException mse = new MismatchedSetException( null,
                                                                             this.input );
                    recoverFromMismatchedSet( this.input,
                                              mse,
                                              JavaParser.FOLLOW_set_in_modifier288 );
                    throw mse;
                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end modifier

    // $ANTLR start classDefinition
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:225:1: classDefinition : 'class' IDENT superClassClause implementsClause classBlock ;
    public void classDefinition() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:226:17: ( 'class' IDENT superClassClause implementsClause classBlock )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:226:17: 'class' IDENT superClassClause implementsClause classBlock
            {
                match( this.input,
                       89,
                       JavaParser.FOLLOW_89_in_classDefinition356 );
                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_classDefinition358 );
                this.following.push( JavaParser.FOLLOW_superClassClause_in_classDefinition365 );
                superClassClause();
                this.following.pop();

                this.following.push( JavaParser.FOLLOW_implementsClause_in_classDefinition372 );
                implementsClause();
                this.following.pop();

                this.following.push( JavaParser.FOLLOW_classBlock_in_classDefinition379 );
                classBlock();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end classDefinition

    // $ANTLR start superClassClause
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:235:1: superClassClause : ( 'extends' identifier )? ;
    public void superClassClause() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:236:17: ( ( 'extends' identifier )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:236:17: ( 'extends' identifier )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:236:17: ( 'extends' identifier )?
                int alt9 = 2;
                final int LA9_0 = this.input.LA( 1 );
                if ( LA9_0 == 90 ) {
                    alt9 = 1;
                } else if ( LA9_0 == JavaParser.LCURLY || LA9_0 == 92 ) {
                    alt9 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "236:17: ( \'extends\' identifier )?",
                                                                          9,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt9 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:236:19: 'extends' identifier
                    {
                        match( this.input,
                               90,
                               JavaParser.FOLLOW_90_in_superClassClause392 );
                        this.following.push( JavaParser.FOLLOW_identifier_in_superClassClause394 );
                        identifier();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end superClassClause

    // $ANTLR start interfaceDefinition
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:241:1: interfaceDefinition : 'interface' IDENT interfaceExtends classBlock ;
    public void interfaceDefinition() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:242:17: ( 'interface' IDENT interfaceExtends classBlock )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:242:17: 'interface' IDENT interfaceExtends classBlock
            {
                match( this.input,
                       91,
                       JavaParser.FOLLOW_91_in_interfaceDefinition412 );
                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_interfaceDefinition414 );
                this.following.push( JavaParser.FOLLOW_interfaceExtends_in_interfaceDefinition421 );
                interfaceExtends();
                this.following.pop();

                this.following.push( JavaParser.FOLLOW_classBlock_in_interfaceDefinition428 );
                classBlock();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end interfaceDefinition

    // $ANTLR start classBlock
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:252:1: classBlock : LCURLY ( field | SEMI )* RCURLY ;
    public void classBlock() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:253:17: ( LCURLY ( field | SEMI )* RCURLY )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:253:17: LCURLY ( field | SEMI )* RCURLY
            {
                match( this.input,
                       JavaParser.LCURLY,
                       JavaParser.FOLLOW_LCURLY_in_classBlock442 );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:254:25: ( field | SEMI )*
                loop10 : do {
                    int alt10 = 3;
                    final int LA10_0 = this.input.LA( 1 );
                    if ( LA10_0 == JavaParser.IDENT || LA10_0 == JavaParser.LCURLY || (LA10_0 >= 68 && LA10_0 <= 89) || LA10_0 == 91 ) {
                        alt10 = 1;
                    } else if ( LA10_0 == JavaParser.SEMI ) {
                        alt10 = 2;
                    }

                    switch ( alt10 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:254:27: field
                        {
                            this.following.push( JavaParser.FOLLOW_field_in_classBlock449 );
                            field();
                            this.following.pop();

                        }
                            break;
                        case 2 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:254:35: SEMI
                        {
                            match( this.input,
                                   JavaParser.SEMI,
                                   JavaParser.FOLLOW_SEMI_in_classBlock453 );

                        }
                            break;

                        default :
                            break loop10;
                    }
                } while ( true );

                match( this.input,
                       JavaParser.RCURLY,
                       JavaParser.FOLLOW_RCURLY_in_classBlock460 );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end classBlock

    // $ANTLR start interfaceExtends
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:260:1: interfaceExtends : ( 'extends' identifier ( COMMA identifier )* )? ;
    public void interfaceExtends() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:261:17: ( ( 'extends' identifier ( COMMA identifier )* )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:261:17: ( 'extends' identifier ( COMMA identifier )* )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:261:17: ( 'extends' identifier ( COMMA identifier )* )?
                int alt12 = 2;
                final int LA12_0 = this.input.LA( 1 );
                if ( LA12_0 == 90 ) {
                    alt12 = 1;
                } else if ( LA12_0 == JavaParser.LCURLY ) {
                    alt12 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "261:17: ( \'extends\' identifier ( COMMA identifier )* )?",
                                                                          12,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt12 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:262:17: 'extends' identifier ( COMMA identifier )*
                    {
                        match( this.input,
                               90,
                               JavaParser.FOLLOW_90_in_interfaceExtends479 );
                        this.following.push( JavaParser.FOLLOW_identifier_in_interfaceExtends483 );
                        identifier();
                        this.following.pop();

                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:263:28: ( COMMA identifier )*
                        loop11 : do {
                            int alt11 = 2;
                            final int LA11_0 = this.input.LA( 1 );
                            if ( LA11_0 == JavaParser.COMMA ) {
                                alt11 = 1;
                            }

                            switch ( alt11 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:263:30: COMMA identifier
                                {
                                    match( this.input,
                                           JavaParser.COMMA,
                                           JavaParser.FOLLOW_COMMA_in_interfaceExtends487 );
                                    this.following.push( JavaParser.FOLLOW_identifier_in_interfaceExtends489 );
                                    identifier();
                                    this.following.pop();

                                }
                                    break;

                                default :
                                    break loop11;
                            }
                        } while ( true );

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end interfaceExtends

    // $ANTLR start implementsClause
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:268:1: implementsClause : ( 'implements' identifier ( COMMA identifier )* )? ;
    public void implementsClause() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:269:17: ( ( 'implements' identifier ( COMMA identifier )* )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:269:17: ( 'implements' identifier ( COMMA identifier )* )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:269:17: ( 'implements' identifier ( COMMA identifier )* )?
                int alt14 = 2;
                final int LA14_0 = this.input.LA( 1 );
                if ( LA14_0 == 92 ) {
                    alt14 = 1;
                } else if ( LA14_0 == JavaParser.LCURLY ) {
                    alt14 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "269:17: ( \'implements\' identifier ( COMMA identifier )* )?",
                                                                          14,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt14 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:270:25: 'implements' identifier ( COMMA identifier )*
                    {
                        match( this.input,
                               92,
                               JavaParser.FOLLOW_92_in_implementsClause514 );
                        this.following.push( JavaParser.FOLLOW_identifier_in_implementsClause516 );
                        identifier();
                        this.following.pop();

                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:270:49: ( COMMA identifier )*
                        loop13 : do {
                            int alt13 = 2;
                            final int LA13_0 = this.input.LA( 1 );
                            if ( LA13_0 == JavaParser.COMMA ) {
                                alt13 = 1;
                            }

                            switch ( alt13 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:270:51: COMMA identifier
                                {
                                    match( this.input,
                                           JavaParser.COMMA,
                                           JavaParser.FOLLOW_COMMA_in_implementsClause520 );
                                    this.following.push( JavaParser.FOLLOW_identifier_in_implementsClause522 );
                                    identifier();
                                    this.following.pop();

                                }
                                    break;

                                default :
                                    break loop13;
                            }
                        } while ( true );

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end implementsClause

    // $ANTLR start field
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:278:1: field : ( modifiers ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) ) | 'static' compoundStatement | compoundStatement );
    public void field() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:280:17: ( modifiers ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) ) | 'static' compoundStatement | compoundStatement )
            int alt19 = 3;
            switch ( this.input.LA( 1 ) ) {
                case 80 :
                    final int LA19_1 = this.input.LA( 2 );
                    if ( LA19_1 == JavaParser.IDENT || (LA19_1 >= 68 && LA19_1 <= 89) || LA19_1 == 91 ) {
                        alt19 = 1;
                    } else if ( LA19_1 == JavaParser.LCURLY ) {
                        alt19 = 2;
                    } else {
                        final NoViableAltException nvae = new NoViableAltException( "278:1: field : ( modifiers ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) ) | \'static\' compoundStatement | compoundStatement );",
                                                                              19,
                                                                              1,
                                                                              this.input );

                        throw nvae;
                    }
                    break;
                case IDENT :
                case 68 :
                case 69 :
                case 70 :
                case 71 :
                case 72 :
                case 73 :
                case 74 :
                case 75 :
                case 76 :
                case 77 :
                case 78 :
                case 79 :
                case 81 :
                case 82 :
                case 83 :
                case 84 :
                case 85 :
                case 86 :
                case 87 :
                case 88 :
                case 89 :
                case 91 :
                    alt19 = 1;
                    break;
                case LCURLY :
                    alt19 = 3;
                    break;
                default :
                    final NoViableAltException nvae = new NoViableAltException( "278:1: field : ( modifiers ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) ) | \'static\' compoundStatement | compoundStatement );",
                                                                          19,
                                                                          0,
                                                                          this.input );

                    throw nvae;
            }

            switch ( alt19 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:280:17: modifiers ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) )
                {
                    this.following.push( JavaParser.FOLLOW_modifiers_in_field548 );
                    modifiers();
                    this.following.pop();

                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:281:17: ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) )
                    int alt18 = 4;
                    switch ( this.input.LA( 1 ) ) {
                        case IDENT :
                            final int LA18_1 = this.input.LA( 2 );
                            if ( LA18_1 == JavaParser.LPAREN ) {
                                alt18 = 1;
                            } else if ( LA18_1 == JavaParser.LBRACK || (LA18_1 >= JavaParser.IDENT && LA18_1 <= JavaParser.DOT) ) {
                                alt18 = 4;
                            } else {
                                final NoViableAltException nvae = new NoViableAltException( "281:17: ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) )",
                                                                                      18,
                                                                                      1,
                                                                                      this.input );

                                throw nvae;
                            }
                            break;
                        case 89 :
                            alt18 = 2;
                            break;
                        case 91 :
                            alt18 = 3;
                            break;
                        case 68 :
                        case 69 :
                        case 70 :
                        case 71 :
                        case 72 :
                        case 73 :
                        case 74 :
                        case 75 :
                        case 76 :
                            alt18 = 4;
                            break;
                        default :
                            final NoViableAltException nvae = new NoViableAltException( "281:17: ( ctorHead constructorBody | classDefinition | interfaceDefinition | typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI ) )",
                                                                                  18,
                                                                                  0,
                                                                                  this.input );

                            throw nvae;
                    }

                    switch ( alt18 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:281:25: ctorHead constructorBody
                        {
                            this.following.push( JavaParser.FOLLOW_ctorHead_in_field554 );
                            ctorHead();
                            this.following.pop();

                            this.following.push( JavaParser.FOLLOW_constructorBody_in_field556 );
                            constructorBody();
                            this.following.pop();

                        }
                            break;
                        case 2 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:284:25: classDefinition
                        {
                            this.following.push( JavaParser.FOLLOW_classDefinition_in_field568 );
                            classDefinition();
                            this.following.pop();

                        }
                            break;
                        case 3 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:287:25: interfaceDefinition
                        {
                            this.following.push( JavaParser.FOLLOW_interfaceDefinition_in_field586 );
                            interfaceDefinition();
                            this.following.pop();

                        }
                            break;
                        case 4 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:290:25: typeSpec ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI )
                        {
                            this.following.push( JavaParser.FOLLOW_typeSpec_in_field600 );
                            typeSpec();
                            this.following.pop();

                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:291:25: ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI )
                            int alt17 = 2;
                            final int LA17_0 = this.input.LA( 1 );
                            if ( LA17_0 == JavaParser.IDENT ) {
                                final int LA17_1 = this.input.LA( 2 );
                                if ( LA17_1 == JavaParser.LPAREN ) {
                                    alt17 = 1;
                                } else if ( LA17_1 == JavaParser.LBRACK || LA17_1 == JavaParser.SEMI || LA17_1 == JavaParser.COMMA || LA17_1 == JavaParser.ASSIGN ) {
                                    alt17 = 2;
                                } else {
                                    final NoViableAltException nvae = new NoViableAltException( "291:25: ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI )",
                                                                                          17,
                                                                                          1,
                                                                                          this.input );

                                    throw nvae;
                                }
                            } else {
                                final NoViableAltException nvae = new NoViableAltException( "291:25: ( IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI ) | variableDefinitions SEMI )",
                                                                                      17,
                                                                                      0,
                                                                                      this.input );

                                throw nvae;
                            }
                            switch ( alt17 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:291:33: IDENT LPAREN parameterDeclarationList RPAREN declaratorBrackets ( throwsClause )? ( compoundStatement | SEMI )
                                {
                                    match( this.input,
                                           JavaParser.IDENT,
                                           JavaParser.FOLLOW_IDENT_in_field609 );
                                    match( this.input,
                                           JavaParser.LPAREN,
                                           JavaParser.FOLLOW_LPAREN_in_field623 );
                                    this.following.push( JavaParser.FOLLOW_parameterDeclarationList_in_field625 );
                                    parameterDeclarationList();
                                    this.following.pop();

                                    match( this.input,
                                           JavaParser.RPAREN,
                                           JavaParser.FOLLOW_RPAREN_in_field627 );
                                    this.following.push( JavaParser.FOLLOW_declaratorBrackets_in_field634 );
                                    declaratorBrackets();
                                    this.following.pop();

                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:300:33: ( throwsClause )?
                                    int alt15 = 2;
                                    final int LA15_0 = this.input.LA( 1 );
                                    if ( LA15_0 == 95 ) {
                                        alt15 = 1;
                                    } else if ( (LA15_0 >= JavaParser.LCURLY && LA15_0 <= JavaParser.SEMI) ) {
                                        alt15 = 2;
                                    } else {
                                        final NoViableAltException nvae = new NoViableAltException( "300:33: ( throwsClause )?",
                                                                                              15,
                                                                                              0,
                                                                                              this.input );

                                        throw nvae;
                                    }
                                    switch ( alt15 ) {
                                        case 1 :
                                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:300:34: throwsClause
                                        {
                                            this.following.push( JavaParser.FOLLOW_throwsClause_in_field652 );
                                            throwsClause();
                                            this.following.pop();

                                        }
                                            break;

                                    }

                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:302:33: ( compoundStatement | SEMI )
                                    int alt16 = 2;
                                    final int LA16_0 = this.input.LA( 1 );
                                    if ( LA16_0 == JavaParser.LCURLY ) {
                                        alt16 = 1;
                                    } else if ( LA16_0 == JavaParser.SEMI ) {
                                        alt16 = 2;
                                    } else {
                                        final NoViableAltException nvae = new NoViableAltException( "302:33: ( compoundStatement | SEMI )",
                                                                                              16,
                                                                                              0,
                                                                                              this.input );

                                        throw nvae;
                                    }
                                    switch ( alt16 ) {
                                        case 1 :
                                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:302:35: compoundStatement
                                        {
                                            this.following.push( JavaParser.FOLLOW_compoundStatement_in_field663 );
                                            compoundStatement();
                                            this.following.pop();

                                        }
                                            break;
                                        case 2 :
                                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:302:55: SEMI
                                        {
                                            match( this.input,
                                                   JavaParser.SEMI,
                                                   JavaParser.FOLLOW_SEMI_in_field667 );

                                        }
                                            break;

                                    }

                                }
                                    break;
                                case 2 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:303:33: variableDefinitions SEMI
                                {
                                    this.following.push( JavaParser.FOLLOW_variableDefinitions_in_field676 );
                                    variableDefinitions();
                                    this.following.pop();

                                    match( this.input,
                                           JavaParser.SEMI,
                                           JavaParser.FOLLOW_SEMI_in_field678 );

                                }
                                    break;

                            }

                        }
                            break;

                    }

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:310:17: 'static' compoundStatement
                {
                    match( this.input,
                           80,
                           JavaParser.FOLLOW_80_in_field704 );
                    this.following.push( JavaParser.FOLLOW_compoundStatement_in_field706 );
                    compoundStatement();
                    this.following.pop();

                }
                    break;
                case 3 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:314:17: compoundStatement
                {
                    this.following.push( JavaParser.FOLLOW_compoundStatement_in_field720 );
                    compoundStatement();
                    this.following.pop();

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end field

    // $ANTLR start constructorBody
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:318:1: constructorBody : LCURLY ( options {greedy=true; } : explicitConstructorInvocation )? ( statement )* RCURLY ;
    public void constructorBody() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:319:9: ( LCURLY ( options {greedy=true; } : explicitConstructorInvocation )? ( statement )* RCURLY )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:319:9: LCURLY ( options {greedy=true; } : explicitConstructorInvocation )? ( statement )* RCURLY
            {
                match( this.input,
                       JavaParser.LCURLY,
                       JavaParser.FOLLOW_LCURLY_in_constructorBody739 );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:320:13: ( options {greedy=true; } : explicitConstructorInvocation )?
                int alt20 = 2;
                switch ( this.input.LA( 1 ) ) {
                    case 93 :
                        final int LA20_1 = this.input.LA( 2 );
                        if ( LA20_1 == JavaParser.LPAREN ) {
                            alt20 = 1;
                        } else if ( LA20_1 == JavaParser.LBRACK || (LA20_1 >= JavaParser.DOT && LA20_1 <= JavaParser.STAR) || LA20_1 == JavaParser.SEMI || LA20_1 == JavaParser.ASSIGN || (LA20_1 >= JavaParser.PLUS_ASSIGN && LA20_1 <= JavaParser.DEC) || LA20_1 == 111 ) {
                            alt20 = 2;
                        } else {
                            final NoViableAltException nvae = new NoViableAltException( "320:13: ( options {greedy=true; } : explicitConstructorInvocation )?",
                                                                                  20,
                                                                                  1,
                                                                                  this.input );

                            throw nvae;
                        }
                        break;
                    case 94 :
                        final int LA20_2 = this.input.LA( 2 );
                        if ( LA20_2 == JavaParser.LPAREN ) {
                            alt20 = 1;
                        } else if ( LA20_2 == JavaParser.LBRACK || (LA20_2 >= JavaParser.DOT && LA20_2 <= JavaParser.STAR) || LA20_2 == JavaParser.SEMI || LA20_2 == JavaParser.ASSIGN || (LA20_2 >= JavaParser.PLUS_ASSIGN && LA20_2 <= JavaParser.DEC) || LA20_2 == 111 ) {
                            alt20 = 2;
                        } else {
                            final NoViableAltException nvae = new NoViableAltException( "320:13: ( options {greedy=true; } : explicitConstructorInvocation )?",
                                                                                  20,
                                                                                  2,
                                                                                  this.input );

                            throw nvae;
                        }
                        break;
                    case IDENT :
                    case LCURLY :
                    case SEMI :
                    case RCURLY :
                    case LPAREN :
                    case PLUS :
                    case MINUS :
                    case INC :
                    case DEC :
                    case BNOT :
                    case LNOT :
                    case NUM_INT :
                    case CHAR_LITERAL :
                    case STRING_LITERAL :
                    case NUM_FLOAT :
                    case 68 :
                    case 69 :
                    case 70 :
                    case 71 :
                    case 72 :
                    case 73 :
                    case 74 :
                    case 75 :
                    case 76 :
                    case 77 :
                    case 78 :
                    case 79 :
                    case 80 :
                    case 81 :
                    case 82 :
                    case 83 :
                    case 84 :
                    case 85 :
                    case 86 :
                    case 87 :
                    case 88 :
                    case 89 :
                    case 96 :
                    case 98 :
                    case 99 :
                    case 100 :
                    case 101 :
                    case 102 :
                    case 103 :
                    case 104 :
                    case 105 :
                    case 108 :
                    case 112 :
                    case 113 :
                    case 114 :
                    case 115 :
                        alt20 = 2;
                        break;
                    default :
                        final NoViableAltException nvae = new NoViableAltException( "320:13: ( options {greedy=true; } : explicitConstructorInvocation )?",
                                                                              20,
                                                                              0,
                                                                              this.input );

                        throw nvae;
                }

                switch ( alt20 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:320:40: explicitConstructorInvocation
                    {
                        this.following.push( JavaParser.FOLLOW_explicitConstructorInvocation_in_constructorBody765 );
                        explicitConstructorInvocation();
                        this.following.pop();

                    }
                        break;

                }

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:321:13: ( statement )*
                loop21 : do {
                    int alt21 = 2;
                    final int LA21_0 = this.input.LA( 1 );
                    if ( LA21_0 == JavaParser.IDENT || (LA21_0 >= JavaParser.LCURLY && LA21_0 <= JavaParser.SEMI) || LA21_0 == JavaParser.LPAREN || (LA21_0 >= JavaParser.PLUS && LA21_0 <= JavaParser.MINUS) || (LA21_0 >= JavaParser.INC && LA21_0 <= JavaParser.NUM_FLOAT) || (LA21_0 >= 68 && LA21_0 <= 89) || (LA21_0 >= 93 && LA21_0 <= 94)
                         || LA21_0 == 96 || (LA21_0 >= 98 && LA21_0 <= 105) || LA21_0 == 108 || (LA21_0 >= 112 && LA21_0 <= 115) ) {
                        alt21 = 1;
                    }

                    switch ( alt21 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:321:14: statement
                        {
                            this.following.push( JavaParser.FOLLOW_statement_in_constructorBody782 );
                            statement();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop21;
                    }
                } while ( true );

                match( this.input,
                       JavaParser.RCURLY,
                       JavaParser.FOLLOW_RCURLY_in_constructorBody794 );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end constructorBody

    // $ANTLR start explicitConstructorInvocation
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:325:1: explicitConstructorInvocation : ( 'this' LPAREN argList RPAREN SEMI | 'super' LPAREN argList RPAREN SEMI );
    public void explicitConstructorInvocation() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:327:9: ( 'this' LPAREN argList RPAREN SEMI | 'super' LPAREN argList RPAREN SEMI )
            int alt22 = 2;
            final int LA22_0 = this.input.LA( 1 );
            if ( LA22_0 == 93 ) {
                alt22 = 1;
            } else if ( LA22_0 == 94 ) {
                alt22 = 2;
            } else {
                final NoViableAltException nvae = new NoViableAltException( "325:1: explicitConstructorInvocation : ( \'this\' LPAREN argList RPAREN SEMI | \'super\' LPAREN argList RPAREN SEMI );",
                                                                      22,
                                                                      0,
                                                                      this.input );

                throw nvae;
            }
            switch ( alt22 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:327:9: 'this' LPAREN argList RPAREN SEMI
                {
                    match( this.input,
                           93,
                           JavaParser.FOLLOW_93_in_explicitConstructorInvocation815 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_explicitConstructorInvocation817 );
                    this.following.push( JavaParser.FOLLOW_argList_in_explicitConstructorInvocation819 );
                    argList();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_explicitConstructorInvocation821 );
                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_explicitConstructorInvocation823 );

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:329:9: 'super' LPAREN argList RPAREN SEMI
                {
                    match( this.input,
                           94,
                           JavaParser.FOLLOW_94_in_explicitConstructorInvocation836 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_explicitConstructorInvocation838 );
                    this.following.push( JavaParser.FOLLOW_argList_in_explicitConstructorInvocation840 );
                    argList();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_explicitConstructorInvocation842 );
                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_explicitConstructorInvocation844 );

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end explicitConstructorInvocation

    // $ANTLR start variableDefinitions
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:333:1: variableDefinitions : variableDeclarator ( COMMA variableDeclarator )* ;
    public void variableDefinitions() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:334:17: ( variableDeclarator ( COMMA variableDeclarator )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:334:17: variableDeclarator ( COMMA variableDeclarator )*
            {
                this.following.push( JavaParser.FOLLOW_variableDeclarator_in_variableDefinitions861 );
                variableDeclarator();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:335:17: ( COMMA variableDeclarator )*
                loop23 : do {
                    int alt23 = 2;
                    final int LA23_0 = this.input.LA( 1 );
                    if ( LA23_0 == JavaParser.COMMA ) {
                        alt23 = 1;
                    }

                    switch ( alt23 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:335:25: COMMA variableDeclarator
                        {
                            match( this.input,
                                   JavaParser.COMMA,
                                   JavaParser.FOLLOW_COMMA_in_variableDefinitions867 );
                            this.following.push( JavaParser.FOLLOW_variableDeclarator_in_variableDefinitions872 );
                            variableDeclarator();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop23;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end variableDefinitions

    // $ANTLR start variableDeclarator
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:340:1: variableDeclarator : IDENT declaratorBrackets varInitializer ;
    public void variableDeclarator() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:345:17: ( IDENT declaratorBrackets varInitializer )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:345:17: IDENT declaratorBrackets varInitializer
            {
                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_variableDeclarator890 );
                this.following.push( JavaParser.FOLLOW_declaratorBrackets_in_variableDeclarator892 );
                declaratorBrackets();
                this.following.pop();

                this.following.push( JavaParser.FOLLOW_varInitializer_in_variableDeclarator894 );
                varInitializer();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end variableDeclarator

    // $ANTLR start declaratorBrackets
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:349:1: declaratorBrackets : ( LBRACK RBRACK )* ;
    public void declaratorBrackets() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:351:17: ( ( LBRACK RBRACK )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:351:17: ( LBRACK RBRACK )*
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:351:17: ( LBRACK RBRACK )*
                loop24 : do {
                    int alt24 = 2;
                    final int LA24_0 = this.input.LA( 1 );
                    if ( LA24_0 == JavaParser.LBRACK ) {
                        alt24 = 1;
                    }

                    switch ( alt24 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:351:18: LBRACK RBRACK
                        {
                            match( this.input,
                                   JavaParser.LBRACK,
                                   JavaParser.FOLLOW_LBRACK_in_declaratorBrackets912 );
                            match( this.input,
                                   JavaParser.RBRACK,
                                   JavaParser.FOLLOW_RBRACK_in_declaratorBrackets915 );

                        }
                            break;

                        default :
                            break loop24;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end declaratorBrackets

    // $ANTLR start varInitializer
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:354:1: varInitializer : ( ASSIGN initializer )? ;
    public void varInitializer() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:355:17: ( ( ASSIGN initializer )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:355:17: ( ASSIGN initializer )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:355:17: ( ASSIGN initializer )?
                int alt25 = 2;
                final int LA25_0 = this.input.LA( 1 );
                if ( LA25_0 == JavaParser.ASSIGN ) {
                    alt25 = 1;
                } else if ( LA25_0 == JavaParser.SEMI || LA25_0 == JavaParser.COMMA ) {
                    alt25 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "355:17: ( ASSIGN initializer )?",
                                                                          25,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt25 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:355:19: ASSIGN initializer
                    {
                        match( this.input,
                               JavaParser.ASSIGN,
                               JavaParser.FOLLOW_ASSIGN_in_varInitializer930 );
                        this.following.push( JavaParser.FOLLOW_initializer_in_varInitializer932 );
                        initializer();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end varInitializer

    // $ANTLR start arrayInitializer
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:359:1: arrayInitializer : LCURLY ( initializer ( COMMA initializer )* ( COMMA )? )? RCURLY ;
    public void arrayInitializer() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:360:17: ( LCURLY ( initializer ( COMMA initializer )* ( COMMA )? )? RCURLY )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:360:17: LCURLY ( initializer ( COMMA initializer )* ( COMMA )? )? RCURLY
            {
                match( this.input,
                       JavaParser.LCURLY,
                       JavaParser.FOLLOW_LCURLY_in_arrayInitializer947 );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:361:25: ( initializer ( COMMA initializer )* ( COMMA )? )?
                int alt28 = 2;
                final int LA28_0 = this.input.LA( 1 );
                if ( LA28_0 == JavaParser.IDENT || LA28_0 == JavaParser.LCURLY || LA28_0 == JavaParser.LPAREN || (LA28_0 >= JavaParser.PLUS && LA28_0 <= JavaParser.MINUS) || (LA28_0 >= JavaParser.INC && LA28_0 <= JavaParser.NUM_FLOAT) || (LA28_0 >= 68 && LA28_0 <= 76) || (LA28_0 >= 93 && LA28_0 <= 94)
                     || (LA28_0 >= 112 && LA28_0 <= 115) ) {
                    alt28 = 1;
                } else if ( LA28_0 == JavaParser.RCURLY ) {
                    alt28 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "361:25: ( initializer ( COMMA initializer )* ( COMMA )? )?",
                                                                          28,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt28 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:361:33: initializer ( COMMA initializer )* ( COMMA )?
                    {
                        this.following.push( JavaParser.FOLLOW_initializer_in_arrayInitializer955 );
                        initializer();
                        this.following.pop();

                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:362:33: ( COMMA initializer )*
                        loop26 : do {
                            int alt26 = 2;
                            final int LA26_0 = this.input.LA( 1 );
                            if ( LA26_0 == JavaParser.COMMA ) {
                                final int LA26_1 = this.input.LA( 2 );
                                if ( LA26_1 == JavaParser.IDENT || LA26_1 == JavaParser.LCURLY || LA26_1 == JavaParser.LPAREN || (LA26_1 >= JavaParser.PLUS && LA26_1 <= JavaParser.MINUS) || (LA26_1 >= JavaParser.INC && LA26_1 <= JavaParser.NUM_FLOAT) || (LA26_1 >= 68 && LA26_1 <= 76) || (LA26_1 >= 93 && LA26_1 <= 94)
                                     || (LA26_1 >= 112 && LA26_1 <= 115) ) {
                                    alt26 = 1;
                                }

                            }

                            switch ( alt26 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:367:41: COMMA initializer
                                {
                                    match( this.input,
                                           JavaParser.COMMA,
                                           JavaParser.FOLLOW_COMMA_in_arrayInitializer992 );
                                    this.following.push( JavaParser.FOLLOW_initializer_in_arrayInitializer994 );
                                    initializer();
                                    this.following.pop();

                                }
                                    break;

                                default :
                                    break loop26;
                            }
                        } while ( true );

                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:369:33: ( COMMA )?
                        int alt27 = 2;
                        final int LA27_0 = this.input.LA( 1 );
                        if ( LA27_0 == JavaParser.COMMA ) {
                            alt27 = 1;
                        } else if ( LA27_0 == JavaParser.RCURLY ) {
                            alt27 = 2;
                        } else {
                            final NoViableAltException nvae = new NoViableAltException( "369:33: ( COMMA )?",
                                                                                  27,
                                                                                  0,
                                                                                  this.input );

                            throw nvae;
                        }
                        switch ( alt27 ) {
                            case 1 :
                                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:369:34: COMMA
                            {
                                match( this.input,
                                       JavaParser.COMMA,
                                       JavaParser.FOLLOW_COMMA_in_arrayInitializer1008 );

                            }
                                break;

                        }

                    }
                        break;

                }

                match( this.input,
                       JavaParser.RCURLY,
                       JavaParser.FOLLOW_RCURLY_in_arrayInitializer1020 );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end arrayInitializer

    // $ANTLR start initializer
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:377:1: initializer : ( expression | arrayInitializer );
    public void initializer() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:378:17: ( expression | arrayInitializer )
            int alt29 = 2;
            final int LA29_0 = this.input.LA( 1 );
            if ( LA29_0 == JavaParser.IDENT || LA29_0 == JavaParser.LPAREN || (LA29_0 >= JavaParser.PLUS && LA29_0 <= JavaParser.MINUS) || (LA29_0 >= JavaParser.INC && LA29_0 <= JavaParser.NUM_FLOAT) || (LA29_0 >= 68 && LA29_0 <= 76) || (LA29_0 >= 93 && LA29_0 <= 94) || (LA29_0 >= 112 && LA29_0 <= 115) ) {
                alt29 = 1;
            } else if ( LA29_0 == JavaParser.LCURLY ) {
                alt29 = 2;
            } else {
                final NoViableAltException nvae = new NoViableAltException( "377:1: initializer : ( expression | arrayInitializer );",
                                                                      29,
                                                                      0,
                                                                      this.input );

                throw nvae;
            }
            switch ( alt29 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:378:17: expression
                {
                    this.following.push( JavaParser.FOLLOW_expression_in_initializer1034 );
                    expression();
                    this.following.pop();

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:379:17: arrayInitializer
                {
                    this.following.push( JavaParser.FOLLOW_arrayInitializer_in_initializer1039 );
                    arrayInitializer();
                    this.following.pop();

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end initializer

    // $ANTLR start ctorHead
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:385:1: ctorHead : IDENT LPAREN parameterDeclarationList RPAREN ( throwsClause )? ;
    public void ctorHead() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:386:17: ( IDENT LPAREN parameterDeclarationList RPAREN ( throwsClause )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:386:17: IDENT LPAREN parameterDeclarationList RPAREN ( throwsClause )?
            {
                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_ctorHead1053 );
                match( this.input,
                       JavaParser.LPAREN,
                       JavaParser.FOLLOW_LPAREN_in_ctorHead1063 );
                this.following.push( JavaParser.FOLLOW_parameterDeclarationList_in_ctorHead1065 );
                parameterDeclarationList();
                this.following.pop();

                match( this.input,
                       JavaParser.RPAREN,
                       JavaParser.FOLLOW_RPAREN_in_ctorHead1067 );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:392:17: ( throwsClause )?
                int alt30 = 2;
                final int LA30_0 = this.input.LA( 1 );
                if ( LA30_0 == 95 ) {
                    alt30 = 1;
                } else if ( LA30_0 == JavaParser.LCURLY ) {
                    alt30 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "392:17: ( throwsClause )?",
                                                                          30,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt30 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:392:18: throwsClause
                    {
                        this.following.push( JavaParser.FOLLOW_throwsClause_in_ctorHead1076 );
                        throwsClause();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end ctorHead

    // $ANTLR start throwsClause
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:396:1: throwsClause : 'throws' identifier ( COMMA identifier )* ;
    public void throwsClause() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:397:17: ( 'throws' identifier ( COMMA identifier )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:397:17: 'throws' identifier ( COMMA identifier )*
            {
                match( this.input,
                       95,
                       JavaParser.FOLLOW_95_in_throwsClause1090 );
                this.following.push( JavaParser.FOLLOW_identifier_in_throwsClause1092 );
                identifier();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:397:37: ( COMMA identifier )*
                loop31 : do {
                    int alt31 = 2;
                    final int LA31_0 = this.input.LA( 1 );
                    if ( LA31_0 == JavaParser.COMMA ) {
                        alt31 = 1;
                    }

                    switch ( alt31 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:397:39: COMMA identifier
                        {
                            match( this.input,
                                   JavaParser.COMMA,
                                   JavaParser.FOLLOW_COMMA_in_throwsClause1096 );
                            this.following.push( JavaParser.FOLLOW_identifier_in_throwsClause1098 );
                            identifier();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop31;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end throwsClause

    // $ANTLR start parameterDeclarationList
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:402:1: parameterDeclarationList : ( parameterDeclaration ( COMMA parameterDeclaration )* )? ;
    public void parameterDeclarationList() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:403:17: ( ( parameterDeclaration ( COMMA parameterDeclaration )* )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:403:17: ( parameterDeclaration ( COMMA parameterDeclaration )* )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:403:17: ( parameterDeclaration ( COMMA parameterDeclaration )* )?
                int alt33 = 2;
                final int LA33_0 = this.input.LA( 1 );
                if ( LA33_0 == JavaParser.IDENT || (LA33_0 >= 68 && LA33_0 <= 76) || LA33_0 == 82 ) {
                    alt33 = 1;
                } else if ( LA33_0 == JavaParser.RPAREN ) {
                    alt33 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "403:17: ( parameterDeclaration ( COMMA parameterDeclaration )* )?",
                                                                          33,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt33 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:403:19: parameterDeclaration ( COMMA parameterDeclaration )*
                    {
                        this.following.push( JavaParser.FOLLOW_parameterDeclaration_in_parameterDeclarationList1116 );
                        parameterDeclaration();
                        this.following.pop();

                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:403:40: ( COMMA parameterDeclaration )*
                        loop32 : do {
                            int alt32 = 2;
                            final int LA32_0 = this.input.LA( 1 );
                            if ( LA32_0 == JavaParser.COMMA ) {
                                alt32 = 1;
                            }

                            switch ( alt32 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:403:42: COMMA parameterDeclaration
                                {
                                    match( this.input,
                                           JavaParser.COMMA,
                                           JavaParser.FOLLOW_COMMA_in_parameterDeclarationList1120 );
                                    this.following.push( JavaParser.FOLLOW_parameterDeclaration_in_parameterDeclarationList1122 );
                                    parameterDeclaration();
                                    this.following.pop();

                                }
                                    break;

                                default :
                                    break loop32;
                            }
                        } while ( true );

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end parameterDeclarationList

    // $ANTLR start parameterDeclaration
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:407:1: parameterDeclaration : parameterModifier typeSpec IDENT declaratorBrackets ;
    public void parameterDeclaration() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:408:17: ( parameterModifier typeSpec IDENT declaratorBrackets )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:408:17: parameterModifier typeSpec IDENT declaratorBrackets
            {
                this.following.push( JavaParser.FOLLOW_parameterModifier_in_parameterDeclaration1140 );
                parameterModifier();
                this.following.pop();

                this.following.push( JavaParser.FOLLOW_typeSpec_in_parameterDeclaration1142 );
                typeSpec();
                this.following.pop();

                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_parameterDeclaration1144 );
                this.following.push( JavaParser.FOLLOW_declaratorBrackets_in_parameterDeclaration1148 );
                declaratorBrackets();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end parameterDeclaration

    // $ANTLR start parameterModifier
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:412:1: parameterModifier : ( 'final' )? ;
    public void parameterModifier() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:413:17: ( ( 'final' )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:413:17: ( 'final' )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:413:17: ( 'final' )?
                int alt34 = 2;
                final int LA34_0 = this.input.LA( 1 );
                if ( LA34_0 == 82 ) {
                    alt34 = 1;
                } else if ( LA34_0 == JavaParser.IDENT || (LA34_0 >= 68 && LA34_0 <= 76) ) {
                    alt34 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "413:17: ( \'final\' )?",
                                                                          34,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt34 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:413:18: 'final'
                    {
                        match( this.input,
                               82,
                               JavaParser.FOLLOW_82_in_parameterModifier1160 );

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end parameterModifier

    // $ANTLR start compoundStatement
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:426:1: compoundStatement : LCURLY ( statement )* RCURLY ;
    public void compoundStatement() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:427:17: ( LCURLY ( statement )* RCURLY )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:427:17: LCURLY ( statement )* RCURLY
            {
                match( this.input,
                       JavaParser.LCURLY,
                       JavaParser.FOLLOW_LCURLY_in_compoundStatement1185 );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:429:25: ( statement )*
                loop35 : do {
                    int alt35 = 2;
                    final int LA35_0 = this.input.LA( 1 );
                    if ( LA35_0 == JavaParser.IDENT || (LA35_0 >= JavaParser.LCURLY && LA35_0 <= JavaParser.SEMI) || LA35_0 == JavaParser.LPAREN || (LA35_0 >= JavaParser.PLUS && LA35_0 <= JavaParser.MINUS) || (LA35_0 >= JavaParser.INC && LA35_0 <= JavaParser.NUM_FLOAT) || (LA35_0 >= 68 && LA35_0 <= 89) || (LA35_0 >= 93 && LA35_0 <= 94)
                         || LA35_0 == 96 || (LA35_0 >= 98 && LA35_0 <= 105) || LA35_0 == 108 || (LA35_0 >= 112 && LA35_0 <= 115) ) {
                        alt35 = 1;
                    }

                    switch ( alt35 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:429:26: statement
                        {
                            this.following.push( JavaParser.FOLLOW_statement_in_compoundStatement1196 );
                            statement();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop35;
                    }
                } while ( true );

                match( this.input,
                       JavaParser.RCURLY,
                       JavaParser.FOLLOW_RCURLY_in_compoundStatement1202 );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end compoundStatement

    // $ANTLR start statement
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:434:1: statement : ( compoundStatement | declaration SEMI | expression SEMI | modifiers classDefinition | IDENT COLON statement | 'if' LPAREN expression RPAREN statement ( 'else' statement )? | 'for' LPAREN forInit SEMI forCond SEMI forIter RPAREN statement | 'while' LPAREN expression RPAREN statement | 'do' statement 'while' LPAREN expression RPAREN SEMI | 'break' ( IDENT )? SEMI | 'continue' ( IDENT )? SEMI | 'return' ( expression )? SEMI | 'switch' LPAREN expression RPAREN LCURLY ( casesGroup )* RCURLY | tryBlock | 'throw' expression SEMI | 'synchronized' LPAREN expression RPAREN compoundStatement | SEMI );
    public void statement() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:436:17: ( compoundStatement | declaration SEMI | expression SEMI | modifiers classDefinition | IDENT COLON statement | 'if' LPAREN expression RPAREN statement ( 'else' statement )? | 'for' LPAREN forInit SEMI forCond SEMI forIter RPAREN statement | 'while' LPAREN expression RPAREN statement | 'do' statement 'while' LPAREN expression RPAREN SEMI | 'break' ( IDENT )? SEMI | 'continue' ( IDENT )? SEMI | 'return' ( expression )? SEMI | 'switch' LPAREN expression RPAREN LCURLY ( casesGroup )* RCURLY | tryBlock | 'throw' expression SEMI | 'synchronized' LPAREN expression RPAREN compoundStatement | SEMI )
            int alt41 = 17;
            alt41 = this.dfa41.predict( this.input );
            switch ( alt41 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:436:17: compoundStatement
                {
                    this.following.push( JavaParser.FOLLOW_compoundStatement_in_statement1216 );
                    compoundStatement();
                    this.following.pop();

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:443:17: declaration SEMI
                {
                    this.following.push( JavaParser.FOLLOW_declaration_in_statement1232 );
                    declaration();
                    this.following.pop();

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1234 );

                }
                    break;
                case 3 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:448:17: expression SEMI
                {
                    this.following.push( JavaParser.FOLLOW_expression_in_statement1246 );
                    expression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1248 );

                }
                    break;
                case 4 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:451:17: modifiers classDefinition
                {
                    this.following.push( JavaParser.FOLLOW_modifiers_in_statement1256 );
                    modifiers();
                    this.following.pop();

                    this.following.push( JavaParser.FOLLOW_classDefinition_in_statement1258 );
                    classDefinition();
                    this.following.pop();

                }
                    break;
                case 5 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:454:17: IDENT COLON statement
                {
                    match( this.input,
                           JavaParser.IDENT,
                           JavaParser.FOLLOW_IDENT_in_statement1266 );
                    match( this.input,
                           JavaParser.COLON,
                           JavaParser.FOLLOW_COLON_in_statement1268 );
                    this.following.push( JavaParser.FOLLOW_statement_in_statement1271 );
                    statement();
                    this.following.pop();

                }
                    break;
                case 6 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:457:17: 'if' LPAREN expression RPAREN statement ( 'else' statement )?
                {
                    match( this.input,
                           96,
                           JavaParser.FOLLOW_96_in_statement1279 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_statement1281 );
                    this.following.push( JavaParser.FOLLOW_expression_in_statement1283 );
                    expression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_statement1285 );
                    this.following.push( JavaParser.FOLLOW_statement_in_statement1287 );
                    statement();
                    this.following.pop();

                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:458:17: ( 'else' statement )?
                    int alt36 = 2;
                    final int LA36_0 = this.input.LA( 1 );
                    if ( LA36_0 == 97 ) {
                        alt36 = 1;
                    } else if ( LA36_0 == JavaParser.IDENT || (LA36_0 >= JavaParser.LCURLY && LA36_0 <= JavaParser.RCURLY) || LA36_0 == JavaParser.LPAREN || (LA36_0 >= JavaParser.PLUS && LA36_0 <= JavaParser.MINUS) || (LA36_0 >= JavaParser.INC && LA36_0 <= JavaParser.NUM_FLOAT) || (LA36_0 >= 68 && LA36_0 <= 89)
                                || (LA36_0 >= 93 && LA36_0 <= 94) || LA36_0 == 96 || (LA36_0 >= 98 && LA36_0 <= 108) || (LA36_0 >= 112 && LA36_0 <= 115) ) {
                        alt36 = 2;
                    } else {
                        final NoViableAltException nvae = new NoViableAltException( "458:17: ( \'else\' statement )?",
                                                                              36,
                                                                              0,
                                                                              this.input );

                        throw nvae;
                    }
                    switch ( alt36 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:462:25: 'else' statement
                        {
                            match( this.input,
                                   97,
                                   JavaParser.FOLLOW_97_in_statement1308 );
                            this.following.push( JavaParser.FOLLOW_statement_in_statement1310 );
                            statement();
                            this.following.pop();

                        }
                            break;

                    }

                }
                    break;
                case 7 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:466:17: 'for' LPAREN forInit SEMI forCond SEMI forIter RPAREN statement
                {
                    match( this.input,
                           98,
                           JavaParser.FOLLOW_98_in_statement1323 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_statement1328 );
                    this.following.push( JavaParser.FOLLOW_forInit_in_statement1334 );
                    forInit();
                    this.following.pop();

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1336 );
                    this.following.push( JavaParser.FOLLOW_forCond_in_statement1345 );
                    forCond();
                    this.following.pop();

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1347 );
                    this.following.push( JavaParser.FOLLOW_forIter_in_statement1356 );
                    forIter();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_statement1370 );
                    this.following.push( JavaParser.FOLLOW_statement_in_statement1375 );
                    statement();
                    this.following.pop();

                }
                    break;
                case 8 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:475:17: 'while' LPAREN expression RPAREN statement
                {
                    match( this.input,
                           99,
                           JavaParser.FOLLOW_99_in_statement1404 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_statement1406 );
                    this.following.push( JavaParser.FOLLOW_expression_in_statement1408 );
                    expression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_statement1410 );
                    this.following.push( JavaParser.FOLLOW_statement_in_statement1412 );
                    statement();
                    this.following.pop();

                }
                    break;
                case 9 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:478:17: 'do' statement 'while' LPAREN expression RPAREN SEMI
                {
                    match( this.input,
                           100,
                           JavaParser.FOLLOW_100_in_statement1420 );
                    this.following.push( JavaParser.FOLLOW_statement_in_statement1422 );
                    statement();
                    this.following.pop();

                    match( this.input,
                           99,
                           JavaParser.FOLLOW_99_in_statement1424 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_statement1426 );
                    this.following.push( JavaParser.FOLLOW_expression_in_statement1428 );
                    expression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_statement1430 );
                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1432 );

                }
                    break;
                case 10 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:481:17: 'break' ( IDENT )? SEMI
                {
                    match( this.input,
                           101,
                           JavaParser.FOLLOW_101_in_statement1440 );
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:481:25: ( IDENT )?
                    int alt37 = 2;
                    final int LA37_0 = this.input.LA( 1 );
                    if ( LA37_0 == JavaParser.IDENT ) {
                        alt37 = 1;
                    } else if ( LA37_0 == JavaParser.SEMI ) {
                        alt37 = 2;
                    } else {
                        final NoViableAltException nvae = new NoViableAltException( "481:25: ( IDENT )?",
                                                                              37,
                                                                              0,
                                                                              this.input );

                        throw nvae;
                    }
                    switch ( alt37 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:481:26: IDENT
                        {
                            match( this.input,
                                   JavaParser.IDENT,
                                   JavaParser.FOLLOW_IDENT_in_statement1443 );

                        }
                            break;

                    }

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1447 );

                }
                    break;
                case 11 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:484:17: 'continue' ( IDENT )? SEMI
                {
                    match( this.input,
                           102,
                           JavaParser.FOLLOW_102_in_statement1455 );
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:484:28: ( IDENT )?
                    int alt38 = 2;
                    final int LA38_0 = this.input.LA( 1 );
                    if ( LA38_0 == JavaParser.IDENT ) {
                        alt38 = 1;
                    } else if ( LA38_0 == JavaParser.SEMI ) {
                        alt38 = 2;
                    } else {
                        final NoViableAltException nvae = new NoViableAltException( "484:28: ( IDENT )?",
                                                                              38,
                                                                              0,
                                                                              this.input );

                        throw nvae;
                    }
                    switch ( alt38 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:484:29: IDENT
                        {
                            match( this.input,
                                   JavaParser.IDENT,
                                   JavaParser.FOLLOW_IDENT_in_statement1458 );

                        }
                            break;

                    }

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1462 );

                }
                    break;
                case 12 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:487:17: 'return' ( expression )? SEMI
                {
                    match( this.input,
                           103,
                           JavaParser.FOLLOW_103_in_statement1470 );
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:487:26: ( expression )?
                    int alt39 = 2;
                    final int LA39_0 = this.input.LA( 1 );
                    if ( LA39_0 == JavaParser.IDENT || LA39_0 == JavaParser.LPAREN || (LA39_0 >= JavaParser.PLUS && LA39_0 <= JavaParser.MINUS) || (LA39_0 >= JavaParser.INC && LA39_0 <= JavaParser.NUM_FLOAT) || (LA39_0 >= 68 && LA39_0 <= 76) || (LA39_0 >= 93 && LA39_0 <= 94) || (LA39_0 >= 112 && LA39_0 <= 115) ) {
                        alt39 = 1;
                    } else if ( LA39_0 == JavaParser.SEMI ) {
                        alt39 = 2;
                    } else {
                        final NoViableAltException nvae = new NoViableAltException( "487:26: ( expression )?",
                                                                              39,
                                                                              0,
                                                                              this.input );

                        throw nvae;
                    }
                    switch ( alt39 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:487:27: expression
                        {
                            this.following.push( JavaParser.FOLLOW_expression_in_statement1473 );
                            expression();
                            this.following.pop();

                        }
                            break;

                    }

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1477 );

                }
                    break;
                case 13 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:490:17: 'switch' LPAREN expression RPAREN LCURLY ( casesGroup )* RCURLY
                {
                    match( this.input,
                           104,
                           JavaParser.FOLLOW_104_in_statement1485 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_statement1487 );
                    this.following.push( JavaParser.FOLLOW_expression_in_statement1489 );
                    expression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_statement1491 );
                    match( this.input,
                           JavaParser.LCURLY,
                           JavaParser.FOLLOW_LCURLY_in_statement1493 );
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:491:25: ( casesGroup )*
                    loop40 : do {
                        int alt40 = 2;
                        final int LA40_0 = this.input.LA( 1 );
                        if ( (LA40_0 >= 106 && LA40_0 <= 107) ) {
                            alt40 = 1;
                        }

                        switch ( alt40 ) {
                            case 1 :
                                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:491:27: casesGroup
                            {
                                this.following.push( JavaParser.FOLLOW_casesGroup_in_statement1500 );
                                casesGroup();
                                this.following.pop();

                            }
                                break;

                            default :
                                break loop40;
                        }
                    } while ( true );

                    match( this.input,
                           JavaParser.RCURLY,
                           JavaParser.FOLLOW_RCURLY_in_statement1507 );

                }
                    break;
                case 14 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:495:17: tryBlock
                {
                    this.following.push( JavaParser.FOLLOW_tryBlock_in_statement1515 );
                    tryBlock();
                    this.following.pop();

                }
                    break;
                case 15 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:498:17: 'throw' expression SEMI
                {
                    match( this.input,
                           105,
                           JavaParser.FOLLOW_105_in_statement1523 );
                    this.following.push( JavaParser.FOLLOW_expression_in_statement1525 );
                    expression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1527 );

                }
                    break;
                case 16 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:501:17: 'synchronized' LPAREN expression RPAREN compoundStatement
                {
                    match( this.input,
                           86,
                           JavaParser.FOLLOW_86_in_statement1535 );
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_statement1537 );
                    this.following.push( JavaParser.FOLLOW_expression_in_statement1539 );
                    expression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_statement1541 );
                    this.following.push( JavaParser.FOLLOW_compoundStatement_in_statement1543 );
                    compoundStatement();
                    this.following.pop();

                }
                    break;
                case 17 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:507:17: SEMI
                {
                    match( this.input,
                           JavaParser.SEMI,
                           JavaParser.FOLLOW_SEMI_in_statement1556 );

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end statement

    // $ANTLR start casesGroup
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:510:1: casesGroup : ( options {greedy=true; } : aCase )+ caseSList ;
    public void casesGroup() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:511:17: ( ( options {greedy=true; } : aCase )+ caseSList )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:511:17: ( options {greedy=true; } : aCase )+ caseSList
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:511:17: ( options {greedy=true; } : aCase )+
                int cnt42 = 0;
                loop42 : do {
                    int alt42 = 2;
                    final int LA42_0 = this.input.LA( 1 );
                    if ( LA42_0 == 106 ) {
                        alt42 = 1;
                    } else if ( LA42_0 == 107 ) {
                        alt42 = 1;
                    }

                    switch ( alt42 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:517:25: aCase
                        {
                            this.following.push( JavaParser.FOLLOW_aCase_in_casesGroup1602 );
                            aCase();
                            this.following.pop();

                        }
                            break;

                        default :
                            if ( cnt42 >= 1 ) {
                                break loop42;
                            }
                            final EarlyExitException eee = new EarlyExitException( 42,
                                                                             this.input );
                            throw eee;
                    }
                    cnt42++;
                } while ( true );

                this.following.push( JavaParser.FOLLOW_caseSList_in_casesGroup1611 );
                caseSList();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end casesGroup

    // $ANTLR start aCase
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:523:1: aCase : ( 'case' expression | 'default' ) COLON ;
    public void aCase() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:524:17: ( ( 'case' expression | 'default' ) COLON )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:524:17: ( 'case' expression | 'default' ) COLON
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:524:17: ( 'case' expression | 'default' )
                int alt43 = 2;
                final int LA43_0 = this.input.LA( 1 );
                if ( LA43_0 == 106 ) {
                    alt43 = 1;
                } else if ( LA43_0 == 107 ) {
                    alt43 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "524:17: ( \'case\' expression | \'default\' )",
                                                                          43,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt43 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:524:18: 'case' expression
                    {
                        match( this.input,
                               106,
                               JavaParser.FOLLOW_106_in_aCase1626 );
                        this.following.push( JavaParser.FOLLOW_expression_in_aCase1628 );
                        expression();
                        this.following.pop();

                    }
                        break;
                    case 2 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:524:38: 'default'
                    {
                        match( this.input,
                               107,
                               JavaParser.FOLLOW_107_in_aCase1632 );

                    }
                        break;

                }

                match( this.input,
                       JavaParser.COLON,
                       JavaParser.FOLLOW_COLON_in_aCase1635 );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end aCase

    // $ANTLR start caseSList
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:527:1: caseSList : ( statement )* ;
    public void caseSList() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:528:17: ( ( statement )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:528:17: ( statement )*
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:528:17: ( statement )*
                loop44 : do {
                    int alt44 = 2;
                    final int LA44_0 = this.input.LA( 1 );
                    if ( LA44_0 == JavaParser.IDENT || (LA44_0 >= JavaParser.LCURLY && LA44_0 <= JavaParser.SEMI) || LA44_0 == JavaParser.LPAREN || (LA44_0 >= JavaParser.PLUS && LA44_0 <= JavaParser.MINUS) || (LA44_0 >= JavaParser.INC && LA44_0 <= JavaParser.NUM_FLOAT) || (LA44_0 >= 68 && LA44_0 <= 89) || (LA44_0 >= 93 && LA44_0 <= 94)
                         || LA44_0 == 96 || (LA44_0 >= 98 && LA44_0 <= 105) || LA44_0 == 108 || (LA44_0 >= 112 && LA44_0 <= 115) ) {
                        alt44 = 1;
                    }

                    switch ( alt44 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:528:18: statement
                        {
                            this.following.push( JavaParser.FOLLOW_statement_in_caseSList1647 );
                            statement();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop44;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end caseSList

    // $ANTLR start forInit
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:533:1: forInit : ( declaration | expressionList )? ;
    public void forInit() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:536:17: ( ( declaration | expressionList )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:536:17: ( declaration | expressionList )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:536:17: ( declaration | expressionList )?
                int alt45 = 3;
                alt45 = this.dfa45.predict( this.input );
                switch ( alt45 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:536:25: declaration
                    {
                        this.following.push( JavaParser.FOLLOW_declaration_in_forInit1678 );
                        declaration();
                        this.following.pop();

                    }
                        break;
                    case 2 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:538:25: expressionList
                    {
                        this.following.push( JavaParser.FOLLOW_expressionList_in_forInit1687 );
                        expressionList();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end forInit

    // $ANTLR start forCond
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:543:1: forCond : ( expression )? ;
    public void forCond() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:544:17: ( ( expression )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:544:17: ( expression )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:544:17: ( expression )?
                int alt46 = 2;
                final int LA46_0 = this.input.LA( 1 );
                if ( LA46_0 == JavaParser.IDENT || LA46_0 == JavaParser.LPAREN || (LA46_0 >= JavaParser.PLUS && LA46_0 <= JavaParser.MINUS) || (LA46_0 >= JavaParser.INC && LA46_0 <= JavaParser.NUM_FLOAT) || (LA46_0 >= 68 && LA46_0 <= 76) || (LA46_0 >= 93 && LA46_0 <= 94) || (LA46_0 >= 112 && LA46_0 <= 115) ) {
                    alt46 = 1;
                } else if ( LA46_0 == JavaParser.SEMI ) {
                    alt46 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "544:17: ( expression )?",
                                                                          46,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt46 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:544:18: expression
                    {
                        this.following.push( JavaParser.FOLLOW_expression_in_forCond1707 );
                        expression();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end forCond

    // $ANTLR start forIter
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:548:1: forIter : ( expressionList )? ;
    public void forIter() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:549:17: ( ( expressionList )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:549:17: ( expressionList )?
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:549:17: ( expressionList )?
                int alt47 = 2;
                final int LA47_0 = this.input.LA( 1 );
                if ( LA47_0 == JavaParser.IDENT || LA47_0 == JavaParser.LPAREN || (LA47_0 >= JavaParser.PLUS && LA47_0 <= JavaParser.MINUS) || (LA47_0 >= JavaParser.INC && LA47_0 <= JavaParser.NUM_FLOAT) || (LA47_0 >= 68 && LA47_0 <= 76) || (LA47_0 >= 93 && LA47_0 <= 94) || (LA47_0 >= 112 && LA47_0 <= 115) ) {
                    alt47 = 1;
                } else if ( LA47_0 == JavaParser.RPAREN ) {
                    alt47 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "549:17: ( expressionList )?",
                                                                          47,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt47 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:549:18: expressionList
                    {
                        this.following.push( JavaParser.FOLLOW_expressionList_in_forIter1724 );
                        expressionList();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end forIter

    // $ANTLR start tryBlock
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:554:1: tryBlock : 'try' compoundStatement ( handler )* ( finallyClause )? ;
    public void tryBlock() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:555:17: ( 'try' compoundStatement ( handler )* ( finallyClause )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:555:17: 'try' compoundStatement ( handler )* ( finallyClause )?
            {
                match( this.input,
                       108,
                       JavaParser.FOLLOW_108_in_tryBlock1741 );
                this.following.push( JavaParser.FOLLOW_compoundStatement_in_tryBlock1743 );
                compoundStatement();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:556:17: ( handler )*
                loop48 : do {
                    int alt48 = 2;
                    final int LA48_0 = this.input.LA( 1 );
                    if ( LA48_0 == 110 ) {
                        alt48 = 1;
                    }

                    switch ( alt48 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:556:18: handler
                        {
                            this.following.push( JavaParser.FOLLOW_handler_in_tryBlock1748 );
                            handler();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop48;
                    }
                } while ( true );

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:557:17: ( finallyClause )?
                int alt49 = 2;
                final int LA49_0 = this.input.LA( 1 );
                if ( LA49_0 == 109 ) {
                    alt49 = 1;
                } else if ( LA49_0 == JavaParser.IDENT || (LA49_0 >= JavaParser.LCURLY && LA49_0 <= JavaParser.RCURLY) || LA49_0 == JavaParser.LPAREN || (LA49_0 >= JavaParser.PLUS && LA49_0 <= JavaParser.MINUS) || (LA49_0 >= JavaParser.INC && LA49_0 <= JavaParser.NUM_FLOAT) || (LA49_0 >= 68 && LA49_0 <= 89)
                            || (LA49_0 >= 93 && LA49_0 <= 94) || (LA49_0 >= 96 && LA49_0 <= 108) || (LA49_0 >= 112 && LA49_0 <= 115) ) {
                    alt49 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "557:17: ( finallyClause )?",
                                                                          49,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt49 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:557:19: finallyClause
                    {
                        this.following.push( JavaParser.FOLLOW_finallyClause_in_tryBlock1756 );
                        finallyClause();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end tryBlock

    // $ANTLR start finallyClause
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:560:1: finallyClause : 'finally' compoundStatement ;
    public void finallyClause() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:561:17: ( 'finally' compoundStatement )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:561:17: 'finally' compoundStatement
            {
                match( this.input,
                       109,
                       JavaParser.FOLLOW_109_in_finallyClause1770 );
                this.following.push( JavaParser.FOLLOW_compoundStatement_in_finallyClause1772 );
                compoundStatement();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end finallyClause

    // $ANTLR start handler
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:565:1: handler : 'catch' LPAREN parameterDeclaration RPAREN compoundStatement ;
    public void handler() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:566:17: ( 'catch' LPAREN parameterDeclaration RPAREN compoundStatement )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:566:17: 'catch' LPAREN parameterDeclaration RPAREN compoundStatement
            {
                match( this.input,
                       110,
                       JavaParser.FOLLOW_110_in_handler1784 );
                match( this.input,
                       JavaParser.LPAREN,
                       JavaParser.FOLLOW_LPAREN_in_handler1786 );
                this.following.push( JavaParser.FOLLOW_parameterDeclaration_in_handler1788 );
                parameterDeclaration();
                this.following.pop();

                match( this.input,
                       JavaParser.RPAREN,
                       JavaParser.FOLLOW_RPAREN_in_handler1790 );
                this.following.push( JavaParser.FOLLOW_compoundStatement_in_handler1792 );
                compoundStatement();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end handler

    // $ANTLR start expression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:605:1: expression : assignmentExpression ;
    public void expression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:606:17: ( assignmentExpression )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:606:17: assignmentExpression
            {
                this.following.push( JavaParser.FOLLOW_assignmentExpression_in_expression1839 );
                assignmentExpression();
                this.following.pop();

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end expression

    // $ANTLR start expressionList
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:612:1: expressionList : expression ( COMMA expression )* ;
    public void expressionList() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:613:17: ( expression ( COMMA expression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:613:17: expression ( COMMA expression )*
            {
                this.following.push( JavaParser.FOLLOW_expression_in_expressionList1855 );
                expression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:613:28: ( COMMA expression )*
                loop50 : do {
                    int alt50 = 2;
                    final int LA50_0 = this.input.LA( 1 );
                    if ( LA50_0 == JavaParser.COMMA ) {
                        alt50 = 1;
                    }

                    switch ( alt50 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:613:29: COMMA expression
                        {
                            match( this.input,
                                   JavaParser.COMMA,
                                   JavaParser.FOLLOW_COMMA_in_expressionList1858 );
                            this.following.push( JavaParser.FOLLOW_expression_in_expressionList1860 );
                            expression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop50;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end expressionList

    // $ANTLR start assignmentExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:619:1: assignmentExpression : conditionalExpression ( (ASSIGN|PLUS_ASSIGN|MINUS_ASSIGN|STAR_ASSIGN|DIV_ASSIGN|MOD_ASSIGN|SR_ASSIGN|BSR_ASSIGN|SL_ASSIGN|BAND_ASSIGN|BXOR_ASSIGN|BOR_ASSIGN) assignmentExpression )? ;
    public void assignmentExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:620:17: ( conditionalExpression ( (ASSIGN|PLUS_ASSIGN|MINUS_ASSIGN|STAR_ASSIGN|DIV_ASSIGN|MOD_ASSIGN|SR_ASSIGN|BSR_ASSIGN|SL_ASSIGN|BAND_ASSIGN|BXOR_ASSIGN|BOR_ASSIGN) assignmentExpression )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:620:17: conditionalExpression ( (ASSIGN|PLUS_ASSIGN|MINUS_ASSIGN|STAR_ASSIGN|DIV_ASSIGN|MOD_ASSIGN|SR_ASSIGN|BSR_ASSIGN|SL_ASSIGN|BAND_ASSIGN|BXOR_ASSIGN|BOR_ASSIGN) assignmentExpression )?
            {
                this.following.push( JavaParser.FOLLOW_conditionalExpression_in_assignmentExpression1878 );
                conditionalExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:621:17: ( (ASSIGN|PLUS_ASSIGN|MINUS_ASSIGN|STAR_ASSIGN|DIV_ASSIGN|MOD_ASSIGN|SR_ASSIGN|BSR_ASSIGN|SL_ASSIGN|BAND_ASSIGN|BXOR_ASSIGN|BOR_ASSIGN) assignmentExpression )?
                int alt51 = 2;
                final int LA51_0 = this.input.LA( 1 );
                if ( LA51_0 == JavaParser.ASSIGN || (LA51_0 >= JavaParser.PLUS_ASSIGN && LA51_0 <= JavaParser.BOR_ASSIGN) ) {
                    alt51 = 1;
                } else if ( LA51_0 == JavaParser.RBRACK || (LA51_0 >= JavaParser.SEMI && LA51_0 <= JavaParser.COMMA) || LA51_0 == JavaParser.RPAREN || LA51_0 == JavaParser.COLON ) {
                    alt51 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "621:17: ( (ASSIGN|PLUS_ASSIGN|MINUS_ASSIGN|STAR_ASSIGN|DIV_ASSIGN|MOD_ASSIGN|SR_ASSIGN|BSR_ASSIGN|SL_ASSIGN|BAND_ASSIGN|BXOR_ASSIGN|BOR_ASSIGN) assignmentExpression )?",
                                                                          51,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt51 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:621:25: (ASSIGN|PLUS_ASSIGN|MINUS_ASSIGN|STAR_ASSIGN|DIV_ASSIGN|MOD_ASSIGN|SR_ASSIGN|BSR_ASSIGN|SL_ASSIGN|BAND_ASSIGN|BXOR_ASSIGN|BOR_ASSIGN) assignmentExpression
                    {
                        if ( this.input.LA( 1 ) == JavaParser.ASSIGN || (this.input.LA( 1 ) >= JavaParser.PLUS_ASSIGN && this.input.LA( 1 ) <= JavaParser.BOR_ASSIGN) ) {
                            this.input.consume();
                            this.errorRecovery = false;
                        } else {
                            final MismatchedSetException mse = new MismatchedSetException( null,
                                                                                     this.input );
                            recoverFromMismatchedSet( this.input,
                                                      mse,
                                                      JavaParser.FOLLOW_set_in_assignmentExpression1886 );
                            throw mse;
                        }

                        this.following.push( JavaParser.FOLLOW_assignmentExpression_in_assignmentExpression2103 );
                        assignmentExpression();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end assignmentExpression

    // $ANTLR start conditionalExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:640:1: conditionalExpression : logicalOrExpression ( QUESTION assignmentExpression COLON conditionalExpression )? ;
    public void conditionalExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:641:17: ( logicalOrExpression ( QUESTION assignmentExpression COLON conditionalExpression )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:641:17: logicalOrExpression ( QUESTION assignmentExpression COLON conditionalExpression )?
            {
                this.following.push( JavaParser.FOLLOW_logicalOrExpression_in_conditionalExpression2121 );
                logicalOrExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:642:17: ( QUESTION assignmentExpression COLON conditionalExpression )?
                int alt52 = 2;
                final int LA52_0 = this.input.LA( 1 );
                if ( LA52_0 == JavaParser.QUESTION ) {
                    alt52 = 1;
                } else if ( LA52_0 == JavaParser.RBRACK || (LA52_0 >= JavaParser.SEMI && LA52_0 <= JavaParser.COMMA) || (LA52_0 >= JavaParser.RPAREN && LA52_0 <= JavaParser.BOR_ASSIGN) ) {
                    alt52 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "642:17: ( QUESTION assignmentExpression COLON conditionalExpression )?",
                                                                          52,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt52 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:642:19: QUESTION assignmentExpression COLON conditionalExpression
                    {
                        match( this.input,
                               JavaParser.QUESTION,
                               JavaParser.FOLLOW_QUESTION_in_conditionalExpression2127 );
                        this.following.push( JavaParser.FOLLOW_assignmentExpression_in_conditionalExpression2129 );
                        assignmentExpression();
                        this.following.pop();

                        match( this.input,
                               JavaParser.COLON,
                               JavaParser.FOLLOW_COLON_in_conditionalExpression2131 );
                        this.following.push( JavaParser.FOLLOW_conditionalExpression_in_conditionalExpression2133 );
                        conditionalExpression();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end conditionalExpression

    // $ANTLR start logicalOrExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:647:1: logicalOrExpression : logicalAndExpression ( LOR logicalAndExpression )* ;
    public void logicalOrExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:648:17: ( logicalAndExpression ( LOR logicalAndExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:648:17: logicalAndExpression ( LOR logicalAndExpression )*
            {
                this.following.push( JavaParser.FOLLOW_logicalAndExpression_in_logicalOrExpression2149 );
                logicalAndExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:648:38: ( LOR logicalAndExpression )*
                loop53 : do {
                    int alt53 = 2;
                    final int LA53_0 = this.input.LA( 1 );
                    if ( LA53_0 == JavaParser.LOR ) {
                        alt53 = 1;
                    }

                    switch ( alt53 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:648:39: LOR logicalAndExpression
                        {
                            match( this.input,
                                   JavaParser.LOR,
                                   JavaParser.FOLLOW_LOR_in_logicalOrExpression2152 );
                            this.following.push( JavaParser.FOLLOW_logicalAndExpression_in_logicalOrExpression2154 );
                            logicalAndExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop53;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end logicalOrExpression

    // $ANTLR start logicalAndExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:653:1: logicalAndExpression : inclusiveOrExpression ( LAND inclusiveOrExpression )* ;
    public void logicalAndExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:654:17: ( inclusiveOrExpression ( LAND inclusiveOrExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:654:17: inclusiveOrExpression ( LAND inclusiveOrExpression )*
            {
                this.following.push( JavaParser.FOLLOW_inclusiveOrExpression_in_logicalAndExpression2169 );
                inclusiveOrExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:654:39: ( LAND inclusiveOrExpression )*
                loop54 : do {
                    int alt54 = 2;
                    final int LA54_0 = this.input.LA( 1 );
                    if ( LA54_0 == JavaParser.LAND ) {
                        alt54 = 1;
                    }

                    switch ( alt54 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:654:40: LAND inclusiveOrExpression
                        {
                            match( this.input,
                                   JavaParser.LAND,
                                   JavaParser.FOLLOW_LAND_in_logicalAndExpression2172 );
                            this.following.push( JavaParser.FOLLOW_inclusiveOrExpression_in_logicalAndExpression2174 );
                            inclusiveOrExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop54;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end logicalAndExpression

    // $ANTLR start inclusiveOrExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:659:1: inclusiveOrExpression : exclusiveOrExpression ( BOR exclusiveOrExpression )* ;
    public void inclusiveOrExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:660:17: ( exclusiveOrExpression ( BOR exclusiveOrExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:660:17: exclusiveOrExpression ( BOR exclusiveOrExpression )*
            {
                this.following.push( JavaParser.FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2189 );
                exclusiveOrExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:660:39: ( BOR exclusiveOrExpression )*
                loop55 : do {
                    int alt55 = 2;
                    final int LA55_0 = this.input.LA( 1 );
                    if ( LA55_0 == JavaParser.BOR ) {
                        alt55 = 1;
                    }

                    switch ( alt55 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:660:40: BOR exclusiveOrExpression
                        {
                            match( this.input,
                                   JavaParser.BOR,
                                   JavaParser.FOLLOW_BOR_in_inclusiveOrExpression2192 );
                            this.following.push( JavaParser.FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2194 );
                            exclusiveOrExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop55;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end inclusiveOrExpression

    // $ANTLR start exclusiveOrExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:665:1: exclusiveOrExpression : andExpression ( BXOR andExpression )* ;
    public void exclusiveOrExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:666:17: ( andExpression ( BXOR andExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:666:17: andExpression ( BXOR andExpression )*
            {
                this.following.push( JavaParser.FOLLOW_andExpression_in_exclusiveOrExpression2209 );
                andExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:666:31: ( BXOR andExpression )*
                loop56 : do {
                    int alt56 = 2;
                    final int LA56_0 = this.input.LA( 1 );
                    if ( LA56_0 == JavaParser.BXOR ) {
                        alt56 = 1;
                    }

                    switch ( alt56 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:666:32: BXOR andExpression
                        {
                            match( this.input,
                                   JavaParser.BXOR,
                                   JavaParser.FOLLOW_BXOR_in_exclusiveOrExpression2212 );
                            this.following.push( JavaParser.FOLLOW_andExpression_in_exclusiveOrExpression2214 );
                            andExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop56;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end exclusiveOrExpression

    // $ANTLR start andExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:671:1: andExpression : equalityExpression ( BAND equalityExpression )* ;
    public void andExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:672:17: ( equalityExpression ( BAND equalityExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:672:17: equalityExpression ( BAND equalityExpression )*
            {
                this.following.push( JavaParser.FOLLOW_equalityExpression_in_andExpression2229 );
                equalityExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:672:36: ( BAND equalityExpression )*
                loop57 : do {
                    int alt57 = 2;
                    final int LA57_0 = this.input.LA( 1 );
                    if ( LA57_0 == JavaParser.BAND ) {
                        alt57 = 1;
                    }

                    switch ( alt57 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:672:37: BAND equalityExpression
                        {
                            match( this.input,
                                   JavaParser.BAND,
                                   JavaParser.FOLLOW_BAND_in_andExpression2232 );
                            this.following.push( JavaParser.FOLLOW_equalityExpression_in_andExpression2234 );
                            equalityExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop57;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end andExpression

    // $ANTLR start equalityExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:677:1: equalityExpression : relationalExpression ( (NOT_EQUAL|EQUAL) relationalExpression )* ;
    public void equalityExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:678:17: ( relationalExpression ( (NOT_EQUAL|EQUAL) relationalExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:678:17: relationalExpression ( (NOT_EQUAL|EQUAL) relationalExpression )*
            {
                this.following.push( JavaParser.FOLLOW_relationalExpression_in_equalityExpression2249 );
                relationalExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:678:38: ( (NOT_EQUAL|EQUAL) relationalExpression )*
                loop58 : do {
                    int alt58 = 2;
                    final int LA58_0 = this.input.LA( 1 );
                    if ( (LA58_0 >= JavaParser.NOT_EQUAL && LA58_0 <= JavaParser.EQUAL) ) {
                        alt58 = 1;
                    }

                    switch ( alt58 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:678:39: (NOT_EQUAL|EQUAL) relationalExpression
                        {
                            if ( (this.input.LA( 1 ) >= JavaParser.NOT_EQUAL && this.input.LA( 1 ) <= JavaParser.EQUAL) ) {
                                this.input.consume();
                                this.errorRecovery = false;
                            } else {
                                final MismatchedSetException mse = new MismatchedSetException( null,
                                                                                         this.input );
                                recoverFromMismatchedSet( this.input,
                                                          mse,
                                                          JavaParser.FOLLOW_set_in_equalityExpression2253 );
                                throw mse;
                            }

                            this.following.push( JavaParser.FOLLOW_relationalExpression_in_equalityExpression2260 );
                            relationalExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop58;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end equalityExpression

    // $ANTLR start relationalExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:683:1: relationalExpression : shiftExpression ( ( (LT|GT|LE|GE) shiftExpression )* | 'instanceof' typeSpec ) ;
    public void relationalExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:684:17: ( shiftExpression ( ( (LT|GT|LE|GE) shiftExpression )* | 'instanceof' typeSpec ) )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:684:17: shiftExpression ( ( (LT|GT|LE|GE) shiftExpression )* | 'instanceof' typeSpec )
            {
                this.following.push( JavaParser.FOLLOW_shiftExpression_in_relationalExpression2275 );
                shiftExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:685:17: ( ( (LT|GT|LE|GE) shiftExpression )* | 'instanceof' typeSpec )
                int alt60 = 2;
                final int LA60_0 = this.input.LA( 1 );
                if ( LA60_0 == JavaParser.RBRACK || (LA60_0 >= JavaParser.SEMI && LA60_0 <= JavaParser.COMMA) || (LA60_0 >= JavaParser.RPAREN && LA60_0 <= JavaParser.GE) ) {
                    alt60 = 1;
                } else if ( LA60_0 == 111 ) {
                    alt60 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "685:17: ( ( (LT|GT|LE|GE) shiftExpression )* | \'instanceof\' typeSpec )",
                                                                          60,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt60 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:685:25: ( (LT|GT|LE|GE) shiftExpression )*
                    {
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:685:25: ( (LT|GT|LE|GE) shiftExpression )*
                        loop59 : do {
                            int alt59 = 2;
                            final int LA59_0 = this.input.LA( 1 );
                            if ( (LA59_0 >= JavaParser.LT && LA59_0 <= JavaParser.GE) ) {
                                alt59 = 1;
                            }

                            switch ( alt59 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:685:33: (LT|GT|LE|GE) shiftExpression
                                {
                                    if ( (this.input.LA( 1 ) >= JavaParser.LT && this.input.LA( 1 ) <= JavaParser.GE) ) {
                                        this.input.consume();
                                        this.errorRecovery = false;
                                    } else {
                                        final MismatchedSetException mse = new MismatchedSetException( null,
                                                                                                 this.input );
                                        recoverFromMismatchedSet( this.input,
                                                                  mse,
                                                                  JavaParser.FOLLOW_set_in_relationalExpression2285 );
                                        throw mse;
                                    }

                                    this.following.push( JavaParser.FOLLOW_shiftExpression_in_relationalExpression2321 );
                                    shiftExpression();
                                    this.following.pop();

                                }
                                    break;

                                default :
                                    break loop59;
                            }
                        } while ( true );

                    }
                        break;
                    case 2 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:692:25: 'instanceof' typeSpec
                    {
                        match( this.input,
                               111,
                               JavaParser.FOLLOW_111_in_relationalExpression2333 );
                        this.following.push( JavaParser.FOLLOW_typeSpec_in_relationalExpression2335 );
                        typeSpec();
                        this.following.pop();

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end relationalExpression

    // $ANTLR start shiftExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:698:1: shiftExpression : additiveExpression ( (SL|SR|BSR) additiveExpression )* ;
    public void shiftExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:699:17: ( additiveExpression ( (SL|SR|BSR) additiveExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:699:17: additiveExpression ( (SL|SR|BSR) additiveExpression )*
            {
                this.following.push( JavaParser.FOLLOW_additiveExpression_in_shiftExpression2352 );
                additiveExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:699:36: ( (SL|SR|BSR) additiveExpression )*
                loop61 : do {
                    int alt61 = 2;
                    final int LA61_0 = this.input.LA( 1 );
                    if ( (LA61_0 >= JavaParser.SL && LA61_0 <= JavaParser.BSR) ) {
                        alt61 = 1;
                    }

                    switch ( alt61 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:699:37: (SL|SR|BSR) additiveExpression
                        {
                            if ( (this.input.LA( 1 ) >= JavaParser.SL && this.input.LA( 1 ) <= JavaParser.BSR) ) {
                                this.input.consume();
                                this.errorRecovery = false;
                            } else {
                                final MismatchedSetException mse = new MismatchedSetException( null,
                                                                                         this.input );
                                recoverFromMismatchedSet( this.input,
                                                          mse,
                                                          JavaParser.FOLLOW_set_in_shiftExpression2356 );
                                throw mse;
                            }

                            this.following.push( JavaParser.FOLLOW_additiveExpression_in_shiftExpression2367 );
                            additiveExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop61;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end shiftExpression

    // $ANTLR start additiveExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:704:1: additiveExpression : multiplicativeExpression ( (PLUS|MINUS) multiplicativeExpression )* ;
    public void additiveExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:705:17: ( multiplicativeExpression ( (PLUS|MINUS) multiplicativeExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:705:17: multiplicativeExpression ( (PLUS|MINUS) multiplicativeExpression )*
            {
                this.following.push( JavaParser.FOLLOW_multiplicativeExpression_in_additiveExpression2382 );
                multiplicativeExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:705:42: ( (PLUS|MINUS) multiplicativeExpression )*
                loop62 : do {
                    int alt62 = 2;
                    final int LA62_0 = this.input.LA( 1 );
                    if ( (LA62_0 >= JavaParser.PLUS && LA62_0 <= JavaParser.MINUS) ) {
                        alt62 = 1;
                    }

                    switch ( alt62 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:705:43: (PLUS|MINUS) multiplicativeExpression
                        {
                            if ( (this.input.LA( 1 ) >= JavaParser.PLUS && this.input.LA( 1 ) <= JavaParser.MINUS) ) {
                                this.input.consume();
                                this.errorRecovery = false;
                            } else {
                                final MismatchedSetException mse = new MismatchedSetException( null,
                                                                                         this.input );
                                recoverFromMismatchedSet( this.input,
                                                          mse,
                                                          JavaParser.FOLLOW_set_in_additiveExpression2386 );
                                throw mse;
                            }

                            this.following.push( JavaParser.FOLLOW_multiplicativeExpression_in_additiveExpression2393 );
                            multiplicativeExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop62;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end additiveExpression

    // $ANTLR start multiplicativeExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:710:1: multiplicativeExpression : unaryExpression ( (STAR|DIV|MOD) unaryExpression )* ;
    public void multiplicativeExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:711:17: ( unaryExpression ( (STAR|DIV|MOD) unaryExpression )* )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:711:17: unaryExpression ( (STAR|DIV|MOD) unaryExpression )*
            {
                this.following.push( JavaParser.FOLLOW_unaryExpression_in_multiplicativeExpression2408 );
                unaryExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:711:33: ( (STAR|DIV|MOD) unaryExpression )*
                loop63 : do {
                    int alt63 = 2;
                    final int LA63_0 = this.input.LA( 1 );
                    if ( LA63_0 == JavaParser.STAR || (LA63_0 >= JavaParser.DIV && LA63_0 <= JavaParser.MOD) ) {
                        alt63 = 1;
                    }

                    switch ( alt63 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:711:34: (STAR|DIV|MOD) unaryExpression
                        {
                            if ( this.input.LA( 1 ) == JavaParser.STAR || (this.input.LA( 1 ) >= JavaParser.DIV && this.input.LA( 1 ) <= JavaParser.MOD) ) {
                                this.input.consume();
                                this.errorRecovery = false;
                            } else {
                                final MismatchedSetException mse = new MismatchedSetException( null,
                                                                                         this.input );
                                recoverFromMismatchedSet( this.input,
                                                          mse,
                                                          JavaParser.FOLLOW_set_in_multiplicativeExpression2412 );
                                throw mse;
                            }

                            this.following.push( JavaParser.FOLLOW_unaryExpression_in_multiplicativeExpression2424 );
                            unaryExpression();
                            this.following.pop();

                        }
                            break;

                        default :
                            break loop63;
                    }
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end multiplicativeExpression

    // $ANTLR start unaryExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:714:1: unaryExpression : ( INC unaryExpression | DEC unaryExpression | MINUS unaryExpression | PLUS unaryExpression | unaryExpressionNotPlusMinus );
    public void unaryExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:715:17: ( INC unaryExpression | DEC unaryExpression | MINUS unaryExpression | PLUS unaryExpression | unaryExpressionNotPlusMinus )
            int alt64 = 5;
            switch ( this.input.LA( 1 ) ) {
                case INC :
                    alt64 = 1;
                    break;
                case DEC :
                    alt64 = 2;
                    break;
                case MINUS :
                    alt64 = 3;
                    break;
                case PLUS :
                    alt64 = 4;
                    break;
                case IDENT :
                case LPAREN :
                case BNOT :
                case LNOT :
                case NUM_INT :
                case CHAR_LITERAL :
                case STRING_LITERAL :
                case NUM_FLOAT :
                case 68 :
                case 69 :
                case 70 :
                case 71 :
                case 72 :
                case 73 :
                case 74 :
                case 75 :
                case 76 :
                case 93 :
                case 94 :
                case 112 :
                case 113 :
                case 114 :
                case 115 :
                    alt64 = 5;
                    break;
                default :
                    final NoViableAltException nvae = new NoViableAltException( "714:1: unaryExpression : ( INC unaryExpression | DEC unaryExpression | MINUS unaryExpression | PLUS unaryExpression | unaryExpressionNotPlusMinus );",
                                                                          64,
                                                                          0,
                                                                          this.input );

                    throw nvae;
            }

            switch ( alt64 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:715:17: INC unaryExpression
                {
                    match( this.input,
                           JavaParser.INC,
                           JavaParser.FOLLOW_INC_in_unaryExpression2437 );
                    this.following.push( JavaParser.FOLLOW_unaryExpression_in_unaryExpression2439 );
                    unaryExpression();
                    this.following.pop();

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:716:17: DEC unaryExpression
                {
                    match( this.input,
                           JavaParser.DEC,
                           JavaParser.FOLLOW_DEC_in_unaryExpression2444 );
                    this.following.push( JavaParser.FOLLOW_unaryExpression_in_unaryExpression2446 );
                    unaryExpression();
                    this.following.pop();

                }
                    break;
                case 3 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:717:17: MINUS unaryExpression
                {
                    match( this.input,
                           JavaParser.MINUS,
                           JavaParser.FOLLOW_MINUS_in_unaryExpression2451 );
                    this.following.push( JavaParser.FOLLOW_unaryExpression_in_unaryExpression2454 );
                    unaryExpression();
                    this.following.pop();

                }
                    break;
                case 4 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:718:17: PLUS unaryExpression
                {
                    match( this.input,
                           JavaParser.PLUS,
                           JavaParser.FOLLOW_PLUS_in_unaryExpression2459 );
                    this.following.push( JavaParser.FOLLOW_unaryExpression_in_unaryExpression2463 );
                    unaryExpression();
                    this.following.pop();

                }
                    break;
                case 5 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:719:17: unaryExpressionNotPlusMinus
                {
                    this.following.push( JavaParser.FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression2468 );
                    unaryExpressionNotPlusMinus();
                    this.following.pop();

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end unaryExpression

    // $ANTLR start unaryExpressionNotPlusMinus
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:722:1: unaryExpressionNotPlusMinus : ( BNOT unaryExpression | LNOT unaryExpression | LPAREN builtInTypeSpec RPAREN unaryExpression | LPAREN classTypeSpec RPAREN unaryExpressionNotPlusMinus | postfixExpression );
    public void unaryExpressionNotPlusMinus() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:723:17: ( BNOT unaryExpression | LNOT unaryExpression | LPAREN builtInTypeSpec RPAREN unaryExpression | LPAREN classTypeSpec RPAREN unaryExpressionNotPlusMinus | postfixExpression )
            int alt65 = 5;
            alt65 = this.dfa65.predict( this.input );
            switch ( alt65 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:723:17: BNOT unaryExpression
                {
                    match( this.input,
                           JavaParser.BNOT,
                           JavaParser.FOLLOW_BNOT_in_unaryExpressionNotPlusMinus2479 );
                    this.following.push( JavaParser.FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus2481 );
                    unaryExpression();
                    this.following.pop();

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:724:17: LNOT unaryExpression
                {
                    match( this.input,
                           JavaParser.LNOT,
                           JavaParser.FOLLOW_LNOT_in_unaryExpressionNotPlusMinus2486 );
                    this.following.push( JavaParser.FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus2488 );
                    unaryExpression();
                    this.following.pop();

                }
                    break;
                case 3 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:726:9: LPAREN builtInTypeSpec RPAREN unaryExpression
                {
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus2499 );
                    this.following.push( JavaParser.FOLLOW_builtInTypeSpec_in_unaryExpressionNotPlusMinus2501 );
                    builtInTypeSpec();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus2503 );
                    this.following.push( JavaParser.FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus2513 );
                    unaryExpression();
                    this.following.pop();

                }
                    break;
                case 4 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:732:9: LPAREN classTypeSpec RPAREN unaryExpressionNotPlusMinus
                {
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus2549 );
                    this.following.push( JavaParser.FOLLOW_classTypeSpec_in_unaryExpressionNotPlusMinus2551 );
                    classTypeSpec();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus2553 );
                    this.following.push( JavaParser.FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpressionNotPlusMinus2563 );
                    unaryExpressionNotPlusMinus();
                    this.following.pop();

                }
                    break;
                case 5 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:735:9: postfixExpression
                {
                    this.following.push( JavaParser.FOLLOW_postfixExpression_in_unaryExpressionNotPlusMinus2572 );
                    postfixExpression();
                    this.following.pop();

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end unaryExpressionNotPlusMinus

    // $ANTLR start postfixExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:739:1: postfixExpression : primaryExpression ( DOT IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT 'super' ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression | LBRACK expression RBRACK )* ( (INC|DEC))? ;
    public void postfixExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:740:13: ( primaryExpression ( DOT IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT 'super' ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression | LBRACK expression RBRACK )* ( (INC|DEC))? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:740:13: primaryExpression ( DOT IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT 'super' ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression | LBRACK expression RBRACK )* ( (INC|DEC))?
            {
                this.following.push( JavaParser.FOLLOW_primaryExpression_in_postfixExpression2586 );
                primaryExpression();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:741:17: ( DOT IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT 'super' ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression | LBRACK expression RBRACK )*
                loop69 : do {
                    int alt69 = 6;
                    final int LA69_0 = this.input.LA( 1 );
                    if ( LA69_0 == JavaParser.DOT ) {
                        switch ( this.input.LA( 2 ) ) {
                            case 93 :
                                alt69 = 2;
                                break;
                            case IDENT :
                                alt69 = 1;
                                break;
                            case 94 :
                                alt69 = 3;
                                break;
                            case 115 :
                                alt69 = 4;
                                break;

                        }

                    } else if ( LA69_0 == JavaParser.LBRACK ) {
                        alt69 = 5;
                    }

                    switch ( alt69 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:741:21: DOT IDENT ( LPAREN argList RPAREN )?
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_postfixExpression2594 );
                            match( this.input,
                                   JavaParser.IDENT,
                                   JavaParser.FOLLOW_IDENT_in_postfixExpression2596 );
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:742:25: ( LPAREN argList RPAREN )?
                            int alt66 = 2;
                            final int LA66_0 = this.input.LA( 1 );
                            if ( LA66_0 == JavaParser.LPAREN ) {
                                alt66 = 1;
                            } else if ( (LA66_0 >= JavaParser.LBRACK && LA66_0 <= JavaParser.RBRACK) || (LA66_0 >= JavaParser.DOT && LA66_0 <= JavaParser.STAR) || (LA66_0 >= JavaParser.SEMI && LA66_0 <= JavaParser.COMMA) || (LA66_0 >= JavaParser.RPAREN && LA66_0 <= JavaParser.DEC) || LA66_0 == 111 ) {
                                alt66 = 2;
                            } else {
                                final NoViableAltException nvae = new NoViableAltException( "742:25: ( LPAREN argList RPAREN )?",
                                                                                      66,
                                                                                      0,
                                                                                      this.input );

                                throw nvae;
                            }
                            switch ( alt66 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:742:33: LPAREN argList RPAREN
                                {
                                    match( this.input,
                                           JavaParser.LPAREN,
                                           JavaParser.FOLLOW_LPAREN_in_postfixExpression2603 );
                                    this.following.push( JavaParser.FOLLOW_argList_in_postfixExpression2610 );
                                    argList();
                                    this.following.pop();

                                    match( this.input,
                                           JavaParser.RPAREN,
                                           JavaParser.FOLLOW_RPAREN_in_postfixExpression2616 );

                                }
                                    break;

                            }

                        }
                            break;
                        case 2 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:746:25: DOT 'this'
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_postfixExpression2628 );
                            match( this.input,
                                   93,
                                   JavaParser.FOLLOW_93_in_postfixExpression2630 );

                        }
                            break;
                        case 3 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:748:25: DOT 'super' ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? )
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_postfixExpression2637 );
                            match( this.input,
                                   94,
                                   JavaParser.FOLLOW_94_in_postfixExpression2639 );
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:749:13: ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? )
                            int alt68 = 2;
                            final int LA68_0 = this.input.LA( 1 );
                            if ( LA68_0 == JavaParser.LPAREN ) {
                                alt68 = 1;
                            } else if ( LA68_0 == JavaParser.DOT ) {
                                alt68 = 2;
                            } else {
                                final NoViableAltException nvae = new NoViableAltException( "749:13: ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? )",
                                                                                      68,
                                                                                      0,
                                                                                      this.input );

                                throw nvae;
                            }
                            switch ( alt68 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:750:17: LPAREN argList RPAREN
                                {
                                    match( this.input,
                                           JavaParser.LPAREN,
                                           JavaParser.FOLLOW_LPAREN_in_postfixExpression2674 );
                                    this.following.push( JavaParser.FOLLOW_argList_in_postfixExpression2676 );
                                    argList();
                                    this.following.pop();

                                    match( this.input,
                                           JavaParser.RPAREN,
                                           JavaParser.FOLLOW_RPAREN_in_postfixExpression2678 );

                                }
                                    break;
                                case 2 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:752:29: DOT IDENT ( LPAREN argList RPAREN )?
                                {
                                    match( this.input,
                                           JavaParser.DOT,
                                           JavaParser.FOLLOW_DOT_in_postfixExpression2704 );
                                    match( this.input,
                                           JavaParser.IDENT,
                                           JavaParser.FOLLOW_IDENT_in_postfixExpression2706 );
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:753:17: ( LPAREN argList RPAREN )?
                                    int alt67 = 2;
                                    final int LA67_0 = this.input.LA( 1 );
                                    if ( LA67_0 == JavaParser.LPAREN ) {
                                        alt67 = 1;
                                    } else if ( (LA67_0 >= JavaParser.LBRACK && LA67_0 <= JavaParser.RBRACK) || (LA67_0 >= JavaParser.DOT && LA67_0 <= JavaParser.STAR) || (LA67_0 >= JavaParser.SEMI && LA67_0 <= JavaParser.COMMA) || (LA67_0 >= JavaParser.RPAREN && LA67_0 <= JavaParser.DEC) || LA67_0 == 111 ) {
                                        alt67 = 2;
                                    } else {
                                        final NoViableAltException nvae = new NoViableAltException( "753:17: ( LPAREN argList RPAREN )?",
                                                                                              67,
                                                                                              0,
                                                                                              this.input );

                                        throw nvae;
                                    }
                                    switch ( alt67 ) {
                                        case 1 :
                                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:753:25: LPAREN argList RPAREN
                                        {
                                            match( this.input,
                                                   JavaParser.LPAREN,
                                                   JavaParser.FOLLOW_LPAREN_in_postfixExpression2726 );
                                            this.following.push( JavaParser.FOLLOW_argList_in_postfixExpression2749 );
                                            argList();
                                            this.following.pop();

                                            match( this.input,
                                                   JavaParser.RPAREN,
                                                   JavaParser.FOLLOW_RPAREN_in_postfixExpression2771 );

                                        }
                                            break;

                                    }

                                }
                                    break;

                            }

                        }
                            break;
                        case 4 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:758:25: DOT newExpression
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_postfixExpression2810 );
                            this.following.push( JavaParser.FOLLOW_newExpression_in_postfixExpression2812 );
                            newExpression();
                            this.following.pop();

                        }
                            break;
                        case 5 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:759:25: LBRACK expression RBRACK
                        {
                            match( this.input,
                                   JavaParser.LBRACK,
                                   JavaParser.FOLLOW_LBRACK_in_postfixExpression2818 );
                            this.following.push( JavaParser.FOLLOW_expression_in_postfixExpression2821 );
                            expression();
                            this.following.pop();

                            match( this.input,
                                   JavaParser.RBRACK,
                                   JavaParser.FOLLOW_RBRACK_in_postfixExpression2823 );

                        }
                            break;

                        default :
                            break loop69;
                    }
                } while ( true );

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:762:17: ( (INC|DEC))?
                int alt70 = 2;
                final int LA70_0 = this.input.LA( 1 );
                if ( (LA70_0 >= JavaParser.INC && LA70_0 <= JavaParser.DEC) ) {
                    alt70 = 1;
                } else if ( LA70_0 == JavaParser.RBRACK || LA70_0 == JavaParser.STAR || (LA70_0 >= JavaParser.SEMI && LA70_0 <= JavaParser.COMMA) || (LA70_0 >= JavaParser.RPAREN && LA70_0 <= JavaParser.MOD) || LA70_0 == 111 ) {
                    alt70 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "762:17: ( (INC|DEC))?",
                                                                          70,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt70 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:764:25: (INC|DEC)
                    {
                        if ( (this.input.LA( 1 ) >= JavaParser.INC && this.input.LA( 1 ) <= JavaParser.DEC) ) {
                            this.input.consume();
                            this.errorRecovery = false;
                        } else {
                            final MismatchedSetException mse = new MismatchedSetException( null,
                                                                                     this.input );
                            recoverFromMismatchedSet( this.input,
                                                      mse,
                                                      JavaParser.FOLLOW_set_in_postfixExpression2854 );
                            throw mse;
                        }

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end postfixExpression

    // $ANTLR start primaryExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:770:1: primaryExpression : ( identPrimary ( options {greedy=true; } : DOT 'class' )? | constant | 'true' | 'false' | 'null' | newExpression | 'this' | 'super' | LPAREN assignmentExpression RPAREN | builtInType ( LBRACK RBRACK )* DOT 'class' );
    public void primaryExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:771:17: ( identPrimary ( options {greedy=true; } : DOT 'class' )? | constant | 'true' | 'false' | 'null' | newExpression | 'this' | 'super' | LPAREN assignmentExpression RPAREN | builtInType ( LBRACK RBRACK )* DOT 'class' )
            int alt73 = 10;
            switch ( this.input.LA( 1 ) ) {
                case IDENT :
                    alt73 = 1;
                    break;
                case NUM_INT :
                case CHAR_LITERAL :
                case STRING_LITERAL :
                case NUM_FLOAT :
                    alt73 = 2;
                    break;
                case 112 :
                    alt73 = 3;
                    break;
                case 113 :
                    alt73 = 4;
                    break;
                case 114 :
                    alt73 = 5;
                    break;
                case 115 :
                    alt73 = 6;
                    break;
                case 93 :
                    alt73 = 7;
                    break;
                case 94 :
                    alt73 = 8;
                    break;
                case LPAREN :
                    alt73 = 9;
                    break;
                case 68 :
                case 69 :
                case 70 :
                case 71 :
                case 72 :
                case 73 :
                case 74 :
                case 75 :
                case 76 :
                    alt73 = 10;
                    break;
                default :
                    final NoViableAltException nvae = new NoViableAltException( "770:1: primaryExpression : ( identPrimary ( options {greedy=true; } : DOT \'class\' )? | constant | \'true\' | \'false\' | \'null\' | newExpression | \'this\' | \'super\' | LPAREN assignmentExpression RPAREN | builtInType ( LBRACK RBRACK )* DOT \'class\' );",
                                                                          73,
                                                                          0,
                                                                          this.input );

                    throw nvae;
            }

            switch ( alt73 ) {
                case 1 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:771:17: identPrimary ( options {greedy=true; } : DOT 'class' )?
                {
                    this.following.push( JavaParser.FOLLOW_identPrimary_in_primaryExpression2881 );
                    identPrimary();
                    this.following.pop();

                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:771:30: ( options {greedy=true; } : DOT 'class' )?
                    int alt71 = 2;
                    final int LA71_0 = this.input.LA( 1 );
                    if ( LA71_0 == JavaParser.DOT ) {
                        final int LA71_1 = this.input.LA( 2 );
                        if ( LA71_1 == JavaParser.IDENT || (LA71_1 >= 93 && LA71_1 <= 94) || LA71_1 == 115 ) {
                            alt71 = 2;
                        } else if ( LA71_1 == 89 ) {
                            alt71 = 1;
                        } else {
                            final NoViableAltException nvae = new NoViableAltException( "771:30: ( options {greedy=true; } : DOT \'class\' )?",
                                                                                  71,
                                                                                  1,
                                                                                  this.input );

                            throw nvae;
                        }
                    } else if ( (LA71_0 >= JavaParser.LBRACK && LA71_0 <= JavaParser.RBRACK) || LA71_0 == JavaParser.STAR || (LA71_0 >= JavaParser.SEMI && LA71_0 <= JavaParser.COMMA) || (LA71_0 >= JavaParser.RPAREN && LA71_0 <= JavaParser.DEC) || LA71_0 == 111 ) {
                        alt71 = 2;
                    } else {
                        final NoViableAltException nvae = new NoViableAltException( "771:30: ( options {greedy=true; } : DOT \'class\' )?",
                                                                              71,
                                                                              0,
                                                                              this.input );

                        throw nvae;
                    }
                    switch ( alt71 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:771:56: DOT 'class'
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_primaryExpression2893 );
                            match( this.input,
                                   89,
                                   JavaParser.FOLLOW_89_in_primaryExpression2895 );

                        }
                            break;

                    }

                }
                    break;
                case 2 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:772:9: constant
                {
                    this.following.push( JavaParser.FOLLOW_constant_in_primaryExpression2908 );
                    constant();
                    this.following.pop();

                }
                    break;
                case 3 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:773:17: 'true'
                {
                    match( this.input,
                           112,
                           JavaParser.FOLLOW_112_in_primaryExpression2913 );

                }
                    break;
                case 4 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:774:17: 'false'
                {
                    match( this.input,
                           113,
                           JavaParser.FOLLOW_113_in_primaryExpression2918 );

                }
                    break;
                case 5 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:775:17: 'null'
                {
                    match( this.input,
                           114,
                           JavaParser.FOLLOW_114_in_primaryExpression2923 );

                }
                    break;
                case 6 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:776:9: newExpression
                {
                    this.following.push( JavaParser.FOLLOW_newExpression_in_primaryExpression2933 );
                    newExpression();
                    this.following.pop();

                }
                    break;
                case 7 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:777:17: 'this'
                {
                    match( this.input,
                           93,
                           JavaParser.FOLLOW_93_in_primaryExpression2938 );

                }
                    break;
                case 8 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:778:17: 'super'
                {
                    match( this.input,
                           94,
                           JavaParser.FOLLOW_94_in_primaryExpression2943 );

                }
                    break;
                case 9 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:779:17: LPAREN assignmentExpression RPAREN
                {
                    match( this.input,
                           JavaParser.LPAREN,
                           JavaParser.FOLLOW_LPAREN_in_primaryExpression2948 );
                    this.following.push( JavaParser.FOLLOW_assignmentExpression_in_primaryExpression2950 );
                    assignmentExpression();
                    this.following.pop();

                    match( this.input,
                           JavaParser.RPAREN,
                           JavaParser.FOLLOW_RPAREN_in_primaryExpression2952 );

                }
                    break;
                case 10 :
                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:781:17: builtInType ( LBRACK RBRACK )* DOT 'class'
                {
                    this.following.push( JavaParser.FOLLOW_builtInType_in_primaryExpression2960 );
                    builtInType();
                    this.following.pop();

                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:782:17: ( LBRACK RBRACK )*
                    loop72 : do {
                        int alt72 = 2;
                        final int LA72_0 = this.input.LA( 1 );
                        if ( LA72_0 == JavaParser.LBRACK ) {
                            alt72 = 1;
                        }

                        switch ( alt72 ) {
                            case 1 :
                                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:782:19: LBRACK RBRACK
                            {
                                match( this.input,
                                       JavaParser.LBRACK,
                                       JavaParser.FOLLOW_LBRACK_in_primaryExpression2966 );
                                match( this.input,
                                       JavaParser.RBRACK,
                                       JavaParser.FOLLOW_RBRACK_in_primaryExpression2969 );

                            }
                                break;

                            default :
                                break loop72;
                        }
                    } while ( true );

                    match( this.input,
                           JavaParser.DOT,
                           JavaParser.FOLLOW_DOT_in_primaryExpression2976 );
                    match( this.input,
                           89,
                           JavaParser.FOLLOW_89_in_primaryExpression2978 );

                }
                    break;

            }
        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end primaryExpression

    // $ANTLR start identPrimary
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:786:1: identPrimary : i= IDENT ( options {greedy=true; k=2; } : DOT IDENT )* ( options {greedy=true; } : ( LPAREN argList RPAREN ) | ( options {greedy=true; } : LBRACK RBRACK )+ )? ;
    public void identPrimary() throws RecognitionException {
        Token i = null;

        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:791:17: (i= IDENT ( options {greedy=true; k=2; } : DOT IDENT )* ( options {greedy=true; } : ( LPAREN argList RPAREN ) | ( options {greedy=true; } : LBRACK RBRACK )+ )? )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:791:17: i= IDENT ( options {greedy=true; k=2; } : DOT IDENT )* ( options {greedy=true; } : ( LPAREN argList RPAREN ) | ( options {greedy=true; } : LBRACK RBRACK )+ )?
            {
                i = (Token) this.input.LT( 1 );
                match( this.input,
                       JavaParser.IDENT,
                       JavaParser.FOLLOW_IDENT_in_identPrimary2993 );
                this.identifiers.add( i.getText() );
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:792:17: ( options {greedy=true; k=2; } : DOT IDENT )*
                loop74 : do {
                    int alt74 = 2;
                    final int LA74_0 = this.input.LA( 1 );
                    if ( LA74_0 == JavaParser.DOT ) {
                        final int LA74_3 = this.input.LA( 2 );
                        if ( LA74_3 == JavaParser.IDENT ) {
                            alt74 = 1;
                        }

                    }

                    switch ( alt74 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:796:25: DOT IDENT
                        {
                            match( this.input,
                                   JavaParser.DOT,
                                   JavaParser.FOLLOW_DOT_in_identPrimary3031 );
                            match( this.input,
                                   JavaParser.IDENT,
                                   JavaParser.FOLLOW_IDENT_in_identPrimary3033 );

                        }
                            break;

                        default :
                            break loop74;
                    }
                } while ( true );

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:798:17: ( options {greedy=true; } : ( LPAREN argList RPAREN ) | ( options {greedy=true; } : LBRACK RBRACK )+ )?
                int alt76 = 3;
                final int LA76_0 = this.input.LA( 1 );
                if ( LA76_0 == JavaParser.LPAREN ) {
                    alt76 = 1;
                } else if ( LA76_0 == JavaParser.LBRACK ) {
                    final int LA76_2 = this.input.LA( 2 );
                    if ( LA76_2 == JavaParser.RBRACK ) {
                        alt76 = 2;
                    }
                }
                switch ( alt76 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:804:21: ( LPAREN argList RPAREN )
                    {
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:804:21: ( LPAREN argList RPAREN )
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:804:23: LPAREN argList RPAREN
                        {
                            match( this.input,
                                   JavaParser.LPAREN,
                                   JavaParser.FOLLOW_LPAREN_in_identPrimary3095 );
                            this.following.push( JavaParser.FOLLOW_argList_in_identPrimary3098 );
                            argList();
                            this.following.pop();

                            match( this.input,
                                   JavaParser.RPAREN,
                                   JavaParser.FOLLOW_RPAREN_in_identPrimary3100 );

                        }

                    }
                        break;
                    case 2 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:805:25: ( options {greedy=true; } : LBRACK RBRACK )+
                    {
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:805:25: ( options {greedy=true; } : LBRACK RBRACK )+
                        int cnt75 = 0;
                        loop75 : do {
                            int alt75 = 2;
                            final int LA75_0 = this.input.LA( 1 );
                            if ( LA75_0 == JavaParser.LBRACK ) {
                                final int LA75_2 = this.input.LA( 2 );
                                if ( LA75_2 == JavaParser.RBRACK ) {
                                    alt75 = 1;
                                }

                            }

                            switch ( alt75 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:806:15: LBRACK RBRACK
                                {
                                    match( this.input,
                                           JavaParser.LBRACK,
                                           JavaParser.FOLLOW_LBRACK_in_identPrimary3133 );
                                    match( this.input,
                                           JavaParser.RBRACK,
                                           JavaParser.FOLLOW_RBRACK_in_identPrimary3136 );

                                }
                                    break;

                                default :
                                    if ( cnt75 >= 1 ) {
                                        break loop75;
                                    }
                                    final EarlyExitException eee = new EarlyExitException( 75,
                                                                                     this.input );
                                    throw eee;
                            }
                            cnt75++;
                        } while ( true );

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end identPrimary

    // $ANTLR start newExpression
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:811:1: newExpression : 'new' type ( LPAREN argList RPAREN ( classBlock )? | newArrayDeclarator ( arrayInitializer )? ) ;
    public void newExpression() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:861:17: ( 'new' type ( LPAREN argList RPAREN ( classBlock )? | newArrayDeclarator ( arrayInitializer )? ) )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:861:17: 'new' type ( LPAREN argList RPAREN ( classBlock )? | newArrayDeclarator ( arrayInitializer )? )
            {
                match( this.input,
                       115,
                       JavaParser.FOLLOW_115_in_newExpression3172 );
                this.following.push( JavaParser.FOLLOW_type_in_newExpression3174 );
                type();
                this.following.pop();

                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:862:17: ( LPAREN argList RPAREN ( classBlock )? | newArrayDeclarator ( arrayInitializer )? )
                int alt79 = 2;
                final int LA79_0 = this.input.LA( 1 );
                if ( LA79_0 == JavaParser.LPAREN ) {
                    alt79 = 1;
                } else if ( LA79_0 == JavaParser.LBRACK ) {
                    alt79 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "862:17: ( LPAREN argList RPAREN ( classBlock )? | newArrayDeclarator ( arrayInitializer )? )",
                                                                          79,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt79 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:862:25: LPAREN argList RPAREN ( classBlock )?
                    {
                        match( this.input,
                               JavaParser.LPAREN,
                               JavaParser.FOLLOW_LPAREN_in_newExpression3180 );
                        this.following.push( JavaParser.FOLLOW_argList_in_newExpression3182 );
                        argList();
                        this.following.pop();

                        match( this.input,
                               JavaParser.RPAREN,
                               JavaParser.FOLLOW_RPAREN_in_newExpression3184 );
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:862:47: ( classBlock )?
                        int alt77 = 2;
                        final int LA77_0 = this.input.LA( 1 );
                        if ( LA77_0 == JavaParser.LCURLY ) {
                            alt77 = 1;
                        } else if ( (LA77_0 >= JavaParser.LBRACK && LA77_0 <= JavaParser.RBRACK) || (LA77_0 >= JavaParser.DOT && LA77_0 <= JavaParser.STAR) || (LA77_0 >= JavaParser.SEMI && LA77_0 <= JavaParser.COMMA) || (LA77_0 >= JavaParser.RPAREN && LA77_0 <= JavaParser.DEC) || LA77_0 == 111 ) {
                            alt77 = 2;
                        } else {
                            final NoViableAltException nvae = new NoViableAltException( "862:47: ( classBlock )?",
                                                                                  77,
                                                                                  0,
                                                                                  this.input );

                            throw nvae;
                        }
                        switch ( alt77 ) {
                            case 1 :
                                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:862:48: classBlock
                            {
                                this.following.push( JavaParser.FOLLOW_classBlock_in_newExpression3187 );
                                classBlock();
                                this.following.pop();

                            }
                                break;

                        }

                    }
                        break;
                    case 2 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:872:25: newArrayDeclarator ( arrayInitializer )?
                    {
                        this.following.push( JavaParser.FOLLOW_newArrayDeclarator_in_newExpression3225 );
                        newArrayDeclarator();
                        this.following.pop();

                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:872:44: ( arrayInitializer )?
                        int alt78 = 2;
                        final int LA78_0 = this.input.LA( 1 );
                        if ( LA78_0 == JavaParser.LCURLY ) {
                            alt78 = 1;
                        } else if ( (LA78_0 >= JavaParser.LBRACK && LA78_0 <= JavaParser.RBRACK) || (LA78_0 >= JavaParser.DOT && LA78_0 <= JavaParser.STAR) || (LA78_0 >= JavaParser.SEMI && LA78_0 <= JavaParser.COMMA) || (LA78_0 >= JavaParser.RPAREN && LA78_0 <= JavaParser.DEC) || LA78_0 == 111 ) {
                            alt78 = 2;
                        } else {
                            final NoViableAltException nvae = new NoViableAltException( "872:44: ( arrayInitializer )?",
                                                                                  78,
                                                                                  0,
                                                                                  this.input );

                            throw nvae;
                        }
                        switch ( alt78 ) {
                            case 1 :
                                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:872:45: arrayInitializer
                            {
                                this.following.push( JavaParser.FOLLOW_arrayInitializer_in_newExpression3228 );
                                arrayInitializer();
                                this.following.pop();

                            }
                                break;

                        }

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end newExpression

    // $ANTLR start argList
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:876:1: argList : ( expressionList | ) ;
    public void argList() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:877:17: ( ( expressionList | ) )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:877:17: ( expressionList | )
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:877:17: ( expressionList | )
                int alt80 = 2;
                final int LA80_0 = this.input.LA( 1 );
                if ( LA80_0 == JavaParser.IDENT || LA80_0 == JavaParser.LPAREN || (LA80_0 >= JavaParser.PLUS && LA80_0 <= JavaParser.MINUS) || (LA80_0 >= JavaParser.INC && LA80_0 <= JavaParser.NUM_FLOAT) || (LA80_0 >= 68 && LA80_0 <= 76) || (LA80_0 >= 93 && LA80_0 <= 94) || (LA80_0 >= 112 && LA80_0 <= 115) ) {
                    alt80 = 1;
                } else if ( LA80_0 == JavaParser.RPAREN ) {
                    alt80 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException( "877:17: ( expressionList | )",
                                                                          80,
                                                                          0,
                                                                          this.input );

                    throw nvae;
                }
                switch ( alt80 ) {
                    case 1 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:877:25: expressionList
                    {
                        this.following.push( JavaParser.FOLLOW_expressionList_in_argList3247 );
                        expressionList();
                        this.following.pop();

                    }
                        break;
                    case 2 :
                        // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:880:17: 
                    {

                    }
                        break;

                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end argList

    // $ANTLR start newArrayDeclarator
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:883:1: newArrayDeclarator : ( options {k=1; } : LBRACK ( expression )? RBRACK )+ ;
    public void newArrayDeclarator() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:884:17: ( ( options {k=1; } : LBRACK ( expression )? RBRACK )+ )
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:884:17: ( options {k=1; } : LBRACK ( expression )? RBRACK )+
            {
                // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:884:17: ( options {k=1; } : LBRACK ( expression )? RBRACK )+
                int cnt82 = 0;
                loop82 : do {
                    int alt82 = 2;
                    final int LA82_0 = this.input.LA( 1 );
                    if ( LA82_0 == JavaParser.LBRACK ) {
                        alt82 = 1;
                    }

                    switch ( alt82 ) {
                        case 1 :
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:893:25: LBRACK ( expression )? RBRACK
                        {
                            match( this.input,
                                   JavaParser.LBRACK,
                                   JavaParser.FOLLOW_LBRACK_in_newArrayDeclarator3317 );
                            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:894:33: ( expression )?
                            int alt81 = 2;
                            final int LA81_0 = this.input.LA( 1 );
                            if ( LA81_0 == JavaParser.IDENT || LA81_0 == JavaParser.LPAREN || (LA81_0 >= JavaParser.PLUS && LA81_0 <= JavaParser.MINUS) || (LA81_0 >= JavaParser.INC && LA81_0 <= JavaParser.NUM_FLOAT) || (LA81_0 >= 68 && LA81_0 <= 76) || (LA81_0 >= 93 && LA81_0 <= 94)
                                 || (LA81_0 >= 112 && LA81_0 <= 115) ) {
                                alt81 = 1;
                            } else if ( LA81_0 == JavaParser.RBRACK ) {
                                alt81 = 2;
                            } else {
                                final NoViableAltException nvae = new NoViableAltException( "894:33: ( expression )?",
                                                                                      81,
                                                                                      0,
                                                                                      this.input );

                                throw nvae;
                            }
                            switch ( alt81 ) {
                                case 1 :
                                    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:894:34: expression
                                {
                                    this.following.push( JavaParser.FOLLOW_expression_in_newArrayDeclarator3325 );
                                    expression();
                                    this.following.pop();

                                }
                                    break;

                            }

                            match( this.input,
                                   JavaParser.RBRACK,
                                   JavaParser.FOLLOW_RBRACK_in_newArrayDeclarator3332 );

                        }
                            break;

                        default :
                            if ( cnt82 >= 1 ) {
                                break loop82;
                            }
                            final EarlyExitException eee = new EarlyExitException( 82,
                                                                             this.input );
                            throw eee;
                    }
                    cnt82++;
                } while ( true );

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end newArrayDeclarator

    // $ANTLR start constant
    // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:899:1: constant : (NUM_INT|CHAR_LITERAL|STRING_LITERAL|NUM_FLOAT);
    public void constant() throws RecognitionException {
        try {
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:900:9: ( (NUM_INT|CHAR_LITERAL|STRING_LITERAL|NUM_FLOAT))
            // /Users/bob/checkouts/jbossrules/drools-compiler/src/main/resources/org/drools/semantics/java/parser/java.g:900:17: (NUM_INT|CHAR_LITERAL|STRING_LITERAL|NUM_FLOAT)
            {
                if ( (this.input.LA( 1 ) >= JavaParser.NUM_INT && this.input.LA( 1 ) <= JavaParser.NUM_FLOAT) ) {
                    this.input.consume();
                    this.errorRecovery = false;
                } else {
                    final MismatchedSetException mse = new MismatchedSetException( null,
                                                                             this.input );
                    recoverFromMismatchedSet( this.input,
                                              mse,
                                              JavaParser.FOLLOW_set_in_constant3348 );
                    throw mse;
                }

            }

        } catch ( final RecognitionException re ) {
            reportError( re );
            recover( this.input,
                     re );
        } finally {
        }
        return;
    }

    // $ANTLR end constant

    protected DFA41 dfa41 = new DFA41();
    protected DFA45 dfa45 = new DFA45();
    protected DFA65 dfa65 = new DFA65();

    class DFA41 extends DFA {
        public int predict(final IntStream input) throws RecognitionException {
            return predict( input,
                            this.s0 );
        }

        DFA.State s1  = new DFA.State() {
                          {
                              this.alt = 1;
                          }
                      };
        DFA.State s32 = new DFA.State() {
                          {
                              this.alt = 16;
                          }
                      };
        DFA.State s19 = new DFA.State() {
                          {
                              this.alt = 4;
                          }
                      };
        DFA.State s35 = new DFA.State() {
                          {
                              this.alt = 2;
                          }
                      };
        DFA.State s30 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case 89 :
                                      return DFA41.this.s19;

                                  case 77 :
                                  case 78 :
                                  case 79 :
                                  case 80 :
                                  case 81 :
                                  case 82 :
                                  case 83 :
                                  case 84 :
                                  case 85 :
                                  case 86 :
                                  case 87 :
                                  case 88 :
                                      return DFA41.this.s30;

                                  case IDENT :
                                  case 68 :
                                  case 69 :
                                  case 70 :
                                  case 71 :
                                  case 72 :
                                  case 73 :
                                  case 74 :
                                  case 75 :
                                  case 76 :
                                      return DFA41.this.s35;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            30,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s2  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case LPAREN :
                                      return DFA41.this.s32;

                                  case 89 :
                                      return DFA41.this.s19;

                                  case 77 :
                                  case 78 :
                                  case 79 :
                                  case 80 :
                                  case 81 :
                                  case 82 :
                                  case 83 :
                                  case 84 :
                                  case 85 :
                                  case 86 :
                                  case 87 :
                                  case 88 :
                                      return DFA41.this.s30;

                                  case IDENT :
                                  case 68 :
                                  case 69 :
                                  case 70 :
                                  case 71 :
                                  case 72 :
                                  case 73 :
                                  case 74 :
                                  case 75 :
                                  case 76 :
                                      return DFA41.this.s35;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            2,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s37 = new DFA.State() {
                          {
                              this.alt = 5;
                          }
                      };
        DFA.State s5  = new DFA.State() {
                          {
                              this.alt = 3;
                          }
                      };
        DFA.State s69 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case DOT :
                                  case STAR :
                                  case SEMI :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA41.this.s5;

                                  case LBRACK :
                                      return DFA41.this.s39;

                                  case IDENT :
                                      return DFA41.this.s35;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            69,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s39 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA41_39 = input.LA( 1 );
                              if ( LA41_39 == JavaParser.RBRACK ) {
                                  return DFA41.this.s69;
                              }
                              if ( LA41_39 == JavaParser.IDENT || LA41_39 == JavaParser.LPAREN || (LA41_39 >= JavaParser.PLUS && LA41_39 <= JavaParser.MINUS) || (LA41_39 >= JavaParser.INC && LA41_39 <= JavaParser.NUM_FLOAT) || (LA41_39 >= 68 && LA41_39 <= 76) || (LA41_39 >= 93 && LA41_39 <= 94)
                                   || (LA41_39 >= 112 && LA41_39 <= 115) ) {
                                  return DFA41.this.s5;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    41,
                                                                                    39,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s65 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case STAR :
                                  case SEMI :
                                  case LPAREN :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA41.this.s5;

                                  case DOT :
                                      return DFA41.this.s38;

                                  case LBRACK :
                                      return DFA41.this.s39;

                                  case IDENT :
                                      return DFA41.this.s35;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            65,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s38 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA41_38 = input.LA( 1 );
                              if ( LA41_38 == 89 || (LA41_38 >= 93 && LA41_38 <= 94) || LA41_38 == 115 ) {
                                  return DFA41.this.s5;
                              }
                              if ( LA41_38 == JavaParser.IDENT ) {
                                  return DFA41.this.s65;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    41,
                                                                                    38,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s3  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case COLON :
                                      return DFA41.this.s37;

                                  case DOT :
                                      return DFA41.this.s38;

                                  case LBRACK :
                                      return DFA41.this.s39;

                                  case IDENT :
                                      return DFA41.this.s35;

                                  case STAR :
                                  case SEMI :
                                  case LPAREN :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA41.this.s5;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            3,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s86 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case IDENT :
                                      return DFA41.this.s35;

                                  case LBRACK :
                                      return DFA41.this.s57;

                                  case DOT :
                                      return DFA41.this.s5;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            86,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s57 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA41_57 = input.LA( 1 );
                              if ( LA41_57 == JavaParser.RBRACK ) {
                                  return DFA41.this.s86;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    41,
                                                                                    57,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s4  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case LBRACK :
                                      return DFA41.this.s57;

                                  case DOT :
                                      return DFA41.this.s5;

                                  case IDENT :
                                      return DFA41.this.s35;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            4,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s20 = new DFA.State() {
                          {
                              this.alt = 6;
                          }
                      };
        DFA.State s21 = new DFA.State() {
                          {
                              this.alt = 7;
                          }
                      };
        DFA.State s22 = new DFA.State() {
                          {
                              this.alt = 8;
                          }
                      };
        DFA.State s23 = new DFA.State() {
                          {
                              this.alt = 9;
                          }
                      };
        DFA.State s24 = new DFA.State() {
                          {
                              this.alt = 10;
                          }
                      };
        DFA.State s25 = new DFA.State() {
                          {
                              this.alt = 11;
                          }
                      };
        DFA.State s26 = new DFA.State() {
                          {
                              this.alt = 12;
                          }
                      };
        DFA.State s27 = new DFA.State() {
                          {
                              this.alt = 13;
                          }
                      };
        DFA.State s28 = new DFA.State() {
                          {
                              this.alt = 14;
                          }
                      };
        DFA.State s29 = new DFA.State() {
                          {
                              this.alt = 15;
                          }
                      };
        DFA.State s31 = new DFA.State() {
                          {
                              this.alt = 17;
                          }
                      };
        DFA.State s0  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case LCURLY :
                                      return DFA41.this.s1;

                                  case 86 :
                                      return DFA41.this.s2;

                                  case IDENT :
                                      return DFA41.this.s3;

                                  case 68 :
                                  case 69 :
                                  case 70 :
                                  case 71 :
                                  case 72 :
                                  case 73 :
                                  case 74 :
                                  case 75 :
                                  case 76 :
                                      return DFA41.this.s4;

                                  case LPAREN :
                                  case PLUS :
                                  case MINUS :
                                  case INC :
                                  case DEC :
                                  case BNOT :
                                  case LNOT :
                                  case NUM_INT :
                                  case CHAR_LITERAL :
                                  case STRING_LITERAL :
                                  case NUM_FLOAT :
                                  case 93 :
                                  case 94 :
                                  case 112 :
                                  case 113 :
                                  case 114 :
                                  case 115 :
                                      return DFA41.this.s5;

                                  case 89 :
                                      return DFA41.this.s19;

                                  case 96 :
                                      return DFA41.this.s20;

                                  case 98 :
                                      return DFA41.this.s21;

                                  case 99 :
                                      return DFA41.this.s22;

                                  case 100 :
                                      return DFA41.this.s23;

                                  case 101 :
                                      return DFA41.this.s24;

                                  case 102 :
                                      return DFA41.this.s25;

                                  case 103 :
                                      return DFA41.this.s26;

                                  case 104 :
                                      return DFA41.this.s27;

                                  case 108 :
                                      return DFA41.this.s28;

                                  case 105 :
                                      return DFA41.this.s29;

                                  case 77 :
                                  case 78 :
                                  case 79 :
                                  case 80 :
                                  case 81 :
                                  case 82 :
                                  case 83 :
                                  case 84 :
                                  case 85 :
                                  case 87 :
                                  case 88 :
                                      return DFA41.this.s30;

                                  case SEMI :
                                      return DFA41.this.s31;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            41,
                                                                                            0,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };

    }

    class DFA45 extends DFA {
        public int predict(final IntStream input) throws RecognitionException {
            return predict( input,
                            this.s0 );
        }

        DFA.State s1  = new DFA.State() {
                          {
                              this.alt = 1;
                          }
                      };
        DFA.State s4  = new DFA.State() {
                          {
                              this.alt = 2;
                          }
                      };
        DFA.State s47 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case IDENT :
                                      return DFA45.this.s1;

                                  case LBRACK :
                                      return DFA45.this.s20;

                                  case DOT :
                                  case STAR :
                                  case SEMI :
                                  case COMMA :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA45.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            45,
                                                                                            47,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s20 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA45_20 = input.LA( 1 );
                              if ( LA45_20 == JavaParser.RBRACK ) {
                                  return DFA45.this.s47;
                              }
                              if ( LA45_20 == JavaParser.IDENT || LA45_20 == JavaParser.LPAREN || (LA45_20 >= JavaParser.PLUS && LA45_20 <= JavaParser.MINUS) || (LA45_20 >= JavaParser.INC && LA45_20 <= JavaParser.NUM_FLOAT) || (LA45_20 >= 68 && LA45_20 <= 76) || (LA45_20 >= 93 && LA45_20 <= 94)
                                   || (LA45_20 >= 112 && LA45_20 <= 115) ) {
                                  return DFA45.this.s4;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    45,
                                                                                    20,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s44 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case STAR :
                                  case SEMI :
                                  case COMMA :
                                  case LPAREN :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA45.this.s4;

                                  case DOT :
                                      return DFA45.this.s19;

                                  case LBRACK :
                                      return DFA45.this.s20;

                                  case IDENT :
                                      return DFA45.this.s1;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            45,
                                                                                            44,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s19 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA45_19 = input.LA( 1 );
                              if ( LA45_19 == 89 || (LA45_19 >= 93 && LA45_19 <= 94) || LA45_19 == 115 ) {
                                  return DFA45.this.s4;
                              }
                              if ( LA45_19 == JavaParser.IDENT ) {
                                  return DFA45.this.s44;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    45,
                                                                                    19,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s2  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case DOT :
                                      return DFA45.this.s19;

                                  case LBRACK :
                                      return DFA45.this.s20;

                                  case IDENT :
                                      return DFA45.this.s1;

                                  case STAR :
                                  case SEMI :
                                  case COMMA :
                                  case LPAREN :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA45.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            45,
                                                                                            2,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s64 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case IDENT :
                                      return DFA45.this.s1;

                                  case LBRACK :
                                      return DFA45.this.s39;

                                  case DOT :
                                      return DFA45.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            45,
                                                                                            64,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s39 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA45_39 = input.LA( 1 );
                              if ( LA45_39 == JavaParser.RBRACK ) {
                                  return DFA45.this.s64;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    45,
                                                                                    39,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s3  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case LBRACK :
                                      return DFA45.this.s39;

                                  case DOT :
                                      return DFA45.this.s4;

                                  case IDENT :
                                      return DFA45.this.s1;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            45,
                                                                                            3,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s18 = new DFA.State() {
                          {
                              this.alt = 3;
                          }
                      };
        DFA.State s0  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case 77 :
                                  case 78 :
                                  case 79 :
                                  case 80 :
                                  case 81 :
                                  case 82 :
                                  case 83 :
                                  case 84 :
                                  case 85 :
                                  case 86 :
                                  case 87 :
                                  case 88 :
                                      return DFA45.this.s1;

                                  case IDENT :
                                      return DFA45.this.s2;

                                  case 68 :
                                  case 69 :
                                  case 70 :
                                  case 71 :
                                  case 72 :
                                  case 73 :
                                  case 74 :
                                  case 75 :
                                  case 76 :
                                      return DFA45.this.s3;

                                  case LPAREN :
                                  case PLUS :
                                  case MINUS :
                                  case INC :
                                  case DEC :
                                  case BNOT :
                                  case LNOT :
                                  case NUM_INT :
                                  case CHAR_LITERAL :
                                  case STRING_LITERAL :
                                  case NUM_FLOAT :
                                  case 93 :
                                  case 94 :
                                  case 112 :
                                  case 113 :
                                  case 114 :
                                  case 115 :
                                      return DFA45.this.s4;

                                  case SEMI :
                                      return DFA45.this.s18;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            45,
                                                                                            0,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };

    }

    class DFA65 extends DFA {
        public int predict(final IntStream input) throws RecognitionException {
            return predict( input,
                            this.s0 );
        }

        DFA.State s1  = new DFA.State() {
                          {
                              this.alt = 1;
                          }
                      };
        DFA.State s2  = new DFA.State() {
                          {
                              this.alt = 2;
                          }
                      };
        DFA.State s31 = new DFA.State() {
                          {
                              this.alt = 3;
                          }
                      };
        DFA.State s4  = new DFA.State() {
                          {
                              this.alt = 5;
                          }
                      };
        DFA.State s50 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case RPAREN :
                                      return DFA65.this.s31;

                                  case LBRACK :
                                      return DFA65.this.s29;

                                  case DOT :
                                      return DFA65.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            65,
                                                                                            50,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s29 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA65_29 = input.LA( 1 );
                              if ( LA65_29 == JavaParser.RBRACK ) {
                                  return DFA65.this.s50;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    65,
                                                                                    29,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s13 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case LBRACK :
                                      return DFA65.this.s29;

                                  case DOT :
                                      return DFA65.this.s4;

                                  case RPAREN :
                                      return DFA65.this.s31;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            65,
                                                                                            13,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s73 = new DFA.State() {
                          {
                              this.alt = 4;
                          }
                      };
        DFA.State s34 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA65_34 = input.LA( 1 );
                              if ( LA65_34 == JavaParser.IDENT || LA65_34 == JavaParser.LPAREN || (LA65_34 >= JavaParser.BNOT && LA65_34 <= JavaParser.NUM_FLOAT) || (LA65_34 >= 68 && LA65_34 <= 76) || (LA65_34 >= 93 && LA65_34 <= 94) || (LA65_34 >= 112 && LA65_34 <= 115) ) {
                                  return DFA65.this.s73;
                              }
                              if ( (LA65_34 >= JavaParser.LBRACK && LA65_34 <= JavaParser.RBRACK) || (LA65_34 >= JavaParser.DOT && LA65_34 <= JavaParser.STAR) || (LA65_34 >= JavaParser.SEMI && LA65_34 <= JavaParser.COMMA) || (LA65_34 >= JavaParser.RPAREN && LA65_34 <= JavaParser.DEC) || LA65_34 == 111 ) {
                                  return DFA65.this.s4;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    65,
                                                                                    34,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s56 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case RPAREN :
                                      return DFA65.this.s34;

                                  case LBRACK :
                                      return DFA65.this.s33;

                                  case DOT :
                                  case STAR :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA65.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            65,
                                                                                            56,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s33 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA65_33 = input.LA( 1 );
                              if ( LA65_33 == JavaParser.RBRACK ) {
                                  return DFA65.this.s56;
                              }
                              if ( LA65_33 == JavaParser.IDENT || LA65_33 == JavaParser.LPAREN || (LA65_33 >= JavaParser.PLUS && LA65_33 <= JavaParser.MINUS) || (LA65_33 >= JavaParser.INC && LA65_33 <= JavaParser.NUM_FLOAT) || (LA65_33 >= 68 && LA65_33 <= 76) || (LA65_33 >= 93 && LA65_33 <= 94)
                                   || (LA65_33 >= 112 && LA65_33 <= 115) ) {
                                  return DFA65.this.s4;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    65,
                                                                                    33,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s52 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case LBRACK :
                                      return DFA65.this.s33;

                                  case RPAREN :
                                      return DFA65.this.s34;

                                  case DOT :
                                      return DFA65.this.s32;

                                  case STAR :
                                  case LPAREN :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA65.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            65,
                                                                                            52,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s32 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              int LA65_32 = input.LA( 1 );
                              if ( LA65_32 == 89 || (LA65_32 >= 93 && LA65_32 <= 94) || LA65_32 == 115 ) {
                                  return DFA65.this.s4;
                              }
                              if ( LA65_32 == JavaParser.IDENT ) {
                                  return DFA65.this.s52;
                              }

                              NoViableAltException nvae = new NoViableAltException( "",
                                                                                    65,
                                                                                    32,
                                                                                    input );

                              throw nvae;
                          }
                      };
        DFA.State s14 = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case DOT :
                                      return DFA65.this.s32;

                                  case LBRACK :
                                      return DFA65.this.s33;

                                  case RPAREN :
                                      return DFA65.this.s34;

                                  case STAR :
                                  case LPAREN :
                                  case ASSIGN :
                                  case PLUS_ASSIGN :
                                  case MINUS_ASSIGN :
                                  case STAR_ASSIGN :
                                  case DIV_ASSIGN :
                                  case MOD_ASSIGN :
                                  case SR_ASSIGN :
                                  case BSR_ASSIGN :
                                  case SL_ASSIGN :
                                  case BAND_ASSIGN :
                                  case BXOR_ASSIGN :
                                  case BOR_ASSIGN :
                                  case QUESTION :
                                  case LOR :
                                  case LAND :
                                  case BOR :
                                  case BXOR :
                                  case BAND :
                                  case NOT_EQUAL :
                                  case EQUAL :
                                  case LT :
                                  case GT :
                                  case LE :
                                  case GE :
                                  case SL :
                                  case SR :
                                  case BSR :
                                  case PLUS :
                                  case MINUS :
                                  case DIV :
                                  case MOD :
                                  case INC :
                                  case DEC :
                                  case 111 :
                                      return DFA65.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            65,
                                                                                            14,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s3  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case 68 :
                                  case 69 :
                                  case 70 :
                                  case 71 :
                                  case 72 :
                                  case 73 :
                                  case 74 :
                                  case 75 :
                                  case 76 :
                                      return DFA65.this.s13;

                                  case IDENT :
                                      return DFA65.this.s14;

                                  case LPAREN :
                                  case PLUS :
                                  case MINUS :
                                  case INC :
                                  case DEC :
                                  case BNOT :
                                  case LNOT :
                                  case NUM_INT :
                                  case CHAR_LITERAL :
                                  case STRING_LITERAL :
                                  case NUM_FLOAT :
                                  case 93 :
                                  case 94 :
                                  case 112 :
                                  case 113 :
                                  case 114 :
                                  case 115 :
                                      return DFA65.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            65,
                                                                                            3,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };
        DFA.State s0  = new DFA.State() {
                          public DFA.State transition(IntStream input) throws RecognitionException {
                              switch ( input.LA( 1 ) ) {
                                  case BNOT :
                                      return DFA65.this.s1;

                                  case LNOT :
                                      return DFA65.this.s2;

                                  case LPAREN :
                                      return DFA65.this.s3;

                                  case IDENT :
                                  case NUM_INT :
                                  case CHAR_LITERAL :
                                  case STRING_LITERAL :
                                  case NUM_FLOAT :
                                  case 68 :
                                  case 69 :
                                  case 70 :
                                  case 71 :
                                  case 72 :
                                  case 73 :
                                  case 74 :
                                  case 75 :
                                  case 76 :
                                  case 93 :
                                  case 94 :
                                  case 112 :
                                  case 113 :
                                  case 114 :
                                  case 115 :
                                      return DFA65.this.s4;

                                  default :
                                      NoViableAltException nvae = new NoViableAltException( "",
                                                                                            65,
                                                                                            0,
                                                                                            input );

                                      throw nvae;
                              }
                          }
                      };

    }

    public static final BitSet FOLLOW_modifiers_in_declaration59                                     = new BitSet( new long[]{0x0000000000000040L, 0x0000000000001FF0L} );
    public static final BitSet FOLLOW_typeSpec_in_declaration61                                      = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_variableDefinitions_in_declaration63                           = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_classTypeSpec_in_typeSpec79                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_builtInTypeSpec_in_typeSpec84                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_identifier_in_classTypeSpec97                                  = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_LBRACK_in_classTypeSpec100                                     = new BitSet( new long[]{0x0000000000000020L} );
    public static final BitSet FOLLOW_RBRACK_in_classTypeSpec103                                     = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_builtInType_in_builtInTypeSpec118                              = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_LBRACK_in_builtInTypeSpec121                                   = new BitSet( new long[]{0x0000000000000020L} );
    public static final BitSet FOLLOW_RBRACK_in_builtInTypeSpec124                                   = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_identifier_in_type139                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_builtInType_in_type144                                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_set_in_builtInType156                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_IDENT_in_identifier209                                         = new BitSet( new long[]{0x0000000000000082L} );
    public static final BitSet FOLLOW_DOT_in_identifier214                                           = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_identifier216                                         = new BitSet( new long[]{0x0000000000000082L} );
    public static final BitSet FOLLOW_IDENT_in_identifierStar230                                     = new BitSet( new long[]{0x0000000000000082L} );
    public static final BitSet FOLLOW_DOT_in_identifierStar236                                       = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_identifierStar238                                     = new BitSet( new long[]{0x0000000000000082L} );
    public static final BitSet FOLLOW_DOT_in_identifierStar247                                       = new BitSet( new long[]{0x0000000000000100L} );
    public static final BitSet FOLLOW_STAR_in_identifierStar249                                      = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_modifier_in_modifiers270                                       = new BitSet( new long[]{0x0000000000000002L, 0x0000000001FFE000L} );
    public static final BitSet FOLLOW_set_in_modifier288                                             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_89_in_classDefinition356                                       = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_classDefinition358                                    = new BitSet( new long[]{0x0000000000000002L, 0x0000000004000000L} );
    public static final BitSet FOLLOW_superClassClause_in_classDefinition365                         = new BitSet( new long[]{0x0000000000000002L, 0x0000000010000000L} );
    public static final BitSet FOLLOW_implementsClause_in_classDefinition372                         = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_classBlock_in_classDefinition379                               = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_90_in_superClassClause392                                      = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_identifier_in_superClassClause394                              = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_91_in_interfaceDefinition412                                   = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_interfaceDefinition414                                = new BitSet( new long[]{0x0000000000000002L, 0x0000000004000000L} );
    public static final BitSet FOLLOW_interfaceExtends_in_interfaceDefinition421                     = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_classBlock_in_interfaceDefinition428                           = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LCURLY_in_classBlock442                                        = new BitSet( new long[]{0x0000000000000E02L, 0x0000000001FFE000L} );
    public static final BitSet FOLLOW_field_in_classBlock449                                         = new BitSet( new long[]{0x0000000000000E02L, 0x0000000001FFE000L} );
    public static final BitSet FOLLOW_SEMI_in_classBlock453                                          = new BitSet( new long[]{0x0000000000000E02L, 0x0000000001FFE000L} );
    public static final BitSet FOLLOW_RCURLY_in_classBlock460                                        = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_90_in_interfaceExtends479                                      = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_identifier_in_interfaceExtends483                              = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_COMMA_in_interfaceExtends487                                   = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_identifier_in_interfaceExtends489                              = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_92_in_implementsClause514                                      = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_identifier_in_implementsClause516                              = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_COMMA_in_implementsClause520                                   = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_identifier_in_implementsClause522                              = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_modifiers_in_field548                                          = new BitSet( new long[]{0x0000000000000040L, 0x000000000A001FF0L} );
    public static final BitSet FOLLOW_ctorHead_in_field554                                           = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_constructorBody_in_field556                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_classDefinition_in_field568                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_interfaceDefinition_in_field586                                = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_typeSpec_in_field600                                           = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_field609                                              = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_field623                                             = new BitSet( new long[]{0x0000000000000002L, 0x0000000000040000L} );
    public static final BitSet FOLLOW_parameterDeclarationList_in_field625                           = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_field627                                             = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_declaratorBrackets_in_field634                                 = new BitSet( new long[]{0x0000000000000600L, 0x0000000080000000L} );
    public static final BitSet FOLLOW_throwsClause_in_field652                                       = new BitSet( new long[]{0x0000000000000600L} );
    public static final BitSet FOLLOW_compoundStatement_in_field663                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_SEMI_in_field667                                               = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_variableDefinitions_in_field676                                = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_field678                                               = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_80_in_field704                                                 = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_compoundStatement_in_field706                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_compoundStatement_in_field720                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LCURLY_in_constructorBody739                                   = new BitSet( new long[]{0x007F980000002E42L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_explicitConstructorInvocation_in_constructorBody765            = new BitSet( new long[]{0x007F980000002E42L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_constructorBody782                                = new BitSet( new long[]{0x007F980000002E42L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_RCURLY_in_constructorBody794                                   = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_93_in_explicitConstructorInvocation815                         = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_explicitConstructorInvocation817                     = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_argList_in_explicitConstructorInvocation819                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_explicitConstructorInvocation821                     = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation823                       = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_94_in_explicitConstructorInvocation836                         = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_explicitConstructorInvocation838                     = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_argList_in_explicitConstructorInvocation840                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_explicitConstructorInvocation842                     = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation844                       = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_variableDeclarator_in_variableDefinitions861                   = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_COMMA_in_variableDefinitions867                                = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_variableDeclarator_in_variableDefinitions872                   = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_IDENT_in_variableDeclarator890                                 = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_declaratorBrackets_in_variableDeclarator892                    = new BitSet( new long[]{0x0000000000008002L} );
    public static final BitSet FOLLOW_varInitializer_in_variableDeclarator894                        = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LBRACK_in_declaratorBrackets912                                = new BitSet( new long[]{0x0000000000000020L} );
    public static final BitSet FOLLOW_RBRACK_in_declaratorBrackets915                                = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_ASSIGN_in_varInitializer930                                    = new BitSet( new long[]{0x007F980000002240L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_initializer_in_varInitializer932                               = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LCURLY_in_arrayInitializer947                                  = new BitSet( new long[]{0x007F980000002A40L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_initializer_in_arrayInitializer955                             = new BitSet( new long[]{0x0000000000001800L} );
    public static final BitSet FOLLOW_COMMA_in_arrayInitializer992                                   = new BitSet( new long[]{0x007F980000002240L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_initializer_in_arrayInitializer994                             = new BitSet( new long[]{0x0000000000001800L} );
    public static final BitSet FOLLOW_COMMA_in_arrayInitializer1008                                  = new BitSet( new long[]{0x0000000000000800L} );
    public static final BitSet FOLLOW_RCURLY_in_arrayInitializer1020                                 = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_expression_in_initializer1034                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_arrayInitializer_in_initializer1039                            = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_IDENT_in_ctorHead1053                                          = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_ctorHead1063                                         = new BitSet( new long[]{0x0000000000000002L, 0x0000000000040000L} );
    public static final BitSet FOLLOW_parameterDeclarationList_in_ctorHead1065                       = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_ctorHead1067                                         = new BitSet( new long[]{0x0000000000000002L, 0x0000000080000000L} );
    public static final BitSet FOLLOW_throwsClause_in_ctorHead1076                                   = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_95_in_throwsClause1090                                         = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_identifier_in_throwsClause1092                                 = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_COMMA_in_throwsClause1096                                      = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_identifier_in_throwsClause1098                                 = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_parameterDeclaration_in_parameterDeclarationList1116           = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_COMMA_in_parameterDeclarationList1120                          = new BitSet( new long[]{0x0000000000000002L, 0x0000000000040000L} );
    public static final BitSet FOLLOW_parameterDeclaration_in_parameterDeclarationList1122           = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_parameterModifier_in_parameterDeclaration1140                  = new BitSet( new long[]{0x0000000000000040L, 0x0000000000001FF0L} );
    public static final BitSet FOLLOW_typeSpec_in_parameterDeclaration1142                           = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_parameterDeclaration1144                              = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_declaratorBrackets_in_parameterDeclaration1148                 = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_82_in_parameterModifier1160                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LCURLY_in_compoundStatement1185                                = new BitSet( new long[]{0x007F980000002E42L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_compoundStatement1196                             = new BitSet( new long[]{0x007F980000002E42L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_RCURLY_in_compoundStatement1202                                = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_compoundStatement_in_statement1216                             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_declaration_in_statement1232                                   = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1234                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_expression_in_statement1246                                    = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1248                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_modifiers_in_statement1256                                     = new BitSet( new long[]{0x0000000000000000L, 0x0000000002000000L} );
    public static final BitSet FOLLOW_classDefinition_in_statement1258                               = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_IDENT_in_statement1266                                         = new BitSet( new long[]{0x0000000000010000L} );
    public static final BitSet FOLLOW_COLON_in_statement1268                                         = new BitSet( new long[]{0x007F980000002642L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_statement1271                                     = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_96_in_statement1279                                            = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_statement1281                                        = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_statement1283                                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_statement1285                                        = new BitSet( new long[]{0x007F980000002642L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_statement1287                                     = new BitSet( new long[]{0x0000000000000002L, 0x0000000200000000L} );
    public static final BitSet FOLLOW_97_in_statement1308                                            = new BitSet( new long[]{0x007F980000002642L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_statement1310                                     = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_98_in_statement1323                                            = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_statement1328                                        = new BitSet( new long[]{0x007F980000002042L, 0x000F000061FFFFF0L} );
    public static final BitSet FOLLOW_forInit_in_statement1334                                       = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1336                                          = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_forCond_in_statement1345                                       = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1347                                          = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_forIter_in_statement1356                                       = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_statement1370                                        = new BitSet( new long[]{0x007F980000002642L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_statement1375                                     = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_99_in_statement1404                                            = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_statement1406                                        = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_statement1408                                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_statement1410                                        = new BitSet( new long[]{0x007F980000002642L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_statement1412                                     = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_100_in_statement1420                                           = new BitSet( new long[]{0x007F980000002642L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_statement_in_statement1422                                     = new BitSet( new long[]{0x0000000000000000L, 0x0000000800000000L} );
    public static final BitSet FOLLOW_99_in_statement1424                                            = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_statement1426                                        = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_statement1428                                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_statement1430                                        = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1432                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_101_in_statement1440                                           = new BitSet( new long[]{0x0000000000000440L} );
    public static final BitSet FOLLOW_IDENT_in_statement1443                                         = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1447                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_102_in_statement1455                                           = new BitSet( new long[]{0x0000000000000440L} );
    public static final BitSet FOLLOW_IDENT_in_statement1458                                         = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1462                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_103_in_statement1470                                           = new BitSet( new long[]{0x007F980000002440L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_statement1473                                    = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1477                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_104_in_statement1485                                           = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_statement1487                                        = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_statement1489                                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_statement1491                                        = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_LCURLY_in_statement1493                                        = new BitSet( new long[]{0x0000000000000800L, 0x00000C0000000000L} );
    public static final BitSet FOLLOW_casesGroup_in_statement1500                                    = new BitSet( new long[]{0x0000000000000800L, 0x00000C0000000000L} );
    public static final BitSet FOLLOW_RCURLY_in_statement1507                                        = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_tryBlock_in_statement1515                                      = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_105_in_statement1523                                           = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_statement1525                                    = new BitSet( new long[]{0x0000000000000400L} );
    public static final BitSet FOLLOW_SEMI_in_statement1527                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_86_in_statement1535                                            = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_statement1537                                        = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_statement1539                                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_statement1541                                        = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_compoundStatement_in_statement1543                             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_SEMI_in_statement1556                                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_aCase_in_casesGroup1602                                        = new BitSet( new long[]{0x007F980000002642L, 0x000F1FFD61FFFFF0L} );
    public static final BitSet FOLLOW_caseSList_in_casesGroup1611                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_106_in_aCase1626                                               = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_aCase1628                                        = new BitSet( new long[]{0x0000000000010000L} );
    public static final BitSet FOLLOW_107_in_aCase1632                                               = new BitSet( new long[]{0x0000000000010000L} );
    public static final BitSet FOLLOW_COLON_in_aCase1635                                             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_statement_in_caseSList1647                                     = new BitSet( new long[]{0x007F980000002642L, 0x000F13FD61FFFFF0L} );
    public static final BitSet FOLLOW_declaration_in_forInit1678                                     = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_expressionList_in_forInit1687                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_expression_in_forCond1707                                      = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_expressionList_in_forIter1724                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_108_in_tryBlock1741                                            = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_compoundStatement_in_tryBlock1743                              = new BitSet( new long[]{0x0000000000000002L, 0x0000600000000000L} );
    public static final BitSet FOLLOW_handler_in_tryBlock1748                                        = new BitSet( new long[]{0x0000000000000002L, 0x0000600000000000L} );
    public static final BitSet FOLLOW_finallyClause_in_tryBlock1756                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_109_in_finallyClause1770                                       = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_compoundStatement_in_finallyClause1772                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_110_in_handler1784                                             = new BitSet( new long[]{0x0000000000002000L} );
    public static final BitSet FOLLOW_LPAREN_in_handler1786                                          = new BitSet( new long[]{0x0000000000000002L, 0x0000000000040000L} );
    public static final BitSet FOLLOW_parameterDeclaration_in_handler1788                            = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_handler1790                                          = new BitSet( new long[]{0x0000000000000200L} );
    public static final BitSet FOLLOW_compoundStatement_in_handler1792                               = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_assignmentExpression_in_expression1839                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_expression_in_expressionList1855                               = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_COMMA_in_expressionList1858                                    = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_expressionList1860                               = new BitSet( new long[]{0x0000000000001002L} );
    public static final BitSet FOLLOW_conditionalExpression_in_assignmentExpression1878              = new BitSet( new long[]{0x000000000FFE8002L} );
    public static final BitSet FOLLOW_set_in_assignmentExpression1886                                = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression2103               = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_logicalOrExpression_in_conditionalExpression2121               = new BitSet( new long[]{0x0000000010000002L} );
    public static final BitSet FOLLOW_QUESTION_in_conditionalExpression2127                          = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_assignmentExpression_in_conditionalExpression2129              = new BitSet( new long[]{0x0000000000010000L} );
    public static final BitSet FOLLOW_COLON_in_conditionalExpression2131                             = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_conditionalExpression_in_conditionalExpression2133             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression2149                = new BitSet( new long[]{0x0000000020000002L} );
    public static final BitSet FOLLOW_LOR_in_logicalOrExpression2152                                 = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression2154                = new BitSet( new long[]{0x0000000020000002L} );
    public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression2169              = new BitSet( new long[]{0x0000000040000002L} );
    public static final BitSet FOLLOW_LAND_in_logicalAndExpression2172                               = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression2174              = new BitSet( new long[]{0x0000000040000002L} );
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2189             = new BitSet( new long[]{0x0000000080000002L} );
    public static final BitSet FOLLOW_BOR_in_inclusiveOrExpression2192                               = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2194             = new BitSet( new long[]{0x0000000080000002L} );
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression2209                     = new BitSet( new long[]{0x0000000100000002L} );
    public static final BitSet FOLLOW_BXOR_in_exclusiveOrExpression2212                              = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression2214                     = new BitSet( new long[]{0x0000000100000002L} );
    public static final BitSet FOLLOW_equalityExpression_in_andExpression2229                        = new BitSet( new long[]{0x0000000200000002L} );
    public static final BitSet FOLLOW_BAND_in_andExpression2232                                      = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_equalityExpression_in_andExpression2234                        = new BitSet( new long[]{0x0000000200000002L} );
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression2249                 = new BitSet( new long[]{0x0000000C00000002L} );
    public static final BitSet FOLLOW_set_in_equalityExpression2253                                  = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression2260                 = new BitSet( new long[]{0x0000000C00000002L} );
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression2275                    = new BitSet( new long[]{0x000000F000000002L, 0x0000800000000000L} );
    public static final BitSet FOLLOW_set_in_relationalExpression2285                                = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression2321                    = new BitSet( new long[]{0x000000F000000002L} );
    public static final BitSet FOLLOW_111_in_relationalExpression2333                                = new BitSet( new long[]{0x0000000000000040L, 0x0000000000001FF0L} );
    public static final BitSet FOLLOW_typeSpec_in_relationalExpression2335                           = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression2352                      = new BitSet( new long[]{0x0000070000000002L} );
    public static final BitSet FOLLOW_set_in_shiftExpression2356                                     = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression2367                      = new BitSet( new long[]{0x0000070000000002L} );
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2382             = new BitSet( new long[]{0x0000180000000002L} );
    public static final BitSet FOLLOW_set_in_additiveExpression2386                                  = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2393             = new BitSet( new long[]{0x0000180000000002L} );
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression2408                = new BitSet( new long[]{0x0000600000000102L} );
    public static final BitSet FOLLOW_set_in_multiplicativeExpression2412                            = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression2424                = new BitSet( new long[]{0x0000600000000102L} );
    public static final BitSet FOLLOW_INC_in_unaryExpression2437                                     = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2439                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_DEC_in_unaryExpression2444                                     = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2446                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_MINUS_in_unaryExpression2451                                   = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2454                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_PLUS_in_unaryExpression2459                                    = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2463                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression2468             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_BNOT_in_unaryExpressionNotPlusMinus2479                        = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus2481             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LNOT_in_unaryExpressionNotPlusMinus2486                        = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus2488             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus2499                      = new BitSet( new long[]{0x0000000000000000L, 0x0000000000001FF0L} );
    public static final BitSet FOLLOW_builtInTypeSpec_in_unaryExpressionNotPlusMinus2501             = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus2503                      = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus2513             = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus2549                      = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_classTypeSpec_in_unaryExpressionNotPlusMinus2551               = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus2553                      = new BitSet( new long[]{0x007E000000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpressionNotPlusMinus2563 = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_postfixExpression_in_unaryExpressionNotPlusMinus2572           = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_primaryExpression_in_postfixExpression2586                     = new BitSet( new long[]{0x0001800000000092L} );
    public static final BitSet FOLLOW_DOT_in_postfixExpression2594                                   = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_postfixExpression2596                                 = new BitSet( new long[]{0x0001800000002092L} );
    public static final BitSet FOLLOW_LPAREN_in_postfixExpression2603                                = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_argList_in_postfixExpression2610                               = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_postfixExpression2616                                = new BitSet( new long[]{0x0001800000000092L} );
    public static final BitSet FOLLOW_DOT_in_postfixExpression2628                                   = new BitSet( new long[]{0x0000000000000000L, 0x0000000020000000L} );
    public static final BitSet FOLLOW_93_in_postfixExpression2630                                    = new BitSet( new long[]{0x0001800000000092L} );
    public static final BitSet FOLLOW_DOT_in_postfixExpression2637                                   = new BitSet( new long[]{0x0000000000000000L, 0x0000000040000000L} );
    public static final BitSet FOLLOW_94_in_postfixExpression2639                                    = new BitSet( new long[]{0x0000000000002080L} );
    public static final BitSet FOLLOW_LPAREN_in_postfixExpression2674                                = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_argList_in_postfixExpression2676                               = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_postfixExpression2678                                = new BitSet( new long[]{0x0001800000000092L} );
    public static final BitSet FOLLOW_DOT_in_postfixExpression2704                                   = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_postfixExpression2706                                 = new BitSet( new long[]{0x0001800000002092L} );
    public static final BitSet FOLLOW_LPAREN_in_postfixExpression2726                                = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_argList_in_postfixExpression2749                               = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_postfixExpression2771                                = new BitSet( new long[]{0x0001800000000092L} );
    public static final BitSet FOLLOW_DOT_in_postfixExpression2810                                   = new BitSet( new long[]{0x0000000000000000L, 0x0008000000000000L} );
    public static final BitSet FOLLOW_newExpression_in_postfixExpression2812                         = new BitSet( new long[]{0x0001800000000092L} );
    public static final BitSet FOLLOW_LBRACK_in_postfixExpression2818                                = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_postfixExpression2821                            = new BitSet( new long[]{0x0000000000000020L} );
    public static final BitSet FOLLOW_RBRACK_in_postfixExpression2823                                = new BitSet( new long[]{0x0001800000000092L} );
    public static final BitSet FOLLOW_set_in_postfixExpression2854                                   = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_identPrimary_in_primaryExpression2881                          = new BitSet( new long[]{0x0000000000000082L} );
    public static final BitSet FOLLOW_DOT_in_primaryExpression2893                                   = new BitSet( new long[]{0x0000000000000000L, 0x0000000002000000L} );
    public static final BitSet FOLLOW_89_in_primaryExpression2895                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_constant_in_primaryExpression2908                              = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_112_in_primaryExpression2913                                   = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_113_in_primaryExpression2918                                   = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_114_in_primaryExpression2923                                   = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_newExpression_in_primaryExpression2933                         = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_93_in_primaryExpression2938                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_94_in_primaryExpression2943                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LPAREN_in_primaryExpression2948                                = new BitSet( new long[]{0x007F980000002040L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_assignmentExpression_in_primaryExpression2950                  = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_primaryExpression2952                                = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_builtInType_in_primaryExpression2960                           = new BitSet( new long[]{0x0000000000000090L} );
    public static final BitSet FOLLOW_LBRACK_in_primaryExpression2966                                = new BitSet( new long[]{0x0000000000000020L} );
    public static final BitSet FOLLOW_RBRACK_in_primaryExpression2969                                = new BitSet( new long[]{0x0000000000000090L} );
    public static final BitSet FOLLOW_DOT_in_primaryExpression2976                                   = new BitSet( new long[]{0x0000000000000000L, 0x0000000002000000L} );
    public static final BitSet FOLLOW_89_in_primaryExpression2978                                    = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_IDENT_in_identPrimary2993                                      = new BitSet( new long[]{0x0000000000002092L} );
    public static final BitSet FOLLOW_DOT_in_identPrimary3031                                        = new BitSet( new long[]{0x0000000000000040L} );
    public static final BitSet FOLLOW_IDENT_in_identPrimary3033                                      = new BitSet( new long[]{0x0000000000002092L} );
    public static final BitSet FOLLOW_LPAREN_in_identPrimary3095                                     = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_argList_in_identPrimary3098                                    = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_identPrimary3100                                     = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LBRACK_in_identPrimary3133                                     = new BitSet( new long[]{0x0000000000000020L} );
    public static final BitSet FOLLOW_RBRACK_in_identPrimary3136                                     = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_115_in_newExpression3172                                       = new BitSet( new long[]{0x0000000000000040L, 0x0000000000001FF0L} );
    public static final BitSet FOLLOW_type_in_newExpression3174                                      = new BitSet( new long[]{0x0000000000002010L} );
    public static final BitSet FOLLOW_LPAREN_in_newExpression3180                                    = new BitSet( new long[]{0x007F980000002042L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_argList_in_newExpression3182                                   = new BitSet( new long[]{0x0000000000004000L} );
    public static final BitSet FOLLOW_RPAREN_in_newExpression3184                                    = new BitSet( new long[]{0x0000000000000202L} );
    public static final BitSet FOLLOW_classBlock_in_newExpression3187                                = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_newArrayDeclarator_in_newExpression3225                        = new BitSet( new long[]{0x0000000000000202L} );
    public static final BitSet FOLLOW_arrayInitializer_in_newExpression3228                          = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_expressionList_in_argList3247                                  = new BitSet( new long[]{0x0000000000000002L} );
    public static final BitSet FOLLOW_LBRACK_in_newArrayDeclarator3317                               = new BitSet( new long[]{0x007F980000002060L, 0x000F000060001FF0L} );
    public static final BitSet FOLLOW_expression_in_newArrayDeclarator3325                           = new BitSet( new long[]{0x0000000000000020L} );
    public static final BitSet FOLLOW_RBRACK_in_newArrayDeclarator3332                               = new BitSet( new long[]{0x0000000000000012L} );
    public static final BitSet FOLLOW_set_in_constant3348                                            = new BitSet( new long[]{0x0000000000000002L} );

}