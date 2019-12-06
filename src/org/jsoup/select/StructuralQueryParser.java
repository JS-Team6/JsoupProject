package org.jsoup.select;

import static org.jsoup.internal.Normalizer.normalize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.helper.Validate;

public class StructuralQueryParser extends CustomQueryParser {
	
	public StructuralQueryParser(AbstractQueryParser _parser){
		parser = _parser;
	}
	

    public StructuralQueryParser() {

    }


    protected void findElements() {
    	if (tq.matchChomp(":nth-child("))
        	cssNthChild(false, false);
        else if (tq.matchChomp(":nth-last-child("))
        	cssNthChild(true, false);
        else if (tq.matchChomp(":nth-of-type("))
        	cssNthChild(false, true);
        else if (tq.matchChomp(":nth-last-of-type("))
        	cssNthChild(true, true);
        else if (tq.matchChomp(":first-child"))
        	evals.add(new Evaluator.IsFirstChild());
        else if (tq.matchChomp(":last-child"))
        	evals.add(new Evaluator.IsLastChild());
        else if (tq.matchChomp(":first-of-type"))
        	evals.add(new Evaluator.IsFirstOfType());
        else if (tq.matchChomp(":last-of-type"))
        	evals.add(new Evaluator.IsLastOfType());
        else if (tq.matchChomp(":only-child"))
        	evals.add(new Evaluator.IsOnlyChild());
        else if (tq.matchChomp(":only-of-type"))
        	evals.add(new Evaluator.IsOnlyOfType());
        else if (tq.matchChomp(":empty"))
        	evals.add(new Evaluator.IsEmpty());
        else if (tq.matchChomp(":root"))
        	evals.add(new Evaluator.IsRoot());
		else // unhandled
			parser.findElements();
    }
    //pseudo selectors :first-child, :last-child, :nth-child, ...
    private static final Pattern NTH_AB = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", Pattern.CASE_INSENSITIVE);
    private static final Pattern NTH_B  = Pattern.compile("([+-])?(\\d+)");

	private void cssNthChild(boolean backwards, boolean ofType) {
		String argS = normalize(tq.chompTo(")"));
		Matcher mAB = NTH_AB.matcher(argS);
		Matcher mB = NTH_B.matcher(argS);
		final int a, b;
		if ("odd".equals(argS)) {
			a = 2;
			b = 1;
		} else if ("even".equals(argS)) {
			a = 2;
			b = 0;
		} else if (mAB.matches()) {
			a = mAB.group(3) != null ? Integer.parseInt(mAB.group(1).replaceFirst("^\\+", "")) : 1;
			b = mAB.group(4) != null ? Integer.parseInt(mAB.group(4).replaceFirst("^\\+", "")) : 0;
		} else if (mB.matches()) {
			a = 0;
			b = Integer.parseInt(mB.group().replaceFirst("^\\+", ""));
		} else {
			throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", argS);
		}
		if (ofType)
			if (backwards)
				evals.add(new Evaluator.IsNthLastOfType(a, b));
			else
				evals.add(new Evaluator.IsNthOfType(a, b));
		else {
			if (backwards)
				evals.add(new Evaluator.IsNthLastChild(a, b));
			else
				evals.add(new Evaluator.IsNthChild(a, b));
		}
	}
 
    

}
