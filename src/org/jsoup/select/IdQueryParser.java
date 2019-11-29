package org.jsoup.select;

import org.jsoup.helper.Validate;

public class IdQueryParser extends QueryParser {

	protected IdQueryParser(String query) {
		super(query);
		// TODO Auto-generated constructor stub
	}
    public static Evaluator parse(String query) {
        try {
//        	System.out.println("1"+query);
        	IdQueryParser p = new IdQueryParser(query);
            return p.parse();
        } catch (IllegalArgumentException e) {
            throw new Selector.SelectorParseException(e.getMessage());
        }
    }
    Evaluator parse() {
        tq.consumeWhitespace();

        if (tq.matchesAny(combinators)) { // if starts with a combinator, use root as elements
            evals.add(new StructuralEvaluator.Root());
            combinator(tq.consume());
        } else {
            findElements();
        }

        while (!tq.isEmpty()) {
            // hierarchy and extras
            boolean seenWhite = tq.consumeWhitespace();
            if (tq.matchesAny(combinators)) {
                combinator(tq.consume());
            } else if (seenWhite) {
                combinator(' ');
            } else { // E.class, E#id, E[attr] etc. AND
                findElements(); // take next el, #. etc off queue
            }
        }

        if (evals.size() == 1)
            return evals.get(0);

        return new CombiningEvaluator.And(evals);
    }
    protected void findElements() {
    	
        if (tq.matchChomp("#"))
            byId();
        else
        	super.findElements();
    }
    private void byId() {
    	System.out.println("123");
        String id = tq.consumeCssIdentifier();
        Validate.notEmpty(id);
        evals.add(new Evaluator.Id(id));
    }
}
