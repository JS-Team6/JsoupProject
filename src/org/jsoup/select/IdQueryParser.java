package org.jsoup.select;

import org.jsoup.helper.Validate;

public class IdQueryParser extends CustomQueryParser {
	IdQueryParser(AbstractQueryParser parser){
		this.queryParser = parser;
	}
	@Override
	protected Evaluator parse(String subQuery) {
		// TODO Auto-generated method stub
		
		return this.queryParser.parse(subQuery);
	}

	@Override
	protected void findElements() {
		// TODO Auto-generated method stub
	    if (tq.matchChomp("#"))
	    	byId();
	    else {
	    	this.queryParser.findElements();
	    }
	    
	}

	private void byId() {
		String id = tq.consumeCssIdentifier();
		Validate.notEmpty(id);
		evals.add(new Evaluator.Id(id));
	}
}
