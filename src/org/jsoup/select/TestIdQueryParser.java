package org.jsoup.select;

import static org.jsoup.internal.Normalizer.normalize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;

public class TestIdQueryParser extends CustomQueryParser {
	
	public TestIdQueryParser(AbstractQueryParser _parser){
		parser = _parser;
	}
	

    public TestIdQueryParser() {

    }


    protected void findElements() {
        if (tq.matchChomp("#"))
            byId();
		else // unhandled
			parser.findElements();
    }

    private void byId() {
        String id = tq.consumeCssIdentifier();
        Validate.notEmpty(id);
        evals.add(new Evaluator.Id(id));
    }

}
