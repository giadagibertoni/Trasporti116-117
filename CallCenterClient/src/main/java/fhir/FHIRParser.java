/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

/**
 * Reppresent the parser for parse FHIR resources
 */
public class FHIRParser {
    private FHIRParser() { }
    private static IParser parser = null;

    private static void createParser() {
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
}
