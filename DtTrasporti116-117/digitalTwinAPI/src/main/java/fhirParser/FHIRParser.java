/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package fhirParser;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.StrictErrorHandler;

/**
 * Reppresent the parser for parse FHIR resources
 */
public class FHIRParser {
    private FHIRParser() { }
    private static IParser parser = null;

    private static void createParser() {
        // Create a context
        FhirContext ctx = FhirContext.forR4();
        parser = ctx.newJsonParser();
    }

    /**
     * @return the parser
     */
    public static IParser getParser() {
        if (parser == null) {
            createParser();
        }
        return parser;
    }

    /**
     * @return the parser
     */
    public static Boolean inputJSONResourceIsCorrect(String jsonResource) {
        getParser().setParserErrorHandler(new StrictErrorHandler());
        try {
            getParser().parseResource(jsonResource);
        }catch (Exception e){
            return false;
        }
        return true;
    }



}
