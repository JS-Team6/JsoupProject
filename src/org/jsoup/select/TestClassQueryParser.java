package org.jsoup.select;

import static org.jsoup.internal.Normalizer.normalize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;

public class TestClassQueryParser extends CustomQueryParser {
	
	public TestClassQueryParser(AbstractQueryParser _parser){
		parser = _parser;
	}
	

    public TestClassQueryParser() {

    }


    protected void findElements() {
        if (tq.matchChomp("."))
            byClass();
		else // unhandled
			parser.findElements();
    }

    private void byClass() {
        String className = tq.consumeCssIdentifier();
        Validate.notEmpty(className);
        evals.add(new Evaluator.Class(className.trim()));
    }

}
