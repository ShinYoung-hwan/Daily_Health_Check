
public class LossOfSmell extends Symptoms {

	public LossOfSmell() {
		super("LossOfSmell", 2, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getExplainationOfSymptom() {
		// TODO Auto-generated method stub

		return String.format("%s[%s]\n - %s\n", "Definition", "Cambridge Dictionary",
				"one's sense of smell is dulled or lost.\n");
	}
}
