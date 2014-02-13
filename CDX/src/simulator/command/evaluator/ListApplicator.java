package command.evaluator;

public interface ListApplicator
//used to apply things to every item in an ObjectList
{
	public void apply(Object o, int termNumber, int totalTerms);
	//termNumber starts with 1, goes up
}
